
package com.dataSwitch.ds.ScriptTranspiler.Compiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;

public class PythonCompiler 
{

	private static void pythonAzureSQLWrapper(LinkedList<SQLValue> sqLValList, String filename, String outpath, Console console) 
	{
		String scriptType = ".py";
		String tmpData = "";
		tmpData +="\n import pyodbc "
				+ "\n server = '<server>.database.windows.net'"
				+ "\n database = '<database>'"
				+ "\n username = '<username>' "
				+ "\n password = '<password>'"
				+ "\n driver= '{ODBC Driver 17 for SQL Server}'"
				+ "\n cnxn = pyodbc.connect('DRIVER='+driver+';SERVER='+server+';PORT=1433;DATABASE='+database+';UID='+username+';PWD='+ password)"
				+ "\n cursor = cnxn.cursor()";
       
		for(int i = 0 ;i < sqLValList.size() ; i++)
		{
			String sqlStmts = sqLValList.get(i).sqlValue;
			tmpData += "\n \n cursor.execute("+'"'+ sqlStmts.replace("\n", "  ") +'"'+")";
			
			// need Clarifiaction for how to handle this cursor
			/*
				row = cursor.fetchone()
				while row:
				    print (str(row[0]) + " " + str(row[1]))
				    row = cursor.fetchone() */
		}
		
		CompilerUtils.createFileForCompiler(tmpData, outpath, filename, scriptType);	
	}

	private static void pythonRedShiftWrapper(LinkedList<SQLValue> sqLValList, String filename, String outpath, Console console) 
	{
		String scriptType = ".py";
		String tmpData = "";
		
		tmpData +=" from sqlalchemy import create_engine "
				+ "\n import pandas as padas"
				+ "\n engine = create_engine('postgresql://test:test@hredshift_host:5439/mydatabase') ";
        int count = 1;
       
		for(int i = 0 ;i < sqLValList.size() ; i++)
		{
			int cnt = count++;
			String sqlStmts = sqLValList.get(i).sqlValue;
			tmpData += "\n \n data_frame = padas.read_sql_query('"+ sqlStmts.replace("\n", "  ") +"', engine)";
		}
		
		CompilerUtils.createFileForCompiler(tmpData, outpath, filename, scriptType);		
	}

	private static void pythonBigQueryWrapper(LinkedList<SQLValue> sqLValList, String filename, String outpath, Console console) 
	{
		String scriptType = ".py";
		String tmpData = "";
		tmpData +=" from google.cloud import bigquery "
				+ "\n import os"
				+ "\n import json "
				+ "\n import atexit "
				+ "\n json_key=os.environ['JSON_KEY'] "
				+ "\n client = bigquery.Client.from_service_account_json(json_key) "
				+ "\n ACTIVITYCOUNT=0 "
				+ "\n ERRORCODE=0 \n ";
        int count = 1;
       
		for(int i = 0 ;i < sqLValList.size() ; i++)
		{
			String sqlStmts = sqLValList.get(i).sqlValue;
			tmpData +="\n \n query='''"+ "\n           " +sqlStmts.replace("\n", "\n          ") +"'''"
					+ "\n job_config=bigquery.QueryJobConfig(); "
					+ "\n rows=client.query (query, job_config=job_config) "
					+ "\n while not rows.done(): "
					+ "\n pass "
					+ "\n ACTIVITYCOUNT=0 "
					+ "\n ERRORCODE=0  ";
		}
		tmpData += "\n $$";
		tmpData += "\n ;";
		
		CompilerUtils.createFileForCompiler(tmpData, outpath, filename, scriptType);
		
		
	}
	
	private static void pythonSnowflakeWrapper(LinkedList<SQLValue> sqLValList, String filename, String outpath, Console console) 
	{
		String scriptType = ".py";
		String tmpData = "";
		tmpData +=" import os "
				+ "\n import sys"
				+ "\n import snowflake.connector";
		tmpData +="\n  \n  \n";
		tmpData += "#==============================================================================================";
		
		tmpData += "\n def "+filename+"():";
		
		tmpData += "\n try:";
		
		tmpData +="\n  #BEGINNING PROC SECTION  "
				+ "\n  #Establishing Snowflake Connections "
				+ "\n  UserName=['UserName'] "
				+ "\n  UserPass=['UserPassword'] "
				+ "\n  AccName=['AccountName']"
				+ "\n  ctx = snowflake.connector.connect(user=UserName,password=UserPass,account=AccName,)"
				+ "\n  print('You Have Successfully Connected to SnowFlake')"

		 		+"\n  Warehs= ['Warehouse']"
		+"\n  whouse= (\"USE warehouse \" + Warehs + \"\")"
		+"\n  DB=[\'Database\']"
		+"\n  Schm=\'test\'"
		+"\n  dbschema= (\"USE \" + DB + \".\" + Schm + \"\")"

		+"\n  ctx.cursor().execute(whouse)"
		+"\n  ctx.cursor().execute(dbschema)"

		+"\n  print(\'Please Wait While We Connect You to Warehouse\')";
		
		String queryDtls = "";
	 	int count = 1;
		for(int i = 0 ;i < sqLValList.size() ; i++)
		{
			int cnt = count++;
			String sqlStmts = sqLValList.get(i).sqlValue;
			
			queryDtls += "\n  try:"
			
			
				+"\n    #Executing Step #"+cnt
				+"\n    print(\'Executing Step "+ cnt +"\')"
				+"\n    db_cursor"+cnt+ "=ctx.cursor()"
				
					
			
				+"\n    query='"+sqlStmts.replace("\n", "  ") +"'"
				+"\n    db_cursor"+cnt+".execute(query)"	
				+"\n    affected_rowcount=db_cursor"+cnt+".rowcount"
				+"\n    db_cursor"+cnt+".close()"
			    +"\n \n "
				+"\n  except Exception as err:"
			    +"\n    print(\'Error occurred - \',err)"
				+"\n    #if  SQLCODE != -942  :"
			    +"\n    #  RAISE"
				
			    
			    +"\n \n"
			    +"   ctx.close()"
			    +" sys.exit("+cnt+")";
		
		}
		
		String endStmt = "\n except Exception as err: "
				+ "\n  print(\'Error occurred - \',err) "
				+ "\n  ctx.close()";
			
		String pythonStmt = tmpData + " " + queryDtls + " " + endStmt;
		
		CompilerUtils.createFileForCompiler(pythonStmt, outpath, filename, scriptType);
		
	}

	public static void processPythonCompilerForDbs(String filename, String outpath, LinkedList<SQLValue> sqLValList, int newDbId, Console console)
	{
		if(newDbId == ToolTypeConstants.AZURESQL)
			pythonAzureSQLWrapper(sqLValList, filename, outpath, console);
		
		else if(newDbId == ToolTypeConstants.SNOWFLAKE)
			pythonSnowflakeWrapper(sqLValList, filename, outpath, console);
		
		else if(newDbId == ToolTypeConstants.REDSHIFT)
			pythonRedShiftWrapper(sqLValList, filename, outpath, console);
		
		else if(newDbId == ToolTypeConstants.BIGQUERY)
			pythonBigQueryWrapper(sqLValList, filename, outpath, console);
			
	}

}