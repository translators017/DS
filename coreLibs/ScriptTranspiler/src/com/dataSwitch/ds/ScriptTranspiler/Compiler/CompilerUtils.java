
package com.dataSwitch.ds.ScriptTranspiler.Compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;

import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLGenerator.QueryGenerator;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.Script.StatementObjts.dsProc;
import com.dataSwitch.ds.Script.StatementObjts.dsQuery;

public class CompilerUtils 
{

	public static void getSQLQueryValues(iStatement istatObject, int newDbId, int currentDbId, String outpath,
			String filename, String configJsonPath, Console console, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, String fileType, LinkedList<iStatement> sqlQryList) 
	{
		iBlockStatement iBlockStat = new iBlockStatement();
		
		if(istatObject instanceof iBlockStatement)
			iBlockStat = (iBlockStatement) istatObject;
		
		String SQLValue = "";
		
		LinkedList<SQLValue> sqLValList = null ;//= new LinkedList<SQLValue>();
		LinkedHashMap<iStatement, ArrayList<String>> manualChangesList = new LinkedHashMap<iStatement, ArrayList<String>>();
		
		int totalTransCount = 0;
		int manualChangesCount = 0;
		int totalAttributeCount = 0;
		
		if (sqlQryList == null)
		{
			sqLValList = new LinkedList<SQLValue>();
			if(iBlockStat != null)
			{
				for(Entry<Float, iStatement> entrySet : iBlockStat.iBlockStatements.entrySet())
				{
					iStatement istatVal = entrySet.getValue();
					
					ReportLogInfo rptLogInfo = new ReportLogInfo();
					
					float key = entrySet.getKey();
					
					if(istatVal.type == StatType.SQLQUERY)
					{
						SQLValue sqlQuery = SQLConverter.getConvertedQueryForScript(istatVal.tokenList, currentDbId, newDbId, rptLogInfo, configHash);
						
						SQLValue += sqlQuery.sqlValue;
					
						sqLValList.add(sqlQuery);
						totalTransCount += rptLogInfo.stmtsAdd.size();
						manualChangesCount += rptLogInfo.manualChanges.size();

						totalAttributeCount += rptLogInfo.totalAttributeTransCount.size();
						manualChangesList.put(istatVal, rptLogInfo.manualChanges);
					}
				}
			}
		}
		else
		{
			sqLValList = new LinkedList<SQLValue>();
			for (int i = 0; i < sqlQryList.size(); i++) 
			{
				ReportLogInfo rptLogInfo = new ReportLogInfo();
				dsQuery iQry = new dsQuery();
				if (sqlQryList.get(i).statDetail instanceof dsQuery)
					iQry = (dsQuery)sqlQryList.get(i).statDetail;
				
				SQLValue sqlQryVal = QueryGenerator.fillSQLStatementForDbs(iQry.sqlQuery, currentDbId, newDbId, rptLogInfo, configHash);
				SQLValue += sqlQryVal.sqlValue;
				
				sqLValList.add(sqlQryVal);
				totalTransCount += rptLogInfo.stmtsAdd.size();
				manualChangesCount += rptLogInfo.manualChanges.size();

				totalAttributeCount += rptLogInfo.totalAttributeTransCount.size();
				manualChangesList.put(sqlQryList.get(i), rptLogInfo.manualChanges);
			}
		}
		
		fillReportLogDetails(console, filename, totalTransCount, manualChangesCount, totalAttributeCount, currentDbId, newDbId, sqLValList, manualChangesList, outpath);
		
		if(fileType.equalsIgnoreCase("Query") || fileType.equalsIgnoreCase("DDL") || fileType.equalsIgnoreCase("PLSQL PROC"))
		{
			if (SQLValue.length() > 0) {
				createFileForCompiler(SQLValue, outpath, filename, ".sql");
			}
		}
		else if(fileType.equalsIgnoreCase("Python QL"))
			PythonCompiler.processPythonCompilerForDbs(filename, outpath, sqLValList, newDbId, console);

		
	}
	
