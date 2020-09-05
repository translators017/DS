package co.dataswitch.Migrate;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hemf.record.HemfText.ExtCreateFontIndirectW;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.xml.TalendCodegen.CreateTalendCodegen;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.dataswitch.common.Console;
import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.ProcessConfig;
import co.dataswitch.nosqldbDTO.ProcessGroup;
import co.dataswitch.nosqldbDTO.processRun;
import co.dataswitch.utils.InputValidation;
import co.dataswitch.utils.Utils;
import co.dataswitch.utils.ZipUtils;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcessConverter {
	private static final Logger dslogger = Logger.getLogger(ProcessConverter.class);

	@GetMapping(value = "/home/migrate/process-migrator/process-converter")
	public ModelAndView processConverter(HttpServletRequest request, Model model) {
		dslogger.info("Entering process converter");
		ArrayList<String> data = null;
		ProcessGroup processGroup;
		List<ProcessGroup> groupList = new ArrayList<>();
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		ModelAndView mv = new ModelAndView("migrate/processMigrator/processConverter/processConverter");
		File processGroupFile = new File(dataStoreDir + File.separator + username + File.separator+ "ProcessGroups");
		if(!processGroupFile.exists())
			processGroupFile.mkdirs();

		try {
			data = DBEngine_new.getKeyValuesList("ProcessGroups",dataStoreDir + File.separator + username);
		} catch (Exception e) {
			dslogger.error("Error in getting values from db - "+e.getMessage());
		}
		if(data != null)
		{
			for(int i = 0; i < data.size(); i++)
			{
				try {
					processGroup = (ProcessGroup) DBEngine_new.getData("ProcessGroups", data.get(i),dataStoreDir + File.separator + username);
					groupList.add(processGroup);

				}catch (Exception e) {
					dslogger.error("Error in getting values from db - "+e.getMessage());
				}
			}
		}else{
			dslogger.error("Unable to fetch catalog data from db");
		}
		mv.addObject("type", model.asMap().get("type"));
		mv.addObject("message", model.asMap().get("message"));
		mv.addObject("processGroups", groupList);
		return mv;
	}

	@ResponseBody
	@GetMapping(value = "/home/migrate/process-migrator/process-converter/getRunDetails")
	public String getRunDetails(HttpServletRequest request) throws JsonProcessingException {
		dslogger.info("Getting run details");
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		ProcessGroup processGrp = null;
		String groupName = InputValidation.getCleanedString(request.getParameter("groupName"));
		try {
			processGrp = (ProcessGroup) DBEngine_new.getData("ProcessGroups", groupName ,dataStoreDir + File.separator + username);

		}catch (Exception e) {
			dslogger.error("Error in getting values from db - "+e.getMessage());
		}
		
		ObjectMapper obj = new ObjectMapper();
		String json = obj.writeValueAsString(processGrp.processRunList);
		System.out.println(json);
		return json;
	}

	@ResponseBody
	@PostMapping(value = "/home/migrate/process-migrator/process-converter/addFilesToDTO")
	public String addFilesToDTO(HttpServletRequest request) throws JsonProcessingException {
		dslogger.info("adding Files To DTO");
		String groupName = InputValidation.getCleanedString(request.getParameter("selectedGroup"));
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		ProcessGroup grp = Utils.getProcessGroupObj(groupName,dataStoreDir,username);
		String filenames [] = request.getParameter("filenames").split(",");
		List<processRun> addedFiles = new ArrayList<>();
		for(String name : filenames)
		{
			name = InputValidation.getCleanedString(name);
			long time = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(time);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY HH:mm:ss");
			UUID uuid = UUID.randomUUID();
			processRun runObj = new processRun();
			runObj.runId = uuid.toString();
			runObj.fileName = name;
			runObj.startTime = dateFormat.format(timestamp);
			grp.processRunList.add(0,runObj);
			addedFiles.add(0,runObj);
		}
		try {
			dslogger.info("Updating run list to process group");
			DBEngine_new.updateData("ProcessGroups", groupName, grp, dataStoreDir+ File.separator +username);
		} catch (Exception e) {
			dslogger.error(e.getMessage());
		}

		File groupFolder = new File(dataStoreDir + File.separator + username + File.separator +"ProcessGroups" + File.separator + groupName);
		groupFolder.mkdirs();

		ObjectMapper obj = new ObjectMapper();
		String json = obj.writeValueAsString(addedFiles);

		return json;
	}

	@GetMapping(value = "/home/migrate/process-migrator/process-converter/add-process")
	public ModelAndView addProcessGroup(HttpServletRequest request) {
		dslogger.info("Adding process group");
		ArrayList<String> data = null;
		ArrayList<String> projectData = null;
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		ModelAndView mv = new ModelAndView("migrate/processMigrator/processConverter/addProcessGroup");
		try {
			data = DBEngine_new.getKeyValuesList("Catalogs",dataStoreDir + File.separator + username);
		} catch (Exception e) {
			dslogger.error("Error in getting values from db - "+e.getMessage());
		}

		try {
			projectData = DBEngine_new.getKeyValuesList("SchemaDesigner",dataStoreDir + File.separator + username);
		} catch (Exception e) {
			dslogger.error("Error in getting projectData values from db - "+e.getMessage());
		}

		String root = request.getServletContext().getRealPath("/");
		String sessionId = request.getSession(false).getId();
		File fullPath = new File(root + "temp" + File.separator + sessionId + File.separator +"ScriptConverter");
		if(!fullPath.exists())
			fullPath.mkdirs();

		File[] list = fullPath.listFiles();
		for(File file : list)
			Utils.cleanUpAndDeleteFolder(file);


		mv.addObject("catalogNames", data);
		mv.addObject("projectData", projectData);
		return mv;
	}

	@ResponseBody
	@PostMapping(value = "/home/migrate/process-migrator/process-converter/add-process-group")
	public boolean addNewProcess(HttpServletRequest request) {
		System.out.println("Adding new process group");
		boolean isInsert = false;
		String groupName = InputValidation.getCleanedString(request.getParameter("groupName"));
		String json = InputValidation.getCleanedString(request.getParameter("json"));
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		String basePath = dataStoreDir + File.separator + username + File.separator + "ProcessGroups";
		if(!new File(basePath).exists())
			new File(basePath).mkdirs();

		System.out.println(json);
		org.json.JSONObject obj = new org.json.JSONObject(json);

		long time = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY HH:mm:ss");

		String currentCode = (String) obj.getString("currentCode");
		String targetCode = (String) obj.getString("targetCode");
		String currentTech = (String) obj.getString("currentTech");
		String targetTech = (String) obj.getString("targetTech");

		ProcessGroup processGrp = new ProcessGroup();
		processGrp.groupName = groupName;
		processGrp.lastModified = dateFormat.format(timestamp);
		processGrp.processType = currentCode +" - "+ targetCode;
		processGrp.selectedTechnology = currentTech +" - "+ targetTech;

		ProcessConfig config = new ProcessConfig();
		config.currentCode = currentCode;
		config.targetCode = targetCode;
		config.currentTech = currentTech;
		config.targetTech = targetTech;


		if(currentCode.equalsIgnoreCase("db") && targetCode.equalsIgnoreCase("db"))
		{
			String currentScript = (String) obj.get("currentScript");
			String targetScript = (String) obj.get("targetScript");
			String schemaMappingName = (String) obj.get("schemaMappingName");

			config.currentScript = currentScript;
			config.targetScript = targetScript;
			config.schemaMappingName = schemaMappingName;
		}
		if(currentCode.equalsIgnoreCase("db") && targetCode.equalsIgnoreCase("etl"))
		{
			String currentScript = (String) obj.get("currentScript");
			String targetProcessFlow = (String) obj.get("processFlow");

			config.currentScript = currentScript;
			config.targetProcessFlow = targetProcessFlow;
			config.sourceCatalogList = (String) obj.get("catalogList");
		}
		processGrp.config = config;

		//save to db
		try {
			dslogger.info("Inserting catalog data into database");
			isInsert = DBEngine_new.insertData("ProcessGroups", groupName, processGrp, dataStoreDir+ File.separator +username);
		} catch (Exception e) {
			dslogger.error(e.getMessage());
		}


		return isInsert;
	}

	@ResponseBody
	@PostMapping(value="/home/migrate/process-migrator/process-converter/{selectedGroup}/{runID}/uploadScript")
	public synchronized String uploadScriptFile(MultipartHttpServletRequest request, @PathVariable("runID") String runID, @PathVariable("selectedGroup") String selectedGroup) {
		dslogger.info("Uploading script file");
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		File basePath = new File(dataStoreDir + File.separator + username + File.separator + "ProcessGroups");
		File runIDFolder = new File(basePath + File.separator + selectedGroup + File.separator + runID + File.separator + "input");
		if(!runIDFolder.exists())
			runIDFolder.mkdirs();

		List<MultipartFile> files = request.getFiles("files");
		for (int i = 0; i < files.size(); i++) {
			MultipartFile mpf = files.get(i);		
			String fileName = "";
			if (!mpf.isEmpty()) {
				fileName = mpf.getOriginalFilename();
				try {
					byte[] bytes = mpf.getBytes();
					fileName = mpf.getOriginalFilename();

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(runIDFolder.getPath(),fileName)));
					stream.write(bytes);
					stream.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}


		return null;
	}

	@ResponseBody
	@PostMapping(value="/home/migrate/process-migrator/process-converter/{selectedGroup}/{runID}/convertScript")
	public synchronized String convertFile(HttpServletRequest request, @PathVariable("runID") String runID, @PathVariable("selectedGroup") String selectedGroup) {
		dslogger.info("Converting script file");
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		runID = InputValidation.getCleanedString(runID);
		selectedGroup = InputValidation.getCleanedString(selectedGroup);
		File basePath = new File(dataStoreDir + File.separator + username + File.separator + "ProcessGroups" + File.separator + selectedGroup +  File.separator + runID);
		String convertedPath = basePath + File.separator + "output" + File.separator;
		String configPath = request.getServletContext().getRealPath("/") + File.separator + "resources" + File.separator + "data" + File.separator + "scriptMigratorConfig.json";
		String catalogPath = dataStoreDir + File.separator + username + File.separator + "Catalogs" + File.separator;
		File inputPath = new File(basePath+ File.separator + "input");
		String inputPathWithFile = inputPath.getAbsolutePath() + File.separator + inputPath.listFiles()[0].getName();
		String newCatalogString = "";
		String json = "";
		File outputFolder = new File(convertedPath);
		if(!outputFolder.exists())
			outputFolder.mkdirs();

		String extension = FilenameUtils.getExtension(inputPath.listFiles()[0].getAbsolutePath());
		String fileName = FilenameUtils.removeExtension(inputPath.listFiles()[0].getName());
		ProcessGroup grp = Utils.getProcessGroupObj(selectedGroup, dataStoreDir, username);
		int currentDbId = Utils.toolNo(grp.config.currentTech);
		int newDbId = Utils.toolNo(grp.config.targetTech);
		String sourceScript = grp.config.currentScript;
		String targetScript = grp.config.targetScript;

		if(grp.config.targetCode.equalsIgnoreCase("ETL"))
		{
			for(String ct : grp.config.sourceCatalogList.split(","))
			{
				newCatalogString += "\"" +ct + "\"" +",";
			}
			newCatalogString = newCatalogString.substring(0, newCatalogString.length()-1);
			newCatalogString = "[" + newCatalogString + "]";
			newDbId = ToolTypeConstants.DS_TOOL;
			targetScript = "ETL";
		}else {
			newCatalogString = null;
		}
		Console console = new Console();

		long time = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY HH:mm:ss");
		String lastModifiedDate = dateFormat.format(timestamp);
		processRun runObj = getRunObject(grp.processRunList, runID);
		if(runObj != null)
		{
			try {
				ScriptTranspiler transpiler = new ScriptTranspiler(inputPathWithFile , convertedPath, newCatalogString, fileName, extension, currentDbId, sourceScript, newDbId, targetScript, console, configPath, catalogPath);
				if(targetScript != null && targetScript.equalsIgnoreCase("ETL"))
				{
					//call codegen
					CreateTalendCodegen createTalendJobscript =  new CreateTalendCodegen(convertedPath+ File.separator +fileName+"_connector.xml", convertedPath);
					createTalendJobscript.TalendcodeGenStarts(null);
				}
				if(new File(convertedPath).listFiles().length > 0)
				{
					runObj.status = "success";
					runObj.endTime = lastModifiedDate;
					parseReportFile(convertedPath, runObj);
				}else {
					runObj.status = "failed";
					runObj.endTime = lastModifiedDate;
				}
				grp.lastModified = lastModifiedDate;

			}catch(Exception e) {
				e.printStackTrace();
				runObj.status = "failed";
				runObj.endTime = lastModifiedDate;
			}
			try {
				DBEngine_new.updateData("ProcessGroups", selectedGroup, grp, dataStoreDir+ File.separator +username);
				ObjectMapper obj = new ObjectMapper();
				json = obj.writeValueAsString(runObj);
				
				if(grp.config.targetTech!= null && grp.config.targetTech.equalsIgnoreCase("talend"))
				{
					File talendPropFile = new File(request.getServletContext().getRealPath("/")+ File.separator + "resources" + File.separator + "data" + File.separator + "talend.project");
					File targetFolder = new File(convertedPath+ File.separator + "OutputCode"+ File.separator+ "talend.project");
					FileUtils.copyFile(talendPropFile, targetFolder);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

		return json;

		//		String sourceDb = request.getParameter("sourceDb");
		//		String targetDb = request.getParameter("targetDb");
		//		String sourceScript = request.getParameter("sourceScript");
		//		String targetScript = request.getParameter("targetScript");
		//		String targetETL = request.getParameter("targetETL");
		//		String catalogList = request.getParameter("catalogList");
		//		String root = request.getServletContext().getRealPath("/");
		//		String sessionId = request.getSession(false).getId();
		//		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		//		String username = (String) request.getSession().getAttribute("username");
		//		String basePath = root + "temp" + File.separator + sessionId + File.separator + "ScriptConverter";
		//
		//		File fullPath = new File(basePath + File.separator + "input");
		//		String convertedPath = basePath + File.separator + "output" + File.separator;
		//		String talendOutput = basePath + File.separator + "talend";
		//		String catalogPath = dataStoreDir + File.separator + username + File.separator + "Catalogs" + File.separator;
		//		String configPath = request.getServletContext().getRealPath("/") + File.separator + "resources" + File.separator + "data" + File.separator + "scriptMigratorConfig.json";
		//		File outputZipPath = new File(basePath + File.separator + "output.zip");
		//		if(outputZipPath.exists()) 
		//		{
		//			outputZipPath.delete(); //deleting previous outputs
		//		}
		//		File outputFolder = new File(convertedPath);
		//		if(!outputFolder.exists())
		//		{
		//			outputFolder.mkdirs();
		//		}else
		//		{
		//			try {
		//				FileUtils.cleanDirectory(outputFolder);
		//			}catch(Exception e) {
		//				e.printStackTrace();
		//			}
		//		}
		//		
		//		//changing catalog name
		//		String newCatalogString = "";
		//		for(String ct : catalogList.split(","))
		//		{
		//			newCatalogString += "\"" +ct + "\"" +",";
		//		}
		//		newCatalogString = newCatalogString.substring(0, newCatalogString.length()-1);
		//		newCatalogString = "[" + newCatalogString + "]";
		//
		//		int currentDbId = Utils.toolNo(sourceDb);
		//		int newDbId = Utils.toolNo(targetDb);
		//		
		//		if(targetETL != null && !targetETL.equalsIgnoreCase(""))
		//		{
		//			System.out.println(newCatalogString);
		//			newDbId = ToolTypeConstants.DS_TOOL;
		//			targetScript = "ETL";
		//		}else {
		//			newCatalogString = null;
		//		}
		//
		//		Console console =null;
		//		if(console == null){
		//			console = new Console();
		//			request.getSession().removeAttribute("console");
		//			request.getSession().setAttribute("console", console);
		//		}
		//		console.put("Message", "Converting "+sourceDb+ " "+ sourceScript + " to "+ targetDb + " "+ targetScript);
		//		
		//		File[] listFiles = fullPath.listFiles();
		//		String filenameWithExtension = "";
		//		String fileName = "";
		//		String extension = "";
		//		for(File file : listFiles)
		//		{	
		//			filenameWithExtension = file.getName();
		//			fileName = filenameWithExtension.substring(0,filenameWithExtension.lastIndexOf("."));
		//			extension = filenameWithExtension.substring(filenameWithExtension.lastIndexOf(".")+1, filenameWithExtension.length());
		//
		//			ScriptTranspiler transpiler = new ScriptTranspiler(fullPath +  File.separator + file.getName(), convertedPath, newCatalogString, fileName, extension, currentDbId, sourceScript, newDbId, targetScript, console, configPath, catalogPath);
		//			if(targetETL != null && !targetETL.equalsIgnoreCase(""))
		//			{
		//				//call codegen
		//				
		//				File talendOutputPath = new File(talendOutput);
		//				if(!talendOutputPath.exists())
		//					talendOutputPath.mkdirs();
		//				
		//				CreateTalendCodegen createTalendJobscript =  new CreateTalendCodegen(convertedPath+ File.separator +fileName+"_connector.xml", talendOutput);
		//				createTalendJobscript.TalendcodeGenStarts(null);
		//			}
		//		}
		//		
		//		File talendPropFile = new File(request.getServletContext().getRealPath("/")+ File.separator + "resources" + File.separator + "data" + File.separator + "talend.project");
		//		File targetFolder = new File(talendOutput+ File.separator + "OutputCode"+ File.separator+ "talend.project");
		//		try {
		//			FileUtils.copyFile(talendPropFile, targetFolder);
		//		} catch (IOException e2) {
		//			e2.printStackTrace();
		//		}
		//		
		//		//parse report log
		//		JSONArray summaryArray = new JSONArray();
		//		File reportLog = new File(convertedPath + File.separator + "ReportLog.log");
		//		if(reportLog.exists())
		//		{
		//			parseReportLog(reportLog, summaryArray);
		//		}
		//		System.out.println(summaryArray.toString());
		//		return summaryArray.toString();
	}

	private void parseReportFile(String convertedPath, processRun runObj) {
		// TODO Auto-generated method stub
		File reportLog = new File(convertedPath + File.separator + "ReportLog.log");
		String complexityType = "", automationPercentage = "", sqlQueries="", entTr="", attrTr="", totalTr="", manualTr="";
		try {
			List<String> contents = FileUtils.readLines(reportLog, "UTF-8");
			for (String line : contents) {
				if(line.trim().contains("Complexity Type"))
				{
					complexityType = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
					runObj.complexityType = complexityType;
				}
				if(line.trim().contains("Automation Percentage"))
				{
					automationPercentage = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
					runObj.automationPercentage = automationPercentage;
				}
				if(line.trim().contains("Number of SQL Queries"))
				{
					sqlQueries = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
					runObj.sqlQueryCount = Integer.parseInt(sqlQueries);
				}
				if(line.trim().contains("Number of Entity Transformations"))
				{
					entTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
					runObj.entityTransformationCount = Integer.parseInt(entTr);
				}
				if(line.trim().contains("Number of Attribute Transformations"))
				{
					attrTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
					runObj.attributeTransformationCount = Integer.parseInt(attrTr);
				}
				if(line.trim().contains("Total Number of Transformations"))
				{
					totalTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
					runObj.totalTransformationCount = Integer.parseInt(totalTr);
				}
				if(line.trim().contains("Number of Transformation to be changed Manually"))
				{
					manualTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
					runObj.unsuccessfulTransformationCount = Integer.parseInt(manualTr);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private processRun getRunObject(List<processRun> processRunList, String runID) {
		for(processRun obj : processRunList)
		{
			if(obj.runId.equalsIgnoreCase(runID))
				return obj;
		}
		return null;
	}


	//	@SuppressWarnings("unchecked")
	//	private void parseReportLog(File reportLog, JSONArray summaryArray) {
	//		String scriptName = "", complexityType = "", automationPercentage = "", currentDb="", newDB="", sqlQueries="", entTr="", attrTr="", totalTr="", manualTr="";
	//		JSONObject obj = null;
	//		try {
	//			List<String> contents = FileUtils.readLines(reportLog, "UTF-8");
	//			for (String line : contents) {
	//				
	//				if(line.trim().contains("--------"))
	//				{
	//					obj = new JSONObject();
	//				}
	//                if(line.trim().contains("Script Name"))
	//                {
	//                	scriptName = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("scriptName", scriptName);
	//                }
	//                if(line.trim().contains("CurrentDB"))
	//                {
	//                	currentDb = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("currentDb", currentDb);
	//                }
	//                if(line.trim().contains("NewDB"))
	//                {
	//                	newDB = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("newDB", newDB);
	//                }
	//                if(line.trim().contains("Number of SQL Queries"))
	//                {
	//                	sqlQueries = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("sqlQueries", sqlQueries);
	//                }
	//                if(line.trim().contains("Number of Entity Transformations"))
	//                {
	//                	entTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("entTr", entTr);
	//                }
	//                if(line.trim().contains("Number of Attribute Transformations"))
	//                {
	//                	attrTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("attrTr", attrTr);
	//                }
	//                if(line.trim().contains("Total Number of Transformations"))
	//                {
	//                	totalTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("totalTr", totalTr);
	//                }
	//                if(line.trim().contains("Number of Transformation to be changed Manually"))
	//                {
	//                	manualTr = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("manualTr", manualTr);
	//                }
	//                if(line.trim().contains("Complexity Type"))
	//                {
	//                	complexityType = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("complexityType", complexityType);
	//                }
	//                if(line.trim().contains("Automation Percentage"))
	//                {
	//                	automationPercentage = line.substring(line.lastIndexOf("-")+1, line.length()).trim();
	//                	obj.put("automationPercentage", automationPercentage);
	//                	summaryArray.add(obj);
	//                }
	//            }
	//		}catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//		
	//	}

	@PostMapping(value="/home/migrate/process-migrator/process-converter/{selectedGroup}/{runID}/downloadCode")
	public void downloadConvertedFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("selectedGroup") String selectedGroup, @PathVariable("runID") String runID) {
		dslogger.info("Downloading converted code zip");
		runID = InputValidation.getCleanedString(runID);
		selectedGroup = InputValidation.getCleanedString(selectedGroup);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		String basePath = dataStoreDir + File.separator + username + File.separator + "ProcessGroups" + File.separator + selectedGroup + File.separator + runID;
		String outputPath = basePath + File.separator + "output";
		String fileDir = basePath + File.separator + "output.zip";
		if(new File(fileDir).exists())
		{
			new File(fileDir).delete();
		}
		try { 
			ZipUtils.zipFolder(outputPath, fileDir); 
		} catch (Exception e1) {
			e1.printStackTrace(); 
		} 
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition","attachment;filename="+runID+".zip");

		File file = new File(fileDir); 
		try { 
			FileInputStream fileInputStream = new FileInputStream(file); 
			OutputStream responseOutputStream = response.getOutputStream(); 
			int bytes; while ((bytes = fileInputStream.read()) != -1) 
			{ 
				responseOutputStream.write(bytes); 
			}
			fileInputStream.close(); responseOutputStream.close(); 
		} catch(FileNotFoundException e) { e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	@PostMapping(value="/home/migrate/process-migrator/process-converter/{selectedGroup}/downloadBulk")
	public void downloadBulk(HttpServletRequest request, HttpServletResponse response, @PathVariable("selectedGroup") String selectedGroup) {
		dslogger.info("Downloading bulk code as zip");
		selectedGroup = InputValidation.getCleanedString(selectedGroup);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		String runIds [] = request.getParameter("runIds").split(",");
		String basePath = dataStoreDir + File.separator + username + File.separator + "ProcessGroups";
		File outputPath = new File(basePath + File.separator +"bulk_output");
		File srcDir = null;
		if(!outputPath.exists())
		{
			outputPath.mkdirs();
		}
		String fileDir = basePath + File.separator + "output.zip";
		if(new File(fileDir).exists())
		{
			new File(fileDir).delete();
		}
		try {
			for(String runID : runIds)
			{
				srcDir = new File(basePath + File.separator + selectedGroup + File.separator + runID);
				FileUtils.copyDirectory(srcDir, outputPath);
			}
			ZipUtils.zipFolder(outputPath.getAbsolutePath(), fileDir); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition","attachment;filename=output.zip");

		File file = new File(fileDir); 
		try { 
			FileInputStream fileInputStream = new FileInputStream(file); 
			OutputStream responseOutputStream = response.getOutputStream(); 
			int bytes; while ((bytes = fileInputStream.read()) != -1) 
			{ 
				responseOutputStream.write(bytes); 
			}
			fileInputStream.close(); responseOutputStream.close(); 
		} catch(FileNotFoundException e) { e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}

	@PostMapping(value="/home/migrate/process-migrator/process-converter/{selectedGroup}/{runID}/downloadReportLog")
	public void downloadReportLog(HttpServletRequest request, HttpServletResponse response, @PathVariable("selectedGroup") String selectedGroup, @PathVariable("runID") String runID) {
		dslogger.info("Downloading report log file");
		runID = InputValidation.getCleanedString(runID);
		selectedGroup = InputValidation.getCleanedString(selectedGroup);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		String basePath = dataStoreDir + File.separator + username + File.separator + "ProcessGroups" + File.separator + selectedGroup + File.separator + runID;
		String logPath = basePath + File.separator + "output" + File.separator + "ReportLog.log";

		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename=reportLog.txt");

		File file = new File(logPath); 
		try { 
			FileInputStream fileInputStream = new FileInputStream(file); 
			OutputStream responseOutputStream = response.getOutputStream(); 
			int bytes; while ((bytes = fileInputStream.read()) != -1) 
			{ 
				responseOutputStream.write(bytes); 
			}
			fileInputStream.close(); responseOutputStream.close(); 
		} catch(FileNotFoundException e) { e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	//delete group code
	@PostMapping(value = "home/migrate/process-migrator/process-converter/delete-group")
	public ModelAndView deleteGroup(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		dslogger.info("Entering method to delete catalog");
		String groupName = request.getParameter("groupName");
		groupName = InputValidation.getCleanedString(groupName);
		ModelAndView mv = new ModelAndView("redirect:/home/migrate/process-migrator/process-converter");
		boolean isDelete = false;
		String username = (String) request.getSession().getAttribute("username");
		String noSqlpath = (String) request.getSession().getAttribute("nosqlPath");
		try {
			isDelete = DBEngine_new.deleteData("ProcessGroups", groupName, noSqlpath+ File.separator +username);
			if(isDelete){
				File groupFolder = new File(noSqlpath + File.separator + username + File.separator + "ProcessGroups" + File.separator + groupName);
				if(groupFolder.exists())
					FileUtils.deleteDirectory(groupFolder);
				
				redirectAttributes.addFlashAttribute("type", "Success");
				redirectAttributes.addFlashAttribute("message", "Process group has been deleted successfully.");	
			}
			else{
				redirectAttributes.addFlashAttribute("type", "Failed");
				redirectAttributes.addFlashAttribute("message", "Failed to delete process group. Please try again after sometime.");
			}
		}catch (Exception e) {
			dslogger.error(e.getMessage());
			redirectAttributes.addFlashAttribute("type", "Failed");
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return mv;
	}
		
	//get group details
	@ResponseBody
	@GetMapping(value = "/home/migrate/process-migrator/process-converter/getGroupDetails")
	public String getGroupDetails(HttpServletRequest request) {
		dslogger.info("Getting group details");
		String groupName = request.getParameter("groupName");
		groupName = InputValidation.getCleanedString(groupName);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		String json = "";
		try {
			ProcessGroup grp = Utils.getProcessGroupObj(groupName, dataStoreDir, username);
			ObjectMapper obj = new ObjectMapper();
			json = obj.writeValueAsString(grp.config);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//get outputs for comparison
	@SuppressWarnings("unchecked")
	@ResponseBody
	@GetMapping(value = "/home/migrate/process-migrator/process-converter/{selectedGroup}/{runID}/getOutputs")
	public String getOutputsForRun(HttpServletRequest request, @PathVariable("selectedGroup") String selectedGroup, @PathVariable("runID") String runID) {
		dslogger.info("Getting outputs for run");
		selectedGroup = InputValidation.getCleanedString(selectedGroup);
		runID = InputValidation.getCleanedString(runID);
		String dataStoreDir = (String) request.getSession().getAttribute("nosqlPath");
		String username = (String) request.getSession().getAttribute("username");
		ProcessGroup grp = Utils.getProcessGroupObj(selectedGroup, dataStoreDir, username);
		String inputFileName = "";
		String outputExtension = "";
		if(grp.config.targetScript!=null && grp.config.targetScript.contains("Python"))
			outputExtension = ".py";
		for(processRun run : grp.processRunList)
		{
			if(run.runId.equalsIgnoreCase(runID))
				inputFileName = run.fileName;
		}
		String inputFileNameWithoutExtension = FilenameUtils.removeExtension(inputFileName);
		File basePath = new File(dataStoreDir + File.separator + username + File.separator + "ProcessGroups" + File.separator + selectedGroup + File.separator + runID);
		File inputPath = new File(basePath + File.separator + "input");
		File outputPath = new File(basePath + File.separator + "output");
		File inputFile = new File(inputPath.getAbsolutePath() + File.separator + inputFileName);
		String inputText = "", outputText = "";
		try {
			inputText = FileUtils.readFileToString(inputFile, "UTF-8");
			outputText = FileUtils.readFileToString(new File(outputPath.getAbsolutePath() + File.separator +"Out_"+inputFileNameWithoutExtension+ outputExtension), "UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
		}
		JSONObject content = new JSONObject();
		content.put("input", inputText);
		content.put("output", outputText);
		return content.toString();
	}
}
