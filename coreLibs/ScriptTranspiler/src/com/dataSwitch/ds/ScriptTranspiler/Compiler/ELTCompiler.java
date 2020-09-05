
package com.dataSwitch.ds.ScriptTranspiler.Compiler;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dataSwitch.ds.ScriptTranspiler.Compiler.AzureSqlDB.AzureSqlDBCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.AzureSynapse.AzureSynapseCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.GoogleBigQuery.GoogleBigQueryCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.Redshift.RedshiftCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.Snowflake.SnowFlakeProcCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.Snowflake.SnowflakeCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Interpreter.DbInterpreter;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.Script.StatementObjts.dsProc;

public class ELTCompiler 
{

	public static void ELTScriptCompiler(iStatement istatObject, int currentDbId, int newDbId,
			String filename, String outpath, String selectedCatalogNames, Console console, String fileType, 
			String configJsonPath, String metaDataJsonPath, LinkedHashMap<Float, iStatement> sqlNodeMaps)
	{
		
		LinkedHashMap<Float, ArrayList<Token>> updatedMaps = getUpdatedSQLNodeMaps(istatObject,currentDbId);
		
		LinkedHashMap<String, LinkedHashMap<String, String>> configHash = getConfigValues(configJsonPath);
		
		if (newDbId == ToolTypeConstants.SNOWFLAKE)
			SnowflakeCompiler.processSnowflakeCompiler ( istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, fileType, configHash);
		
		else if (newDbId == ToolTypeConstants.AZURESQL)
			AzureSynapseCompiler.processAzureSynapseCompiler ( istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType);
		
		else if (newDbId == ToolTypeConstants.SQLSERVER)
			AzureSqlDBCompiler.processAzureSqlDBCompiler ( istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType);
		
		else if (newDbId == ToolTypeConstants.REDSHIFT)
			RedshiftCompiler.prcoessRedshiftCompiler ( istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType);
		
		else if (newDbId == ToolTypeConstants.BIGQUERY)
			GoogleBigQueryCompiler.processGoogleBigQueryCompiler ( istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType);

		else if(newDbId == ToolTypeConstants.DS_TOOL && fileType.equalsIgnoreCase("ETL"))
			com.dataSwitch.ds.sql.SQLQueryRevEngg.MainSQLRevEngg.processSQLForETL(updatedMaps, currentDbId, newDbId, filename, outpath, selectedCatalogNames, metaDataJsonPath, console);
		
	}
	
