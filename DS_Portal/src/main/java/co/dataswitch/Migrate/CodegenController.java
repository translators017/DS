package co.dataswitch.Migrate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dataSwitch.adaptor.DS.dataSwitchAdapter;
import com.dataSwitch.xml.TalendCodegen.CreateTalendCodegen;

import co.dataswitch.utils.Utils;
import co.dataswitch.utils.ZipUtils;

@Controller
public class CodegenController {

	private static final Logger dslogger = Logger.getLogger(CodegenController.class);

	@PostMapping(value = "/home/migrate/schemaDesigner/{selectedProject}/generate-code")
	public void generateCode(HttpServletResponse response, HttpServletRequest request, @PathVariable("selectedProject") String selectedProject) {
		dslogger.info("Entering code generation...");

		String noSqlPath = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		String configPath = noSqlPath + File.separator + username + File.separator + "SchemaDesigner" + File.separator + selectedProject + File.separator + "schemaDesign" + File.separator + "schemaEntities.bin";
		String catalogPath = noSqlPath + File.separator + username + File.separator + "Catalogs";
		String collectionPath = noSqlPath + File.separator + username + File.separator + "SchemaDesigner" + File.separator + selectedProject + File.separator + "schemaDesign" + File.separator + "schemaEntities";
		String outputPath = request.getServletContext().getRealPath("/")+ File.separator + "temp" + File.separator + request.getSession(false).getId()+ File.separator + "codeGeneration";
		File output = new File(outputPath);
		if(!output.exists())
			output.mkdirs();

		dataSwitchAdapter dsAdapter = new dataSwitchAdapter(configPath, outputPath, catalogPath, collectionPath);

		//calling talend codegen
		String talendOutput = outputPath + File.separator + "talend";
		File talendOutputPath = new File(talendOutput);
		if(!talendOutputPath.exists())
			talendOutputPath.mkdirs();

		File [] fileList = output.listFiles();
		for(File adapterFile : fileList)
		{
			CreateTalendCodegen createTalendJobscript =  new CreateTalendCodegen(outputPath+ File.separator +adapterFile.getName(), talendOutput);
			createTalendJobscript.TalendcodeGenStarts(null);
		}

		File talendPropFile = new File(request.getServletContext().getRealPath("/")+ File.separator + "resources" + File.separator + "data" + File.separator + "talend.project");
		File targetFolder = new File(talendOutput+ File.separator + "OutputCode"+ File.separator+ "talend.project");
		try {
			FileUtils.copyFile(talendPropFile, targetFolder);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		//downloading the talend job


		String fileDir = talendOutput+"output.zip";

		try { ZipUtils.zipFolder(talendOutput, fileDir); } catch (Exception e1) {
			e1.printStackTrace(); } response.setContentType("application/zip");
			response.setHeader("Content-Disposition","attachment;filename=Output.zip");

			File file = new File(fileDir); 
			try { 
				FileInputStream fileInputStream = new
						FileInputStream(file); OutputStream responseOutputStream =
						response.getOutputStream(); int bytes; while ((bytes =
						fileInputStream.read()) != -1) { responseOutputStream.write(bytes); 
						}
						fileInputStream.close(); responseOutputStream.close(); 
			} catch
			(FileNotFoundException e) { e.printStackTrace(); 
			} catch (IOException e) {
				e.printStackTrace(); }


	}
}
