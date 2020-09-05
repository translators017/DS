package co.dataswitch.MetadataCatalog;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.dataswitch.config.bean.Configurations;
import co.dataswitch.config.bean.Configurations.Configuration;
import co.dataswitch.config.bean.Configurations.Configuration.Properties.Property;
import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.Entity;
import co.dataswitch.utils.UnmarshallerController;
import co.dataswitch.utils.Utils;

@Controller
public class EntityController {

	private static final Logger dslogger = Logger.getLogger(EntityController.class);

	@SuppressWarnings("unchecked")
	@GetMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}")
	public ModelAndView getAttributesforEntity(HttpServletRequest request, @PathVariable("catalogName") String catalogName , @PathVariable("entityName") String entityName){
		dslogger.info("Entering method for getting attributes for entity - "+entityName);
		ModelAndView mv = null;
		Catalogs catalog = CatalogController.getCatalogObject(request,catalogName);
		List<String> dtList = new ArrayList<>();
		JSONArray entityAttrArray = new JSONArray();
		ObjectMapper mapper = null;
		String jsonString = null;
		if(catalog != null)
		{
			//mv = new ModelAndView("rdw/Intelligent_Mapper/attributes");
			mv = new ModelAndView("migrate/schemaMigrator/catalog/attributes");
			String dbToolName= catalog.getDbType();
			if(dbToolName != null && !dbToolName.equals(""))
			{
				DataTypeConvertor dtypConv = new DataTypeConvertor();
				dtList = dtypConv.getAllDataType(Utils.toolNo(dbToolName));
			}

			for(int i=0;i<catalog.getEntityLst().size();i++){
				if(catalog.getEntityLst().get(i).getPhysicalName().equalsIgnoreCase(entityName)){
					mv.addObject("attrList", catalog.getEntityLst().get(i).getAttrList());
				}else{
					mapper = new ObjectMapper();
					jsonString = null;
					try {
						jsonString = mapper.writeValueAsString(catalog.getEntityLst().get(i).getAttrList());
						JSONObject entityJSONObect = new JSONObject();
						entityJSONObect.put("entityName", catalog.getEntityLst().get(i).getPhysicalName());
						entityJSONObect.put("attrList", jsonString);
						entityAttrArray.add(entityJSONObect);
					} catch (JsonProcessingException e) {
						dslogger.error("Unable to map attribute object to json string - "+e.getMessage());
					}
				}
			}
			TreeMap<String, List<String>> treeMap = Utils.getCatalogTree(request);
			mv.addObject("treeMap", treeMap);

			List<Catalogs> catalogArray = CatalogController.getAllCatalogs(request);
			mapper = new ObjectMapper();
			jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(catalogArray);
			} catch (JsonProcessingException e) {
				dslogger.error("Unable to map object to string - "+e.getMessage());
			}
			mv.addObject("selectedCatalog", catalogName);
			mv.addObject("selectedEntity", entityName);
			mv.addObject("catalogjsonString", jsonString);
			mv.addObject("DatatypeList", dtList);
			mv.addObject("success", "none");
			mv.addObject("error", "none");
			mv.addObject("entityAttrArray", entityAttrArray);
		}
		else
		{
			//mv = new ModelAndView("catalogs/"+catalogName);
			mv.addObject("type", "Failed");
			mv.addObject("message", "Unable to fetch catalog object from db. Please check the database path.");
		}
		return mv;
	}

	@GetMapping(value = "home/migrate/schema-migrator/catalogs/{name}")
	public ModelAndView getCatalogDetails(HttpServletRequest request, @PathVariable("name") String catalogName, Model model){
		dslogger.info("Getting entity details for catalog - "+catalogName);
		//ModelAndView mv = new ModelAndView("rdw/Intelligent_Mapper/entities");
		ModelAndView mv = new ModelAndView("migrate/schemaMigrator/catalog/entities");
		Catalogs catalog = CatalogController.getCatalogObject(request,catalogName);
		Configurations ds = UnmarshallerController.unmarshallDataSwitchConfigXML(request);
		String dbType = "", dbList = "";
		JSONArray dbListArray = new JSONArray();
		if(ds != null){
			dbType = Utils.getUpdatedDbType(catalog.getDbType(),ds);
			dbList = getDBListArray(ds, dbListArray);
		}else{
			dbType = catalog.getDbType();
		}
		String jsonString = "";
		if(catalog != null)
		{
			mv.addObject("entityList", catalog.getEntityLst());
			mv.addObject("catalogdbType", dbType);
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(catalog.getEntityLst());
		}catch (Exception e) {
			dslogger.error("Unable to fetch entity data from db for catalog - "+catalog.getCatalogName());
		}
		
		mv.addObject("dbList", dbList);
		mv.addObject("entityJson", jsonString);
		mv.addObject("selectedCatalog", catalogName);
		TreeMap<String, List<String>> treeMap = Utils.getCatalogTree(request);
		mv.addObject("treeMap", treeMap);
		mv.addObject("type", model.asMap().get("type"));
		mv.addObject("message", model.asMap().get("message"));
		dslogger.info("Returning to entity page");
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	private String getDBListArray(Configurations ds, JSONArray dbListArray) {
		for(Configuration conf : ds.getConfiguration()){
			JSONArray dbList = new JSONArray();
			if(conf.getName().equalsIgnoreCase("Files")){
				JSONObject dbObj = new JSONObject();
				dbObj.put("name", "Files");
				for(Property prop : conf.getProperties().getProperty()){
					JSONObject dbListObj = new JSONObject();
					dbListObj.put("name", prop.getName());
					dbListObj.put("value", prop.getValue());
					dbList.add(dbListObj);
				}
				dbObj.put("dbList", dbList);
				dbListArray.add(dbObj);
			}
		}
		return dbListArray.toString();
	}

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/saveEntity")
	public ModelAndView saveEntityDetails(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable("catalogName") String catalogName){
		dslogger.info("Entering method to add entity");
		String physicalName = Jsoup.clean(request.getParameter("physicalName"), Whitelist.none());
		String logicalName = Jsoup.clean(request.getParameter("logicalName"), Whitelist.none());
		String description = Jsoup.clean(request.getParameter("description"), Whitelist.none());
		String entityType = Jsoup.clean(request.getParameter("entityType"), Whitelist.none());
		String storageType = Jsoup.clean(request.getParameter("storageType"), Whitelist.none());
		String directoryPath = Jsoup.clean(request.getParameter("directoryPath"), Whitelist.none());
		String fileExtenstion = Jsoup.clean(request.getParameter("fileExtenstion"), Whitelist.none());
		String headerRows = Jsoup.clean(request.getParameter("headerRows"), Whitelist.none());
		String textQualifier = Jsoup.clean(request.getParameter("textQualifier"), Whitelist.none());
		String columnDelimiter = Jsoup.clean(request.getParameter("columnDelimiter"), Whitelist.none());
		String rowDelimiter = Jsoup.clean(request.getParameter("rowDelimiter"), Whitelist.none());
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs/"+catalogName);

		Entity en = new Entity();
		en.setPhysicalName(physicalName);
		en.setLogicalName(logicalName);
		en.setDescription(description);
		en.setCatalogName(catalogName);
		en.setEntityType(entityType);
		en.setStorageType(storageType);
		en.setDirectoryPath(directoryPath);
		en.setFileExtenstion(fileExtenstion);
		en.setHeaderRows(headerRows);
		en.setTextQualifier(textQualifier);
		en.setColumnDelimiter(columnDelimiter);
		en.setRowDelimiter(rowDelimiter);

		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		boolean isUpdate = false;
		String username = (String) request.getSession().getAttribute("username");
		Catalogs catalog = CatalogController.getCatalogObject(request,catalogName);
		if(catalog != null)
		{
			for (Iterator<Entity> iter = catalog.getEntityLst().iterator(); iter.hasNext(); ) 
			{
				Entity entity = iter.next();
				if (entity.getPhysicalName().equalsIgnoreCase(physicalName)) {
					iter.remove();
					break;
				}
			}
			catalog.getEntityLst().add(en);
			try {
				isUpdate = DBEngine_new.updateData("Catalogs", catalogName, catalog,dataStoreDir + File.separator + username);
				if(isUpdate)
				{
					redirectAttributes.addFlashAttribute("type", "Success");
					redirectAttributes.addFlashAttribute("message", "Entity has been added successfully.");
				}
				else
				{
					redirectAttributes.addFlashAttribute("type", "Failed");
					redirectAttributes.addFlashAttribute("message", "Failed to create entity. Please try again after some time.");
				}
			} catch (Exception e) {
				dslogger.error("Error inserting to db - "+e.getMessage());
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to create entity. "+e.getMessage());
			}
		}else
		{
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", "Unable to retrieve catalog - "+catalogName+" from db. Please check the database path.");
		}
		return mv;
	}

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/delete-entity")
	public ModelAndView deleteEntity(@PathVariable("catalogName") String catalogName, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String physicalName = request.getParameter("physicalName");
		dslogger.info("Entering function to delete entity " +physicalName+ " from catalog "+catalogName);
		boolean deleteStatus = false;
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs/"+catalogName);
		String username = (String) request.getSession().getAttribute("username");
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		Catalogs catalogObj = CatalogController.getCatalogObject(request, catalogName);
		if(catalogObj != null)
		{
			for (Iterator<Entity> iter = catalogObj.getEntityLst().listIterator(); iter.hasNext(); ) {
				Entity entityNm = iter.next();
				if(entityNm.getPhysicalName().equals(physicalName)){
					iter.remove();
					break;
				}
			}
			try {
				deleteStatus = DBEngine_new.updateData("Catalogs", catalogObj.getCatalogName(), catalogObj,dataStoreDir + File.separator + username);
				if(deleteStatus)
				{
					redirectAttributes.addFlashAttribute("type", "Success");
					redirectAttributes.addFlashAttribute("message", "Entity has been deleted successfully.");
				}
				else
				{
					redirectAttributes.addFlashAttribute("type", "Failed");
					redirectAttributes.addFlashAttribute("message", "Failed to delete entity. Please try again after some time.");
				}
			} catch (Exception e) {
				dslogger.error("Error deleting entity from catalog - "+e.getMessage());
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to delete entity. "+e.getMessage());
			}
		}else{
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", "Unable to fetch catalog data from db");
		}
		dslogger.debug(mv.getViewName());
		return mv;
	}

	@ResponseBody
	@GetMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}/getDetails")
	public String getToolDetails(HttpServletRequest request, @PathVariable("catalogName") String catalogName, @PathVariable("entityName") String entityName) {
		dslogger.info("Entering method get the entity details for "+entityName+ " in catalog - "+catalogName);
		Catalogs catalog = CatalogController.getCatalogObject(request, catalogName);
		String jsonString = "";
		if(catalog != null){
			try {
				if(catalog.getCatalogName().equalsIgnoreCase(catalogName)){
					ObjectMapper mapper = new ObjectMapper();
					jsonString = mapper.writeValueAsString(catalog);
				}
			}catch (Exception e) {
				dslogger.error("Unable to fetch entity data from db for catalog - "+catalog.getCatalogName());
			}
		}else
		{
			dslogger.error("Unable to fetch catalog data from db for catalog - "+catalog.getCatalogName());
		}
		return jsonString;
	}

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/{catalogName}/{entityName}/editEntity")
	public ModelAndView editEntityDetails(@ModelAttribute("Entity") Entity entity, HttpServletRequest request, @PathVariable("catalogName") String catalogName, @PathVariable("entityName") String entityName, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering function to update entities from catalog "+catalogName);
		boolean updateStatus = false;
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs/"+catalogName);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		Catalogs catalogObj = CatalogController.getCatalogObject(request, catalogName);
		if(catalogObj != null)
		{
			try{

				for(int i = 0; i < catalogObj.getEntityLst().size(); i++)
				{
					if(catalogObj.getEntityLst().get(i).getPhysicalName().equals(entityName))
					{
						Entity en = catalogObj.getEntityLst().get(i);
						en.setLogicalName(entity.getLogicalName());
						en.setDescription(entity.getDescription());
						en.setEntityType(entity.getEntityType());
						en.setStorageType(entity.getStorageType());
						en.setDirectoryPath(entity.getDirectoryPath());
						en.setFileExtenstion(entity.getFileExtenstion());
						en.setHeaderRows(entity.getHeaderRows());
						en.setTextQualifier(entity.getTextQualifier());
						en.setColumnDelimiter(entity.getColumnDelimiter());
						en.setRowDelimiter(entity.getRowDelimiter());
					}
				}
				updateStatus = DBEngine_new.updateData("Catalogs", catalogObj.getCatalogName(), catalogObj,dataStoreDir + File.separator + username);
				if(updateStatus)
				{
					redirectAttributes.addFlashAttribute("type", "Success");
					redirectAttributes.addFlashAttribute("message", "Entity has been updated successfully.");
				}else
				{
					redirectAttributes.addFlashAttribute("type", "Failed");
					redirectAttributes.addFlashAttribute("message", "Failed to update entity.");
				}
			}catch(Exception e){
				dslogger.error("Error - "+e.getMessage());
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to update entity - "+e.getMessage());
			}
		}
		else
		{
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", "Failed to update entity. Unable to fetch data from db");
		}
		return mv;
	}

	public static boolean editEntityDetails(String physicalName,String logicalName,String description,String catalogName,HttpServletRequest request) {
		dslogger.info("Entering method to update entity details");
		System.out.println("Entering function to update entity details");
		System.out.println(physicalName+" : "+logicalName+" : "+description+" : "+catalogName);
		ArrayList<String> data = null;
		Catalogs catalogData = null;
		boolean updateStatus = false;
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		try {
			data = DBEngine_new.getKeyValuesList("Catalogs",dataStoreDir+"/"+username);
			//data = DBEngine.getKeyValuesList("Catalogs",dataStoreDir+"/"+username);
		} catch (Exception e) {

			e.printStackTrace();
		}

		//get data values for each of the catalogs.

		for(int i=0;i<data.size();i++){

			//looping through the data
			try {
				catalogData = (Catalogs) DBEngine_new.getData("Catalogs", data.get(i),dataStoreDir+"/"+username);
				for(int k=0;k<catalogData.getEntityLst().size();k++){
					if(catalogData.getEntityLst().get(k).getPhysicalName().equalsIgnoreCase(physicalName.trim())){
						dslogger.info("Updating catalog details");
						catalogData.getEntityLst().get(k).setLogicalName(logicalName);
						catalogData.getEntityLst().get(k).setDescription(description);
					}
				}
				updateStatus = DBEngine_new.updateData("Catalogs", catalogData.getCatalogName(), catalogData,dataStoreDir+"/"+username);
				//updateStatus = DBEngine.updateData("Catalogs", catalogData.getCatalogName(), catalogData,dataStoreDir+"/"+username);

			}catch (ClassCastException e) {
				updateStatus = false;
			}catch (Exception e) {
				updateStatus = false;
			}

		}
		return updateStatus;
	}

	public static String deleteEntity(String catalogName, String entityName, HttpServletRequest request) {
		dslogger.info("Entering function to delete entity " +entityName+ "from catalog "+catalogName);
		Catalogs catalogData = null;
		boolean deleteStatus = false;
		String username = (String) request.getSession().getAttribute("username");
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");		
		Catalogs catalogObj = CatalogController.getCatalogObject(request, catalogName);
		String message="";
		if(catalogObj != null)
		{
			for (Iterator<Entity> iter = catalogObj.getEntityLst().listIterator(); iter.hasNext(); ) {
				Entity entityNm = iter.next();
				if(entityNm.getPhysicalName().equals(entityName)){
					iter.remove();
					break;
				}
			}
			try {
				deleteStatus = DBEngine_new.updateData("Catalogs", catalogObj.getCatalogName(), catalogObj,dataStoreDir + File.separator + username);
				if(deleteStatus)
				{
					message="Entity has been deleted successfully.";
				}
				else
				{
					message="Failed to delete entity. Please try again after some time.";
				}
			} catch (Exception e) {
				dslogger.error("Error deleting entity from catalog - "+e.getMessage());
				message=e.getMessage();
			}			
		}else{
			message="Unable to fetch catalog data from db";
		}

		return message;
	}
}

