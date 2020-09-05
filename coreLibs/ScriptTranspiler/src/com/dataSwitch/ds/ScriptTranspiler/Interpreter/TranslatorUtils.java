
package com.dataSwitch.ds.ScriptTranspiler.Interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Stack;

import com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata.TDProcDtls;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class TranslatorUtils 
{

	public static LinkedHashMap<String, ArrayList<iStatement>> getStatementNodes(
			LinkedHashMap<Float, iStatement> newTokenCloseBraceMap, int currentDbId, String startKey, String endKey, String extension, int newDbId) 
	{
		
		LinkedList<Token> nodeToken = new LinkedList<Token>(); 
		LinkedHashMap<Token, Float> blockNode = new LinkedHashMap<Token, Float>();
		 
		for (Entry<Float, iStatement> entrySet : newTokenCloseBraceMap.entrySet())
    	{
    		float keyVal = entrySet.getKey();
    		
    		ArrayList<Token> tokenList = entrySet.getValue().tokenList;
    		
    		for (int i = 0; i < tokenList.size(); i++) 
    		{
    			nodeToken.add(tokenList.get(i));
    			blockNode.put(tokenList.get(i), keyVal);
			}
    		
    	}
		
		LinkedList<LinkedList<Token>> nodeLists = new LinkedList<LinkedList<Token>>();
		Stack<Token> stkNode = new Stack<Token>();
		
		getStmtPosNodes(startKey, endKey, nodeToken, stkNode, nodeLists, blockNode);
		
		LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls = createBlockStatement(nodeLists,newTokenCloseBraceMap,startKey, endKey);
	
		//istatDtls = DbInterpreter.getDbiStatementDtls(blockStatDtls, currentDbId, extension, newDbId);
		
		return blockStatDtls;
	}

	
	public static ArrayList<ArrayList<iStatement>> getGroupOfiStatObject(Token startToken,
			Token endToken, ArrayList<iStatement> outeriStatDetail) 
	{
		ArrayList<ArrayList<iStatement>> nodeHashmap = new ArrayList<ArrayList<iStatement>>();
		
		boolean tpMainflag = false;
		boolean tpflag = false;
		
		ArrayList<iStatement> otherToken = new ArrayList<iStatement>();
		
		for(int i = 0 ; i < outeriStatDetail.size() ; i++)
		{
			  iStatement istmt = outeriStatDetail.get(i);
			  ArrayList<Token> statementToken = istmt.tokenList;
			  
			    if(statementToken.size() > 0)
			    {
				    if(statementToken.get(0) == startToken)
				    {
				    	tpMainflag = true;
				    }
			    }
		    		    
				if(tpMainflag)
				{
					if(statementToken.size() > 0)
					{
						if((statementToken.get(0) == startToken) && tpflag == false)
						{
							tpflag = true;
						}
					}
					
					if(tpflag)
		        	{
						otherToken.add(istmt);
						
						if(statementToken.size() > 0)
						{
			        		if(statementToken.get(0) == endToken)   
				        	{
			        		   nodeHashmap.add(otherToken);
			        			
			        			tpflag = false;
			        			tpMainflag = false;
			        			otherToken = new ArrayList<iStatement>();
				        	}
						}
		        	}
					
				}
			
		}
		
		return nodeHashmap;
	}

	public static HashMap<Token,Token> getKeywordClauseForStmts(ArrayList<iStatement> outeriStatDetail, int currentDbId, HashMap<String,String> sqlClauseKeyWords) 
	{
		LinkedHashMap<Token,Token> tokenNode = new LinkedHashMap<Token,Token>();
		
		ArrayList<Token> keyWordsLst = new ArrayList<Token>();
		for (int i = 0; i < outeriStatDetail.size(); i++) 
		{
			if(outeriStatDetail.get(i).type == StatType.SQLQUERY || outeriStatDetail.get(i).type == StatType.BLANKSTAT)
				continue;
			
			for(int j = 0 ; j < outeriStatDetail.get(i).tokenList.size(); j++)
			{
				if(sqlClauseKeyWords.get(outeriStatDetail.get(i).tokenList.get(j).data.toUpperCase())  != null)
				{
					keyWordsLst.add(outeriStatDetail.get(i).tokenList.get(j));
				}
			}
		}
		
		for(int i = 0 ; i+1 < keyWordsLst.size() ;i++)
		{
			tokenNode.put(keyWordsLst.get(i), keyWordsLst.get(i+1));
		}
		
		return tokenNode;
	   
	}

	public static ArrayList<iStatement> getOuteriStatDetail(
			LinkedHashMap<String, ArrayList<iStatement>> blockStatDtls) 
	{
		ArrayList<iStatement> iStatDtls = null;
		for(Entry<String, ArrayList<iStatement>> iStatEntry : blockStatDtls.entrySet())
		{
			//System.out.println("iStatEntry.getKey()-------------"+iStatEntry.getKey());
			for(int i = 0 ; i < iStatEntry.getValue().size() ; i++)
			{
				iStatement iStatDtlLst = iStatEntry.getValue().get(i);
				//System.out.println(iStatDtlLst.tokenList);
				
				if(iStatDtlLst.type == StatType.SQLQUERY || iStatDtlLst.type == StatType.BLANKSTAT)
				    iStatDtlLst.isBeingUsed = 78;
				
			}
			
			
			iStatDtls = iStatEntry.getValue();
		}
		
		return iStatDtls;
	}


	private static LinkedHashMap<String, ArrayList<iStatement>> createBlockStatement(LinkedList<LinkedList<Token>> nodeLists, LinkedHashMap<Float, iStatement> nodeHashMap, String startKey, String endKey) 
	{
		LinkedList<ArrayList<iStatement>> tmp = new LinkedList<ArrayList<iStatement>>();
		
		
		for(int i = 0 ; i < nodeLists.size(); i++)
		{
			
			ArrayList<iStatement> tmpSt = new ArrayList<iStatement>();
			LinkedList<Token> lnkdNode = nodeLists.get(i);
			
			boolean flag = false;
			
			Token startToken = lnkdNode.get(0);
			Token endToken = lnkdNode.get(1);
			for(Entry<Float, iStatement> entrySet : nodeHashMap.entrySet())
			{
				ArrayList<Token> tknLists = entrySet.getValue().tokenList;
				iStatement iSt = new iStatement();
				
				if(flag)
				{
					if (entrySet.getValue().type == StatType.SQLQUERY || entrySet.getValue().type == StatType.BLANKSTAT)//For Sql
					{
						tmpSt.add(entrySet.getValue());
					}
				}
				
				for (int j = 0; j < tknLists.size(); j++)
				{
					Token nodeTkn = tknLists.get(j);
					
					if (startToken == nodeTkn)
					{
						if (nodeTkn.data.equalsIgnoreCase(startKey))
						{
							nodeTkn.isBeingUsed = (byte) i;
							flag = true;
						}
					}
					if (flag)
					{
						if(entrySet.getValue().type == StatType.SQLQUERY || entrySet.getValue().type == StatType.BLANKSTAT)
							continue;
						
						iSt.tokenList.add(nodeTkn);
						iSt.originalLineNo = entrySet.getValue().originalLineNo;
						iSt.type = entrySet.getValue().type;
						
						if(!tmpSt.contains(iSt))
						{
							tmpSt.add(iSt); 
						}
                        
						if (endToken == nodeTkn)
						{
							if (nodeTkn.data.equalsIgnoreCase(endKey))
							{
								nodeTkn.isBeingUsed = (byte) i;
								tmp.add(tmpSt);
								
								tmpSt = new ArrayList<iStatement>();
								flag = false;
							}
						}
						
					}
				
				}
			}
		}
		
		
		boolean startFlag1 = false;
		ArrayList<iStatement> tmpList2 = new ArrayList<iStatement>();
		LinkedHashMap<String, ArrayList<iStatement>> finalBlk2 = new LinkedHashMap<String, ArrayList<iStatement>>();
		
		for (int j = 0; j < tmp.size(); j++) 
		{
			int tmpVal = -1, startVal = -1;
			int cnt = 0;
			boolean endFlag = false;
			
		    for(int i2 = 0 ; i2 < tmp.get(j).size(); i2++)
		    {
		    	int originalLnNo = tmp.get(j).get(i2).originalLineNo;
		    	StatType stmtType = tmp.get(j).get(i2).type;
		    	
		    	ArrayList<Token> finalTknLst = tmp.get(j).get(i2).tokenList;
		    	
		    	if(stmtType == StatType.SQLQUERY || stmtType == StatType.BLANKSTAT)
		    	{
		    		if((!tmpList2.contains(tmp.get(j).get(i2))) && tmp.get(j).get(i2).isBeingUsed == 78)
		    		{
		    			tmpList2.add(tmp.get(j).get(i2));
		    			tmp.get(j).get(i2).isBeingUsed = 89;		    		
		    		}
		    	}
		    	
		    	if(stmtType == StatType.SQLQUERY || stmtType == StatType.BLANKSTAT)
		    		continue;
		    	
		    	for (int j1 = 0; j1 < finalTknLst.size(); j1++) 
		    	{
		    		Token tkn = finalTknLst.get(j1);
		    		
		    		int val = tkn.isBeingUsed;
		    		
		    		if (val == startVal)
		    		{
		    			iStatement ist = new iStatement();
		    			ist.tokenList.add(tkn);
		    			ist.originalLineNo = originalLnNo;
		    			ist.type = stmtType;
		    			tmpList2.add(ist);
		    			
		    			startVal = -1;
		    			endFlag = false;
		    			break;
		    		}
		    		
		    		if (cnt == 0)
		    			startVal = val; 
		    		
		    		if (startFlag1)
		    		{
		    			if (val == tmpVal)
		    			{
		    				endFlag = true;
		    				
		    				ArrayList<Token> token = FunctionTypeConv.getTokens("ds__Block_"+val);
		    				
		    				iStatement istat2 = new iStatement();
		    				istat2.type = StatType.UNKNOWNSTAT;
		    				istat2.tokenList.addAll(token);
		    				tmpList2.add(istat2);
		    				
		    				tmpVal = -1;
		    				startFlag1 = false;
		    				cnt = 0;
		    			}
		    		}
		    		if (val != 78 && cnt > 0)
		    		{
		    			if (tmpVal == -1)
		    			{
		    				startFlag1 = true;
			    			tmpVal = val;
		    			}
		    		}
		    		if (!startFlag1)
		    		{
		    			if (!endFlag)
		    			{
		    				iStatement istat2 = new iStatement();
		    				istat2.tokenList.add(tkn);
		    				istat2.originalLineNo = originalLnNo;
		    				istat2.type = stmtType;
		    				tmpList2.add(istat2);
		    			}
		    			else
		    				endFlag = false;
		    			
		    		}
		    		
		    		cnt++;
		    	}
		    }
		    
		    finalBlk2.put("ds__Block_"+j, tmpList2);
		    tmpList2 = new ArrayList<iStatement>();
		}
		
		return finalBlk2;
		
	}
	
  
	private static void getStmtPosNodes(String startKey, String endKey, LinkedList<Token> nodeToken,
			Stack<Token> stkNode, LinkedList<LinkedList<Token>> nodeLists,
			LinkedHashMap<Token, Float> blockNode) 
	{
		for(int i = 0; i <  nodeToken.size() ; i++)
		{
			if(nodeToken.get(i).data.equalsIgnoreCase(endKey))
			{
				LinkedList<Token> innerNode = new LinkedList<Token>();
				while(true)
				{
					if(!stkNode.isEmpty())
					{
						Token checkOpenParan = stkNode.pop();  
						if(checkOpenParan.data.equalsIgnoreCase(startKey))
						{
						    float getVal = blockNode.get(checkOpenParan);
						    innerNode.add(checkOpenParan); 
							break; 
						}
						
					}
					else 
						break;
				}
				float getVal = blockNode.get(nodeToken.get(i));
				innerNode.add(nodeToken.get(i));
				nodeLists.add(innerNode);
			}
			else
			{
				stkNode.push(nodeToken.get(i));
			}
			
		}
		
	}
	
	public static String getStructedExprValue(String sqlExpr)
	{
		sqlExpr = sqlExpr.replace("( ", "(").replace(" (", "(").replace(" )", ")")
				.replace("[ ", "[").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
				.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").trim();
		
		return sqlExpr;		
	}

}