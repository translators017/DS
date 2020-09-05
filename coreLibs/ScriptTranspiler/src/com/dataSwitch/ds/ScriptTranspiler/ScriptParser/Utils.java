
package com.dataSwitch.ds.ScriptTranspiler.ScriptParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.Messaging.SyncScopeHelper;

import com.dataSwitch.ds.ScriptTranspiler.Interpreter.DbInterpreter;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;
import com.dataSwitch.ds.sql.SQLParser.SQLUtils;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class Utils 
{

	public static HashMap<Integer,iStatement> readCodeAndClearComments(LinkedHashMap<Integer, iStatement> slineMaps, BufferedReader reader, int giEmptyLine,
			int giIndentOrder, int giStamentOrder, List<String> singleCommentsVal, List<String> multiCommentsVal, List<String> inlineCommentsVal) 
	{

		String szLine= "";
	    boolean multiLineComment = false;
	    String multiLineCommentsEnd = "*/";
        try {
        	int LineOrder = 1; 
        	String nextLineVal = "";
            String oddVal = "";
            Boolean oddFlag = false;
            int cnt = 0;
			while ((szLine = reader.readLine()) != null) 
			{
				int lineKeyOrder = LineOrder++;
			    szLine = szLine.trim();
			  /*  if(!nextLineVal.equalsIgnoreCase(""))
		    	{
			    	if(nextLineVal.trim().length() > 0)
			    	{
			    		szLine = nextLineVal + " " +szLine;
			    		nextLineVal = "";
			    	}
		    	}*/
			    
			    if ((szLine.length() > 0)) 
			    {
			    	boolean isMultiLineComment = checkMultiLineComment(multiCommentsVal, szLine);
			    	boolean isSingleLineComment = chkForSingleLineComments(singleCommentsVal, szLine);
			    	boolean inLineComment = chkForInLineComments(inlineCommentsVal, szLine);

			    	if(isMultiLineComment)
			    	{
			    		if(szLine.contains(multiLineCommentsEnd))
			    		{
			    			szLine = szLine.substring(szLine.indexOf(multiLineCommentsEnd)+2).trim();
			    	        isMultiLineComment = false;
			    		}
			    	
			    	}
			    	
			    	if(isSingleLineComment)
			    	{
			    		for (int i = 0; i < singleCommentsVal.size(); i++) 
			    		{
			    			if(szLine.contains(singleCommentsVal.get(i)))
			    			{
			    				if(szLine.startsWith("#") || szLine.startsWith("*"))
			    				{
			    					szLine = "";
			    				}
			    				else
			    				{
									  String singleLineComment = szLine.substring(0, szLine.indexOf(singleCommentsVal.get(i)));
									  
									  ArrayList<String> strLineLst = strCnstsinterpret(singleLineComment);
									  boolean isMultiLineStrCnsts = checkMultiLineStrCnsts(strLineLst);
									  
									  if(!isMultiLineStrCnsts)
										  szLine = szLine;
									  else
										  szLine = singleLineComment;
									
			    				}
			    			}
						}
			    	}
			    	
			    	if(inLineComment)
			    	{
			    		if(szLine.contains(multiCommentsVal.get(0)))
			    		{
			    			multiLineComment = true;
			    		}
			    		
			    	}
			    	
					if(isMultiLineComment)
					{
						multiLineComment = true;
					}
					
					if(multiLineComment)
					{
			    		if(szLine.contains(multiLineCommentsEnd))
			        	{
			    			//szLine = szLine.substring(szLine.indexOf(multiLineCommentsEnd)+2).trim();
			    			String beforeComment = "";
		        			if(szLine.contains(multiCommentsVal.get(0)))
		        				beforeComment = szLine.substring(0,szLine.indexOf(multiCommentsVal.get(0))).trim();
		        			
		        			String afterComment = szLine.substring(szLine.indexOf(multiLineCommentsEnd)+2,szLine.length()).trim();
		        			szLine = (beforeComment + " " +afterComment).trim();
			    			multiLineComment = false;
			        	}
			    		
			    		else if(szLine.contains(multiCommentsVal.get(0)))
			    		{
							szLine = szLine.substring(0,szLine.lastIndexOf(multiCommentsVal.get(0))).trim();
			    		}
			    		else
			    		{
			    			szLine = szLine.replace(szLine, "");
			    		}
			    	}
					
				
					if(szLine.length() > 0)
					{
						
						 if(szLine.contains("\"") || szLine.contains("'"))
						  {
							  ArrayList<String> strLineLst = strCnstsinterpret(szLine);
							  boolean isMultiLineStrCnsts = checkMultiLineStrCnsts(strLineLst);
							  if (!isMultiLineStrCnsts)
							  {
								  oddFlag = true;
								  cnt++;
							  }
							  else
							  {
								  //slineMaps.put(lineKeyOrder ,szLine);
								  slineMaps.put(lineKeyOrder, getiStatementObjt(StatType.UNKNOWNSTAT, lineKeyOrder,null, szLine));
							  }
							  if (cnt == 1)
							  {
								  oddVal += " " + szLine;
							  }
							  if (cnt == 2)
							  {
								  oddFlag = false;
								  oddVal += " " + szLine;
								 // slineMaps.put(lineKeyOrder ,oddVal);
								  slineMaps.put(lineKeyOrder, getiStatementObjt(StatType.UNKNOWNSTAT, lineKeyOrder, null, szLine));
								  oddVal = "";
								  cnt = 0;
							  }
							  
						  }
						  else
						  {
							  
							  if (oddFlag)
							  {
								  oddVal += " " + szLine;
							  }
							  else
							  {
								 //slineMaps.put(lineKeyOrder ,szLine);
								 slineMaps.put(lineKeyOrder, getiStatementObjt(StatType.UNKNOWNSTAT, lineKeyOrder, null, szLine));
							  } 
						  }
						 
						
					  giStamentOrder++;
					}
					else
					{
						giEmptyLine++;
					}
					
			    }
			    else {
			        giEmptyLine++;
			        
			       // slineMaps.put(lineKeyOrder ,szLine);
			        slineMaps.put(lineKeyOrder, getiStatementObjt(StatType.BLANKSTAT, lineKeyOrder,null, szLine));
			    }
			    
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return slineMaps;
	
	}
	
	
	private static boolean checkMultiLineStrCnsts(ArrayList<String> strLineLst)
	{
		int quoteCnt = 0;
		for(int i = 0 ; i < strLineLst.size() ; i++)
		{
			if(strLineLst.get(i).equalsIgnoreCase("StrConstsnts"))
			{
				quoteCnt++;
			}
		}
		if(quoteCnt % 2 == 0)
		{
			return true;
		}
		return false;
	}


	public enum stringTokenType{
	    
	    WHITESPACE("[ \t\f\r\n]+"),
	    NotStrConstsnts("[']+[\"]+[']"),
		StrConstsnts("[\"]|[']");
		
	    public final String pattern;
	    private stringTokenType(String pattern) {
	        this.pattern = pattern;
	    }
	    @Override
	    public String toString(){
	        return String.format(pattern);
	    }
	}
	
	
	public static ArrayList<String> strConstslex(String line){
	    ArrayList<String> tokens = new ArrayList<String>();
	    StringBuffer tpb = new StringBuffer();
	    for(stringTokenType tt : stringTokenType.values()){
	        tpb.append(String.format("|(?<%s>%s)", tt.name(), tt.pattern));
	    }
	    Pattern tPatterns = Pattern.compile(new String(tpb.substring(1)));
	    Matcher matcher = tPatterns.matcher(line);
	    while (matcher.find()) {
	        for (stringTokenType tk: stringTokenType.values()) {
	            if(matcher.group(stringTokenType.WHITESPACE.name()) != null)
	                continue;
	            else if (matcher.group(tk.name()) != null) {
	                tokens.add(tk.name());    
	                break;
	            }
	        }
	    }
	    return tokens;
	}
	
	public static ArrayList<String> strCnstsinterpret(String line){
	    String expr="";
	    ArrayList<String> t = new ArrayList<String>();
	    try{
	     
	            if(line.length()>0){
	                if(line.charAt(0)=='#'){
	                    t.addAll(strConstslex(line));
	                }
	                else{
	                    for(int i=0; i<line.length(); i++){
	                        char c = line.charAt(i);
	                        expr=expr+c;
	                       
	                    }
	                }
	            }
	        if(expr.length()>0){
	            t.addAll(strConstslex(expr));
	            expr = "";
	        }
	    }
	   
	    catch(Exception e){
	        e.printStackTrace();
	        return null;
	    }
	    return t;
	}
	
	private static boolean chkForInLineComments(List<String> comments, String szLine) 
    {
		for (int i = 0; i < comments.size(); i++) 
		{
			if(szLine.contains(comments.get(i)) && (!szLine.startsWith("/*")))
				return true;
		}
		
		return false;
	}

	private static boolean chkForSingleLineComments(List<String> comments, String szLine) 
    {
		for (int i = 0; i < comments.size(); i++) 
		{
			if(comments.get(i).equalsIgnoreCase("*"))
			{
				if(szLine.startsWith("*") && (!szLine.contains("*/")))
					return true;
			}
			else
			{
				if((szLine.contains(comments.get(i))) &&  (!(szLine.contains("/*--") || szLine.contains("--*/") || (szLine.contains("'--") && szLine.contains("--'")))))
						return true;
			}
		}

		return false;
	}

	private static boolean checkMultiLineComment(List<String> comments, String szLine) 
    {
		for (int i = 0; i < comments.size(); i++) 
		{
			if((szLine.startsWith(comments.get(i))))
					return true;
		}
		
	   
		return false;
	}

	public static void addCommentsForTeraData(List<String> singleCommentsVal, List<String> multiCommentsVal, List<String> inlineCommentsVal) 
	{
		singleCommentsVal.add("#");
		singleCommentsVal.add("//");
		singleCommentsVal.add("--");
		//singleCommentsVal.add("**");//singleCommentsVal.add("*");
		
		multiCommentsVal.add("/*");
		
		inlineCommentsVal.add("/*");		
	}
	
	public static void addCommentsForOracle(List<String> singleCommentsVal, List<String> multiCommentsVal, List<String> inlineCommentsVal) 
	{
		singleCommentsVal.add("--");
		
		multiCommentsVal.add("/*");
		
		inlineCommentsVal.add("/*");	
	}
	   
	  
	   public static final LinkedHashMap<Float, iStatement> mergeStatementsOfASqlQuery(LinkedHashMap<Integer, iStatement> statementNodeMaps, int currentDbId)
	   {
			 LinkedHashMap<Float, iStatement> SQLQuery = new LinkedHashMap<Float, iStatement>();
			 
			  boolean flag = false;
			  boolean mainFlag = false;
			  
			  float keyVal = 0 ;
			  int orginalLineNo = 0;
			  float SqlCount = 0.01f;
			  
			  LinkedHashMap<Token,Integer> nodeHashmap = new LinkedHashMap<Token,Integer>();
			  for(Entry<Integer, iStatement> entry : statementNodeMaps.entrySet())
			  {
				  ArrayList<Token> otherToken = new  ArrayList<Token>();
				  
				  iStatement istmt = entry.getValue();
				  
				  if(istmt.type != StatType.BLANKSTAT)
				  {
					  ArrayList<Token> statementToken = istmt.tokenList;
					  
					  String orginalLine = istmt.originalLine;
					   
					  for(int i = 0 ; i < statementToken.size();i++)
					  {
						boolean checkDbProedure = false;
						if(currentDbId == ToolTypeConstants.TERADATA ||
								currentDbId == ToolTypeConstants.ORACLE || currentDbId == ToolTypeConstants.VERTICA 
								|| currentDbId == ToolTypeConstants.NETEZZA)
						{
							if(statementToken.get(i).data.equalsIgnoreCase("Create") || statementToken.get(i).data.equalsIgnoreCase("Replace"))
							{
								boolean checkDbProc = getDbProcKeyWord(statementToken.get(i+1), statementToken, i);
								if(checkDbProc)
								{
									checkDbProedure = true;
								}
							}
						}
						
						boolean checkSQLKeyword = getSQLKeyWord(statementToken.get(i), currentDbId, checkDbProedure);
						boolean checkSQLEod = getSQLEodKeyWord(statementToken.get(i), currentDbId);
						 
					    if(checkSQLKeyword)
							mainFlag = true;
	
					    if(!mainFlag)
						{
					    	otherToken.add(statementToken.get(i));
						}
						else 
						{
							if(otherToken.size() > 0)
							{
						    	float tempkeyVal = entry.getKey();
								ArrayList<Token> cloned_list = new ArrayList<Token>(otherToken);
				      			StatType type = StatType.UNKNOWNSTAT; // For unknown Type is -1
				      			SQLQuery.put((float) tempkeyVal, getiStatementObjt(type, istmt.originalLineNo, cloned_list, orginalLine));
							}
							if((checkSQLKeyword) && flag == false)
							{
							    flag = true;
							    keyVal = entry.getKey();
							    orginalLineNo = istmt.originalLineNo;
							}
						
							nodeHashmap.put(statementToken.get(i), i);
							
			        		if(checkSQLEod)
				        	{
			        			ArrayList<Token> cloned_list = new ArrayList<Token>();
			        			
			        			String queryVal = "";
			        					
			        			for(Entry<Token ,Integer> sqlNode : nodeHashmap.entrySet())
			        			{
			        				cloned_list.add(sqlNode.getKey());
			        				queryVal += sqlNode.getKey().data;
			        			}
			        			
			        			queryVal = getStructedValue(queryVal);
			        			
			        			StatType type = StatType.SQLQUERY; // For SQLStatement Type is 0
			        			SQLQuery.put(keyVal+SqlCount, getiStatementObjt(type, orginalLineNo, cloned_list, queryVal));
			        			nodeHashmap.clear();
			        			flag = false;
				        	}	
							if(checkSQLEod)
							{
								mainFlag = false;
							}
					   	}
					 }
					  
					    if(otherToken.size() > 0)
						{
					    	float tempkeyVal = entry.getKey();
							ArrayList<Token> cloned_list = new ArrayList<Token>(otherToken);
							StatType type = StatType.UNKNOWNSTAT; // For unknown Type is -1
			      			SQLQuery.put((float) tempkeyVal, getiStatementObjt(type, istmt.originalLineNo, cloned_list, orginalLine));
						} 
				  }
				  else
					  SQLQuery.put((float) entry.getKey(), istmt);
				  
			  }
			  
			  
			  getUpdatedSQLQueryNode(SQLQuery);
			  	
			return SQLQuery;
			
		}	
	   
   private static boolean getDbProcKeyWord(Token token, ArrayList<Token> statementToken, int i) 
   {
		if(token.data.equalsIgnoreCase("Macro") || token.data.equalsIgnoreCase("procedure") ||
				token.data.equalsIgnoreCase("package") || token.data.equalsIgnoreCase("function") || token.data.equalsIgnoreCase("or"))
		{
			if(token.data.equalsIgnoreCase("or"))
			{
				if(statementToken.get(i+3).data.equalsIgnoreCase("Macro") || statementToken.get(i+3).data.equalsIgnoreCase("procedure") ||
						statementToken.get(i+3).data.equalsIgnoreCase("package") || statementToken.get(i+3).data.equalsIgnoreCase("function"))
				{
					return true;
				}
			}
			else
				return true;
		}
		return false;
	}


private static void getUpdatedSQLQueryNode(LinkedHashMap<Float, iStatement> sQLQuery)
   {
	   
	    for(Entry<Float, iStatement> iStmtNode : sQLQuery.entrySet())
	    {
	    	 boolean checkNotSql = false;
	    	if(iStmtNode.getValue().type == StatType.SQLQUERY)
	    	{
	    		for(int i = 0; i < iStmtNode.getValue().tokenList.size() ; i++)
	    		{
	    			if(iStmtNode.getValue().tokenList.get(i).data.equalsIgnoreCase("Insert"))
	    			{
	    				if(iStmtNode.getValue().tokenList.get(i+1).data.equalsIgnoreCase("For"))
	    				{
	    				    checkNotSql = true;
	    				}
	    				
	    			}
	    		}
	    	}
	    	
	    	if(checkNotSql)
	    	{
	    		iStatement iStmt = iStmtNode.getValue();
	    		iStmt.type =  StatType.UNKNOWNSTAT;
	    	}
	    }
	   
   }


	public static boolean getSQLKeyWord(Token token, int currentDbId, boolean checkDbProc) 
	   {
	  
	       if(token.data.equalsIgnoreCase("update") || token.data.equalsIgnoreCase("del") || token.data.equalsIgnoreCase("delete") ||
		    		token.data.equalsIgnoreCase("insert") || token.data.equalsIgnoreCase("select")
		    		|| token.data.equalsIgnoreCase("drop") || token.data.equalsIgnoreCase("truncate")
		    		|| token.data.equalsIgnoreCase("with") || token.data.equalsIgnoreCase("call"))
		    {
		    	return true;
		    }
		    if(currentDbId == ToolTypeConstants.REDSHIFT || currentDbId == ToolTypeConstants.TERADATA ||
		    		currentDbId == ToolTypeConstants.ORACLE || currentDbId == ToolTypeConstants.VERTICA || currentDbId == ToolTypeConstants.NETEZZA)
		    {
		    	 if(token.data.equalsIgnoreCase("merge") || token.data.equalsIgnoreCase("sel") ||
		    			 token.data.equalsIgnoreCase("collect"))
		    	 {
		    			 return true;
		    	 }
		    	 if((token.data.equalsIgnoreCase("create") || token.data.equalsIgnoreCase("Replace")) && checkDbProc == false)
		    	 {
		    		 return true;
		    	 }
		    	 
		    }
		   
		   return false;
	   }
	  
	   private static boolean getSQLEodKeyWord(Token token, int currentDbId) 
	   { 
		   if(token.data.equalsIgnoreCase(";") || token.type.name().equalsIgnoreCase("EOD"))
		   {
		   	   return true;
		   }

		   return false;
	   }

		public static LinkedHashMap<Integer, iStatement> getStatementObjeds(LinkedHashMap<Integer, iStatement> slineMaps, int currentDbId, int newDbId, String extension) 
		{
			LinkedHashMap<Integer, iStatement> stmtMap = new LinkedHashMap<Integer, iStatement>();
			
			int cnt = 0;
			for(Entry<Integer, iStatement> entry : slineMaps.entrySet())
			{

				iStatement istatVal = entry.getValue();
				
				if (cnt == 0)
				{
					istatVal.originalLine = "{" + istatVal.originalLine;
					istatVal.type = StatType.UNKNOWNSTAT;
					entry.setValue(istatVal);
					
				}
				if ((slineMaps.size() - 1) == cnt)
				{
					istatVal.originalLine = istatVal.originalLine + "}";
					istatVal.type = StatType.UNKNOWNSTAT;
					entry.setValue(istatVal);
				}
				
				cnt ++;
			}
			
			
			for(Entry<Integer, iStatement> entry : slineMaps.entrySet())
			{
				int lineKey = entry.getKey();
				iStatement iStatObjt = entry.getValue();
				
				if(iStatObjt.type == StatType.UNKNOWNSTAT)
				{
					ArrayList<Token> token = FunctionTypeConv.getTokens(iStatObjt.originalLine);
					
					String originalLine = iStatObjt.originalLine;
					
					boolean countEolInStatements = getCountForEol(token);
					if(countEolInStatements)
					{
						ArrayList<Integer> multiStatements = new ArrayList<Integer>();
						
						multiStatements.add(0); 
						
						for (int i = 0 ; i < token.size(); i++)
						{
							if(token.get(i).data.equalsIgnoreCase(";") && token.get(i).type.name().equalsIgnoreCase("EOL"))
							{
								multiStatements.add(i);
								multiStatements.add(i+1);
							}
							
						}
						
					    multiStatements.add(token.size()-1);
						
						ArrayList<ArrayList<Integer>> inPosList = new ArrayList<ArrayList<Integer>>();
						if(multiStatements.size() > 2)
						{
							for(int i = 0 ; i < multiStatements.size() ; i++)
							{
								ArrayList<Integer> intPos = new ArrayList<Integer>();
								intPos.add(multiStatements.get(i));
								intPos.add(multiStatements.get(i+1));
								inPosList.add(intPos);
								i++;
							}
						}
						
						
						if(inPosList.size() > 0)
						{
							int Count = 0 ;
							for (int i = 0 ; i < inPosList.size() ; i++)
							{
								ArrayList<Integer> intPos = inPosList.get(i);
								
								ArrayList<Token> tokens  = getIndexPositionForToken(intPos.get(0), intPos.get(1), token);
								if(tokens != null)
								{
									int keyVal = (lineKey * 100) + Count++;
									stmtMap.put(keyVal, getiStatementObjt(StatType.UNKNOWNSTAT, lineKey, tokens, originalLine));
								}
							}
							
						}
					}
					else
					{
						int keyType = lineKey * 100;
						stmtMap.put(keyType, getiStatementObjt(StatType.UNKNOWNSTAT, lineKey, token, originalLine));
					}
				}
				else
				{
					int keyType = lineKey * 100;
					stmtMap.put(keyType, iStatObjt);
				}

			}	
			if ((currentDbId ==  ToolTypeConstants.TERADATA || currentDbId ==  ToolTypeConstants.ORACLE))
			{
				LinkedHashMap<Integer, iStatement> updatedStatementNodeMaps = Utils.chkQryTerminator (stmtMap, currentDbId);
				return updatedStatementNodeMaps;
				
			}
			else
				return stmtMap;
		}
		
		
		public static iStatement getiStatementObjt(StatType type, int lineKey, ArrayList<Token> token, String originalLine) 
		{
			iStatement stmtObjt = new iStatement();
			stmtObjt.type = type;
			stmtObjt.originalLineNo = lineKey;
			if(token != null)
				stmtObjt.tokenList.addAll(token);
			
			stmtObjt.originalLine = originalLine;
			
			return stmtObjt;
		}
		
		public static iStatement getiStmtObjt(StatType type, int lineKey, String line)
		{
			iStatement stmtObjt = new iStatement();
			stmtObjt.type = type;
			
			stmtObjt.originalLineNo = lineKey;
			
			stmtObjt.originalLine = line;
			
			return stmtObjt;
		}
		

		private static boolean getCountForEol(ArrayList<Token> token)
		{
			int count = 0;
			for (int i = 0; i < token.size() ; i++)
			{
				if(token.get(i).type.name().equalsIgnoreCase("EOL") && token.get(i).data.equalsIgnoreCase(";"))
				{
					count++;
				}
			}
			
			if(count > 0)
			{
				return true;
			}
			return false;
		}

		private static ArrayList<Token> getIndexPositionForToken(int key, int value, ArrayList<Token> token) 
		{
			boolean mainFlag = false;
			boolean flag = false;
			ArrayList<Token> tknNodes = new ArrayList<Token>();
			
			for (int i = 0; i < token.size(); i++) 
			{
			   if(key == i)
				   mainFlag  = true;
			  
			    if(mainFlag)
				{
					if(mainFlag && (!flag))
						flag = true;
					
					if(flag)
					{
						tknNodes.add(token.get(i));
		        		if(value == i)   
			        	{
		        			flag = false;
		        			return tknNodes;
			        	}	
					}
					else
						mainFlag = false;
				}
			}
			return null;
			
		}

		public static LinkedHashMap<Integer, iStatement> chkQryTerminator(LinkedHashMap<Integer, iStatement> statementNodeMaps, int currentDbId) 
		{
			LinkedHashMap<Integer, iStatement> nodeHashmap = new LinkedHashMap<Integer, iStatement>();
			boolean flag = false, openChk = false;
			LinkedList<Token> tmpQry = new LinkedList<>();
			for(Entry<Integer, iStatement> entry : statementNodeMaps.entrySet())
			{
			  iStatement istmt = entry.getValue();
			  
			  if(istmt.type != StatType.BLANKSTAT)
			  {
				  iStatement newIStmt = new iStatement();
				  
				  for (int i = 0; i < istmt.tokenList.size(); i++) 
				  {
					  Token tkn = istmt.tokenList.get(i);
					  if (openChk && (tkn.data.equalsIgnoreCase("sqlexception") || tkn.data.equalsIgnoreCase("while")))
						  openChk = false;
					  if (openChk)
					  {
						  if ((tkn.data.equalsIgnoreCase("do") || tkn.data.equalsIgnoreCase("Loop")) && tkn.type.name().equalsIgnoreCase("var"))
						  {
							  flag = false;
							  openChk = false;
                              
							 if(checkParanForLoop(tmpQry))
							       chkQryEOL(tmpQry, currentDbId, newIStmt, nodeHashmap);
	
							  tmpQry = new LinkedList<>();
						  }
						  else
							  tmpQry.add(tkn);
					  }
					  if (tkn.data.equalsIgnoreCase("for") && tkn.type.name().equalsIgnoreCase("var"))
						  openChk = true;
					
					  newIStmt.tokenList.add(tkn);
				   }
				  newIStmt.originalLineNo = istmt.originalLineNo;
				  newIStmt.type = istmt.type; 
				  newIStmt.originalLine = istmt.originalLine;
				  
				  nodeHashmap.put(entry.getKey(), newIStmt);
			  }
			  else
				  nodeHashmap.put(entry.getKey(), entry.getValue());
			  
			}
			return nodeHashmap;
		}

		private static boolean checkParanForLoop(LinkedList<Token> tmpQry)
		{
	        for(int i = 0 ; i < tmpQry.size() ; i++)
	        {
	        	if(tmpQry.get(i).type.name().equalsIgnoreCase("openparen"))
	        		return true;
	        	
	        }
	        if(tmpQry.get(tmpQry.size()-1).type.name().equalsIgnoreCase("EOL"))
	        	return true;
	        
			return false;
		}


		private static Token appendNewTkn()
		{
			byte val = 0;
			TokenType t = TokenType.EOL;
			Token curleyToken = new Token(t, ";", val);
			
			return curleyToken;
		}

		private static boolean chkQryEOL(LinkedList<Token> tmpQry, int currentDbId, iStatement newIStmt, LinkedHashMap<Integer, iStatement> nodeHashmap) 
		{
			if (tmpQry.size() > 0)	
			{
				if(tmpQry.get(tmpQry.size()-1).data.equalsIgnoreCase(")") || tmpQry.get(tmpQry.size()-1).type.name().equalsIgnoreCase("closeparen"))
					if (tmpQry.get(tmpQry.size()-2).data.equalsIgnoreCase(";"))
						return true;
					else
					{
						if (newIStmt.tokenList.size() > 0)
						{
							Token tkn = newIStmt.tokenList.get(newIStmt.tokenList.size() - 1);
							newIStmt.tokenList.remove(newIStmt.tokenList.size() - 1);
							Token tmpTkn = appendNewTkn();
							newIStmt.tokenList.add(tmpTkn);
							newIStmt.tokenList.add(tkn);
						}
						else
						{
							iStatement istmt = null ;int val = 0;
							StatType type = null;
						    int originalLineNo = 0;
						    
							for(Entry<Integer, iStatement> entry : nodeHashmap.entrySet())
							{
								istmt = entry.getValue();
								val = entry.getKey();
								originalLineNo = istmt.originalLineNo;
								type = istmt.type;
							}
							if (istmt.tokenList.size() > 0)
							{
								Token tkn = istmt.tokenList.get(istmt.tokenList.size() - 1);
								istmt.tokenList.remove(istmt.tokenList.size() - 1);
								Token tmpTkn = appendNewTkn();
								istmt.tokenList.add(tmpTkn);
								istmt.tokenList.add(tkn);
							}
							istmt.originalLineNo = originalLineNo;
							istmt.type = type;
							nodeHashmap.put(val, istmt);
//							System.out.println(istmt.tokenList);
						}
						return false;
					}
				
				else if(tmpQry.get(tmpQry.size()-1).data.equalsIgnoreCase(";") || tmpQry.get(tmpQry.size()-1).type.name().equalsIgnoreCase("eol"))
						return true;
					else
					{
						if (newIStmt.tokenList.size() > 0)
						{
							Token tmpTkn = appendNewTkn();
							newIStmt.tokenList.add(tmpTkn);
						}
						else
						{
							iStatement istmt = null ;
							int val = 0;
							StatType type = null;
						    int originalLineNo = 0;
						    
							for(Entry<Integer, iStatement> entry : nodeHashmap.entrySet())
							{
								istmt = entry.getValue();
								val = entry.getKey();
								originalLineNo = istmt.originalLineNo;
								type = istmt.type;
							}
							if (istmt.tokenList.size() > 0)
							{
								Token tmpTkn = appendNewTkn();
								istmt.tokenList.add(tmpTkn);
							}
							istmt.originalLineNo = originalLineNo;
							istmt.type = type;
							nodeHashmap.put(val, istmt);
						}
					}
			}

			
			return false;
		}
		
		private static String getStructedValue(String sqlExpr)
		{
			sqlExpr = sqlExpr.replace("( ", "(").replace(" (", "(").replace(" )", ")")
					.replace("[ ", "[").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
					.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").trim();
			
			return sqlExpr;		
		}
		
		
}