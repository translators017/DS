
package com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.ScriptTranspiler.ScriptParser.Utils;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;

public class AddCurleyBraceForScriptKeyWords 
{

	public static void addCurleyBracesForProcScripts(LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId,
			int newDbId) 
	{
		
		LinkedList<String> packFnProcNames = new LinkedList<String>();
		
		ArrayList<Token> checkExceptionList = new ArrayList<Token>();
		
		for(Entry<Float, iStatement> entrysqlNodes : sqlNodeMaps.entrySet())
		{	
			
			if(entrysqlNodes.getValue().type == StatType.SQLQUERY ||
					entrysqlNodes.getValue().type == StatType.BLANKSTAT)
				continue;
			
			ArrayList<Token> token = entrysqlNodes.getValue().tokenList;
			
			ArrayList<Token>curleyToken = getUpdTokenForProcScripts(token, packFnProcNames, currentDbId, checkExceptionList);
			
			entrysqlNodes.setValue(Utils.getiStatementObjt(entrysqlNodes.getValue().type, entrysqlNodes.getValue().originalLineNo, curleyToken ,entrysqlNodes.getValue().originalLine));
		}
		
		//if(currentDbId == ToolTypeConstants.ORACLE)
			//updateProcStatementForProcFns (sqlNodeMaps, packFnProcNames);
		
	}
	
//	public static ArrayList<Token> getUpdTokenForProcScriptsOrac(ArrayList<Token> tokenNode,
//			LinkedList<String> packFnProcNames, int currentDbId)
//	{
//		
//        Boolean flag = false;
//		
//		ArrayList<Token> token = new ArrayList<Token>();
//		
////		for(int k = 0 ; k < ScriptKeywordsForDbs.getRespectiveKeyWordForDbs(currentDbId).size() ; k++)
//		{
////			ScriptTypeRule scriptTypeRule= ScriptKeywordsForDbs.getRespectiveKeyWordForDbs(currentDbId).get(k);
//			
////			String startKeyWord = scriptTypeRule.getStartKeyWord();
////			String endKeyWord = scriptTypeRule.getEndKeyWord();
//			for (int i = 0; i < tokenNode.size(); i++) 
//			{
//				if (tokenNode.get(i).data.equalsIgnoreCase("begin") || tokenNode.get(i).data.equalsIgnoreCase("loop") || tokenNode.get(i).data.equalsIgnoreCase("if"))// loop, if
//				{
//					if (i >= 1)
//					{
//						if (!tokenNode.get(i-1).data.equalsIgnoreCase("end"))	//end
//						{
//							if(tokenNode.get(i).data.equalsIgnoreCase("For"))
//							{
//								if((tokenNode.get(i-1).data.equalsIgnoreCase(";") && tokenNode.get(i-1).type.name().equalsIgnoreCase("EOL")) 
//										&& tokenNode.get(i).data.equalsIgnoreCase("For"))
//								{
//										token.add(i, addCurlyBrace());
//								}
//							}
//							else
//								token.add(i, addCurlyBrace());
//						}
//					}
//					else if (i == 0)
//					{
//						if(tokenNode.get(i).data.equalsIgnoreCase("For"))
//						{
//							token.add(i, addCurlyBrace());
//						}
//						else
//							token.add(i, addCurlyBrace());
//					}
//				}
//				
//				if (tokenNode.get(i).data.equalsIgnoreCase("end"))
//					flag = true;
//					
//					if (flag)
//					{
//						if(i >= 1)
//						{
//							if (i == 1)
//							{
//								if(tokenNode.get(i).data.equalsIgnoreCase(";") && tokenNode.get(i).type.name().equalsIgnoreCase("EOL"))//Check for end ;
//								{
//									if (tokenNode.get(i-1).data.equalsIgnoreCase("end"))// End
//									{
//										if(!token.contains(addCloseBrace()))
//											token.add(i, addCloseBrace());
//									}
//											
//								}
//								if (!(tokenNode.get(i).data.equalsIgnoreCase("begin") || tokenNode.get(i).data.equalsIgnoreCase("loop") || tokenNode.get(i).data.equalsIgnoreCase("if") ||
//										(tokenNode.get(i).data.equalsIgnoreCase(";") && tokenNode.get(i).type.name().equalsIgnoreCase("EOL"))))
//								{
//									if (packFnProcNames.size() == 0)
//										packFnProcNames.add(tokenNode.get(i).data);
//									else
//									{
//										if (!packFnProcNames.contains(tokenNode.get(i).data))
//											packFnProcNames.add(tokenNode.get(i).data);
//									}
//								}
//							}
//							if (i > 1)
//							{
//								if(tokenNode.get(i).data.equalsIgnoreCase(";") && tokenNode.get(i).type.name().equalsIgnoreCase("EOL"))///Check for end loop; and end if;
//								{
//									if (tokenNode.get(i-1).type.name().equalsIgnoreCase("var") || 
//											tokenNode.get(i-1).type.name().equalsIgnoreCase("varwithdot"))
//									{
//										if(!token.contains(addCloseBrace()))
//											token.add(i, addCloseBrace());
//									}
//								}
//							}
//							
//							
//						}
//					}
//					
//					///System.out.println("b4     "+token);
//					
//					  if(token.size() < tokenNode.size())
//                          token.add(tokenNode.get(i));
//					  
//                      else if (token.size() == tokenNode.size())
//                      {
//                          if (token.size()-1 >= 0)
//                          {
//                              Token tmp = token.get(0);//token.size()-1);
////                              System.out.println("after     "+tmp+"   "+tokenNode.get(i));
//                              if ((tmp.type.name().equalsIgnoreCase("OPENBRACKET") || tmp.type.name().equalsIgnoreCase("CLOSEBRACKET")) && (tokenNode.get(i) != tmp))
//                              {
//                                  token.add(tokenNode.get(i));
//                                  return token;
//                              }
//                          }
//                      }
////					  System.out.println("after     "+token);
//			}
//		}
////		System.out.println(token);
//		
//		return token;
//	}
	