	public static void createFileForCompiler(String Script, String sOutputPath, String fileName,
			String scriptType) {

		File file = new File(FilenameUtils.normalize(sOutputPath + "Out_"+ fileName + scriptType));
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (file.exists()) {
			try {
				//FileWriter fw = new FileWriter(file, true);
				Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
				fw.write(Script);
				fw.flush();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private static void fillReportLogDetails(Console console, String filename, int totalTransCount, int manualChangesCount, int totalAttributeCount, int currentDbId, int newDbId, LinkedList<SQLValue> sqLValList, LinkedHashMap<iStatement, ArrayList<String>> manualChangesList, String outpath) 
	{
		String sqlReport = "";
		
		String currentDb = ScriptTranspiler.getDBNamesFromToolTypeConstant(currentDbId);
		String newDb = ScriptTranspiler.getDBNamesFromToolTypeConstant(newDbId);

		sqlReport += "---------------------------- " + filename
				+ " Script - Summary----------------------------------------------";
		sqlReport += "\n Script Name - " + filename;
		sqlReport += "\n CurrentDB - " + currentDb;
		sqlReport += "\n NewDB - " + newDb;

		sqlReport += "\n Number of SQL Queries -" + sqLValList.size();
		sqlReport += "\n Number of Entity Transformations -" + totalTransCount;
		sqlReport += "\n Number of Attribute Transformations -" + totalAttributeCount;

		console.put("Message", "Number of SQL Queries -" + sqLValList.size());
		console.put("Message", "Number of Entity Transformations -" + totalTransCount);
		console.put("Message", "Number of Attribute Transformations -" + totalAttributeCount);

		int totCnt = totalAttributeCount + totalTransCount;
		console.put("Message", "Total Number of Transformations - " + totCnt);

		sqlReport += "\n Total Number of Transformations - " + totCnt;

		int automationPercent = 0;
		if (totCnt > 0)
			automationPercent = ((totCnt - manualChangesCount) * 100) / (totCnt);

		String complexType = "";
		if (totCnt >= 1 && totCnt <= 20)
			complexType = "Simple";
		else if (totCnt > 20 && totCnt <= 40)
			complexType = "Medium";
		else if (totCnt > 40)
			complexType = "Complex";

		console.put("Message", "Complexity Type - " + complexType);
		console.put("Message", "Automation Percentage - " + automationPercent + "%");
		console.put("Message", "Number of Transformation to be changed Manually -" + manualChangesCount);

		sqlReport += "\n Complexity Type - " + complexType;
		sqlReport += "\n Automation Percentage - " + automationPercent + "%";
		sqlReport += "\n Number of Transformation to be changed Manually -" + manualChangesCount;
		sqlReport += "\n  ";
		sqlReport += "\n  ";
		sqlReport += "\n  ";

		if (manualChangesList.size() > 0) {
			sqlReport += "\n  --------------------------- " + filename
					+ " Script - Statement(s) that which Need Manual Update---------------------------";
			sqlReport += "\n  ";
			int count = 1;
			for (Entry<iStatement, ArrayList<String>> mannualHashMap : manualChangesList.entrySet()) {
				float org_No = mannualHashMap.getKey().originalLineNo;
				int org_No1 = (int) org_No;

				if (mannualHashMap.getValue().size() > 0) {
					sqlReport += "\n  #" + count++ + ". Statement's Original Line Number: " + org_No1;

					for (int i = 0; i < mannualHashMap.getValue().size(); i++) {
						String codeSnippet = mannualHashMap.getValue().get(i);
						if (codeSnippet.contains(";")) {
							codeSnippet = codeSnippet.substring(0, codeSnippet.indexOf(";")).trim();
							String code = mannualHashMap.getValue().get(i)
									.substring(mannualHashMap.getValue().get(i).indexOf(";") + 1).trim();

							sqlReport += "\n       " + codeSnippet + " == " + code;
						}
					}

				}
			}
			sqlReport += "\n  ";
			sqlReport += "\n  -------------------------- " + filename
					+ " Script - End of Manual Changes ----------------------------------------";
		}

		console.put("Message", " ");

		console.put("Success", "Successfully generated (DB - " + newDb + ") Script...");
		
		createfileForReportLog(sqlReport, outpath);
		
	}

	private static void createfileForReportLog(String sqlReport, String outpath) 
	{
		File file = new File(FilenameUtils.normalize(outpath + "ReportLog.log"));
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (file.exists()) {
			try {
				FileWriter fw = new FileWriter(file, true);
				fw.write(sqlReport);
				fw.flush();
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void processProcReportLog(int currentDbId, int newDbId, String filename, String outpath,
			Console console, ReportLogInfo rptLogInfo, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount) 
	{
		
		
        int manualChangesCount = 0;
        String sqlReport = ""; 
        
		String currentDb = ScriptTranspiler.getDBNamesFromToolTypeConstant (currentDbId);
 		String newDb = ScriptTranspiler.getDBNamesFromToolTypeConstant (newDbId);
 		
 		sqlReport += "---------------------------- "+ filename +" Script - Summary----------------------------------------------";
 		sqlReport += "\n Script Name - "+filename;
 		sqlReport += "\n CurrentDB - "+currentDb;
 		sqlReport += "\n NewDB - "+newDb;
 		
 		for(Entry<ArrayList<String>, Integer>  manualChangs : manualChangesList.entrySet())
 		{
	        manualChangesCount += manualChangs.getKey().size();//rptLogInfo.manualChanges.size();

 		}
 		int totalEntityCount = 0;
 		int totalAttrCount = 0;
 		for (int i = 0; i < totalTransCount.size(); i++)
 		{
 			totalEntityCount += Integer.parseInt(totalTransCount.get(i));
		}
 		for (int i = 0; i < totalAttributeCount.size(); i++)
 		{
 			totalAttrCount += Integer.parseInt(totalAttributeCount.get(i));
		}
 		
 		sqlReport += "\n Number of Statements -"+rptLogInfo.statementCount;
 		sqlReport += "\n Number of SQL Queries -"+sqLValList.size();
 		sqlReport += "\n Number of Entity Transformations -"+totalEntityCount;
 		sqlReport += "\n Number of Attribute Transformations -"+totalAttrCount;
 		
 		console.put("Message", "Number of Statements -"+rptLogInfo.statementCount);
 		console.put("Message", "Number of SQL Queries -"+sqLValList.size());
 		console.put("Message", "Number of Entity Transformations -"+totalEntityCount);
 		console.put("Message", "Number of Attribute Transformations -"+totalAttrCount);
 		
 		int totCnt = totalAttrCount + totalEntityCount + rptLogInfo.statementCount;
 		console.put("Message", "Total Number of Transformations - "+totCnt);
 		
 		sqlReport += "\n Total Number of Transformations - "+totCnt;
 		
 		int automationPercent = 0;
 		if (totCnt > 0)
 			automationPercent = ((totCnt - manualChangesCount)*100)/(totCnt);
 		
 		String complexType = "";
		
		if (totCnt >= 1 && totCnt <= 20)
			complexType = "Simple";
		else if (totCnt > 20 && totCnt <= 40)
			complexType = "Medium";
		else if (totCnt > 40)
			complexType = "Complex";
		
 		
 		console.put("Message", "Complexity Type - "+complexType);
 		console.put("Message", "Automation Percentage - "+ automationPercent+"%");
 		console.put("Message", "Number of Transformation to be changed Manually -"+manualChangesCount);
 		
 		
 		
 		sqlReport += "\n Complexity Type - "+complexType;
 		sqlReport += "\n Automation Percentage - "+ automationPercent+"%";
 		sqlReport += "\n Number of Transformation to be changed Manually -"+manualChangesCount;
 		sqlReport += "\n  ";
 		sqlReport += "\n  ";
 		sqlReport += "\n  ";
 		
		if(manualChangesList.size() > 0)
		{
			sqlReport += "\n  --------------------------- "+ filename +" Script - Statement(s) that which Need Manual Update---------------------------";
	    	sqlReport += "\n  ";
			int count = 1;
			for(Entry<ArrayList<String>, Integer> mannualHashMap : manualChangesList.entrySet())
			{ 
	            int org_No = mannualHashMap.getValue();
				
				
			    if(mannualHashMap.getKey().size() > 0)
			    {
			    	sqlReport += "\n  #"+ count++ +". Statement's Original Line Number: "+org_No;
			    	
				    for(int  i = 0 ; i < mannualHashMap.getKey().size();i++)
				    {
				    	String codeSnippet = mannualHashMap.getKey().get(i);
				    		
				    	if (codeSnippet.contains(";"))
				    		codeSnippet = codeSnippet.substring(codeSnippet.indexOf(";")+1, codeSnippet.length()).trim();
//				    	codeSnippet = mannualHashMap.getKey().get(i).substring(0, mannualHashMap.getKey().get(i).indexOf(";")).trim();
				    	
				    	sqlReport += "\n       "+ codeSnippet;
				    }
				   
			    }
			}
			 sqlReport += "\n  ";
			    sqlReport += "\n  -------------------------- "+ filename +" Script - End of Manual Changes ----------------------------------------";
		}
		
		console.put("Message", " ");		
		
		
		console.put("Success", "Successfully generated (DB - "+newDb+") Script...");
		
		File file = new File (outpath + "ReportLog.log"); 
    	try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	if(file.exists())
    	{
	    	 try {
	 			FileWriter fw = new FileWriter(file,true);
	 			fw.write(sqlReport);
	 			fw.flush();
	 			fw.close();
	 		} catch (IOException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}
    	}			
	}
	

}