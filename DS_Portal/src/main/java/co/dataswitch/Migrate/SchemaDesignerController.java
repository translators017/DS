package co.dataswitch.Migrate;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.dataswitch.MetadataCatalog.CatalogController;
import co.dataswitch.config.bean.Configurations;
import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.AttributeDesignDTO;
import co.dataswitch.nosqldbDTO.AttributeMap.sourceAttributeRef_json;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.EntityDesignDTO;
import co.dataswitch.nosqldbDTO.SchemaMigrator;
import co.dataswitch.utils.UnmarshallerController;
import co.dataswitch.utils.Utils;


@Controller
public class SchemaDesignerController {
	
	private static final Logger dslogger = Logger.getLogger(SchemaDesignerController.class);

	@GetMapping(value = "home/migrate/schema-migrator/schema-designer")
	public ModelAndView launchSchemaDesignerPage(HttpServletRequest request, Model model) {
		dslogger.info("Entering method to get schema design page");
		ModelAndView mv = new ModelAndView("migrate/schemaMigrator/schemaDesigner/schema_designer");
		Configurations ds = UnmarshallerController.unmarshallDataSwitchConfigXML(request);
		ArrayList<String> data = null;
		String username = (String) request.getSession().getAttribute("username");
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		
		String selectedProject = request.getParameter("selectedProject");
		String status = request.getParameter("status");
		if(selectedProject != null && !selectedProject.equalsIgnoreCase(""))
		{
			if(status.equalsIgnoreCase("mapped"))
			{
				SchemaMigrator projMig = Utils.getSchemaDesignObject(request, selectedProject);
				projMig.setStatus("mapped");
				System.out.println("updating");
				try {
					dslogger.info("updating schema project");
					DBEngine_new.updateData("SchemaDesigner",selectedProject,projMig, dataStoreDir+ File.separator +username);
					
				} catch (Exception e) {
					e.printStackTrace();
					dslogger.error(e.getMessage());
				}
			}
			
		}
		
		try {
			data = DBEngine_new.getKeyValuesList("Catalogs",dataStoreDir + File.separator + username);
		} catch (Exception e) {
			dslogger.error("Error in getting catalog values from db - "+e.getMessage());
		}
		List<SchemaMigrator> projectList = Utils.getAllSchemaMigrator(request);
		for (Iterator<SchemaMigrator> iter = projectList.listIterator(); iter.hasNext(); ) {
			SchemaMigrator project = iter.next();
			if(ds != null)
				project.setDbType(Utils.getUpdatedDbType(project.getDbType(), ds) != null ? Utils.getUpdatedDbType(project.getDbType(), ds) : project.getDbType());
		}
		org.json.JSONArray dbListArray = new org.json.JSONArray();
		List<String> catalogingTools = new ArrayList<>();
		org.json.JSONObject configList = new org.json.JSONObject();
		if(ds != null)
		{
			configList = CatalogController.getDBListArray(ds, dbListArray, catalogingTools, configList);
		}
		
			
		
		request.getSession().setAttribute("dbList", configList.get("dbList").toString());
		mv.addObject("catalogList", data);
		mv.addObject("dbListArray", configList.get("dbList"));
		mv.addObject("projectArray", projectList);
		mv.addObject("type", model.asMap().get("type"));
		mv.addObject("message", model.asMap().get("message"));
		
		return mv;
	}
	
