
package com.dataSwitch.ds.ScriptTranspiler.Interpreter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;

import com.dataSwitch.ds.ScriptTranspiler.Compiler.ELTCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.PythonCompiler;
import com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules.ScriptKeywordTypeMapRules;
import com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules.ScriptKeywordsForDbs;
import com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules.ScriptTypeRule;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.Utils;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Netezza.MainNetezzaTranslator;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Oracle.MainOracleTranslator;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata.MainTDTranslator;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata.TDProcDtls;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata.TDtPumpDtls;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Vertica.MainVerticaTranslator;
import com.dataSwitch.ds.commons.datatyperules.DataTypeRule;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;
import com.dataSwitch.ds.sql.SQLParser.SQLUtils;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class DbInterpreter 
{
	
	public static iStatement createBlockStatements(
			LinkedHashMap<Float, iStatement> statementObjedsWithMergedSql, int currentDbId, String extension, int newDbId, String outpath, String fileType, String filename, String configJsonPath, Console console, String currentFileType)
	{
		if (currentDbId == ToolTypeConstants.TERADATA || currentDbId == ToolTypeConstants.VERTICA
				|| currentDbId == ToolTypeConstants.NETEZZA || currentDbId == ToolTypeConstants.ORACLE)// Teradata To For Remaining db's we have to create the separate class
		{
			iStatement dbStatDtls = DbInterpreter.getDbiStatementDtls(extension, statementObjedsWithMergedSql, currentDbId, newDbId, outpath, fileType, filename, configJsonPath, console, currentFileType);
			
			return dbStatDtls;
		} 
		
		return null;
	}

	private static iStatement getDbiStatementDtls(String extension,
			LinkedHashMap<Float, iStatement> statementObjedsWithMergedSql, int currentDbId, int newDbId,
			String outpath, String fileType, String filename, String configJsonPath, Console console, String currentFileType)
	{
	    
		if(currentDbId == ToolTypeConstants.TERADATA)
			return MainTDTranslator.processTDTranslator(extension, statementObjedsWithMergedSql, currentDbId, newDbId, outpath, fileType, filename, configJsonPath, console, currentFileType);
        
		else if(currentDbId == ToolTypeConstants.ORACLE)
			return MainOracleTranslator.processOraTranslator(extension, statementObjedsWithMergedSql, currentDbId, newDbId, outpath, fileType, filename, configJsonPath, currentFileType);
		
		else if(currentDbId == ToolTypeConstants.VERTICA)
			return MainVerticaTranslator.processVerticaTranslator(extension, statementObjedsWithMergedSql, currentDbId, newDbId, outpath, fileType, filename, configJsonPath, currentFileType);
	    
		else if(currentDbId == ToolTypeConstants.NETEZZA)
			return MainNetezzaTranslator.processNetezzaTranslator(extension, statementObjedsWithMergedSql, currentDbId, newDbId, outpath, fileType, filename, configJsonPath, currentFileType);
		
		return null;
	}

}