
package com.dataSwitch.ds.ScriptTranspiler.Translator.Oracle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.ScriptTranspiler.Compiler.ELTCompiler;
import com.dataSwitch.ds.ScriptTranspiler.Interpreter.TranslatorUtils;
import com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules.AddCurleyBraceForScriptKeyWords;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.ScriptTranspiler;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.Utils;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata.MainTDTranslator;
import com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata.TDProcDtls;
import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;
import com.dataSwitch.ds.sql.SQLParser.SQLUtils;
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

public class OracleProcTranslator 
{

	public static iStatement getiStatementProcObjeds(LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId,
			int newDbId, String extension, String currentFileType) 
	{
		AddCurleyBraceForScriptKeyWords.addCurleyBracesForProcScripts(sqlNodeMaps, currentDbId, newDbId);
		
		LinkedHashMap<String, ArrayList<iStatement>> updBlockNodes = TranslatorUtils.getStatementNodes (sqlNodeMaps, currentDbId, "{", "}", extension, newDbId);
		       
		iStatement istatObjt = getiStatObjectForProcs(currentDbId, newDbId, extension, updBlockNodes, currentFileType, sqlNodeMaps);
		
		return istatObjt;
	}
	
	
	private static iStatement getiStatObjectForProcs(int currentDbId, int newDbId, String extension, LinkedHashMap<String, ArrayList<iStatement>> updBlockNodes, String currentFileType, LinkedHashMap<Float, iStatement> sqlNodeMaps) 
	{
		iStatement iStatDetail = new iStatement();
		
		
		ArrayList<iStatement> outeriStatDetail = TranslatorUtils.getOuteriStatDetail(updBlockNodes);
		
        boolean checkProc = false;
        
		for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(outeriStatDetail, currentDbId, getOracleBlockScriptKeyWords()).entrySet())
	    {
			Token startToken = entrySet.getKey();
			Token endToken = entrySet.getValue();
			
			for(ArrayList<iStatement> entrySetmap : TranslatorUtils.getGroupOfiStatObject(startToken,endToken, outeriStatDetail))
			{
				if (startToken.data.equalsIgnoreCase("replace") || startToken.data.equalsIgnoreCase("create"))
				{
					fillProcStatDetailObjt(startToken, endToken, updBlockNodes, iStatDetail, entrySetmap, currentDbId, newDbId);
					checkProc = true;
				}
			}
			
	    }
		
