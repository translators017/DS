
package com.dataSwitch.ds.sql.SQLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionTypeRules.TokenNodes;
import com.dataSwitch.ds.functionparser.Lexer;
import com.dataSwitch.ds.functionparser.Token;


public class SQLUtils 
{
	
	
	public static HashMap<String,String> getStmtNodes(String StartKey,String endKey,ArrayList<Token> tokens, String nodeTyp, String inputSQL) 
	{
		 inputSQL = "("+ inputSQL + ")";
		 ArrayList<Token> tokenObjts = FunctionTypeConv.getTokens(inputSQL);
		 HashMap<String,String> str = getClauseStmtNodesObjts(StartKey,endKey,tokenObjts,nodeTyp);
		 str = TokenNodes.getUpdatedNodes(str);
		 
		return str;
	}
	
	
	public static final HashMap<String,String> getClauseStmtNodesObjts(String StartKey,String endKey,ArrayList<Token> tokens, String nodeTyp)
	{
		char[] tokencode = new char[tokens.size()]; 
		
		for (int i = 0; i < tokens.size(); i++) 
		{
			Token sqlTokens = tokens.get(i);
			
			createTokenTypeChars(sqlTokens, tokencode,i);
			
		}
		
		HashMap<Token,String> indexPosition = getHashMapIndexPosition(tokens);
		
		List<List<Token>> posOfNestedExp  = getStmtPosNodes(StartKey,endKey,tokens);
		
		
		LinkedHashMap<String,String> strValMaps = new LinkedHashMap<String,String>();
		if(posOfNestedExp.size() > 0)
		{
			int count = 0;
			for(int i = 0 ; i < posOfNestedExp.size(); i++)
			{
				if(posOfNestedExp.get(i).size() > 1)
				{
					String keyValue = nodeTyp+"_"+count++;
					HashMap<String,List<String>> nestedStr = getNodeForNestedFuncs(keyValue,tokens,posOfNestedExp.get(i),indexPosition,tokencode);
					List<String> strVals = nestedStr.get(keyValue);
					String nestedVal = "";
					for(int i1 = 0 ; i1 < strVals.size(); i1++)
					{
						String val = strVals.get(i1);
						nestedVal += val.replace("( ", "(").replace(") ", ")").replace("[ ", "[").replace("] ", "]").replace("  ", "");
					}
					nestedVal = nestedVal.trim();
					strValMaps.put(keyValue, nestedVal);
				}	
			}
		}
		
		return strValMaps;
	}

	public static char[] createTokenTypeChars(Token tokens, char[] tokencode, int i)
	{
		if(tokens.type.name().equalsIgnoreCase("OPENPAREN"))
			tokencode[i] = '4';
		
		else if(tokens.type.name().equalsIgnoreCase("CLOSEPAREN"))
			tokencode[i] = '5';
		
		else if(tokens.type.name().equalsIgnoreCase("LOGICALOP"))
			tokencode[i] = '2';
		
		else if(tokens.type.name().equalsIgnoreCase("VARWITHDOT") || tokens.type.name().equalsIgnoreCase("VAR"))
			tokencode[i] = '3';
		
		else if(tokens.type.name().equalsIgnoreCase("DECSEP"))
			tokencode[i] = '6';
		
		else
			tokencode[i] = '1';
		
		return tokencode;
	
	}
	
	private static List<List<Token>> getStmtPosNodes(String startKey, String endKey, ArrayList<Token> tokens) 
	{
		List<List<Token>> NodeLists = new ArrayList<List<Token>>();
		Stack<Token> stkNode = new Stack<Token>();
		for(Token t1 : tokens)
		{
			if(t1.data.equalsIgnoreCase(endKey))
			{
				List<Token> nodeObjt = new ArrayList<Token>();
				
				while(true)
				{
					if(!stkNode.isEmpty())
					{
						Token checkOpenParan = stkNode.pop();  
						if(checkOpenParan.data.equalsIgnoreCase(startKey))
						{
								
							nodeObjt.add(checkOpenParan);
							break; 
						}
						
					}
					else 
						break;
				}
				nodeObjt.add(t1);
				
				NodeLists.add(nodeObjt);
			}
			else
			{
				stkNode.push(t1);
			}
			
		}
		return NodeLists;
	}
	
	private static LinkedHashMap<Token, String> getHashMapIndexPosition(
			ArrayList<Token> alltokens) 
	{
		LinkedHashMap<Token,String> hashMap = new LinkedHashMap<Token,String>();
		
		for(int i = 0 ; i < alltokens.size();i++)
		{
			Token token = alltokens.get(i);
			hashMap.put(token,i+"");
		}
		return hashMap;
	}
	
	public static HashMap<String,List<String>> getNodeForNestedFuncs(String keyValue, ArrayList<Token> alltokens,	List<Token> list, HashMap<Token, String> indexPosition, char[] tokencode)
	{
		boolean mainFlag = false;
		boolean flag = false;
		
		String fn = "";
		List<String> funcs = new ArrayList<String>();
		for(int i = 0 ; i < alltokens.size(); i++)
		{
		   String inPos = indexPosition.get(list.get(0));
		   String endPos = indexPosition.get(list.get(1));
		   int initialPos = Integer.parseInt(inPos);
		   int endPostn = Integer.parseInt(endPos);
		   Token token = alltokens.get(i); 
		   String curntFun = token.data;
		   
		   if(initialPos == i)
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
					 if(token.type.name().equals("OPENPAREN") || token.type.name().equals("CLOSEPAREN") 
			    			   || token.type.name().equals("BINARYOP") || token.type.name().equals("DECSEP")  || token.type.name().equals("EOL") 
			    			   || token.type.name().equals("BRACKET"))
					 {
						 fn += curntFun;
					 }
			    	 else
			    		 fn += " "+curntFun;
					 
	        		if(endPostn == i)   
		        	{
	        			funcs.add(fn);
	        			flag = false;
	        			fn = "";
		        	}	
	        		
				}
				else
					mainFlag = false;
				
			}
		    
		}
		
		HashMap<String,List<String>> nodefunc= new HashMap<String,List<String>>();
		nodefunc.put(keyValue, funcs);
		
		return nodefunc;
		
	}
	
	public static final String getUniqueCompName(String newCompName, ArrayList<String> uniqueCompNamesLst)
	{
		if(newCompName == null)
			return null;
		
		String compName = newCompName.toLowerCase();
		int cnt = 1;
		
		newCompName = compName;
		
		if(uniqueCompNamesLst.size() > 0)
		{
			while(uniqueCompNamesLst.contains(newCompName))
			{
				newCompName = compName + cnt;
				cnt++;
			}
		}
		
		uniqueCompNamesLst.add(newCompName);
		
		return newCompName;
		
	}
	
}