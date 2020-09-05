
package com.dataSwitch.ds.sql.SQLGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLObjeds.DDLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.ExternalTable;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoin;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoinCond;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.SQLUnion;
import com.dataSwitch.ds.sql.SQLObjeds.UpdateElement;
import com.dataSwitch.ds.sql.SQLParser.SQLUtils;

public class QueryGenerator {

	public static SQLValue fillSQLStatementForDbs(SQLObject sqlObjt, int currentDbId, int newDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash) 
	{
		ArrayList<String> uniqueAliasList = new ArrayList<String>();
		
		LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts = new LinkedHashMap<String, LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> prefixConfigObjts = null;
		LinkedHashMap<String, String> encloseConfigObjts = null;
		
		if(configHash != null)
		{
			if(configHash.get("PrefixSchemaName") != null)
			{
				 prefixConfigObjts = configHash.get("PrefixSchemaName");
			}
			if(configHash.get("EncloseNameWithQuotes") != null)
			{
				encloseConfigObjts = configHash.get("EncloseNameWithQuotes");
			}
			if(configHash.size() > 0)
			{
				for(Entry<String, LinkedHashMap<String, String>> configEntry : configHash.entrySet())
				{
					String keyValue = configEntry.getKey();
					LinkedHashMap<String ,String> configValue = configEntry.getValue();
					if(!(keyValue.equalsIgnoreCase("PrefixSchemaName") || keyValue.equalsIgnoreCase("EncloseNameWithQuotes")))
					{  
						replaceSchemaConfigObjts.put(keyValue, configValue) ;
					}
					
				}
				
			}
		}
		
		SQLValue sqlVal = new SQLValue();
		SQLQuery sqlobjts = (SQLQuery) sqlObjt;
		List<String> tblMap = new ArrayList<String>();
		if(sqlobjts.dmlType != null)
		{
				if(sqlobjts.dmlType.equalsIgnoreCase("Insert"))
				{
					sqlVal.sqlValue = fillSqlStmtsForInsrtStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("Select"))
				{
					sqlVal.sqlValue = fillSelectStmts(sqlobjts,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList);   
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("Update"))
				{
					sqlVal.sqlValue = fillSqlStmtsForUpdStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId, encloseConfigObjts,prefixConfigObjts , replaceSchemaConfigObjts, uniqueAliasList);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("Delete") || sqlobjts.dmlType.equalsIgnoreCase("Drop")
						|| sqlobjts.dmlType.equalsIgnoreCase("Truncate"))
				{
					sqlVal.sqlValue = fillSqlStmtsForDltStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("Create"))
				{
					//sqlVal.sqlValue = fillCreateDDLStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
					sqlVal.sqlValue =  DDLQueryGenerator.fillCreateDDLStmts(sqlobjts, sqlVal, rptLogInfo, tblMap, newDbId, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("Merge"))
				{
					sqlVal.sqlValue = fillMergeStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId, encloseConfigObjts ,prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("WITH TEMPORARY TABLE"))
				{
					sqlVal.sqlValue = fillWithTempStatements(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("REPLACE VIEW") || sqlobjts.dmlType.equalsIgnoreCase("CREATE VIEW") || 
						sqlobjts.dmlType.equalsIgnoreCase("CREATE OR REPLACE VIEW"))
				{
					sqlVal.sqlValue = fillCreateOrReplaceViewStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("CALL PROC/FUNC"))
				{
					sqlVal.sqlValue = fillCallProcStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts);
				}
				else if(sqlobjts.dmlType.equalsIgnoreCase("GRANT"))
				{
					sqlVal.sqlValue = fillGrantStmts(sqlobjts,sqlVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts);
				}
				
		
			sqlVal.sqlValue += "\n ;"
					+ "\n          "
					+ "\n          "
					+ "\n          "
					+"\n";
		}
		
		rptLogInfo.sqlTotalTransCount = tblMap.size();
		rptLogInfo.stmtsAdd = tblMap;
		
		return sqlVal;
	}

	private static String fillGrantStmts(SQLQuery sqlobjts, SQLValue sqlVal, ReportLogInfo rptLogInfo,
			List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts)
	{
		String sqlValue ="GRANT";
		
		if (sqlobjts.colsList.size() > 0)
		{
			SQLElement sqlElem = new SQLElement();
			if (sqlobjts.colsList.get(0) instanceof SQLElement)
				sqlElem = (SQLElement) sqlobjts.colsList.get(0);
			sqlValue += sqlElem.name;
		}
		if (sqlobjts.tableList.size() > 0)
		{
			SQLElement sqlElem = new SQLElement();
			if (sqlobjts.tableList.get(0) instanceof SQLElement)
				sqlElem = (SQLElement) sqlobjts.tableList.get(0);
			sqlValue += " TO " + sqlElem.name;
		}
		return sqlValue;
	}

	private static String fillCallProcStmts(SQLQuery sqlobjts, SQLValue sqlVal, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts,
			LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts) 
	{
		String sqlValue ="";
		if (sqlobjts.callProcOrFunc != null)
		{
			SQLExpr sqlExpr = new SQLExpr();

			if (sqlobjts.callProcOrFunc instanceof SQLExpr)
				sqlExpr = (SQLExpr) sqlobjts.callProcOrFunc;
			
			if (sqlExpr.value != null)
			{
				sqlValue += "CALL ";
						
				if (sqlExpr.value.contains("("))
					sqlValue += sqlExpr.value.substring(0, sqlExpr.value.indexOf("("));
				else
					sqlValue += sqlValue += sqlExpr.value.substring(0, sqlExpr.value.indexOf(" "));
			}
			if (sqlExpr.args.size() > 0)
			{
				for (int i = 0; i < sqlExpr.args.size(); i++) 
				{
					SQLExpr sqlExprArg = new SQLExpr();
					if (sqlExpr.args.get(i) instanceof SQLExpr)
						sqlExprArg = (SQLExpr) sqlExpr.args.get(i);
					if (sqlExprArg.value != null)
					{
						if (i == 0)
							sqlValue += "\n( ";
						
						if (sqlExprArg.value != null && i != (sqlExpr.args.size()-1))
							sqlValue += "  "+sqlExprArg.value +",\n";
						
						if (i == (sqlExpr.args.size()-1))
							sqlValue += "  "+sqlExprArg.value + "\n) ";
					}
				}
			}
		}
		return sqlValue;
	}

	private static String fillCreateOrReplaceViewStmts(SQLQuery sqlQuery, SQLValue sqlVal, ReportLogInfo rptLogInfo,
			List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		String sqlValue ="";
		if(newDbId == ToolTypeConstants.BIGQUERY || newDbId == ToolTypeConstants.REDSHIFT)
		{
			sqlValue += "CREATE OR REPLACE VIEW ";
		}
		else
		{
			if(sqlQuery.dmlType.equalsIgnoreCase("REPLACE VIEW"))
				sqlValue += "REPLACE VIEW ";
			
			else if(sqlQuery.dmlType.equalsIgnoreCase("CREATE VIEW"))
				sqlValue += "CREATE VIEW ";
			
			else if(sqlQuery.dmlType.equalsIgnoreCase("CREATE OR REPLACE VIEW"))
				sqlValue += "CREATE OR REPLACE VIEW ";
		}
	    	
	    String tableVal = "";
	    if(sqlQuery.tableList.size() > 0)
	    {
			sqlValue += getTableSQLVal(sqlQuery,tableVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts,false, true, false,uniqueAliasList,null);
	    }
	    
	    if(newDbId != ToolTypeConstants.BIGQUERY)
	    {
	    	String colVal = "";
		    if(sqlQuery.colsList.size() > 0)
		    {
		    	sqlValue += fillColsDtls(sqlQuery,colVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts,false, true);
		    }
	    }
	    
	    if(sqlQuery.externalTables.size() > 0)
	    {
	    	sqlValue += "\n AS ";
			for(int i = 0 ; i < sqlQuery.externalTables.size();i++)
			{
				ExternalTable exteTbl = new ExternalTable();
				if (sqlQuery.externalTables.get(i) instanceof ExternalTable)
					exteTbl = (ExternalTable) sqlQuery.externalTables.get(i);
				SQLQuery extrnlQuery = new SQLQuery();
				if (exteTbl.dmlQuery instanceof SQLQuery)
					extrnlQuery = (SQLQuery) exteTbl.dmlQuery;
				
				if(sqlQuery.colsList.size() > 0 && newDbId == ToolTypeConstants.BIGQUERY)
                {
                   if  (extrnlQuery.colsList.size() == sqlQuery.colsList.size())
                   {
                	   sqlValue += fillSelectStmts(extrnlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts, sqlQuery.colsList, uniqueAliasList);
                   }
                   else
                	   sqlValue += fillSelectStmts(extrnlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList);
                }
				else
					sqlValue += fillSelectStmts(extrnlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList);
			}
	    }
		return sqlValue;
	}

	private static String fillColsDtls(SQLQuery sqlQuery, String colsVal, ReportLogInfo rptLogInfo, List<String> tblMap,
			int newDbId, LinkedHashMap<String, String> encloseConfigObjts,
			LinkedHashMap<String, String> prefixConfigObjts,
			LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, boolean b, boolean c) 
	{
		colsVal += "\n  (";
		for(int i = 0 ; i < sqlQuery.colsList.size() ; i++)
		{
			SQLElement sqlElem = new SQLElement();
			
			if(sqlQuery.colsList.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) sqlQuery.colsList.get(i);
			
			if(sqlElem.name != null)
			{
				if(i==(sqlQuery.colsList.size()-1))
					colsVal += "\n     "+ sqlElem.name;
				else
					colsVal += "\n     "+ sqlElem.name+",";
			}		
		}
		
		 colsVal += "\n  )";
		
		return colsVal;
	}