	public static ArrayList<Token> getUpdTokenForProcScripts(ArrayList<Token> tokenNode,
			LinkedList<String> packFnProcNames, int currentDbId, ArrayList<Token> checkExceptionList)
	{
		
        Boolean flag = false;
        
		ArrayList<Token> token = new ArrayList<Token>();
		
		for(int k = 0 ; k < ScriptKeywordsForDbs.getRespectiveKeyWordForDbs(currentDbId).size() ; k++)
		{
			ScriptTypeRule scriptTypeRule= ScriptKeywordsForDbs.getRespectiveKeyWordForDbs(currentDbId).get(k);
			
			String startKeyWord = scriptTypeRule.getStartKeyWord();
			String endKeyWord = scriptTypeRule.getEndKeyWord();

			for (int i = 0; i < tokenNode.size(); i++) 
			{
				if (tokenNode.get(i).data.equalsIgnoreCase(startKeyWord))// loop, if
				{
					if (i >= 1)
					{
						if (!tokenNode.get(i-1).data.equalsIgnoreCase(endKeyWord))	//end
						{
							if(tokenNode.get(i).data.equalsIgnoreCase("For"))
							{
								if((tokenNode.get(i-1).data.equalsIgnoreCase(";") && tokenNode.get(i-1).type.name().equalsIgnoreCase("EOL")) 
										&& tokenNode.get(i).data.equalsIgnoreCase("For"))
								{
										token.add(i, addCurlyBrace());
								}
							}
							else
							{
								if(currentDbId == ToolTypeConstants.ORACLE)
								{
									if(tokenNode.get(i).data.equalsIgnoreCase("EXCEPTION"))
										checkExceptionList.add(tokenNode.get(i));
								}
								
								token.add(i, addCurlyBrace());
								
							}
						}
					}
					else if (i == 0)
					{
						token.add(i, addCurlyBrace());
						
						if(currentDbId == ToolTypeConstants.ORACLE)
						{
							if(tokenNode.get(i).data.equalsIgnoreCase("EXCEPTION"))
								checkExceptionList.add(tokenNode.get(i));
						}
					}
				}
								
				if (tokenNode.get(i).data.equalsIgnoreCase(endKeyWord))
					flag = true;
				
					if (flag)
					{
						if(i >= 1)
						{
							if (i == 1)
							{
								if(tokenNode.get(i).data.equalsIgnoreCase(";") && tokenNode.get(i).type.name().equalsIgnoreCase("EOL"))//Check for end ;
								{	
									if (tokenNode.get(i-1).data.equalsIgnoreCase(endKeyWord))// End
									{
										if(!token.contains(addCloseBrace()))
										{
											if(currentDbId == ToolTypeConstants.ORACLE)
											{
												if(checkExceptionList.size() > 0)
												{
													
													if(!token.contains(addCloseBrace()))
														token.add(i, addCloseBrace());
													
													checkExceptionList.clear();
												}
											}
											
											token.add(i, addCloseBrace());
										}
									}
											
								}
								if (!(tokenNode.get(i).data.equalsIgnoreCase(startKeyWord) ||
										(tokenNode.get(i).data.equalsIgnoreCase(";") && tokenNode.get(i).type.name().equalsIgnoreCase("EOL"))))
								{
									if (packFnProcNames.size() == 0)
										packFnProcNames.add(tokenNode.get(i).data);
									else
									{
										if (!packFnProcNames.contains(tokenNode.get(i).data))
											packFnProcNames.add(tokenNode.get(i).data);
									}
								}
							}
							if (i > 1)
							{
								if(tokenNode.get(i).data.equalsIgnoreCase(";") && tokenNode.get(i).type.name().equalsIgnoreCase("EOL"))///Check for end loop; and end if;
								{
									if (tokenNode.get(i-1).type.name().equalsIgnoreCase("var") || 
											tokenNode.get(i-1).type.name().equalsIgnoreCase("varwithdot"))
									{
										if(!token.contains(addCloseBrace()))
										{
											if(currentDbId == ToolTypeConstants.ORACLE)
											{
												if(checkExceptionList.size() > 0)
												{
													
													if(!token.contains(addCloseBrace()))
														token.add(i, addCloseBrace());
													
													checkExceptionList.clear();
												}
											}
											
											token.add(i, addCloseBrace());
										}
										
										
									}
								}
							}
							
							
						}
					}
					
					  if(token.size() < tokenNode.size())
                          token.add(tokenNode.get(i));
					  
                      else if (token.size() == tokenNode.size())
                      {
                          if (token.size()-1 >= 0)
                          {
                              Token tmp = token.get(token.size()-1);
                        	  //Token tmp = token.get(0);
                              
                              if ((tmp.type.name().equalsIgnoreCase("OPENBRACKET") || tmp.type.name().equalsIgnoreCase("CLOSEBRACKET")) && (tokenNode.get(i) != tmp))
                              {
                                  token.add(tokenNode.get(i));
                                  //return token;
                              }
                          }
                      }
					  
			}
		}
	
		return token;
	}
	

