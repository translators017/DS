package co.dataswitch.Migrate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dataSwitch.ds.functionTypeRules.CodeGenRules;
import com.dataSwitch.ds.functionTypeRules.RuleMapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.dataswitch.MetadataCatalog.CatalogController;
import co.dataswitch.config.bean.Configurations;
import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Attribute;
import co.dataswitch.nosqldbDTO.AttributeDesignDTO;
import co.dataswitch.nosqldbDTO.AttributeMap;
import co.dataswitch.nosqldbDTO.AttributeMap.sourceAttributeRef_json;
import co.dataswitch.nosqldbDTO.AttributeMap.transformationRule_json;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.Entity;
import co.dataswitch.nosqldbDTO.EntityDesignDTO;
import co.dataswitch.nosqldbDTO.Mapping;
import co.dataswitch.nosqldbDTO.SchemaDesignDTO;
import co.dataswitch.nosqldbDTO.SchemaMigrator;
import co.dataswitch.utils.UnmarshallerController;
import co.dataswitch.utils.Utils;

@Controller
public class EntityDesignController {

	private static final Logger dslogger = Logger.getLogger(EntityDesignController.class);

	//////////////////////Added for Data Switch - Schema Design Page - Code starts here/////////////////////
	///////////Added for Data Switch - Entity Design Page/////////////
	@RequestMapping(value = "home/migrate/schema-migrator/schema-designer/{selectedProject}/entityDesign", method = RequestMethod.GET)
	public ModelAndView showSchemaEntityDesignDtl(HttpServletRequest request,  @PathVariable("selectedProject") String selectedProject) throws JsonProcessingException{
		long startTime = System.nanoTime();	
		dslogger.info("Schema Designer - Entity Design Page");

		dslogger.info("Selected Project - " +selectedProject);

		//ModelAndView mv = new ModelAndView("rdw/Intelligent_Mapper/Mappings/attributePage");
		ModelAndView mv = new ModelAndView("migrate/schemaMigrator/schemaDesigner/schemaEntityDesign");
		String dataStoreDir = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");

		SchemaMigrator schemaDesign = Utils.getSchemaDesignObject(request, selectedProject);
		String srcCatalogName = "";
		srcCatalogName = schemaDesign.getCatalogName();

		List<Catalogs> catalogList = CatalogController.getAllCatalogs(request);
		System.out.println("catalogList: "+catalogList);
		System.out.println("srcCatalogName: "+srcCatalogName);
		System.out.println("schemaDesign: "+schemaDesign);
		
		JSONArray catalogsArray = new JSONArray();
		JSONObject catalogsObject = new JSONObject();
		List<String> catalogNames = new ArrayList<String>();
		try {
			catalogNames = DBEngine_new.getKeyValuesList("Catalogs", dataStoreDir+"/"+username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(String catalog : catalogNames){

			Catalogs catalogObj = CatalogController.getCatalogObject(request, catalog);


			for(Entity en : catalogObj.getEntityLst()){
				List<String> attributeList = new ArrayList<String>();
				for(Attribute arr : en.getAttrList()){
					attributeList.add((arr.getLogicalName()!=null && !arr.getLogicalName().equals("")) ? arr.getLogicalName()+"::"+arr.getPhysicalName() : arr.getPhysicalName()+"::"+arr.getPhysicalName());
				}
				catalogsObject.put("catalogName", catalog);
				catalogsObject.put("entityName", en.getPhysicalName());
				catalogsObject.put("entityLogName", (en.getLogicalName()!=null && !en.getLogicalName().equals("")) ? en.getLogicalName() : en.getPhysicalName());
				catalogsObject.put("componentName", en.getPhysicalName());
				catalogsObject.put("attributeList", attributeList);
				catalogsArray.put(catalogsObject);
				catalogsObject = new JSONObject();
			}
		}
		mv.addObject("catalogsArray",catalogsArray);
		mv.addObject("catalogArrayKey", catalogList);
		mv.addObject("selectedProject", selectedProject);
		mv.addObject("srcCatalogName", srcCatalogName);

		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		dslogger.info("Entity data collection took "+duration+" milliseconds to execute");
		return mv;
	}	

	//To save the entity design and redirect to next page
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "schema-designer/{selectedProject}/saveSchemaDesign", method = RequestMethod.POST) //throws IOException, JSONException, ParseException
	public String saveSchemaDesign(HttpServletRequest request,@PathVariable("selectedProject") String selectedProject,@ModelAttribute("schemaDesign") SchemaDesignDTO schemaDesign) throws JsonProcessingException{
		dslogger.info("**************Saving schema entity design**************");
		String dataStoreDir = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		String schemaDesignFileNm = "schemaEntities";

		String objSchemaDesignJson = request.getParameter("objSchemaDesignJson");
		dslogger.info("Schema Design Json - "+objSchemaDesignJson);

		//create folder for entity design
		File schemaProjectFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+selectedProject);
		File schemaDesignFolder = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+selectedProject+"/schemaDesign");
		if(!schemaProjectFolder.exists()){
			schemaProjectFolder.mkdirs();
		}
		if(!schemaDesignFolder.exists()){
			schemaDesignFolder.mkdirs();
		}
		
