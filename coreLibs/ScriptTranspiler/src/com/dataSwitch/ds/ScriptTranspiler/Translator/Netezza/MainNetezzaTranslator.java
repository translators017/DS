
package com.dataSwitch.ds.ScriptTranspiler.Translator.Netezza;

import java.util.LinkedHashMap;

import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class MainNetezzaTranslator {

	public static iStatement processNetezzaTranslator(String extension, LinkedHashMap<Float, iStatement> sqlNodeMaps,
			int currentDbId, int newDbId, String outpath, String fileType, String filename, String configJsonPath, String currentFileType)
	{	
		if(currentFileType.equalsIgnoreCase("DDL") || currentFileType.equalsIgnoreCase("DML Queries") || currentFileType.equalsIgnoreCase("PLSQL Proc"))
		    return getiStatObjtForNetezzaDb(extension, sqlNodeMaps, currentDbId, newDbId, outpath, fileType, filename, configJsonPath);
		
		return null;
		
	}

	private static iStatement getiStatObjtForNetezzaDb(String extension, LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId, int newDbId, String outpath, String fileType, String filename, String configJsonPath)
	{
		
		iBlockStatement iBlockStat = new iBlockStatement();
		
		iBlockStat.iBlockStatements = sqlNodeMaps;
		iBlockStat.type =  StatType.UNKNOWNSTAT;
		
		return iBlockStat;
		
	}

}