	public static Token addCurlyBrace() 
	{
		byte val = 0;
		TokenType t = TokenType.OPENBRACKET;
		Token curleyToken = new Token(t, "{", val);
		
		return curleyToken;
	}
	public static Token addCloseBrace() 
	{
		byte val = 0;
		TokenType t = TokenType.CLOSEBRACKET;
		Token curleyToken = new Token(t, "}", val);
		
		return curleyToken;
	}
	
	private static LinkedHashMap<Float, iStatement> updateProcStatementForProcFns(LinkedHashMap<Float, iStatement> sqlNodeMaps, LinkedList<String> packFnProcNames)
	{
		for(Entry<Float, iStatement> entryScript : sqlNodeMaps.entrySet())
		{
			float getKeyVal = entryScript.getKey();
			ArrayList<Token> token = entryScript.getValue().tokenList;
			
			if(entryScript.getValue().type == StatType.SQLQUERY || 
					entryScript.getValue().type == StatType.BLANKSTAT)
				continue;
			
			ArrayList<Token>  getUpdTknForOracleFnNames = addCurleyBraceForFncs(token, packFnProcNames, sqlNodeMaps, getKeyVal);
			
			entryScript.setValue(Utils.getiStatementObjt(entryScript.getValue().type, entryScript.getValue().originalLineNo, getUpdTknForOracleFnNames, entryScript.getValue().originalLine));
		}
		return sqlNodeMaps;
	}
	