	@PostMapping(value = "home/migrate/schema-migrator/schema-designer/add-schema-project")
	public ModelAndView addSchemaDesignerProject(HttpServletRequest request, @ModelAttribute("schemaInfo") SchemaMigrator schemaInfo, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering method add new schema project - "+schemaInfo.getProjectName());
		String username = (String) request.getSession().getAttribute("username");
		boolean isInsert = false;
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		System.out.println("DataStoreDir in schema designer: "+dataStoreDir);
		schemaInfo.setStatus("new");
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/schema-designer");

		//create folder for system
		File rootDesignerFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner");
		File schemaDesignFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+schemaInfo.getProjectName()+"/schemaDesign");
		File transformationFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+schemaInfo.getProjectName()+"/transformationVarCount");
		File graphFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+schemaInfo.getProjectName()+"/graph");
		File statsFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+schemaInfo.getProjectName()+"/dbStats");
		if(!rootDesignerFolder.exists()){
			rootDesignerFolder.mkdirs();
		}
		if(!schemaDesignFolder.exists()){
			schemaDesignFolder.mkdirs();
		}
		if(!transformationFolder.exists()){
			transformationFolder.mkdirs();
		}
		if(!graphFolder.exists()){
			graphFolder.mkdirs();
		}
		if(!statsFolder.exists()){
			statsFolder.mkdirs();
		}	
		
		try {
			dslogger.info("Inserting schema project into database");
			isInsert = DBEngine_new.insertData("SchemaDesigner",schemaInfo.getProjectName(),schemaInfo,dataStoreDir+"/"+username);
			if(isInsert){
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Project schema has been added successfully.");
			}else{
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to create new project. Please try again after some time.");
			}
		} catch (Exception e) {
			dslogger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("type", "Failed");
			if(e.getMessage().equals("Key already exists - Cannot insert this record")){
				redirectAttributes.addFlashAttribute("message", "Project schema name already exists. Please use a different name.");
			}else{
				redirectAttributes.addFlashAttribute("message", e.getMessage());
			}
		}
		return mv;
	}
	
	@PostMapping(value = "home/migrate/schema-migrator/schema-designer/delete-schema-project")
	public ModelAndView deleteSchemaDesignerProject(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering method to delete schema project");
		String projectName = request.getParameter("projectName");
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession(false).getAttribute("username");
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/schema-designer");
		boolean isDelete = false;
		String noSqlpath = Utils.getNoSQLDBPath(request);
		try {
			isDelete = DBEngine_new.deleteData("SchemaDesigner", projectName, noSqlpath+"/"+username);
			File spfSchemaDesignFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+projectName);
			FileUtils.deleteDirectory(spfSchemaDesignFolder);
			if(isDelete){
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Project has been deleted successfully.");	
			}
			else{
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to delete the Project. Please try again after sometime.");
			}
		}catch (Exception e) {
			dslogger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return mv;
	}
	
	@ResponseBody
	@GetMapping(value = "home/migrate/schema-migrator/schema-designer/getDetails")
	public String getSchemaDetails(HttpServletRequest request) {
		String projectName = request.getParameter("projectName");
		dslogger.info("Entering method to get project details - "+projectName);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		SchemaMigrator schemaDesignData = null;
		String jsonString = null;
		try {
			schemaDesignData = (SchemaMigrator) DBEngine_new.getData("SchemaDesigner",projectName,dataStoreDir+"/"+username);
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(schemaDesignData);
		} catch (Exception e) {
			dslogger.error(e.getMessage());
		}
		return jsonString;
	}

	@PostMapping(value = "home/migrate/schema-migrator/schema-designer/edit-schema-project")
	public ModelAndView editSchemaDesignerProject(HttpServletRequest request, @ModelAttribute("schemaDesign") SchemaMigrator schemaDesign, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering method to edit the schema design project - "+schemaDesign.getProjectName());
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/schema-migrator/schema-designer");
		SchemaMigrator existingSchemaDesign = Utils.getSchemaDesignObject(request, schemaDesign.getProjectName());
		if(existingSchemaDesign != null)
		{
			if(schemaDesign.getDbType() != null && schemaDesign.getCatalogName() != null)
			{
				existingSchemaDesign.setDbType(schemaDesign.getDbType());
				existingSchemaDesign.setCatalogName(schemaDesign.getCatalogName());
			}
			existingSchemaDesign.setProjectDescription(schemaDesign.getProjectDescription());
			
		}else{
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", "Unable to fetch schema design project for update. Please try again after sometime.");
		}

		boolean isUpdate = false;
		try {
			dslogger.info("Updating schema design data");
			System.out.println("Existing Schema Design: "+existingSchemaDesign);
			isUpdate = DBEngine_new.updateData("SchemaDesigner", schemaDesign.getProjectName(), existingSchemaDesign, dataStoreDir+"/"+username);
			if(isUpdate){
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Project has been updated successfully.");
			}else{
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to update Project. Please try again after sometime.");
			}
		} catch (Exception e) {
			dslogger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", "Update Failed - "+e.getMessage());
		}
		return mv;
	}
	
	//to get the schema design objects(all fields)
	public static SchemaMigrator getSchemaDesignObject(HttpServletRequest request,String projectName){
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		SchemaMigrator schemaDesign = null;
		try {
			schemaDesign = (SchemaMigrator) DBEngine_new.getData("SchemaDesigner", projectName, dataStoreDir+"/"+username);
		} catch (Exception e) {
			dslogger.error("Unable to get schema design object for project - "+projectName);
		}
		return schemaDesign;
	}
	
	
	
	
	public static void fillJsonValueFromSchemaDesign(String configPath, String ouputPath, String cataLogPath) 
	{
	   JSONParser parser = new JSONParser();
		try {
			
			FileReader inputFile = new FileReader(configPath);
			Object obj = parser.parse(inputFile);

			JSONObject jsonObject = (JSONObject) obj;

			String scheDesign = (String) jsonObject.get("schemaDesign");
		
			Object schemaObject = new JSONParser().parse(scheDesign);

			JSONObject collectionObjects = (JSONObject) schemaObject;
			
			Set<String> keys = collectionObjects.keySet();
			Iterator<String> iterator = keys.iterator();
			while(iterator.hasNext())
			{
				String key = iterator.next();
				
				 if (collectionObjects.get(key) instanceof JSONObject)
				 {
			         JSONObject collectionObjt = (JSONObject) collectionObjects.get(key);
			         
			         EntityDesignDTO entityObjt = new EntityDesignDTO();
			         
			         getUpdatedCatalogObjects(collectionObjt, cataLogPath, entityObjt);
			         
			         JSONObject jsonObjt = entityObjt.getJsonData();
			         
			     	 ObjectMapper mapper = new ObjectMapper();
			     	 // pretty print
			    	 String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObjt);
			    	 
			         File file = new File(ouputPath);
			         file.createNewFile();
			         
			         FileWriter fw = new FileWriter(ouputPath +file.separator + key+".bin");
			         fw.write(json);
			         //fw.write(jsonObjt.toString());
			        
			         fw.flush();
			         fw.close();
			         
			     }
			}
			
			inputFile.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
   }

		private static void getUpdatedCatalogObjects(JSONObject collectionObjt, String cataLogPath, EntityDesignDTO entityObjt) 
		{
			String[] attrEntityName = getEntityAttributeName(collectionObjt);
		
			entityObjt.entityName = (String) collectionObjt.get("nodeName");
			
			JSONArray srcAttributes = getAttributeJsonArray(attrEntityName[0], attrEntityName[1], cataLogPath);
			
			getSrcAttrsDetails(srcAttributes, entityObjt, attrEntityName,  (String) collectionObjt.get("nodeName"), null);
			
			JSONArray nodeArray = (JSONArray) collectionObjt.get("nodes");
			
		    ArrayList<String> parentNodeNames = new ArrayList<String>();
		    
		    parentNodeNames.add((String) collectionObjt.get("nodeName"));
		    
			for(int i = 0 ; i < nodeArray.size(); i++)
			{
				JSONObject childNodeObject = (JSONObject) nodeArray.get(i);
				
				String parentObjt = (String) childNodeObject.get("parentName");
						
				String xPath = "";
				if(parentObjt.equalsIgnoreCase(parentNodeNames.get(parentNodeNames.size()-1)))
				{
					for(int j = 0 ; j < parentNodeNames.size(); j++)
					{
						xPath += parentNodeNames.get(j) +".";
					}
				}
				
				if(!parentNodeNames.contains((String) childNodeObject.get("nodeName")))
					parentNodeNames.add((String) childNodeObject.get("nodeName"));
				
				xPath = xPath+(String) childNodeObject.get("nodeName");
				
				String[] attrEntityNodeName = getEntityAttributeName(childNodeObject);
				
				JSONArray srcNodeAttrs = getAttributeJsonArray(attrEntityNodeName[0], attrEntityNodeName[1], cataLogPath);
				
				getSrcAttrsDetails(srcNodeAttrs, entityObjt, attrEntityNodeName,  (String) collectionObjt.get("nodeName"), xPath);
				
			}
			
		}

		private static void getSrcAttrsDetails(JSONArray srcAttributes, EntityDesignDTO entityObjt, String[] attrEntityName, String tgtTableName, String xPath) 
		{
			for(int j = 0 ; j  < srcAttributes.size() ; j++)
			{
				JSONObject srcObjt = (JSONObject) srcAttributes.get(j);
				
				String logicalName = (String) srcObjt.get("logicalName");
				String physicalName = (String) srcObjt.get("physicalName");
			
				AttributeDesignDTO attrDesignDto = new AttributeDesignDTO();
				
				attrDesignDto.logicalTargetAttributeRef = logicalName;
				attrDesignDto.targetAttributeRef = physicalName;
				
				if(xPath != null && xPath.length() > 0) {
					attrDesignDto.targetEntityRef = xPath;
					attrDesignDto.logicalTargetEntityRef = xPath;
				}else {
					attrDesignDto.targetEntityRef = tgtTableName;
					attrDesignDto.logicalTargetEntityRef = tgtTableName;
				}
				
				attrDesignDto.targetCompName = tgtTableName;
				attrDesignDto.targetCatalogName = attrEntityName[1];
	
				sourceAttributeRef_json srcAtrLst = new sourceAttributeRef_json();
				
				srcAtrLst.logicalSourceDataElementRef = logicalName;
				srcAtrLst.sourceDataElementRef = physicalName;
				srcAtrLst.sourceCatalogName = attrEntityName[1];
				srcAtrLst.sourceCompName = attrEntityName[0];
				srcAtrLst.sourceFeedRef = attrEntityName[0];
				srcAtrLst.logicalSourceFeedRef = attrEntityName[0];
				
				if(srcAtrLst != null)
					attrDesignDto.getSrcAtrRefList().add(srcAtrLst);
				
				entityObjt.attributesList.add(attrDesignDto);
			}
			
		}

		private static JSONArray getAttributeJsonArray(String entityName, String catalogName, String cataLogPath) 
		{
			catalogName = catalogName+".bin";
			
			File catalogPathDir = new File(cataLogPath);
			
			File[] cataLogFiles = catalogPathDir.listFiles();
			  
			JSONArray attbrArray = new JSONArray();
			
			for (File catalogFile : cataLogFiles) 
			{
				  File srcFilePathDir =new File(catalogFile.getPath());

				  if(catalogName.equals(srcFilePathDir.getName()))
				  {
					 JSONParser parser = new JSONParser();
					 
					 try {	
							  FileReader inputFile = new FileReader(srcFilePathDir);
							  
							  Object obj = parser.parse(inputFile);
							  
							  JSONObject jsonObject = (JSONObject) obj;
							  
							  JSONArray entityArray = (JSONArray) jsonObject.get("entityJsonArray");
							  
							  for(int i = 0 ; i < entityArray.size() ; i++)
							  {
								  JSONObject entityObjt = (JSONObject) entityArray.get(i);;
								  String checkCatalogName = catalogName.substring(0, catalogName.lastIndexOf("."));
								  
								  if((entityName.equalsIgnoreCase((String)entityObjt.get("entityPhyName")) 
										  || entityName.equalsIgnoreCase((String)entityObjt.get("entityLogName"))) && (checkCatalogName.equalsIgnoreCase((String)entityObjt.get("catalogName"))))
								  {
									  JSONArray attrJsonArray =  (JSONArray) entityObjt.get("attribJsonArray");
									
									  for(int j = 0 ; j < attrJsonArray.size() ; j++)
									  {
										  JSONObject attrObjt = (JSONObject) attrJsonArray.get(j);
										  attbrArray.add(attrObjt);
									  }
								  }
							  }
							  
							  inputFile.close();
					     }
					  	 catch (Exception e) {
							e.printStackTrace();
						 }
				  }
			}
	        
			return attbrArray;
		}
		
		private static String[] getEntityAttributeName(JSONObject collectionObjt) 
		{
			System.out.println(collectionObjt);
			String srcAttribute = (String) collectionObjt.get("nodeSrcRefName");
			
			String catalogName = "" , entityName = "";  
			if(srcAttribute.contains("("))
			{
				entityName = srcAttribute.substring(0,srcAttribute.indexOf("(")).trim();
				catalogName = srcAttribute.substring(srcAttribute.indexOf("(")+1, srcAttribute.lastIndexOf(")")).trim();
			}
			
			String[] arrayValue = {entityName,catalogName};
			
			return arrayValue;
		}
}
