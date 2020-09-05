
package com.dataSwitch.ds.ScriptTranspiler.Compiler.Redshift;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.ScriptTranspiler.Compiler.CompilerUtils;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.ELTCompiler;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.sql.SQLGenerator.QueryGenerator;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.Script.StatementObjts.dsAssignment;
import com.dataSwitch.ds.Script.StatementObjts.dsCallExecute;
import com.dataSwitch.ds.Script.StatementObjts.dsCollectStats;
import com.dataSwitch.ds.Script.StatementObjts.dsDoWhile;
import com.dataSwitch.ds.Script.StatementObjts.dsField;
import com.dataSwitch.ds.Script.StatementObjts.dsFor;
import com.dataSwitch.ds.Script.StatementObjts.dsIF;
import com.dataSwitch.ds.Script.StatementObjts.dsQuery;
import com.dataSwitch.ds.Script.StatementObjts.dsThrows;
import com.dataSwitch.ds.Script.StatementObjts.dsWhile;

public class RedshiftCompiler {

	public static void prcoessRedshiftCompiler(iStatement istatObject, int newDbId, int currentDbId, String outpath,
			String filename, String configJsonPath, Console console, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, String fileType) 
	{
		LinkedList<iStatement> sqlQryList = ELTCompiler.getQueryListFromiStatObjt(fileType, istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash);
		
		if(fileType.equalsIgnoreCase("Query") || fileType.equalsIgnoreCase("Python QL") 
				|| fileType.equalsIgnoreCase("DDL"))
		    CompilerUtils.getSQLQueryValues(istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType, sqlQryList);
		else if(fileType.equalsIgnoreCase("PLSQL PROC"))
			RedshiftProcCompiler.getProcValues (fileType, sqlQryList, istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash);
	}

	
}