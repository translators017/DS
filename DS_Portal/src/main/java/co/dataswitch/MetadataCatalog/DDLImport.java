package co.dataswitch.MetadataCatalog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dataSwitch.ds.sql.SQLObjeds.DDLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.SQLColstruct;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;

import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Attribute;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.Entity;
import co.dataswitch.utils.Utils;

@Controller
public class DDLImport {


	private static final Logger dslogger = Logger.getLogger(DDLImport.class);

	@PostMapping(value = "home/migrate/schema-migrator/catalogs/extract-ddl")
	@ResponseBody
	public String parseDDL(Model model, MultipartHttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		dslogger.info("Parsing DDL file");
		JSONArray tblArray = new JSONArray();
		JSONObject outputObj = new JSONObject();
		request.getSession().removeAttribute("entityList");
		request.getSession().removeAttribute("treeMap");
		String message = "";
		String selectedCatalog = request.getParameter("catalogName");
		selectedCatalog = Jsoup.clean(selectedCatalog, Whitelist.none());
		dslogger.debug("selectedCatalog--->" + selectedCatalog);
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		dslogger.info("DDLFile::" + mpf.getOriginalFilename());
		Catalogs catalog = Utils.getCatalogObject(request, selectedCatalog);
		if (catalog != null) {
			String filename = "";
			String root = request.getServletContext().getRealPath("/");
			String sessionId = request.getSession(false).getId();
			String fullPath = root + "temp" + File.separator + sessionId;
			String selectedDBType = catalog.getDbType();
			if (!mpf.isEmpty()) {
				try {
					byte[] bytes = mpf.getBytes();
					filename = mpf.getOriginalFilename();

					File fpath = new File(fullPath);
					if (!fpath.exists()) {
						fpath.mkdirs();
					}
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath, filename)));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			String inputSQL = SQLConverter.getBufferedReadSQL(fullPath + File.separator + filename);

			String[] sqlQueries = inputSQL.split(";");
			for (int i = 0; i < sqlQueries.length; i++) {
				if (sqlQueries[i].trim().length() > 0) {
					try {
						SQLQuery sqlObjt = SQLConverter.processSQl(sqlQueries[i].trim(),
								Utils.toolNo(selectedDBType),
								Utils.toolNo(selectedDBType));
						System.out.println(sqlObjt);
						if (sqlObjt.dmlType.equalsIgnoreCase("CREATE")) {
							// part to validate catalog selected and ddl
							// uploaded
							DDLQuery ddlQueryValidationChk = sqlObjt.ddlQuery;
							for (int v = 0; v < ddlQueryValidationChk.colsList.size(); v++) {
								SQLColstruct sqlColObj = (SQLColstruct) ddlQueryValidationChk.colsList.get(v);
								if (sqlColObj.dataType.equalsIgnoreCase("null")
										|| sqlColObj.dataType.equalsIgnoreCase("") || sqlColObj.dataType == null) {
									message = "Invalid DDL. Please upload a valid " + selectedDBType + " DDL.";
									return message;
								}
							}

							SQLElement tblList = (SQLElement) sqlObjt.ddlQuery.tableName;
							JSONObject tblObject = new JSONObject();
							tblObject.put("tableName", tblList.name);
							JSONArray colArray = new JSONArray();
							
							DDLQuery ddlQuery = sqlObjt.ddlQuery;
							ArrayList<SQLObject> colsList = ddlQuery.colsList;
							for (int j = 0; j < colsList.size(); j++) {
								JSONObject colObj = new JSONObject();
								SQLColstruct sqlColObj = (SQLColstruct) colsList.get(j);
								colObj.put("physicalName", sqlColObj.colName.replace(" ", "_"));
								colObj.put("logicalName", sqlColObj.colName);
								colObj.put("datatype", sqlColObj.dataType);
								colObj.put("length_precision", sqlColObj.length_precision);
								colObj.put("scale", sqlColObj.Scale);
								if (sqlColObj.nullType!= null && sqlColObj.nullType.trim().equalsIgnoreCase("primary key")) {
									colObj.put("nullable", "No");
								} else {
									colObj.put("nullable", "Yes");
								}
								colArray.put(colObj);
							}
							tblObject.put("cols", colArray);
							tblArray.put(tblObject);
							

							/*if (tblName != null) {
								entity = new Entity();

								if (!catalog.getEntityLst().isEmpty()) {
									for (int j = 0; j < catalog.getEntityLst().size(); j++) {
										if (catalog.getEntityLst().get(j).getPhysicalName()
												.equalsIgnoreCase(tblName)) {
											catalog.getEntityLst().remove(j);
											j--;
											catalog.setNoOfEntities(catalog.getNoOfEntities() - 1);
										}
									}
								}
								catalog.setNoOfEntities(catalog.getNoOfEntities() + 1);
								entity.setPhysicalName(tblName.replaceAll(" ", "_"));
								entity.setLogicalName(tblName.replaceAll(" ", "_"));
								entity.setCatalogName(selectedCatalog);
								entity.setStorageType("");
								catalog.getEntityLst().add(entity);
								boolean isInsert = false;
								DDLQuery ddlQuery = sqlObjt.ddlQuery;
								ArrayList<SQLObject> colsList = ddlQuery.colsList;
								for (int j = 0; j < colsList.size(); j++) {
									SQLColstruct sqlColObj = (SQLColstruct) colsList.get(j);
									Attribute attribute = new Attribute();
									attribute.setPhysicalName(sqlColObj.colName.replace(" ", "_"));
									attribute.setLogicalName(sqlColObj.colName);
									attribute.setDescription("");
									attribute.setDatatype(sqlColObj.dataType);
									attribute.setLength(sqlColObj.length_precision);
									attribute.setPrecision("");
									attribute.setScale(sqlColObj.Scale);
									if (sqlColObj.nullType!= null && sqlColObj.nullType.trim().equalsIgnoreCase("primary key")) {
										attribute.setNullable("No");
									} else {
										attribute.setNullable("Yes");
									}
									attribute.setDataFormat("");
									attribute.setDefaultValue("");
									attribute.setCheckConstraint("");
									attribute.setKeyType("");
									attribute.setCharacterSetName("");
									attribute.setCollationName("");
									attribute.setxpath("");
									attribute.setPrimaryTableName("");
									attribute.setprimaryColumnName("");
									for (int z = 0; z < catalog.getEntityLst().size(); z++) {
										if (catalog.getEntityLst().get(z).getPhysicalName()
												.equalsIgnoreCase(tblName)) {
											catalog.getEntityLst().get(z).getAttrList().add(attribute);
										}
									}
									try {
										isInsert = DBEngine_new.updateData("Catalogs", selectedCatalog, catalog,dataStoreDir + File.separator + username);
										if (isInsert) {
											message = "DDl Parsed Successfully";
										} else {
											message = "Failed to parse DDL Successfully";
										}
									} catch (Exception e) {
										e.printStackTrace();
										message = e.getMessage();
										return message;
									}
								}*/

						} else {
							outputObj.put("error","Invalid DDL. Please upload a valid DDL.");
						}
					} catch (Exception e) {
						e.printStackTrace();
						outputObj.put("error", e.getMessage());
					}
				}
			}
			outputObj.put("output",tblArray);
			outputObj.put("catalogName",selectedCatalog);
			outputObj.put("error","");
		} else {
			outputObj.put("error","Unable to fetch catalog for update. Please try again after sometime.");
		}

