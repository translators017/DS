
package com.dataSwitch.ds.ScriptTranspiler.Compiler.GoogleBigQuery;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.dataSwitch.ds.ScriptTranspiler.Compiler.CompilerUtils;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.ELTCompiler;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class GoogleBigQueryCompiler {

	public static void processGoogleBigQueryCompiler(iStatement istatObject, int newDbId, int currentDbId, String outpath,
			String filename, String configJsonPath, Console console, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, String fileType) {
	
		LinkedList<iStatement> sqlQryList = ELTCompiler.getQueryListFromiStatObjt(fileType, istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash);
		
		if(fileType.equalsIgnoreCase("Query") || fileType.equalsIgnoreCase("Python QL") 
				|| fileType.equalsIgnoreCase("DDL"))
		    CompilerUtils.getSQLQueryValues(istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType, sqlQryList);
		
	}

}