		SchemaMigrator projectDesign = Utils.getSchemaDesignObject(request, selectedProject);
		projectDesign.setStatus("draft");
		//set the schema design
		schemaDesign.setSchemaDesign(objSchemaDesignJson);
		dslogger.info("getSchemaDesign - "+schemaDesign.getSchemaDesign());

		boolean isDesignFileExist = false;
		File file = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+selectedProject+"/schemaDesign/"+schemaDesignFileNm+".bin");
		if (!file.isDirectory() && file.exists()) {
			isDesignFileExist = true;
		}
		dslogger.info("isDesignFileExist - "+isDesignFileExist);

		boolean isInsert = false;
		boolean isUpdate = false;
		try {
			if (!isDesignFileExist) {
				isInsert = DBEngine_new.insertData("SchemaDesigner"+"/"+selectedProject+"/schemaDesign", schemaDesignFileNm, schemaDesign, dataStoreDir+"/"+username);
			}else {
				isUpdate = DBEngine_new.updateData("SchemaDesigner"+"/"+selectedProject+"/schemaDesign", schemaDesignFileNm, schemaDesign, dataStoreDir+"/"+username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			dslogger.info("Updating schema design data");
			isUpdate = DBEngine_new.updateData("SchemaDesigner", projectDesign.getProjectName(), projectDesign, dataStoreDir+"/"+username);
		} catch (Exception e) {
			dslogger.error(e.getMessage());
		}

		dslogger.info("schemaDesignFileNm - "+schemaDesignFileNm);
		String output = "test-data";
		return output;
	}

	@ResponseBody
	@RequestMapping(value = "schema-designer/getSchemaDesignDetails", method = RequestMethod.GET)
	public String getSchemaDesignDetails(HttpServletRequest request,@ModelAttribute("schemaDesign") SchemaDesignDTO schemaDesign,RedirectAttributes redirectAttributes){
		//@ResponseBody
		//@RequestMapping(value = "entity-designer/getEntityDesignDetails", method = RequestMethod.GET)
		//public String getEntityDesignDetails(HttpServletRequest request) {
		String projectName = request.getParameter("projectName");
		dslogger.info("Entering method to get schema entity design details - "+projectName);
		String dataStoreDir = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		SchemaDesignDTO schemaDesignData = null;
		String jsonString = null;
		try {
			schemaDesignData = (SchemaDesignDTO) DBEngine_new.getData("schemaDesign","schemaEntities",dataStoreDir+"/"+username+"/SchemaDesigner/"+projectName+"/");
			dslogger.info("Schema Design Data - "+schemaDesignData);
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(schemaDesignData);
		} catch (Exception e) {
			dslogger.error(e.getMessage());
		}
		return jsonString;
	}

	///////////Added for Data Switch - Attribute Design Page/////////////
@RequestMapping(value = "schema-designer/{selectedProject}/attributeDesign", method = RequestMethod.GET)
public ModelAndView showSchemaAttributeDesignDetails(HttpServletRequest request, @PathVariable("selectedProject") String selectedProject) throws JsonProcessingException{
	dslogger.info("Redirecting schema attribute design page to get request");		
	dslogger.info("Selected Project - " +selectedProject);

	//ModelAndView mv = new ModelAndView("rdw/Intelligent_Mapper/Mappings/attributePage");
	ModelAndView mv = new ModelAndView("migrate/schemaMigrator/schemaDesigner/schemaAttributeDesign");
	String dataStoreDir = Utils.getNoSQLDBPath(request);
	String username = (String) request.getSession().getAttribute("username");
	
	String configPath = dataStoreDir+"/"+username+"/"+"SchemaDesigner"+"/"+selectedProject+"/"+"schemaDesign"+"/"+"schemaEntities.bin";
	
	File schemaEntitiesFolder = new File(dataStoreDir+"/"+username+"/"+"SchemaDesigner"+"/"+selectedProject+"/"+"schemaDesign"+"/"+"schemaEntities");
	if(!schemaEntitiesFolder.exists()){
		schemaEntitiesFolder.mkdirs();
	}
	
	String outputPath = dataStoreDir+"/"+username+"/"+"SchemaDesigner"+"/"+selectedProject+"/"+"schemaDesign"+"/"+"schemaEntities";
	
	String cataLogPath = dataStoreDir+"/"+username+"/"+"Catalogs";

	dslogger.info("configPath - " +configPath);
	dslogger.info("outputPath - " +outputPath);
	dslogger.info("cataLogPath - " +cataLogPath);
	SchemaDesignerController.fillJsonValueFromSchemaDesign(configPath, outputPath, cataLogPath);
	
	
	/////////////Required Variables for Attribute Design///////////////
	JSONArray rules = new JSONArray();
	JSONObject rule = new JSONObject();
	String root = request.getServletContext().getRealPath("/");
	try {
		List<RuleMapping> ruleRef = CodeGenRules.getCodeGenerationRules("Informatica");
		LinkedHashMap<String,String> TrRuleMap = new LinkedHashMap<String,String>();
		List<String> TransformationRule = new ArrayList<String>();
		List<String> TransformationRuleAutoComplete = new ArrayList<String>();
		for(int i=0;i<ruleRef.size();i++){
			if(!(ruleRef.get(i).getTransformationRule().getRuleName().trim().equalsIgnoreCase("Data Flow - Conditional Grouping")) && !(ruleRef.get(i).getTransformationRule().getRuleName().trim().equalsIgnoreCase("Data Flow - Reusable Transformation"))){
				TransformationRule.add(ruleRef.get(i).getTransformationRule().getRuleName());
				TransformationRuleAutoComplete.add(ruleRef.get(i).getTransformationRule().getRuleName().substring(ruleRef.get(i).getTransformationRule().getRuleName().indexOf("-")+1, ruleRef.get(i).getTransformationRule().getRuleName().length()).trim());
				String ruleName = ruleRef.get(i).getTransformationRule().getRuleName();
				ruleName = ruleName.substring(ruleName.indexOf("-")+1, ruleName.length());
				TrRuleMap.put(ruleName.trim(), ruleRef.get(i).getExpression().replaceAll(",", ";"));

				rule.put("rulename", ruleName.substring(ruleName.indexOf("-")+1, ruleName.length()).trim());
				if(ruleRef.get(i).getTransformationRule().getParameter()!=null)
				{
					rule.put("parameterSize",ruleRef.get(i).getTransformationRule().getParameter().size());
				}
				rules.put(rule);
				rule = new JSONObject();
			}
		}
		for (Iterator<String> iter = TransformationRuleAutoComplete.listIterator(); iter.hasNext(); ) {
			String a = iter.next();
			if (a.equalsIgnoreCase("Special - Lookup") || a.equalsIgnoreCase("Data Flow - Sorting") || a.equalsIgnoreCase("joiner") || a.equalsIgnoreCase("filter") || a.equalsIgnoreCase("reusable transformation") || a.equalsIgnoreCase("stored procedure")) {
				iter.remove();
			}
		}
		for (Iterator<String> iter = TransformationRule.listIterator(); iter.hasNext(); ) {
			String a = iter.next();
			if (a.equalsIgnoreCase("Special - Lookup") || a.equalsIgnoreCase("Data Flow - Sorting") || a.equalsIgnoreCase("joiner") || a.equalsIgnoreCase("filter") || a.equalsIgnoreCase("reusable transformation") || a.equalsIgnoreCase("stored procedure") ) {
				iter.remove();
			}
		}
		

		Properties prop = new Properties();
		String propFileName = root+"resources/properties/ds_static.properties";
		String dateformat ="";
		try {
		 System.out.println("Root===>"+root);
		FileInputStream inputStream = new FileInputStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} 
		dateformat = prop.getProperty("defaultdateformat");
		
		 } catch(FileNotFoundException fnfe) {
			 System.err.println("property file '" + propFileName + "' not found in the classpath");
	      } catch(IOException ioe) {
	    	  System.err.println("property file '" + propFileName + "' not found in the classpath");
	      } 

		//TransformationRuleMappings ruleMappings = ruleRef.getTransformationRuleMappings();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		jsonString = mapper.writeValueAsString(ruleRef);
		dslogger.info("ruleMappings - " +jsonString);
		mv.addObject("ruleMappings", jsonString);

		dslogger.info("TransformationRule - " +TransformationRule);
		mv.addObject("rules", rules.toString());
		mv.addObject("TransformationRules", TransformationRule);
		mv.addObject("TransformationRuleAutoComplete", TransformationRuleAutoComplete);
		mv.addObject("TrRuleMap", TrRuleMap);
		mv.addObject("defaultadteformat", dateformat);
		
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	//for lookup
	JSONArray lkpcatalogsArray = new JSONArray();
	JSONObject lkpcatalogsObject = new JSONObject();
	List<String> catalogNames = new ArrayList<String>();
	try {
		catalogNames = DBEngine_new.getKeyValuesList("Catalogs", dataStoreDir+"/"+username);
	} catch (Exception e) {
		e.printStackTrace();
	}
	for(String catalog : catalogNames){

		Catalogs catalogObj = CatalogController.getCatalogObject(request, catalog);


		for(Entity en : catalogObj.getEntityLst()){
			List<String> attributeList = new ArrayList<String>();
			for(Attribute arr : en.getAttrList()){
				attributeList.add((arr.getLogicalName()!=null && !arr.getLogicalName().equals("")) ? arr.getLogicalName()+"::"+arr.getPhysicalName() : arr.getPhysicalName()+"::"+arr.getPhysicalName());
			}
			lkpcatalogsObject.put("catalogName", catalog);
			lkpcatalogsObject.put("entityName", en.getPhysicalName());
			lkpcatalogsObject.put("entityLogName", (en.getLogicalName()!=null && !en.getLogicalName().equals("")) ? en.getLogicalName() : en.getPhysicalName());
			lkpcatalogsObject.put("componentName", en.getPhysicalName());
			//lkpcatalogsObject.put("entityName", (en.getLogicalName()!=null && !en.getLogicalName().equals("")) ? en.getLogicalName()+"::"+en.getPhysicalName() : en.getPhysicalName()+"::"+en.getPhysicalName());
			lkpcatalogsObject.put("attributeList", attributeList);
			lkpcatalogsArray.put(lkpcatalogsObject);
			lkpcatalogsObject = new JSONObject();
		}
	}
	
	
	List<Catalogs> catalogList = CatalogController.getAllCatalogs(request);
	System.out.println("catalogList: "+catalogList);
	
	SchemaMigrator schemaDesign = Utils.getSchemaDesignObject(request, selectedProject);
	String srcCatalogName = "";
	srcCatalogName = schemaDesign.getCatalogName();
	
	mv.addObject("catalogArrayKey", catalogList);
	mv.addObject("catalogsArray", lkpcatalogsArray);
	mv.addObject("selectedProject", selectedProject);
	mv.addObject("srcCatalogName", srcCatalogName);

	return mv;
}

	//to get the details for attribute design page when launching
	@ResponseBody
	@RequestMapping(value = "schema-designer/getEntityDesignDetails", method = RequestMethod.GET)
	public String getEntityDesignDetails(HttpServletRequest request) {
		String projectName = request.getParameter("projectName");
		String entityName = request.getParameter("entityName");
		dslogger.info("//////////////Entering method to get schema entity details/////////////");
		dslogger.info("Entity Name: "+entityName);
		dslogger.info("Project Name: "+projectName);
		String dataStoreDir = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");

		String jsonString = null;
		try {
			//EntityDesignDTO entityDesignData = (EntityDesignDTO) DBEngine_new.getData("schemaEntities",entityName,dataStoreDir+"/"+username+"/SchemaDesigner/"+projectName+"/schemaDesign");		
			//dslogger.info("Raw Entity Design Data: "+entityDesignData.entityName);
			//dslogger.info("Entity Design Data: "+entityDesignData.attributesList);
			//ObjectMapper mapper = new ObjectMapper();
			//jsonString = mapper.writeValueAsString(entityDesignData);

			File file = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+projectName+"/schemaDesign/schemaEntities/"+entityName+".bin");
			System.out.println("file"+file);
			if (!file.isDirectory() && file.exists()) {
				System.out.println(file.exists());
				FileReader reader = new FileReader(file);

				//read the collection and parse the json
				JSONParser parser = new JSONParser();
				org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(reader); 

				//to set the collection details in the object
				EntityDesignDTO entityDesignData = new EntityDesignDTO();
				entityDesignData.setJsonData(json);

				reader.close();
				return json.toString();
			}			
		} catch (Exception e) {
			dslogger.error(e.getMessage());
		}
		return null;
	}	

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "schema-designer/{selectedProject}/{selectedEntityName}/attribute-design/save", method = {RequestMethod.POST})
	public String saveSchemaAttributeDesign(HttpServletRequest request,@PathVariable("selectedProject") String selectedProject,@PathVariable("selectedEntityName") String selectedEntityName) throws IOException, JSONException, ParseException {
		dslogger.info("Saving attribute mapping page details");

		String dataStoreDir = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		//EntityDesignDTO mapping = Common.getSchemaDesignObject(request, selectedProject, selectedEntityName, dataStoreDir, username);
		//mapping.setStatus("draft");
		EntityDesignDTO schemaEntityDesign = new EntityDesignDTO();
		dslogger.info("EntityDesignDTO - "+schemaEntityDesign);
		schemaEntityDesign.setEntityName(selectedEntityName);

		String map = request.getParameter("map");
		//String catalogJSONnew = request.getParameter("catalogJSON");
		//mapping.setCatalogObject(catalogJSONnew);
		String index = request.getParameter("index");

		JSONParser parserJson = new JSONParser();
		Object objJson = parserJson.parse(map);
		org.json.simple.JSONArray jsonArray = (org.json.simple.JSONArray) objJson;
		System.out.println("Size--"+jsonArray.size());
		for(int p = 0; p < jsonArray.size(); p++)
		{
			try {
				org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonArray.get(p);

				AttributeDesignDTO attributeMp = new AttributeDesignDTO();
				attributeMp.setCatalogName((String) jsonObject.get("targetCatalog"));
				attributeMp.setTargetEntityRef((String) jsonObject.get("targetEntityRef"));
				attributeMp.setTargetAttributeRef((String) jsonObject.get("targetAttributeRef"));
				attributeMp.setLogicalTargetAttributeRef((String) jsonObject.get("logicalTargetAttributeRef"));
				attributeMp.setLogicalTargetEntityRef((String) jsonObject.get("logicalTargetEntityRef"));
				attributeMp.setTargetCompName((String) jsonObject.get("targetCompName"));
				if(jsonObject.get("derivedVarDetails") != null){
					attributeMp.setDerivedVarDetails(jsonObject.get("derivedVarDetails").toString());
				}

				System.out.println("Input size transformationRuleList: "+jsonObject.get("transformationRuleList"));
				if(jsonObject.get("transformationRuleList")!=null)
				{
					System.out.println("Input data transformationRuleList: "+jsonObject.get("transformationRuleList").toString());
					String arr = jsonObject.get("transformationRuleList").toString();
					Object objRule = parserJson.parse(arr);

					org.json.simple.JSONArray jsonArrRule = (org.json.simple.JSONArray) objRule;
					for(int r = 0; r < jsonArrRule.size(); r++)
					{
						System.out.println("Trans Rule: "+jsonArrRule.get(r));
						try {
							org.json.simple.JSONObject jsonObjRule = (org.json.simple.JSONObject) jsonArrRule.get(r);
							transformationRule_json trfmRule = new transformationRule_json();
							trfmRule.setRuleName((String) jsonObjRule.get("ruleName"));
							org.json.simple.JSONArray parameterArr = (org.json.simple.JSONArray) jsonObjRule.get("parameter");
							trfmRule.setParameter(parameterArr.toJSONString());
							trfmRule.setRuleDescription((String) jsonObjRule.get("ruleDescription"));
							trfmRule.setType((String) jsonObjRule.get("ruleType"));
							trfmRule.setComponentName((String) jsonObjRule.get("componentName"));
							trfmRule.setPrevComponentRef((String) jsonObjRule.get("prevComponentRef"));
							trfmRule.setRuledesctext((String) jsonObjRule.get("ruledesctext"));							

							attributeMp.getTransformationRuleList().add(trfmRule);

						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}

				String sourceEntityAttributes = jsonObject.get("srcAtrRefList").toString();
				Object srcObjJson = parserJson.parse(sourceEntityAttributes);
				org.json.simple.JSONArray srcJsonArray = (org.json.simple.JSONArray) srcObjJson;
				for(int q = 0; q < srcJsonArray.size(); q++)
				{
					try {
						org.json.simple.JSONObject srcJsonObject = (org.json.simple.JSONObject) srcJsonArray.get(q);
						sourceAttributeRef_json srcRef = new sourceAttributeRef_json();
						srcRef.setSourceCatalogName((String) srcJsonObject.get("sourceCatalog"));
						srcRef.setLogicalSourceFeedRef((String) srcJsonObject.get("logicalSourceFeedRef"));
						srcRef.setLogicalSourceDataElementRef((String) srcJsonObject.get("logicalSourceDataElementRef"));
						srcRef.setSourceFeedRef((String) srcJsonObject.get("sourceFeedRef"));
						srcRef.setSourceDataElementRef((String) srcJsonObject.get("sourceDataElementRef"));
						srcRef.setSourceCompName((String) srcJsonObject.get("sourceCompName"));
						attributeMp.getSrcAtrRefList().add(srcRef);
					}catch(Exception e){
						e.printStackTrace();
					}
				}				

				System.out.println("attributeMp"+attributeMp);
				schemaEntityDesign.getattributeMapList().add(attributeMp);

			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*try {
DBEngine_new.updateData("schemaEntities", selectedEntityName, mapping, dataStoreDir+"/"+username+"/SchemaDesigner/"+selectedProject+"/schemaDesign");
} catch (Exception e1) {
e1.printStackTrace();
}*/

		boolean isDesignFileExist = false;
		File file = new File(dataStoreDir+"/"+username+"/SchemaDesigner/"+selectedProject+"/schemaDesign/schemaEntities/"+selectedEntityName+".bin");
		if (!file.isDirectory() && file.exists()) {
			isDesignFileExist = true;
		}
		dslogger.info("isDesignFileExist - "+isDesignFileExist);

		boolean isInsert = false;
		boolean isUpdate = false;
		try {
			if (!isDesignFileExist) {
				isInsert = DBEngine_new.insertData("schemaEntities", selectedEntityName, schemaEntityDesign, dataStoreDir+"/"+username+"/SchemaDesigner/"+selectedProject+"/schemaDesign");
			}else {
				isUpdate = DBEngine_new.updateData("schemaEntities", selectedEntityName, schemaEntityDesign, dataStoreDir+"/"+username+"/SchemaDesigner/"+selectedProject+"/schemaDesign");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		JSONArray attributeMapArray = new JSONArray();
		getAttributeDesignMapArray(schemaEntityDesign,attributeMapArray);

		return attributeMapArray.toString();
	}

	private void getAttributeDesignMapArray(EntityDesignDTO mapping, JSONArray attributeMapArray) {
		System.out.println("sizeeee--"+mapping.getattributeMapList().size());
		for(AttributeDesignDTO am : mapping.getattributeMapList())
		{
			JSONObject attributeMapObject = new JSONObject();
			attributeMapObject.put("targetEntityRef", am.getTargetEntityRef());
			attributeMapObject.put("logicalTargetEntityRef", am.getLogicalTargetEntityRef());
			attributeMapObject.put("targetAttributeRef", am.getTargetAttributeRef());
			attributeMapObject.put("logicalTargetAttributeRef", am.getLogicalTargetAttributeRef());
			attributeMapObject.put("targetCompName", am.getTargetCompName());
			attributeMapObject.put("targetCatalogName", am.getCatalogName());
			attributeMapObject.put("derivedVarDetails", am.getDerivedVarDetails());

			JSONArray srcAtrRefList = new JSONArray();
			for(sourceAttributeRef_json srcRef : am.getSrcAtrRefList())
			{
				JSONObject srcAtrRefObj = new JSONObject();
				srcAtrRefObj.put("sourceFeedRef", srcRef.getSourceFeedRef());
				srcAtrRefObj.put("logicalSourceFeedRef", srcRef.getLogicalSourceFeedRef());
				srcAtrRefObj.put("sourceDataElementRef", srcRef.getSourceDataElementRef());
				srcAtrRefObj.put("logicalSourceDataElementRef", srcRef.getLogicalSourceDataElementRef());
				srcAtrRefObj.put("sourceCompName", srcRef.getSourceCompName());
				srcAtrRefObj.put("sourceCatalogName", srcRef.getSourceCatalogName());
				srcAtrRefList.put(srcAtrRefObj);
			}
			attributeMapObject.put("srcAtrRefList", srcAtrRefList);

			JSONArray transformationRuleList = new JSONArray();
			for(transformationRule_json tr : am.getTransformationRuleList())
			{
				JSONObject transformationRuleObj = new JSONObject();
				transformationRuleObj.put("ruleName", tr.getRuleName());
				transformationRuleObj.put("componentName", tr.getComponentName());
				transformationRuleObj.put("parameter", tr.getParameter());
				transformationRuleObj.put("prevComponentRef", tr.getPrevComponentRef());
				transformationRuleObj.put("ruleDescription", tr.getRuleDescription());
				transformationRuleObj.put("type", tr.getType());
				transformationRuleObj.put("ruledesctext", tr.getRuledesctext());
				transformationRuleList.put(transformationRuleObj);
			}
			attributeMapObject.put("transformationRuleList", transformationRuleList);
			attributeMapArray.put(attributeMapObject);
		}
	}

	//////////////////////Added for Data Switch - Schema Design Page - Code ends here/////////////////////

	@RequestMapping(value = "schema-designer/{selectedProject}/generate", method = {RequestMethod.GET})
	public ModelAndView schemaGeneration(HttpServletRequest request,@PathVariable("selectedProject") String selectedProject){
		dslogger.info("Navigating to generation page");
		ModelAndView mv = new ModelAndView("migrate/schemaGenerate");
		mv.addObject("selectedProject", selectedProject);
		return mv;
	}
}
