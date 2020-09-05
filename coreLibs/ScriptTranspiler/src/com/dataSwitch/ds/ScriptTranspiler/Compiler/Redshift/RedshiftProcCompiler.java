
package com.dataSwitch.ds.ScriptTranspiler.Compiler.Redshift;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.Script.StatementObjts.dsAssignment;
import com.dataSwitch.ds.Script.StatementObjts.dsCallExecute;
import com.dataSwitch.ds.Script.StatementObjts.dsCollectStats;
import com.dataSwitch.ds.Script.StatementObjts.dsCommit;
import com.dataSwitch.ds.Script.StatementObjts.dsCondition;
import com.dataSwitch.ds.Script.StatementObjts.dsCursor;
import com.dataSwitch.ds.Script.StatementObjts.dsDeclaration;
import com.dataSwitch.ds.Script.StatementObjts.dsDoWhile;
import com.dataSwitch.ds.Script.StatementObjts.dsException;
import com.dataSwitch.ds.Script.StatementObjts.dsField;
import com.dataSwitch.ds.Script.StatementObjts.dsFor;
import com.dataSwitch.ds.Script.StatementObjts.dsIF;
import com.dataSwitch.ds.Script.StatementObjts.dsProc;
import com.dataSwitch.ds.Script.StatementObjts.dsQuery;
import com.dataSwitch.ds.Script.StatementObjts.dsRaise;
import com.dataSwitch.ds.Script.StatementObjts.dsThrows;
import com.dataSwitch.ds.Script.StatementObjts.dsWhile;
import com.dataSwitch.ds.ScriptTranspiler.Compiler.CompilerUtils;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLGenerator.QueryGenerator;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;