		return outputObj.toString();
	}
	
	@PostMapping(value = "home/migrate/schema-migrator/catalogs/add-custom-catalog")
	public ModelAndView importCustomData(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		dslogger.info("Importing custom data to catalog");
		boolean isInsert = false;
		ModelAndView mv = null;
		String tableValues = request.getParameter("tableValues");
		tableValues = Jsoup.clean(tableValues, Whitelist.none());
		System.out.println(tableValues);
		String catalogName = request.getParameter("catalogName");
		catalogName = Jsoup.clean(catalogName, Whitelist.none());
		String username = (String) request.getSession().getAttribute("username");
		String noSqlpath = (String) request.getSession().getAttribute("nosqlPath");
		Catalogs catalog = Utils.getCatalogObject(request, catalogName);
		JSONParser parser = new JSONParser();
		try {
			org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) parser.parse(tableValues);
			for(int i = 0; i < jsonArray.size(); i++)
			{
				org.json.simple.JSONObject obj = (org.json.simple.JSONObject) jsonArray.get(i);
				checkAndDeleteDuplicateEntities(catalog, (String) obj.get("tableName"));
				Entity en = new Entity();
				en.setPhysicalName((String) obj.get("tableName"));
				en.setLogicalName((String) obj.get("tableName"));
				en.setCatalogName(catalogName);
				en.setStorageType("");
				
				org.json.simple.JSONArray colsArray = (org.json.simple.JSONArray) obj.get("cols");
				for(int j = 0; j < colsArray.size(); j++) {
					org.json.simple.JSONObject attrObj = (org.json.simple.JSONObject) colsArray.get(j);
					Attribute attr = new Attribute();
					attr.setPhysicalName((String) attrObj.get("physicalName"));
					attr.setLogicalName((String) attrObj.get("logicalName"));
					attr.setDatatype((String) attrObj.get("datatype"));
					attr.setLength_precision((String) attrObj.get("length_precision"));
					attr.setScale((String) attrObj.get("scale"));
					attr.setNullable((String) attrObj.get("nullable"));
					attr.setDataFormat("");
					attr.setDefaultValue("");
					attr.setCheckConstraint("");
					attr.setKeyType("");
					attr.setxpath("");
					attr.setPrimaryTableName("");
					attr.setprimaryColumnName("");
					en.getAttrList().add(attr);
				}
				catalog.getEntityLst().add(en);
			}
			
			isInsert = DBEngine_new.updateData("Catalogs", catalogName, catalog,noSqlpath + File.separator + username);
			if (isInsert) {
				mv = new ModelAndView("redirect:/home/migrate/schema-migrator/catalogs/"+catalogName);
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Successfully imported ddl into catalog");
			} else {
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to insert data to catalog");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	private void checkAndDeleteDuplicateEntities(Catalogs catalog, String name) {
		for (Iterator<Entity> iter = catalog.getEntityLst().iterator(); iter.hasNext(); ) {
			Entity en = iter.next();
			if(en.getPhysicalName().equalsIgnoreCase(name))
				iter.remove();
		}
		
	}

}
