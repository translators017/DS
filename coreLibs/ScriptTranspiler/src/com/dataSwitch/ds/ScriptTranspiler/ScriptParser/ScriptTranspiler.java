
package com.dataSwitch.ds.ScriptTranspiler.ScriptParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.omg.Messaging.SyncScopeHelper;

import com.dataSwitch.ds.ScriptTranspiler.Compiler.ELTCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Interpreter.DbInterpreter;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class ScriptTranspiler 
{
	public ScriptTranspiler(String input, String outpath, String selectedCatalogNames, String filename, String extension,
			int currentDbId, String currentFileType, int newDbId, String fileType, Console console,
			String configJsonPath, String metaDataJsonPath) {
		BufferedReader reader = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;

		try {
			fis = new FileInputStream(FilenameUtils.normalize(input));
			isr = new InputStreamReader(fis,"UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LinkedHashMap<Integer, iStatement> slineMaps = new LinkedHashMap<Integer, iStatement>();
		String currentDb = getDBNamesFromToolTypeConstant(currentDbId);
		
		try {
			int giEmptyLine = 1;
			int giStamentOrder = 1;

			console.put("Message", "Started Processing (DB - " + currentDb + ") Script...");
			reader = new BufferedReader(isr);

			int giIndentOrder = 0;
			

			List<String> singleCommentsVal = new ArrayList<String>();
			List<String> multiCommentsVal = new ArrayList<String>();
			List<String> inlineCommentsVal = new ArrayList<String>();

			if (currentDbId == ToolTypeConstants.TERADATA) {
				Utils.addCommentsForTeraData(singleCommentsVal, multiCommentsVal, inlineCommentsVal);
			} else if (currentDbId == ToolTypeConstants.ORACLE || currentDbId == ToolTypeConstants.VERTICA
					|| currentDbId == ToolTypeConstants.NETEZZA) {
				Utils.addCommentsForOracle(singleCommentsVal, multiCommentsVal, inlineCommentsVal);
			}

			Utils.readCodeAndClearComments(slineMaps, reader, giEmptyLine, giIndentOrder, giStamentOrder, singleCommentsVal,
					multiCommentsVal, inlineCommentsVal);
			
			reader.close();
			fis.close();
			isr.close();
		}
		
		catch (IOException e) {
			
			console.put("Error", "Issue in Commented Lines");
			e.printStackTrace();
		}
		
			console.put("Message", " ");
	
			console.put("Message", "Reverse Engineering (DB - " + currentDb + ") Script...");
	
			console.put("Message", " ");
			console.put("Message", "Started Conversion... ");
			console.put("Message", " ");
	
			console.put("Message", "Script Name - " + filename);
			
			try
			{
				LinkedHashMap<Integer, iStatement> statementNodeMaps = Utils.getStatementObjeds(slineMaps, currentDbId, newDbId, extension);
				
				LinkedHashMap<Float, iStatement> statementObjedsWithMergedSql = Utils.mergeStatementsOfASqlQuery(statementNodeMaps, currentDbId);
				
				iStatement statementObjectWithBlocks = DbInterpreter.createBlockStatements(statementObjedsWithMergedSql, currentDbId, extension, newDbId, outpath, fileType, filename, configJsonPath, console, currentFileType);
				
				ELTCompiler.ELTScriptCompiler(statementObjectWithBlocks, currentDbId, newDbId, filename, outpath, selectedCatalogNames, console, fileType, configJsonPath, metaDataJsonPath, statementObjedsWithMergedSql);
				
			}
			catch(Exception e)
			{
				console.put("Error", "\n\nConversion aborted due to Error");
				e.printStackTrace();
			}

	}


	public static String getDBNamesFromToolTypeConstant(int currentDbId) {
		HashMap<String, Integer> toolKey = com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants.getMap();
		String temp = null;
		for (Entry<String, Integer> entrySet : toolKey.entrySet()) {
			if (entrySet.getValue() == currentDbId) {
				temp = entrySet.getKey();
				return temp;
			}
		}
		return null;
	}

}