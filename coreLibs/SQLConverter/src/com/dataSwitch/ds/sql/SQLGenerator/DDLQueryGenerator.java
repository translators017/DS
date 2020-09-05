
package com.dataSwitch.ds.sql.SQLGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.sql.SQLObjeds.DDLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.SQLColstruct;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;

public class DDLQueryGenerator 
{
	static String fillCreateDDLStmts(SQLQuery sqlobjts, SQLValue sqlVal, ReportLogInfo rptLogInfo, List<String> tblMap, int newDbId, LinkedHashMap<String, String> encloseConfigObjts, LinkedHashMap<String, String> prefixConfigObjts, LinkedHashMap<String, LinkedHashMap<String, String>> replaceSchemaConfigObjts, ArrayList<String> uniqueAliasList) 
	{
		String sqlValue ="";
		sqlValue += "CREATE ";//TABLE";
		String fromTableList = "";
		String updateTable = "";
		
		DDLQuery ddlQry = new DDLQuery();
		if (sqlobjts.ddlQuery instanceof DDLQuery)
			ddlQry = sqlobjts.ddlQuery;
		
		if (ddlQry.tableType != null)
		{
			if (newDbId == ToolTypeConstants.SNOWFLAKE && ddlQry.tableType.equalsIgnoreCase("volatile"))
				sqlValue += "Temporary" + " TABLE ";
			else
				sqlValue += ddlQry.tableType + " TABLE ";
		}
		else
			sqlValue += " TABLE ";
		
//		for (int i = 0; i < sqlobjts.tableList.size(); i++) 
		{
			SQLElement sqlElem = new SQLElement();
			SQLQuery sqlQuery = new SQLQuery();
			if (ddlQry.tableName instanceof SQLElement)
				sqlElem = (SQLElement) ddlQry.tableName;
		
//			else if(sqlobjts.tableList.get(0) instanceof SQLElement)
//				sqlElem = (SQLElement) sqlobjts.tableList.get(0);
//			if(i == 0)
			{
				String elemName = QueryGenerator.getUpdatedTableFromConfig((SQLElement) ddlQry.tableName, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
				
				if(sqlQuery.dmlType == null)
				{
					if(sqlElem.aliasName == null)
						updateTable += "\n       "+ elemName;
					else
						updateTable += "\n       "+ elemName+" " + sqlElem.aliasName;
				}
				tblMap.add("CREATE ; "+elemName);
			}
			
			
//			if(i >= 1)
			{
				String fromTable = "";
				SQLElement sqlElem2 = new SQLElement();
				SQLQuery sqlQuery1 = new SQLQuery();
				
//				if (sqlobjts.tableList.get(i) instanceof SQLQuery)
//					sqlQuery1 = (SQLQuery) sqlobjts.tableList.get(i);
			
//				else if(sqlobjts.tableList.get(i) instanceof SQLElement)
//					sqlElem2 = (SQLElement) sqlobjts.tableList.get(i);
				
				
//				if(sqlQuery1.dmlType == null)
//				{
//					if(sqlElem2.name.equalsIgnoreCase("DEFAULT"))
//						sqlElem2.name = "";
//					
//					String elemName  = "";
//					if(!sqlElem2.name.equalsIgnoreCase("DEFAULT"))
//					{
//						if(sqlElem2.name.length() > 0)
//						{
//							elemName = QueryGenerator.getUpdatedTableFromConfig(sqlElem2, encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts);
//						}
//					}
//					
//					tblMap.add(elemName);
//					
//					if(sqlElem2.aliasName == null)
//						fromTable += "\n       "+ elemName;
//					else
//						if(sqlQuery.tableList.size() > 1)
//						{
//							if(i==(sqlobjts.tableList.size()-1))
//								fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName;
//							else
//								fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName+",";
//						}
//						else
//							fromTable += "\n       "+ elemName+" " + sqlElem2.aliasName;
//				}
//				
//				else
//				{
//					tblMap.add("SQLQueryTable");
//					if(sqlobjts.tableList.size() > 1)
//					{
//						if(i==(sqlobjts.tableList.size()-1))
//						{
//							if(sqlQuery1.aliasName != null)
//								fromTable += QueryGenerator.fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName;
//							else
//								fromTable += QueryGenerator.fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
//						}
//						else
//						{
//							if(sqlQuery1.aliasName != null)
//								fromTable += QueryGenerator.fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" +" " +sqlQuery1.aliasName+",";
//							else
//								fromTable += QueryGenerator.fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")";
//						}
//					}
//					else
//					{
//						if(sqlQuery1.aliasName != null)
//							fromTable += QueryGenerator.fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" + " " +sqlQuery1.aliasName;
//						else
//							fromTable += QueryGenerator.fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
//					}
//				}
				
				fromTableList += fromTable;
				
			}
		}
		if(updateTable.length() > 0)
		{
			sqlValue += updateTable;
		}
		if(fromTableList.trim().length() > 0)
		{

			if(!fromTableList.contains("SELECT"))
			{
				sqlValue += "\n )";
			}
		}
		if(ddlQry.colsList.size() > 0)
		{
//			sqlValue += "\n ( ";
			for (int j = 0; j < ddlQry.colsList.size(); j++) 
			{
				SQLColstruct sqlCons = new SQLColstruct();
				SQLQuery sqlQuery1 = new SQLQuery();
				
				if (ddlQry.colsList.get(j) instanceof SQLQuery)
					sqlQuery1 = (SQLQuery) ddlQry.colsList.get(j);
			
				else if(ddlQry.colsList.get(j) instanceof SQLColstruct)
					sqlCons = (SQLColstruct) ddlQry.colsList.get(j);
				
				if(sqlCons.colName != null)
				{
					if (j == 0)
						sqlValue += "\n ( ";
					
					String dataType = "",defaultVal = "", nullType = "";
					if (sqlCons.dataType != null)
					{
						dataType = sqlCons.dataType;
						if (sqlCons.length_precision != null && sqlCons.length_precision.length() > 0)
							dataType += "(" + sqlCons.length_precision;
						if (sqlCons.Scale != null && sqlCons.Scale.length() > 0)
							dataType += "," + sqlCons.Scale;
						
						if (sqlCons.length_precision.length() > 0 || sqlCons.Scale.length() > 0)
							dataType += ")";
						if (sqlCons.nullType != null)
							nullType = sqlCons.nullType;
						
						if(newDbId == ToolTypeConstants.BIGQUERY)
						{
							dataType = QueryGenerator.getUpdatedDataTypeForBGQuery(dataType);
						}
						dataType += nullType;
					}
					if (sqlCons.defaultVal != null && sqlCons.defaultVal.length() > 0)
						defaultVal += sqlCons.defaultVal;
					
					if(j==(ddlQry.colsList.size()-1))
				       sqlValue += "\n       "+ sqlCons.colName+" " + dataType + defaultVal;
					else
					   sqlValue += "\n       "+ sqlCons.colName+" " + dataType + defaultVal + ",";
					
					if(j==(ddlQry.colsList.size()-1))
						sqlValue += "\n ) ";
				}

				if (sqlQuery1 != null)
				{
					if (sqlQuery1.dmlType != null)
					{
						if (sqlQuery1.dmlType.equalsIgnoreCase("WITH TEMPORARY TABLE"))
						{
							sqlValue += "\n AS";
							sqlValue += "\n (";
							SQLValue sqlValTmp = new SQLValue();
							sqlValue += QueryGenerator.fillWithTempStatements(sqlQuery1,sqlValTmp,rptLogInfo,tblMap,newDbId,encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, uniqueAliasList)+ ")" ;
						}
						else if (sqlQuery1.dmlType.equalsIgnoreCase("select"))
						{
							if(newDbId == ToolTypeConstants.AZURESQL)
							{
								sqlValue += "\n WITH" ;
								sqlValue +="\n ("  ; 
								sqlValue += "\n    CLUSTERED COLUMNSTORE INDEX,"  ;
								sqlValue += "\n    DISTRIBUTION = ROUND_ROBIN " ;
								sqlValue += "\n  )";
							}
							sqlValue += "\n AS";
							sqlValue += "\n (";
							sqlValue += QueryGenerator.fillSelectStmts(sqlQuery1,rptLogInfo,tblMap,newDbId,"",encloseConfigObjts, prefixConfigObjts, replaceSchemaConfigObjts, null, uniqueAliasList) + ")" ;
						}
					}
				}
			}
			
//			sqlValue += "\n ) ";
		}
		return sqlValue;
	}
}