		if(!checkProc)
		{
			iBlockStatement iBlockStat = new iBlockStatement();
			
			iBlockStat.iBlockStatements = sqlNodeMaps;
			iBlockStat.type =  StatType.UNKNOWNSTAT;
			
			return iBlockStat;
		}
		else
			return iStatDetail;
	}
	
	public static void fillProcStatDetailObjt(Token startToken, Token endToken,
			LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, iStatement istatDetail, ArrayList<iStatement> entrySetmap, int currentDbId, int newDbId) 
	{
		
	    boolean checkProcScript = isProcedureScript(entrySetmap, "Procedure");
	    boolean checkPackageScript = isProcedureScript(entrySetmap, "Package");
	    
	    if(checkProcScript || checkPackageScript)
	    {
	    	if(checkProcScript)
	    		istatDetail.type = StatType.PROCSTAT;
	    	else
	    		istatDetail.type = StatType.UNKNOWNSTAT;
	    	
	    	istatDetail.statDetail = createProcStatObjt(istatDetail, entrySetmap, startToken, endToken, blockStatDtls, currentDbId, newDbId);
	    }
	}
	
	private static boolean isProcedureScript(ArrayList<iStatement> entrySetmap, String string) 
	{
		for(int i = 0 ; i < entrySetmap.size() ;i++)
		{
			if (entrySetmap.get(i).type == StatType.BLANKSTAT)
				continue;
			if(entrySetmap.get(i).tokenList.get(0).data.equalsIgnoreCase(string))
				return true;
		}
		
		return false;
	}
	
	public static iStateDetail createProcStatObjt(iStatement istatDetail, ArrayList<iStatement> entrySetmap,
			Token startToken, Token endToken, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId, int newDbId) 
	{

		dsProc iProc = new dsProc();
		
		if(startToken.data.equalsIgnoreCase("replace"))
			iProc.replace = true;
		
		
		String procName = "";
		
		ArrayList<iStatement> istatDeclare = new ArrayList<iStatement>();
		
		boolean checkReplace = false;
		boolean endFlag = false;
		boolean declareStat = false;
		int argLineNo = 0;
		String argVal = "";
		for(int i = 0 ; i < entrySetmap.size() ; i++)
		{
			ArrayList<Token> tknVal = entrySetmap.get(i).tokenList;
			
			if(entrySetmap.get(i).type == StatType.SQLQUERY)
			{
				if(declareStat)
				{
					istatDeclare.add(entrySetmap.get(i));
				}
			}
			else
			{
				for(int j = 0 ; j < tknVal.size() ; j++)
				{
				
					if(tknVal.get(j).data.contains("ds__Block_"))
					{
						ArrayList<iStatement> blockLstVal= blockStatDtls.get(tknVal.get(j).data);
						iProc.procBlockStatements = filliBlockStatementObjt(blockLstVal, currentDbId, newDbId, blockStatDtls);
					}
					else
					{
						if(tknVal.get(j).data.equalsIgnoreCase("procedure") || tknVal.get(j).data.equalsIgnoreCase("package"))
						{
							checkReplace = true;
							istatDetail.originalLineNo = entrySetmap.get(i).originalLineNo;
						}
						
						if(checkReplace && (endFlag == false && declareStat == false))
						{
							if(tknVal.get(j).data.equalsIgnoreCase("procedure") || tknVal.get(j).data.equalsIgnoreCase("package"))
								continue;
							
							if(!(tknVal.get(j).data.equalsIgnoreCase("(") || (tknVal.get(j).data.equalsIgnoreCase("IS") || tknVal.get(j).data.equalsIgnoreCase("AS"))))
								procName += tknVal.get(j).data;
							
							if(tknVal.get(j).type.name().equalsIgnoreCase("openparen") || tknVal.get(j).type.name().equalsIgnoreCase("VAR") || tknVal.get(j).type.name().equalsIgnoreCase("varwithdot"))
							{
							    endFlag = true;
							}
							
						}
						else
						{
							if(!(tknVal.get(j).data.equalsIgnoreCase("Replace") || tknVal.get(j).data.equalsIgnoreCase("}")))
							{
								if(endFlag)
								{
								    if(tknVal.get(j).data.equalsIgnoreCase("IS") || tknVal.get(j).data.equalsIgnoreCase("AS"))
								    {
										endFlag = false;
										declareStat = true;
								    }
								    else
								    {
								    	argLineNo = entrySetmap.get(i).originalLineNo;
								    	argVal += " " + tknVal.get(j).data;
	 							    }
								}
								
								
								if(declareStat)
								{
									iStatement iStat = new iStatement();
									iStat.originalLine = entrySetmap.get(i).originalLine;
									iStat.originalLineNo = entrySetmap.get(i).originalLineNo;
									iStat.type = entrySetmap.get(i).type;
									iStat.tokenList.add(tknVal.get(j));
									istatDeclare.add(iStat);
								}
							}
						}
					}
				}
			}
		}
		
		fillArgsForProcedure(iProc, argVal, argLineNo);
		
		fillDeclareStatsForProc(iProc, istatDeclare, currentDbId, newDbId);
	
		iProc.name = procName;
		
		return iProc;
	}
	
	private static void fillDeclareStatsForProc(dsProc iProc, ArrayList<iStatement> istatDeclare, int currentDbId, int newDbId) {
		
		boolean flag = false;
		
		ArrayList<iStatement> istatList =  new ArrayList<iStatement>();
		
		ArrayList<ArrayList<iStatement>> groupDeclareList = new ArrayList<ArrayList<iStatement>>();
		for(int j = 0; j < istatDeclare.size(); j++)
		{
			if(istatDeclare.get(j).type != StatType.SQLQUERY)
			{
				if(istatDeclare.get(j).tokenList.get(0).data.equalsIgnoreCase("IS") || istatDeclare.get(j).tokenList.get(0).type.name().equalsIgnoreCase("var"))
					flag = true;
			}
			
			if(flag)
			{
				if(!(istatDeclare.get(j).tokenList.get(0).data.equalsIgnoreCase("IS") || istatDeclare.get(j).tokenList.get(0).data.equalsIgnoreCase("AS")))
					istatList.add(istatDeclare.get(j));

				if(istatDeclare.get(j).type == StatType.SQLQUERY || istatDeclare.get(j).tokenList.get(0).data.equalsIgnoreCase(";"))
				{
				   flag = false;
				   groupDeclareList.add(istatList);
				   istatList = new ArrayList<iStatement>();
				}
			}
		
		}
		
	    iBlockStatement iblock = new iBlockStatement();
		for(int j = 0; j < groupDeclareList.size(); j++)
		{	
			iStatement istatObjt = new iStatement();
			if(getCursorKeyWord(groupDeclareList.get(j)))
			{
				dsCursor curserObjt = new dsCursor();
				for(int k = 0 ; k < groupDeclareList.get(j).size(); k++)
				{
					if(groupDeclareList.get(j).get(k).type == StatType.SQLQUERY)
					{
						curserObjt.sqlQuery = SQLConverter.processScriptSQl(groupDeclareList.get(j).get(k).tokenList, currentDbId, newDbId);
					}
					else if(groupDeclareList.get(j).get(k).tokenList.get(0).data.equalsIgnoreCase("cursor"))
					{
						istatObjt.originalLineNo = groupDeclareList.get(j).get(k+1).originalLineNo;
						curserObjt.cursorName = groupDeclareList.get(j).get(k+1).tokenList.get(0).data;
					}
				}
				
				istatObjt.type = StatType.CURSOR;
				istatObjt.statDetail = curserObjt;
			}
			else
			{
				dsDeclaration iDeclare = new dsDeclaration();
				dsField iFiled = new dsField();
				
			    String dataType = "";
				for(int k = 0 ; k < groupDeclareList.get(j).size(); k++)
				{
					iFiled.name = groupDeclareList.get(j).get(0).tokenList.get(0).data;
				
					if(k >= 1)
					{
						dataType += groupDeclareList.get(j).get(k).tokenList.get(0).data + " ";
					}
					
					
					istatObjt.originalLineNo = groupDeclareList.get(j).get(k).originalLineNo;
				}
				
				iFiled.dataType = getStructedExprValue(dataType);
				iDeclare.declare = iFiled; 
				istatObjt.statDetail = iDeclare;
			}
			
		    
			float keyVal = j;
			
			iblock.iBlockStatements.put(keyVal, istatObjt);
		}
		
		iProc.asBlock = iblock;
	}


	private static boolean getCursorKeyWord(ArrayList<iStatement> arrayList) 
	{
		for(int k = 0 ; k < arrayList.size(); k++)
		{
			if(arrayList.get(k).tokenList.get(0).data.equalsIgnoreCase("cursor"))
			{
				return true;
			}
		}
		return false;
	}


	private static void fillArgsForProcedure(dsProc iProc, String argVal, int argLineNo)
	{
		dsField iField;
		iStatement iStat;
		
		if(argVal.contains("("))
		{
			String procArgs = argVal.substring(argVal.indexOf("(") + 1, argVal.lastIndexOf(")"));
		
			String[] argList = procArgs.split(",");
			
			ArrayList<iStatement> argsList = new ArrayList<iStatement>();
			
			for(String arg : argList)
			{
				String[] fields = arg.trim().split(" ");
				
				iField = new dsField();
				iField.name = fields[0].trim();
				
				if(fields[1].equalsIgnoreCase("IN") || fields[1].equalsIgnoreCase("OUT"))
					iField.dataType = fields[2].trim();
				else
					iField.dataType = fields[1].trim();
				
				iStat = new iStatement();
				iStat.statDetail = iField;
				iStat.originalLineNo = argLineNo;
				iStat.type = StatType.ARGSSTAT;
				
				argsList.add(iStat);
			}
			iProc.argsList = argsList;
		}
	}


	private static iBlockStatement filliBlockStatementObjt(ArrayList<iStatement> blockLstVal, int currentDbId, int newDbId, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls) 
	{

		iBlockStatement iBlockStat = new iBlockStatement();

		iBlockStat.originalLineNo = blockLstVal.get(0).originalLineNo;
		
		for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(blockLstVal, currentDbId, getOracleBlockScriptKeyWords()).entrySet())
	    {
			Token startToken = entrySet.getKey();
			Token endToken = entrySet.getValue();
			
			if((startToken.data.equalsIgnoreCase("begin") || startToken.data.equalsIgnoreCase("loop")) && (endToken.data.equalsIgnoreCase("end") || endToken.data.equalsIgnoreCase("}")))
			{
				if(startToken.data.equalsIgnoreCase("begin"))
					iBlockStat.type = StatType.BEGINBLOCK;
				
				else if(startToken.data.equalsIgnoreCase("loop"))
					iBlockStat.type = StatType.LOOPBLOCK;
				
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal);
			
				fillBlockStatementDtls(blockLst, currentDbId, iBlockStat, newDbId, blockStatDtls);
			}
			else if((startToken.data.equalsIgnoreCase("elsif") && endToken.data.equalsIgnoreCase("then")) || 
					(startToken.data.equalsIgnoreCase("if") && endToken.data.equalsIgnoreCase("then")))
			{
				if (startToken.data.equalsIgnoreCase("elsif"))
					iBlockStat.type = StatType.ELSEIFBLOCK;
				else
					iBlockStat.type = StatType.IFBLOCK;
				
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal);
				fillBlockStatementDtls(blockLst, currentDbId, iBlockStat, newDbId, blockStatDtls);
			}
			else if((startToken.data.equalsIgnoreCase("then") && (endToken.data.equalsIgnoreCase("else") || endToken.data.equalsIgnoreCase("elsif") || endToken.data.equalsIgnoreCase("when"))) || 
					(startToken.data.equalsIgnoreCase("then") && endToken.data.equalsIgnoreCase("end")))
			{
				iBlockStat.type = StatType.THENBLOCK;
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal);
				fillBlockStatementDtls(blockLst, currentDbId, iBlockStat, newDbId, blockStatDtls);
			}
				
			else if((startToken.data.equalsIgnoreCase("else") && endToken.data.equalsIgnoreCase("end")))
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
		
			for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(blockLstVal, currentDbId, getOracleScriptKeyWordDtls()).entrySet())
		    {
				Token startToken = entrySet.getKey();
				Token endToken = entrySet.getValue();
				
				for(int j = 0 ; j < TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal).size() ; j++)
				{	
				    float keyVal = keyCount++;	

				    ArrayList<iStatement> declareStatList = new ArrayList<iStatement>();
			    	ArrayList<iStatement> iStatement = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, blockLstVal).get(j);
				   
					float CheckKey = 0.01f; 
				    float inNodeKeyVal = 0;
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
								
								if(startToken.data.equalsIgnoreCase(";") && endToken.data.equalsIgnoreCase(";"))
								{
									declareStatList.add(iStatement.get(k));
									inNodeKeyVal = keyVal;
								}
								else if(startToken.type.name().equalsIgnoreCase("VAR") && endToken.data.equalsIgnoreCase(";"))
								{
									declareStatList.add(iStatement.get(k));
									inNodeKeyVal = keyVal;
								}
								
								//iBlockStat.iBlockStatements.put(keyVal, fillUnkownStatement(iStatement.get(k)));
							}
						}
						
					}
					
					if(declareStatList.size() > 0)
					{
						iBlockStat.iBlockStatements.put(inNodeKeyVal, createCallAssgnDeclareObject(declareStatList, startToken, endToken, blockStatDtls, currentDbId, newDbId));
					}
					
					declareStatList.clear();
				}
				
				
				
		    } 
		}
	}
	
	private static iStatement createCallAssgnDeclareObject(ArrayList<iStatement> entrySetmap, Token startToken, Token endToken,
			LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId, int newDbId) 
	{
		iStatement istat = new iStatement();
		
		int orgiLneNo = 0;
		
		boolean checkSQLExpception = false;
		String funCall = "";
		
		for(int  i = 0 ; i < entrySetmap.size(); i++)
		{
				for(int j = 0 ; j < entrySetmap.get(i).tokenList.size() ; j++)
				{
					funCall += " "+entrySetmap.get(i).tokenList.get(j).data;
				}
			
			istat.tokenList.addAll(entrySetmap.get(i).tokenList);
			istat.originalLineNo = entrySetmap.get(i).originalLineNo;
		}
		
	    boolean checkCallType = false;
		for(int k = 0 ; k < istat.tokenList.size(); k++)
		{
			if(istat.tokenList.get(k).type.name().equalsIgnoreCase("OPENPAREN") && istat.tokenList.get(k).data.equalsIgnoreCase("("))
			{
				if(istat.tokenList.get(k-1).data.length() >0 )
				{
					 DataTypeConvertor dtc = new DataTypeConvertor();
					 String dataType = dtc.getTargetDataType(istat.tokenList.get(k-1).data, ToolTypeConstants.ORACLE, ToolTypeConstants.ORACLE);
					 
					 if(dataType == null)
					 {
						 checkCallType = true;
						 break;
					 }
				}
			}
		} 
		
		if(!checkSQLExpception)
			funCall = getStructedExprValue(funCall);

		if(funCall.contains(":="))
		{
			istat.type = StatType.ASSGNSTAT;
			
			dsAssignment dsassign = new dsAssignment();
			
			istat.statDetail = dsassign;
			
			String[] fields = funCall.split(":=");
			
			dsassign.assignToVariable = fields[0].trim();
			
			SQLExpr exp = new SQLExpr();
			exp.value = fields[1].trim();
			
			dsassign.exp = exp;
			istat.statDetail = dsassign;
			
			checkCallType = false;
		}
		else if(funCall.equalsIgnoreCase("commit"))
		{
			istat.type = StatType.COMMIT;
			dsCommit dscommit = new dsCommit();
			dscommit.type = StatType.COMMIT;
			istat.statDetail = dscommit;
		}
		
		else if(funCall.equalsIgnoreCase("raise"))
		{
			istat.type = StatType.RAISE;
			dsRaise dsraise = new dsRaise();
			dsraise.type = StatType.RAISE;
			istat.statDetail = dsraise;
		}
		else if(startToken.data.equalsIgnoreCase("execute"))
		{
			istat.type = StatType.EXECUTE;
			
			dsCallExecute dsExec = new dsCallExecute();
			dsExec.type = StatType.EXECUTE;
			
			SQLExpr exp = new SQLExpr();
			exp.value = funCall;
			
			dsExec.argsList.add(exp);
			
			istat.statDetail = dsExec;	
		}
		else
		{
			istat.type = StatType.UNKNOWNSTAT;
		}
		
		if(checkCallType)
		{
			istat.type = StatType.CALLSTAT;
			
			dsCallExecute dsExec = new dsCallExecute();
			dsExec.procName = funCall+";";
			
			dsExec.type = StatType.CALLSTAT;
			istat.statDetail = dsExec;
			
		}
		

		//iField.name = funCall;
		//istat.originalLineNo = orgiLneNo;
		
		istat.originalLine = TranslatorUtils.getStructedExprValue(funCall);
		
		if(!checkSQLExpception)
		{
			//iField.dataType = null;
		}
		else
		{
			//filliStatExceptionObjt(entrySetmap, istat, startToken, endToken, blockStatDtls, currentDbId, newDbId);
		}
		
		return istat;	
	}
	
	private static String getStructedExprValue(String sqlExpr)
	{
		sqlExpr = sqlExpr.replace("( ", "(").replace(" (", "(").replace(" )", ")")
				.replace("[ ", "[").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
				.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").replace(": =", ":=").trim();
		
		return sqlExpr;		
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
		
		istat.originalLine = TranslatorUtils.getStructedExprValue(unkwnVal);
		
		return istat;
	}


	private static iStatement filliBlockTypeStatementObjt(ArrayList<iStatement> innerBlkDtl, int currentDbId,
			int newDbId, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls) 
	{
		StatType statType = getBlockType(innerBlkDtl);
		
		iStatement istat = new iStatement();
		istat.type = statType;
		istat.originalLineNo = innerBlkDtl.get(1).originalLineNo;
	
		if(statType == StatType.IFBLOCK || statType == StatType.EXCEPTIONBLOCK )
		     fillIfExceptionBlockObjt(istat, innerBlkDtl, newDbId, blockStatDtls, currentDbId);
		
		else if(statType == StatType.BEGINBLOCK || statType == StatType.LOOPBLOCK)
			return filliBlockStatementObjt(innerBlkDtl, currentDbId, newDbId, blockStatDtls);
		
		else if(statType == StatType.FORLOOP)
			fillForLoopObjt(istat, innerBlkDtl, currentDbId, newDbId, blockStatDtls);
		
		return istat;
	}	

	private static void fillForLoopObjt(iStatement istat, ArrayList<iStatement> innerBlkDtl, int currentDbId, int newDbId, LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls) 
	{

		dsFor doFor = null;
		
	    if(istat.type == StatType.FORLOOP)
	    	doFor = new dsFor();
	    
	  for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(innerBlkDtl, currentDbId, getOracleBlockScriptKeyWords()).entrySet())
	    {
			Token startToken = entrySet.getKey();
			Token endToken = entrySet.getValue();
			
			if((startToken.data.equalsIgnoreCase("end") && endToken.data.equalsIgnoreCase("loop")) || (startToken.data.equalsIgnoreCase("loop") && endToken.data.equalsIgnoreCase("}")))
				continue;
				
			if(startToken.data.equalsIgnoreCase("{") && endToken.data.equalsIgnoreCase("loop"))
			{
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
				fillBlockCondObjt(null ,blockLst, startToken, endToken, null, doFor, currentDbId, newDbId, null);
			}
			else
			{
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
				
				for(int j = 0 ; j < blockLst.size(); j++)
				{
					iBlockStatement iBlckStmt = filliBlockStatementObjt(blockLst.get(j), currentDbId, newDbId, blockStatDtls);
					if(doFor != null)
					{
						if(iBlckStmt.type == StatType.LOOPBLOCK)
							doFor.forBlock = iBlckStmt;
					}
				}
			}
	    }
	  if(doFor != null)
		  istat.statDetail = doFor;
	}


	private static void fillIfExceptionBlockObjt(iStatement istat, ArrayList<iStatement> innerBlkDtl, int newDbId,
			LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls, int currentDbId)
	{
		dsIF ifObjt = null;
		dsException dsExcep = null;
		
		if(istat.type == StatType.IFBLOCK)
			ifObjt = new dsIF();
		
		if(istat.type == StatType.EXCEPTIONBLOCK)
			dsExcep = new dsException();
		
		
		ArrayList<iStatement> elseIfStmtObjt = new ArrayList<iStatement>();
		
		for(Entry<Token, Token> entrySet : TranslatorUtils.getKeywordClauseForStmts(innerBlkDtl, currentDbId, getOracleBlockScriptKeyWords()).entrySet())
	    {
			Token startToken = entrySet.getKey();
			Token endToken = entrySet.getValue();
			if(((startToken.data.equalsIgnoreCase("If") || startToken.data.equalsIgnoreCase("when"))  && endToken.data.equalsIgnoreCase("}")) || 
					(startToken.data.equalsIgnoreCase("end") && endToken.data.equalsIgnoreCase("if")))
				continue;
			
			if(((startToken.data.equalsIgnoreCase("If")  || startToken.data.equalsIgnoreCase("when") || startToken.data.equalsIgnoreCase("elsIf")) && endToken.data.equalsIgnoreCase("then")))
			{
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
				
				if(startToken.data.equalsIgnoreCase("If"))
					fillBlockCondObjt(ifObjt,blockLst, startToken, endToken, null, null, currentDbId, newDbId, null);
			
				else if(startToken.data.equalsIgnoreCase("elsIf") || startToken.data.equalsIgnoreCase("when"))
				{
					fillBlockCondObjt(null,blockLst, startToken, endToken, null, null, currentDbId, newDbId, elseIfStmtObjt);
				}
			
			}
			else
			{
				ArrayList<ArrayList<iStatement>> blockLst = TranslatorUtils.getGroupOfiStatObject(startToken,endToken, innerBlkDtl);
				
				for(int j = 0 ; j < blockLst.size(); j++)
				{
					iBlockStatement iBlckStmt = filliBlockStatementObjt(blockLst.get(j), currentDbId, newDbId, blockStatDtls);
					
					if(!(startToken.data.equalsIgnoreCase("then") &&  (endToken.data.equalsIgnoreCase("elsif") || endToken.data.equalsIgnoreCase("when"))))
					{
						if(ifObjt != null)
						{
							if(iBlckStmt.type == StatType.THENBLOCK)
								ifObjt.thenBlock = iBlckStmt;
							else if(iBlckStmt.type == StatType.ELSEBLOCK)
								ifObjt.elseBlock = iBlckStmt;
						}
						if(dsExcep != null)
						{
							if(iBlckStmt.type == StatType.THENBLOCK)
								dsExcep.thenBlock = iBlckStmt;
						}
					}
					else
					{
						if(iBlckStmt.type == StatType.THENBLOCK)
						{
							elseIfStmtObjt.add(iBlckStmt);
						}
					}
				}
			}
			
	    }
		
		if(elseIfStmtObjt.size() > 0)
		{
			iBlockStatement iblockStat = new iBlockStatement();
			
			if(ifObjt != null)
				iblockStat.type = StatType.ELSEIFBLOCK;
			
			for(int j = 0 ; j < elseIfStmtObjt.size(); j++)
			{
				float KeyVal = j;
				iblockStat.iBlockStatements.put(KeyVal, elseIfStmtObjt.get(j));
			}
			
			if(ifObjt != null)
				ifObjt.elseIfBlock = iblockStat;
			
			if(dsExcep != null)
			{
				if(dsExcep.thenBlock == null)
					dsExcep.thenBlock = iblockStat;
				else
					dsExcep.condition = iblockStat;
			}
		}
		
		if(ifObjt != null)
			istat.statDetail = ifObjt;
		
		if(dsExcep != null)
			istat.statDetail = dsExcep;
		
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
		
		else if(innerBlkDtl.get(1).tokenList.get(0).data.equalsIgnoreCase("Exception"))
			return StatType.EXCEPTIONBLOCK;
		
		else if(innerBlkDtl.get(1).tokenList.get(0).data.equalsIgnoreCase("When"))
			return StatType.WHEN;
		
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
	
	public static HashMap<String, String> getOracleBlockScriptKeyWords()
	{
		LinkedHashMap<String, String> OracleScriptKeyWord = new LinkedHashMap<String,String>();
		OracleScriptKeyWord.put("{", "{");
		OracleScriptKeyWord.put("CREATE", "create");
		OracleScriptKeyWord.put("REPLACE", "replace");
		OracleScriptKeyWord.put("BEGIN", "begin");
		OracleScriptKeyWord.put("EXCEPTION", "exception");
		OracleScriptKeyWord.put("WHEN", "when");
		OracleScriptKeyWord.put("LOOP", "loop");
		OracleScriptKeyWord.put("WHILE", "while");
		OracleScriptKeyWord.put("DO", "do");
		OracleScriptKeyWord.put("IF", "if");
	    OracleScriptKeyWord.put("THEN", "then");
		OracleScriptKeyWord.put("ELSIF", "elsif");
		OracleScriptKeyWord.put("ELSE", "else");
		OracleScriptKeyWord.put("END", "end");
		OracleScriptKeyWord.put("}", "}");
		
		return OracleScriptKeyWord;
	}
	

	private static HashMap<String, String> getOracleScriptKeyWordDtls() 
	{
		LinkedHashMap<String, String> OracleScriptKeyWord = new LinkedHashMap<String,String>();
		
		OracleScriptKeyWord.put("BEGIN", "begin");
		OracleScriptKeyWord.put("EXCEPTION", "exception");
		OracleScriptKeyWord.put("LOOP", "loop");
		OracleScriptKeyWord.put("DECLARE", "declare");
		OracleScriptKeyWord.put("EXECUTE", "execute");
		OracleScriptKeyWord.put("WHILE", "while");
		OracleScriptKeyWord.put("LOCKING", "locking");
		OracleScriptKeyWord.put("LOCK", "lock");
		OracleScriptKeyWord.put("ACCESS", "access");
		OracleScriptKeyWord.put("DO", "do");
		OracleScriptKeyWord.put("IF", "if");
		OracleScriptKeyWord.put("WHEN", "when");
		OracleScriptKeyWord.put("THEN", "then");
		OracleScriptKeyWord.put("ELSIF", "elsif");
		OracleScriptKeyWord.put("ELSE", "else");
		OracleScriptKeyWord.put("END", "end");
		OracleScriptKeyWord.put(";", ";");
		OracleScriptKeyWord.put("}", "}");
		
		return OracleScriptKeyWord;
	}
	
	private static void fillBlockCondObjt(dsIF ifObjt, ArrayList<ArrayList<iStatement>> blockLst, Token startToken,
			Token endToken, dsDoWhile doWhile, dsFor doFor, int currentDbId, int newDbId, ArrayList<iStatement> elseIfStmtObjt) 
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
		
		exprVal = TranslatorUtils.getStructedExprValue(exprVal);
		
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
		
		if(elseIfStmtObjt != null)
		{
			elseIfStmtObjt.add(istat);
		}
		
		
	}


}