	private static ArrayList<Token> addCurleyBraceForFncs(ArrayList<Token> token, LinkedList<String> packFnProcNames, LinkedHashMap<Float, iStatement> sqlNodeMaps, float getKeyVal) 
	{
		String CheckName = "";
		ArrayList<Token> nodeTkn = new ArrayList<Token>();
		
		for (int i = 0; i < token.size(); i++) 
		{
			for (int j = 0; j < packFnProcNames.size(); j++) 
			{
				String name = packFnProcNames.get(j);
				String schemaName = "";
				Boolean flag = false;
				
				if (token.get(i).data.contains(".") && token.get(i).type.name().equalsIgnoreCase("varwithdot"))
				{
					schemaName = token.get(i).data.substring(0,  token.get(i).data.indexOf("."));
					CheckName =  token.get(i).data.substring( token.get(i).data.indexOf(".")+1,  token.get(i).data.length());
				}
				
				if (i == 0)
					continue;
				
				if ((name.equalsIgnoreCase(token.get(i).data) || name.equalsIgnoreCase(CheckName)) && (!token.get(i-1).data.equalsIgnoreCase("end")))
				{
					flag = getProcFnFlag (token.get(i-1).data, sqlNodeMaps, getKeyVal, name);
					if (flag)
					{
						if (!schemaName.equalsIgnoreCase(""))
						{
							nodeTkn.add(i, addCurlyBrace());
							break;
						}
						else
						{
							nodeTkn.add(i, addCurlyBrace());
							break;
						}
					}
				}
			}
			nodeTkn.add(token.get(i));
		}
		
		return nodeTkn;
	}



	private static Boolean getProcFnFlag(String data, LinkedHashMap<Float, iStatement> sqlNodeMaps, float getKeyVal2, String name) 
	{
		Boolean flag = false, tknFlag = false, paranFlag = false;
		String startParan = "(", closeParan = ")";
		
		if (data.equalsIgnoreCase("function") || data.equalsIgnoreCase("procedure"))
		{
			for(Entry<Float, iStatement> entryScript : sqlNodeMaps.entrySet())
			{
				float getKeyVal = entryScript.getKey();
				
				if(entryScript.getValue().type == StatType.SQLQUERY)
					continue;
				
				if (getKeyVal == getKeyVal2)
					flag = true;
				
				if (!flag)
					continue;
				
				ArrayList<Token> token = entryScript.getValue().tokenList;
				
				for (int j = 0; j < token.size(); j++) 
				{
					Token tkn = token.get(j);
					String tknName = tkn.data;
					if (tknName.equalsIgnoreCase(name))
						tknFlag = true;
					
					if (!tknFlag)
						continue;
					
					if(startParan.equalsIgnoreCase(tknName))
						paranFlag = true;
					
					if (!paranFlag)
						continue;
					
					if(closeParan.equalsIgnoreCase(tknName))
					{
						//Get nxt 3 words for fn Ex:return clob ;
						//Get 1 words for procedure Ex: ;
						//If satisfies above 2 return false
						LinkedList<String> nxtTknName = new LinkedList<String>();
						if (data.equalsIgnoreCase("function"))
						{
							getNxtTokenDetails (sqlNodeMaps, getKeyVal, nxtTknName);
							if (nxtTknName.get(0).equalsIgnoreCase("return"))
								return false;
							else
								return true;
//							{
//								if (nxtTknName.get(2).equalsIgnoreCase(";"))
//									return false;
//								else
//									return true;
//							}
						}
						if (data.equalsIgnoreCase("procedure"))
						{
							getNxtTokenDetails (sqlNodeMaps, getKeyVal, nxtTknName);
							if (nxtTknName.get(0).equalsIgnoreCase(";") || nxtTknName.get(0).equalsIgnoreCase("as") || nxtTknName.get(0).equalsIgnoreCase("is"))
								return false;
							else
								return true;
						}
					}
				}
			}
		}
		if (data.equalsIgnoreCase("package") || data.equalsIgnoreCase("body"))
			return true;
		
		return false;
	}

	private static void getNxtTokenDetails(LinkedHashMap<Float, iStatement> sqlNodeMaps, float getKeyVal2, LinkedList<String> nxtTknName) 
	{
		Boolean flag = false, tknFlag = false;
		for(Entry<Float, iStatement> entryScript : sqlNodeMaps.entrySet())
		{
			float getKeyVal = entryScript.getKey();
			if (getKeyVal == getKeyVal2)
				flag = true;
			
			if (!flag)
				continue;
			
			ArrayList<Token> token  = entryScript.getValue().tokenList;
			
			for (int j = 0; j < token.size(); j++) 
			{
				Token tkn = token.get(j);
				String tknName = tkn.data;
				
				
				if (tknName.equalsIgnoreCase(")"))
					tknFlag = true;
				
				if (!tknFlag)
					continue;
				
				if (tkn.type.name().equalsIgnoreCase("CLOSEPAREN") && tknName.equalsIgnoreCase(")"))
					continue;
				
				if (nxtTknName.size() == 3)
					break;
				
				nxtTknName.add(tknName);
			}
		}
	}

	
}