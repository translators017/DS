
package com.dataSwitch.ds.ScriptTranspiler.ScriptParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
public class MainScriptTranspiler {

  
	public static void main (String[] args)
	{
		String path = System.getProperty("user.dir");
		
		String input = path + File.separator + File.separator + "TestingSQL" + File.separator+ "test.sql";//load_CTO_Adv_Analytics_Rptg.btq//EDW.lms_hotel_activity.tp";

		String metaDataJsonPath = path + File.separator+File.separator+"TestingSQL"+File.separator+"Vertica_Db"+File.separator;
		
		String selectedCatalogNames = "[\"appleSnow\"]";
		
		String extension = input.substring(input.lastIndexOf("."),input.length());
		
		String outputFileName = input.substring(input.lastIndexOf(File.separator)+1,input.lastIndexOf("."));
		
		String outpath = path + File.separator+File.separator+"output"+File.separator;
        
		String configJsonPath = path + File.separator+File.separator+"TestingSQL"+File.separator+"scriptRemediationConfigFormat.json";

        int currentDbId = ToolTypeConstants.NETEZZA;
		int newDbId = ToolTypeConstants.SNOWFLAKE;

	     Console console = new Console();
	     
		String currentFileType = "Query";
		String newDbFileType = "Query";//Python QL";//PLSQL Proc//ETL//DDL";

		ScriptTranspiler spec = new ScriptTranspiler(input, outpath, selectedCatalogNames,outputFileName, extension, currentDbId, currentFileType, newDbId, newDbFileType, console, configJsonPath, metaDataJsonPath);
		
		Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
		
	}
}
