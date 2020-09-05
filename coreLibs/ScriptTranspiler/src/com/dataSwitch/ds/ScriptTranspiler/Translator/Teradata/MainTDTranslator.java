
package com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.ScriptTranspiler.Interpreter.TranslatorUtils;
import com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules.AddCurleyBraceForScriptKeyWords;
import com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules.ScriptKeywordsForDbs;
import com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules.ScriptTypeRule;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.Utils;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class MainTDTranslator 
{
	public static iStatement processTDTranslator(String extension, LinkedHashMap<Float, iStatement> sqlNodeMaps,
			int currentDbId, int newDbId, String outpath, String fileType, String filename, String configJsonPath, Console console, String currentFileType) 
	{	
		if(extension.equalsIgnoreCase(".tp") || currentFileType.equalsIgnoreCase("TPMP"))
			return TDtPumpDtls.processTdPumpScripts (sqlNodeMaps, currentDbId, newDbId, extension);
		
		else if(extension.equalsIgnoreCase(".btq") || extension.equalsIgnoreCase(".bteq") 
				|| currentFileType.equalsIgnoreCase("BTEQ"))
			return processBteqScripts (sqlNodeMaps, currentDbId, newDbId);
		
		else if(extension.equalsIgnoreCase(".spl") || currentFileType.equalsIgnoreCase("PLSQL Proc"))
		{
			AddCurleyBraceForScriptKeyWords.addCurleyBracesForProcScripts(sqlNodeMaps, currentDbId, newDbId);
		
			LinkedHashMap<String, ArrayList<iStatement>> updBlockNodes = TranslatorUtils.getStatementNodes (sqlNodeMaps, currentDbId, "{", "}", extension, newDbId);
		
			return getiStatObjectForProcs(currentDbId, newDbId, extension, updBlockNodes, currentFileType);
		}
		else if(currentFileType.equalsIgnoreCase("DDL") || currentFileType.equalsIgnoreCase("(k)sh") || 
				currentFileType.equalsIgnoreCase("MACRO") || currentFileType.equalsIgnoreCase("DML Queries") )
		{
			return getiStatObjtForTdDb(extension, sqlNodeMaps, currentDbId, newDbId, outpath, fileType, filename, configJsonPath);
		}
		
		return getiStatObjtForTdDb(extension, sqlNodeMaps, currentDbId, newDbId, outpath, fileType, filename, configJsonPath);
	}

	private static iStatement getiStatObjtForTdDb(String extension, LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId, int newDbId, String outpath, String fileType, String filename, String configJsonPath)
	{
		
		iBlockStatement iBlockStat = new iBlockStatement();
		
		iBlockStat.iBlockStatements = sqlNodeMaps;
		iBlockStat.type =  StatType.UNKNOWNSTAT;
		
		return iBlockStat;
		
	}
	
	private static iStatement getiStatObjectForProcs(int currentDbId, int newDbId, String extension, LinkedHashMap<String, ArrayList<iStatement>> updBlockNodes, String currentFileType) 
	{
		iStatement iStatDetail = new iStatement();
		
		
		ArrayList<iStatement> outeriStatDetail = TranslatorUtils.getOuteriStatDetail(updBlockNodes);
	
		for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(outeriStatDetail, currentDbId, TDProcDtls.getTdBlockScriptKeyWords()).entrySet())
	    {
			Token startToken = entrySet.getKey();
			Token endToken = entrySet.getValue();
			
			for(ArrayList<iStatement> entrySetmap : TranslatorUtils.getGroupOfiStatObject(startToken,endToken, outeriStatDetail))
			{
				if(startToken.data.equalsIgnoreCase("replace") || startToken.data.equalsIgnoreCase("create"))
				{
					fillProcStatDetailObjt(startToken, endToken, updBlockNodes, iStatDetail, entrySetmap, currentDbId, newDbId);
				}
			}
			
	    }
		
		return iStatDetail;
	}
	
	public static void fillProcStatDetailObjt(Token startToken, Token endToken,
			LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, iStatement istatDetail, ArrayList<iStatement> entrySetmap, int currentDbId, int newDbId) 
	{
		
	    boolean checkProcScript = isProcedureScript(entrySetmap, "Procedure");
	    
	    if(checkProcScript)
	    {
	    	if(checkProcScript)
	    		istatDetail.type = StatType.PROCSTAT;
	    	else
	    		istatDetail.type = StatType.UNKNOWNSTAT;
	    	
	    	istatDetail.statDetail = TDProcDtls.createProcStatObjt(istatDetail, entrySetmap, startToken, endToken, blockStatDtls, currentDbId, newDbId);
	    }
	}

	private static boolean isProcedureScript(ArrayList<iStatement> entrySetmap, String string) 
	{
		for(int i = 0 ; i < entrySetmap.size() ;i++)
		{
			if(entrySetmap.get(i).tokenList.get(0).data.equalsIgnoreCase(string))
				return true;
		}
		
		return false;
	}


	private static iStatement processBteqScripts(LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId, int newDbId)
	{
		iBlockStatement iBlockStat = new iBlockStatement();
		
		iBlockStat.iBlockStatements = sqlNodeMaps;
		iBlockStat.type =  StatType.UNKNOWNSTAT;
		
		return iBlockStat;
	}
}