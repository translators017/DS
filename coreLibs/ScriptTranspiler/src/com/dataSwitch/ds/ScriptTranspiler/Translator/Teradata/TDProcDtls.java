
package com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.dataSwitch.ds.ScriptTranspiler.Interpreter.TranslatorUtils;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLGenerator.QueryGenerator;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.Script.StatementObjts.dsAssignment;
import com.dataSwitch.ds.Script.StatementObjts.dsCallExecute;
import com.dataSwitch.ds.Script.StatementObjts.dsCollectStats;
import com.dataSwitch.ds.Script.StatementObjts.dsCondition;
import com.dataSwitch.ds.Script.StatementObjts.dsDoWhile;
import com.dataSwitch.ds.Script.StatementObjts.dsField;
import com.dataSwitch.ds.Script.StatementObjts.dsFor;
import com.dataSwitch.ds.Script.StatementObjts.dsIF;
import com.dataSwitch.ds.Script.StatementObjts.dsLockAccess;
import com.dataSwitch.ds.Script.StatementObjts.dsProc;
import com.dataSwitch.ds.Script.StatementObjts.dsQuery;
import com.dataSwitch.ds.Script.StatementObjts.dsThrows;

public class TDProcDtls 
{

	public static iStateDetail createProcStatObjt(iStatement istatDetail, ArrayList<iStatement> entrySetmap,
			Token startToken, Token endToken, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId, int newDbId) 
	{

		dsProc iProc = new dsProc();
		
		if(startToken.data.equalsIgnoreCase("replace"))
			iProc.replace = true;
		
		
		String procName = "";
		String argVal = "";
		boolean checkReplace = false;
		boolean endFlag = false;
		for(int i = 0 ; i < entrySetmap.size() ; i++)
		{
			ArrayList<Token> tknVal = entrySetmap.get(i).tokenList;
			
			for(int j = 0 ; j < tknVal.size() ; j++)
			{
				if(tknVal.get(j).data.contains("ds__Block_"))
				{
					ArrayList<iStatement> blockLstVal= blockStatDtls.get(tknVal.get(j).data);
					iProc.procBlockStatements = filliBlockStatementObjt(blockLstVal, currentDbId, newDbId, blockStatDtls);
				}
				else
				{
					if(tknVal.get(j).data.equalsIgnoreCase("procedure"))
					{
						checkReplace = true;
						istatDetail.originalLineNo = entrySetmap.get(i).originalLineNo;
					}
					
					if(checkReplace && endFlag == false)
					{
						if(tknVal.get(j).data.equalsIgnoreCase("procedure"))
							continue;
						
						if(!tknVal.get(j).data.equalsIgnoreCase("("))
							procName += tknVal.get(j).data;
						
						if(tknVal.get(j).type.name().equalsIgnoreCase("openparen"))
						    endFlag = true;
					}
					else
					{
						if(!(tknVal.get(j).data.equalsIgnoreCase("Replace") || 
								tknVal.get(j).data.equalsIgnoreCase(";") || tknVal.get(j).data.equalsIgnoreCase("}")))
							argVal += " "+tknVal.get(j).data;
					}
				}
			}
		}
		iProc.name = procName;
		
		if(argVal.length() > 0)
		{
			fillArgsForProcOrFunc(argVal, iProc, istatDetail);
		}
		
		return iProc;
	}


	private static void fillArgsForProcOrFunc(String argVal, dsProc iProc, iStatement istatDetail)
	{
		argVal = argVal.substring(0, argVal.length()-1);
		
		String[] argsVal = argVal.split(",");
		
		for(int i = 0 ; i < argsVal.length ; i++)
		{
			iStatement istat = new iStatement();
			istat.type = StatType.ARGSSTAT;
			istat.tokenList.addAll(FunctionTypeConv.getTokens(argsVal[i]));
			istat.originalLineNo = istatDetail.originalLineNo;
			dsCondition dscond = new dsCondition();
			SQLExpr sqlExpr = new SQLExpr(); 
			sqlExpr.type = "dsArguments";
			sqlExpr.value = getStructedExprValue(argsVal[i]);
			dscond.sqlExpr = sqlExpr;
			istat.statDetail = dscond;
			iProc.argsList.add(istat);
		}
		
	}

