package co.dataswitch.MetadataCatalog;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.dataswitch.Migrate.MigrateController;
import co.dataswitch.config.bean.Configurations;
import co.dataswitch.config.bean.Configurations.Configuration;
import co.dataswitch.config.bean.Configurations.Configuration.Properties.Property;
import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.Sync;
import co.dataswitch.utils.UnmarshallerController;
import co.dataswitch.utils.Utils;

@Controller
public class CatalogController {
	
	
	private static final Logger dslogger = Logger.getLogger(CatalogController.class);

	@GetMapping(value = "home/migrate/schema-migrator/catalogs")
	public ModelAndView catalogPage(HttpServletRequest request, Model model) {
		dslogger.info("Entering method to get catalog page");
		ModelAndView mv = new ModelAndView("migrate/schemaMigrator/catalog/catalog");

		Configurations ds = UnmarshallerController.unmarshallDataSwitchConfigXML(request);
		List<Catalogs> catalogList = getAllCatalogs(request);
		for (Iterator<Catalogs> iter = catalogList.listIterator(); iter.hasNext(); ) {
			Catalogs catalog = iter.next();
			if(ds != null)
				catalog.setDbType(getUpdatedDbType(catalog.getDbType(), ds) != null ? getUpdatedDbType(catalog.getDbType(), ds) : catalog.getDbType());
		}
		JSONArray dbListArray = new JSONArray();
		List<String> catalogingTools = new ArrayList<>();
		JSONObject configList = new JSONObject();
		if(ds != null)
		{
			configList = getDBListArray(ds, dbListArray, catalogingTools, configList);
		}
		request.getSession().setAttribute("dbList", configList.get("dbList").toString());
		mv.addObject("dbListArray", configList.get("dbList"));
		mv.addObject("catalogingToolList", configList.get("catalogingTools"));
		mv.addObject("catalogArray", catalogList);
		mv.addObject("type", model.asMap().get("type"));
		mv.addObject("message", model.asMap().get("message"));

		return mv;
	}
	
