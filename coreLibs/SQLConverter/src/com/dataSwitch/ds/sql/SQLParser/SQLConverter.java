
package com.dataSwitch.ds.sql.SQLParser;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.commons.datatyperules.*;
import com.dataSwitch.ds.functionparser.Lexer;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLGenerator.QueryGenerator;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;


public class SQLConverter 
{
	public static void main(String args[])
	{ 
		try
		{		
			String szSrcSQLFileName= System.getProperty("user.dir") + File.separator+"TestingSQL"+File.separator+/*"New"+  File.separator+*/"test_new.sql";
			String SQLExt = szSrcSQLFileName.substring(szSrcSQLFileName.lastIndexOf("."),szSrcSQLFileName.length());
			String tgtSQLSrciptFileName = szSrcSQLFileName.substring(szSrcSQLFileName.lastIndexOf("\\")+1,szSrcSQLFileName.lastIndexOf("."));
			String outpath = System.getProperty("user.dir") + File.separator+"output"+File.separator;
			String logPath = outpath;
			Console console = new Console();
			int currentDBId = ToolTypeConstants.TERADATA;
			int newDBId = ToolTypeConstants.REDSHIFT;
			
			String inputSQL = getBufferedReadSQL(szSrcSQLFileName);
			SQLObject sqlObjt = SQLConverter.processSQl(inputSQL,currentDBId,newDBId);
			//getConvertedQuery(inputSQL, currentDBId, newDBId);
			System.out.println(sqlObjt);
			Runtime runtime = Runtime.getRuntime();
	        // Run the garbage collector
	        runtime.gc();
	        // Calculate the used memory
	        long memory = runtime.totalMemory() - runtime.freeMemory();
	        System.out.println("Used memory is bytes: " + memory);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SQLQuery processSQl(String inputSQLQuery,int currentDBId,int newDBId) 
	{
		String ipSQLQuery = inputSQLQuery.trim();
		ipSQLQuery = ipSQLQuery.substring(ipSQLQuery.length()-1,ipSQLQuery.length());
		
		if(!ipSQLQuery.equalsIgnoreCase(";"))//To append ; 
			inputSQLQuery = inputSQLQuery + ";";
		
		ArrayList<Token> tokens = FunctionTypeConv.getTokens(inputSQLQuery);
		
		getUpdatedTokens(tokens,currentDBId);
		String sqlVal = SQLStatement.generateSQL(tokens);
		
		SQLQuery sqlObj = SQLParser(tokens, sqlVal, currentDBId, newDBId);
		
		return sqlObj;
	}
	
	public static SQLQuery processScriptSQl(ArrayList<Token> tokens, int currentDBId,int newDBId) 
	{
		getUpdatedTokens(tokens,currentDBId);
		String sqlVal = SQLStatement.generateSQL(tokens);
		SQLQuery sqlObj = SQLParser(tokens, sqlVal, currentDBId, newDBId);
		
		return sqlObj;
	}
	
	public static String getConvertedQuery(String inputSQLQuery,int currentDBId,int newDBId)
	{
		SQLQuery sqlQueryObjt = processSQl(inputSQLQuery, currentDBId, newDBId);
		ReportLogInfo rptLogInfo = new ReportLogInfo();
		SQLValue sqlVal = QueryGenerator.fillSQLStatementForDbs(sqlQueryObjt, currentDBId, newDBId, rptLogInfo, null);
		
		return sqlVal.sqlValue;
		
	}
	
	public static SQLValue getConvertedQueryForScript(ArrayList<Token> tokens,int currentDBId,int newDBId,ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash)
	{
		SQLQuery sqlQueryObjt = processScriptSQl(tokens, currentDBId, newDBId);
		
		SQLValue sqlVal = new SQLValue();
		if(!(sqlQueryObjt.dmlType.equalsIgnoreCase("ds_Unknwn") || sqlQueryObjt.dmlType.equalsIgnoreCase("COLLECT STATS")))
			sqlVal = QueryGenerator.fillSQLStatementForDbs(sqlQueryObjt, currentDBId, newDBId, rptLogInfo, configHash);
		else
			if(sqlQueryObjt.dmlType.equalsIgnoreCase("COLLECT STATS") && newDBId == ToolTypeConstants.SNOWFLAKE)
				sqlVal.sqlValue = "\n ----[DS_WarningMessage] [INFO] ---<Ignored since SF does auto stats collection>--- "+SQLStatement.generateSQL(tokens) +"\n \n \n ";
			else
				sqlVal.sqlValue = "\n ----[DSErrorMessage] [INFO] ---<Unable to convert the sql>--- "+SQLStatement.generateSQL(tokens) +"\n \n \n ";
		
		return sqlVal;
		
	}
	
	public static SQLQuery SQLParser(ArrayList<Token> tokens,String inputSQLQuery, int currentDBId,int newDBId ) 
	{
		SQLQuery sqlObj = new SQLQuery();
		sqlObj.dmlType = getSQLType(tokens);
		sqlObj.withInParanthesis = checkSQLHasOpenParen(tokens);
		sqlObj.currentDbId = currentDBId;
		sqlObj.newDbId = newDBId;
		if(sqlObj.dmlType != null)
		{
			if(sqlObj.dmlType.equalsIgnoreCase("INSERT") 
					|| sqlObj.dmlType.equalsIgnoreCase("SELECT") || 
					sqlObj.dmlType.equalsIgnoreCase("UPDATE") || sqlObj.dmlType.equalsIgnoreCase("DELETE") 
					|| sqlObj.dmlType.equalsIgnoreCase("CREATE") || sqlObj.dmlType.equalsIgnoreCase("DROP")
					 || sqlObj.dmlType.equalsIgnoreCase("MERGE") ||  sqlObj.dmlType.equalsIgnoreCase("TRUNCATE") ||  
					 sqlObj.dmlType.equalsIgnoreCase("WITH TEMPORARY TABLE") || sqlObj.dmlType.equalsIgnoreCase("REPLACE VIEW") || 
					 sqlObj.dmlType.equalsIgnoreCase("CREATE VIEW") ||  sqlObj.dmlType.equalsIgnoreCase("CREATE OR REPLACE VIEW") || sqlObj.dmlType.equalsIgnoreCase("COLLECT STATS") || sqlObj.dmlType.equalsIgnoreCase("CALL PROC/FUNC")
					 || sqlObj.dmlType.equalsIgnoreCase("GRANT"))
			{
				SQLStatement.getSQLQueryObject(tokens,inputSQLQuery,sqlObj,currentDBId,newDBId);
			}
			
		}
		
		return sqlObj;
	}
	
	private static boolean checkSQLHasOpenParen(List<Token> tokens) 
	{
		if(tokens.get(0).data.equalsIgnoreCase("(") || tokens.get(1).data.equalsIgnoreCase("("))
			return true;
		
		return false;
	}
	
	
	public static final String getSQLType(List<Token> tokensinputSQL)
	{
		String sqlType = null;
		
		if(tokensinputSQL.get(0).data.equalsIgnoreCase("Select"))
			sqlType = "SELECT";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Insert"))
			sqlType = "INSERT";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Update"))
			sqlType = "UPDATE";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Delete"))
			sqlType = "DELETE";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Create"))
		{
		   if(tokensinputSQL.size() > 4)
		   {
			   if(tokensinputSQL.get(0).data.equalsIgnoreCase("Create") && tokensinputSQL.get(2).data.equalsIgnoreCase("REPLACE") &&
					   tokensinputSQL.get(3).data.equalsIgnoreCase("View"))
				    sqlType = "CREATE OR REPLACE VIEW";
			   
			   else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Create") && tokensinputSQL.get(1).data.equalsIgnoreCase("View"))
					sqlType = "CREATE VIEW";
				   
			   else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Create"))
					sqlType = "CREATE";
		   }
		   
		}
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Drop"))
			sqlType = "DROP";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Merge"))
			sqlType = "MERGE";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Truncate"))
			sqlType = "TRUNCATE";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("With"))
			sqlType = "WITH TEMPORARY TABLE";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("Replace") && tokensinputSQL.get(1).data.equalsIgnoreCase("View"))
			sqlType = "REPLACE VIEW";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("call"))
			sqlType = "CALL PROC/FUNC";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("collect"))
			sqlType = "COLLECT STATS";
		
		else if(tokensinputSQL.get(0).data.equalsIgnoreCase("grant"))
			sqlType = "GRANT";
		
		else
			sqlType = "ds_Unknwn";
			
		return sqlType;
		
	}

	public static final String getBufferedReadSQL(String input)
	{
		String sqlCommand = "";
		FileReader fr = null;
		try {
			
			fr = new FileReader(input);
			BufferedReader br = new BufferedReader(fr);
			String strCurrentLine;
			  if(br.ready())
			  {
				while ((strCurrentLine = br.readLine()) != null)
				{
					sqlCommand += strCurrentLine+" ";
				}	
			  }
			  br.close();
			  return sqlCommand;
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static ArrayList<Token> updatedClause(ArrayList<Token> getScriptTkn, String checkVal, int currentDbId) {
		for (int i = 0; i < getScriptTkn.size(); i++) {
			if (getScriptTkn.get(i).data.equalsIgnoreCase(checkVal)) {
				if (getScriptTkn.get(i).data.equalsIgnoreCase("sel")) {
					getScriptTkn.get(i).data = getScriptTkn.get(i).data.replace(getScriptTkn.get(i).data, "SELECT");
				}
				if (getScriptTkn.get(i).data.equalsIgnoreCase("del")) {
					getScriptTkn.get(i).data = getScriptTkn.get(i).data.replace(getScriptTkn.get(i).data, "DELETE");
				}
			}
		}

		return getScriptTkn;

	}
	
	private static void getUpdatedTokens(ArrayList<Token> tokens, int currentDBId) 
	{
		updatedClause(tokens, "sel", currentDBId);
		updatedClause(tokens, "del", currentDBId);
	}




}