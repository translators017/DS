
package com.dataSwitch.ds.sql.SQLParser;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLGenerator.QueryGenerator;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.DDLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.ExternalTable;
import com.dataSwitch.ds.sql.SQLObjeds.SQLColstruct;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoin;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoinCond;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.SQLUnion;
import com.dataSwitch.ds.sql.SQLObjeds.UpdateElement;


public class SQLStatement 
{

	public static void getSQLQueryObject(ArrayList<Token> tokens, String inputSQL, SQLQuery sqlObj, int currentDBId, int newDBId) 
	{
		if(sqlObj.dmlType.equalsIgnoreCase("MERGE"))
		{
			inputSQL = updatedMergeStmt(inputSQL);
		}
		
		String StartINTO,endSelect,nodeTyp;
		StartINTO = "(";
		endSelect = ")";
		nodeTyp = "ds__QueryNode";
		HashMap<String,String> sqlObjts = SQLUtils.getStmtNodes(StartINTO, endSelect, tokens,nodeTyp,inputSQL); //getSQLObjectList;
		String sqlStmts = getInsrtStmt(sqlObjts);
		processSQLStmt(sqlStmts, tokens, sqlObjts, sqlObj, currentDBId, newDBId);
	}
	
	private static String updatedMergeStmt(String inputSQL) 
	{
		 String when =  updatedStmt(inputSQL, "when");
		 String not =  updatedStmt(when, "not");
		 String matched =  updatedStmt(not, "matched");
		 String mergeThen =  updatedStmt(matched, "then");
		 
		 if(mergeThen.contains(" WHEN MATCHED THEN ") || mergeThen.contains(" WHEN NOT MATCHED THEN "))
		 {
			 mergeThen = mergeThen.replace(" WHEN MATCHED THEN ", " ").replace(" WHEN NOT MATCHED THEN ", " ");
		 }
		 
		return mergeThen;
	}

	public static void processSQLStmt(String sqlStmts, ArrayList<Token> tokens, HashMap<String, String> sqlObjts, SQLQuery sqlObj, int currentDBId, int newDBId)
	{
		ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(sqlStmts);
		
		boolean checkUnionStmt = checkValues(sqlTkns,"Union");
		boolean checkMinusStmt = checkValues(sqlTkns,"Minus");
		boolean countSelect = checkCountSelect(sqlTkns);
		String updStmt = ""; 
		
		if(sqlObj.dmlType.equalsIgnoreCase("CALL PROC/FUNC"))
		{
		     processCallProcStmts(sqlTkns, sqlStmts, sqlObj, currentDBId, newDBId, tokens, sqlObjts);
		}
		else
		{
			if((checkUnionStmt == false || checkMinusStmt == false) && countSelect == false && (!(sqlObj.dmlType.equalsIgnoreCase("WITH TEMPORARY TABLE") || 
					sqlObj.dmlType.equalsIgnoreCase("REPLACE VIEW") || sqlObj.dmlType.equalsIgnoreCase("CREATE VIEW") || sqlObj.dmlType.equalsIgnoreCase("CREATE OR REPLACE VIEW"))))
			{
				for(Entry<Token,Token> entry : getKeywordClauseForStmts(sqlStmts,sqlTkns).entrySet())
				{
					String StartVal = entry.getKey().data;
					String endVal = entry.getValue().data;
					String nodeTyp = "ds__QueryNode";
					HashMap<String,String> sqlNodeList = SQLUtils.getStmtNodes(StartVal, endVal, tokens,nodeTyp,sqlStmts);
					
					if(StartVal.equalsIgnoreCase("insert") || (StartVal.equalsIgnoreCase("into") && (!sqlObj.dmlType.equalsIgnoreCase("select")))  || StartVal.equalsIgnoreCase("update")
							|| ( (StartVal.equalsIgnoreCase("table") || (StartVal.equalsIgnoreCase("create") && endVal.equalsIgnoreCase("table") )) &&  (sqlObj.dmlType.equalsIgnoreCase("Create") || sqlObj.dmlType.equalsIgnoreCase("Truncate") ||  sqlObj.dmlType.equalsIgnoreCase("Drop"))))
					{
						for(Entry<String,String> nodeEntry : sqlNodeList.entrySet())
						{
						    String sqlStmt = nodeEntry.getValue();
							String colListName = sqlStmt.substring(sqlStmt.indexOf(StartVal)+StartVal.length(), sqlStmt.lastIndexOf(endVal)).trim();
						
							
							if(StartVal.equalsIgnoreCase("into") || StartVal.equalsIgnoreCase("update") ||  StartVal.equalsIgnoreCase("table"))
							{
								if(!sqlObj.dmlType.equalsIgnoreCase("INSERT"))
								{
									if(colListName.contains(","))
									{
										String[] colsName = colListName.split(",");
										
										 for (String colName : colsName) 
										 {
											 processTblStmts(colName,sqlObjts,tokens,sqlObj,currentDBId,newDBId);
										 }
									}
									else
									{
										if(colListName.length() > 0)
										{
											processTblStmts(colListName,sqlObjts,tokens,sqlObj,currentDBId,newDBId);
										}
									}
								}
								else
								{
									if(colListName.length() > 0)
									{
										processTblStmts(colListName,sqlObjts,tokens,sqlObj,currentDBId,newDBId);
									}
								}
							}
							else
							{  
								if(sqlObj.dmlType.equalsIgnoreCase("MERGE"))
									fillExternalTableColsForMerge(colListName, sqlObj , sqlObjts, tokens, currentDBId, newDBId);
								
								else if(StartVal.equalsIgnoreCase("create") && endVal.equalsIgnoreCase("table"))
								{
									if(colListName.length() > 0)
									{
										DDLQuery ddlQuery = new DDLQuery();
										ddlQuery.tableType = colListName;
										sqlObj.ddlQuery = ddlQuery;
									}
								}
							}
						}
					}
					
					else 
					{  
						if(!sqlNodeList.isEmpty())
						{ 
							for(Entry<String,String> nodeEntry : sqlNodeList.entrySet())
							{
								if(entry.getValue().data.equalsIgnoreCase(";"))
									updStmt += nodeEntry.getValue()+" ";
								else
									updStmt += nodeEntry.getValue().substring(0, nodeEntry.getValue().lastIndexOf(endVal))+" ";
								
							}
							
						}
						else
						{
							String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),sqlTkns).trim();
							updStmt += value.substring(0, value.lastIndexOf(entry.getValue().data)) +" ";
						}
					}
				}
			}
			
			else
			{
				updStmt = sqlStmts.substring(sqlStmts.indexOf("(")+1, sqlStmts.lastIndexOf(")"));
				
				if(sqlObj.dmlType.equalsIgnoreCase("WITH TEMPORARY TABLE"))
				{
					processExternalTable(updStmt, sqlObj, sqlObjts, currentDBId, newDBId);
				}
				
				if(sqlObj.dmlType.equalsIgnoreCase("REPLACE VIEW") || sqlObj.dmlType.equalsIgnoreCase("CREATE VIEW") || sqlObj.dmlType.equalsIgnoreCase("CREATE OR REPLACE VIEW"))
				{
					processCreateOrReplaceView(updStmt, sqlObj, sqlObjts, currentDBId, newDBId);
				}
				
			}
			
			boolean checkInsrtStmtsHasValues = checkValues(tokens,"Values");
			boolean checkInsrtStmts = checkValues(tokens,"Insert");
			boolean checkSelectStmts = checkValues(tokens,"Select");
			boolean checkUpdateSetStmts = checkValues(tokens,"update");
			boolean checkUpdateDeleteStmts = checkValues(tokens,"delete");
			boolean checkUpdateDropStmts = checkValues(tokens,"drop");
			boolean checkUpdateTruncateStmts = checkValues(tokens,"truncate");
			