	public static String getUpdatedDbType(String dbType, Configurations ds) {
		for(Configuration conf : ds.getConfiguration()){
			if(conf.getName().equalsIgnoreCase("Cloud DBs")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("NoSQL DBs")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("Bigdata")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("Relational DBs")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("Files")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
		}
		return null;
	}
	
	public static List<Catalogs> getAllCatalogs(HttpServletRequest request) {
		dslogger.info("Entering method to get all catalogs from db");
		ArrayList<String> data = null;
		String tableDocName = "Catalogs";
		Catalogs catalogData = null;
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		try {
			data = DBEngine_new.getKeyValuesList(tableDocName,dataStoreDir + File.separator + username);
		} catch (Exception e) {
			dslogger.error("Error in getting values from db - "+e.getMessage());
		}
		List<Catalogs> catalogList = new ArrayList<>();
		if(data != null)
		{
			for(int i = 0; i < data.size(); i++)
			{
				try {
					catalogData = (Catalogs) DBEngine_new.getData("Catalogs", data.get(i),dataStoreDir + File.separator + username);
					System.out.println("catalogData--"+catalogData);
					catalogList.add(catalogData);

				}catch (Exception e) {
					dslogger.error("Error in getting values from db - "+e.getMessage());
				}
			}
		}else{
			dslogger.error("Unable to fetch catalog data from db");
		}
		System.out.println(catalogList);
		return catalogList;
	}


	@PostMapping(value = "home/migrate/schema-migrator/catalogs/add-catalog")
	public ModelAndView addConfiguration(HttpServletRequest request, @ModelAttribute("catalog") Catalogs catalog, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering method add new catalog - "+catalog.getCatalogName());
		String username = (String) request.getSession().getAttribute("username");
		boolean isInsert = false;
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		File catalogsFolder = new File(dataStoreDir+"/"+username+"/Catalogs");
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs");
		if(!catalogsFolder.exists()){
			catalogsFolder.mkdirs();
		}
		try {
			dslogger.info("Inserting catalog data into database");
			isInsert = DBEngine_new.insertData("Catalogs", catalog.getCatalogName(), catalog,dataStoreDir+"/"+username);
			if(isInsert){
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Catalog has been added successfully.");
			}else{
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to create new catalog. Please try again after some time.");
			}
		} catch (Exception e) {
			dslogger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("type", "Failed");
			if(e.getMessage().equals("Key already exists - Cannot insert this record")){
				redirectAttributes.addFlashAttribute("message", "Catalog name already exists. Please use a different name.");
			}else{
				redirectAttributes.addFlashAttribute("message", e.getMessage());
			}
			
			
		}
		return mv;
	}

	@ResponseBody
	@GetMapping(value = "home/migrate/schema-migrator/catalog/getDetails")
	public String editCatalog(HttpServletRequest request) {
		String catalogName = request.getParameter("catalogName");
		catalogName = Jsoup.clean(catalogName, Whitelist.none());
		dslogger.info("Entering method to get catalog details - "+catalogName);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		Catalogs catalogData = null;
		String jsonString = null;
		try {
			catalogData = (Catalogs) DBEngine_new.getData("Catalogs", catalogName ,dataStoreDir+"/"+username);
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(catalogData);
		} catch (Exception e) {
			dslogger.error(e.getMessage());
		}
		return jsonString;
	}
	
	public static Catalogs getCatalogObject(HttpServletRequest request,String catalogName){
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		Catalogs catalog = null;
		try {
			catalog = (Catalogs) DBEngine_new.getData("Catalogs", catalogName, dataStoreDir+"/"+username);
		} catch (Exception e) {
			dslogger.error("Unable to get catalog object for catalog - "+catalogName);
		}
		return catalog;
	}

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/edit-catalog")
	public ModelAndView editConfig(HttpServletRequest request, @ModelAttribute("catalog") Catalogs catalog, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering method to edit catalog - "+catalog.getCatalogName());
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs");
		Catalogs existingCatalog = getCatalogObject(request, catalog.getCatalogName());
		if(existingCatalog != null)
		{
			existingCatalog.setCatalogDescription(catalog.getCatalogDescription());
			existingCatalog.setConnectionName(catalog.getConnectionName());
			existingCatalog.setUserName(catalog.getUserName());
			existingCatalog.setPassword(catalog.getPassword());
			existingCatalog.setHostName(catalog.getHostName());
			existingCatalog.setSchemaName(catalog.getSchemaName());
			existingCatalog.setDirectoryPath(catalog.getDirectoryPath());
			existingCatalog.setFileExtenstion(catalog.getFileExtenstion());
			existingCatalog.setHeaderRows(catalog.getHeaderRows());
			existingCatalog.setColumnDelimiter(catalog.getColumnDelimiter());
			existingCatalog.setRowDelimiter(catalog.getRowDelimiter());
			existingCatalog.setTextQualifier(catalog.getTextQualifier());
			existingCatalog.setPort(catalog.getPort());
			existingCatalog.setAccountName(catalog.getAccountName());
			existingCatalog.setStorageType(catalog.getStorageType());
		}else{
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", "Unable to fetch catalog for update. Please try again after sometime.");
		}

		boolean isUpdate = false;
		try {
			dslogger.info("Updating catalog data");
			isUpdate = DBEngine_new.updateData("Catalogs", catalog.getCatalogName(), existingCatalog,dataStoreDir+"/"+username);
			if(isUpdate){
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Catalog has been updated successfully.");
			}else{
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to update Catalog. Please try again after sometime.");
			}
		} catch (Exception e) {
			dslogger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", "Update Failed - "+e.getMessage());
		}
		return mv;
	}

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/delete-catalog")
	public ModelAndView deleteCatalog(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering method to delete catalog");
		String catalogName = request.getParameter("catalogName");
		catalogName = Jsoup.clean(catalogName, Whitelist.none());
		System.out.println("name--"+catalogName);
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs");
		boolean isDelete = false;
		String username = (String) request.getSession().getAttribute("username");
		String noSqlpath = (String) request.getSession().getAttribute("nosqlPath");
		try {
			isDelete = DBEngine_new.deleteData("Catalogs", catalogName, noSqlpath+"/"+username);
			if(isDelete){
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Catalog has been deleted successfully.");	
			}
			else{
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to delete Catalog. Please try again after sometime.");
			}
		}catch (Exception e) {
			dslogger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return mv;
	}

	public static JSONObject getDBListArray(Configurations ds, JSONArray dbListArray, List<String> catalogingTools, JSONObject configList) {
		for(Configuration conf : ds.getConfiguration()){
			JSONArray dbList = new JSONArray();
			if(conf.getName().equalsIgnoreCase("Cloud DBs")){
				JSONObject dbObj = new JSONObject();
				dbObj.put("name", "Cloud DBs");
				for(Property prop : conf.getProperties().getProperty()){
					JSONObject dbListObj = new JSONObject();
					dbListObj.put("name", prop.getName());
					dbListObj.put("value", prop.getValue());
					dbList.put(dbListObj);
				}
				dbObj.put("dbList", dbList);
				dbListArray.put(dbObj);
			}
			if(conf.getName().equalsIgnoreCase("NoSQL DBs")){
				JSONObject dbObj = new JSONObject();
				dbObj.put("name", "NoSQL DBs");
				for(Property prop : conf.getProperties().getProperty()){
					JSONObject dbListObj = new JSONObject();
					dbListObj.put("name", prop.getName());
					dbListObj.put("value", prop.getValue());
					dbList.put(dbListObj);
				}
				dbObj.put("dbList", dbList);
				dbListArray.put(dbObj);
			}
			if(conf.getName().equalsIgnoreCase("Bigdata")){
				JSONObject dbObj = new JSONObject();
				dbObj.put("name", "Bigdata");
				for(Property prop : conf.getProperties().getProperty()){
					JSONObject dbListObj = new JSONObject();
					dbListObj.put("name", prop.getName());
					dbListObj.put("value", prop.getValue());
					dbList.put(dbListObj);
				}
				dbObj.put("dbList", dbList);
				dbListArray.put(dbObj);
			}
			if(conf.getName().equalsIgnoreCase("Relational DBs")){
				JSONObject dbObj = new JSONObject();
				dbObj.put("name", "Relational DBs");
				for(Property prop : conf.getProperties().getProperty()){
					JSONObject dbListObj = new JSONObject();
					dbListObj.put("name", prop.getName());
					dbListObj.put("value", prop.getValue());
					dbList.put(dbListObj);
				}
				dbObj.put("dbList", dbList);
				dbListArray.put(dbObj);
			}
			if(conf.getName().equalsIgnoreCase("Files")){
				JSONObject dbObj = new JSONObject();
				dbObj.put("name", "Files");
				for(Property prop : conf.getProperties().getProperty()){
					JSONObject dbListObj = new JSONObject();
					dbListObj.put("name", prop.getName());
					dbListObj.put("value", prop.getValue());
					dbList.put(dbListObj);
				}
				dbObj.put("dbList", dbList);
				dbListArray.put(dbObj);
			}
			if(conf.getName().equalsIgnoreCase("Cataloging Tools")){
				for(Property prop : conf.getProperties().getProperty()){
					catalogingTools.add(prop.getName());
				}
			}
		}
		configList.put("dbList", dbListArray);
		configList.put("catalogingTools", catalogingTools);
		return configList;
	}
}
