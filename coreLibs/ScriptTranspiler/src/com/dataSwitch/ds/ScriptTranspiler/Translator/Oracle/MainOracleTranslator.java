
package com.dataSwitch.ds.ScriptTranspiler.Translator.Oracle;

import java.util.LinkedHashMap;

import com.dataSwitch.ds.ScriptTranspiler.Interpreter.TranslatorUtils;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class MainOracleTranslator 
{

	public static iStatement processOraTranslator(String extension, LinkedHashMap<Float, iStatement> sqlNodeMaps,
			int currentDbId, int newDbId, String outpath, String fileType, String filename, String configJsonPath, String currentFileType) 
	{
		
		if(currentFileType.equalsIgnoreCase("DDL") || currentFileType.equalsIgnoreCase("DML Queries"))// || currentFileType.equalsIgnoreCase("PLSQL Proc"))
		    return getiStatObjtForOracleDb(extension, sqlNodeMaps, currentDbId, newDbId, outpath, fileType, filename, configJsonPath);
		
		else if (currentFileType.equalsIgnoreCase("PLSQL Proc"))
			return OracleProcTranslator.getiStatementProcObjeds(sqlNodeMaps, currentDbId, newDbId, extension, currentFileType);
	
		
		return null;
		
	}
	
	public static iStatement getiStatObjtForOracleDb(String extension, LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId, int newDbId, String outpath, String fileType, String filename, String configJsonPath)
	{
		
		iBlockStatement iBlockStat = new iBlockStatement();
		
		iBlockStat.iBlockStatements = sqlNodeMaps;
		iBlockStat.type =  StatType.UNKNOWNSTAT;
		
		return iBlockStat;
		
	}

	
}