public class RedshiftProcCompiler 
{
	public static void getProcValues(String fileType, LinkedList<iStatement> sqlQryList, iStatement istatDtls, int newDbId, int currentDbId, String outpath, String filename, String configJsonPath, Console console, LinkedHashMap<String, LinkedHashMap<String, String>> configHash)
	{
		ReportLogInfo rptLogInfo = new ReportLogInfo();
		LinkedHashMap<ArrayList<String>,Integer> manualChangesList = new LinkedHashMap<ArrayList<String>,Integer>();
		
		LinkedList<SQLValue> sqLValList = new LinkedList<SQLValue>();
		ArrayList<String> totalTransCount = new ArrayList<String>();
		ArrayList<String> totalAttributeCount = new ArrayList<String>();
		LinkedHashMap<String, iStateDetail> cursorList = new LinkedHashMap<String, iStateDetail>();
            
		iStatement iStDet = new iStatement();
		if (istatDtls instanceof iStatement)
			iStDet = istatDtls;
		
		String updatedVal = "";
		
	    ArrayList<String> throwsVal = new ArrayList<>();
		int qryCnt = 1;
		int tabCnt = 1; 
		ArrayList<Integer> statementCnt = new ArrayList<Integer>();
		if (iStDet.statDetail instanceof dsProc)//Procedure
		{
			
			dsProc iProc = (dsProc) iStDet.statDetail;
			
			String procArgVals = "";
//			procArgVals = getArgsVal(iProc);
			
			updatedVal += "create or replace procedure"+ " "+ iProc.name+"("+procArgVals+")\n AS $$\n";//
		
			if (iProc.asBlock != null)
			{
				if (iProc.asBlock instanceof iBlockStatement)
				{
					updatedVal = processBlkSt (iProc.asBlock, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount,
							sqlQryList, cursorList);
				}
			}
			if (iProc.procBlockStatements != null)
			{
				if (iProc.procBlockStatements instanceof iBlockStatement)
				{
					String tmpTab = "";
					if (tabCnt > 0)
						tmpTab = getTabSpace (tabCnt, tmpTab);
					updatedVal += "BEGIN\n";
					tabCnt++;
					statementCnt.add(statementCnt.size() + 1);
					updatedVal = processBlkSt (iProc.procBlockStatements, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
				}
			}
				updatedVal += "\nEND;\n$$ LANGUAGE plpgsql;";
		}
		else
		{
			CompilerUtils.getSQLQueryValues(istatDtls, newDbId, currentDbId, outpath, filename, configJsonPath, console, configHash, fileType, sqlQryList);
		}
		
		if (fileType.equalsIgnoreCase("PLSQL PROC") && updatedVal.length() > 0)
		{
			CompilerUtils.createFileForCompiler(updatedVal, outpath, filename, ".sql");
			
			CompilerUtils.processProcReportLog(currentDbId, newDbId, filename, outpath, console, rptLogInfo, manualChangesList, sqLValList, totalAttributeCount, totalTransCount);
		}
	}

	
	
	private static String getDeclareFromFields(dsField iField, String declareVal, int newDbId, int currentDbId) 
	{
		if(declareVal.contains("DECLARE"))
			declareVal = "";
		else
			declareVal = "DECLARE ";
		
		if (iField.name != null)
			declareVal += iField.name;
		
		if (iField.dataType != null)
		{
			String dataType = "";
			DataTypeConvertor dtc = new DataTypeConvertor();
			ArrayList<Token> tkn = FunctionTypeConv.getTokens(iField.dataType);
			boolean flag = false;

			if (iField.dataType.toLowerCase().contains("default"))
			{
				iField.dataType = iField.dataType.toLowerCase();
				String temp  = iField.dataType.substring(0, iField.dataType.indexOf("default"));
				ArrayList<Token> tempTkn = FunctionTypeConv.getTokens(temp);
				
				for (int i = 0; i < tempTkn.size(); i++) //For DataType
				{
					if (i == 0)
						dataType = dtc.getTargetDataType(tempTkn.get(0).data, currentDbId, newDbId);
					else
						dataType += tempTkn.get(i).data;//" "+tkn.get(i).data;
				}
				
				for (int i = 0; i < tkn.size(); i++) //For default values
				{
						if (i == 0)
							continue;
						if (flag)
							dataType += "  DEFAULT := "+tkn.get(i).data;
						if (tkn.get(i).data.equalsIgnoreCase("default"))
							flag = true;
				}
			}
			else
			{
				for (int i = 0; i < tkn.size(); i++) //For DataType
				{
					if (i == 0)
						dataType = dtc.getTargetDataType(tkn.get(0).data, currentDbId, newDbId);
					else
						dataType += tkn.get(i).data;//" "+tkn.get(i).data;
				}
			}
			
			if (dataType != null)
				declareVal +=  " "+ dataType;
			else
				declareVal +=  iField.dataType;
		}
		
		if(!declareVal.contains(";"))
			declareVal += ";";
		
		return declareVal;
	}
	
	
	private static String processBlkSt(iBlockStatement iBlockSt, int newDbId, int currentDbId, String updatedVal, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt,
			ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList,LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		String tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);
//		System.out.println(statementCnt);
		if (iBlockSt != null)
		{
			if (iBlockSt.iBlockStatements.size() > 0)
			{
				iStatement iSt = null;
				dsField iField = null;
				dsAssignment iAssig = null;
				dsIF iIf = null;
				dsQuery iQuery = null;
				dsFor iFor = null;
				dsWhile iWhile = null;
				dsDoWhile iDoWhile = null;
				dsThrows iThrows = null;
				dsCallExecute iCallEx = null;
				iBlockStatement iBlkSt = null;
				dsCollectStats iCollectStat = null;
				dsRaise iRaise = null;
				dsCommit iCommit = null;
				dsException iExcep = null;
				dsDeclaration iDecl = null;
				dsCursor iCursor = null;
				String tmp = "";
				
				for(Entry<Float, iStatement> entrySet : iBlockSt.iBlockStatements.entrySet())
				{
					if (entrySet.getValue() instanceof iStatement)
						iSt = entrySet.getValue();

					if(iSt.statDetail instanceof dsField)
						iField = (dsField) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsRaise)
						iRaise = (dsRaise) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsCommit)
						iCommit = (dsCommit) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsException)
						iExcep = (dsException) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsAssignment)
						iAssig = (dsAssignment) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsIF)
						iIf = (dsIF) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsFor)
						iFor = (dsFor) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsQuery)
						iQuery = (dsQuery) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsWhile)
						iWhile = (dsWhile) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsDoWhile)
						iDoWhile = (dsDoWhile) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsThrows)
						iThrows = (dsThrows) iSt.statDetail;
					
					if(iSt.statDetail instanceof dsCallExecute)
						iCallEx = (dsCallExecute) iSt.statDetail;
					
					if(iSt instanceof iBlockStatement)
						iBlkSt = (iBlockStatement) iSt;
					
					if (iSt.statDetail instanceof dsCollectStats)
						iCollectStat = (dsCollectStats) iSt.statDetail;
					
					if (iSt.statDetail instanceof dsDeclaration)
						iDecl = (dsDeclaration) iSt.statDetail;
					
					if (iSt.statDetail instanceof dsCursor)
						iCursor = (dsCursor) iSt.statDetail;
					
					if (iDecl != null)
					{
						if (iDecl.declare instanceof dsField)
						{
							iField = (dsField) iDecl.declare;
							tmp = "";
							tmp += getDeclareFromFields (iField, updatedVal, newDbId, currentDbId) + "\n";
							
							statementCnt.add(statementCnt.size() + 1);
							updatedVal += tmpTab + tmp;
							iField = null; iDecl = null;
						}
					}
					if (iCursor != null)
					{
						tmp = "";
						tmp += getCursorValues (iCursor, tmp, newDbId, currentDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList) + "\n";
						
						cursorList.put(iCursor.cursorName, iCursor);
						
						statementCnt.add(statementCnt.size() + 1);
						updatedVal += tmpTab + tmp;
						iCursor = null; 
					}
					if (iSt.type == StatType.BEGINBLOCK)
					{
						if (iBlkSt.iBlockStatements.size() > 0)
						{
							updatedVal += tmpTab + "BEGIN\n";
							tabCnt++;
							updatedVal =  processBlkSt(iBlkSt, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
							statementCnt.add(statementCnt.size() + 1);
							updatedVal += tmpTab + "END;\n";
						}
					}
					if (iSt.type == StatType.LOOPBLOCK)
					{
						if (iBlkSt.iBlockStatements.size() > 0)
						{
							updatedVal =  processBlkSt(iBlkSt, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iCollectStat != null)
					{
						updatedVal += iCollectStat.collectStatDtls;
						statementCnt.add(statementCnt.size() + 1);
						iCollectStat = null;
					}
					if (iSt.type == StatType.UNKNOWNSTAT || iSt.type == StatType.LOCKACCESS)
					{
						statementCnt.add(statementCnt.size() + 1);
						if(iSt.type == StatType.LOCKACCESS)
						{
							updatedVal += tmpTab +"--[WARNING] :: Below Statement is not Converted by DS  For Review OrginalLineNo :- "+ iSt.originalLineNo+"\n";
							updatedVal += tmpTab +"--dsError_" + iSt.originalLine.trim() +";"+ "\n"; iSt = null;
						}
						else
						{
							String origLine = iSt.originalLine.trim().toUpperCase();
//							if (!(origLine.startsWith("ROLLBACK") || origLine.startsWith("COMMIT")))
							{
								updatedVal += tmpTab +"dsError_" + origLine +";"+ "\n"; 
								
								int key = iSt.originalLineNo;
								
								ArrayList<String> manualChanges = new ArrayList<String>();
								
//								manualChanges.add("dsError_" + origLine+";"+key);
								manualChanges.add("dsError_" + origLine);
								manualChangesList.put(manualChanges, key);
							}
//							else
//								updatedVal += tmpTab + origLine +";"+ "\n"; 
								
								iSt = null;
						}
					}
					if (iCallEx != null)
					{
						tmp = "";
						if (iCallEx.type == StatType.CALLSTAT || iCallEx.type == StatType.EXECUTE)
							tmp += getCallSt (iCallEx, updatedVal, newDbId, currentDbId, tabCnt) + "\n";
					
						updatedVal += tmpTab + tmp;
						iCallEx = null;
						statementCnt.add(statementCnt.size() + 1);
					}
						
					if (iField != null)
					{
						tmp = "";
						if (iSt.type == StatType.DECLARESTAT)
							tmp += getDeclareFromFields (iField, updatedVal, newDbId, currentDbId) + "\n";
						
						statementCnt.add(statementCnt.size() + 1);
						updatedVal += tmpTab + tmp;
						iField = null;
					}
					if (iAssig != null)
					{
						if (iSt.type == StatType.ASSGNSTAT)
						{
							tmp = "";
							tmp += getAssignmentVal (iAssig, tmp, newDbId, currentDbId) + "\n";
							updatedVal += tmpTab + tmp;iAssig = null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iIf != null)
					{
						if (iSt.type == StatType.IFBLOCK || iSt.type == StatType.ELSEIFBLOCK)// || iSt.type == StatType.THENBLOCK)
						{
							tmp = "";
							tmp += getIfBlockVal (iIf, tmp, newDbId, currentDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList) + "\n";
							updatedVal += tmpTab + tmp;iIf = null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iFor != null)
					{
						if (iSt.type == StatType.FORLOOP)
						{
							tmp = "";
							tmp += getForBlockVal (iFor, tmp, newDbId, currentDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList) + "\n";
							updatedVal += tmp;
							iFor=null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iQuery != null)
					{
						if (iSt.type == StatType.SQLQUERY)
						{
							if (iQuery.sqlQuery != null)
							{
								tmp = "";
								tmp += "\n"+ tmpTab + getSQLDet(iSt.originalLineNo, iQuery.sqlQuery, currentDbId, newDbId, qryCnt, rptLogInfo, configHash, tmp, tabCnt, sqLValList, totalAttributeCount, totalTransCount, manualChangesList, sqlQryList).trim()+"\n";
								if (sqlQryList == null)
									sqlQryList = new LinkedList<iStatement>();
									
								sqlQryList.add(iSt);
								updatedVal += tmp+"\n";//sqlQryVal.sqlValue;
								iQuery = null;
//								statementCnt.add(statementCnt.size() + 1);
							}
						}
					}
					if (iWhile != null)
					{
						if (iWhile.whileBlock.type == StatType.WHILELOOP)
						{
							tmp = "";
							tmp += getWhileBlockVal (iWhile, tmp, newDbId, currentDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList) + "\n";
							updatedVal += tmpTab + tmp;iWhile = null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iDoWhile != null)
					{
						if (iDoWhile.doBlock.type == StatType.DOBLOCK)//DOBLOCK)
						{
							tmp = "";
							tmp += getDoBlockVal (iDoWhile, tmp, newDbId, currentDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList) + "\n";
							updatedVal += tmpTab + tmp;iDoWhile = null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iThrows != null)
					{
//						if (iSt.type == StatType.IFBLOCK)
						{
							tmp = "";
							tmp += "\nEXCEPTION \n" + getThrowsVal (iThrows, tmp, newDbId, currentDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList) + "\n";
							throwsVal.add(tmp);
							updatedVal += tmp;
							iThrows=null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iRaise != null)
					{
						if (iRaise.type == StatType.RAISE)
						{
							tmp = "";
							updatedVal += tmpTab + "RAISE INFO '[Error]';\n";
							iRaise = null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iCommit != null)
					{
						if (iCommit.type == StatType.COMMIT)
						{
							tmp = "";
							updatedVal += tmpTab + "COMMIT;\n";
							iCommit = null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
					if (iExcep != null)
					{
						if (iSt.type == StatType.EXCEPTIONBLOCK)
						{
							tmp = "";
							tmp += getExceptionBlockVal (iExcep, tmp, newDbId, currentDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList) + "\n";
							updatedVal += tmp;iExcep = null;
							statementCnt.add(statementCnt.size() + 1);
						}
					}
				}
			}
		}
		rptLogInfo.statementCount = statementCnt.size();
		return updatedVal;
	}

	private static String getCursorValues(dsCursor iCursor, String updatedVal, int newDbId, int currentDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList) 
	{
		updatedVal = "DECLARE " + iCursor.cursorName;
		
		if (iCursor.sqlQuery != null)
		{
			String tmp = "";
			int orgLineNo = 0;
			updatedVal += " CURSOR FOR " + getSQLDet (orgLineNo,iCursor.sqlQuery, currentDbId, newDbId, qryCnt, rptLogInfo, configHash, tmp, tabCnt, sqLValList, totalAttributeCount, totalTransCount, manualChangesList, sqlQryList);
		}
		
		return updatedVal;
	}



	private static String getExceptionBlockVal(dsException iExcep, String updatedVal, int newDbId, int currentDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt,
			LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		String tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);
		
		updatedVal += tmpTab + "EXCEPTION \n";
		
		if (iExcep.condition != null)
		{
			dsCondition iCond = null;
			if (iExcep.condition != null)
			{
				iBlockStatement iBlk = null;
				if (iExcep.condition instanceof iBlockStatement)
					iBlk = (iBlockStatement) iExcep.condition;
				
				tabCnt++;
				if (tabCnt > 0)
				{
					tmpTab = "";
					tmpTab = getTabSpace (tabCnt, tmpTab);
				}
				updatedVal += tmpTab + "WHEN ";
				
				if (iBlk.iBlockStatements.size() > 1)
				{
						updatedVal = processBlkSt(iBlk, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
				}
				else
				{
					iStatement iSt = null;
					for(Entry<Float, iStatement> entrySet : iBlk.iBlockStatements.entrySet())
					{
						if (entrySet.getValue() instanceof iStatement)
							iSt = entrySet.getValue();
						
						if (iSt.statDetail instanceof dsCondition)
							iCond = (dsCondition) iSt.statDetail;
						
						if (iCond != null)
						{
							String ifCond = "";
							if (iCond.sqlExpr != null)
							{
								String exprVal = FunctionTypeConv.getCurrentFunctions(iCond.sqlExpr.value, currentDbId, newDbId);
//								if (exprVal.contains("("))
									ifCond +=  exprVal ;
//								else
//									ifCond +=  "("+exprVal+") " ;
							}
							updatedVal += ifCond ;
						}
					}
				}
			}

			updatedVal += "\n" ;
		}
		if (iExcep.thenBlock != null)
		{
			updatedVal += tmpTab + "THEN \n";
			if (iExcep.thenBlock.iBlockStatements.size() > 0)
			{
					tabCnt++;	
					updatedVal = processBlkSt(iExcep.thenBlock, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
			}
		}
		return updatedVal;
	}


	private static String getThrowsVal(dsThrows iThrows, String tmp, int newDbId, int currentDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		String tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);

		if (iThrows.exception != null)
		{
			iBlockStatement iBlk = new iBlockStatement();
			if (iThrows.exception instanceof iBlockStatement)
				iBlk = (iBlockStatement) iThrows.exception;
			statementCnt.add(statementCnt.size() + 1);
			tmp = processBlkSt(iBlk, newDbId, currentDbId, tmp, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
		}
		
		return tmp;
	}
	
	private static String getDoBlockVal(dsDoWhile iDoWhile, String updatedVal, int newDbId, int currentDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		String tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);
		if (iDoWhile.condition != null)
		{
			dsCondition iCond = null;
			
			if (iDoWhile.condition.statDetail instanceof dsCondition)
				iCond = (dsCondition) iDoWhile.condition.statDetail;
			if (iCond != null)
			{
				updatedVal += "\n"+tmpTab + "WHILE ";	
				if (iCond.sqlExpr != null)
				{
					String exprVal = FunctionTypeConv.getCurrentFunctions(iCond.sqlExpr.value, currentDbId, newDbId);
					
					updatedVal += exprVal +"\n";
				}
			}
		}
		if (iDoWhile.doBlock != null)
		{
			if (iDoWhile.doBlock.iBlockStatements.size() > 0)
			{
				tabCnt++;
				updatedVal += tmpTab + "LOOP \n";							
				updatedVal =  processBlkSt(iDoWhile.doBlock, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
			}
		}
		updatedVal += tmpTab + "END LOOP;\n";
		return updatedVal;
	}


	private static String getWhileBlockVal(dsWhile iWhile, String updatedVal, int newDbId, int currentDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		if (iWhile.condition != null)
		{
			dsCondition iCond = null;
			
			if (iWhile.condition.statDetail instanceof dsCondition)
				iCond = (dsCondition) iWhile.condition.statDetail;
			if (iCond != null)
			{
				updatedVal += "WHILE ";	
				if (iCond.sqlExpr != null)
				{
					updatedVal += iCond.sqlExpr.value +"\nLOOP";
				}
			}
		}
		if (iWhile.whileBlock != null)
		{
			if (iWhile.whileBlock.iBlockStatements.size() > 0)
			{
				updatedVal =  processBlkSt(iWhile.whileBlock, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
			}
		}
		updatedVal += "END LOOP;\n";
		return updatedVal;
	}
	
	private static String getSQLDet(int originalLineNo, SQLQuery sqlQuery, int currentDbId, int newDbId, int qryCnt, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, String tmp, int tabCnt, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<iStatement> sqlQryList) 
	{
		String tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);
		
		ReportLogInfo rpInfo = new ReportLogInfo();
		SQLValue sqlQryVal = QueryGenerator.fillSQLStatementForDbs(sqlQuery, currentDbId, newDbId, rpInfo, configHash);
		String sqlStmts = sqlQryVal.sqlValue;
		
		sqLValList.add(sqlQryVal);
//		tmp = "\n"+ tmpTab + sqlStmts.replace("\n", " ").replace("  ", " ")+"\n";
		tmp = "\n"+ tmpTab + sqlStmts.replace("\n", "\n"+tmpTab )+"\n";
		
		int size = rpInfo.stmtsAdd.size();
		totalTransCount.add(Integer.toString(size));
		
		int attrSize = rpInfo.totalAttributeTransCount.size();
		totalAttributeCount.add(Integer.toString(attrSize));
		
		if (rpInfo.manualChanges.size() > 0)
		{
//			if (rpInfo.manualChanges.get(0).contains(";"))
//			{
//				String temp = rpInfo.manualChanges.get(0);
//				rpInfo.manualChanges.add(temp.substring(temp.indexOf(";")+1,temp.length()));
//			}
			manualChangesList.put(rpInfo.manualChanges, originalLineNo);
		}
		
		
		return tmp;
	}
	
	private static String getForBlockVal(dsFor iFor, String updatedVal, int newDbId, int currentDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		String tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);

		String tempVal = "";
		
		//tempVal += "FOR ";
		boolean checkSQL = false;
		
		String condVal = "";
		if (iFor.condition != null)
		{
			dsCondition iCond = null;
			int orgLineNo = 0;
			if (iFor.condition instanceof iStatement)
				orgLineNo = iFor.condition.originalLineNo;
			
			if (iFor.condition.statDetail instanceof dsCondition)
				iCond = (dsCondition) iFor.condition.statDetail;
			if (iCond != null)
			{
				SQLExpr sqlExp = new SQLExpr();
				if (iCond.sqlExpr != null)
				{
					sqlExp = iCond.sqlExpr;
					if (sqlExp.args.size() > 0)
					{
						String condExpr = iCond.sqlExpr.value;
						condExpr = updatedStmt(condExpr, "as");
						
	                    if(condExpr.contains(" AS "))
	                    	condVal = condExpr.substring(0, condExpr.indexOf(" AS "));
	                    else
	                    	condVal = condExpr.substring(0, condExpr.indexOf("("));
	                    
						for (int i = 0; i < sqlExp.args.size(); i++) 
						{
							SQLQuery sqlQry = new SQLQuery();
							if (sqlExp.args.get(i) instanceof SQLQuery)
								sqlQry = (SQLQuery) sqlExp.args.get(i);
							
							if (sqlQry != null)
							{
								String tmp = "";
								//tmp = "\n"+tmpTab +  + "\n";
								tmp = "FOR " + condVal+"(" + getSQLDet (orgLineNo,sqlQry, currentDbId, newDbId, qryCnt, rptLogInfo, configHash, tmp, tabCnt, sqLValList, totalAttributeCount, totalTransCount, manualChangesList, sqlQryList);
								tempVal += tmp + tmpTab + ")"+"\n";//sqlQryVal.sqlValue;
							}
							
							checkSQL = true;
						}
					}
					else
						condVal = iCond.sqlExpr.value +"\n";
				}
			}
		}
		
		String variableName = "", cursorName = "";
		boolean checkCursorCond = false;
		if(!checkSQL)
		{
			ArrayList<Token> condToken = FunctionTypeConv.getTokens(condVal);
			
			for(int j =  0 ;j  < condToken.size(); j++)
			{
				String cursorVal  = condToken.get(j).data;
				
				if(cursorList.get(cursorVal) != null)
				{
					checkCursorCond = true;
					cursorName = cursorVal;
				}
				
				if(condToken.get(j).data.equalsIgnoreCase("IN"))
				{
					variableName = condToken.get(j-1).data;
				}
			}
			
			if(!checkCursorCond)
				tempVal += tmpTab + "FOR ("+condVal.trim()+")"+"\n";
			else
			{
				tempVal += tmpTab + "DECLARE " + variableName +" Record;            ----[ErrorMessage] Need manual Intervention to declare this cursor variable globally.\n\n"; 	
				tempVal += tmpTab +"OPEN "+ cursorName +";\n";
				
			}
				
		}
		
		System.out.println(checkCursorCond);
//		else
//			tempVal += tmpTab + "while ("+condVal.trim()+".next()"+")"+"\n";
		
		tempVal += tmpTab + "LOOP\n";
		
		if(checkCursorCond)
			tempVal += "\n"+ tmpTab + "fetch "+cursorName+ " into "+ variableName +";\n\n";
		
		if (iFor.initializeSt != null)
		{
			for (int i = 0; i < iFor.initializeSt.size(); i++) 
			{
				iStatement intSt = iFor.initializeSt.get(i);
				
				for (int j = 0; j < intSt.tokenList.size(); j++) 
				{
					tempVal += intSt.tokenList.get(j).data +" ";
				}
			}
		}
		if (iFor.forBlock != null)
		{
			if (iFor.forBlock.iBlockStatements.size() > 0)
			{
				tabCnt++;
				tempVal +=  processBlkSt(iFor.forBlock, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
			}
		}
		
		if(checkCursorCond)
			tempVal += "\n"+tmpTab + "exit when not found;\n\n";
			
		tempVal += tmpTab + "END LOOP;\n";
				
		updatedVal += tempVal;
		
		return updatedVal;
	}

	private static String updatedStmt(String sqlStmts, String checkVal) 
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
	
	private static String getIfBlockVal(dsIF iIf, String updatedVal, int newDbId, int currentDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		String tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);
		if (iIf.condition != null)
		{
			dsCondition iCond = null;
			if (iIf.condition.statDetail instanceof dsCondition)
				iCond = (dsCondition) iIf.condition.statDetail;
			if (iCond != null)
			{
				String ifCond = "";
				ifCond += "\n"+tmpTab + "IF ";	
				if (iCond.sqlExpr != null)
				{
					String exprVal = FunctionTypeConv.getCurrentFunctions(iCond.sqlExpr.value, currentDbId, newDbId);
					if (exprVal.contains("("))
						ifCond +=  exprVal ;
					else
						ifCond +=  "("+exprVal+") " ;
				}
				updatedVal += ifCond ;
			}
			updatedVal += "\n" ;
		}
		if (iIf.elseIfBlock != null)
		{
			if (iIf.elseIfBlock.iBlockStatements.size() > 0)
			{
				String tempVal = "";
				tempVal  = processElseIf (iIf.elseIfBlock, currentDbId, newDbId, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, tmpTab, cursorList);
				updatedVal += tmpTab + tempVal;
			}
		}
		if (iIf.thenBlock != null)
		{
			updatedVal += tmpTab + "THEN \n";
			if (iIf.thenBlock.iBlockStatements.size() > 0)
			{
//					updatedVal += tmpTab + " THEN \n";
					tabCnt++;	
					updatedVal = processBlkSt(iIf.thenBlock, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
//					updatedVal += tmpTab ;
//					tabCnt--;	
			}
		}
		if (iIf.elseBlock != null)
		{
			if (iIf.elseBlock.iBlockStatements.size() > 0)
			{
				if (tabCnt >= 1)
				{
					tmpTab = "";
					tabCnt = tabCnt - 1;
					tmpTab = getTabSpace (tabCnt, tmpTab);
				}
				else
					tmpTab = "";
				updatedVal += tmpTab +"ELSE \n";
				tabCnt++;
				if (tabCnt >= 1)
				{
					tmpTab = "";
					tmpTab = getTabSpace (tabCnt, tmpTab);
				}
				else
					tmpTab = "";
//				updatedVal += tmpTab ;
				updatedVal = tmpTab + processBlkSt(iIf.elseBlock, newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList,totalAttributeCount, totalTransCount, sqlQryList, cursorList);
//				updatedVal += tmpTab ;
			}
		}
		
		if (tabCnt >= 1)
		{
			tmpTab = "";
			tabCnt = tabCnt - 1;
			tmpTab = getTabSpace (tabCnt, tmpTab);
		}
		else
			tmpTab = "";
		updatedVal += tmpTab +"END IF;";
		
		tabCnt++;
		return updatedVal;
	}
	
	private static String processElseIf(iBlockStatement elseIfBlock, int currentDbId, int newDbId, ReportLogInfo rptLogInfo, LinkedHashMap<String, LinkedHashMap<String, String>> configHash, int qryCnt, int tabCnt, ArrayList<String> throwsVal, ArrayList<Integer> statementCnt, LinkedHashMap<ArrayList<String>, Integer> manualChangesList, LinkedList<SQLValue> sqLValList, ArrayList<String> totalAttributeCount, ArrayList<String> totalTransCount, LinkedList<iStatement> sqlQryList, String tmpTab, LinkedHashMap<String, iStateDetail> cursorList) 
	{
		String updatedVal = "";
		tmpTab = "";
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);
		if (elseIfBlock.iBlockStatements.size() > 0)
		{
			for(Entry<Float, iStatement> entrySet : elseIfBlock.iBlockStatements.entrySet())
			{
				iStatement iSt = null;
				if (entrySet.getValue() instanceof iStatement)
					iSt = entrySet.getValue();
				
				if (iSt != null)
				{
					if (iSt.type == StatType.THENBLOCK)
					{
						updatedVal += "\n" +tmpTab +"THEN \n";
						updatedVal = processBlkSt((iBlockStatement) entrySet.getValue(), newDbId, currentDbId, updatedVal, rptLogInfo, configHash, qryCnt, tabCnt, throwsVal, statementCnt, manualChangesList, sqLValList, totalAttributeCount, totalTransCount, sqlQryList, cursorList);
//						updatedVal += tmpTab ;
					}
					if (iSt.type == StatType.CONDSTAT)
					{
						dsCondition iCond = (dsCondition) iSt.statDetail;
						if (iCond != null)
						{
							String ifCond = "ELSE IF ";
							if (iCond.sqlExpr != null)
							{
								String exprVal = FunctionTypeConv.getCurrentFunctions(iCond.sqlExpr.value, currentDbId, newDbId);
								if (exprVal.contains("("))
									ifCond +=  exprVal ;
								else
									ifCond +=  "("+exprVal+") " ;
							}
							if (tabCnt > 0)
							{
								tmpTab = "";
								tmpTab = getTabSpace (tabCnt, tmpTab);
							}
							else
								tmpTab = "";
							updatedVal += tmpTab + ifCond ;//+" THEN \n";
						}
					}
				}
			}
		}
		return updatedVal;
	}

	private static String getAssignmentVal(dsAssignment iAssig, String assigVal, int newDbId, int currentDbId) 
	{
		if (iAssig.assignToVariable != null)
			assigVal += iAssig.assignToVariable +" := ";
		
		if (iAssig.exp != null)
		{
			SQLExpr sqlExpr = iAssig.exp;
			if (sqlExpr != null)
			{
				String exprVal = FunctionTypeConv.getCurrentFunctions(sqlExpr.value, currentDbId, newDbId);
				assigVal += exprVal+ ";";
			}
		}
		
		return assigVal;
	}
	
	private static String getCallSt(dsCallExecute iCallEx, String updatedVal, int newDbId, int currentDbId, int tabCnt) 
	{
		String tmpTab = "";
		boolean flag = false;
		if (tabCnt > 0)
			tmpTab = getTabSpace (tabCnt, tmpTab);
		
		if (iCallEx.procName != null)
		{
			ArrayList<Token> tkn = FunctionTypeConv.getTokens(iCallEx.procName);
			updatedVal = "CALL ";
			for (int i = 0; i < tkn.size(); i++) 
			{
				if (tkn.get(i).type.name().equalsIgnoreCase("varwithdot"))
				{
					String callProcName = tkn.get(i).data.substring(tkn.get(i).data.indexOf(".")+1, tkn.get(i).data.length());
					/*if (callProcName.equalsIgnoreCase("sysExecSQL") && newDbId == ToolTypeConstants.REDSHIFT)
					{
						updatedVal = getExecDetail (callProcName, newDbId, tmpTab, iCallEx.argsList, currentDbId);
						flag = true;
					}
					else*/

						updatedVal +=  tkn.get(i).data +" ";
				}
				else
					updatedVal +=  tkn.get(i).data +" ";
			}
		}
		
		if(iCallEx.type == StatType.EXECUTE)
		{
			updatedVal = "EXECUTE ";
			
			String args = getArgValue (iCallEx.argsList, newDbId, currentDbId);
			
			args = getUpdatedArgs(args).trim();
			
			updatedVal += args + ";";
			
			flag = true;
		}
			
		if (iCallEx.argsList.size() > 0 && (!flag))
		{
			String args ="";
			updatedVal += " (";
			args = getArgValue (iCallEx.argsList, newDbId, currentDbId);
			updatedVal += args + ");";
		}
			
		return updatedVal;
	}

	
	private static String getUpdatedArgs(String args) {
		
		ArrayList<Token> tknVal = FunctionTypeConv.getTokens(args);
		
		String updatedVal = "";
		for(int j = 0  ; j < tknVal.size(); j++)
		{
			if(!tknVal.get(j).data.equalsIgnoreCase("immediate"))
			{
				updatedVal += tknVal.get(j).data + " ";
			}
		}
		
		return updatedVal;
	}



	private static String getArgsVal(dsProc iProc) 
	{
		String tempVal = "";
		
		dsField iField = null;
		dsCondition dscond = null;
		for (int i = 0; i < iProc.argsList.size(); i++)
		{
			iStatement iSt = iProc.argsList.get(i);
			
			if(iSt.statDetail instanceof dsField)
				iField = (dsField) iSt.statDetail;
			if(iSt.statDetail instanceof dsCondition)
				dscond = (dsCondition) iSt.statDetail;
			
			if(dscond != null)
			{
				if(dscond.sqlExpr != null)
				{
					if(i == iProc.argsList.size()-1)
						tempVal += dscond.sqlExpr.value;
					else
						tempVal += dscond.sqlExpr.value+",";
				}
			}
			if (iField != null)
			{
				if (iField.name != null)
					tempVal += iField.name;
				
				if (iField.dataType != null)
				{
					String dataType = null;
					DataTypeConvertor dtc = new DataTypeConvertor();
					ArrayList<Token> tkn = FunctionTypeConv.getTokens(iField.dataType);
					boolean flag = false;
					for (int j = 0; j < tkn.size(); j++) 
					{
						if (j == 0)
							continue;
						if (flag)
							dataType += "  = "+tkn.get(j).data;
						if (tkn.get(j).data.equalsIgnoreCase("default"))
							flag = true;
//								dataType = dtc.getTargetDataType(tkn.get(0).data, currentDbId, newDbId);
					}
					
					if (dataType != null)
						tempVal +=  dataType;
					else
						tempVal +=  iField.dataType;
				}
				tempVal += ";";
			}
			
		}
		
		return tempVal;
	}
	
	private static String getArgValue(ArrayList<SQLExpr> argsList, int newDbId, int currentDbId)
	{
		String updatedVal = "";
		SQLExpr sqlExpr = new SQLExpr();
		for (int i = 0; i < argsList.size(); i++) 
		{
			sqlExpr = argsList.get(i);
			String val = FunctionTypeConv.getCurrentFunctions(sqlExpr.value, currentDbId, newDbId);
			if(i==(argsList.size()-1))
				updatedVal += ""+ val;
			else
				updatedVal += ""+ val+", ";
		}
	
		return updatedVal;
	}
	
	
	private static String getTabSpace(int tabCnt, String tmpTab)
	{
		for (int i = 0; i < tabCnt; i++) 
		{
			tmpTab += "\t";
		}
		return tmpTab;
	}
}