	private static iBlockStatement filliBlockStatementObjt(ArrayList<iStatement> blockLstVal, int currentDbId, int newDbId, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls) 
	{

		iBlockStatement iBlockStat = new iBlockStatement();

		iBlockStat.originalLineNo = blockLstVal.get(0).originalLineNo;
		
		for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(blockLstVal, currentDbId, getTdBlockScriptKeyWords()).entrySet())
	    {
			Token startToken = entrySet.getKey();
			Token endToken = entrySet.getValue();

			if((startToken.data.equalsIgnoreCase("begin") || startToken.data.equalsIgnoreCase("loop")) && endToken.data.equalsIgnoreCase("end"))
			{
				if(startToken.data.equalsIgnoreCase("begin"))
					iBlockStat.type = StatType.BEGINBLOCK;
				
				else if(startToken.data.equalsIgnoreCase("loop"))
					iBlockStat.type = StatType.LOOPBLOCK;
				
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal);
				fillBlockStatementDtls(blockLst, currentDbId, iBlockStat, newDbId, blockStatDtls);
			}
			
			else if((startToken.data.equalsIgnoreCase("then") && endToken.data.equalsIgnoreCase("else")) || 
					(startToken.data.equalsIgnoreCase("then") && endToken.data.equalsIgnoreCase("end")))
			{
				iBlockStat.type = StatType.THENBLOCK;
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal);
				fillBlockStatementDtls(blockLst, currentDbId, iBlockStat, newDbId, blockStatDtls);
			}
				
			else if(startToken.data.equalsIgnoreCase("else") && endToken.data.equalsIgnoreCase("end"))
			{
				iBlockStat.type = StatType.ELSEBLOCK;
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal);
				fillBlockStatementDtls(blockLst, currentDbId, iBlockStat, newDbId, blockStatDtls);
			}
			else if(startToken.data.equalsIgnoreCase("do") && endToken.data.equalsIgnoreCase("end"))
			{
				iBlockStat.type = StatType.DOBLOCK;
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal);
				fillBlockStatementDtls(blockLst, currentDbId, iBlockStat, newDbId, blockStatDtls);
			}
			
	    }

		
		for(int i = 0 ; i < blockLstVal.size() ; i++)
		{
			iBlockStat.tokenList.addAll(blockLstVal.get(i).tokenList);
		}
		
		return iBlockStat;
	}


	private static void fillBlockStatementDtls(ArrayList<ArrayList<iStatement>> blockLst, int currentDbId, iBlockStatement iBlockStat, int newDbId, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls)
	{
		int keyCount = 0;
		
		for(int i = 0 ; i < blockLst.size() ; i++)
		{
			ArrayList<iStatement> blockLstVal = blockLst.get(i);
			for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(blockLstVal, currentDbId, getTDScriptKeyWordDtls()).entrySet())
		    {
				Token startToken = entrySet.getKey();
				Token endToken = entrySet.getValue();
				
				for(int j = 0 ; j < TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal).size() ; j++)
				{	
				    float keyVal = keyCount++;	

					if(startToken.data.equalsIgnoreCase("declare"))
					{
					    iBlockStat.iBlockStatements.put(keyVal, createDeclareObject(TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal).get(j), startToken , 
					    		endToken, blockStatDtls, currentDbId, newDbId));
					}
					
					else if(startToken.data.equalsIgnoreCase("set"))
					{
						iBlockStat.iBlockStatements.put(keyVal, createAssignmentObject(TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal).get(j), startToken , endToken));
					}
					else if(startToken.data.equalsIgnoreCase("locking"))
					{
						iBlockStat.iBlockStatements.put(keyVal, createLockAccessStmtObject(TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal).get(j), startToken , endToken));
					}
					else 
					{
						ArrayList<iStatement> iStatement = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal).get(j);
						
						float CheckKey = 0.01f; 
						
						for (int k = 0; k < iStatement.size(); k++) 
						{
							keyVal = keyVal + CheckKey + 0.01f;
							
							if (iStatement.get(k).type == StatType.SQLQUERY)
							{
								iStatement iSt = iStatement.get(k);
								iBlockStat.iBlockStatements.put(keyVal, createSQLQuery(iSt, currentDbId, newDbId));
							}
							else if(iStatement.get(k).type == StatType.BLANKSTAT)
							{
								iStatement iSt = iStatement.get(k);
								iBlockStat.iBlockStatements.put(keyVal, iSt);
							}
							else if (iStatement.get(k).type == StatType.UNKNOWNSTAT)
							{
								if (iStatement.get(k).tokenList.size() <= 0)
									continue;
								
								if (iStatement.get(k).tokenList.get(0).data.contains("ds__Block_"))
								{
									ArrayList<iStatement>  innerBlkDtl  = blockStatDtls.get(iStatement.get(k).tokenList.get(0).data);
									iBlockStat.iBlockStatements.put(keyVal, filliBlockTypeStatementObjt (innerBlkDtl, currentDbId, newDbId, blockStatDtls));
								}
								else
								{
									if(iStatement.get(k).tokenList.get(0) == startToken || iStatement.get(k).tokenList.get(0) == endToken)
										continue;
									
									iBlockStat.iBlockStatements.put(keyVal, fillUnkownStatement(iStatement.get(k)));
								}
							}	
						}
							
					}
				}
				
		    } 
		}
	}


	private static iStatement createLockAccessStmtObject(ArrayList<iStatement> arrayList, Token startToken,
			Token endToken) 
	{
		iStatement iStat = new iStatement();
		
	    iStat.type = StatType.LOCKACCESS;
	    iStat.originalLineNo = arrayList.get(0).originalLineNo;
	    
	    dsLockAccess lckAccessObjt = new dsLockAccess();
	    
	    String StringVal = "";
    		
	    for(int j = 0 ; j < arrayList.size() ; j++)
	    {
	    	iStatement istmt = arrayList.get(j);
	    	
	    	if(arrayList.get(j).tokenList.size() > 0)
	    	{
		    	if(startToken == arrayList.get(j).tokenList.get(0))
				{
					if(arrayList.get(j+1).tokenList.get(0).data.length() > 0)
					{
						lckAccessObjt.tableName = arrayList.get(j+1).tokenList.get(0).data; 
					}
				}
	    	}
	    	
	    	for(int k = 0 ; k < istmt.tokenList.size() ; k++)
	    	{
	    		StringVal += " "+istmt.tokenList.get(k).data;
	    	}
	    }
	    
	   
	    iStat.statDetail = lckAccessObjt;
	    iStat.originalLine = StringVal;
	    
		return iStat;
	}


	private static iStatement fillUnkownStatement(iStatement iStatement) 
	{
		iStatement istat = new iStatement();
		
		istat.type = StatType.UNKNOWNSTAT;
		istat.originalLineNo = iStatement.originalLineNo;
		String unkwnVal = "";
		
		for(int j = 0 ; j < iStatement.tokenList.size() ; j++)
		{
			unkwnVal +=" " +iStatement.tokenList.get(j).data;
		}
		
		istat.originalLine = getStructedExprValue(unkwnVal);
		
		return istat;
	}


	private static iStatement filliBlockTypeStatementObjt(ArrayList<iStatement> innerBlkDtl, int currentDbId,
			int newDbId, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls) 
	{
		StatType statType = getBlockType(innerBlkDtl);
		
		iStatement istat = new iStatement();
		istat.type = statType;
		istat.originalLineNo = innerBlkDtl.get(1).originalLineNo;
		
		if(statType == StatType.IFBLOCK)
		     fillIfBlockObjt(istat, innerBlkDtl, newDbId, blockStatDtls, currentDbId);
		
		else if(statType == StatType.WHILELOOP || statType == StatType.FORLOOP)
			fillForWhileLoopObjt(istat, innerBlkDtl, newDbId, blockStatDtls, currentDbId);
		
		else if(statType == StatType.BEGINBLOCK || statType == StatType.LOOPBLOCK)
			return filliBlockStatementObjt(innerBlkDtl, currentDbId, newDbId, blockStatDtls);
		
		
		return istat;
	}


	private static void fillForWhileLoopObjt(iStatement istat, ArrayList<iStatement> innerBlkDtl, int newDbId,
			LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId) 
	{
		dsDoWhile doWhile = null;
		dsFor doFor = null;
		
		if(istat.type == StatType.WHILELOOP)
			doWhile = new dsDoWhile();
		
	    else if(istat.type == StatType.FORLOOP)
	    	doFor = new dsFor();
		
		  for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(innerBlkDtl, currentDbId, getTdBlockScriptKeyWords()).entrySet())
		    {
				Token startToken = entrySet.getKey();
				Token endToken = entrySet.getValue();
				
				if((startToken.data.equalsIgnoreCase("While") && endToken.data.equalsIgnoreCase("}")) || 
						(startToken.data.equalsIgnoreCase("end") && endToken.data.equalsIgnoreCase("while")) ||
						(startToken.data.equalsIgnoreCase("For") && endToken.data.equalsIgnoreCase("}"))||
						(startToken.data.equalsIgnoreCase("end") && endToken.data.equalsIgnoreCase("For")))
					continue;
				
				if(startToken.data.equalsIgnoreCase("while") && endToken.data.equalsIgnoreCase("do"))
				{
					ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
					fillBlockCondObjt(null ,blockLst, startToken, endToken, doWhile, null, currentDbId, newDbId);
				}
				else if(istat.type == StatType.FORLOOP && endToken.data.equalsIgnoreCase("do"))
				{
					ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
					fillBlockCondObjt(null, blockLst, startToken, endToken, null, doFor, currentDbId, newDbId);
				}
				else
				{
					ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
					
					for(int j = 0 ; j < blockLst.size(); j++)
					{
						iBlockStatement iBlckStmt = filliBlockStatementObjt(blockLst.get(j), currentDbId, newDbId, blockStatDtls);
						
						if(doWhile != null)
						{
							if(iBlckStmt.type == StatType.DOBLOCK)
								doWhile.doBlock = iBlckStmt;
						}
						else if(doFor != null)
						{
							if(iBlckStmt.type == StatType.DOBLOCK)
								doFor.forBlock = iBlckStmt;
						}
							
					}
				}
				
		    }
		  
		  if(doWhile != null)
			  istat.statDetail = doWhile;
		  
		  else if(doFor != null)
			  istat.statDetail = doFor;
	}


	private static void fillIfBlockObjt(iStatement istat, ArrayList<iStatement> innerBlkDtl, int newDbId,
			LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId)
	{
		
		dsIF ifObjt = new dsIF();
        
		for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(innerBlkDtl, currentDbId, getTdBlockScriptKeyWords()).entrySet())
	    {
			Token startToken = entrySet.getKey();
			Token endToken = entrySet.getValue();
			
			if((startToken.data.equalsIgnoreCase("If") && endToken.data.equalsIgnoreCase("}")) || 
					(startToken.data.equalsIgnoreCase("end") && endToken.data.equalsIgnoreCase("if")))
				continue;
			
			if(startToken.data.equalsIgnoreCase("If") && endToken.data.equalsIgnoreCase("then"))
			{
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
				fillBlockCondObjt(ifObjt,blockLst, startToken, endToken, null, null, currentDbId, newDbId);
			}
			else
			{
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
				
				for(int j = 0 ; j < blockLst.size(); j++)
				{
					iBlockStatement iBlckStmt = filliBlockStatementObjt(blockLst.get(j), currentDbId, newDbId, blockStatDtls);
					
					if(iBlckStmt.type == StatType.THENBLOCK)
						ifObjt.thenBlock = iBlckStmt;
					
					else if(iBlckStmt.type == StatType.ELSEBLOCK)
						ifObjt.elseBlock = iBlckStmt;
				}
			}
			
	    }
		
		istat.statDetail = ifObjt;
		
	}


	private static void fillBlockCondObjt(dsIF ifObjt, ArrayList<ArrayList<iStatement>> blockLst, Token startToken,
			Token endToken, dsDoWhile doWhile, dsFor doFor, int currentDbId, int newDbId) 
	{
		
		iStatement istat = new iStatement();
		
		String exprVal = "";
		SQLExpr sqlExpr = new SQLExpr();
		
		for(int i = 0 ; i < blockLst.size() ; i++)
		{
			for(int j = 0 ; j < blockLst.get(i).size() ; j++)
			{
				ArrayList<Token> toknVal = blockLst.get(i).get(j).tokenList;
				StatType statType = blockLst.get(i).get(j).type;
				
				if(!(statType == StatType.SQLQUERY || statType == StatType.BLANKSTAT))
				{
					for(int k = 0 ; k < toknVal.size() ; k++)
					{
						if(startToken == toknVal.get(k) || endToken == toknVal.get(k) || toknVal.get(0).data.equalsIgnoreCase("FOR"))
						{
							if(startToken == toknVal.get(k) || toknVal.get(0).data.equalsIgnoreCase("FOR"))
							{
								istat.originalLineNo = blockLst.get(i).get(j).originalLineNo;
								istat.type = StatType.CONDSTAT;
							}
							
						    continue;
						}
						else
						{
							exprVal += " "+toknVal.get(k).data;
						}
						
					}
				}
				else
				{
					if(statType == StatType.SQLQUERY)
					{
						SQLQuery sqlQry = SQLConverter.processScriptSQl(toknVal, currentDbId, newDbId);
						//ReportLogInfo rptLogInfo = new ReportLogInfo();
						//SQLValue sqlQuery = QueryGenerator.fillSQLStatementForDbs(sqlQry, currentDbId, newDbId,rptLogInfo, null);
						//exprVal += " "+ sqlQuery.sqlValue;
						sqlExpr.args.add(sqlQry);
					}
					
				}
			}
		}
		
		exprVal = getStructedExprValue(exprVal);
		
		dsCondition dscond = new dsCondition();
		
		sqlExpr.type = "dsCondition";
		sqlExpr.value = exprVal;
		dscond.sqlExpr = sqlExpr;
		istat.statDetail = dscond;
		
		if(ifObjt != null)
			ifObjt.condition = istat;
		
		else if(doWhile != null)
			doWhile.condition = istat;
		
		else if(doFor != null)
			doFor.condition = istat;
		
		
	}


	private static StatType getBlockType(ArrayList<iStatement> innerBlkDtl) 
	{
		if(innerBlkDtl.get(1).tokenList.get(0).data.equalsIgnoreCase("if"))
			return StatType.IFBLOCK;
		
		else if(innerBlkDtl.get(1).tokenList.get(0).data.equalsIgnoreCase("while"))
			return StatType.WHILELOOP;
		
		else if(innerBlkDtl.get(1).tokenList.get(0).data.equalsIgnoreCase("For"))
			return StatType.FORLOOP;
		
		else if(innerBlkDtl.get(1).tokenList.get(0).data.equalsIgnoreCase("Begin"))
			return StatType.BEGINBLOCK;
		
		else if(innerBlkDtl.get(1).tokenList.get(0).data.equalsIgnoreCase("Loop"))
			return StatType.LOOPBLOCK;
		
		return null;
		
	}

	private static iStatement createSQLQuery(iStatement iSt, int currentDbId, int newDbId)
	{
		
		SQLQuery sqlQry = SQLConverter.processScriptSQl(iSt.tokenList, currentDbId, newDbId);
		
		if(!sqlQry.dmlType.equalsIgnoreCase("CALL PROC/FUNC"))
		{
			if(!sqlQry.dmlType.equalsIgnoreCase("Collect Stats"))
			{
				dsQuery dsQry = new dsQuery();
				dsQry.sqlQuery = sqlQry;
				iSt.statDetail = dsQry;
			}
			else
			{
				dsCollectStats dsCollectStats = new dsCollectStats();
				ReportLogInfo rptLogInfo = new  ReportLogInfo();
				SQLValue sqlVal = SQLConverter.getConvertedQueryForScript(iSt.tokenList, currentDbId, newDbId, rptLogInfo, null); 
				dsCollectStats.collectStatDtls = sqlVal.sqlValue;
				iSt.statDetail = dsCollectStats;
			}
		}
		else
		{
			dsCallExecute dsCallExec = new dsCallExecute();
			dsCallExec.type = StatType.CALLSTAT;
			
			if(sqlQry.callProcOrFunc != null)
			{
				dsCallExec.procName = sqlQry.callProcOrFunc.aliasName;
				
				for(int i = 0 ; i < sqlQry.callProcOrFunc.args.size() ; i++)
				{
					SQLExpr sqlExpr = (SQLExpr) sqlQry.callProcOrFunc.args.get(i);
					dsCallExec.argsList.add(sqlExpr);
				}
			}
			iSt.type = StatType.CALLSTAT;
			iSt.statDetail = dsCallExec;
		}
		
		return iSt;
	}


	private static iStatement createAssignmentObject(ArrayList<iStatement> arrayList, Token startToken,
			Token endToken) 
	{
		iStatement istat = new iStatement();
		istat.type = StatType.ASSGNSTAT;
		
		int originalLnNo = 0;
		
		dsAssignment dsAssgnMnt = new dsAssignment();
		
		String sqlExpr = "";
		for(int i = 0 ; i < arrayList.size() ; i++)
		{
			for(int j = 0 ; j < arrayList.get(i).tokenList.size() ; j++)
			{
				if(arrayList.get(i).tokenList.get(j) == startToken || arrayList.get(i).tokenList.get(j) == endToken)
				{
					if(arrayList.get(i).tokenList.get(j).data.equalsIgnoreCase(startToken.data))
						originalLnNo = arrayList.get(i).originalLineNo;
					
					continue;
				}
				else
				{
					if(i == 1)
					{
						dsAssgnMnt.assignToVariable = arrayList.get(i).tokenList.get(j).data;
					}
					else
					{
						if(i > 2)
						{
							sqlExpr += " " +  arrayList.get(i).tokenList.get(j).data;
						}
					}
				}
			}
			
			SQLExpr sqlExprObjt = new SQLExpr();
			sqlExprObjt.type = startToken.data;
			sqlExpr = getStructedExprValue (sqlExpr);
			sqlExprObjt.value = sqlExpr;
			
			dsAssgnMnt.exp = sqlExprObjt;
			istat.statDetail = dsAssgnMnt;
			
			istat.tokenList.addAll(arrayList.get(i).tokenList);
		}
		
		istat.originalLineNo = originalLnNo;
		
		
		return istat;
	}


	private static String getStructedExprValue(String sqlExpr)
	{
		sqlExpr = sqlExpr.replace("( ", "(").replace(" (", "(").replace(" )", ")")
				.replace("[ ", "[").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
				.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").trim();
		
		return sqlExpr;		
	}


	private static iStatement createDeclareObject(ArrayList<iStatement> entrySetmap, Token startToken, Token endToken, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId, int newDbId) 
	{
		iStatement istat = new iStatement();
		istat.type = StatType.DECLARESTAT;
		
		int orgiLneNo = 0;
		dsField iField = new dsField();
		
		boolean checkSQLExpception = false;
		String dataType = "";
		for(int  i = 0 ; i < entrySetmap.size(); i++)
		{
			for(int j = 0 ; j < entrySetmap.get(i).tokenList.size() ; j++)
			{
				if(entrySetmap.get(i).tokenList.get(j) == startToken || entrySetmap.get(i).tokenList.get(j) == endToken)
				{
					if(startToken.data.equalsIgnoreCase("Declare"))
					{
						orgiLneNo = entrySetmap.get(i).originalLineNo;
					}
					
					continue;
				}
				
				if(i == 1)
					iField.name = entrySetmap.get(1).tokenList.get(0).data;
				else
				{
					if(!entrySetmap.get(i).tokenList.get(j).data.equalsIgnoreCase("SQLEXCEPTION"))
						dataType += " "+entrySetmap.get(i).tokenList.get(j).data;
					else
						checkSQLExpception = true;
				}
				
			}
			
			istat.tokenList.addAll(entrySetmap.get(i).tokenList);
		}
		
		if(!checkSQLExpception)
			dataType = getStructedExprValue(dataType);
		
		
		
		istat.originalLineNo = orgiLneNo;
		
		if(!checkSQLExpception)
		{
			iField.dataType = dataType;
			istat.statDetail = iField;
		}
		else
		{
			filliStatExceptionObjt(entrySetmap, istat, startToken, endToken, blockStatDtls, currentDbId, newDbId);
		}
		
		return istat;	
	}
	
	
	private static void filliStatExceptionObjt(ArrayList<iStatement> entrySetmap, iStatement istat, Token startToken, Token endToken, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId, int newDbId) 
	{
		dsThrows excep = new dsThrows();
		for(int  i = 0 ; i < entrySetmap.size(); i++)
		{
			for(int j = 0 ; j < entrySetmap.get(i).tokenList.size() ; j++)
			{
				if(entrySetmap.get(i).tokenList.get(j) == startToken || entrySetmap.get(i).tokenList.get(j) == endToken)
					continue;
				
				if(entrySetmap.get(i).tokenList.get(j).data.contains("ds__Block_"))
				{
					ArrayList<iStatement> istatBlock = blockStatDtls.get(entrySetmap.get(i).tokenList.get(j).data);
					excep.exception = filliBlockStatementObjt(istatBlock, currentDbId, newDbId, blockStatDtls);
				}
				
			}
			
			istat.statDetail = excep;
			istat.tokenList.addAll(entrySetmap.get(i).tokenList);
		}
		
	}

	public static HashMap<String, String> getTdBlockScriptKeyWords()
	{
		LinkedHashMap<String, String> tdScriptKeyWord = new LinkedHashMap<String,String>();
		tdScriptKeyWord.put("{", "{");
		tdScriptKeyWord.put("CREATE", "create");
		tdScriptKeyWord.put("REPLACE", "replace");
		tdScriptKeyWord.put("BEGIN", "begin");
		tdScriptKeyWord.put("LOOP", "loop");
		tdScriptKeyWord.put("WHILE", "while");
		tdScriptKeyWord.put("DO", "do");
		tdScriptKeyWord.put("IF", "if");
		tdScriptKeyWord.put("THEN", "then");
		tdScriptKeyWord.put("ELSE", "else");
		tdScriptKeyWord.put("END", "end");
		tdScriptKeyWord.put("}", "}");
		
		return tdScriptKeyWord;
	}
	

	private static HashMap<String, String> getTDScriptKeyWordDtls() 
	{
		LinkedHashMap<String, String> tdScriptKeyWord = new LinkedHashMap<String,String>();
		
		tdScriptKeyWord.put("BEGIN", "begin");
		tdScriptKeyWord.put("LOOP", "loop");
		tdScriptKeyWord.put("DECLARE", "declare");
		tdScriptKeyWord.put("SET", "set");
		tdScriptKeyWord.put("WHILE", "while");
		tdScriptKeyWord.put("LOCKING", "locking");
		tdScriptKeyWord.put("LOCK", "lock");
		tdScriptKeyWord.put("ACCESS", "access");
		tdScriptKeyWord.put("DO", "do");
		tdScriptKeyWord.put("IF", "if");
		tdScriptKeyWord.put("THEN", "then");
		tdScriptKeyWord.put("ELSE", "else");
		tdScriptKeyWord.put("END", "end");
		tdScriptKeyWord.put(";", ";");
		
		return tdScriptKeyWord;
	}

}