package co.dataswitch.Migrate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.dataswitch.utils.Utils;
import co.dataswitch.utils.ZipUtils;


@Controller
public class MigrateController {
	
	private static final Logger dslogger = Logger.getLogger(MigrateController.class);

	@RequestMapping(value = "/home/migrate", method = RequestMethod.GET)
	public ModelAndView migrate(HttpServletRequest request) {
		dslogger.info("Entering migrate controller ...");
		ModelAndView mv = new ModelAndView("migrate/migrate");
		return mv;
	}
	
	@RequestMapping(value = "/home/migrate/schema-migrator", method = RequestMethod.GET)
	public ModelAndView schemaMigrator(HttpServletRequest request) {
		dslogger.info("Entering schema migrator controller ...");
		ModelAndView mv = new ModelAndView("migrate/schemaMigrator/schema_migrator");
		return mv;
	}
	
	@RequestMapping(value = "/home/migrate/data-migrator", method = RequestMethod.GET)
	public ModelAndView processMigrator(HttpServletRequest request) {
		dslogger.info("Entering data migrator controller ...");
		ModelAndView mv = new ModelAndView("migrate/dataMigrator/data_migrator");
		return mv;
	}
	
	@RequestMapping(value = "/home/migrate/process-migrator", method = RequestMethod.GET)
	public ModelAndView dataMigrator(HttpServletRequest request) {
		dslogger.info("Entering process migrator controller ...");
		ModelAndView mv = new ModelAndView("migrate/processMigrator/process_migrator");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/schemaDesigner/{selectedProject}/visualize", method = RequestMethod.GET)
	public String visualization(HttpServletRequest request, @PathVariable("selectedProject") String selectedProject) throws IOException {
		dslogger.info("Entering visualization ...");
		
		String noSqlPath = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		String configPath = noSqlPath + File.separator + username + File.separator + "SchemaDesigner" + File.separator + selectedProject + File.separator + "schemaDesign" + File.separator + "schemaEntities.bin";
		String collectionPath = noSqlPath + File.separator + username + File.separator + "SchemaDesigner" + File.separator + selectedProject + File.separator + "schemaDesign" + File.separator + "schemaEntities";
		
		File configFile = new File(configPath);
		String schemaEntities = FileUtils.readFileToString(configFile, "UTF-8");
		
		JSONObject schemaEntitiesObj = new JSONObject(schemaEntities);
		
		JSONArray collectionsArray = new JSONArray();
		File collectionFile = new File(collectionPath);
		File [] fileList = collectionFile.listFiles();
		for(File file : fileList)
		{
			String collections = FileUtils.readFileToString(file, "UTF-8");
			JSONObject collectionsObj = new JSONObject(collections);
			collectionsArray.put(collectionsObj);
		}
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("schemaEntities", schemaEntitiesObj.toString());
		outputJson.put("attributes", collectionsArray);
		String outputVal = outputJson.toString();
		return outputVal;
	}
	
	@RequestMapping(value = "/schemaDesigner/{selectedProject}/downloadSchema", method = RequestMethod.POST)
	public void downloadingSchema(HttpServletRequest request, @PathVariable("selectedProject") String selectedProject, HttpServletResponse response) throws IOException {
		dslogger.info("Downloading Schema");
		
		String schemaFile = request.getServletContext().getRealPath("/")+ File.separator + "resources" + File.separator + "data" + File.separator+ "output_schema.txt"; 
		File file = new File(schemaFile);
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename=output_schema.txt");
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			OutputStream responseOutputStream = response.getOutputStream();
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
			fileInputStream.close();
			responseOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
