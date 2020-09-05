
package com.dataSwitch.ds.ScriptTranspiler.Compiler.Snowflake;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.dataSwitch.ds.ScriptTranspiler.Compiler.CompilerUtils;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.ELTCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.PythonCompiler;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.Script.StatementObjts.dsProc;

public class SnowflakeCompiler 
{

	public static void processSnowflakeCompiler( iStatement istatObject, int newDbId, int currentDbId, String outpath,
			String filename, String configJsonPath, Console console, String fileType,
			LinkedHashMap<String, LinkedHashMap<String, String>> configHash) 
	{
		
		LinkedList<iStatement> sqlQryList = ELTCompiler.getQueryListFromiStatObjt(fileType, istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash);
		
		if(fileType.equalsIgnoreCase("PLSQL PROC"))
			SnowFlakeProcCompiler.getProcValues (fileType, sqlQryList, istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash);
		
		else if(fileType.equalsIgnoreCase("Query") || fileType.equalsIgnoreCase("Python QL") 
				|| fileType.equalsIgnoreCase("DDL"))
		    CompilerUtils.getSQLQueryValues(istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType, sqlQryList);
		
	}
      
}