	public static final LinkedHashMap<String, LinkedHashMap<String, String>> getConfigValues(String configJsonPath) 
	{
		LinkedHashMap<String, LinkedHashMap<String, String>> hashConfigVals = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		JSONParser parser = new JSONParser();
		Object obj = null;
		FileReader fileRd = null;
		try 
		{
			fileRd = new FileReader(FilenameUtils.normalize(configJsonPath));
			obj = parser.parse(fileRd);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray scriptHeaderConfig = (JSONArray) jsonObject.get("scriptRemediationConfig");
			
			for(int j2  = 0 ; j2 < scriptHeaderConfig.size(); j2++)
			{	
				JSONObject config = (JSONObject) scriptHeaderConfig.get(j2);
				
				JSONArray schemaArray = (JSONArray) config.get("schema") ;
				
				if(schemaArray != null)
				{
					if(schemaArray.size() > 0)
					{
						for(int i = 0 ; i < schemaArray.size() ; i++)
						{
							JSONObject actionObjt = (JSONObject) schemaArray.get(i);
							
							String schmeaObjtType = (String) actionObjt.get("action");
							if(schmeaObjtType.equalsIgnoreCase("Replace Schema Name"))
							{
								String currentName = (String) actionObjt.get("current");
								String newName = (String) actionObjt.get("newVal");
								LinkedHashMap<String, String> replaceConfigHash = new LinkedHashMap<String, String>();
								
								if(currentName.length() > 0)
									replaceConfigHash.put("curName", currentName);
								if(newName.length() > 0)
									replaceConfigHash.put("newName", newName);
								
								String tableCheck = (String) actionObjt.get("table");
								String procedureNamesCheck = (String) actionObjt.get("storedProcedure");
								String PackageNamesCheck = (String) actionObjt.get("packageObj");
								String fnNamesCheck = (String) actionObjt.get("functionObj");
								String viewNamesCheck = (String) actionObjt.get("view");
								
								getConfigObjtsValues(tableCheck, procedureNamesCheck, PackageNamesCheck, fnNamesCheck, viewNamesCheck, replaceConfigHash);
								
								
								if(currentName.length() > 0)
									hashConfigVals.put(currentName, replaceConfigHash);
							}
							
							if(schmeaObjtType.equalsIgnoreCase("Prefix Schema Name"))
							{
								LinkedHashMap<String, String> prefixHash = new LinkedHashMap<String, String>();
								
								String prefixWithName = "";
								if(actionObjt != null)
									prefixWithName = (String) actionObjt.get("newVal");
								
								if(prefixWithName.length() > 0)
									prefixHash.put("Prefix_With", prefixWithName);
								
								if(actionObjt != null)
								{
									String tableCheck = (String) actionObjt.get("table");
									String procedureNamesCheck = (String) actionObjt.get("storedProcedure");
									String PackageNamesCheck = (String) actionObjt.get("packageObj");
									String fnNamesCheck = (String) actionObjt.get("functionObj");
									String viewNamesCheck = (String) actionObjt.get("view");
									getConfigObjtsValues(tableCheck, procedureNamesCheck, PackageNamesCheck, fnNamesCheck, viewNamesCheck, prefixHash);
								}
								
								if(!prefixHash.isEmpty())
								{
									hashConfigVals.put("PrefixSchemaName", prefixHash);
								}
							}
							
							if(schmeaObjtType.equalsIgnoreCase("Add/Replace Quotes for Object Names"))
							{
								LinkedHashMap<String, String> encloseQuotesHash = new LinkedHashMap<String, String>();
								
								String quotesTyp = "";
								if(actionObjt != null)
								{
									quotesTyp = (String) actionObjt.get("newVal");
								}
								
								if(quotesTyp.length() > 0)
									encloseQuotesHash.put("QuotesType", quotesTyp);
								
								if(actionObjt != null)
								{	
									String quotetableCheck = (String) actionObjt.get("table");
									String quoteprocedureNamesCheck = (String) actionObjt.get("storedProcedure");
									String quotePackageNamesCheck = (String) actionObjt.get("packageObj");
									String quotefnNamesCheck = (String) actionObjt.get("functionObj");
									String quoteviewNamesCheck = (String) actionObjt.get("view");
									
									getConfigObjtsValues(quotetableCheck, quoteprocedureNamesCheck, quotePackageNamesCheck, quotefnNamesCheck, quoteviewNamesCheck, encloseQuotesHash);
								}
								
								if(!encloseQuotesHash.isEmpty())
								{
									hashConfigVals.put("EncloseNameWithQuotes", encloseQuotesHash);
								}
							}
						}
					}
				}
				
			}
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		try {
			fileRd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashConfigVals;
		
	}

	public static void getConfigObjtsValues(String tableCheck, String procedureNamesCheck, String packageNamesCheck, String fnNamesCheck, String viewNamesCheck, LinkedHashMap<String, String> replaceConfigHash) 
	{
		if(tableCheck.equalsIgnoreCase("yes"))
			replaceConfigHash.put("TableNames", "yes");
		if(procedureNamesCheck.equalsIgnoreCase("yes"))
			replaceConfigHash.put("ProcedureNames", "yes");
		if(packageNamesCheck.equalsIgnoreCase("yes"))
			replaceConfigHash.put("PackageNames", "yes");
		if(fnNamesCheck.equalsIgnoreCase("yes"))
			replaceConfigHash.put("FunctionNames", "yes");
		if(viewNamesCheck.equalsIgnoreCase("yes"))
			replaceConfigHash.put("ViewNames", "yes");
	}
	
	public static LinkedHashMap<Float, ArrayList<Token>> getUpdatedSQLNodeMaps(iStatement istatObject, int currentDbId) 
	{
		
		LinkedHashMap<Float, ArrayList<Token>> sqlMaps = new LinkedHashMap<Float, ArrayList<Token>>();

		if(istatObject != null)
		{
			iBlockStatement iblockStat = new iBlockStatement();
			
			if(istatObject instanceof iBlockStatement)
				iblockStat = (iBlockStatement) istatObject;
			
			LinkedHashMap<Float, iStatement> iblockMap = iblockStat.iBlockStatements;
			
			for (Entry<Float, iStatement> entryScript : iblockMap.entrySet())
			{
				ArrayList<Token> getSQLNodes = entryScript.getValue().tokenList;
	            
				int orginalLineNo = entryScript.getValue().originalLineNo;
				float lineNo = orginalLineNo;
	            float getKey = entryScript.getKey();
				if (entryScript.getValue().type == StatType.SQLQUERY) 
				{
					sqlMaps.put(getKey, getSQLNodes);
				}
	
			}
		}
		
		return sqlMaps;
		
	}
	
	public static LinkedList<iStatement> getQueryListFromiStatObjt(String fileType, iStatement istatObject, int newDbId, int currentDbId, String outpath, String filename, String configJsonPath, Console console, LinkedHashMap<String, LinkedHashMap<String, String>> configHash) 
	{
		iBlockStatement iBlockStat = new iBlockStatement();
		
		LinkedList<iStatement> sqlQryList = null;
		
		if(istatObject instanceof iBlockStatement)
			iBlockStat = (iBlockStatement) istatObject;
		
		if (iBlockStat.iBlockStatements.size() <= 0 && (!fileType.equalsIgnoreCase("PLSQL PROC")))
		{
			sqlQryList = new LinkedList<iStatement>();
			dsProc iProc = new dsProc();
			
			if (istatObject.statDetail instanceof dsProc)
				iProc = (dsProc) istatObject.statDetail;
			
			if (iProc.procBlockStatements != null)
			{
				SnowFlakeProcCompiler.getProcValues (fileType, sqlQryList, istatObject, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash);
			}
		}
		
		return sqlQryList;
	}
	
}