	private static String fillMergeStmts(SQLQuery sqlQuery, SQLValue sqlVal, ReportLogInfo rptLogInfo,
			List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		
		String sqlValue ="";
		
		if(newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT)
		{
			sqlValue = getUpdatedMergeStmts(sqlValue, sqlQuery, rptLogInfo, tblMap, newDbId, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
		}
		else
		{
		    sqlValue += "MERGE INTO";
		    
		    String tableVal = "";
		    if(sqlQuery.tableList.size() > 0)
		    {
				   sqlValue += getTableSQLVal(sqlQuery,tableVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts,replaceSchemaConfigObjts,false, false, true, uniqueAliasList, null);
		    }
				
		    sqlValue += "\n USING ";
		    
			if(sqlQuery.joinCond != null)
			{
				SQLJoin join = new SQLJoin();
				
				if (sqlQuery.joinCond instanceof SQLJoin)
					join = (SQLJoin) sqlQuery.joinCond;
				String joinconds = "";
				sqlValue += fillJoinCondDtls(sqlQuery, join,joinconds,rptLogInfo,tblMap,"","",-1, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
			}
			
			if(sqlQuery.updateCols.size() > 0)
			{
				sqlValue += "\nWHEN MATCHED THEN UPDATE ";
				sqlValue += "\nSET";
				
				for(int i = 0 ; i < sqlQuery.updateCols.size();i++)
				{
					UpdateElement updElem = new UpdateElement();
					if (sqlQuery.updateCols.get(i) instanceof UpdateElement)
						updElem = (UpdateElement) sqlQuery.updateCols.get(i);
					
					SQLElement sqlElem = new SQLElement();
					if(updElem.value instanceof SQLElement)
						sqlElem = (SQLElement) updElem.value;
					if(sqlQuery.updateCols.size() > 1)
					{
						if(i==(sqlQuery.updateCols.size()-1))
							 sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name;
						else
						     sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name +",";
					}
					else
						 sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name;
				   
					if(updElem.updColName.contains("ds") || sqlElem.name.contains("ds"))
					{
						rptLogInfo.manualChanges.add("SET ; " + updElem.updColName + " = " + sqlElem.name);
					}
	
					boolean checkExp = getExpressionFunc(sqlElem.name);
					if(checkExp)
					{
						rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
					}
				}
				
			}
			
			if(sqlQuery.colsList.size() > 0)
			{
				sqlValue += "\nWHEN NOT MATCHED THEN INSERT ";
			}
			if(sqlQuery.externalTables.size() > 0)
			{
			    sqlValue += "\n( ";
				for(int i = 0 ; i < sqlQuery.externalTables.size();i++)
				{
					SQLElement updElem = new SQLElement();
					if (sqlQuery.externalTables.get(i) instanceof SQLElement)
						updElem = (SQLElement) sqlQuery.externalTables.get(i);
					if(sqlQuery.externalTables.size() > 1)
					{
						if(i==(sqlQuery.externalTables.size()-1))
							 sqlValue += "\n     "+ updElem.name;
						else
						     sqlValue += "\n     "+ updElem.name+",";
					}
					else
						sqlValue += "\n     "+ updElem.name;
					
				}
				sqlValue += "\n) ";
			}
			if(sqlQuery.colsList.size() > 0)
			{
				sqlValue += "\n VALUES (";
				for (int i = 0; i < sqlQuery.colsList.size(); i++) 
				{
					SQLElement sqlElem = new SQLElement();
					SQLQuery sqlQuery1 = new SQLQuery();
					
					if (sqlQuery.colsList.get(i) instanceof SQLQuery)
						sqlQuery1 = (SQLQuery) sqlQuery.colsList.get(i);
				
					else if(sqlQuery.colsList.get(i) instanceof SQLElement)
						sqlElem = (SQLElement) sqlQuery.colsList.get(i);
					
					if(sqlQuery1.dmlType == null)
					{
						if(sqlElem.aliasName == null)
						{
							if(sqlQuery.colsList.size() > 1)
							{
								if(i==(sqlQuery.colsList.size()-1))
								    sqlValue += "\n       "+ sqlElem.name;
								else
									sqlValue += "\n       "+ sqlElem.name + ",";
							}
							else
								sqlValue += "\n       "+ sqlElem.name;
						}
						else
						{
							if(sqlQuery.colsList.size() > 1)
							{
								if(i==(sqlQuery.colsList.size()-1))
							       sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName;
								else
								   sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName+ ",";
							}
							else
								sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName;
						}
						
						boolean checkExp = getExpressionFunc(sqlElem.name);
						if(checkExp)
						{
							rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
						}
						
						if(sqlElem.name.contains("ds"))
						{
							rptLogInfo.manualChanges.add("SELECT ; " + sqlElem.name);
						}
					}
					  
				}
				sqlValue += "\n )";
			}
		}
		
		return sqlValue;
	}
	
	private static String getUpdatedMergeStmts(String sqlValue, SQLQuery sqlQuery, ReportLogInfo rptLogInfo,
			List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts,LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		
		if(sqlQuery.updateCols.size() > 0)
		{
			sqlValue += "\nUPDATE";
			
			String tableVal_upd = "";
			
			ArrayList<String> MergeTblNameList = new ArrayList<String>();
			if(sqlQuery.tableList.size() > 0)
			{
				sqlValue += getTableSQLVal(sqlQuery,tableVal_upd,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts,replaceSchemaConfigObjts, false, false, true,uniqueAliasList, MergeTblNameList);
			}
			
			sqlValue += "\nSET";
			
			String aliasName = "";
			String tblName = "";
			
			if(MergeTblNameList.size() > 0)
			{
				String tblNames = MergeTblNameList.get(0);
				aliasName = tblNames.substring(0, tblNames.indexOf(";"));
				tblName = tblNames.substring(tblNames.indexOf(";")+1, tblNames.length());
			}
		
			for(int i = 0 ; i < sqlQuery.updateCols.size();i++)
			{
				UpdateElement updElem = new UpdateElement();
				if (sqlQuery.updateCols.get(i) instanceof UpdateElement)
					updElem = (UpdateElement) sqlQuery.updateCols.get(i);
				
				SQLElement sqlElem = new SQLElement();
				if(updElem.value instanceof SQLElement)
					sqlElem = (SQLElement) updElem.value;
				
				if(aliasName.length() > 0)
				{
					if(updElem.updColName.contains(aliasName) && updElem.updColName.contains("."))
					{
						String updTblColName = updElem.updColName.substring(updElem.updColName.indexOf(".")+1, updElem.updColName.length());
						updElem.updColName = tblName+"."+updTblColName;
					}
				}
				if(sqlQuery.updateCols.size() > 1)
				{
					if(i==(sqlQuery.updateCols.size()-1))
						 sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name;
					else
					     sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name +",";
				}
				else
					 sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name;
			   
				if(updElem.updColName.contains("ds") || sqlElem.name.contains("ds"))
				{
					rptLogInfo.manualChanges.add("SET ; " + updElem.updColName + " = " + sqlElem.name);
				}

				boolean checkExp = getExpressionFunc(sqlElem.name);
				if(checkExp)
				{
					rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
				}
			}
			
			if(sqlQuery.joinCond != null)
			 {
			    	sqlValue += "\nFROM";
					SQLJoin join = new SQLJoin();
					
					if (sqlQuery.joinCond instanceof SQLJoin)
						join = (SQLJoin) sqlQuery.joinCond;
					String joinconds = "";
					sqlValue += fillJoinCondDtls(sqlQuery, join,joinconds,rptLogInfo,tblMap,aliasName,"MERGE ; UPDATE;" + tblName,newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
			}
			
			sqlValue +="\n ;";
			
		}
		
		
		
		if(sqlQuery.colsList.size() > 0)
		{
			sqlValue += "\n \n \nINSERT INTO ";
		    
		    String tableVal = "";
		    String tgtTableName = "";
		    ArrayList<String> MergeTblNameList = new ArrayList<String>();
		    
		    if(sqlQuery.tableList.size() > 0)
		    {
		    	tgtTableName = getTableSQLVal(sqlQuery,tableVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts,replaceSchemaConfigObjts,false, false, true,uniqueAliasList, MergeTblNameList);
		    	sqlValue += tgtTableName ;   
		    }
		    
		    if(sqlQuery.externalTables.size() > 0)
			{
				sqlValue += "\n( ";
				for(int i = 0 ; i < sqlQuery.externalTables.size();i++)
				{
					SQLElement updElem = new SQLElement();
					if (sqlQuery.externalTables.get(i) instanceof SQLElement)
						updElem = (SQLElement) sqlQuery.externalTables.get(i);
					if(sqlQuery.externalTables.size() > 1)
					{
						if(i==(sqlQuery.externalTables.size()-1))
							 sqlValue += "\n     "+ updElem.name;
						else
						     sqlValue += "\n     "+ updElem.name+",";
					}
					else
						sqlValue += "\n     "+ updElem.name;
					
				}
				
				sqlValue += "\n) ";
			}
		    
			sqlValue += "\n SELECT";
			for (int i = 0; i < sqlQuery.colsList.size(); i++) 
			{
				SQLElement sqlElem = new SQLElement();
				SQLQuery sqlQuery1 = new SQLQuery();
				
				if (sqlQuery.colsList.get(i) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) sqlQuery.colsList.get(i);
			
				else if(sqlQuery.colsList.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) sqlQuery.colsList.get(i);
				
				if(sqlQuery1.dmlType == null)
				{
					if(sqlElem.aliasName == null)
					{
						if(sqlQuery.colsList.size() > 1)
						{
							if(i==(sqlQuery.colsList.size()-1))
							    sqlValue += "\n       "+ sqlElem.name;
							else
								sqlValue += "\n       "+ sqlElem.name + ",";
						}
						else
							 sqlValue += "\n       "+ sqlElem.name;
					}
					else
					{
						if(sqlQuery.colsList.size() > 1)
						{
							if(i==(sqlQuery.colsList.size()-1))
						       sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName;
							else
							   sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName+ ",";
						}
						else
							sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName;
					}
					
					boolean checkExp = getExpressionFunc(sqlElem.name);
					if(checkExp)
					{
						rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
					}
					
					if(sqlElem.name.contains("ds"))
					{
						rptLogInfo.manualChanges.add("SELECT ; " + sqlElem.name);
					}
				}
				  
			}
			
			 if(sqlQuery.joinCond != null)
			 {
			    	sqlValue += "\nFROM";
					SQLJoin join = new SQLJoin();
					
					if (sqlQuery.joinCond instanceof SQLJoin)
						join = (SQLJoin) sqlQuery.joinCond;
					String joinconds = "";
					String condVal = fillJoinCondDtls(sqlQuery, join,joinconds,rptLogInfo,tblMap,"","MERGE ; INSERT",newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
					sqlValue += condVal;
					
					sqlValue += "\nWHERE ";
					String condValForMerge = getCondValsForMergeStmt(sqlQuery, join,joinconds,rptLogInfo,tblMap,tgtTableName,"MERGE ; INSERT",newDbId, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
					sqlValue += condValForMerge;
					
			}
		}
	    
	    
	    
	 
		return sqlValue;
		    
		
	}

	
	private static String getCondValsForMergeStmt(SQLQuery sqlQuery, SQLJoin join, String joinconds,
			ReportLogInfo rptLogInfo, List<String> tblMap, String updateTblName, String updateAliasName, int newDbId,
			LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts,LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		String sqlValue = "";
		for (int i = 0; i < join.joinCond.size(); i++) 
		{
			
			SQLJoinCond sqlElem = new SQLJoinCond();
			
			SQLQuery sqlQuery1 = new SQLQuery();
			SQLExpr sqlExpr = new SQLExpr();
			
			if (join.joinCond.get(i) instanceof SQLQuery)
				sqlQuery1 = (SQLQuery) join.joinCond.get(i);
			else if(join.joinCond.get(i) instanceof SQLJoinCond)
				sqlElem = (SQLJoinCond) join.joinCond.get(i);
			else if(join.joinCond.get(i) instanceof SQLExpr)
				sqlExpr = (SQLExpr) join.joinCond.get(i);
			
			if(sqlQuery1.dmlType == null)
			{
				
		    		if(sqlExpr.value != null)
		    		{
		    			SQLQuery sqlQueryExpr = new SQLQuery();
						if(sqlExpr.args.size() > 0)
							if(sqlExpr.args.get(0) instanceof SQLQuery)
								sqlQueryExpr = (SQLQuery) sqlExpr.args.get(0);
						
						 String condVals = getUpdSQLExprVal("",sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap ,"","",newDbId,null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
						
						 String condVal = "";
						 if(condVals.contains("="))
						 {
							 String condValTbl = condVals.substring(0, condVals.indexOf("="));
							 String condTbl =  condVals.substring(condVals.indexOf("=")+1 , condVals.length());
							 
							 ArrayList<Token> checkTknList = FunctionTypeConv.getTokens(updateTblName);
							 String tblName = "";
							 String tblAliasName = "";
							 if(checkTknList.size() > 0)
							 {
								 tblName = checkTknList.get(0).data;
								 if(checkTknList.size() > 1)
									 tblAliasName = checkTknList.get(1).data;
							 }
							 if(condValTbl.contains(tblName) || condValTbl.contains(tblAliasName))
								 condVal = condValTbl;
							 
							 if(condTbl.contains(tblName) || condTbl.contains(tblAliasName))
								 condVal = condTbl;
						 }
						 if(condVal.equalsIgnoreCase(""))
						 {
							 condVal = condVals;
						 }
						 
						if(join.joinCond.size() > 1)
						{
							if(i > 0) 
							{
								 String sqlExprTyp  = sqlExpr.type.toUpperCase();
							
								if(sqlExpr.args.size() == 1)
								{
									sqlValue += "\n IS NULL " +" "+sqlExprTyp + " " +condVal;
								}
								else
								{
									sqlValue +="\n "+sqlExprTyp + " " +condVal+ " IS NULL " ;
								}
							}
							else
							{
								sqlValue +="\n "+ condVal + " IS NULL " ;
							}
						}
						else
						{
						    sqlValue +="\n "+ condVal+ " IS NULL ";
						}
		    		}
		    	
			}
			else
			{
				if(join.joinCond.size() > 1)	
				{
					if(i==(join.joinCond.size()-1))
					{
						if(sqlQuery1.aliasName != null)
				            sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
						else
							sqlValue +="( "+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
					}
					else
						if(sqlQuery1.aliasName != null)
						    sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName+ " AND ";
						else
							sqlValue += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" "+ " AND ";
				}
				else
				{
					
					if(sqlQuery1.aliasName != null)
					   sqlValue += "("+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +sqlQuery1.aliasName;
					else 
					   sqlValue += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts,replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
				}
			}
		}
		
		return sqlValue;
	}

	public static String fillWithTempStatements(SQLQuery sqlobjts, SQLValue sqlVal, ReportLogInfo rptLogInfo,
			List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		
		String sqlValue = "";
		if(sqlobjts.externalTables.size() > 0)
		{
			sqlValue += " WITH ";

			for(int i = 0; i < sqlobjts.externalTables.size(); i++)
			{
				ExternalTable extrTable = new ExternalTable();
				if(sqlobjts.externalTables.get(i) instanceof ExternalTable)
					extrTable = (ExternalTable) sqlobjts.externalTables.get(i);
				
				SQLElement sqlElem = new SQLElement();
				SQLQuery sqlQuery = new SQLQuery();
				if (extrTable.dmlQuery instanceof SQLQuery)
					sqlQuery = (SQLQuery) extrTable.dmlQuery;
			
				else if(extrTable.dmlQuery instanceof SQLElement)
					sqlElem = (SQLElement) extrTable.dmlQuery;
				
				if(extrTable.loopType != null)
				{
					if (!(extrTable.loopType.equalsIgnoreCase("recursive") && newDbId == ToolTypeConstants.REDSHIFT))
						sqlValue += extrTable.loopType +" ";
						
				}
				
				String tableName = extrTable.tableAliasName;
						
				if (extrTable.colsList.size() > 0)
				{
					SQLElement sqlElemCols;
					String tempCol = "";
					for (int j = 0; j < extrTable.colsList.size(); j++) 
					{
						 sqlElemCols = new SQLElement();
						if (extrTable.colsList.get(j) instanceof SQLElement)
							 sqlElemCols = (SQLElement)extrTable.colsList.get(j);
						
						if (j == 0)
							tempCol += "\n( ";
						
						if (sqlElemCols.name != null && j != (extrTable.colsList.size()-1))
							tempCol += "  "+sqlElemCols.name +",\n";
						
						if (j == (extrTable.colsList.size()-1))
							tempCol +="  "+sqlElemCols.name + "\n) ";
					}
					if (tempCol.length() > 0)
						tableName += tempCol;
				}
					
				if(sqlQuery.dmlType != null)
				{
					if( sqlobjts.externalTables.size() > 1)
					{
						if(i==(sqlobjts.externalTables.size()-1))
							sqlValue += "\n "+tableName + " as "+ "(" + fillSelectStmts(sqlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
						else
							sqlValue += "\n "+tableName + " as "+ "(" + fillSelectStmts(sqlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")"+ ",";
					}
					else
						sqlValue += "\n "+ tableName + " as "+ "(" + fillSelectStmts(sqlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
					
				}
				
			}
		}
		
		
		if(sqlobjts.colsList.size() > 0)
		{
			SQLQuery sqlQuery = new SQLQuery();
			if (sqlobjts.colsList.get(0) instanceof SQLQuery)
				sqlQuery = (SQLQuery) sqlobjts.colsList.get(0);
			
			if(sqlQuery.dmlType != null)
			{
			    sqlValue += "\n "+ fillSelectStmts(sqlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList);
			}
		}
		return sqlValue;
	}

	private static String fillCreateDDLStmts(SQLQuery sqlobjts, SQLValue sqlVal, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		String sqlValue ="";
		sqlValue += "CREATE TABLE";
		String fromTableList = "";
		String updateTable = "";
		
	
		for (int i = 0; i < sqlobjts.tableList.size(); i++) 
		{
			SQLElement sqlElem = new SQLElement();
			SQLQuery sqlQuery = new SQLQuery();
			if (sqlobjts.tableList.get(0) instanceof SQLQuery)
				sqlQuery = (SQLQuery) sqlobjts.tableList.get(0);
		
			else if(sqlobjts.tableList.get(0) instanceof SQLElement)
				sqlElem = (SQLElement) sqlobjts.tableList.get(0);
			if(i == 0)
			{
				String elemName = getUpdatedTableFromConfig(sqlElem, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
				
				if(sqlQuery.dmlType == null)
				{
					if(sqlElem.aliasName == null)
						updateTable += "\n       "+ elemName;
					else
						updateTable += "\n       "+ elemName+" " + sqlElem.aliasName;
				}
				tblMap.add("CREATE ; "+elemName);
			}
			
			
			if(i >= 1)
			{
				String fromTable = "";
				SQLElement sqlElem2 = new SQLElement();
				SQLQuery sqlQuery1 = new SQLQuery();
				
				if (sqlobjts.tableList.get(i) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) sqlobjts.tableList.get(i);
			
				else if(sqlobjts.tableList.get(i) instanceof SQLElement)
					sqlElem2 = (SQLElement) sqlobjts.tableList.get(i);
				
				
				if(sqlQuery1.dmlType == null)
				{
					if(sqlElem2.name.equalsIgnoreCase("DEFAULT"))
						sqlElem2.name = "";
					
					String elemName  = "";
					if(!sqlElem2.name.equalsIgnoreCase("DEFAULT"))
					{
						if(sqlElem2.name.length() > 0)
						{
							elemName = getUpdatedTableFromConfig(sqlElem2, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
						}
					}
					
					tblMap.add(elemName);
					
					if(sqlElem2.aliasName == null)
						fromTable += "\n       "+ elemName;
					else
						if(sqlQuery.tableList.size() > 1)
						{
							if(i==(sqlobjts.tableList.size()-1))
								fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName;
							else
								fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName+",";
						}
						else
							fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName;
				}
				
				else
				{
					tblMap.add("SQLQueryTable");
					if(sqlobjts.tableList.size() > 1)
					{
						if(i==(sqlobjts.tableList.size()-1))
						{
							if(sqlQuery1.aliasName != null)
								fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
							else
								fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
						}
						else
						{
							if(sqlQuery1.aliasName != null)
								fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName+",";
							else
								fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
						}
					}
					else
					{
						if(sqlQuery1.aliasName != null)
							fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +sqlQuery1.aliasName;
						else
							fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
					}
				}
				
				fromTableList += fromTable;
				
			}
		}
		if(updateTable.length() > 0)
		{
			sqlValue += updateTable;
		}
		if(fromTableList.trim().length() > 0)
		{
			if(fromTableList.contains("SELECT") && newDbId == ToolTypeConstants.AZURESQL)
			{
				sqlValue += "\n  WITH" ;
				sqlValue +="\n ("  ; 
				sqlValue += "\n    CLUSTERED COLUMNSTORE INDEX,"  ;
				sqlValue += "\n    DISTRIBUTION = ROUND_ROBIN " ;
				sqlValue += "\n  )";
			}
			sqlValue += "\n AS";
			sqlValue += "\n ("+fromTableList;
			if(!fromTableList.contains("SELECT"))
			{
				sqlValue += "\n )";
			}
		}
		if(sqlobjts.colsList.size() > 0)
		{
//			sqlValue += "\n ( ";
			for (int j = 0; j < sqlobjts.colsList.size(); j++) 
			{
				SQLElement sqlElem2 = new SQLElement();
				SQLQuery sqlQuery1 = new SQLQuery();
				
				if (sqlobjts.colsList.get(j) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) sqlobjts.colsList.get(j);
			
				else if(sqlobjts.colsList.get(j) instanceof SQLElement)
					sqlElem2 = (SQLElement) sqlobjts.colsList.get(j);
				
				if(sqlElem2.name != null)
				{
					if (j == 0)
						sqlValue += "\n ( ";
					
					if(sqlElem2.aliasName == null)
						if(j==(sqlobjts.colsList.size()-1))
						    sqlValue += "\n       "+ sqlElem2.name;
						else
							sqlValue += "\n       "+ sqlElem2.name + ",";
					else
					{
						String dataType = "";
						if(newDbId == ToolTypeConstants.BIGQUERY)
						{
							String temp = "";
							if (sqlElem2.aliasName.contains("not null") || sqlElem2.aliasName.contains("NOT NULL"))
								temp = " NOT NULL";
							
							dataType = getUpdatedDataTypeForBGQuery(sqlElem2.aliasName);
							
							if (temp.equalsIgnoreCase(" not null") && (!dataType.contains("NOT NULL")))
								dataType += temp;
						}
						else
						{
							dataType = sqlElem2.aliasName;
						}
						if(j==(sqlobjts.colsList.size()-1))
					       sqlValue += "\n       "+ sqlElem2.name+" " + dataType;
						else
						   sqlValue += "\n       "+ sqlElem2.name+" " + dataType+ ",";
					}
					
					if(j==(sqlobjts.colsList.size()-1))
						sqlValue += "\n ) ";
				}

				if (sqlQuery1 != null)
				{
					if (sqlQuery1.dmlType != null)
					{
						if (sqlQuery1.dmlType.equalsIgnoreCase("WITH TEMPORARY TABLE"))
						{
							SQLValue sqlValTmp = new SQLValue();
							sqlValue += "\n"+fillWithTempStatements(sqlQuery1,sqlValTmp,rptLogInfo,tblMap,newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
						}
					}
				}
			}
			
//			sqlValue += "\n ) ";
		}
		return sqlValue;
	}

	public static String getUpdatedDataTypeForBGQuery(String aliasName) 
	{
		if(aliasName.contains("NUMERIC") || aliasName.contains("TIMESTAMP")|| aliasName.contains("STRING") || aliasName.contains("DATETIME"))
		{
			if(aliasName.contains("("))
			{
				aliasName = aliasName.substring(0,aliasName.indexOf("("));
			}
			
		}
		return aliasName;
	}

	private static String fillSqlStmtsForDltStmts(SQLQuery sqlQuery, SQLValue sqlVal, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList)
	{
		String sqlValue ="";
		
		
		if(sqlQuery.dmlType.equalsIgnoreCase("DELETE"))
		{
			sqlValue += "DELETE";
		}
		else if(sqlQuery.dmlType.equalsIgnoreCase("DROP"))
		{
			sqlValue += "DROP TABLE";
		}
		else if(sqlQuery.dmlType.equalsIgnoreCase("TRUNCATE"))
		{
			sqlValue += "TRUNCATE TABLE";
		}
		String updateTbleName = getTableAlias (sqlQuery);
		if(sqlQuery.tableList.size() > 0)
		{
			String tableVal = "";
			 if (encloseConfigObjts != null)
				if (encloseConfigObjts.get("TableNames") == null)
					encloseConfigObjts = null;
				
			if (prefixConfigObjts != null)
				if (prefixConfigObjts.get("TableNames") == null)
					prefixConfigObjts = null;
		
			sqlValue += getTableSQLVal(sqlQuery,tableVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts,replaceSchemaConfigObjts,false, false, true,uniqueAliasList, null);
		}
		
		if(sqlQuery.whereCond != null)
		{
			if (!sqlQuery.whereCond.type.equalsIgnoreCase("qualify"))
			{
				String whereCond = "";
				String condTyp = sqlQuery.whereCond.type.toUpperCase();
				String updAlias = "",updTab = "";
				if (updateTbleName.contains(";"))
				{
					updTab = updateTbleName.substring(0, updateTbleName.indexOf(";"));
					updAlias = updateTbleName.substring(updateTbleName.indexOf(";")+1, updateTbleName.length());
				}
//				sqlValue += fillWhereConds(sqlQuery,whereCond,rptLogInfo,tblMap,updAlias,updTab,-1, condTyp);
				sqlValue += fillWhereConds(sqlQuery,whereCond,rptLogInfo,tblMap,updAlias,updTab,newDbId, condTyp,null, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
			}
		}
		else
		{
			if(newDbId == ToolTypeConstants.BIGQUERY)
			{
				sqlValue += "\n WHERE true";
			}
		}
		
		return sqlValue;
	}

	private static String getTableAlias(SQLQuery sqlobjts) 
	{
		String updateTable = "";
		if(sqlobjts.tableList.size() > 0)
		{
			for (int i = 0; i < sqlobjts.tableList.size(); i++) 
			{
				SQLElement sqlElem = new SQLElement();
				SQLQuery sqlQuery = new SQLQuery();
				if (sqlobjts.tableList.get(0) instanceof SQLQuery)
					sqlQuery = (SQLQuery) sqlobjts.tableList.get(0);
			
				else if(sqlobjts.tableList.get(0) instanceof SQLElement)
					sqlElem = (SQLElement) sqlobjts.tableList.get(0);
				if(i == 0)
				{
					if(sqlQuery.dmlType == null)
					{
						if(sqlElem.aliasName != null && (!sqlElem.aliasName.equalsIgnoreCase("")))							
						{
							updateTable = sqlElem.name+";" + sqlElem.aliasName;
						}
					}
				}
			}
		}
		return updateTable;
	}

	private static String fillSqlStmtsForUpdStmts(SQLQuery sqlobjts, SQLValue sqlVal, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		String sqlValue ="";
		sqlValue += "UPDATE";
		String fromTableList = "";
		String updateTable = "";
		String updateAliasName = "";
		String updateTblName = "";
		String tmpQryList = "";
		LinkedHashMap<String, String> tableList = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < sqlobjts.tableList.size(); i++) 
		{
			SQLElement sqlElem = new SQLElement();
			SQLQuery sqlQuery = new SQLQuery();
			if (sqlobjts.tableList.get(0) instanceof SQLQuery)
				sqlQuery = (SQLQuery) sqlobjts.tableList.get(0);
		
			else if(sqlobjts.tableList.get(0) instanceof SQLElement)
				sqlElem = (SQLElement) sqlobjts.tableList.get(0);
			if(i == 0)
			{
				String elemName = getUpdatedTableFromConfig(sqlElem, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
				
				if(sqlQuery.dmlType == null)
				{
					if(sqlElem.aliasName == null)
						updateTable += "\n       "+ elemName;
					else
					{
						if(newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.SQLSERVER || newDbId == ToolTypeConstants.REDSHIFT)
						{
							updateTable += "\n       "+ elemName;
							updateTblName = elemName;
							updateAliasName = sqlElem.aliasName;
						}
						else
						{
							updateTable += "\n       "+ elemName+" " + sqlElem.aliasName;
						}
					   
					}
				}
				tblMap.add("UPDATE ; "+elemName);
			}
			if(i >= 1)
			{
				String fromTable = "";String tmpQry = "";
				SQLElement sqlElem2 = new SQLElement();
				SQLQuery sqlQuery1 = new SQLQuery();
				
				if (sqlobjts.tableList.get(i) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) sqlobjts.tableList.get(i);
			
				else if(sqlobjts.tableList.get(i) instanceof SQLElement)
					sqlElem2 = (SQLElement) sqlobjts.tableList.get(i);
				
				if(sqlElem2.name != null)
				{
					String elemName = getUpdatedTableFromConfig(sqlElem2, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
					
					if(sqlQuery1.dmlType == null)
					{
						
						if(sqlElem2.aliasName == null)
							fromTable += "\n       "+ elemName;
						else
						{
							tableList.put(sqlElem2.aliasName, sqlElem2.name);
							if(sqlQuery.tableList.size() > 1)
							{
								if(i==(sqlobjts.tableList.size()-1))
									fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName;
								else
									fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName+",";
							}
							else
								fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName;
							
						}
						
						tblMap.add(elemName);
					}
				}
				else
				{
					
					if(newDbId == ToolTypeConstants.AZURESQL)//To create tempSQl for subQuery
					{
						String tmpTable = "";
						if (sqlQuery1.aliasName != null)
						{
							tmpTable += "#"+sqlQuery1.aliasName+" ";
							fromTable += "#"+sqlQuery1.aliasName+" "+sqlQuery1.aliasName;
						}
						else
						{
							tmpTable += "#temp";
							fromTable += "#temp"+" "+"#temp";
						}
						tmpTable = tmpTable.trim();
//						tmpQry += "IF OBJECT_ID('tempdb.."+tmpTable+"') IS NOT NULL  \n DROP TABLE "+tmpTable+";\n";
						tmpQry += "IF OBJECT_ID("+tmpTable+") IS NOT NULL  \n SELECT * INTO "+tmpTable+"\n FROM (";
						String tmp = fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,tmpTable,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList).trim() ;
						String tempAliasTable = tmpTable.substring(1, tmpTable.length()).trim();
						
						if (tmp.startsWith("("))
						{
							tmpQry += tmp.substring(1, tmp.length())+"\n)"+tempAliasTable+"_tmp ";
						}
						    
						else
						{
							tmpQry += tmp+"\n)"+tempAliasTable+"_tmp ";
						}
						if(sqlQuery1.whereCond != null)
						{
							if(sqlQuery1.whereCond.type != null && newDbId == ToolTypeConstants.AZURESQL)
							{
								if(sqlQuery1.whereCond.type.equalsIgnoreCase("qualify"))
								{
									ArrayList<String> qualifyCond = new ArrayList<String>();
									boolean chkFlag = true;
									String exprVal = fillWhereConds(sqlQuery1,"",rptLogInfo,tblMap,"","",newDbId, "",null, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList); 
									getUpdateValForQualify(exprVal, qualifyCond, chkFlag);
									
									String condVal = "";
									if(qualifyCond.size() > 0)
									condVal = qualifyCond.get(0);
									else
									condVal = "DSError";

									
									tmpQry+= "\n WHERE"+" \n  "+tempAliasTable+"_Alias " + condVal+"\n---DS_Validation_Message: Require manual intervention to validate the condition in Qualify clause ";
									rptLogInfo.manualChanges.add("WHERE ; " + "DS_Validation_Message: Require manual intervention to validate the condition in Qualify clause "+"\""+condVal+"\"");
									
								}
							}
						}
					}
					else
					{
						tblMap.add("SQLQueryTable");
						if(sqlobjts.tableList.size() > 1)
							if(i==(sqlobjts.tableList.size()-1))
							{
								if(sqlQuery1.aliasName != null)
									fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
								else
									fromTable += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
							}
							else
							{
								if(sqlQuery1.aliasName != null)
									fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName+",";
								else
									fromTable += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
							}
						else
							if(sqlQuery1.aliasName != null)
								fromTable += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +sqlQuery1.aliasName;
							else
								fromTable += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
					}
				}
				fromTableList += fromTable;
				tmpQryList += tmpQry;
				if (fromTable.length() > 0 && (i<(sqlobjts.tableList.size()-1)))
					fromTableList += ",";
			}
		}
		
		if(updateTable.length() > 0)
		{
			if(newDbId == ToolTypeConstants.AZURESQL)
			{
				if (tableList.size() > 0)
				{
					if (tableList.get(updateTable.trim()) != null)
						updateTable = "\n       "+ tableList.get(updateTable.trim());
//					for (Entry<String, String> entrySet : tableList.entrySet())
//					{
//						if (entrySet.getKey().equalsIgnoreCase(updateTable.trim()))
//							updateTable = "\n       "+ entrySet.getValue();
//					}
				}
			}
			sqlValue += updateTable;
		}
		
		if(sqlobjts.updateCols.size() > 0)
		{
			sqlValue += "\nSET";
			
			for(int i = 0 ; i < sqlobjts.updateCols.size();i++)
			{
				UpdateElement updElem = new UpdateElement();
				if (sqlobjts.updateCols.get(i) instanceof UpdateElement)
					updElem = (UpdateElement) sqlobjts.updateCols.get(i);
				
				SQLElement sqlElem = new SQLElement();
				if(updElem.value instanceof SQLElement)
					sqlElem = (SQLElement) updElem.value;
				
				SQLQuery sqlquery = new SQLQuery();
				if(updElem.value instanceof SQLQuery)
					sqlquery = (SQLQuery) updElem.value;
				if(sqlElem.name != null)
				{
					if(i==(sqlobjts.updateCols.size()-1))
						 sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name;
					else
					     sqlValue += "\n     "+updElem.updColName + " = " + sqlElem.name +",";
				   
					if(updElem.updColName.contains("ds") || sqlElem.name.contains("ds"))
					{
						rptLogInfo.manualChanges.add("SET ; " + updElem.updColName + " = " + sqlElem.name);
					}
					
	
					boolean checkExp = getExpressionFunc(sqlElem.name);
					if(checkExp)
					{
						rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
					}
				}
				if(sqlquery != null)
			    {
			    	if(sqlquery.dmlType != null)
			    	{
				    	if(sqlquery.aliasName != null)
						    sqlValue += "   "+updElem.updColName + " = " +fillSelectStmts(sqlquery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlquery.aliasName;
						else
						{
							sqlValue += "   "+updElem.updColName + " = " + "( "+fillSelectStmts(sqlquery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
						}
				    	
				    	tblMap.add("SQLQueryTable");
			    	}
			    }
				
			}
			
		}
		if(fromTableList.length() > 0)
		{
			sqlValue += "\n FROM";
			if (fromTableList.contains(updateTable))//tmpQryList.length() > 0)//To remove the from table
			{
				String tmp = fromTableList.substring(0, fromTableList.indexOf(",")).trim();
				
				if(tmp.contains(" "))
				{
					updateAliasName = tmp.substring(tmp.indexOf(" "), tmp.length());
					updateTblName = tmp.substring(0, tmp.indexOf(" "));
				}
				
				if(newDbId == ToolTypeConstants.AZURESQL)
					fromTableList = fromTableList.substring(fromTableList.indexOf(",")+1, fromTableList.length());
				
			}
			
			if (fromTableList.endsWith(",")) 
				fromTableList = fromTableList.substring(0,fromTableList.length() - 1);
			
			sqlValue += "\n "+fromTableList;
		}
		if(sqlobjts.joinCond != null)
		{
			SQLJoin join = new SQLJoin();
			
			if (sqlobjts.joinCond instanceof SQLJoin)
				join = (SQLJoin) sqlobjts.joinCond;
			String joinconds = "";
			sqlValue += fillJoinCondDtls(sqlobjts, join,joinconds,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
		}
		if(sqlobjts.whereCond != null)
		{
			if (!sqlobjts.whereCond.type.equalsIgnoreCase("qualify"))
			{
				String whereCond = "";
				String condTyp = sqlobjts.whereCond.type.toUpperCase();
				
				ArrayList<Integer> subQryCnt = new ArrayList<Integer>();
				
				sqlValue += fillWhereConds(sqlobjts,whereCond,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId, condTyp, subQryCnt,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
			    
				if (subQryCnt.size() > 0 && newDbId == ToolTypeConstants.AZURESQL)
				{
					sqlValue+= "\n "+"---DS_Validation_Message: Require manual intervention to validate the SubQuery in Where Clause ";
					
					rptLogInfo.manualChanges.add("WHERE ; " +"DS_Validation_Message: Require manual intervention to validate the SubQuery in Where Clause");
					subQryCnt = new ArrayList<Integer>();
				}
			}
		}
		if (tmpQryList.length() > 0)
		{
			String tmpSQl = sqlValue;
			sqlValue = "";
			sqlValue = tmpQryList +"\n\n"+ tmpSQl;
		}
		
		return sqlValue;
	}

	public static String getUpdatedTableFromConfig(SQLElement sqlElem,
			LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts,
			LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts) 
	{
		String quoteType = "",prefixWith = "";
		boolean quoteTbl = false, prefixTbl = false, replaceTbl = false, quoteView = false, prefixView = false;
		if (encloseConfigObjts != null)
		{
			quoteType = encloseConfigObjts.get("QuotesType");
			if (encloseConfigObjts.get("TableNames") != null )
				quoteTbl = true;
			
		}
		if (prefixConfigObjts != null)
		{
			prefixWith = prefixConfigObjts.get("Prefix_With");
			if (prefixConfigObjts.get("TableNames") != null )
				prefixTbl = true; 
			
		}
		if(!replaceSchemaConfigObjts.isEmpty() || replaceSchemaConfigObjts.size() > 0)
		{
			replaceTbl = true;
		}
		
		String elemName  = getUpdatedTableName (sqlElem ,quoteType,quoteTbl, prefixTbl, prefixWith, replaceTbl,replaceSchemaConfigObjts, false ,false,
				quoteView ,prefixView, true);
		
		return elemName;
	}

	private static String getUpdatedTableName(SQLElement sqlElemCond, String quoteType, boolean quoteTbl, boolean prefixTbl,
			String prefixWith, boolean checkRplsTbl, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, boolean checkTable, boolean checkViews, boolean quoteView, boolean prefixView, boolean chkTgtTable) 
	{
		String elemName = sqlElemCond.name;
		
		if(checkRplsTbl)
		{
			LinkedHashMap<String, String> getUpdatedVals = getCurNameMap(checkTable, checkViews, elemName, replaceSchemaConfigObjts, chkTgtTable);
			
			if(getUpdatedVals.size() > 0)
			{
				String currentName = getUpdatedVals.get("curName");
				String newName = getUpdatedVals.get("newName");
				
				boolean checkTbl = false, checkView = false ;
				if(getUpdatedVals.get("checkTable") != null)
					checkTbl = true;
				
				if(getUpdatedVals.get("checkView") != null)
					checkView = true;
				
				if(checkView  || checkTbl)
				{
					if(checkViews || checkTable || chkTgtTable)
						elemName = elemName.replace(currentName, newName);
				}
				
			}
			
		}
		
		if (checkViews)
			if(prefixView)
				elemName = prefixWith+"."+elemName;
		
		if(checkTable || chkTgtTable)
			if(prefixTbl)
				elemName = prefixWith+"."+elemName;
		
		quoteType = getQuoteType(quoteType);
		if(checkViews)
			if(quoteView)
				elemName = quoteType+elemName+quoteType;
			
		if(checkTable || chkTgtTable)
			if(quoteTbl)
				elemName = quoteType+elemName+quoteType;
		
		if((prefixTbl || checkRplsTbl || quoteTbl) && checkTable && sqlElemCond.aliasName == null)
		{
			String tableName = "";
			if(sqlElemCond.name != null)
			{
				if(sqlElemCond.name.contains("."))
				{
					tableName = sqlElemCond.name.substring(sqlElemCond.name.lastIndexOf(".")+1, sqlElemCond.name.length());
				}
				else
				{
					tableName = sqlElemCond.name;
				}
				
				elemName = elemName + " " + tableName;
			}
				
			
		}
		
		return elemName;
		
	}

	private static LinkedHashMap<String, String> getCurNameMap(boolean checkTable, boolean checkViews, String elemName,
			LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, boolean chkTgtTable)
	{
		LinkedHashMap<String,String> updatedSchemaVal = new LinkedHashMap<String, String>();
		for(Entry<String, LinkedHashMap<String, String>> configEntry : replaceSchemaConfigObjts.entrySet())
		{
			String keyValue = configEntry.getKey();
			LinkedHashMap<String ,String> configValue = configEntry.getValue();
			if(elemName != null)
			{
				if(elemName.contains("."))
				{
					String schemaName = elemName.substring(0, elemName.lastIndexOf("."));
					if(schemaName.equalsIgnoreCase(keyValue))
					{
						updatedSchemaVal.put("curName", keyValue);
						updatedSchemaVal.put("newName", configValue.get("newName"));
						
						if(configValue.get("TableNames") != null)
						{
							if(checkTable || chkTgtTable)
							{
								updatedSchemaVal.put("checkTable", "true");
							}
						}
							
						if(configValue.get("ViewNames") != null)
						{
					       if(checkViews)
					       {
					    	   updatedSchemaVal.put("checkView", "true");
					       }
						}
					}
				}
			}
			
		}
		return updatedSchemaVal;
	}

	

	private static String fillSqlStmtsForInsrtStmts(SQLQuery sqlobjts, SQLValue sqlVal, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, 
			LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		String sqlValue ="";
		sqlValue += "INSERT INTO";
		
		for (int i = 0; i < sqlobjts.tableList.size(); i++) 
		{
			SQLElement sqlElem = new SQLElement();
			SQLQuery sqlQuery = new SQLQuery();
			
			if (sqlobjts.tableList.get(i) instanceof SQLQuery)
				sqlQuery = (SQLQuery) sqlobjts.tableList.get(i);
		
			else if(sqlobjts.tableList.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) sqlobjts.tableList.get(i);
			
			if(sqlQuery.dmlType == null)
			{
				String elemName = getUpdatedTableFromConfig(sqlElem, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
				
				if(sqlElem.aliasName == null)
					sqlValue += "\n       "+ elemName;
				else
				{
					if(newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.SQLSERVER)
					{
						if(sqlElem.name.endsWith("."))
							sqlValue += "\n       "+ elemName+"" + sqlElem.aliasName;
						else
							sqlValue += "\n       "+ elemName;
					}
					else
					{
						sqlValue += "\n       "+ elemName+" " + sqlElem.aliasName;
					}
					
				}
				

				tblMap.add("INSERT INTO ;"+elemName);
			}
		}
		
		ArrayList<ExternalTable> tblList = new ArrayList<ExternalTable>();
		if(sqlobjts.externalTables.size() > 0)
		{
			//sqlValue += "\n( ";
			String colVal = "";
			
			for(int i = 0 ; i < sqlobjts.externalTables.size();i++)
			{
				SQLElement updElem = null;//new SQLElement();
				ExternalTable exTab = null;
				if (sqlobjts.externalTables.get(i) instanceof ExternalTable)
					exTab = (ExternalTable) sqlobjts.externalTables.get(i);
				else if (sqlobjts.externalTables.get(i) instanceof SQLElement)
					updElem = (SQLElement) sqlobjts.externalTables.get(i);
				
				if (updElem != null)
				{
					if(sqlobjts.externalTables.size() > 1)
					{
						if(i==(sqlobjts.externalTables.size()-1))
							colVal += "\n     "+ updElem.name;
						else
							colVal += "\n     "+ updElem.name+",";
					}
				}
				else if (exTab != null)
				{
					if(exTab.tableAliasName != null)
						tblList.add(exTab);
				}
				else
					colVal += "\n     "+ updElem.name;
				
			}
			
			
			if(colVal.length() > 0)
			{
				if (colVal.substring(0, colVal.length()-1).equalsIgnoreCase(","))
					colVal = colVal.substring(0, colVal.length()-1);

				sqlValue += "\n("+ colVal +"\n) ";
			}
			
		}
		
		if(tblList.size() > 0)
		{
			SQLQuery sqlQuery = new SQLQuery();
			sqlQuery.dmlType = "WITH TEMPORARY TABLE";
			sqlQuery.externalTables.addAll(tblList);
			sqlValue += "\n "+fillWithTempStatements(sqlQuery,sqlVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
		}
		
		
		for (int i = 0; i < sqlobjts.colsList.size(); i++) 
		{
			SQLElement sqlElem = new SQLElement();
			SQLQuery sqlQuery = new SQLQuery();
			
			if (sqlobjts.colsList.get(i) instanceof SQLQuery)
				sqlQuery = (SQLQuery) sqlobjts.colsList.get(i);
		
			else if(sqlobjts.colsList.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) sqlobjts.colsList.get(i);
				
			
			if(sqlQuery.dmlType == null)
			{
				if(i == 0)
				{
					sqlValue += "\n VALUES (";
				}
				
				if(sqlElem.aliasName == null)
					if(i==(sqlobjts.colsList.size()-1))
					    sqlValue += "\n       "+ sqlElem.name;
					else
						sqlValue += "\n       "+ sqlElem.name + ",";
				else
					if(i==(sqlobjts.colsList.size()-1))
				       sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName;
					else
					   sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName+ ",";
				
				if(sqlElem.name.contains("ds"))
				{
					rptLogInfo.manualChanges.add("VALUES ; " + sqlElem.name);
				}

				boolean checkExp = getExpressionFunc(sqlElem.name);
				if(checkExp)
				{
					rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
				}
				if(i == (sqlobjts.colsList.size()-1))
				{
					sqlValue += "\n )";
				}
				
			}
			else if(sqlQuery.dmlType.equalsIgnoreCase("SELECT"))
			{
				String selectStmts = fillSelectStmts(sqlQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList);
				sqlValue  += selectStmts;
			}
			
		}
		return sqlValue;
	}

	public static String fillSelectStmts(SQLQuery sqlQuery, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, String tmpTable, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<SQLObject> viewColsList, ArrayList<String> uniqueAliasList)
	{
		String sqlValue = "";
		
		ArrayList<SQLExpr> dummyExprForAzure = new  ArrayList<SQLExpr>();
		
		if(sqlQuery.aliasName != null)
		{
			if(sqlQuery.hiddenSQL == false)
				sqlValue += "\n (SELECT";
		}
		else
		{
			if(sqlQuery.hiddenSQL == false)
				sqlValue += "\n SELECT";
		}
			
		if(sqlQuery.predicate != null)
		{
			sqlValue += "\n "+sqlQuery.predicate;
		}
			
		if(sqlQuery.hiddenSQL == false)
		{
			int attrCnt =0;
			for (int i = 0; i < sqlQuery.colsList.size(); i++) 
			{
				SQLElement sqlElem = new SQLElement();
				SQLQuery sqlQuery1 = new SQLQuery();
				
				if (sqlQuery.colsList.get(i) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) sqlQuery.colsList.get(i);
			
				else if(sqlQuery.colsList.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) sqlQuery.colsList.get(i);
				
				if (viewColsList != null)
				{
					if (viewColsList.size() > 0 && newDbId == ToolTypeConstants.BIGQUERY)
					{
                        SQLElement aliasSqlElem = new SQLElement();
                        if (viewColsList.get(i) instanceof SQLElement)
                               aliasSqlElem = (SQLElement) viewColsList.get(i);
                        
                        ArrayList<Token> tknList = FunctionTypeConv.getTokens(sqlElem.name);
                        if (tknList.size() == 1)
                        {
                        	if (tknList.get(0).type.name().equalsIgnoreCase("VARWITHDOT"))
                        	{
                            	if (sqlElem.name.substring(sqlElem.name.indexOf(".")+1, sqlElem.name.length()).equalsIgnoreCase(aliasSqlElem.name))
                                    sqlElem.name = sqlElem.name;
                                else
                                {
                                    //sqlElem.name = sqlElem.name+" " + aliasSqlElem.name;
                                	sqlElem.name = sqlElem.name;
                                    sqlElem.aliasName = aliasSqlElem.name;
                                }
                        	}
                        	else
                        	{
                        		if (sqlElem.name.equalsIgnoreCase(aliasSqlElem.name))
                                    sqlElem.name = sqlElem.name;
                                else
                                {
                                    //sqlElem.name = sqlElem.name+" " + aliasSqlElem.name;
                                	sqlElem.name = sqlElem.name;
                                    sqlElem.aliasName = aliasSqlElem.name;
                                }
                        	}
                        }
                        else
                        {
                        	if (sqlElem.aliasName != null && sqlElem.aliasName.equalsIgnoreCase("end"))
                        		sqlElem.aliasName = sqlElem.aliasName + " "+aliasSqlElem.name;
                        	else
                        	{
                        		//sqlElem.name = sqlElem.name+" " + aliasSqlElem.name;
                        		sqlElem.name = sqlElem.name;
                                sqlElem.aliasName = aliasSqlElem.name;
                        	}
                        }
					}
				}
				if(sqlQuery1.dmlType == null)
				{
						if (newDbId == ToolTypeConstants.BIGQUERY)
						{
							ArrayList<Token> tknList = FunctionTypeConv.getTokens(sqlElem.name);

							for (int j = 0; j < tknList.size(); j++)
							{
								Token tkn = tknList.get(j);
								if (tkn.type.name().equalsIgnoreCase("BINARYOP") && tkn.data.equalsIgnoreCase("-"))
								{
									sqlValue += "\n ---DS_Validation_Message: Require manual intervention to update the DATE difference ";

									rptLogInfo.manualChanges.add("SELECT ; " + sqlElem.name
											+ "\n DS_Validation_Message: Require manual intervention to update the DATE difference");

								}
							}
						}
						
						if(newDbId == ToolTypeConstants.REDSHIFT)
						{
							boolean hasRowid = checkRowidForRedshift(sqlElem.name);
							
							if(hasRowid)
							{
								sqlElem.name = "null";
							}
						}
					
					if(sqlElem.aliasName == null)
						if(i==(sqlQuery.colsList.size()-1))
						    sqlValue += "\n       "+ sqlElem.name;
						else
							sqlValue += "\n       "+ sqlElem.name + ",";
					else
						if(i==(sqlQuery.colsList.size()-1))
					       sqlValue += "\n       "+ sqlElem.name+" as " + sqlElem.aliasName;
						else
						   sqlValue += "\n       "+ sqlElem.name+" as " + sqlElem.aliasName+ ",";
					
					boolean checkExp = getExpressionFunc(sqlElem.name);
					if(checkExp)
					{
						rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
					}
					
					if(sqlElem.name.contains("ds"))
					{
						rptLogInfo.manualChanges.add("SELECT ; " + sqlElem.name);
					}
				}
				else
				{
					if(sqlQuery1.dmlType != null)
			    	{
				    	if(sqlQuery1.aliasName != null)
						    sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
						else
						{
							sqlValue += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
						}
			    	}
				}
				  
			}
			if(sqlQuery.whereCond != null)
			{
				if(sqlQuery.whereCond.type != null && (newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT))
				{
					if(sqlQuery.whereCond.type.equalsIgnoreCase("qualify"))
					{
						String whereCond = "";
						String exprVal = fillWhereConds(sqlQuery,whereCond,rptLogInfo,tblMap,"","",newDbId, "",null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
						String updatedVal = getUpdateValForQualify(exprVal, null, false);
						if(tmpTable.length() > 0)
							sqlValue += ","+updatedVal+" "+tmpTable.substring(1,tmpTable.length())+"_Alias";
						else
						{
							SQLExpr sqlExpr = sqlQuery.whereCond;
							
							if(sqlExpr.args.size() > 0)
							{
								if(sqlExpr.args.size() == 1)
								{
									SQLExpr sqlUpdExpr = (SQLExpr) sqlExpr.args.get(0);
									if(sqlUpdExpr.type.equalsIgnoreCase("qualify"))
									{
										String alisaQFName = SQLUtils.getUniqueCompName("QF_"+0, uniqueAliasList);
										sqlValue += ","+updatedVal+ " as " + alisaQFName;
										
										if(exprVal.contains("="))
										{
											String qualifyVal = exprVal.substring(exprVal.indexOf("=")+1, exprVal.length());
											sqlUpdExpr.type = "WHERE";
											sqlUpdExpr.value = alisaQFName+ " ="+ qualifyVal; 
											
											sqlExpr.type = "WHERE";
											sqlExpr.value = alisaQFName+ " ="+ qualifyVal;	
											sqlQuery.whereCond = sqlExpr;
											
											dummyExprForAzure.add(sqlExpr);
											
											sqlExpr.args.remove(sqlUpdExpr);
											sqlQuery.whereCond = null;
										}
									}
								}
							}
						}
						
					}
					else
					{
						SQLExpr sqlExpr = sqlQuery.whereCond;
						
						if(sqlExpr.args.size() > 1)
						{
							for(int i = 0 ; i < sqlExpr.args.size() ; i++)
							{
								SQLExpr sqlUpdExpr = (SQLExpr) sqlExpr.args.get(i);
								
								if(sqlUpdExpr.type.equalsIgnoreCase("qualify"))
								{
									String alisaQFName = SQLUtils.getUniqueCompName("QF_"+i, uniqueAliasList);
									
									String exprVal = sqlUpdExpr.value;
									String updatedVal = getUpdateValForQualify(exprVal, null, false);
									if(tmpTable.length() > 0)
										sqlValue += ","+updatedVal+" "+tmpTable.substring(1,tmpTable.length())+"_Alias";
									else
										sqlValue += ","+updatedVal +" as " +alisaQFName;
									if(exprVal.contains("="))
									{
										String qualifyVal = exprVal.substring(exprVal.indexOf("=")+1, exprVal.length());
										sqlUpdExpr.type = "AND";
										sqlUpdExpr.value = alisaQFName+ " ="+ qualifyVal; 
										
										dummyExprForAzure.add(sqlUpdExpr);
										
										sqlExpr.args.remove(i);
									}
									
								}
							}
						}
					}
				}
			}
		}
	
		if (sqlQuery.externalTables.size() > 0)
		{
			SQLElement sqlElem = new SQLElement();
			sqlValue += " INTO ";
			for (int i = 0; i < sqlQuery.externalTables.size(); i++) 
			{
				if (sqlQuery.externalTables.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) sqlQuery.externalTables.get(i);
				
				if(i==(sqlQuery.externalTables.size()-1))
					sqlValue += sqlElem.name;
				else
					sqlValue += sqlElem.name+",";
				
//				sqlValue += " INTO "+sqlElem.name;
			}
		}
		String tableVal = "";
		if(sqlQuery.tableList.size() > 0)
		{
			if (encloseConfigObjts != null)
				if (encloseConfigObjts.get("TableNames") == null)
					encloseConfigObjts = null;
				
			if (prefixConfigObjts != null)
				if (prefixConfigObjts.get("TableNames") == null)
					prefixConfigObjts = null;
			
		   sqlValue += getTableSQLVal(sqlQuery,tableVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts,replaceSchemaConfigObjts,true, false, false,uniqueAliasList, null);

		}
		
//		if(sqlQuery.whereCond != null && newDbId != ToolTypeConstants.AZURESQL)
//		{
//			if(sqlQuery.whereCond.type.equalsIgnoreCase("qualify"))
//			{
//				String whereCond = "";
//				String exprVal = fillWhereConds(sqlQuery,whereCond,rptLogInfo,tblMap,"","",newDbId, "",null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
//				sqlValue += "\n"+exprVal;
//				
//			}
//		}
		
		if(sqlQuery.joinCond != null)
		{
			SQLJoin join = new SQLJoin();
			
			if (sqlQuery.joinCond instanceof SQLJoin)
				join = (SQLJoin) sqlQuery.joinCond;
			String joinconds = "";
			sqlValue += fillJoinCondDtls(sqlQuery, join,joinconds,rptLogInfo,tblMap,"","",newDbId,encloseConfigObjts, prefixConfigObjts , replaceSchemaConfigObjts, uniqueAliasList);
		}
		
		if(sqlQuery.whereCond != null)
		{
			if (!(sqlQuery.whereCond.type.equalsIgnoreCase("qualify") && (newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT)))
			{
				String whereCond = "";
				String condTyp = sqlQuery.whereCond.type.toUpperCase();
				sqlValue += fillWhereConds(sqlQuery,whereCond,rptLogInfo,tblMap,"","",newDbId, condTyp,null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
			}
		}
		if(sqlQuery.groupByCols != null)
		{
			String groupByCols = "";
			sqlValue += fillGroupByCols(sqlQuery,groupByCols,rptLogInfo,tblMap,newDbId);
		}
		if(sqlQuery.OrderBy != null)
		{
			String orderByCols = "";
			sqlValue += fillOrderByCols(sqlQuery,orderByCols,rptLogInfo,tblMap,newDbId);
		}
		
		if(sqlQuery.setOperation != null)
		{
			String unionStmts = "";
			sqlValue += fillUnionStmts(sqlQuery,unionStmts,rptLogInfo,tblMap,newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
		}
		
		if(newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT)
		{
			if(dummyExprForAzure.size() > 0)
			{
				String outerQuery = "";
				if(sqlQuery.aliasName == null)
				  outerQuery = "\n SELECT * FROM ";
				else
				  outerQuery = "\n (SELECT * FROM ";
				
				if(sqlQuery.aliasName == null)
					outerQuery += "(";
				
				sqlValue = outerQuery + sqlValue;
				
				for(int i = 0 ; i < dummyExprForAzure.size(); i++)
				{
					SQLExpr sqlExprVal = dummyExprForAzure.get(i);
					
					String queryAlias = sqlExprVal.value.trim().substring(0, sqlExprVal.value.indexOf("=")).trim();
					sqlValue = sqlValue + " ) ds_"+queryAlias + "\n WHERE \n      " +"ds_"+queryAlias+"."+sqlExprVal.value;
					
					dummyExprForAzure.clear();
				}
				
			}
		}
		
		return sqlValue;
	
	}
	
	private static boolean checkRowidForRedshift(String name) 
	{	
		for(Token token : FunctionTypeConv.getTokens(name))
		{
			if(token.type.name().equalsIgnoreCase("varwithdot"))
			{
				if(token.data.contains(".rowid"))
				{
					return true;
				}
			}
		}
		
		return false;
	}

	private static boolean getExpressionFunc(String name) 
	{
		ArrayList<Token> tmp = FunctionTypeConv.getTokens(name);
		
		if(tmp.size() > 1 )
			return true;
		
		return false;
	}

	private static String fillJoinCondDtls(SQLQuery sqlQuery, SQLJoin join, String sqlValue, ReportLogInfo rptLogInfo, List<String> tblMap, String updateAliasName, String updateTblName, int newDbId, 
			LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		
		String quoteType = "",prefixWith = "";
		boolean quoteTbl = false, prefixTbl = false, replaceTbl = false, quoteView = false, prefixView = false;
		if (encloseConfigObjts != null)
		{
			quoteType = encloseConfigObjts.get("QuotesType");
			if (encloseConfigObjts.get("TableNames") != null )
				quoteTbl = true;
			
		}
		if (prefixConfigObjts != null)
		{
			prefixWith = prefixConfigObjts.get("Prefix_With");
			if (prefixConfigObjts.get("TableNames") != null )
				prefixTbl = true; 
			
		}
		if(!replaceSchemaConfigObjts.isEmpty() || replaceSchemaConfigObjts.size() > 0)
		{
			replaceTbl = true;
		}
		
		SQLJoinCond sqlJoinCond = new SQLJoinCond();
		
		if(join.joinCondDtls instanceof SQLJoinCond)
			sqlJoinCond = (SQLJoinCond) join.joinCondDtls;
		
		if(sqlJoinCond.childTable != null)
		{
			
			tblMap.add("JOIN");
			if(!sqlQuery.dmlType.equalsIgnoreCase("Merge"))
			{
				if(join.joinType.equalsIgnoreCase("Join"))
					sqlValue += "\n "+join.joinType.toUpperCase();
				else
					sqlValue += "\n "+ join.joinType.toUpperCase() + " JOIN ";
			}
				
			SQLElement sqlElemCond = new SQLElement();
			SQLQuery sqlQueryCond = new SQLQuery();
			if(sqlJoinCond.childTable instanceof SQLElement)
				sqlElemCond = (SQLElement) sqlJoinCond.childTable;
			else if (sqlJoinCond.childTable instanceof SQLQuery)
				sqlQueryCond = (SQLQuery) sqlJoinCond.childTable;
			
		    if(sqlElemCond != null)
		    {
		    	String elemName = getUpdatedTableName(sqlElemCond, quoteType, quoteTbl, prefixTbl, prefixWith, replaceTbl,replaceSchemaConfigObjts,
		    			true, false,quoteView, prefixView, false );
		    	
		    	if(sqlElemCond.name != null)
		    	{
			    	if(sqlElemCond.aliasName == null)
			    	   sqlValue += "\n    "+elemName;
			    	else
			    	   sqlValue += "\n    "+elemName + " " + sqlElemCond.aliasName;
			    	
			    	tblMap.add(elemName);
		    	}
		    }
		    if(sqlQueryCond != null)
		    {
		    	if(sqlQueryCond.dmlType != null)
		    	{
			    	if(sqlQueryCond.aliasName != null)
					    sqlValue += fillSelectStmts(sqlQueryCond,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQueryCond.aliasName;
					else
					{
						sqlValue += "( "+fillSelectStmts(sqlQueryCond,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
					}
			    	
			    	tblMap.add("SQLQueryTable");
		    	}
		    }
		    
			if((newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT) && sqlQuery.dmlType.equalsIgnoreCase("Merge")
					&& updateTblName.equalsIgnoreCase("MERGE ; INSERT"))
			{
				sqlValue += "\nLEFT JOIN";
				
			    String tableVal = "";
			    if(sqlQuery.tableList.size() > 0)
			    {
			    	 if (encloseConfigObjts != null)
							if (encloseConfigObjts.get("TableNames") == null)
								encloseConfigObjts = null;
						
					if (prefixConfigObjts != null)
						if (prefixConfigObjts.get("TableNames") == null)
							prefixConfigObjts = null;
		
					   sqlValue += getTableSQLVal(sqlQuery,tableVal,rptLogInfo,tblMap,newDbId,encloseConfigObjts,prefixConfigObjts,replaceSchemaConfigObjts,true, false,false, uniqueAliasList, null);

			    }
			}
			
			String checkUpdTableName = "";
			if(updateTblName.contains("MERGE ; UPDATE"))
			{
				checkUpdTableName = updateTblName.substring(0,updateTblName.lastIndexOf(";")).trim();
				updateTblName = updateTblName.substring(updateTblName.lastIndexOf(";")+1, updateTblName.length()).trim();
			}
			
		    if(!((newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT) && sqlQuery.dmlType.equalsIgnoreCase("Merge")
					&& checkUpdTableName.equalsIgnoreCase("MERGE ; UPDATE")))
		    {
			    if(join.joinCond.size() > 0)
			    	   sqlValue += "\n  ON";
		    }
		    else
		    {
		    	 if(join.joinCond.size() > 0)
			    	   sqlValue += "\nWHERE";
		    }
		    boolean whereFlag = false;
			for (int i = 0; i < join.joinCond.size(); i++) 
			{
				
				SQLJoinCond sqlElem = new SQLJoinCond();
				
				SQLQuery sqlQuery1 = new SQLQuery();
				SQLExpr sqlExpr = new SQLExpr();
				
				if (join.joinCond.get(i) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) join.joinCond.get(i);
				else if(join.joinCond.get(i) instanceof SQLJoinCond)
					sqlElem = (SQLJoinCond) join.joinCond.get(i);
				else if(join.joinCond.get(i) instanceof SQLExpr)
					sqlExpr = (SQLExpr) join.joinCond.get(i); 
				
				if(sqlQuery1.dmlType == null)
				{
					/*if(join.joinCond.size() > 1)
						if(i==(join.joinCond.size()-1))  
							sqlValue += "\n       "+ sqlExpr.value;
						else
							sqlValue += "\n       "+ sqlExpr.value + " AND ";
					else
						sqlValue += "\n       "+ sqlExpr.value;*/
					
			    		if(sqlExpr.value != null)
			    		{
			    			SQLQuery sqlQueryExpr = new SQLQuery();
							if(sqlExpr.args.size() > 0)
								if(sqlExpr.args.get(0) instanceof SQLQuery)
									sqlQueryExpr = (SQLQuery) sqlExpr.args.get(0);
							
							String tempSqlValue = "";
							boolean inChk = false, bwChk = false;
							if ((sqlQueryExpr.dmlType != null) && newDbId == ToolTypeConstants.BIGQUERY)
							{
								SQLExpr sqlExprChk = new SQLExpr();
								if (sqlExpr.args.size() > 1)//For IN
								{
									for (int j = 0; j < sqlExpr.args.size(); j++) 
									{
										if (j == 0)
											continue;
										if(sqlExpr.args.get(j) instanceof SQLExpr)
											sqlExprChk = (SQLExpr) sqlExpr.args.get(j);
										
										if (sqlExprChk.value.trim().equalsIgnoreCase("IN"))
											inChk  = true;
									}
								}
								else//For BETWEEN
								{
									ArrayList<Token> tkn = FunctionTypeConv.getTokens(sqlExpr.value);
									for (int j = 0; j < tkn.size(); j++)
									{
										if (tkn.size() > 1)
										{
											if (tkn.get(1).data.equalsIgnoreCase("between"))
												bwChk = true;
											break;
										}
									}
								}
								
								tempSqlValue = sqlValue;
							}
							if(join.joinCond.size() > 1)
							{
								if(i > 0) 
								{
									 String sqlExprTyp  = sqlExpr.type.toUpperCase();
									
									if(sqlExpr.args.size() == 1)
									{
										String exprVal = "";
										
										sqlValue += " "+sqlExprTyp + " " +getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap ,updateAliasName,updateTblName,newDbId,null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
									}
									else
									{
										String exprVal = "";
										sqlValue += " "+sqlExprTyp + " " +getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
									}
								}
								else
								{
									String exprVal = "";
									sqlValue += getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
								}
							}
							else
							{
								String exprVal = "";
							    sqlValue += getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,null,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
							}
							if ((sqlQueryExpr.dmlType != null) && newDbId == ToolTypeConstants.BIGQUERY && (inChk || bwChk))
							{
								sqlValue = tempSqlValue;
								inChk = false;
								if (sqlQuery.whereCond != null)
								{
									sqlQuery.whereCond.args.add(sqlExpr);
								}
								else
								{
									SQLExpr sqlEx = new SQLExpr();
									sqlEx.type = "";
									sqlEx.value = " IN ";
					                sqlQuery.whereCond = sqlEx;
					                if (!(join.joinCond.size()-1 > i))
					                	sqlValue += " WHERE ";
					                else
					                	whereFlag = true;
									sqlQuery.whereCond.args.add(sqlExpr);
								}
							}
			    		}
				}
				else
				{
					if(join.joinCond.size() > 1)	
					{
						if(i==(join.joinCond.size()-1))
						{
							if(sqlQuery1.aliasName != null)
					            sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
							else
								sqlValue +="( "+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
						}
						else
							if(sqlQuery1.aliasName != null)
							    sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName+ " AND ";
							else
								sqlValue += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" "+ " AND ";
					}
					else
					{
						
						if(sqlQuery1.aliasName != null)
						   sqlValue += "("+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +sqlQuery1.aliasName;
						else 
						   sqlValue += "( "+fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
					}
				}
			}
			
			if(join.multiJoinTbl != null)
			{
				String sqlMultiJoin = "";
				SQLJoin multiJoin = (SQLJoin) join.multiJoinTbl;
				//tblMap.add("JOIN");
				sqlValue += fillJoinCondDtls(sqlQuery ,multiJoin, sqlMultiJoin,rptLogInfo,tblMap,"","",newDbId, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
			}
			if (whereFlag)
			{
				sqlValue += " WHERE ";
				whereFlag = false;
			}
		}
		
		return sqlValue;
	}

	private static String fillUnionStmts(SQLQuery sqlQuery, String unionStmts, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, 
			LinkedHashMap<String, String> prefixConfigObjts,LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		SQLUnion sqlUn = new SQLUnion();
		SQLQuery subQuery = new SQLQuery();
		
		if(sqlQuery.setOperation instanceof SQLUnion)
			sqlUn = (SQLUnion) sqlQuery.setOperation;
		if (sqlUn.dmlQuery instanceof SQLQuery) 
			subQuery = (SQLQuery) sqlUn.dmlQuery;
		
		tblMap.add("UNION");
		unionStmts += "\n "+sqlUn.type.toUpperCase();
		
		if(subQuery.dmlType != null)
		{
			if(subQuery.aliasName != null)
				unionStmts += fillSelectStmts(subQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +subQuery.aliasName;
			else
				unionStmts += fillSelectStmts(subQuery,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts,replaceSchemaConfigObjts , null, uniqueAliasList);
		}
		
		return unionStmts;
	}

	private static String fillOrderByCols(SQLQuery sqlQuery, String sqlValue, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId) 
	{
		if(sqlQuery.OrderBy.size() > 0)
		{
			sqlValue += "\n ORDER BY";
			tblMap.add("ORDER BY");
		}
		for (int i = 0; i < sqlQuery.OrderBy.size(); i++) 
		{
			SQLElement sqlElem = new SQLElement();
			SQLQuery sqlQuery1 = new SQLQuery();
			
			if (sqlQuery.OrderBy.get(i) instanceof SQLQuery)
				sqlQuery1 = (SQLQuery) sqlQuery.OrderBy.get(i);
		
			else if(sqlQuery.OrderBy.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) sqlQuery.OrderBy.get(i);
			
			if(sqlQuery1.dmlType == null)
			{
				String orderBy = "";
				if (sqlElem.OrderBy != null)
					orderBy = " "+ sqlElem.OrderBy;
				
				if (newDbId == ToolTypeConstants.BIGQUERY || 
						newDbId == ToolTypeConstants.SNOWFLAKE || newDbId == ToolTypeConstants.AZURESQL)
				{
					String colName = sqlElem.name;
					if (Integer.toString(i+1).equalsIgnoreCase(sqlElem.name))
						 colName = getColNameFrmColList(i, sqlQuery.colsList, sqlElem.name, sqlQuery.OrderBy.size());//GroupBy 1,2,3
					
					if(newDbId == ToolTypeConstants.BIGQUERY)
						sqlElem.name = colName + " IS NULL, "+colName;
					else
						sqlElem.name = colName;
					
				}
				
				if(sqlElem.aliasName == null)
					if(i==(sqlQuery.OrderBy.size()-1))
					    sqlValue += "\n       "+ sqlElem.name + orderBy;
					else
						sqlValue += "\n       "+ sqlElem.name  + orderBy + ",";
				else
					if(i==(sqlQuery.OrderBy.size()-1))
				       sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName + orderBy;
					else
					   sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName + orderBy + ",";
				
				
				if(sqlElem.name.contains("ds"))
				{
					rptLogInfo.manualChanges.add("ORDER BY ; " + sqlElem.name);
				}

				boolean checkExp = getExpressionFunc(sqlElem.name);
				if(checkExp)
				{
					rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
				}
			}
			  
		}
		return sqlValue;
	}

	private static String fillGroupByCols(SQLQuery sqlQuery, String sqlValue, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId) 
	{
		if(sqlQuery.groupByCols.size() > 0)
		{
			sqlValue += "\n GROUP BY";
			tblMap.add("GROUP BY");
		}
		int cnt = 1;
		for (int i = 0; i < sqlQuery.groupByCols.size(); i++) 
		{
			SQLElement sqlElem = new SQLElement();
			SQLQuery sqlQuery1 = new SQLQuery();
			
			if (sqlQuery.groupByCols.get(i) instanceof SQLQuery)
				sqlQuery1 = (SQLQuery) sqlQuery.groupByCols.get(i);
		
			else if(sqlQuery.groupByCols.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) sqlQuery.groupByCols.get(i);
			
			if(sqlQuery1.dmlType == null)
			{
				
				if (newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.SNOWFLAKE)
				{
					String colName = sqlElem.name;
					if (Integer.toString(cnt).equalsIgnoreCase(sqlElem.name))
						 colName = getColNameFrmColList(i, sqlQuery.colsList, sqlElem.name, sqlQuery.groupByCols.size());//GroupBy 1,2,3
					
					if(i==(sqlQuery.groupByCols.size()-1))
					    sqlValue += "\n       "+ colName;
					else
						sqlValue += "\n       "+ colName + ",";
				}
				else
				{
					if(sqlElem.aliasName == null)
						if(i==(sqlQuery.groupByCols.size()-1))
						    sqlValue += "\n       "+ sqlElem.name;
						else
							sqlValue += "\n       "+ sqlElem.name + ",";
					else
						if(i==(sqlQuery.groupByCols.size()-1))
					       sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName;
						else
						   sqlValue += "\n       "+ sqlElem.name+" " + sqlElem.aliasName+ ",";
				}

				
				if(sqlElem.name.contains("ds"))
				{
					rptLogInfo.manualChanges.add("GROUP BY ; " + sqlElem.name);
				}

				boolean checkExp = getExpressionFunc(sqlElem.name);
				if(checkExp)
				{
					rptLogInfo.totalAttributeTransCount.add(sqlElem.name);
				}
			}
			  cnt++;
		}
		return sqlValue;
	}

	private static String getColNameFrmColList(int i, ArrayList<SQLObject> colsList, String name, int j)
	{
//		System.out.println(colsList.size()+" "+i+""+name);
		
//		if (colsList.size()-1 < i)
		{
			SQLElement sqlElem = new SQLElement();
			
			if(colsList.size() >= j)
			{
				 if(colsList.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) colsList.get(i);
				 
				 if (sqlElem.aliasName == null)
				 {
					 if (!sqlElem.name.equalsIgnoreCase(name))
						 return sqlElem.name;
				 }
				 else
				 {
					 if (!sqlElem.aliasName.equalsIgnoreCase(name))
						 return sqlElem.aliasName;
				 }
			}
	
		}
		return name;
	}

	private static String fillWhereConds(SQLQuery sqlQuery, String sqlValue, ReportLogInfo rptLogInfo, List<String> tblMap, String updateAliasName, String updateTblName, int newDbId, String condTyp,
			ArrayList<Integer> subQryCnt, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts,LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		boolean selfJoinflag = false;
		
		if(sqlQuery.whereCond.args.size() > 0)
		{
			if (!sqlQuery.whereCond.type.equalsIgnoreCase("qualify"))
			{
				sqlValue += "\n "+condTyp;
				
				tblMap.add("WHERE");
			}
				
		}
		
	    for (int i = 0; i < sqlQuery.whereCond.args.size(); i++) 
	    {
	    	SQLExpr sqlExpr = new SQLExpr();
	    	SQLQuery sqlQuery1 = new SQLQuery();
	    	SQLExpr sqlExprVal = new SQLExpr();
	    	if(sqlQuery.whereCond instanceof SQLExpr)
	    		sqlExprVal = (SQLExpr) sqlQuery.whereCond;
	    	if(sqlQuery.whereCond.args.get(i) instanceof SQLExpr)
				sqlExpr = (SQLExpr) sqlQuery.whereCond.args.get(i);
	    	else if(sqlQuery.whereCond.args.get(i) instanceof SQLJoinCond)
	    		sqlQuery1 = (SQLQuery) sqlQuery.whereCond.args.get(i);
	    	
	   	
	    	SQLExpr sqlExprForIn = new SQLExpr();
	    	if(sqlExpr.args.size() > 1)
			{
				if(sqlExpr.args.get(1) instanceof SQLExpr)
					sqlExprForIn = (SQLExpr) sqlExpr.args.get(1);
			}
			
	    	String inVal = "";
			if(sqlExprForIn.value != null)
			{
				inVal = sqlExprForIn.value ; 
			}
			String inVal2 = "";
			SQLExpr sqlExprForInsub = new SQLExpr();
	    	if(sqlExpr.args.size() > 2)
			{
				if(sqlExpr.args.get(2) instanceof SQLExpr)
					sqlExprForInsub = (SQLExpr) sqlExpr.args.get(2);
			}
			
			if(sqlExprForInsub.value != null)
			{
				inVal2 = sqlExprForInsub.value ; 
			}
			

			
	    	if(sqlQuery1.dmlType == null)
			{
	    		if(sqlExpr.value != null)
	    		{
	    			SQLQuery sqlQueryExpr = new SQLQuery();
					if(sqlExpr.args.size() > 0)
						if(sqlExpr.args.get(0) instanceof SQLQuery)
							sqlQueryExpr = (SQLQuery) sqlExpr.args.get(0);
					
					SQLQuery sqlQueryExpr2 = new SQLQuery();
					if(sqlExpr.args.size() > 1)
						if(sqlExpr.args.get(1) instanceof SQLQuery)
							sqlQueryExpr2 = (SQLQuery) sqlExpr.args.get(1);
					
//					if (newDbId == ToolTypeConstants.BIGQUERY || newDbId == ToolTypeConstants.SNOWFLAKE)
					{
						if(sqlQueryExpr2.dmlType == null)
							inVal2 = "";
						
						inVal = "";
					}
					
					if(sqlQuery.whereCond.args.size() > 1)
					{
						if(i > 0) 
						{
							String sqlExprTyp = "";
							if(sqlExpr.type.toUpperCase().contains("WHERE"))
								sqlExprTyp = sqlExpr.type.toUpperCase().substring(sqlExpr.type.toUpperCase().indexOf("WHERE")+5).trim();
							else
								sqlExprTyp = sqlExpr.type.toUpperCase();
							
							if(sqlExprTyp.equalsIgnoreCase("Qualify") && 
									(newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT))
							{
								sqlExprTyp = "";
							}
								
							
							if(sqlExpr.args.size() == 1)
							{
								String exprVal = "";
								
								sqlValue += " "+sqlExprTyp + " " +getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,
										updateAliasName,updateTblName,newDbId,subQryCnt, encloseConfigObjts,prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
							}
							else
							{
								String exprVal = "";
								sqlValue += " "+sqlExprTyp +" "+inVal + " " +getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,
																				subQryCnt, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
								if(sqlQueryExpr2.dmlType != null)
								{
									sqlValue +=" "+inVal2+
											" " + getUpdSQLExprVal(exprVal,sqlQueryExpr2,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,subQryCnt, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
								}		    
							}
						}
						else
						{
							selfJoinflag = chkForSelfJoin (updateAliasName, updateTblName, sqlExpr.value);
							if (selfJoinflag && (newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT))
							{
								sqlValue += "\n EXISTS \n (SELECT * FROM "+updateTblName+" "+updateAliasName+" WHERE";
								updateAliasName = "";
								updateTblName = "";
							}
									
							String sqlExprTyp = "";
							if(sqlExpr.type.contains("EXISTS"))
							{
								if(sqlExpr.type.toUpperCase().contains("AND"))
									sqlExprTyp = sqlExpr.type.toUpperCase().substring(sqlExpr.type.toUpperCase().indexOf("AND")+3).trim();
							}
							if(sqlExprTyp.equalsIgnoreCase("Qualify") && (newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT))
							{
								sqlExprTyp = "";
							}
								
							String exprVal = "";
							sqlValue +=" "+ sqlExprTyp + " " +inVal + " " + getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,subQryCnt,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
											
							
							if(sqlQueryExpr2.dmlType != null)
							{
								sqlValue +=" "+inVal2+
										" " + getUpdSQLExprVal(exprVal,sqlQueryExpr2,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,subQryCnt,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
							}
						}
						/*if(i==(sqlQuery.whereCond.args.size()-1))  
							sqlValue += "\n       "+ sqlExpr.value;
						else
							sqlValue += "\n       "+ sqlExpr.value + " AND ";*/
					}
					else
					{
						
						//sqlValue += "\n       "+ sqlExpr.value;
						String exprVal = "";
						String sqlExprTyp = "";
						if(sqlExpr.type.toUpperCase().contains("WHERE"))
							sqlExprTyp = sqlExpr.type.toUpperCase().substring(sqlExpr.type.toUpperCase().indexOf("WHERE")+5).trim();
						else
							sqlExprTyp = sqlExpr.type.toUpperCase();
						
						if(sqlExprTyp.equalsIgnoreCase("Qualify") && (newDbId == ToolTypeConstants.AZURESQL || newDbId == ToolTypeConstants.REDSHIFT))
						{
							sqlExprTyp = "";
						}
						
						if(sqlExprTyp.equalsIgnoreCase("AND")|| sqlExprTyp.equalsIgnoreCase("EXISTS") || sqlExprTyp.equalsIgnoreCase("NOT EXISTS"))
							sqlExprTyp = "";
							
							
						if(sqlExprTyp.length() > 0)
						{
							sqlValue +=" "+sqlExprTyp+" "+getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,subQryCnt, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
						}
						else
						{
							sqlValue += inVal + " " +getUpdSQLExprVal(exprVal,sqlQueryExpr,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,subQryCnt, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
							
							if(sqlQueryExpr2.dmlType != null)
							{
								sqlValue +=	" "+inVal2+
										" " + getUpdSQLExprVal(exprVal,sqlQueryExpr2,sqlQuery1,sqlExpr,rptLogInfo,tblMap,updateAliasName,updateTblName,newDbId,subQryCnt, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList);
							}
						}
						
					}
	    		}
	    		
			}
	    	else
	    	{
				if(sqlQuery.whereCond.args.size() > 1)	
				{
					if(i==(sqlQuery.whereCond.args.size()-1))
					{
						if(sqlQuery1.aliasName != null)
				            sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
						else
							sqlValue += "("+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
					}
					else
						if(sqlQuery1.aliasName != null)
						    sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName+ " AND ";
						else
							sqlValue += "("+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" "+ " AND ";
				}
				else
				{
					
					if(sqlQuery1.aliasName != null)
					   sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +sqlQuery1.aliasName;
					else
					   sqlValue += "("+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
				}
				if (subQryCnt != null)
					subQryCnt.add(1);
	    	}
		}
	    if (selfJoinflag && newDbId == ToolTypeConstants.AZURESQL)
	    	sqlValue += ")";

		return sqlValue;
		
	}

	private static boolean chkForSelfJoin(String updateAliasName, String updateTblName, String value) 
	{
		if (value.contains("="))
		{
			String temp[] = value.split("=");
			if (temp[0].contains(".") && temp[1].contains("."))
			{
				String tabName = temp[0].substring(0, temp[0].indexOf(".")).trim();
				String aliasNam = temp[1].substring(0, temp[1].indexOf(".")).trim();
				if (updateTblName.contains("."))
					updateTblName = updateTblName.substring(0, updateTblName.indexOf(".")).trim();
				if (tabName.equalsIgnoreCase(updateTblName) && aliasNam.equalsIgnoreCase(updateAliasName.trim()))
					return true;
			}
		}
		return false;
	}

	private static String getUpdateValForQualify(String value, ArrayList<String> qualifyCond, boolean chkFlag)
	{
		ArrayList<Token> tkn = FunctionTypeConv.getTokens(value);
		if (chkFlag)
		{
			if(tkn.size() > 2)
			{
			
			    String condIndex = tkn.get(tkn.size()-2).data;
			    String valIndex = tkn.get(tkn.size()-1).data;
				qualifyCond.add(condIndex+" "+valIndex);
			}
		}
		else
		{
			String tmpVal = "", tmpExpr = "";
			int cnt = 0; 
			for(int j = 0 ; j < tkn.size(); j++)
			{
				Token tknVal = tkn.get(j);
				if (j < tkn.size()-2)
					tmpVal += " "+tknVal.data;
				else
				{
					if (cnt > 0)
						continue;
					value = value.substring(0, value.indexOf(tknVal.data));
				   cnt++;
				}
			}
		}
     
		return value;
	}

	private static String getUpdSQLExprVal(String exprVal, SQLQuery sqlQueryExpr, SQLQuery sqlQuery1, SQLExpr sqlExpr, ReportLogInfo rptLogInfo, List<String> tblMap, String updateAliasName, String updateTblName, int newDbId, ArrayList<Integer> subQryCnt,
			LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		if(sqlQueryExpr.dmlType != null)
		{
			
			String SQLVal = "";
//			if(!sqlExpr.value.startsWith("("))
//			{
				if(sqlExpr.value.contains("("))
				{
					String exprVals = updatedStmts(sqlExpr.value, "select");
					SQLVal = exprVals.substring(0,exprVals.indexOf("( SELECT"));
					SQLVal = getUpdatedColName(SQLVal);
				}
//			}
			if(SQLVal.length() > 0)
			{
				if(!SQLVal.contains("EXISTS"))
				{
					exprVal += "\n      "+ SQLVal;
				}
			}
			if(sqlQuery1.aliasName != null)
			{
				exprVal += fillSelectStmts(sqlQueryExpr,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQueryExpr.aliasName;
			}
			else
			{
				exprVal += fillSelectStmts(sqlQueryExpr,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
			}
			if (subQryCnt != null)
				subQryCnt.add(1);
		}
		else
		{
			if(!updateAliasName.equalsIgnoreCase(""))
			{
				String updateCondVal = sqlExpr.value;
				String updatedString = "";
				ArrayList<Token> tkn = FunctionTypeConv.getTokens(updateCondVal);
				for(int i = 0 ; i < tkn.size() ;i++)
				{
					Token tknVal = tkn.get(i);
					if(tknVal.type.name().equalsIgnoreCase("VARWITHDOT"))
					{
						if(!updateAliasName.equalsIgnoreCase(""))
						{
							String tmp = tknVal.data.substring(0, tknVal.data.indexOf(".")).trim();
							String tmpVal = tknVal.data.substring(tknVal.data.indexOf(".")+1, tknVal.data.length());
							
							if(tmp.equalsIgnoreCase(updateAliasName.trim()))
							{
//								updatedString += " "+ tknVal.data.replace(updateAliasName, updateTblName);
								updatedString += " "+ updateTblName + "."+ tmpVal;
							}
							else
							{
								updatedString += " "+ tknVal.data;
							}
						}
					}
					else
					{
						updatedString += " "+ tknVal.data;
					}
				}
				updatedString = getUpdatedColName(updatedString);
				exprVal += "\n       "+ updatedString;
			}
			else
			{
				
				exprVal += "\n       "+ sqlExpr.value;
			}
			
			if(sqlExpr.value.contains("ds"))
			{
				rptLogInfo.manualChanges.add("WHERE ; " + sqlExpr.value);
			}

			boolean checkExp = getExpressionFunc(sqlExpr.value);
			if(checkExp)
			{
				rptLogInfo.totalAttributeTransCount.add(sqlExpr.value);
			}
		}

		return exprVal;
	}
	
	private static String updatedStmts(String sqlStmts, String checkVal) {
		ArrayList<Token> tkn = FunctionTypeConv.getTokens(sqlStmts);
		String updVal = "";
		for (int i = 0; i < tkn.size(); i++) {
			Token tknData = tkn.get(i);
			if (tknData.data.equalsIgnoreCase(checkVal)) {
				tknData.data = tknData.data.toUpperCase();

				updVal += " " + tknData.data;
			} else
				updVal += " " + tknData.data;
		}

		return updVal.trim();
	}


	

	private static String getTableSQLVal(SQLQuery sqlQuery, String sqlValue, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, 
			boolean checkTable, boolean checkViews, boolean checkTgtTable,ArrayList<String> uniqueAliasList, ArrayList<String> mergeTblNameList) 

	{
		String quoteType = "",prefixWith = "";
		boolean quoteTbl = false, prefixTbl = false, replaceTbl = false, quoteView = false, prefixView = false;
		if (encloseConfigObjts != null)
		{
			quoteType = encloseConfigObjts.get("QuotesType");
			if (encloseConfigObjts.get("TableNames") != null )
				quoteTbl = true;
			if( encloseConfigObjts.get("ViewNames") != null)
				quoteView = true;
			
		}
		if (prefixConfigObjts != null)
		{
			prefixWith = prefixConfigObjts.get("Prefix_With");
			if (prefixConfigObjts.get("TableNames") != null )
				prefixTbl = true; 
			if( prefixConfigObjts.get("ViewNames") != null)
				prefixView = true;
			
		}
		if(!replaceSchemaConfigObjts.isEmpty() || replaceSchemaConfigObjts.size() > 0)
		{
			replaceTbl = true;
		}
		
		if(sqlQuery.tableList.size() > 0)
		{
			if(!(sqlQuery.dmlType.equalsIgnoreCase("DROP") || sqlQuery.dmlType.equalsIgnoreCase("TRUNCATE") 
					|| sqlQuery.dmlType.equalsIgnoreCase("MERGE") || sqlQuery.dmlType.equalsIgnoreCase("REPLACE VIEW") 
					|| sqlQuery.dmlType.equalsIgnoreCase("CREATE VIEW") || sqlQuery.dmlType.equalsIgnoreCase("CREATE OR REPLACE VIEW")))
			{
				if(sqlQuery.hiddenSQL == false)
					sqlValue += "\n FROM";
			}
			for (int i = 0; i < sqlQuery.tableList.size(); i++) 
			{
				SQLElement sqlElem = new SQLElement();
				SQLQuery sqlQuery1 = new SQLQuery();
				
				if (sqlQuery.tableList.get(i) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) sqlQuery.tableList.get(i);
			
				else if(sqlQuery.tableList.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) sqlQuery.tableList.get(i);
				
				
				if(sqlQuery1.dmlType == null)
				{
					tblMap.add(sqlElem.name);
//					String elemName = sqlElem.name;
//					
//					if (prefixTbl)
//					{
//						elemName = prefixWith+"."+elemName;
//					}
//					if(quoteTbl)
//					{
//						quoteType = getQuoteType(quoteType);
//						elemName = quoteType+elemName+quoteType;
//					}
					
					String elemName = getUpdatedTableName(sqlElem, quoteType, quoteTbl, prefixTbl, prefixWith, replaceTbl,
							replaceSchemaConfigObjts,checkTable, checkViews, quoteView, prefixView, checkTgtTable);
						
					if(sqlElem.aliasName == null)
					{
//						sqlValue += "\n       "+ sqlElem.name;
						if(sqlQuery.tableList.size() > 1)
						{
							if(i==(sqlQuery.tableList.size()-1))
							  sqlValue += "\n       "+ elemName;
							else
							  sqlValue += "\n       "+ elemName+",";
						}
						else
							sqlValue += "\n       "+ elemName;
					}
					else
					{
						if(mergeTblNameList != null)
						{
							mergeTblNameList.add(sqlElem.aliasName + ";" +elemName);
							
							sqlValue += "\n       "+ elemName;
						}
						else
						{
							if(sqlQuery.dmlType.equalsIgnoreCase("DROP") || sqlQuery.dmlType.equalsIgnoreCase("TRUNCATE") 
									|| sqlQuery.dmlType.equalsIgnoreCase("DELETE"))
							{
								if(newDbId == ToolTypeConstants.AZURESQL)
								{
									if (sqlElem.name.endsWith("."))
										elemName = elemName + sqlElem.aliasName;
	
									sqlElem.aliasName = "";
								}
							}
							if(sqlQuery.tableList.size() > 1)
							{
								if(i==(sqlQuery.tableList.size()-1))
								  sqlValue += "\n       "+ elemName+" " + sqlElem.aliasName;
								else
								  sqlValue += "\n       "+ elemName+" " + sqlElem.aliasName+",";
							}
							else
								sqlValue += "\n       "+ elemName+" " + sqlElem.aliasName;
						
						}
						
					}
				}
				else
				{
					tblMap.add("SQLQueryTable");
					if(sqlQuery.tableList.size() > 1)
						if(i==(sqlQuery.tableList.size()-1))
						{
							if(sqlQuery1.aliasName != null)
							  sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
							else
							  sqlValue += "("+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
						}
						else
						{   
							if(sqlQuery1.aliasName != null)
							   sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName+",";
							else
							   sqlValue +="("+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")"+",";
						}
					else
						if(sqlQuery1.aliasName != null)
						   sqlValue += fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +sqlQuery1.aliasName;
						else
						   sqlValue += "("+ fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
				}
				
			}
		}
		return sqlValue;
	}
	
	private static String getQuoteType(String quoteType) 
	{
		if (quoteType.equalsIgnoreCase("apostrophe"))
			return "`";
		if (quoteType.equalsIgnoreCase("Single"))
			return "'";
		if (quoteType.equalsIgnoreCase("Double"))
			return "\"";
		if (quoteType.equalsIgnoreCase("None"))
			return "";
		return "";
	}

	private static  String getUpdatedColName(String colName) 
	{
		String ColName = colName.replace("( ", "(").replace(" (", "(").replace(" )", ")")
					.replace("[ ", "[").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
					.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").trim();
		
		
		return ColName;
	}



}