			 if(!(sqlObj.dmlType.equalsIgnoreCase("Create") || sqlObj.dmlType.equalsIgnoreCase("WITH TEMPORARY TABLE") || 
					 sqlObj.dmlType.equalsIgnoreCase("REPLACE VIEW") || sqlObj.dmlType.equalsIgnoreCase("CREATE VIEW") || sqlObj.dmlType.equalsIgnoreCase("CREATE OR REPLACE VIEW") ))
			 {
				if(checkInsrtStmts && checkUpdateSetStmts)
				{
					if(checkInsrtStmts)
					{
						 String matched =  updatedStmt(updStmt, "values").trim();
						 String valLength = "VALUES";
						 String valuesDtls = matched.substring(matched.indexOf(valLength)+valLength.length(), matched.length()-1);
						 getValuesForInsrtStmts(valuesDtls,tokens,sqlObjts,sqlObj,currentDBId,newDBId);
						
					}
					if(checkUpdateSetStmts)
					{
						getUpdateDeleteClauseStmts(updStmt,tokens,sqlObjts,sqlObj,currentDBId,newDBId);
					}
				}
				else if(checkInsrtStmts == true)
				{
					if(checkInsrtStmtsHasValues == false)
					{
						String aliasName = null;
						if(updStmt.length() > 0)
						{
							if(sqlObj.tableList.size() == 0)
							{
								updStmt =  updatedStmt(updStmt, "select").trim();
								String insertTableStmt = updStmt.substring(0, updStmt.indexOf("SELECT"));
								for(Entry<Token,Token> entry : getKeywordClauseForStmts(insertTableStmt,sqlTkns).entrySet())
								{
									String StartVal = entry.getKey().data;
									String endVal = entry.getValue().data;
									String nodeTyp = "ds__QueryNode";
									HashMap<String,String> sqlNodeList = SQLUtils.getStmtNodes(StartVal, endVal, tokens,nodeTyp,sqlStmts);
									
									if(StartVal.equalsIgnoreCase("into"))
									{
										for(Entry<String,String> nodeEntry : sqlNodeList.entrySet())
										{
										    String sqlStmt = nodeEntry.getValue();
											String colListName = sqlStmt.substring(sqlStmt.indexOf(StartVal)+StartVal.length(), sqlStmt.lastIndexOf(endVal)).trim();
											if(StartVal.equalsIgnoreCase("into"))
											{
												 processTblStmts(colListName,sqlObjts,tokens,sqlObj,currentDBId,newDBId);
											}
										}
									}
								}
								
								updStmt = updStmt.substring(updStmt.indexOf("SELECT"), updStmt.length());
								
							}
							
							sqlObj.colsList.add(processSelectStmt(updStmt, tokens, sqlObjts, aliasName,currentDBId,newDBId,false));
						}
					}
					else
					{
						getValuesForInsrtStmts(updStmt,tokens,sqlObjts,sqlObj,currentDBId,newDBId);
					}
				}
				else if(checkUpdateSetStmts == true || checkUpdateDeleteStmts == true || checkUpdateDropStmts || checkUpdateTruncateStmts)
				{
					getUpdateDeleteClauseStmts(updStmt,tokens,sqlObjts,sqlObj,currentDBId,newDBId);
				}
				
				else
				{
					if(sqlObj.dmlType.equalsIgnoreCase("Select"))
					{
						 if(checkSelectStmts == true)
						 {
							String aliasName = null;
							SQLQuery sqlObjt = processSelectStmt(updStmt, tokens, sqlObjts, aliasName,currentDBId,newDBId,false);
							setObjtForsqlObjt(sqlObj,sqlObjt);
						 } 
					}
					else if(sqlObj.dmlType.equalsIgnoreCase("grant"))
					{
						SQLElement sqlElem =  new SQLElement();
						sqlElem.name = updStmt.substring(updStmt.indexOf("grant")+6, updStmt.indexOf("to"));
						sqlObj.colsList.add(sqlElem);
						
						sqlElem =  new SQLElement();
						sqlElem.name = updStmt.substring(updStmt.indexOf("to")+3, updStmt.indexOf(";"));
						sqlObj.tableList.add(sqlElem);
					}
				}
			}
		}
		
	}

	private static void processCallProcStmts(ArrayList<Token> sqlTkns, String sqlStmts, SQLQuery sqlObj,
			int currentDBId, int newDBId, ArrayList<Token> tokens, HashMap<String, String> sqlObjts)
	{
		for(Entry<Token,Token> entry : getKeywordClauseForStmts(sqlStmts,sqlTkns).entrySet())
		{
			String StartVal = entry.getKey().data;
			String endVal = entry.getValue().data;
			String nodeTyp = "ds__QueryNode";
			HashMap<String,String> sqlNodeList = SQLUtils.getStmtNodes(StartVal, endVal, tokens,nodeTyp,sqlStmts);
			
			if(StartVal.equalsIgnoreCase("call"))
			{
				for(Entry<String,String> nodeEntry : sqlNodeList.entrySet())
				{
				    String sqlStmt = nodeEntry.getValue();
					String colListName = sqlStmt.substring(sqlStmt.indexOf(StartVal)+StartVal.length(), sqlStmt.lastIndexOf(endVal)).trim();
					
					ArrayList<Token> tknVal = FunctionTypeConv.getTokens(colListName);
					
					String funcWithArgs = getUpdatedColName(sqlObjts, colListName) ;
					SQLExpr sqlExprCond = new SQLExpr();
					sqlExprCond.type = "CALL Proc/Func";
					sqlExprCond.value = funcWithArgs;
					
					for(int i = 0 ; i < tknVal.size() ; i++)
					{
						if(i == 0 && tknVal.get(0).data.length() > 0)
						{
							sqlExprCond.aliasName = tknVal.get(0).data;
						}
						else
						{
							if(tknVal.get(i).data.contains("ds__QueryNode_") && tknVal.get(i).type.name().equalsIgnoreCase("var"))
							{
								  String argsVal = sqlObjts.get(tknVal.get(i).data);
								  argsVal = argsVal.substring(argsVal.indexOf("(")+1, argsVal.lastIndexOf(")"));
								  
								  ArrayList<Token> tkn = FunctionTypeConv.getTokens(argsVal);
									
									boolean checkDecSep = checkValues(tkn, ",");
									if(checkDecSep == true)
									{
										for(Entry<Token,Token> entryVal : getKeywordDecSep(argsVal, tkn).entrySet())
										{
											String value = getDecSepValues(entryVal, tkn);
											if(value.length() > 0)
											{
												SQLExpr sqlExpr = new SQLExpr();
												String argsValue = getUpdatedColName(sqlObjts, value) ;
												sqlExpr.value = argsValue;
												sqlExprCond.args.add(sqlExpr);
											}
											
										}
										
									}
									else
									{
										SQLExpr sqlExpr = new SQLExpr();
										String argsValue = getUpdatedColName(sqlObjts, argsVal) ;
										sqlExpr.value = argsValue;
										sqlExprCond.args.add(sqlExpr);
									}
							}
						}
					}
					
					sqlObj.callProcOrFunc = sqlExprCond;
					
				}
				
			}
		}
	}

	private static String getDecSepValues(Entry<Token, Token> entry, ArrayList<Token> tkn) 
	{
		String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
		String decSepVal = entry.getKey().data;
		String decSepEndVal = entry.getValue().data;
		if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
			value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
		else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
			value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
		else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
			value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
		
		
		return value;
		
	}

	private static void processCreateOrReplaceView(String updStmt, SQLQuery sqlObj, HashMap<String, String> sqlObjts,
			int currentDBId, int newDBId) 
	{
		String replaceStmt =  updatedStmt(updStmt, "Replace");
		String viewStmt =  updatedStmt(replaceStmt, "view");
		String aliasValue = updatedStmt(viewStmt, "as");
		String selectValue = updatedStmt(aliasValue, "select");
		String createValue = updatedStmt(selectValue, "create");
		ArrayList<Token> tokenVal = FunctionTypeConv.getTokens(createValue);
   	  	boolean checkCreateAlias = checkValues(tokenVal, "as");
   	  	
   	  	if(checkCreateAlias)
   	  	{
   	  		String replaceView = " VIEW ";
   	  		String alias = " AS ";
   	  		String select = " SELECT";
   	  		String getTableVal = selectValue.substring(selectValue.indexOf(replaceView)+replaceView.length(), selectValue.indexOf(alias));
   	  		String aliasQueryTable = selectValue.substring(selectValue.indexOf(alias)+alias.length(), selectValue.length());
   	  		String selectTable = "";
   	  		
   	  		if(!aliasQueryTable.startsWith("ds__QueryNode"))
   	  		{
	   	  		if(!aliasQueryTable.startsWith("SELECT"))
	   	  			selectTable = aliasQueryTable.substring(aliasQueryTable.indexOf(select), aliasQueryTable.length());
	   	  		else
	   	  		{
	   	  			selectTable = aliasQueryTable;
	   	  		}
   	  		}
   	  		else
   	  		{
   	  			ArrayList<Token> aliasTknVal = FunctionTypeConv.getTokens(aliasQueryTable);
   	  			if(aliasTknVal.size() > 0)
   	  		    	aliasQueryTable =  aliasTknVal.get(0).data;
   	  	
				boolean nodeHasSubQuery = checkNodeObjtIsSubquery(sqlObjts.get(aliasQueryTable));
				if(nodeHasSubQuery)
				{
					ExternalTable externalTable = new ExternalTable();
					String aliasName = null;
					String SQLVal = sqlObjts.get(aliasQueryTable);
					String updColName = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
					ArrayList<Token> tkns = FunctionTypeConv.getTokens(updColName);
					externalTable.dmlQuery = processSelectStmt(updColName, tkns, sqlObjts, aliasName, currentDBId, newDBId,false);
					sqlObj.externalTables.add(externalTable);
				}
				
   	  		}
   	  		
   	  		ArrayList<Token> tkns = FunctionTypeConv.getTokens(getTableVal);
   	  		if(tkns.size() > 0)
   	  		{
		  		 SQLElement SQlTblElem = new SQLElement();
		  		 SQlTblElem.name = tkns.get(0).data;

		  		 if(tkns.size() > 1)
		  		 {
		  			 if(tkns.get(1).data.contains("ds__QueryNode"))
		  			 {
		  				String colListName = sqlObjts.get(tkns.get(1).data);
		  				ArrayList<Token> bracktkn = FunctionTypeConv.getTokens(colListName);
						boolean checkParan = checkValues(bracktkn, "(");
						if(checkParan == true)
							colListName = colListName.substring(colListName.indexOf("(")+1,colListName.length()-1).trim();
						
						ArrayList<Token> tkn = FunctionTypeConv.getTokens(colListName);
						
						
						boolean checkDecSep = checkValues(tkn, ",");
						if(checkDecSep == true)
						{
							for(Entry<Token,Token> entry : getKeywordDecSep(colListName, tkn).entrySet())
							{
								String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
								String decSepVal = entry.getKey().data;
								String decSepEndVal = entry.getValue().data;
								if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
									value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
								else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
									value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
								else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
									value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
								if(value.length() > 0)
								{
								  processColsList(sqlObj,value,sqlObjts,currentDBId,newDBId);
								}
							}
							
						}
						else
						{     
							processColsList(sqlObj,colListName.trim(),sqlObjts,currentDBId,newDBId);
						}
		  			 }
		  		 }
		  		 sqlObj.tableList.add(SQlTblElem);
   	  		}
   	  		
   	  		ArrayList<Token> selectTkn = FunctionTypeConv.getTokens(selectTable);
   	  		boolean checkSelect = checkValues(selectTkn, "select");
		  	if(checkSelect)
			{
				ExternalTable externalTable = new ExternalTable();
				String aliasName = null;
				String SQLVal = selectTable;
				externalTable.dmlQuery = processSelectStmt(SQLVal, selectTkn, sqlObjts, aliasName, currentDBId, newDBId,false);
				sqlObj.externalTables.add(externalTable);
			}
   	  		
   	  		
   	  		
   	  		
   	  	    
   	  	}
	}

	private static void fillExternalTableColsForMerge(String colListName, SQLQuery sqlObj,
			HashMap<String, String> sqlObjts, ArrayList<Token> tokens, int currentDBId, int newDBId) 
	{
		String updColName = getUpdatedColName(sqlObjts,colListName);
        
		if(updColName.length() > 0)
		{
			if(sqlObj.dmlType.equalsIgnoreCase("Merge"))
			{
				String colsVal = updColName.substring(updColName.indexOf("(")+1, updColName.lastIndexOf(")"));
				getTableColumnValsForStmts(colsVal, sqlObjts,sqlObj,currentDBId,newDBId);
			}
		}
	}

	

	private static void processExternalTable(String sqlStmts, SQLQuery sqlObj, HashMap<String, String> sqlObjts, int currentDBId, int newDBId) 
	{
	   String withStmt =  updatedStmt(sqlStmts, "with");
	   String externalSelectStmt =  updatedStmt(withStmt, "select");
	   
	   ArrayList<Token> withTkn = FunctionTypeConv.getTokens(externalSelectStmt);
	  
	   boolean checkseleds = checkValues(withTkn, "Select");
	   
	   String ExternalTable = "";
	   String mainTable = ""; 
	   if(checkseleds)
	   {
		    ExternalTable = externalSelectStmt.substring(externalSelectStmt.indexOf("WITH")+4, externalSelectStmt.indexOf("SELECT")).trim();
		    mainTable = externalSelectStmt.substring(externalSelectStmt.indexOf("SELECT"), externalSelectStmt.length()-1);
	   }
	   else
	   {
		   ExternalTable = externalSelectStmt.substring(externalSelectStmt.indexOf("WITH")+4, externalSelectStmt.length()).trim(); 
	   }
	  
	    ArrayList<Token> tkn = FunctionTypeConv.getTokens(ExternalTable);
		
		boolean checkDecSep = checkValues(tkn, ",");
		if(checkDecSep == true)
		{
			for(Entry<Token,Token> entry : getKeywordDecSep(ExternalTable, tkn).entrySet())
			{
				String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
				String decSepVal = entry.getKey().data;
				String decSepEndVal = entry.getValue().data;
				if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
					value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
				else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
					value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
				else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
					value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
				if(value.length() > 0)
				{
					getExternalTableForWithStmt(sqlObj, sqlObjts, value, currentDBId, newDBId );
				}
				
			}
			
		}
		else
		{
			getExternalTableForWithStmt(sqlObj, sqlObjts, ExternalTable, currentDBId, newDBId );
		}
		
		if(mainTable.length() > 0)
		{
			String aliasName = null;
			mainTable = mainTable + " ;";
			ArrayList<Token> tkns = FunctionTypeConv.getTokens(mainTable);
			SQLQuery sqlQuery = processSelectStmt(mainTable, tkns, sqlObjts, aliasName, currentDBId, newDBId,false);
			sqlObj.colsList.add(sqlQuery);
		}
	}

	
	private static void getExternalTableForWithStmt(SQLQuery sqlObj, HashMap<String, String> sqlObjts, String value, int currentDBId, int newDBId)
	{
		 ArrayList<Token> externalTblTkns = FunctionTypeConv.getTokens(value);
		 
		 ExternalTable externalTable = new ExternalTable();
		 String SQLColName = "";
		 String aliasTableName = null;
		 String loopType = null;
		 if(externalTblTkns.size() > 0)
		 {
			 if(!externalTblTkns.get(0).data.equalsIgnoreCase("RECURSIVE"))
				 aliasTableName = externalTblTkns.get(0).data;
			 else
				 if(externalTblTkns.size() > 1)
				 {
					 loopType = externalTblTkns.get(0).data;
					 aliasTableName = externalTblTkns.get(1).data;
				 }
		 }
		 
		 for(int i = 0 ; i < externalTblTkns.size() ; i++)
		 {
			 if(externalTblTkns.get(i).data.contains("ds__QueryNode"))
			 {
					String updColName = getUpdatedColName(sqlObjts,externalTblTkns.get(i).data);
					
					boolean nodeHasSubQuery = checkNodeObjtIsSubquery(sqlObjts.get(externalTblTkns.get(i).data));
					
					if(nodeHasSubQuery == false)
					    SQLColName += " "+ updColName;
					else
					{
						//sqlObj.withInParanthesis = true;
						String aliasName = null;
						String SQLVal = sqlObjts.get(externalTblTkns.get(i).data);
						updColName = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
						externalTable.dmlQuery = processSelectStmt(updColName, externalTblTkns, sqlObjts, aliasName, currentDBId, newDBId,false);
					}
			 } 
		 }
		 
		 if(SQLColName.length() > 0)
		 {
			  if(SQLColName.contains("("))
				 SQLColName = SQLColName.substring(SQLColName.indexOf("(")+1, SQLColName.lastIndexOf(")"));
			 
			   String colsName = SQLColName;
			   ArrayList<Token> tkn = FunctionTypeConv.getTokens(colsName);
				
				boolean checkDecSep = checkValues(tkn, ",");
				if(checkDecSep == true)
				{
					for(Entry<Token,Token> entry : getKeywordDecSep(colsName, tkn).entrySet())
					{
						String value1 = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
						String decSepVal = entry.getKey().data;
						String decSepEndVal = entry.getValue().data;
						if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
							value1 = value1.substring(value1.indexOf(decSepVal)+decSepVal.length(), value1.lastIndexOf(decSepEndVal)).trim();
						else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
							value1 = value1.substring(value1.indexOf(decSepVal)+decSepVal.length(), value1.length()).trim();
						else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
							value1 = value1.substring(0, value1.lastIndexOf(decSepEndVal)).trim();
						if(value1.length() > 0)
						{
							 SQLElement SQlElem = new SQLElement();
							 SQlElem.name = value1;
							 externalTable.colsList.add(SQlElem);
						}
						
					}
					
				}
				else
				{
					if(colsName.length() > 0)
					{
						 SQLElement SQlElem = new SQLElement();
						 SQlElem.name = colsName;
						 externalTable.colsList.add(SQlElem);
					}
				}
			 
		 }
		 if(aliasTableName != null)
			 externalTable.tableAliasName = aliasTableName;
		 
		 if(loopType != null)
			 externalTable.loopType = loopType;
		 
		 sqlObj.externalTables.add(externalTable);
	}

	private static boolean checkCountSelect(ArrayList<Token> sqlTkns)
	{
		int count = 0;
		for(int i = 0 ; i < sqlTkns.size() ; i++)
		{
			Token tkn = sqlTkns.get(i);
			if(tkn.data.equalsIgnoreCase("select"))
			{
				count++;
			}
		}
		if(count  >= 2 )
			return true;
		
		return false;
	}

	private static void getValuesForInsrtStmts(String sqlStmt, ArrayList<Token> tokens,
			HashMap<String, String> sqlObjts, SQLQuery sqlObj, int currentDBId, int newDBId) 
	{
		ArrayList<Token> upTkn = FunctionTypeConv.getTokens(sqlStmt);
		for(int i = 0 ; i < upTkn.size(); i++)
		{
			if(upTkn.get(i).data.contains("ds__QueryNode"))
			{
				String colListName = sqlObjts.get(upTkn.get(i).data);
				ArrayList<Token> bracktkn = FunctionTypeConv.getTokens(colListName);
				boolean checkParan = checkValues(bracktkn, "(");
				if(checkParan == true)
					colListName = colListName.substring(colListName.indexOf("(")+1,colListName.length()-1).trim();
				
				ArrayList<Token> tkn = FunctionTypeConv.getTokens(colListName);
				
				boolean checkDecSep = checkValues(tkn, ",");
				if(checkDecSep == true)
				{
					for(Entry<Token,Token> entry : getKeywordDecSep(colListName, tkn).entrySet())
					{
						String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
						String decSepVal = entry.getKey().data;
						String decSepEndVal = entry.getValue().data;
						if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
							value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
						else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
							value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
						else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
							value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
						if(value.length() > 0)
						{
						  processColsList(sqlObj,value,sqlObjts,currentDBId,newDBId);
						}
					}
					
				}
				else
				{     
					processColsList(sqlObj,colListName.trim(),sqlObjts,currentDBId,newDBId);
				}
			}
		}
		
	}
	
	private static void getUpdateDeleteClauseStmts(String updStmt, ArrayList<Token> tokens,HashMap<String, String> sqlObjts, SQLQuery sqlObj, int currentDBId, int newDBId) 
	{
		
        ArrayList<Token> updSqlTkns = FunctionTypeConv.getTokens(updStmt);
        ArrayList<SQLJoin> SQLJoin = new ArrayList<SQLJoin>();
        ArrayList<String> jointypList = new ArrayList<String>();
        ArrayList<Boolean> OraJnTypLst = new ArrayList<Boolean>();
        boolean checkTblStmt = checkValues(updSqlTkns,"table");
		LinkedList<SQLObject> sqlExprQualify = new LinkedList<SQLObject>();
		
		if(checkTblStmt)
		{
			updSqlTkns = getUpdatedUnknwnTableTkns(updSqlTkns);
			updStmt = getUpdatedUnkwnTableVals(updSqlTkns);
		}
		for(Entry<Token,Token> entry : getKeywordClauseForStmts(updStmt,updSqlTkns).entrySet())
		{
			String StartVal = entry.getKey().data;
			String endVal = entry.getValue().data;
			String nodeTyp = "ds__QueryNode";
			HashMap<String,String> sqlNodeLsit = SQLUtils.getStmtNodes(StartVal, endVal, tokens,nodeTyp,updStmt);
			if(!sqlNodeLsit.isEmpty())
			{
				for(Entry<String,String> nodeEntry : sqlNodeLsit.entrySet())
				{
					processSelectStmtClause(nodeEntry.getValue(),tokens,sqlObjts,sqlObj,StartVal,endVal,nodeTyp,SQLJoin,currentDBId,newDBId,jointypList, OraJnTypLst, sqlExprQualify);
				}
			}
			else
			{
				String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),updSqlTkns).trim();
				ArrayList<Token> joinSQLStmts = FunctionTypeConv.getTokens(value);
				boolean checkJoinTbl = checkValues(joinSQLStmts,"join");
				if(checkJoinTbl == true)
				{
					processSelectStmtClause(value,tokens,sqlObjts,sqlObj,StartVal,endVal,nodeTyp,SQLJoin,currentDBId,newDBId,jointypList, null, sqlExprQualify);
				}
				
			}
		}
		
		if(SQLJoin.size()  > 0)
		{
			ArrayList<SQLJoin> getUpdSQLJn = getUpdatedSQLJoin(SQLJoin,jointypList, OraJnTypLst);
			SQLJoin jn = processUpdSQLJoinStmts(getUpdSQLJn);
			sqlObj.joinCond = jn;
			SQLJoin.clear();
			OraJnTypLst.clear();
		}
		
		
	}

	private static void setObjtForsqlObjt(SQLQuery sqlObj, SQLQuery sqlObjt) 
	{
		sqlObj.aliasName = sqlObjt.aliasName;
		sqlObj.colsList = sqlObjt.colsList;
		sqlObj.currentDbId = sqlObjt.currentDbId;
		sqlObj.dmlType = sqlObjt.dmlType;
		sqlObj.groupByCols = sqlObjt.groupByCols;
		sqlObj.havingCond = sqlObjt.havingCond;
		sqlObj.joinCond = sqlObjt.joinCond;
		sqlObj.newDbId = sqlObjt.newDbId;
		sqlObj.OrderBy = sqlObjt.OrderBy;
		sqlObj.partition = sqlObjt.partition;
		sqlObj.predicate = sqlObjt.predicate;
		sqlObj.setOperation = sqlObjt.setOperation;
		sqlObj.tableList = sqlObjt.tableList;
		sqlObj.whereCond = sqlObjt.whereCond;
		sqlObj.withInParanthesis = sqlObjt.withInParanthesis;
		sqlObj.hiddenSQL = sqlObjt.hiddenSQL;
		sqlObj.updateCols = sqlObjt.updateCols;
		sqlObj.externalTables = sqlObjt.externalTables; 
		
	}

	private static void processTblStmts(String sqlValue, HashMap<String, String> sqlObjts, ArrayList<Token> tokens,
			SQLQuery sqlObj, int currentDBId, int newDBId) 
	{
		ArrayList<Token> coltoken = FunctionTypeConv.getTokens(sqlValue);
	    if(!sqlObj.dmlType.equalsIgnoreCase("create"))
	    {
		        if(sqlValue.contains("ds__QueryNode"))
				{
		        	String SQLVal = "";
		        	if(coltoken.get(0).data.contains("ds__QueryNode"))
		        	{
					   SQLVal = sqlObjts.get(coltoken.get(0).data);
		        	}
		        	if(coltoken.size() > 1)
		        	{
		        		if(coltoken.get(1).data.contains("ds__QueryNode"))
			        	{
		        		   String sqlAliasVal = sqlObjts.get(coltoken.get(1).data);
		        		   boolean nodeHasSubQuery = checkNodeObjtIsSubquery(sqlAliasVal);
		        		   if(nodeHasSubQuery == true)
		  				   {
		        			   sqlObj.withInParanthesis = true;
		        			   sqlAliasVal = sqlAliasVal.substring(sqlAliasVal.indexOf("(")+1, sqlAliasVal.length()-1)+";";
		        			   sqlObj.colsList.add(processSelectStmt(sqlAliasVal, tokens, sqlObjts,null,currentDBId,newDBId,false));
		  				   }
		        		   else
		        		   {
		        			   SQLVal = sqlAliasVal;
		        		   }
			        	}
		        	}
		        	
		        	 boolean nodeHasSubQuery = checkNodeObjtIsSubquery(SQLVal);
		        	 String aliasName = null;
					 if(coltoken.size() > 1)
					 {
						 if(coltoken.size() > 2)
						 {
							 boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
							 boolean checkJoinKeyWords = getJoinSinglecondKeyWord(coltoken.get(2).data);
							 if(checkJoinKeyWord || checkJoinKeyWords)
							 {
								 if(coltoken.get(1).data.equalsIgnoreCase("as"))
									 aliasName = coltoken.get(2).data;
								 else
									 aliasName = coltoken.get(1).data;
								 
							 }
						 }
						 else
						 {
							 boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
						      if(checkJoinKeyWord == true)
							     aliasName = coltoken.get(1).data;
						 }
						  
					 }
					 if(nodeHasSubQuery == true)
					 {
				    	sqlObj.withInParanthesis = true;
				    	SQLVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
				    	sqlObj.tableList.add(processSelectStmt(SQLVal, tokens, sqlObjts,aliasName,currentDBId,newDBId,false));
					 }
				     else  
				     {
				    	boolean checkSQLSubqueries = getSQlValHasSubQueries(SQLVal,sqlObjts,sqlObj,currentDBId,newDBId);
				    	boolean checkInnerMostSubQueries = getSQLHasInnerMostSubQueries(SQLVal,sqlObjts,sqlObj,currentDBId,newDBId);
					    	if(checkSQLSubqueries == false)
					    	{
					    		if(!checkInnerMostSubQueries)
					    		{
							    	SQLElement tblElem = new SQLElement();
							    	SQLVal = getUpdatedColName(sqlObjts, SQLVal);
							    	if(SQLVal.length() > 0)
							    	{
							    		if(sqlObj.dmlType.equalsIgnoreCase("INSERT"))
							    		{
							    			tblElem.name = coltoken.get(0).data;
							    			String colsVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.lastIndexOf(")"));
							    			getTableColumnValsForStmts(colsVal, sqlObjts,sqlObj,currentDBId,newDBId);
							    		}
							    		else
							    			tblElem.name = coltoken.get(0).data + SQLVal;
							    		
							    	}
							    	else
							    	{
							    		tblElem.name = coltoken.get(0).data;
							    	}
						         	
						         	if(aliasName != null)
						         	{
							         	if(!(aliasName.contains("ds__QueryNode") || aliasName.equalsIgnoreCase("with")))
								    	    tblElem.aliasName = aliasName;
							         	else
							         	{
							         		if(coltoken.size() > 2)
							         		{
							         			
							         			if (coltoken.get(coltoken.size()-1).data.contains("ds__QueryNode"))
							         			{
							         				String temp = "";
							         				for (int i = 2; i < coltoken.size()-1; i++) 
							         				{
							         					temp += " " + coltoken.get(i).data;
													}
							         				if(!(SQLVal.length() > 0))
							         				   SQLVal = "WITH"+" "+temp + " "+coltoken.get(coltoken.size()-1).data;
							         				else
								         			   SQLVal = temp + " "+coltoken.get(coltoken.size()-1).data;
								         			
//											    	SQLVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";	
											    	processExternalTable(SQLVal, sqlObj, sqlObjts, currentDBId, newDBId);
							         			}
							         			else
							         				tblElem.aliasName = coltoken.get(coltoken.size()-1).data;
							         		}
								         		
							         	}
						         	}
						         	sqlObj.tableList.add(tblElem);
					    		}
					    		else
					    		{
					    			SQLVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length());
							    	SQLVal = "(SELECT * FROM " +SQLVal;
							    	boolean nodeHasHiddenSQLQuery = checkNodeObjtIsSubquery(SQLVal);
							    	if(nodeHasHiddenSQLQuery)
							    	{
							    		boolean hiddenSQL = true;
							    		sqlObj.withInParanthesis = true;
								    	SQLVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
								    	sqlObj.tableList.add(processSelectStmt(SQLVal, tokens, sqlObjts,aliasName,currentDBId,newDBId,hiddenSQL));
							    	}
					    		}
					    			
					    	}
					    	else 
					    	{
						    	SQLVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length());
						    	SQLVal = "(SELECT * FROM " +SQLVal;
						    	boolean nodeHasHiddenSQLQuery = checkNodeObjtIsSubquery(SQLVal);
						    	if(nodeHasHiddenSQLQuery)
						    	{
						    		boolean hiddenSQL = true;
						    		sqlObj.withInParanthesis = true;
							    	SQLVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
							    	sqlObj.tableList.add(processSelectStmt(SQLVal, tokens, sqlObjts,aliasName,currentDBId,newDBId,hiddenSQL));
						    	}
						    	
					    	}
				          
				     }
				}
		        else 
		        {
		       	  SQLElement tblElem = new SQLElement();
		       	  tblElem.name = coltoken.get(0).data;
			         if(coltoken.size() > 1)
			         {
			        	 if(coltoken.size() > 2)
						 {
			        		 boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
							 boolean checkJoinKeyWords = getJoinSinglecondKeyWord(coltoken.get(2).data);
							 if(checkJoinKeyWord || checkJoinKeyWords)
							 {
								 if(coltoken.get(1).data.equalsIgnoreCase("as"))
									 tblElem.aliasName  = coltoken.get(2).data;
								 else
									 tblElem.aliasName  = coltoken.get(1).data;
							 }
						 }
						 else
						 {
							 
							 if(!coltoken.get(1).type.name().equalsIgnoreCase("openparen") || coltoken.get(1).type.name().equalsIgnoreCase("closeparen"))
							 {
							     boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
							       if(checkJoinKeyWord == true)
							       {
									 tblElem.aliasName  = coltoken.get(1).data;
							       }
							 }
						 }
			         }
			        	
			         sqlObj.tableList.add(tblElem);
		        }
	    }
	    
	    else
	    {
	    	DDLParser.processDDLParser(sqlObj, coltoken, sqlObjts, currentDBId, newDBId, sqlValue, tokens);
	    }
	  
}

	private static boolean getSQLHasInnerMostSubQueries(String sQLVal, HashMap<String, String> sqlObjts,
			SQLQuery sqlObj, int currentDBId, int newDBId)
	{
		ArrayList<Token> tknVal = FunctionTypeConv.getTokens(sQLVal);
		
		boolean withinParen = false;
        boolean checkSubQuery = false;
    	if(tknVal.size() > 0)
    	{
    		if(tknVal.get(0).type.name().equalsIgnoreCase("OPENPAREN"))
			{
				withinParen = true;
			}
			if(withinParen)
			{ 
				if(tknVal.get(1).type.name().equalsIgnoreCase("VAR") && tknVal.get(1).data.contains("ds__QueryNode_"))
				{
					 String subQueryVal = sqlObjts.get(tknVal.get(1).data);
					 
					 subQueryVal = getUpdatedColName(sqlObjts, subQueryVal);
					 subQueryVal = updatedStmt(subQueryVal, "select");
					 if(subQueryVal.contains("SELECT"))
					 {
						 ArrayList<Token> token = FunctionTypeConv.getTokens(subQueryVal);
						 
						 for(int i = 0 ; i < token.size() ; i++)
						 {
							 if(token.get(i).type.name().equalsIgnoreCase("VAR") && token.get(i).data.equalsIgnoreCase("SELECT"))
							 {
								 checkSubQuery = true;
								 break;
							 }
						 }
					 }
					
				}
			}
    	}
    	
		return checkSubQuery;
	}


	private static void getTableColumnValsForStmts(String sQLVal, HashMap<String, String> sqlObjts, SQLQuery sqlObj,
			int currentDBId, int newDBId)
	{
		  ArrayList<Token> tkn = FunctionTypeConv.getTokens(sQLVal);
			
			boolean checkDecSep = checkValues(tkn, ",");
			if(checkDecSep == true)
			{
				for(Entry<Token,Token> entry : getKeywordDecSep(sQLVal, tkn).entrySet())
				{
					String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
					String decSepVal = entry.getKey().data;
					String decSepEndVal = entry.getValue().data;
					if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
					else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
					else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
					if(value.length() > 0)
					{
					    processColsForInsertStmts(value, sqlObjts, sqlObj, currentDBId, newDBId);
					}
					
				}
				
			}
			else
			{
				 processColsForInsertStmts(sQLVal, sqlObjts, sqlObj, currentDBId, newDBId);
			}
	}

	private static void processColsForInsertStmts(String value, HashMap<String, String> sqlObjts, SQLQuery sqlObj,
			int currentDBId, int newDBId) 
	{
		
		String SQLColName = "";
        ArrayList<Token> coltoken = FunctionTypeConv.getTokens(value);
        if (coltoken.size() > 0)
        {
	        if(!coltoken.get(0).data.contains("ds__QueryNode"))
	        	SQLColName += coltoken.get(0).data +" ";
        }
        boolean checkAliasName = checkValues(coltoken,"as");
		String aliasName = null;
		boolean nodeHasSubQuery = false;
        
	        for (int i = 0; i < coltoken.size(); i++) 
	        {
				if(coltoken.get(i).data.contains("ds__QueryNode"))
				{
					String updColName = getUpdatedColName(sqlObjts,coltoken.get(i).data);
					
					nodeHasSubQuery = checkNodeObjtIsSubquery(sqlObjts.get(coltoken.get(i).data));
					
					if(nodeHasSubQuery == false)
					    SQLColName += " "+ updColName;
					else
					{
						//sqlObj.withInParanthesis = true;
						String SQLVal = sqlObjts.get(coltoken.get(i).data);
						updColName = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
						sqlObj.colsList.add(processSelectStmt(updColName, coltoken, sqlObjts,aliasName,currentDBId,newDBId,false));
					}
				     //processSubQueryStmt Need to work
					
				}
				
				if(i > 0)
				{
				  if(!coltoken.get(i).data.contains("ds__QueryNode"))
				  {
					  if(checkAliasName == false)
					  {
						 aliasName = coltoken.get(coltoken.size()-1).data;
						 if(aliasName.contains("ds__QueryNode"))
						 {
							 aliasName = null;
						 }
						
						 if(i != coltoken.size()-1)
						 {
							 SQLColName += " "+coltoken.get(i).data;
						 }
						
					  }
					  else
					  {
						  if(i+1 < coltoken.size())
						  {
							  if(coltoken.get(i).data.equalsIgnoreCase("as"))
							     aliasName = coltoken.get(i+1).data;
							  else
								 SQLColName += " "+coltoken.get(i).data;
						  }
						  
					  }
					 
				  }
				  
				}
				 
			 }
       
	       
	        
        if(nodeHasSubQuery == false)
        {
        	SQLElement sqlElem = new SQLElement();
        	SQLColName = getUpdatedColName(sqlObjts,SQLColName);
        	if(newDBId == ToolTypeConstants.AZURESQL)
        	{
        		SQLColName = SQLColName.replace("(date,format 'yyyy-mm-dd')", "");
        	}
        	SQLColName = FunctionTypeConv.getCurrentFunctions(SQLColName, currentDBId, newDBId);
        	sqlElem.name = SQLColName;
        	sqlElem.aliasName = aliasName;
        	sqlObj.externalTables.add(sqlElem);
        }
	}

	public static String getUpdatedDDLStruct(String sqlValue)
	{
		  String primaryVal =  updatedStmt(sqlValue, "primary");
		  String indexVal =  updatedStmt(primaryVal, "index");
		  sqlValue = indexVal;
		  if(sqlValue.contains(" PRIMARY INDEX "))
		  {
			  String subStr = " PRIMARY INDEX ";
			  sqlValue = sqlValue.substring(0,sqlValue.indexOf(subStr));
		  }
		  
		  String with =  updatedStmt(sqlValue, "with");
		  String data =  updatedStmt(with, "data");
		  String unique =  updatedStmt(data, "unique");
		  sqlValue = unique;
		  if(sqlValue.contains("WITH DATA UNIQUE"))
		  {
			  String subStr = "WITH DATA UNIQUE";
			  sqlValue = sqlValue.substring(0,sqlValue.indexOf(subStr));
		  }
		  else if(sqlValue.contains(" UNIQUE"))
		  {
			  String subStr = "UNIQUE";
			  sqlValue = sqlValue.substring(0,sqlValue.indexOf(subStr)).trim();
		  }
		  String checkSum =  updatedStmt(sqlValue, "checksum");
		  String dfltVal =  updatedStmt(checkSum, "default");
		  sqlValue = dfltVal;
		  if(sqlValue.contains("CHECKSUM = DEFAULT"))
		  {
			  String subStr = "CHECKSUM = DEFAULT ";
			  int subStrCheckSumLen = subStr.length();
			  sqlValue = sqlValue.substring(sqlValue.indexOf(subStr)+subStrCheckSumLen);
		  }
		  String onVal =  updatedStmt(sqlValue, "on");
		  if(onVal.contains(" ON "))
		  {
			  String subStr = " ON ";
			  onVal = onVal.substring(0,onVal.indexOf(subStr));
			  sqlValue = onVal;
		  }
		  
		return sqlValue;
	}

	private static boolean getSQlValHasSubQueries(String sQLVal, HashMap<String, String> sqlObjts, SQLQuery sqlObj, int currentDBId, int newDBId) 
	{
		sQLVal = sQLVal.trim();
		ArrayList<Token> tokens = FunctionTypeConv.getTokens(sQLVal);
		boolean withinParen = false;
		if(tokens.size() > 0)
		{
			if(tokens.get(0).type.name().equalsIgnoreCase("OPENPAREN"))
			{
				withinParen = true;
			}
			if(withinParen)
			{ 
				if(tokens.get(1).type.name().equalsIgnoreCase("VAR") && tokens.get(1).data.contains("ds__QueryNode_"))
				{
					 String subQueryVal = sqlObjts.get(tokens.get(1).data);
					 
					 boolean nodeHasSubQuery = checkNodeObjtIsSubquery(subQueryVal);
					 if(nodeHasSubQuery)
					 {
						 return true;
					 }
				}
			}
		}
		return false;
	}

	private static boolean getJoinSinglecondKeyWord(String data)
	{
		 if(!(data.equalsIgnoreCase("left") || data.equalsIgnoreCase("right") 
				 || data.equalsIgnoreCase("cross") || data.equalsIgnoreCase("outer")
				 || data.equalsIgnoreCase("full") || data.equalsIgnoreCase("inner")))
		 {
			 return true;
		 }
	
		return false;
	}

	

	public static String updatedStmt(String sqlStmts, String checkVal) 
	{
		ArrayList<Token> tkn = FunctionTypeConv.getTokens(sqlStmts);
		String updVal ="";
		for (int i = 0; i < tkn.size(); i++) 
		{
			Token tknData = tkn.get(i);
			if(tknData.data.equalsIgnoreCase(checkVal))
			{
				tknData.data = tknData.data.toUpperCase();
				
				updVal += " "+tknData.data;
			}
			else
				updVal += " "+ tknData.data;
		}
				
		return updVal.trim();
	}
	
	public static boolean checkNodeObjtIsSubquery(String data) 
	{
		 ArrayList<Token> coltoken = FunctionTypeConv.getTokens(data);
		 if(coltoken.size() > 1)
		 {
			 if(coltoken.get(0).data.equalsIgnoreCase("("))
			 {
				 if(coltoken.get(1).data.equalsIgnoreCase("SELECT") || coltoken.get(1).data.equalsIgnoreCase("SEL"))
					 return true;
			 }
		 }
		return false;
		
	}

	public static SQLQuery processSelectStmt(String sqlStmts, ArrayList<Token> tokens, HashMap<String, String> sqlObjts, String aliasName, int currentDBId, int newDBId, boolean hiddenSQL) 
	{
		SQLQuery sqlObjt = new SQLQuery();
		sqlObjt.dmlType = "SELECT";
		sqlObjt.aliasName = aliasName;
		if(hiddenSQL)
		{
			sqlObjt.hiddenSQL = true;
		}
		ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(sqlStmts);
		ArrayList<Boolean> OraJnTypList = new ArrayList<Boolean>();
		
		boolean checkUnionStmt = checkValues(sqlTkns,"Union");
		boolean checkMinusStmt = checkValues(sqlTkns,"Minus");
		
        LinkedList<SQLObject> exprQualifyList = new LinkedList<SQLObject>();
		if(checkUnionStmt == true)
		{
			sqlStmts = updatedStmts(sqlStmts,"Union");
			
			String[] unionTemp = sqlStmts.split(" UNION ");
            getSQLForUnion(unionTemp,sqlObjt,tokens,sqlObjts,currentDBId,newDBId);
			
			sqlStmts = unionTemp[0].trim()+";";
			
		}
		if(checkMinusStmt == true)
		{
			sqlStmts = updatedStmts(sqlStmts,"Minus");
			
			String[] unionTemp = sqlStmts.split(" MINUS ");
            getSQLForMinus(unionTemp,sqlObjt,tokens,sqlObjts,currentDBId,newDBId);
			
			sqlStmts = unionTemp[0].trim()+";";
		}
		ArrayList<SQLJoin> SQLJoin = new ArrayList<SQLJoin>();
		ArrayList<Token> updSqlTkns = FunctionTypeConv.getTokens(sqlStmts);
		boolean checkTblStmt = checkValues(updSqlTkns,"table");
		
		if(checkTblStmt)
		{
			updSqlTkns = getUpdatedUnknwnTableTkns(updSqlTkns);
			sqlStmts = getUpdatedUnkwnTableVals(updSqlTkns);
		}
		
		ArrayList<String> joinTypList = new ArrayList<String>();
		for(Entry<Token,Token> entry : getKeywordClauseForStmts(sqlStmts,updSqlTkns).entrySet())
		{
			String StartVal = entry.getKey().data;
			String endVal = entry.getValue().data;
			String nodeTyp = "ds__QueryNode";
			HashMap<String,String> sqlNodeLsit = SQLUtils.getStmtNodes(StartVal, endVal, tokens,nodeTyp,sqlStmts);
			if(!sqlNodeLsit.isEmpty())
			{
				for(Entry<String,String> nodeEntry : sqlNodeLsit.entrySet())
				{
					processSelectStmtClause(nodeEntry.getValue(),tokens,sqlObjts,sqlObjt,StartVal,endVal,nodeTyp,SQLJoin,currentDBId,newDBId,joinTypList, OraJnTypList, exprQualifyList);
				}
				
			}
			else
			{
				String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),updSqlTkns).trim();
				ArrayList<Token> joinSQLStmts = FunctionTypeConv.getTokens(value);
				boolean checkJoinTbl = checkValues(joinSQLStmts,"join");
				if(checkJoinTbl == true)
				{
					processSelectStmtClause(value,tokens,sqlObjts,sqlObjt,StartVal,endVal,nodeTyp,SQLJoin,currentDBId,newDBId,joinTypList, null, exprQualifyList);
				}
				
			}
		}
		
		if(SQLJoin.size()  > 0)
		{
			ArrayList<SQLJoin> getUpdSQLJn = getUpdatedSQLJoin(SQLJoin,joinTypList, OraJnTypList);
			SQLJoin jn = processUpdSQLJoinStmts(getUpdSQLJn);
			sqlObjt.joinCond = jn;
			SQLJoin.clear();
			OraJnTypList.clear();
		}
		
		return sqlObjt;
		
	}

	

	private static String getUpdatedUnkwnTableVals(ArrayList<Token> sqlTkns) 
	{
		String updatedVal = "";
		for(int i = 0 ; i < sqlTkns.size() ; i++)
		{
			if( sqlTkns.get(i).data.equalsIgnoreCase("table"))
			{
				updatedVal += " "+"dsError_"+sqlTkns.get(i).data;
			}
			else
				updatedVal += " "+sqlTkns.get(i).data;
			
		}
		
		return updatedVal.trim();
	}

	private static ArrayList<Token> getUpdatedUnknwnTableTkns(ArrayList<Token> sqlTkns)
	{
		for(int i = 0 ; i < sqlTkns.size() ; i++)
		{
			if( sqlTkns.get(i).data.equalsIgnoreCase("table"))
			{
				sqlTkns.get(i).data = sqlTkns.get(i).data.replace(sqlTkns.get(i).data, "dsError_"+sqlTkns.get(i).data);
			}
			
		}
		return sqlTkns;
	}

	

	private static void getSQLForMinus(String[] unionTemp, SQLQuery sqlObjt, ArrayList<Token> tokens,
			HashMap<String, String> sqlObjts, int currentDBId, int newDBId) 
	{
		List<SQLUnion> sqlUnion = new ArrayList<SQLUnion>();
		for(int  i = 0 ; i < unionTemp.length ;i ++)
		{
			if(i > 0)
			{  
				String selectStmt = unionTemp[i].trim();
				selectStmt = updatedStmts(unionTemp[i].trim(),"all");
				selectStmt = updatedStmts(selectStmt,"select");
				SQLUnion sqlUn = new SQLUnion();
				
				sqlUn.type = "MINUS";
				
				if(selectStmt.startsWith("SELECT"))
				{
					String aliasName = null;
					ArrayList<Token> tkn = FunctionTypeConv.getTokens(selectStmt);
					boolean checkCloseParen = checkValues(tkn, ")");
					if(checkCloseParen)
					{
						selectStmt = selectStmt.substring(0, selectStmt.lastIndexOf(")"));
					}
					selectStmt = selectStmt + ";";
					sqlUn.dmlQuery = processSelectStmt(selectStmt, tokens, sqlObjts, aliasName,currentDBId,newDBId,false);
				}
				sqlUnion.add(sqlUn);
			}
		}
		SQLUnion sqlUn = getUpdatedSQLUnion(sqlUnion);
		sqlObjt.setOperation = sqlUn;
		
	}

	private static ArrayList<SQLJoin> getUpdatedSQLJoin(ArrayList<SQLJoin> sQLJoin, ArrayList<String> joinTypList, ArrayList<Boolean> oraJnTypList) 
	{
		
		if(!(oraJnTypList.size() > 0))
		{
			ArrayList<SQLJoin> sqlJoin = new ArrayList<SQLJoin>();
			
			int tmp = 0;
			for(int i = 0 ; i < sQLJoin.size() ;i++)
			{
				SQLJoin updSqlJn = sQLJoin.get(i);
				SQLJoin jn = new SQLJoin();
				tmp = sqlJoin.size();
				
				if(i > 0)
				{	
					jn.joinType = sQLJoin.get(i-1).joinType;
				}
				else
				{
					if(joinTypList.size() > 0)
					{
						jn.joinType = joinTypList.get(0).trim();
						joinTypList.clear();
					}
					
				}
				
				if(jn.joinType == null)
					jn.joinType = "Join";
				
				jn.joinCond = updSqlJn.joinCond;
				jn.joinCondDtls = updSqlJn.joinCondDtls;
				jn.multiJoinTbl = updSqlJn.multiJoinTbl;
				
				sqlJoin.add(jn);
			}
			return sqlJoin;
		}
		
		return sQLJoin;
		
	}

	private static SQLJoin processUpdSQLJoinStmts(ArrayList<SQLJoin> sQLJoin) 
	{
		for (int i = 0; i < sQLJoin.size(); i++) 
		{
			SQLJoin sql = new SQLJoin();
			sql.joinType = sQLJoin.get(i).joinType;
			
			sql = sQLJoin.get(i);
			SQLJoin sqlQuery = sQLJoin.get(i);
			
			if(i+1 < sQLJoin.size())
			{
				if(sqlQuery.multiJoinTbl == null)
				{
					SQLJoin sqlObjtUnion = new SQLJoin();
					sqlObjtUnion = sQLJoin.get(i+1);
					
					SQLJoin sqlUnQuery = sQLJoin.get(i+1);
					if(sqlUnQuery.multiJoinTbl == null)
					{
						sqlQuery.multiJoinTbl = sqlObjtUnion;
						sQLJoin.remove(i);
						processUpdSQLJoinStmts(sQLJoin);
					}
				}
			}
			return sql;
		}
		return null;
	}

	private static void getSQLForUnion(String[] unionTemp, SQLQuery sqlObjt, ArrayList<Token> tokens, HashMap<String, String> sqlObjts, int currentDBId, int newDBId) 
	{
		List<SQLUnion> sqlUnion = new ArrayList<SQLUnion>();
		for(int  i = 0 ; i < unionTemp.length ;i ++)
		{
			if(i > 0)
			{  
				String selectStmt = unionTemp[i].trim();
				selectStmt = updatedStmts(unionTemp[i].trim(),"all");
				selectStmt = updatedStmts(selectStmt,"select");
				SQLUnion sqlUn = new SQLUnion();
				if(selectStmt.startsWith("ALL"))
				{
				   sqlUn.type = "UNION ALL";
				   selectStmt = selectStmt.substring(selectStmt.indexOf("ALL ")+4, selectStmt.length()).trim(); 
				}
				else
				   sqlUn.type = "UNION";
				
			    if(selectStmt.startsWith("ds__QueryNode_"))
			    {
			       if (selectStmt.contains(";"))
			    	   selectStmt = selectStmt.substring(0, selectStmt.lastIndexOf(";")).trim();
				   selectStmt = sqlObjts.get(selectStmt);
				   ArrayList<Token> tkn = FunctionTypeConv.getTokens(selectStmt);
				   boolean checkCloseParen = checkValues(tkn, "(");
				   	if(checkCloseParen)
					{
						selectStmt = selectStmt.substring(selectStmt.indexOf("(")+1, selectStmt.lastIndexOf(")"));
					}
				   	selectStmt = updatedStmts(selectStmt,"select");
			    }
			
				if(selectStmt.startsWith("SELECT"))
				{
					String aliasName = null;
					ArrayList<Token> tkn = FunctionTypeConv.getTokens(selectStmt);
					boolean checkCloseParen = checkValues(tkn, ")");
					if(checkCloseParen)
					{
						selectStmt = selectStmt.substring(0, selectStmt.lastIndexOf(")"));
					}
					
					String ipSQLQuery = selectStmt.trim();
					ipSQLQuery = ipSQLQuery.substring(ipSQLQuery.length()-1,ipSQLQuery.length());
					
					if(!ipSQLQuery.equalsIgnoreCase(";"))//To append ; 
						selectStmt = selectStmt + ";";
					
					sqlUn.dmlQuery = processSelectStmt(selectStmt, tokens, sqlObjts, aliasName,currentDBId,newDBId,false);
				}
				sqlUnion.add(sqlUn);
			}
		}
		SQLUnion sqlUn = getUpdatedSQLUnion(sqlUnion);
		sqlObjt.setOperation = sqlUn;
	}

	private static SQLUnion getUpdatedSQLUnion(List<SQLUnion> sqlUnion) 
	{
		for (int i = 0; i < sqlUnion.size(); i++) 
		{
		    SQLUnion sql = new SQLUnion();
			sql.type = sqlUnion.get(i).type;
			sql.dmlQuery = sqlUnion.get(i).dmlQuery;
			
			SQLQuery sqlQuery = new SQLQuery();
			if (sqlUnion.get(i).dmlQuery instanceof SQLQuery)
				sqlQuery = (SQLQuery) sqlUnion.get(i).dmlQuery;
			
			if(i+1 < sqlUnion.size())
			{
				if(sqlQuery.setOperation == null)
				{
					SQLUnion sqlObjtUnion = new SQLUnion();
					sqlObjtUnion.type = sqlUnion.get(i+1).type;
					sqlObjtUnion.dmlQuery = sqlUnion.get(i+1).dmlQuery;
					
					SQLQuery sqlUnQuery = new SQLQuery();
					if (sqlUnion.get(i+1).dmlQuery instanceof SQLQuery)
						sqlUnQuery = (SQLQuery) sqlUnion.get(i+1).dmlQuery;
					
					if(sqlUnQuery.setOperation == null)
					{
						sqlQuery.setOperation = sqlObjtUnion;
						sqlUnion.remove(i);
						getUpdatedSQLUnion(sqlUnion);
					}
				}
			}
			return sql;
			
		}
		return null;
		
		
	}

	private static String updatedStmts(String sqlStmts, String checkVal) 
	{
		ArrayList<Token> tkn = FunctionTypeConv.getTokens(sqlStmts);
		String updVal ="";
		for (int i = 0; i < tkn.size(); i++) 
		{
			Token tknData = tkn.get(i);
			if(tknData.data.equalsIgnoreCase(checkVal))
			{
				tknData.data = tknData.data.toUpperCase();
				updVal += " "+tknData.data;
			}
			else
				updVal += " "+ tknData.data;
		}
				
		return updVal.trim();
	}

	private static void processSelectStmtClause(String sqlStmt, ArrayList<Token> tokens, HashMap<String, String> sqlObjts, SQLQuery sqlObjt, String startVal, String endVal, String nodeTyp, ArrayList<SQLJoin> sQLJoin, int currentDBId, int newDBId, ArrayList<String> joinTypList, ArrayList<Boolean> oraJnTypList, LinkedList<SQLObject> exprQualifyList) 
	{
		if(startVal.equalsIgnoreCase("select") || startVal.equalsIgnoreCase("delete") || startVal.equalsIgnoreCase("drop"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));	
			ArrayList<Token> updTkn = FunctionTypeConv.getTokens(colListName);
			if(updTkn.size() > 0)
			{
				if(updTkn.get(0).data.equalsIgnoreCase("distinct"))
				{
					sqlObjt.predicate = "distinct";
					colListName = colListName.substring(colListName.indexOf(updTkn.get(0).data)+updTkn.get(0).data.length());		
				}
			}
			ArrayList<Token> tkn = FunctionTypeConv.getTokens(colListName);
			boolean checkDecSep = checkValues(tkn, ",");
			if(checkDecSep == true)
			{
				
				for(Entry<Token,Token> entry : getKeywordDecSep(colListName, tkn).entrySet())
				{
					String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
					String decSepVal = entry.getKey().data;
					String decSepEndVal = entry.getValue().data;
					if (!value.equalsIgnoreCase(","))
					{
						if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
							value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
						else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
							value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
						else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
							value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
						
						 processColsList(sqlObjt,value,sqlObjts,currentDBId,newDBId);
					}
				}
				
			}
			else
			{     if(!colListName.trim().equalsIgnoreCase(""))
						processColsList(sqlObjt,colListName.trim(),sqlObjts,currentDBId,newDBId);
			}
		}
		
		if(startVal.equalsIgnoreCase("Set"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal)).trim();
			ArrayList<Token> tkn = FunctionTypeConv.getTokens(colListName);
			boolean checkDecSep = checkValues(tkn, ",");
			if(checkDecSep == true)
			{
				for(Entry<Token,Token> entry : getKeywordDecSep(colListName, tkn).entrySet())
				{
					String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
					String decSepVal = entry.getKey().data;
					String decSepEndVal = entry.getValue().data;
					if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
					else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
					else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
					ArrayList<Token> tknEquals = FunctionTypeConv.getTokens(value);
					boolean checkEqualsSep = checkValues(tknEquals, "=");
					if(checkEqualsSep == true)
					{
						UpdateElement updElem = new UpdateElement();
						createUpdatedElem(value,updElem,sqlObjts,tokens,currentDBId,newDBId);
						sqlObjt.updateCols.add(updElem);
					}
					
				}
				
			}
			else
			{
				ArrayList<Token> tknEquals = FunctionTypeConv.getTokens(colListName);
				boolean checkEqualsSep = checkValues(tknEquals, "=");
				if(checkEqualsSep == true)
				{
					UpdateElement updElem = new UpdateElement();
					createUpdatedElem(colListName,updElem,sqlObjts,tokens,currentDBId,newDBId);
					sqlObjt.updateCols.add(updElem); 
				}
			}
			
		}
		if(startVal.equalsIgnoreCase("into"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
			getTableColumnValsForStmts(colListName, sqlObjts, sqlObjt, currentDBId, newDBId);
		}
		if(startVal.equalsIgnoreCase("from"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
			
			if(colListName.contains(","))
			{
				String[] colsName = colListName.split(",");
				
				 for (String colName : colsName) 
				 {
					processTblStmts(colName,sqlObjts,tokens,sqlObjt,currentDBId,newDBId);
				 }
			}
			else
			{
			    processTblStmts(colListName,sqlObjts,tokens,sqlObjt,currentDBId,newDBId);
			}
		}
		if(startVal.equalsIgnoreCase("where") || startVal.equalsIgnoreCase("qualify"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
			processWhereCondForTbls(colListName,sqlObjt, sqlObjts, tokens,currentDBId,newDBId,startVal,sQLJoin, joinTypList, oraJnTypList);
			
			if(startVal.equalsIgnoreCase("Where") && endVal.equalsIgnoreCase("Qualify"))
				exprQualifyList.add(sqlObjt.whereCond);
			
			if(exprQualifyList.size() > 0)
			{
				getUpdatedSQLObjectForQualify(startVal, exprQualifyList,sqlObjt);
			}
			
		}
		if(startVal.equalsIgnoreCase("group"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
			ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(colListName);
			boolean checkBy = checkValues(sqlTkns,"by");
			if(checkBy == true)
			{
				colListName = updatedStmts(colListName,"BY");
				colListName = colListName.substring(colListName.indexOf("BY")+2,colListName.length());
			}
			processGroupByStmts(colListName,sqlTkns,sqlObjt, sqlObjts, tokens,currentDBId,newDBId);
		}
		if(startVal.equalsIgnoreCase("having"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
			processHavingClause(colListName,sqlObjt, sqlObjts, tokens,currentDBId,newDBId);
		}
		if((endVal.equalsIgnoreCase("join")) || (startVal.equalsIgnoreCase("join") || startVal.equalsIgnoreCase("using")))
		{
			if(startVal.equalsIgnoreCase("from") && endVal.equalsIgnoreCase("join"))
			{
				String joinTypeName = "";
				
				String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
				ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(colListName);
				Token checkJoinTypeData = null;
				Token checkJoinType = null;
				
				if(sqlTkns.size() > 2)
				{
					checkJoinTypeData = sqlTkns.get(sqlTkns.size()-2);
					checkJoinType = sqlTkns.get(sqlTkns.size()-1);
				}
				if(sqlTkns.size() > 1)
				{
					checkJoinType = sqlTkns.get(sqlTkns.size()-1);
				}
				
				if(checkJoinTypeData != null)
				{
					if (checkJoinTypeData.data.equalsIgnoreCase("left") 
							|| checkJoinTypeData.data.equalsIgnoreCase("cross") || checkJoinTypeData.data.equalsIgnoreCase("outer") || checkJoinTypeData.data.equalsIgnoreCase("right") || checkJoinTypeData.data.equalsIgnoreCase("inner") || checkJoinTypeData.data.equalsIgnoreCase("full"))
					{
						joinTypeName += checkJoinTypeData.data;
					}
				}
				if(checkJoinType != null)
				{
					if (checkJoinType.data.equalsIgnoreCase("left") || 
							checkJoinType.data.equalsIgnoreCase("cross") || checkJoinType.data.equalsIgnoreCase("outer") || checkJoinType.data.equalsIgnoreCase("right") || checkJoinType.data.equalsIgnoreCase("inner") || checkJoinType.data.equalsIgnoreCase("full"))
					{
						joinTypeName += " "+checkJoinType.data;
						
					}	
				}
				if(joinTypeName.equalsIgnoreCase(""))
				{
					joinTypeName += "JOIN";
				}
				
				joinTypList.add(joinTypeName.trim());
				
			}
			else
			{
				String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
				processJoinStmt(colListName,sqlObjt, sqlObjts, tokens,sQLJoin,currentDBId,newDBId,joinTypList, null);
			}
			
			
		}
		if(startVal.equalsIgnoreCase("order"))
		{
			String colListName = sqlStmt.substring(sqlStmt.indexOf(startVal)+startVal.length(), sqlStmt.lastIndexOf(endVal));
			ArrayList<Token> oldTkn = FunctionTypeConv.getTokens(colListName);
			boolean checkBy = checkValues(oldTkn,"by");
			if(checkBy == true)
			{
				colListName = updatedStmts(colListName,"BY");
				colListName = colListName.substring(colListName.indexOf("BY")+2,colListName.length());
			}
			ArrayList<Token> tkn = FunctionTypeConv.getTokens(colListName);
			boolean checkDecSep = checkValues(tkn, ",");
			if(checkDecSep == true)
			{
				for(Entry<Token,Token> entry : getKeywordDecSep(colListName, tkn).entrySet())
				{
					String value = getIndexPositionForToken(entry.getKey(),entry.getValue(),tkn).trim();
					String decSepVal = entry.getKey().data;
					String decSepEndVal = entry.getValue().data;
					if(decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.lastIndexOf(decSepEndVal)).trim();
					else if(decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(value.indexOf(decSepVal)+decSepVal.length(), value.length()).trim();
					else if(!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
						value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();
					 processOrderbyStmt(sqlObjt,value,sqlObjts,currentDBId,newDBId);
				}
			}
			else
				processOrderbyStmt(sqlObjt,colListName,sqlObjts,currentDBId,newDBId);
			
		}
	}

	private static void getUpdatedSQLObjectForQualify(String startVal, LinkedList<SQLObject> exprQualifyList, SQLQuery sqlObjt) 
	{
		if(startVal.equalsIgnoreCase("qualify"))
		{
			SQLExpr sqlexpr = sqlObjt.whereCond;
			String qualifyVal = sqlexpr.value;
			sqlObjt.whereCond = (SQLExpr) exprQualifyList.get(0);
			SQLExpr sqlExpr = sqlObjt.whereCond; 
			sqlExpr.args.add(sqlexpr);
			sqlExpr.value = sqlExpr.value + " QUALIFY "+qualifyVal;
			exprQualifyList.clear();
			sqlObjt.whereCond = sqlExpr;
		}
	}

	private static void createUpdatedElem(String colListName, UpdateElement updElem, HashMap<String, String> sqlObjts, ArrayList<Token> tokens, int currentDBId, int newDBId) 
	{
		String[] equalSplit = colListName.split("=");
		String colName = equalSplit[0];
		String updatedValue = equalSplit[1];
		
		if(colName.contains("ds__QueryNode"))
			colName = sqlObjts.get(colName);
		
		updElem.updColName = colName;
		if(updatedValue.contains("ds__QueryNode"))
		{
			 
			 ArrayList<Token> tkn = FunctionTypeConv.getTokens(updatedValue);
			 String updatedVals = "";
			 for(int i = 0 ; i < tkn.size() ; i++)
			 {
				  if(tkn.get(i).data.contains("ds__QueryNode"))
				  {
					  String updatedVal = sqlObjts.get(tkn.get(i).data);
					  boolean nodeHasSubQuery = checkNodeObjtIsSubquery(updatedVal);
			    	  if(nodeHasSubQuery == true)
					  {
			    		 updatedVal = updatedVal.substring(updatedVal.indexOf("(")+1, updatedVal.length()-1)+";";
						 updElem.value = processSelectStmt(updatedVal,tokens, sqlObjts,null,currentDBId,newDBId,false);
					  }
			    	  else
			    		  updatedVals += " "+updatedVal;
				  }
				  else
				  {
					  updatedVals += tkn.get(i).data + " ";
				  }
        	    
			 }
			 if(updatedVals.length() > 0)
			 {
				 SQLElement sqlElem = new SQLElement();
				 updatedVals =  getUpdatedColName(sqlObjts, updatedVals);
				 if(newDBId == ToolTypeConstants.AZURESQL)
				 {
					 updatedVals = updatedVals.replace("(date,format 'yyyy-mm-dd')", "");
				 }
				 updatedVals = FunctionTypeConv.getCurrentFunctions(updatedVals, currentDBId, newDBId);
				 sqlElem.name = updatedVals;
				 updElem.value = sqlElem;
			 }
			 
		}
		else
		{
			 SQLElement sqlElem = new SQLElement();
			 updatedValue = FunctionTypeConv.getCurrentFunctions(updatedValue, currentDBId, newDBId);
			 sqlElem.name = updatedValue;
			 updElem.value = sqlElem;
		}
		
	}

	private static void processOrderbyStmt(SQLQuery sqlObjt, String value, HashMap<String, String> sqlObjts, int currentDBId, int newDBId) 
	{
		String SQLColName = "";
        ArrayList<Token> coltoken = FunctionTypeConv.getTokens(value);
        SQLColName += coltoken.get(0).data +" ";
        boolean checkAliasName = checkValues(coltoken,"as");
		String aliasName = null;
		boolean nodeHasSubQuery = false;
        
	        for (int i = 0; i < coltoken.size(); i++) 
	        {
				if(coltoken.get(i).data.contains("ds__QueryNode"))
				{
					String updColName = getUpdatedColName(sqlObjts,coltoken.get(i).data);
					
					nodeHasSubQuery = checkNodeObjtIsSubquery(sqlObjts.get(coltoken.get(i).data));
					
					if(nodeHasSubQuery == false)
					    SQLColName += " "+ updColName;
					else
					{
						String SQLVal = sqlObjts.get(coltoken.get(i).data);
						updColName = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
						sqlObjt.OrderBy.add(processSelectStmt(SQLVal, coltoken, sqlObjts,aliasName,currentDBId,newDBId,false));
					}
				}
				
				if(i > 0)
				{
					
				  if(!coltoken.get(i).data.contains("ds__QueryNode"))
				  {
					  if(checkAliasName == false)
					  {
						 aliasName = coltoken.get(coltoken.size()-1).data;
						 if(aliasName.contains("ds__QueryNode"))
						 {
							 aliasName = null;
						 }
						 
						 if(i != coltoken.size()-1)
						 {
							 SQLColName += " "+coltoken.get(i).data;
						 }
					  }
					  else
					  {
						  if(i+1 < coltoken.size())
						  {
							  if(coltoken.get(i).data.equalsIgnoreCase("as"))
							  {
								 
							     aliasName = coltoken.get(i+1).data;
							  }
						  }
					  }
					 
				  }
				  
				}
				 
			 }
       
        if(nodeHasSubQuery == false)
        {
        	SQLElement sqlElem = new SQLElement();
        	SQLColName = getUpdatedColName(sqlObjts,SQLColName);
        	sqlElem.name = SQLColName;
        	sqlElem.OrderBy = aliasName;
        	sqlObjt.OrderBy.add(sqlElem);
        }
		
	}

	private static void processJoinStmt(String colListName, SQLQuery sqlObjt,HashMap<String, String> sqlObjts, ArrayList<Token> tokens, ArrayList<SQLJoin> sQLJoin, int currentDBId, int newDBId, ArrayList<String> joinTypList, ArrayList<String> joinTypeLst) 
	{
		
		SQLJoin jn = new SQLJoin();
		ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(colListName);
		String JoinType = null;
		String joinTyp = "";
		
		Token checkJoinType = sqlTkns.get(sqlTkns.size()-1);
		
		String checkJoinTypeData ="";
		if(sqlTkns.size() >= 2)
		{
		  checkJoinTypeData = sqlTkns.get(sqlTkns.size()-2).data;
		}
		
		if(checkJoinTypeData.equalsIgnoreCase("left") || checkJoinTypeData.equalsIgnoreCase("cross") ||
				checkJoinTypeData.equalsIgnoreCase("Outer") || checkJoinTypeData.equalsIgnoreCase("right") || checkJoinTypeData.equalsIgnoreCase("inner") || checkJoinTypeData.equalsIgnoreCase("full"))
		{
			joinTyp += checkJoinTypeData;
		}
		if(checkJoinType.data.equalsIgnoreCase("left") || checkJoinType.data.equalsIgnoreCase("cross") ||
				checkJoinType.data.equalsIgnoreCase("Outer") || checkJoinType.data.equalsIgnoreCase("right") || checkJoinType.data.equalsIgnoreCase("inner") || checkJoinType.data.equalsIgnoreCase("full"))
		{
			joinTyp += " " +checkJoinType.data;
		}
		
		JoinType = joinTyp.trim();
		if(JoinType.equalsIgnoreCase(""))
		{
			if(joinTypeLst != null)
			{
				if(joinTypeLst.size() > 0)
				{
					JoinType = joinTypeLst.get(0);
				}
			}
			else	
				JoinType = null;
		}
		
		jn.joinType = JoinType;
	    getJoincondDtls(jn,sqlTkns,colListName,sqlObjt,sqlObjts,tokens,currentDBId,newDBId);
	    sQLJoin.add(jn);
	}

	private static void getJoincondDtls(SQLJoin jn, ArrayList<Token> sqlTkns, String colListName, SQLQuery sqlObjt, HashMap<String, String> sqlObjts, ArrayList<Token> tokens, int currentDBId, int newDBId)
	{
		boolean checkJoinTbl = checkValues(sqlTkns,"on");
		SQLJoinCond jnCond = new SQLJoinCond();
		String dtlTblDtl  = null;
			if(checkJoinTbl == true)
			{
				colListName = updatedStmts(colListName,"On");
				String[] dtlTbl = colListName.split(" ON ");
				dtlTblDtl = dtlTbl[0];
				 
			}
			else
			{
			    dtlTblDtl = colListName;
			}
			
			ArrayList<Token> coltoken = FunctionTypeConv.getTokens(dtlTblDtl);
		    
	        if(dtlTblDtl.contains("ds__QueryNode"))
			{
	        	String SQLVal = "";
	        	
	        	if(coltoken.get(0).data.contains("ds__QueryNode"))
	        	{
				   SQLVal = sqlObjts.get(coltoken.get(0).data);
	        	}
	        	if(coltoken.size() > 1)
	        	{
	        		if(coltoken.get(1).data.contains("ds__QueryNode"))
		        	{
					   SQLVal = sqlObjts.get(coltoken.get(1).data);
		        	}
	        	}
	        	 boolean nodeHasSubQuery = checkNodeObjtIsSubquery(SQLVal);
	        	 String aliasName = null;
				 if(coltoken.size() > 1)
				 {
					 if(coltoken.size() > 2)
					 {
						 boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
						 boolean checkJoinKeyWords = getJoinSinglecondKeyWord(coltoken.get(2).data);
						 if(checkJoinKeyWord || checkJoinKeyWords)
						 {
							 if(coltoken.get(1).data.equalsIgnoreCase("as"))
								 aliasName = coltoken.get(2).data;
							 else
								 aliasName = coltoken.get(1).data;
						 }
					 }
					 else
					 {
						 boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
						
						 if(checkJoinKeyWord == true)
						 {
						    aliasName = coltoken.get(1).data;
						 }
					 }   
				 }
				 if(nodeHasSubQuery == true)
				 {
			    	//sqlObj.withInParanthesis = true;
			    	SQLVal = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
			    	jnCond.childTable = processSelectStmt(SQLVal, tokens, sqlObjts,aliasName,currentDBId,newDBId,false);
				 }
			     else  
			     {
			    	SQLElement tblElem = new SQLElement();
		         	tblElem.name = coltoken.get(0).data;
		         	
		         	if(!aliasName.contains("ds__QueryNode"))
			    	    tblElem.aliasName = aliasName;
		         	else
		         		tblElem.aliasName = SQLVal;
		         	
		         	jnCond.childTable = tblElem;
			     }
			}
	        else 
	        {
	        	boolean nodeHasSubQuery = checkNodeObjtIsSubquery(dtlTblDtl);
	        	if(nodeHasSubQuery)
	        	{
	        		String aliasName = dtlTblDtl.substring(dtlTblDtl.lastIndexOf(")")+1 , dtlTblDtl.length()).trim();
	        		dtlTblDtl = dtlTblDtl.substring(dtlTblDtl.indexOf("(")+1, dtlTblDtl.lastIndexOf(")")).trim();
	        		SQLQuery subSqlObjt = SQLConverter.processSQl(dtlTblDtl, currentDBId, newDBId);
	        		subSqlObjt.aliasName = aliasName;
	        		jnCond.childTable = subSqlObjt;
	        	}
	        	else
	        	{
		       	  SQLElement tblElem = new SQLElement();
		       	  tblElem.name = coltoken.get(0).data;
			         if(coltoken.size() > 1)
			         {
			        	 if(coltoken.size() > 2)
						 {
			        		 boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
							 boolean checkJoinKeyWords = getJoinSinglecondKeyWord(coltoken.get(2).data);
							 if(checkJoinKeyWord || checkJoinKeyWords)
							 {
								 if(coltoken.get(1).data.equalsIgnoreCase("as"))
									 tblElem.aliasName  = coltoken.get(2).data;
								 else
									 tblElem.aliasName  = coltoken.get(1).data;
							 }
						 }
						 else
						 {
							 boolean checkJoinKeyWord = getJoinSinglecondKeyWord(coltoken.get(1).data);
						     if(checkJoinKeyWord == true)
						     {
						    	 tblElem.aliasName  = coltoken.get(1).data;
						     }
						 }
			         }
			         jnCond.childTable = tblElem;
	        	}
	        }
	    
		jn.joinCondDtls = jnCond;
		
		
		boolean checkJoinDtlTbl = checkValues(sqlTkns,"on");
		if(checkJoinDtlTbl == true)
		{
			colListName = colListName.substring(colListName.indexOf(" ON ")+4, colListName.length()).trim();
		}
		if(colListName.startsWith("ds__QueryNode_"))
		{
		    colListName = getUpdatedJoinCond(colListName, sqlObjts);
		}
		
	    ArrayList<Token> modSqlTkns = FunctionTypeConv.getTokens(colListName);
		boolean checkJoinCond = checkValues(modSqlTkns,"AND");
		if(checkJoinCond == true)
		{
			colListName = updatedStmts(colListName,"and");
			
			String[] dtlTblCond = colListName.split(" AND ");
			for(int i = 0 ; i < dtlTblCond.length ; i++)
			{
				String joinCond = dtlTblCond[i].trim();
				SQLExpr sqlExprCond = new SQLExpr();
				sqlExprCond.type = "And";
				String val = getUpdatedColName(sqlObjts, joinCond);
				sqlExprCond.value = val;
				ArrayList<Token> condNametkn = FunctionTypeConv.getTokens(joinCond);
				checkDsQueryNode(sqlExprCond,joinCond,condNametkn,sqlObjts,tokens,currentDBId,newDBId);
				jn.joinCond.add(sqlExprCond);
				
			 }
		}
		else
		{
			if(checkJoinDtlTbl == true)
			{
				SQLExpr sqlExpr = new SQLExpr();
				sqlExpr.type = "JOIN";
				ArrayList<Token> condNametkn = FunctionTypeConv.getTokens(colListName);
				checkDsQueryNode(sqlExpr,colListName,condNametkn,sqlObjts,tokens,currentDBId,newDBId);	
				jn.joinCond.add(sqlExpr);
			}
		}
		jn.joinCondDtls = jnCond;
		
	}

	private static String getUpdatedJoinCond(String colListName, HashMap<String, String> sqlObjts) 
	{
	    ArrayList<Token> tknNodes = FunctionTypeConv.getTokens(colListName);
	    String updatedVal = "";
	    for(int i = 0 ; i < tknNodes.size() ; i++)
	    {
	    	if(tknNodes.get(i).data.contains("ds__QueryNode_"))
	    	{
	    		boolean checkSubQuery = checkNodeObjtIsSubquery(tknNodes.get(i).data);
	    	    if(!checkSubQuery)
	    	    {
	    	    	String condVals = sqlObjts.get(tknNodes.get(i).data).trim();
	    	    	condVals = condVals.substring(condVals.indexOf("(")+1, condVals.lastIndexOf(")"));
	    	    	updatedVal += " "+condVals; 
	    	    }
	    	    else
	    	    	updatedVal += " "+tknNodes.get(i).data;
	    	}
	    	else
	    		updatedVal += " "+tknNodes.get(i).data;
	    }
	    
	    updatedVal = updatedVal.trim();
	    
		return updatedVal;
	}

	private static void processHavingClause(String colListName,SQLQuery sqlObjt, HashMap<String, String> sqlObjts,ArrayList<Token> tokens, int currentDBId, int newDBId)
	{
		ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(colListName);
		SQLExpr sqlExpr = new SQLExpr();
		SQLExpr sqlExprCond = new SQLExpr();
		sqlExprCond.type = "Having";
		checkDsQueryNode(sqlExprCond,colListName,sqlTkns,sqlObjts,tokens,currentDBId,newDBId);	
		sqlExpr.type = sqlExprCond.type;
		sqlExpr.value = sqlExprCond.value;
	    sqlExpr.args.add(sqlExprCond);
		sqlObjt.havingCond = sqlExpr;
	}

	private static void processGroupByStmts(String colListName,ArrayList<Token> sqlTkns, SQLQuery sqlObjt,HashMap<String, String> sqlObjts, ArrayList<Token> tokens, int currentDBId, int newDBId) 
	{
		if(colListName.contains(","))
		{
			String[] colsName = colListName.split(",");
			
			 for (String colName : colsName) 
			 {
		         processGroupByColsList(sqlObjt,colName.trim(),sqlObjts,currentDBId,newDBId);
			 }
		}
		else
		{     
			processGroupByColsList(sqlObjt,colListName.trim(),sqlObjts,currentDBId,newDBId);
		}
	}

	private static void processGroupByColsList(SQLQuery sqlObjt, String colName,HashMap<String, String> sqlObjts, int currentDBId, int newDBId) 
	{
		String SQLColName = "";
        ArrayList<Token> coltoken = FunctionTypeConv.getTokens(colName);
        SQLColName += coltoken.get(0).data +" ";
        boolean checkAliasName = checkValues(coltoken,"as");
		String aliasName = "";
		boolean nodeHasSubQuery = false;
        for (int i = 0; i < coltoken.size(); i++) 
        {
			if(coltoken.get(i).data.contains("ds__QueryNode"))
			{
				String updColName = getUpdatedColName(sqlObjts,coltoken.get(i).data);
				
				nodeHasSubQuery = checkNodeObjtIsSubquery(sqlObjts.get(coltoken.get(i).data));
				
				if(nodeHasSubQuery == false)
				    SQLColName += " "+ updColName;
				else
				{
					String SQLVal = sqlObjts.get(coltoken.get(i).data);
					updColName = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
					sqlObjt.groupByCols.add(processSelectStmt(SQLVal, coltoken, sqlObjts,aliasName,currentDBId,newDBId,false));
				}
			     //processSubQueryStmt Need to work
				
			}
			
			if(i > 0)
			{
				
			  if(!coltoken.get(i).data.contains("ds__QueryNode"))
			  {
				  if(checkAliasName == false)
				  {
					 aliasName = coltoken.get(coltoken.size()-1).data;
					 if(i != coltoken.size()-1)
					 {
						 SQLColName += " "+coltoken.get(i).data;
					 }
				  }
				  else
				  {
					  if(i+1 < coltoken.size())
					  {
						  if(coltoken.get(i).data.equalsIgnoreCase("as"))
						     aliasName = coltoken.get(i+1).data;
					  }
				  }
				 
			  }
			  
			}
			 
		 }
   
	    if(nodeHasSubQuery == false)
	    {
	    	SQLElement sqlElem = new SQLElement();
	    	SQLColName = getUpdatedColName(sqlObjts,SQLColName);
	    	sqlElem.name = SQLColName;
	    	sqlElem.aliasName = aliasName;
	    	sqlObjt.groupByCols.add(sqlElem);
	    }
	}

	private static void processWhereCondForTbls(String colListName,SQLQuery sqlObjt, HashMap<String, String> sqlObjts,ArrayList<Token> tokens, int currentDBId, int newDBId, String startVal, ArrayList<SQLJoin> sQLJoin, ArrayList<String> joinTypList, ArrayList<Boolean> oraJnTypList) 
	{
	    ArrayList<Token> checkSqlTkns = FunctionTypeConv.getTokens(colListName);
	    
		    if(checkSqlTkns.size() == 1)
		    {
		    	if(checkSqlTkns.get(0).data.contains("ds__QueryNode"))
		    	{
		    		colListName =  sqlObjts.get(checkSqlTkns.get(0).data);
		    	}
		    }
		   if(colListName.startsWith("("))
		   {
			   colListName = colListName.substring(colListName.indexOf("(")+1, colListName.lastIndexOf(")"));
		   }
		
	    ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(colListName);
		boolean checkAndCond = checkValues(sqlTkns,"and");
		
		if(checkAndCond == true)
		{
			colListName = updatedStmts(colListName,"And");
			String[] unionTemp = colListName.split(" AND ");
			SQLExpr sqlExpr = new SQLExpr();
			sqlExpr.type = startVal;//"Where";
			String val = getUpdatedColName(sqlObjts, colListName);
			sqlExpr.value = val;
			
			//LinkedHashMap<String, String> checkJoinMap = new LinkedHashMap<String, String>();
			
			LinkedHashMap<String, ArrayList<SQLObject>> checkJoinMap = new LinkedHashMap<String, ArrayList<SQLObject>>();
			
			for(int i = 0; i < unionTemp.length;i++)
			{
				String condName = unionTemp[i];
				ArrayList<Token> condNametkn = FunctionTypeConv.getTokens(condName);
				String checkJoinTyp_Jnr = getUpdatedColName(sqlObjts, condName);
				ArrayList<Token> jnTyptkn = FunctionTypeConv.getTokens(checkJoinTyp_Jnr);
				
				boolean joinType = checkValues(jnTyptkn,"(+)");
				boolean checkStrConstants = checkStrConstantsForJoinOp(jnTyptkn);
				if (joinType)
				{
					if( currentDBId == ToolTypeConstants.ORACLE && (newDBId != ToolTypeConstants.REDSHIFT))
					{
						if(condName.contains("ds__QueryNode_"))
						{
							if(condNametkn.size() == 1)
							{
								if(condName.startsWith("ds__QueryNode_"))
								{
									condName = sqlObjts.get(condName);
								}
							}
						}
						
						ArrayList<Token> condTknVal = FunctionTypeConv.getTokens(condName);
						boolean checkAndCond_Jnr = checkValues(condTknVal,"and");
						
						if(checkAndCond_Jnr)
						{
							SQLExpr sqlExpr_Jnr = new SQLExpr();
							sqlExpr_Jnr.type = "And";
							
							ArrayList<String> unUsedList = new ArrayList<String>();
							getOracleJoinerTransWithConds(condName, sqlObjts, currentDBId, newDBId, sqlObjt, tokens, sqlExpr, checkJoinMap, joinTypList, unUsedList, sqlExpr_Jnr);
							
							String updatedConds = "";
							for(int k = 0 ; k < unUsedList.size(); k++)
							{
								if(unUsedList.get(k).length() > 0)
								{
									if(k == unUsedList.size()-1)
										updatedConds += unUsedList.get(k) + "  ";
									else
										updatedConds += unUsedList.get(k) + " AND  ";
								}
							}
							
							unUsedList.clear();
							if(updatedConds.length() > 0)
								sqlExpr_Jnr.value = updatedConds;
							
							sqlExpr.args.add(sqlExpr_Jnr);
						}
						else
						{
							ArrayList<String> joinTypeLst = new ArrayList<String>();
							if(checkStrConstants)
							{
								ArrayList<Token> token_cond = FunctionTypeConv.getTokens(condName);
								SQLExpr sqlExprCond = new SQLExpr();
								sqlExprCond.type = "And";
								checkDsQueryNode(sqlExprCond,condName,token_cond,sqlObjts,tokens,currentDBId,newDBId);
								sqlExpr.args.add(sqlExprCond);
							}
							else
							{
								getChildTableNameAndJoinConds(condName, sqlObjt, joinTypList, condTknVal, currentDBId,newDBId, joinTypeLst, checkJoinMap,i, sqlObjts);
								//processJoinStmt(jnCondName, sqlObjt, sqlObjts, tokens, sQLJoin, currentDBId, newDBId, joinTypList, joinTypeLst);
								joinTypeLst.clear();
								//oraJnTypList.add(true);
							}
						}
					}
					else
					{
						SQLExpr sqlExprCond = new SQLExpr();
						sqlExprCond.type = "And";
						checkDsQueryNode(sqlExprCond,condName,condNametkn,sqlObjts,tokens,currentDBId,newDBId);
						sqlExpr.args.add(sqlExprCond);
					}
				}
				else
				{
					if( currentDBId == ToolTypeConstants.ORACLE && (newDBId != ToolTypeConstants.REDSHIFT))
					{
						String updatedcond = "";
						if(condName.contains("ds__QueryNode_"))
						{
							if(condNametkn.size() == 1)
							{
								if(condName.startsWith("ds__QueryNode_"))
								{
									updatedcond = sqlObjts.get(condName);
								}
							}
						}
	
						ArrayList<Token> condTknVal = FunctionTypeConv.getTokens(updatedcond);
						boolean checkAndCond_Jnr = checkValues(condTknVal,"and");
						
						if(checkAndCond_Jnr)
						{
							SQLExpr sqlExpr_Jnr = new SQLExpr();
							sqlExpr_Jnr.type = "And";
							
							ArrayList<String> unUsedList = new ArrayList<String>();
							getOracleJoinerTransWithConds(updatedcond, sqlObjts, currentDBId, newDBId, sqlObjt, tokens, sqlExpr, checkJoinMap, joinTypList, unUsedList, sqlExpr_Jnr);
							
							String updatedConds = "";
							for(int k = 0 ; k < unUsedList.size(); k++)
							{
								if(unUsedList.get(k).length() > 0)
								{
									if(k == unUsedList.size()-1)
										updatedConds += unUsedList.get(k) + "  ";
									else
										updatedConds += unUsedList.get(k) + " AND  ";
								}
							}
							
							unUsedList.clear();
							if(updatedConds.length() > 0)
								sqlExpr_Jnr.value = updatedConds;
							else
								sqlExpr_Jnr.value = getUpdatedColName(sqlObjts, updatedcond);
							
							sqlExpr.args.add(sqlExpr_Jnr);
						}
						else
						{
							SQLExpr sqlExprCond = new SQLExpr();
							sqlExprCond.type = "And";
							checkDsQueryNode(sqlExprCond,condName,condNametkn,sqlObjts,tokens,currentDBId,newDBId);
							sqlExpr.args.add(sqlExprCond);
						}
					}
					else
					{
						SQLExpr sqlExprCond = new SQLExpr();
						sqlExprCond.type = "And";
						checkDsQueryNode(sqlExprCond,condName,condNametkn,sqlObjts,tokens,currentDBId,newDBId);
						sqlExpr.args.add(sqlExprCond);
					}
				}
				
			}
			
			if((currentDBId == ToolTypeConstants.ORACLE && newDBId != ToolTypeConstants.REDSHIFT)&& checkJoinMap != null)
				fillJoinerDtlsForOracle(sqlObjt, joinTypList,  currentDBId,newDBId, checkJoinMap, sQLJoin, sqlObjts, tokens, oraJnTypList);
			
			
			sqlObjt.whereCond = sqlExpr;
		}
		else
		{
			boolean joinType = checkValues(sqlTkns,"(+)");
			if(!joinType)
			{
				SQLExpr sqlExpr = new SQLExpr();
				SQLExpr sqlExprCond = new SQLExpr();
				sqlExprCond.type = startVal;//"Where";
				checkDsQueryNode(sqlExprCond,colListName,sqlTkns,sqlObjts,tokens,currentDBId,newDBId);	
				sqlExpr.type = sqlExprCond.type;
				sqlExpr.value = sqlExprCond.value;
			    sqlExpr.args.add(sqlExprCond);
				sqlObjt.whereCond = sqlExpr;
			}
			else
			{
				
				if(currentDBId == ToolTypeConstants.ORACLE && newDBId != ToolTypeConstants.REDSHIFT)
				{
					//colListName = getChildTableNameAndJoinConds(colListName, sqlObjt, joinTypList, sqlTkns, currentDBId,newDBId, null, null,-1, sqlObjts);
					processJoinStmt(colListName, sqlObjt, sqlObjts, tokens, sQLJoin, currentDBId, newDBId, joinTypList, null);
				}
				else
				{
					SQLExpr sqlExpr = new SQLExpr();
					SQLExpr sqlExprCond = new SQLExpr();
					sqlExprCond.type = startVal;//"Where";
					checkDsQueryNode(sqlExprCond,colListName,sqlTkns,sqlObjts,tokens,currentDBId,newDBId);	
					sqlExpr.type = sqlExprCond.type;
					sqlExpr.value = sqlExprCond.value;
				    sqlExpr.args.add(sqlExprCond);
					sqlObjt.whereCond = sqlExpr;
				}
				
			}
		}
		
	}


	private static void getOracleJoinerTransWithConds(String checkJoinTyp, HashMap<String, String> sqlObjts, int currentDBId, int newDBId, SQLQuery sqlObjt, ArrayList<Token> tokens, SQLExpr sqlExpr, LinkedHashMap<String, ArrayList<SQLObject>> checkJoinMap, ArrayList<String> joinTypList, ArrayList<String> unUsedList, SQLExpr sqlExpr_Jnr) 
	{
		checkJoinTyp = updatedStmts(checkJoinTyp,"And").trim();
		
		if(checkJoinTyp.startsWith("(") && checkJoinTyp.endsWith(")"))
			checkJoinTyp = checkJoinTyp.substring(checkJoinTyp.indexOf("(")+1, checkJoinTyp.lastIndexOf(")"));
		
		String[] unionTemp_Jnr = checkJoinTyp.split(" AND ");
		
		for(int k = 0; k < unionTemp_Jnr.length;k++)
		{
			String condName_Jnr = unionTemp_Jnr[k].trim();
			
			ArrayList<Token> condNametkn = FunctionTypeConv.getTokens(condName_Jnr);
			String checkJoinTyp_Jnr = getUpdatedColName(sqlObjts, condName_Jnr);
			ArrayList<Token> jnTyptkn_Jnr = FunctionTypeConv.getTokens(checkJoinTyp_Jnr);
			boolean joinType_Jnr = checkValues(jnTyptkn_Jnr,"(+)");
			if (joinType_Jnr)
			{
				if(condName_Jnr.contains("ds__QueryNode_"))
				{
					if(condNametkn.size() == 1)
					{
						if(condName_Jnr.startsWith("ds__QueryNode_"))
						{
							condName_Jnr = sqlObjts.get(condName_Jnr);
						}
					}
				}
				
				ArrayList<Token> condTknVal = FunctionTypeConv.getTokens(condName_Jnr);
				boolean checkAndCond_Jnr = checkValues(jnTyptkn_Jnr,"and");
				
				if(checkAndCond_Jnr)
				{
					getOracleJoinerTransWithConds(condName_Jnr.trim(), sqlObjts, currentDBId, newDBId, sqlObjt, tokens, sqlExpr_Jnr, checkJoinMap, joinTypList, unUsedList, sqlExpr_Jnr);
				}
				else
				{
					boolean checkStrConstant_Jnr = checkStrConstantsForJoinOp(jnTyptkn_Jnr);
					ArrayList<String> joinTypeLst = new ArrayList<String>();

					if(checkStrConstant_Jnr)
					{
						SQLExpr sqlExprCond = new SQLExpr();
						sqlExprCond.type = "And";
						checkDsQueryNode(sqlExprCond,condName_Jnr,condNametkn,sqlObjts,tokens,currentDBId,newDBId);
						sqlExpr_Jnr.args.add(sqlExprCond);
						
						String updCondName_Jnr = getUpdatedColName(sqlObjts, condName_Jnr);
						
						if(updCondName_Jnr.length() > 0)
							unUsedList.add(updCondName_Jnr.replace("(+)", "").replace("( + )", ""));
					}
					else
					{
						getChildTableNameAndJoinConds(condName_Jnr, sqlObjt, joinTypList, condTknVal, currentDBId,newDBId, joinTypeLst, checkJoinMap,k, sqlObjts);
						//processJoinStmt(jnCondName, sqlObjt, sqlObjts, tokens, sQLJoin, currentDBId, newDBId, joinTypList, joinTypeLst);
						joinTypeLst.clear();
						//oraJnTypList.add(true);
					}
				}
			}
			else
			{
				SQLExpr sqlExprCond = new SQLExpr();
				sqlExprCond.type = "And";
				checkDsQueryNode(sqlExprCond,condName_Jnr,condNametkn,sqlObjts,tokens,currentDBId,newDBId);
				sqlExpr_Jnr.args.add(sqlExprCond);
			}
		}
		
		
		
	}

	private static boolean checkStrConstantsForJoinOp(ArrayList<Token> jnTyptkn) 
	{
	    for(int i = 0 ; i  < jnTyptkn.size() ; i++)
	    {
	    	if(jnTyptkn.get(i).type.name().equalsIgnoreCase("STRCONSTANTS") || jnTyptkn.get(i).data.matches("\\\\+=|\\\\-=|<>|<=|>=|<|>|!=|==|^=|="))
	    	{
	    		if(jnTyptkn.get(i).data.matches("\\\\+=|\\\\-=|<>|<=|>=|<|>|!=|==|^=|="))
	    		{
	    			if(jnTyptkn.get(i+1).type.name().equalsIgnoreCase("INTEGER"))
	    					return true;
	    		}
	    		else
	    		    return true;
	    	}
	    }
		return false;
	}

	private static void fillJoinerDtlsForOracle(SQLQuery sqlObjt, ArrayList<String> joinTypList, int currentDBId, int newDBId, LinkedHashMap<String, ArrayList<SQLObject>> checkJoinMap, ArrayList<SQLJoin> sQLJoin, HashMap<String, String> sqlObjts, ArrayList<Token> tokens, ArrayList<Boolean> oraJnTypList) 
	{
		LinkedHashMap<String, String> condMap = new LinkedHashMap<String, String>();
		
		String masterTable = declareMasterCompAndProceedForDetail(checkJoinMap, sqlObjt);
		
		ArrayList<String> condList = new ArrayList<String>();
		    
		ArrayList<String> listVal = new ArrayList<String>();
		
			for(Entry<String ,ArrayList<SQLObject>> entryMap : checkJoinMap.entrySet())
			{
				String condType = entryMap.getKey();
				
				String joinType = condType.substring(0, condType.indexOf(";"));
				String condVal = condType.substring(condType.indexOf(";")+1, condType.length());
				
				String[] splitVal = condVal.split("\\\\+=|\\\\-=|<>|<=|>=|<|>|!=|==|^=|=");
				
				for(int i = 0 ; i < splitVal.length; i++)
				{
					ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(splitVal[i]);
					
					for(int j = 0 ; j < sqlTkns.size(); j++)
					{
						if(sqlTkns.get(j).type.name().equalsIgnoreCase("varwithdot"))
						{
							String tableAlias = sqlTkns.get(j).data.substring(0, sqlTkns.get(j).data.lastIndexOf("."));
							if (!listVal.contains(tableAlias.toLowerCase()))
							{
								if(!tableAlias.equalsIgnoreCase(masterTable))
								{
									String childTable = "";
									
									for(int k = 0 ; k < entryMap.getValue().size(); k++)
									{
										SQLElement sqlElem = new SQLElement();
										SQLQuery sqlQuery = new SQLQuery();
										
										if(entryMap.getValue().get(k) instanceof SQLElement)
											sqlElem = (SQLElement) entryMap.getValue().get(k);
										
										if(entryMap.getValue().get(k) instanceof SQLQuery)
											sqlQuery = (SQLQuery) entryMap.getValue().get(k);
										
										if(sqlElem.name != null)
										{
											if(sqlElem.aliasName != null)
											{
												if(sqlElem.aliasName.equalsIgnoreCase(tableAlias))
												{
													childTable = sqlElem.name + " "+sqlElem.aliasName;
												}
											}
											else 
											{
												if(sqlElem.name.equalsIgnoreCase(tableAlias))
												{
													childTable = sqlElem.name;
												}
											}
										}
										
										if(sqlQuery.aliasName !=  null)
										{
											if(sqlQuery.aliasName.equalsIgnoreCase(tableAlias))
											{
												ReportLogInfo info = new ReportLogInfo();
												SQLValue sqlVal = QueryGenerator.fillSQLStatementForDbs(sqlQuery, currentDBId, newDBId, info, null);
												String childTableQuery =sqlVal.sqlValue.replace("\n", "").replace("  ", " ");
												childTableQuery = childTableQuery.substring(childTableQuery.indexOf("(")+1, childTableQuery.lastIndexOf(";"));
												childTableQuery = "( "+childTableQuery + " ) " + sqlQuery.aliasName;
												childTable = childTableQuery;
												
											}
											
										}
									}
									
									listVal.add(tableAlias.toLowerCase());
									System.out.println(joinType + ";" +childTable + " ON " +condVal);
									condMap.put(tableAlias.toLowerCase(), joinType + ";" +childTable + " ON " +condVal);
									condList.add(splitVal[i]);
								}
							}
							else
							{
							
								for(Entry<String, String> entryset : condMap.entrySet())
								{
									if(entryset.getKey().equalsIgnoreCase(tableAlias))
									{
										condVal = entryset.getValue() + " And " +condVal;
										entryset.setValue(condVal);
									}
								}				
							}
							
						}
					}
				}
			}
			

			
			for(Entry<String,String> entryVal : condMap.entrySet())
			{
				String value = entryVal.getValue();
				System.out.println(value);
				if(value.length() > 0)
				{
					String joinType = value.substring(0, value.indexOf(";")).trim();
					String jnCondName = value.substring(value.indexOf(";")+1, value.length()).trim();
					
					ArrayList<String> joinTypeLst = new ArrayList<String>();
	
					if(joinTypList != null)
						joinTypList.add(joinType);
					
				    if(joinTypeLst != null)
				    	joinTypeLst.add(joinType);
				   
					processJoinStmt(jnCondName, sqlObjt, sqlObjts, tokens, sQLJoin, currentDBId, newDBId, joinTypList, joinTypeLst);
					
					joinTypeLst.clear();
					oraJnTypList.add(true);
				}
			}
			
			condMap.clear();
			
			
			
//			String value = entryMap.getValue();
//			
//			String tableNameWithAlias = keyVal.substring(0, keyVal.indexOf(";"));
//			ArrayList<Token> tokenVal = FunctionTypeConv.getTokens(tableNameWithAlias);
//		
//			if(tokenVal.size() > 0)
//			{
//		         if(tokenVal.get(0).data.equalsIgnoreCase("dsOldTable"))
//		         {
//		        	 String updatedVal = "";
//		        	 if(value.length() > 0)
//		        	     updatedVal = value.substring(value.indexOf(" ON ")+4, value.length());
//		        	 
//		        	 if(checkJoinMap.get("dsKnwnTable "+tokenVal.get(1).data.toLowerCase()+";"+true) != null)
//		        	 {
//			        	 String oldVal = checkJoinMap.get("dsKnwnTable "+tokenVal.get(1).data.toLowerCase()+";"+true);
//			        	 for(Entry<String ,String> entryNewMap : checkJoinMap.entrySet())
//			        	 {
//			        		 if(entryNewMap.getKey().equalsIgnoreCase("dsKnwnTable "+tokenVal.get(1).data.toLowerCase()+";"+true))
//			        		 {
//			        			 if(oldVal.length() > 0 && updatedVal.length() > 0)
//			        			 {
//			        				entryNewMap.setValue(oldVal+ " AND " + updatedVal);
//			        			 }
//			        			 else
//			        			 {
//			        				 if(updatedVal.length() > 0)
//			        					 entryNewMap.setValue(value);
//			        			 }
//			        		 }
//			        	 }
//			        	 entryMap.setValue(null);
//		        	 }
//		         }
//			}
//			
//		}
//		
//		for(Entry<String ,String> entryMap : checkJoinMap.entrySet())
//		{
//			String keyVal = entryMap.getKey();
//			
//			
//			String checknwnTbl = keyVal.substring(0,keyVal.indexOf(";")).trim();
//			
//			if(checknwnTbl.contains("dsKnwnTable ") || (checknwnTbl.contains("dsOldTable ") && entryMap.getValue() != null))
//			{
//				String value = entryMap.getValue();
//				if(value.length() > 0)
//				{
//					String joinType = value.substring(0, value.indexOf(";")).trim();
//					String jnCondName = value.substring(value.indexOf(";")+1, value.length()).trim();
//					ArrayList<String> joinTypeLst = new ArrayList<String>();
//	
//					if(joinTypList != null)
//						joinTypList.add(joinType);
//					
//				    if(joinTypeLst != null)
//				    	joinTypeLst.add(joinType);
//				   
//					processJoinStmt(jnCondName, sqlObjt, sqlObjts, tokens, sQLJoin, currentDBId, newDBId, joinTypList, joinTypeLst);
//					
//					joinTypeLst.clear();
//					oraJnTypList.add(true);
//				}
//			}
		//}
		
		//checkJoinMap.clear();
		
	}

	private static String declareMasterCompAndProceedForDetail(LinkedHashMap<String, ArrayList<SQLObject>> checkJoinMap, SQLQuery sqlObjt)
	{	
	    LinkedHashMap<String, Integer> aliasList = new LinkedHashMap<String, Integer>();

	    ArrayList<String> condList = new ArrayList<String>();
	    
	    ArrayList<String> listVal = new ArrayList<String>();
	    
		int cnt = 1;
		for(Entry<String ,ArrayList<SQLObject>> entryMap : checkJoinMap.entrySet())
		{
			String condType = entryMap.getKey();
			
			String condVal = condType.substring(condType.indexOf(";")+1, condType.length());
			
			String[] splitVal = condVal.split("\\\\+=|\\\\-=|<>|<=|>=|<|>|!=|==|^=|=");
			
			for(int i = 0 ; i < splitVal.length; i++)
			{
				ArrayList<Token> sqlTkns = FunctionTypeConv.getTokens(splitVal[i]);
				
				for(int j = 0 ; j < sqlTkns.size(); j++)
				{
					if(sqlTkns.get(j).type.name().equalsIgnoreCase("varwithdot"))
					{
						String tableAlias = sqlTkns.get(j).data.substring(0, sqlTkns.get(j).data.lastIndexOf("."));
						if (!listVal.contains(tableAlias.toLowerCase()))
						{
							listVal.add(tableAlias.toLowerCase());
							aliasList.put(tableAlias.toLowerCase(), cnt);
							condList.add(splitVal[i]);
						}
						else
						{
							for(Entry<String, Integer> entryset : aliasList.entrySet())
							{
								if(entryset.getKey().equalsIgnoreCase(tableAlias))
								{
									cnt = entryset.getValue() + 1;
									entryset.setValue(cnt);
									cnt = 1;
								}
							}
//					
						}
						
					}
				}
			}
		}
		
		ArrayList<Integer> countList = new ArrayList<Integer>();
		
		for(Entry<String, Integer> entryset : aliasList.entrySet())
		{
			countList.add(entryset.getValue());
		}
		
		int val = 0;
		
		if(countList.size() > 0)
			val = Collections.max(countList);
		
		String checkMasterTable = "";
		for (Map.Entry<String, Integer> entry : aliasList.entrySet()) {
			if (val == entry.getValue()) 
            {
            	checkMasterTable = entry.getKey();
            	break;
			}
			
		}
		
		
		if(sqlObjt.tableList.size() == 0)
		{
			SQLObject masterSqlObject = null;
		
			for(Entry<String ,ArrayList<SQLObject>> entryMap : checkJoinMap.entrySet())
			{
				for(int k = 0 ; k < entryMap.getValue().size(); k++)
				{
					SQLElement sqlElem = new SQLElement();
					SQLQuery sqlQuery = new SQLQuery();
					
					if(entryMap.getValue().get(k) instanceof SQLElement)
						sqlElem = (SQLElement) entryMap.getValue().get(k);
					
					if(entryMap.getValue().get(k) instanceof SQLQuery)
						sqlQuery = (SQLQuery) entryMap.getValue().get(k);
					
					if(sqlElem.name != null)
					{		
						if(sqlElem.aliasName != null)
						{
							if(sqlElem.aliasName.equalsIgnoreCase(checkMasterTable))
							{
								masterSqlObject = sqlElem;
								break;
							}
						}
						else
						{
							if(sqlElem.name.equalsIgnoreCase(checkMasterTable))
							{
								masterSqlObject = sqlElem;
								break;
							}
						}
					}
					if(sqlQuery.aliasName !=  null)
					{
						if(sqlQuery.aliasName.equalsIgnoreCase(checkMasterTable))
						{
							masterSqlObject = sqlQuery;
							break;
						}
						
					}
				}
				
			}
			
			sqlObjt.tableList.add(masterSqlObject);
		}		
		
		return checkMasterTable;
		
	}

	private static void getChildTableNameAndJoinConds(String colListName, SQLQuery sqlObjt, ArrayList<String> joinTypList, ArrayList<Token> sqlTkns, int currentDBId, int newDBId, ArrayList<String> joinTypeLst, LinkedHashMap<String, ArrayList<SQLObject>> checkJoinMap, int count, HashMap<String, String> sqlObjts) 
	{
		if(colListName.contains("=") || colListName.contains(">") || colListName.contains("<"))
		{
			colListName = getUpdatedCondVal(colListName, sqlObjts).trim();

			if(colListName.startsWith("("))
				colListName = colListName.substring(colListName.indexOf("(")+1,colListName.lastIndexOf(")")).trim();
			ArrayList<Token> sqlTkn_Jnr = FunctionTypeConv.getTokens(colListName);
			
			
			String[] splitVal = colListName.split("\\\\+=|\\\\-=|<>|<=|>=|<|>|!=|==|^=|=");
			
			String prevCond = splitVal[0];
			String afterCond = splitVal[1];
			
			ArrayList<Token> prevsqlTkns = FunctionTypeConv.getTokens(prevCond);
			ArrayList<Token> aftersqlTkns = FunctionTypeConv.getTokens(afterCond);
			 
			boolean beforejoinType = checkValues(prevsqlTkns,"(+)");
			boolean afterJoinType = checkValues(aftersqlTkns,"(+)");
			String joinType = "";
			if(beforejoinType)
				joinType = "RIGHT";
			if(afterJoinType)
				joinType = "LEFT"; 
			
			if(joinTypList != null)
				joinTypList.add(joinType);
			
		    if(joinTypeLst != null)
		    	joinTypeLst.add(joinType);
			
			String childTable = "";
			boolean checkchildTable = false;
			boolean checksubQuery = false;
			
//			for(int i = 0 ;i < aftersqlTkns.size() ;i++)
//			{
//				if(aftersqlTkns.get(i).type.name().equalsIgnoreCase("varwithdot"))
//				{
//					String tblName = aftersqlTkns.get(i).data;
//					childTable = tblName.substring(0,tblName.lastIndexOf(".")).trim();
//				}
//			}
//			
//			if(childTable.equalsIgnoreCase(""))
//			{
//				for(int i = 0 ;i < prevsqlTkns.size() ;i++)
//				{
//					if(prevsqlTkns.get(i).type.name().equalsIgnoreCase("varwithdot"))
//					{
//						String tblName = prevsqlTkns.get(i).data;
//						childTable = tblName.substring(0,tblName.lastIndexOf(".")).trim();
//					}
//				}
//			}
			
			String masterTable = "";
			String updatedCondVal = "";
			
			String tableVals = "";
			
			ArrayList<SQLObject> sqlTableObjts = new ArrayList<SQLObject>();
			
			for(int i1 = 0 ; i1 < sqlTkn_Jnr.size() ; i1++)
			{
				if(!sqlTkn_Jnr.get(i1).type.name().equalsIgnoreCase("JOINTYPE"))
				{
					updatedCondVal += sqlTkn_Jnr.get(i1).data + " ";
				}
			}
			
			if(sqlObjt.tableList.size() > 0)
			{
					for(int j = 0 ; j  < sqlTkn_Jnr.size() ; j++)
					{

						for(int i = 0 ; i < sqlObjt.tableList.size(); i++)
						{
							SQLElement sqlElem = new SQLElement();
							if(sqlObjt.tableList.get(i) instanceof SQLElement)
								sqlElem  = (SQLElement) sqlObjt.tableList.get(i);
							SQLQuery query = new SQLQuery();
							if(sqlObjt.tableList.get(i) instanceof SQLQuery)
								query  = (SQLQuery) sqlObjt.tableList.get(i);
							
							if(sqlElem.name != null)
							{
								if(sqlElem.aliasName != null)
								{
									if(sqlTkn_Jnr.get(j).type.name().equalsIgnoreCase("varwithdot"))
									{
										String tblName = sqlTkn_Jnr.get(j).data;
										String checkTable = tblName.substring(0,tblName.lastIndexOf(".")).trim();
						
										if(sqlElem.aliasName.equalsIgnoreCase(checkTable))
										{
											sqlTableObjts.add(sqlElem);
											sqlObjt.tableList.remove(i);
										}
									}
									
									
		//							if(sqlElem.aliasName.equalsIgnoreCase(childTable))
		//							{
		//								childTable = sqlElem.name + " " + sqlElem.aliasName;
		//								sqlObjt.tableList.remove(i);
		//								checkchildTable = true;
		//							}
									
									
		//							else
		//							{
		//								masterTable = sqlElem.aliasName;
		//							}
								}
								else
								{
	//								if(sqlElem.name.equalsIgnoreCase(childTable))
	//								{
	//									childTable = sqlElem.name;
	//									sqlObjt.tableList.remove(i);
	//									checkchildTable = true;
	//								}
									
									if(sqlTkn_Jnr.get(j).type.name().equalsIgnoreCase("varwithdot"))
									{
										String tblName = sqlTkn_Jnr.get(j).data;
										String checkTable = tblName.substring(0,tblName.lastIndexOf(".")).trim();
										
										if(sqlElem.name.equalsIgnoreCase(checkTable))
										{
											sqlTableObjts.add(sqlElem);
											sqlObjt.tableList.remove(i);
										}
									}
		//							else
		//							{
		//								masterTable = sqlElem.name;
		//							}
								}
							}
					
					
						if(query.dmlType != null)
						{
							if(query.aliasName != null)
							{
//								if(query.aliasName.equalsIgnoreCase(childTable))
//								{
	//								ReportLogInfo info = new ReportLogInfo();
	//								SQLValue sqlVal = QueryGenerator.fillSQLStatementForDbs(query, currentDBId, newDBId, info, null);
	//								String childTableQuery =sqlVal.sqlValue.replace("\n", "").replace("  ", " ");
	//								childTableQuery = childTableQuery.substring(childTableQuery.indexOf("(")+1, childTableQuery.lastIndexOf(";"));
	//								childTableQuery = "( "+childTableQuery + " ) " + query.aliasName;
	//								childTable = childTableQuery;
									
//									sqlObjt.tableList.remove(i);
	//								checkchildTable = true;
	//								checksubQuery = true;
//								}
								
								if(sqlTkn_Jnr.get(j).type.name().equalsIgnoreCase("varwithdot"))
								{
									String tblName = sqlTkn_Jnr.get(j).data;
									String checkTable = tblName.substring(0,tblName.lastIndexOf(".")).trim();
									
									if(query.aliasName.equalsIgnoreCase(checkTable))
									{
										sqlTableObjts.add(query);
										sqlObjt.tableList.remove(i);
									}
								}
								
							}
						}
					}
					
					
				}
			}
			
//			if(masterTable.length() > 0)
//			{
//				checkJoinMap.put("dsMasterTable"+";"+masterTable, "");
//			}
//			
//            if(childTable.equalsIgnoreCase(""))
//            {
//            	childTable = "DS.ErrorTempTable" +" "+ "--DSErrorMsg_Require_ManualIntervention ";
//            }
            
//            String keyVal = "";
//            if(!checkchildTable && childTable.length() > 0)
//            {
//            	keyVal = "dsOldTable "+childTable+" "+count;
//            }
//            else
//            {
//            	ArrayList<Token> token = FunctionTypeConv.getTokens(childTable);
//            	
//            	if(token.size() == 2)
//            		keyVal = "dsKnwnTable "+token.get(1).data.toLowerCase();
//            	else
//            	{
//            		if(checksubQuery)
//            			keyVal = "dsKnwnTable "+token.get(token.size()-1).data.toLowerCase();
//            		else
//            			keyVal = "dsKnwnTable "+token.get(0).data.toLowerCase();
//            	}
//            	
//            }
            
//        	condList = childTable + " ON " + updatedCondVal;
//        	
//            ArrayList<Token> jnTyptkn = FunctionTypeConv.getTokens(updatedCondVal);
//            
//        	boolean checkStrConstants = checkStrConstantsForJoinOp(jnTyptkn); 
//        	
//        	String keyValue = joinType+";"+condList;
//        	if(checkStrConstants)
//        		keyValue = "";
        	
        	//if(checkJoinMap != null)
			
    		checkJoinMap.put(joinType+";"+updatedCondVal, sqlTableObjts);
        	
		}
		
	}

	private static String getUpdatedCondVal(String colListName, HashMap<String, String> sqlObjts) {
		
		String condVal = "";
		ArrayList<Token> tokenVal = FunctionTypeConv.getTokens(colListName);
		
		for(int j =0 ; j < tokenVal.size() ; j++)
		{
			if(tokenVal.get(j).data.contains("ds__QueryNode_"))
			{
				if(!checkNodeObjtIsSubquery(sqlObjts.get(tokenVal.get(j).data)))
				{
					condVal += " "+sqlObjts.get(tokenVal.get(j).data);
				}
				else
					condVal += " "+tokenVal.get(j).data;	
			}
			else
			{
				condVal += " "+tokenVal.get(j).data;
			}
		}
		
		return condVal;
	}

	private static void checkDsQueryNode(SQLExpr sqlExpr, String colListName,
			ArrayList<Token> sqlTkns, HashMap<String, String> sqlObjts, ArrayList<Token> tokens, int currentDBId, int newDBId) 
	{
		if(colListName.contains("ds__QueryNode"))
		{
			 String whereCond = "";
			 String inNotInOperater =""; 
			 boolean checkInCond = false; 
				
			 for(int i = 0 ; i < sqlTkns.size() ; i++)
			 {
				 if(sqlTkns.get(i).data.contains("ds__QueryNode"))
				 {
					 String getSQLValue = sqlObjts.get(sqlTkns.get(i).data);
					 boolean nodeHasSubQuery = checkNodeObjtIsSubquery(getSQLValue);
					   if(nodeHasSubQuery == true)
					   {
					    	String aliasName = "";
					    	whereCond +=" " +getSQLValue; 
					    	getSQLValue = getSQLValue.substring(getSQLValue.indexOf("(")+1, getSQLValue.length()-1)+";";
					    	sqlExpr.args.add(processSelectStmt(getSQLValue, tokens, sqlObjts,aliasName,currentDBId,newDBId,false));
					   }
					   else
					   {
						   inNotInOperater += " "+getSQLValue;
						    whereCond +=" " +getSQLValue; 
					   }
				 }
				 else
				 {
					 boolean checkJoinCondKey = getJoinSinglecondKeyWord(sqlTkns.get(i).data);
					
					 if(checkJoinCondKey == true)
					 {
						 if(!(sqlTkns.get(i).data.equalsIgnoreCase(";")))
						 {
							 whereCond += " "+sqlTkns.get(i).data;
						 }
						
					 }
					 if(sqlTkns.get(i).data.equalsIgnoreCase("IN") || sqlTkns.get(i).data.equalsIgnoreCase("NOT"))
                     {
                            inNotInOperater += " "+sqlTkns.get(i).data;
                            
                            if(sqlTkns.get(i).data.equalsIgnoreCase("IN"))
                                   checkInCond = true; 
                            
                     }

					 
				 }
			
			 }
			 //Added for In and Not In
			 if(checkInCond)
             {
                   SQLExpr sqlExpr_InOp = new SQLExpr();
                   sqlExpr_InOp.type = "";
                   sqlExpr_InOp.value = inNotInOperater;
                   sqlExpr.args.add(sqlExpr_InOp);
             }

			 
			 String notCond = updatedStmts(whereCond,"NOT");
			 String existCond = updatedStmts(notCond,"EXISTS");
			 
			 if(existCond.startsWith("NOT EXISTS") || existCond.startsWith("EXISTS"))
			 {
				 String Existcond = "";
				 if(existCond.contains("NOT EXISTS"))
				    Existcond = existCond.substring(0,11);
				 else
					Existcond = existCond.substring(0,6);
				 sqlExpr.type = sqlExpr.type + " " + Existcond;
			 }
			 
			 String  updWhereCond = getUpdatedColName(sqlObjts,whereCond);
			 if(newDBId == ToolTypeConstants.DS_TOOL)
			 {
				 if(!updWhereCond.contains("ds"))
					 updWhereCond = FunctionTypeConv.getCurrentFunctions(updWhereCond, currentDBId, newDBId);
			 }
			 else
				 updWhereCond = FunctionTypeConv.getCurrentFunctions(updWhereCond, currentDBId, newDBId);
			 
			 if(currentDBId == ToolTypeConstants.ORACLE && newDBId != ToolTypeConstants.REDSHIFT)
				 updWhereCond = updWhereCond.replace("(+)", "");
			 
			 sqlExpr.value = updWhereCond;
		}
		else
		{
			
			 String updColListName = getUpdatedColName(sqlObjts,colListName);
			 String whereCond = "";
			 ArrayList<Token> tkn = FunctionTypeConv.getTokens(updColListName);
			 for (int i = 0; i < tkn.size(); i++) 
			 {
				 boolean checkJoinCondKey = getJoinSinglecondKeyWord(tkn.get(i).data);
				 if(checkJoinCondKey == true)
				 {
					 if(!(sqlTkns.get(i).data.equalsIgnoreCase(";")))
					 {
						 whereCond += " "+tkn.get(i).data;
					 }
				 }
			
		     }
			 
			 String notCond = updatedStmts(whereCond,"NOT");
			 String existCond = updatedStmts(notCond,"EXISTS");
			 
			 if(existCond.startsWith("NOT EXISTS") || existCond.startsWith("EXISTS"))
			 {
				 String Existcond = "";
				 if(existCond.contains("NOT EXISTS"))
				    Existcond = existCond.substring(0,11);
				 else
					Existcond = existCond.substring(0,6);
				 sqlExpr.type = sqlExpr.type + " " + Existcond;
			 }
			 
			 String updCondName = getUpdatedColName(sqlObjts,whereCond);
			 
			 if(newDBId == ToolTypeConstants.DS_TOOL)
			 {
				 if(!updCondName.contains("ds"))
					 updCondName = FunctionTypeConv.getCurrentFunctions(updCondName, currentDBId, newDBId);
			 }
			 else
				 updCondName = FunctionTypeConv.getCurrentFunctions(updCondName, currentDBId, newDBId);
			 
			 if(currentDBId == ToolTypeConstants.ORACLE && newDBId != ToolTypeConstants.REDSHIFT)
				 updCondName = updCondName.replace("(+)", "");
			 
			 sqlExpr.value = updCondName;
		}
	}

	private static HashMap<Token,Token> getKeywordClauseForStmts(String insrtStmts, ArrayList<Token> sqlTkns) 
	{
		LinkedHashMap<Token,Token> tokenNode = new LinkedHashMap<Token,Token>();
		HashMap<String,String> sqlClauseKeyWords = getSQLCaluseKeyWords();
		ArrayList<Token> keyWordsLst = new ArrayList<Token>();
		for (int i = 0; i < sqlTkns.size(); i++) 
		{
			if(sqlClauseKeyWords.get(sqlTkns.get(i).data.toUpperCase())  != null)
			{
				keyWordsLst.add(sqlTkns.get(i));
			}
		}
		for(int i = 0 ; i+1 < keyWordsLst.size() ;i++)
		{
			tokenNode.put(keyWordsLst.get(i), keyWordsLst.get(i+1));
		}
		return tokenNode;
	   
	}
	 public static HashMap<Token,Token> getKeywordDecSep(String insrtStmts, ArrayList<Token> sqlTkns) 
	{
		LinkedHashMap<Token,Token> tokenNode = new LinkedHashMap<Token,Token>();
		ArrayList<Token> keyWordsLst = new ArrayList<Token>();
		for (int i = 0; i < sqlTkns.size(); i++) 
		{
			if(sqlTkns.get(i).type.name().equalsIgnoreCase("DECSEP"))
			{
				keyWordsLst.add(sqlTkns.get(i));
			}
		}
		tokenNode.put(sqlTkns.get(0),keyWordsLst.get(0));
		for(int i = 0 ; i+1 < keyWordsLst.size() ;i++)
		{
		   tokenNode.put(keyWordsLst.get(i), keyWordsLst.get(i+1));
		}
		if(keyWordsLst.size() == 1)
		{
		   tokenNode.put(keyWordsLst.get(0),sqlTkns.get(sqlTkns.size()-1));
		}
		else
		{
		   tokenNode.put(keyWordsLst.get(keyWordsLst.size()-1), sqlTkns.get(sqlTkns.size()-1));
		}
		
		return tokenNode;
	   
	}

	private static HashMap<String, String> getSQLCaluseKeyWords() 
	{
		LinkedHashMap<String, String> sqlCaluseKeyWord = new LinkedHashMap<String,String>();
		sqlCaluseKeyWord.put("CALL", "call");
		sqlCaluseKeyWord.put("CREATE", "create");
		sqlCaluseKeyWord.put("TABLE", "table");
		sqlCaluseKeyWord.put("GRANT", "grant");
		sqlCaluseKeyWord.put("SELECT", "select");
		sqlCaluseKeyWord.put("INSERT", "insert");
		sqlCaluseKeyWord.put("INTO", "into");
		sqlCaluseKeyWord.put("VALUES", "values");
		sqlCaluseKeyWord.put("UPDATE", "update");
		sqlCaluseKeyWord.put("MERGE", "merge");
		sqlCaluseKeyWord.put("USING", "using");
		sqlCaluseKeyWord.put("SET", "set");
		sqlCaluseKeyWord.put("DELETE", "delete");
		sqlCaluseKeyWord.put("DROP", "drop");
		sqlCaluseKeyWord.put("TRUNCATE", "truncate");
		sqlCaluseKeyWord.put("FROM", "from");
		sqlCaluseKeyWord.put("WHERE", "where");
		sqlCaluseKeyWord.put("QUALIFY", "qualify");
		sqlCaluseKeyWord.put("GROUP", "group");
		sqlCaluseKeyWord.put("HAVING", "having");
		sqlCaluseKeyWord.put("JOIN", "join");
		sqlCaluseKeyWord.put("UNION", "union");
		sqlCaluseKeyWord.put("MINUS", "minus");
		sqlCaluseKeyWord.put("ORDER", "order");
		sqlCaluseKeyWord.put(";",";");
		
		return sqlCaluseKeyWord;
	}

	private static void processColsList(SQLQuery sqlObjt, String colName, HashMap<String, String> sqlObjts, int currentDBId, int newDBId) 
	{
		String SQLColName = "";
        ArrayList<Token> coltoken = FunctionTypeConv.getTokens(colName);
        if (coltoken.size() > 0)
        {
	        if(!coltoken.get(0).data.contains("ds__QueryNode"))
	        	SQLColName += coltoken.get(0).data +" ";
        }
        boolean checkAliasName = checkValues(coltoken,"as");
		String aliasName = null;
		boolean nodeHasSubQuery = false;
        
	        for (int i = 0; i < coltoken.size(); i++) 
	        {
				if(coltoken.get(i).data.contains("ds__QueryNode"))
				{
					String updColName = getUpdatedColName(sqlObjts,coltoken.get(i).data);
					
					nodeHasSubQuery = checkNodeObjtIsSubquery(sqlObjts.get(coltoken.get(i).data));
					
					if(nodeHasSubQuery == false)
					    SQLColName += " "+ updColName;
					else
					{
						//sqlObj.withInParanthesis = true;
						String SQLVal = sqlObjts.get(coltoken.get(i).data);
						
						if(coltoken.get(coltoken.size()-1).data.length() > 0)
						{
							if(!coltoken.get(coltoken.size()-1).data.contains("ds__QueryNode"))
								aliasName = coltoken.get(coltoken.size()-1).data;
						}
						
						updColName = SQLVal.substring(SQLVal.indexOf("(")+1, SQLVal.length()-1)+";";
						sqlObjt.colsList.add(processSelectStmt(updColName, coltoken, sqlObjts,aliasName,currentDBId,newDBId,false));
					}
				     //processSubQueryStmt Need to work
					
				}
				
				if(i > 0)
				{
				  if(!coltoken.get(i).data.contains("ds__QueryNode"))
				  {
					  if(checkAliasName == false)
					  {
						 aliasName = coltoken.get(coltoken.size()-1).data;
						 
						  if(aliasName.equalsIgnoreCase("END"))
                          {
                             if(coltoken.get(i).data.equalsIgnoreCase("END"))
                             {
                                    SQLColName += " "+aliasName;
                                    aliasName = null;
                             }
                          }
                          if(coltoken.size() > 2)
                          {
                             if(aliasName != null)
                             {
                                    if(coltoken.get(coltoken.size()-2).data.equalsIgnoreCase("||"))
                                    {
                                          if(coltoken.get(coltoken.size()-1).data.equalsIgnoreCase(coltoken.get(i).data))
                                          {
                                                  SQLColName += " "+aliasName;
                                                  aliasName = null;
                                          }
                                    }
                             }
                          }
						 if(aliasName != null)
						 {
							 if(coltoken.size() == 2)
							 {
								 if(SQLColName.trim().endsWith("."))
								 {
									 SQLColName += aliasName;
									 aliasName = null;
								 }
							 }
							 
							 if(aliasName != null)
							 {
								 if(aliasName.contains("ds__QueryNode"))
								 {
									 aliasName = null;
								 }
							 }
						 }
						
						 if(i != coltoken.size()-1)
						 {
							 SQLColName += " "+coltoken.get(i).data;
						 }
						
					  }
					  else
					  {
						  if(i+1 < coltoken.size())
						  {
							  if(coltoken.get(i).data.equalsIgnoreCase("as"))
							     aliasName = coltoken.get(i+1).data;
							  else
								 SQLColName += " "+coltoken.get(i).data;
						  }
						  
					  }
					 
				  }
				  
				}
				 
			 }
       
	       
	        
        if(nodeHasSubQuery == false)
        {
        	SQLElement sqlElem = new SQLElement();
        	SQLColName = getUpdatedColName(sqlObjts,SQLColName);
        	if(newDBId == ToolTypeConstants.AZURESQL)
        	{
        		SQLColName = SQLColName.replace("(date,format 'yyyy-mm-dd')", "");
        	}
        	SQLColName = FunctionTypeConv.getCurrentFunctions(SQLColName, currentDBId, newDBId);
        	sqlElem.name = SQLColName;
        	sqlElem.aliasName = aliasName;
        	sqlObjt.colsList.add(sqlElem);
        }
		
	}

	public static  String getUpdatedColName(HashMap<String, String> sqlObjts,String colName) 
	{
		ArrayList<Token> coltoken = FunctionTypeConv.getTokens(colName);
		String ColName = "";
		for(int  i = 0 ; i < coltoken.size(); i++)
		{
			if(coltoken.get(i).data.contains("ds__QueryNode"))
			{
				String updatedColName = sqlObjts.get(coltoken.get(i).data);
			    ColName += " "+updatedColName;	
			}
			else
				ColName += " "+coltoken.get(i).data;
		}
		if(ColName.contains("ds__QueryNode"))
		{
			return getUpdatedColName(sqlObjts, ColName);
		}
		ColName = ColName.replace("( ", "(").replace(" (", "(").replace(" )", ")")
					.replace("[ ", "[").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
					.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").trim();
		
		return ColName;
	}

	

	private static String getInsrtStmt(HashMap<String, String> sqlObjts)
	{
		String last_Entry = "";
		for(Entry<String,String> entry : sqlObjts.entrySet())
		{
			last_Entry = entry.getValue();
			
		}
		return last_Entry;
	}

	public static boolean checkValues(ArrayList<Token> tokens, String string) 
	{
		for (int i = 0; i < tokens.size(); i++) 
		{
			if(tokens.get(i).data.equalsIgnoreCase(string))
				return true;
		}
	
		return false;
	}
	
	
	public static String getIndexPositionForToken(Token key, Token value, ArrayList<Token> token) 
	{
		boolean mainFlag = false;
		boolean flag = false;
		String fn = "";
		
		for (int i = 0; i < token.size(); i++) 
		{
		   if(key == token.get(i))
		   {
			   mainFlag  = true;
		   }
		  
		    if(mainFlag == true)
			{
				if(mainFlag == true && flag == false)
				{
					flag = true;
				}
				if(flag == true)
				{
			    	fn += " "+token.get(i).data;
					 
	        		if(value == token.get(i))   
		        	{
	        			flag = false;
	        			return fn;
		        	}	
	        		
				}
				else
					mainFlag = false;
					
			}
		}
		return null;
		
	}
	
	public static String generateSQL(ArrayList<Token> token) {
		String sqlVal = "";
		for (int i = 0; i < token.size(); i++) {
			sqlVal += token.get(i).data + " ";
		}
		return sqlVal;
	}

	
}