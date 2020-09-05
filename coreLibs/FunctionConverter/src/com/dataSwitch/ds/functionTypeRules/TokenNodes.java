
package com.dataSwitch.ds.functionTypeRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import org.omg.Messaging.SyncScopeHelper;

import java.util.Map.Entry;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.functionparser.Lexer;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;;

public class TokenNodes 
{

	 public static HashMap<String,String> createNodeForNestedFuns(ArrayList<Token> alltokens) 
	 {
	
			char[] tokencode = new char[alltokens.size()]; 
			
			for (int i = 0; i < alltokens.size(); i++) 
			{
				Token tokens = alltokens.get(i);
				
				createTokenTypeChars(tokens, tokencode,i);
				
			}
			
			HashMap<Token,String> indexPosition = getHashMapIndexPosition(alltokens);
			
			List<List<Token>> posOfNestedExp  = getArrayListNestedExpPos(alltokens);
			LinkedHashMap<String,String> strValMaps = new LinkedHashMap<String,String>();
			if(posOfNestedExp.size() > 0)
			{
				int count = 0;
				for(int i = 0 ; i < posOfNestedExp.size(); i++)
				{
					if(posOfNestedExp.get(i).size() > 1)
					{
						//String keyValue = posOfNestedExp.get(i).get(0).data+"_"+count++;
						String keyValue = "nestedVar"+"_"+count++;
						HashMap<String,List<String>> nestedStr = getNodeForNestedFuncs(keyValue,alltokens,posOfNestedExp.get(i),indexPosition,tokencode);
						List<String> strVals = nestedStr.get(keyValue);
						String nestedVal = "";
						for(int i1 = 0 ; i1 < strVals.size(); i1++)
						{
							String val = strVals.get(i1);
							nestedVal += val.replace("( ", "(").replace(") ", ")").replace("[ ", "[").replace("] ", "]");;
						}
						nestedVal = nestedVal.trim();
						strValMaps.put(keyValue, nestedVal);
					}	
				}
			}
			else
			{
				String keyValue = "nestedVar"+"_"+0;
				String value = "";
				for(int i = 0 ; i < alltokens.size(); i++)
				{
				  Token token = alltokens.get(i);
				  value += token.data+" ";
				}
				 strValMaps.put(keyValue, value);
			}
			return strValMaps;
	 }
 
	 
	
	 public static boolean CheckCondGrpNodes(ArrayList<Token> alltokens) 
	 {
	        for(int i=alltokens.size()-1 ;i>=0 ;i--)
	        {
		          Token dtToken = alltokens.get(i);
		          if(dtToken.data.equalsIgnoreCase("if_:"))
		          {
		        	  return true;
		          }  
	        }
			return false;
	 }

	    public static String getUpdatedTokensForIfElseCond(String strExp, int currentToolId, boolean isCurrentToolIDIsDb) 
		{
			String opRule = "";
			ArrayList<Token> alltokens1 = Lexer.interpret(strExp);
			for (int i = 0; i < alltokens1.size(); i++) 
			{
				Token tokens = alltokens1.get(i);
				
				if(tokens.type.name().equalsIgnoreCase("VAR") || tokens.type.name().equalsIgnoreCase("LOGICALOP"))
				{
					if(currentToolId != ToolTypeConstants.DATASTAGE)
					{
						if(tokens.data.equalsIgnoreCase(":"))
						{
							tokens.data = "dsElse";
						} 	
					}
					if (isCurrentToolIDIsDb) {
						if (tokens.data.equalsIgnoreCase("Case"))
							tokens.data = "";

						else if (tokens.data.equalsIgnoreCase("When"))
							tokens.data = "If";

						else if (tokens.data.equalsIgnoreCase("end"))
							tokens.data = "";
					}

					if(tokens.data.equalsIgnoreCase("Then"))
					{
						 tokens.data = "?";
					}
					else if(tokens.data.equalsIgnoreCase("Else"))
					{
						tokens.data = "dsElse";
					}
				}
			}
			
			for (int i = 0; i < alltokens1.size(); i++) 
			{
				Token token = alltokens1.get(i);
			
		    	opRule += " "+token.data;
			}
			
			
			return opRule;
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

	
	public static String getConvertedFunc(String curntFun,int currentToolId, int newToolId) 
	{
		  FnConverter dtc = new FnConverter();
		  String tgtFnType= dtc.getTargetFnType(currentToolId,newToolId, curntFun);
		  String fnData = "";
		  
		   if(tgtFnType != null && !tgtFnType.equalsIgnoreCase("NA") && !tgtFnType.trim().equalsIgnoreCase("") && !tgtFnType.trim().equalsIgnoreCase("null"))
			   fnData = tgtFnType;
		   
		   else
		   {
			   if(curntFun.startsWith("dsError_") || curntFun.startsWith("ds"))
				   fnData = curntFun;
			   
			   else
			       fnData = "dsError_"+curntFun;
		   }
		
		   
		   
		return fnData; 
	}

	public static List<List<Token>> getArrayListNestedExpPos(ArrayList<Token> alltokens) 
	{
		List<List<Token>> NodeLists = new ArrayList<List<Token>>();
		Stack<Token> stkNode = new Stack<Token>();
		for(Token t1 : alltokens)
		{
			if(t1.type.name().equalsIgnoreCase("CLOSEPAREN"))
			{
				List<Token> nodeObjt = new ArrayList<Token>();
				
				while(true)
				{
					if(!stkNode.isEmpty())
					{
						Token checkOpenParan = stkNode.pop();  
						if(checkOpenParan.type.name().equalsIgnoreCase("OPENPAREN"))
						{
							if(!stkNode.isEmpty())
							{
								Token checkPop = stkNode.pop();
								if(checkPop.type.name().equalsIgnoreCase("OPENPAREN"))
								{
									stkNode.push(checkPop);
									nodeObjt.add(checkOpenParan);
								}
								
								else
									nodeObjt.add(checkPop);
							}
							else
								nodeObjt.add(checkOpenParan);
							 
							break; 
						}
						
					}
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

	private static HashMap<Token, String> getHashMapIndexPosition(
			ArrayList<Token> alltokens) 
	{
		HashMap<Token,String> hashMap = new HashMap<Token,String>();
		
		for(int i = 0 ; i < alltokens.size();i++)
		{
			Token token = alltokens.get(i);
			hashMap.put(token,i+"");
		}
		return hashMap;
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

	public static HashMap<String, String> getUpdatedNodes(HashMap<String, String> str) {
		LinkedHashMap<String, String> tempHashMap = new LinkedHashMap<String, String>();
		for (Entry<String, String> entry : str.entrySet()) {
			for (Entry<String, String> entry1 : str.entrySet()) {
				if (entry1.getValue().contains(entry.getValue())
						&& (!entry1.getValue().equalsIgnoreCase(entry.getValue()))) {
					entry1.setValue(entry1.getValue().replace(entry.getValue(), " " + entry.getKey() + " "));
				}
			}
			tempHashMap.put(entry.getKey(), entry.getValue());
		}
		return tempHashMap;
	}


	public static HashMap<String, String> getUpdatedSyntaxForTools(HashMap<String, String> curntFuns, int currentToolId, int newToolId) 
	{
		for (Entry<String, String> entry : curntFuns.entrySet())
		{
			   String value = entry.getValue().trim();
			   value = getUpdatedValueForTools(value,newToolId,currentToolId,entry,curntFuns);
			
			   ArrayList<Token> alltokens1 = Lexer.interpret(value);
			   for(int i = 0 ; i < alltokens1.size(); i++)
			   {
				   if(alltokens1.get(i).data.contains("nestedVar_") && alltokens1.get(i).type.name().equalsIgnoreCase("VAR"))
				   {
					   String syntaxForm  = curntFuns.get(alltokens1.get(i).data);
					   alltokens1.get(i).data = syntaxForm; 
				   } 
			   }
			   String val = FunctionTypeConv.getConvertedString(alltokens1).trim();
			  
			   val = getUpdatedStructValForDbs(val, newToolId);
			   
			   entry.setValue(val);
		}
		
		return curntFuns;
	   
	}

	private static String getUpdatedStructValForDbs(String val, int newToolId) 
	{
		if(newToolId == ToolTypeConstants.BIGQUERY || newToolId == ToolTypeConstants.SNOWFLAKE)
			val = val.replace(": ", ":");  
		
		return val;
	}
	private static String getLitFunction(String value) {
		if (!value.contains("lit(")) {
			StringBuffer sb = new StringBuffer();
			int quoteCount = 0;
			for (int i = 0; i < value.length(); i++) {
				char ch = value.charAt(i);
				if (ch == '\'') {
					quoteCount = quoteCount + 1;
					if (quoteCount % 2 == 0) {
						sb.append("\")");
					} else {
						sb.append("lit(\"");
					}
				} else {
					sb.append(ch);
				}
			}
			return sb.toString();
		} else
			return value;
	}

	private static String getUpdatedValueForTools(String value, int newToolId, int currentToolId, Entry<String, String> entry, HashMap<String, String> curntFuns) 
	{
		
		if(newToolId == ToolTypeConstants.PANDASSQL)
		{
			if(value.startsWith("cast_string"))
			{
				if((value.contains("dsSysVarSESSTARTIME")||value.contains("yyyy-MM-dd HH:MM:SS")||(value.contains("y")))&& (value.contains(","))) 
				{
					String col = value.substring(value.indexOf("(")+1,value.indexOf(")"));
					String paramlist[]=col.split(",");
					
					String updVal = "";
					if(paramlist[0].contains("dsSysVarSESSTARTIME")||paramlist[0].contains("datetime")||paramlist[0].contains("dsSysVarSESSSTARTTIME")) {
						updVal = "datetime.now().strftime("+paramlist[1].replace("YYYY", "%Y").replace("MM", "%m").replace("DD", "%d").replace("HH12", "%I").replace("HH", "%H").replace("MI", "%M").replace("SS", "%S")+")";
					}
						// updVal = col+".astype(str)";
					else
						updVal = paramlist[0]+"dt.strftime("+paramlist[1].replace("YYYY", "%Y").replace("MM", "%m").replace("DD", "%d").replace("HH12", "%I").replace("HH", "%H").replace("MI", "%M").replace("SS", "%S")+")";
					   	
				    value = updVal; 
					
				
				}
				 else {
					String col = value.substring(value.indexOf("("),value.indexOf(")")+1);
					String updVal = "";
				    updVal = col+".astype(str)";
				    
				    value = updVal;
				 }
			}
			
			
		}
		if(newToolId == ToolTypeConstants.PYTHON)
		{

			if(value.contains("dsDate()"))
			{
				value=value.replace("dsDate()", "dt.datetime.now()");
			}
			if(value.startsWith("AddToDate"))
			{
				String params = value.substring(value.indexOf("(")+1,value.indexOf(")"));
				String paramList[] =  params.split(",");
				
				String updVal = "";
				
				if(paramList[1].contains("DD"))
				updVal = paramList[0]+"+timedelta(days="+paramList[2]+")";
				
				value = updVal;
			}
			if(!value.startsWith("np.where")&&(value.contains("&")|value.contains("&")))
			{
				value=value.replace("&",")&(").replace("|",")|(").replace(" = ", " == ");
			}
			 if(value.startsWith("np.where"))
			{
				String updVal = "";
				boolean checkIfThenElse = checkIfThenElse(value);
				if (checkIfThenElse == true) {
					String ifCond = value.substring(0, value.lastIndexOf(","));
					if(ifCond.contains("<>")||ifCond.contains(" = "))
						ifCond=ifCond.replaceAll("<>","!=").replace(" = ", " == ");
					String tem = ifCond.substring(0,ifCond.lastIndexOf(","));
						
				   if(ifCond.contains("&")|ifCond.contains("|"))
						 ifCond=ifCond.replace(tem,tem.replace("&",")&(").replace("|",")|(").replace("np.where","np.where(")+")");
					  
					String elseCond = value.substring(value.lastIndexOf(",") + 1);
					value = ifCond +","+ elseCond.replace("NULL", "None");
				} else {
					String ifCond = value.substring(0, value.lastIndexOf(","));
					String returnTrue = value.substring(value.lastIndexOf(","),value.lastIndexOf(")"));
					String returnfalse =returnTrue+",None";
					
					
					String temp = ifCond;
					if (ifCond.contains("&") || ifCond.contains("|"))
						temp = temp.replace("&", ")&(").replace("|", ")|(").replace("np.where", "np.where(") + ")";
					if(ifCond.contains("<>")||ifCond.contains(" = "))
						temp=temp.replaceAll("<>","!=").replace(" = ", " == ");
					value = value.replace(ifCond, temp).replace(returnTrue, returnfalse);
					System.out.println(value);
				}
				System.out.println(updVal);
			}
			
			
			if(value.startsWith("lstrip")||value.startsWith("rstrip")||value.startsWith("upper")||value.startsWith("lower")||value.startsWith("len"))
			{
				 String col = value.substring(value.indexOf("("));
				 String rule = value.substring(0,value.indexOf("("));
				 String updVal="";
				 if(col.contains("$$"))
					 updVal=col+"."+rule+"()"; 
				 else
				  updVal=col+".str."+rule+"()";
				   
				 value = updVal;
			}
			
			
			if(value.startsWith("sum"))
			{
				 String col = value.substring(value.indexOf("("));
				 String rule = value.substring(0,value.indexOf("("));
				
				 String updVal=col+"."+rule+"()";
				   
				 value = updVal;
				
			}
			
			if(value.startsWith("round"))
			{
				String col = value.substring(value.indexOf("("), value.indexOf(","))+")";
				String params = value.substring(value.indexOf(",")+1);
				
				String updVal = col+".round(decimals = "+params;
				
				value = updVal;
			}
				
			if(value.startsWith("isnull")||value.startsWith("notnull"))
			{
				 String col = value.substring(value.indexOf("("));
				 String rule = value.substring(0,value.indexOf("("));
				
				 String updVal=col+"."+rule+"()";
				   
				 value = updVal;	
			}
			
			if(value.startsWith("replace"))
			{	
				String params = value.substring(value.indexOf("(")+1,value.indexOf(")"));
				String paramList[] =  params.split(",");
				
				String updVal = "";
				if(paramList[0].trim().equalsIgnoreCase("1")||paramList[0].equalsIgnoreCase("0"))
				{
					updVal = "("+paramList[1]+").str.replace("+paramList[2]+","+paramList[3]+")";
				}
				else
				{
					updVal = "("+paramList[0]+").str.replace("+paramList[1]+","+paramList[2]+")";
				}
				
				value = updVal;
			}
			
			if(value.startsWith("slice"))
			{
				String col = value.substring(value.indexOf("("), value.indexOf(","))+")";
				String params = value.substring(value.indexOf(",")+1);
				
				String updVal = col+".str.slice("+params;
				
				value = updVal;
			}
			if(value.startsWith("dsSTRtoDEC"))
			{
				String col = value.substring(value.indexOf("("),value.indexOf(")")+1);
				String updVal = "";
			    updVal = col+".astype(float)";
			    value = updVal;
			}
			
			if(value.startsWith("char"))
			{
				String val = value.substring(value.indexOf("(")+1,value.indexOf(")")).trim();
				String fun = value.substring(0,value.indexOf("("));
				String updVal =fun+"("+val+")";
				value = updVal;	
			}
			
			if(value.startsWith("cast"))
			{
				if(value.startsWith("cast_string"))
				{
					if((value.contains("dsSysVarSESSTARTIME")||value.contains("yyyy-MM-dd HH:MM:SS")||(value.contains("y")))&& (value.contains(","))) 
					{
						String col = value.substring(value.indexOf("(")+1,value.indexOf(")"));
						String paramlist[]=col.split(",");
						
						String updVal = "";
						if(paramlist[0].contains("dsSysVarSESSTARTIME")||paramlist[0].contains("datetime")) {
							updVal = "datetime.now().strftime("+paramlist[1].replace("YYYY", "%Y").replace("MM", "%m").replace("DD", "%d").replace("HH12", "%I").replace("HH", "%H").replace("MI", "%M").replace("SS", "%S")+")";
						}
							// updVal = col+".astype(str)";
						else
							updVal = paramlist[0]+"dt.strftime("+paramlist[1].replace("YYYY", "%Y").replace("MM", "%m").replace("DD", "%d").replace("HH12", "%I").replace("HH", "%H").replace("MI", "%M").replace("SS", "%S")+")";
						   	
					    value = updVal; 
						
					
					}
					 else {
						String col = value.substring(value.indexOf("("),value.indexOf(")")+1);
						String updVal = "";
					    updVal = col+".astype(str)";
					    
					    value = updVal;
					 }
				}
				
				if(value.startsWith("cast_bigint"))
				{
					String col = value.substring(value.indexOf("("),value.indexOf(")")+1);
					String updVal = "";
				    updVal = col+".astype(int64)";
				    
				    value = updVal;
				}
				
				if(value.startsWith("cast_StrToInt"))
				{
					String col = value.substring(value.indexOf("("),value.indexOf(")")+1);
					String updVal = "";
				    updVal = col+".astype(int)";
				    
				    value = updVal;
					
				}
				
				if(value.startsWith("cast_StrToDec"))
				{
					String col = value.substring(value.indexOf("("),value.indexOf(")")+1);
					String updVal = "";
				    updVal = col+".astype(float)";
				    value = updVal;
				}
			}
			if(value.startsWith("find"))
			{
				String col1 = value.substring(value.indexOf("(")+1, value.indexOf(","));
				String col2 = value.substring(value.indexOf(",")+1,value.indexOf(")"));
				String updVal = "";
			    updVal = col1+").str.find("+col2+"";
			    value = updVal;
			}
			if(value.startsWith("concat"))
			{
				
				String params = value.substring(value.indexOf("(")+1,value.indexOf(")"));
				String paramList[] =  params.split(",");
				String updVal = "";
				for(int i = 0;i<paramList.length;i++)
				{
					if(i<paramList.length-1)
						updVal+=paramList[i]+" + ";
					
					else
						updVal+=paramList[i];
			
				}
				
				value = updVal;
			}
			if (value.contains("dsPipe"))
				value = value.replace("dsPipe", " + ");
			else
				value = value;
		}
		
		if(newToolId == ToolTypeConstants.SCALA_SPARK || newToolId == ToolTypeConstants.PYSPARK)
		{
			value = value.replace("YYYY", "yyyy").replace("DD", "dd");
			//if (value.contains("'")) {
			//	value = getLitFunction(value);
			//}
			if (value.contains("dsPipe")) {
				if (!value.contains(",") && value.trim().startsWith("("))
					value = "concat" + value.replaceAll("dsPipe", ",");
				else if (!value.contains(",") && !value.trim().startsWith("("))
					value = "concat(" + value.replaceAll("dsPipe", ",") + ")";
				else {
					StringBuffer sb = new StringBuffer();
					String[] valueArr = value.split(",");
					for (String i : valueArr) {
						if (i.contains("dsPipe") && i.trim().startsWith("("))
							i = "concat" + i.replaceAll("dsPipe", ",");
						else if (i.contains("dsPipe") && !i.trim().startsWith("("))
							i = "concat(" + i.replaceAll("dsPipe", ",") + ")";
						sb.append(i);
						sb.append(",");
					}
					value = sb.toString().substring(0, sb.toString().length() - 1);
				}
			} else
				value = value;

			if (value.startsWith("leftsubstring")) {
				value = value.replace("leftsubstring", "substring");

				String params = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
				String paramList[] = params.split(",");
				value = value.replace(params, paramList[0] + ",0," + paramList[1]);
			}
			if (value.startsWith("strposition")) {
				value = value.replace("StrPosition", "STR_POSITION");

				String params = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
				String paramList[] = params.split("IN");
				value = value.replace(params, paramList[0] + ",0," + paramList[0]);
			}

			if (value.startsWith("when")) {
				boolean checkIfThenElse = checkIfThenElse(value);
				if (newToolId == ToolTypeConstants.SCALA_SPARK) {
					if (checkIfThenElse == true) {
						String ifCond = value.substring(0, value.lastIndexOf(","));
						if (ifCond.contains("<>"))
							ifCond = ifCond.replaceAll("<>", "!=");
						String tem = ifCond.substring(0, ifCond.lastIndexOf(","));

						if (ifCond.contains("&&") || ifCond.contains("||"))
							ifCond = ifCond.replace(tem,
									tem.replace("&&", ") && (").replace("|", ") || (").replace("when", "when(") + ")");

						String elseCond = value.substring(value.lastIndexOf(",") + 1);
						value = ifCond + ")" + ".otherwise(" + elseCond.replace("NULL", "None");
					} else {
						String ifCond = value.substring(0, value.lastIndexOf(","));
						String temp = ifCond;
						if (ifCond.contains("&&") || ifCond.contains("||"))
							temp = temp.replace("&&", ") && (").replace("||", ") || (").replace("when", "when(") + ")";
						value = value.replace(ifCond, temp);
					}
				}
				else {
					if (checkIfThenElse == true) {
						String ifCond = value.substring(0, value.lastIndexOf(","));
						if (ifCond.contains("<>"))
							ifCond = ifCond.replaceAll("<>", "!=");
						String tem = ifCond.substring(0, ifCond.lastIndexOf(","));

						if (ifCond.contains("&") || ifCond.contains("|"))
							ifCond = ifCond.replace(tem,
									tem.replace("&", ") & (").replace("|", ") | (").replace("when", "when(") + ")");

						String elseCond = value.substring(value.lastIndexOf(",") + 1);
						value = ifCond + ")" + ".otherwise(" + elseCond.replace("NULL", "None");
					} else {
						String ifCond = value.substring(0, value.lastIndexOf(","));
						String temp = ifCond;
						if (ifCond.contains("&") || ifCond.contains("|"))
							temp = temp.replace("&", ") & (").replace("|", ") | (").replace("when", "when(") + ")";
						value = value.replace(ifCond, temp);
					}
				}
			}

			else if (value.startsWith("regexp_replace"))
			{	
				String params = value.substring(value.indexOf("(")+1,value.indexOf(")"));
				String paramList[] =  params.split(",");
				
				String updVal = "";
				if(paramList[0].trim().equalsIgnoreCase("1")||paramList[0].equalsIgnoreCase("0"))
				{
					updVal = "regexp_replace("+paramList[1]+","+paramList[2]+","+paramList[3]+")";
				}
				else
				{
					updVal = "regexp_replace("+paramList[0]+","+paramList[1]+","+paramList[2]+")";
				}
				
				value = updVal;
			}
		   else if(value.startsWith("NullToValue"))
		   {
			   String colName = value.substring(value.indexOf("("),value.indexOf(","));
			   value = value.replace(colName,"("+colName+").isNull())");
			   value = value.replace("NullToValue","when(");
		   }
		   else if(value.startsWith("NullToZero"))
		   {
			   String colName = value.substring(value.indexOf("("));
			   value = value.replace(colName,colName+".isNull())");
			   value = value.replace("NullToZero","when(");
		   }
		   
		   else if(value.startsWith("isNull")) 
		   {
			   String colName = value.substring(value.indexOf("("));
			   value = colName+".isNull()"	;		   
		   }
		   else if(value.startsWith("IsBlank"))
		   {
			   String colName = value.substring(value.indexOf("("));
			   value = value.replace(colName,colName+".isNull()||"+colName+"= \"\""+ ",\"TRUE\")");
			   value = value.replace("IsBlank","when(");
		   }
		  
		   else if(value.startsWith("addSecToTs"))
		   {
			   String val = value.substring(value.indexOf("("));
			   String param = val.substring(1,val.indexOf(","));
			   String colName =value.substring(value.indexOf(",")+1,value.length()-1);
			   //String updVal=colName+"f.expr('INTERVAL"+ param +"SECONDS')";
		   String updVal="(unix_timestamp("+colName+")+"+  param +").cast('timestamp')";
			   
		   value = updVal;
		   }
		   
		   else if(value.startsWith("field"))
		   {
			   String val = value.substring(value.indexOf("("));
			   String colName = val.substring(1,val.indexOf(","));
			   String params =value.substring(value.indexOf(",")+1,value.length()-1);
			   String paramlist[] = params.split(",");
			   
			   String updVal="split("+colName+","+paramlist[0]+")["+paramlist[1]+"-1]";
			 
			   value = updVal;
		   }
		   
		   else if(value.startsWith("translate"))
		   {
			   String val = value.substring(value.indexOf("("));
			   
			   String params =val.substring(1,val.length()-1);
			   String paramlist[] = params.split(",");
			   String updVal = "translate("+paramlist[2]+","+paramlist[0]+","+paramlist[1]+")";
			   
			   value = updVal;
		   }
		   
		   
		   
		   else if(value.startsWith("strdecode"))
		   {
			   String val = value.substring(value.indexOf("("));
			   String colName = val.substring(0,val.indexOf(","))+")";
			   String params =value.substring(value.indexOf(",")+1,value.length()-1);
			   String paramlist[] = params.split(",");
			   
			   String updVal="";
			   
			   List<Token> Tokens = FunctionTypeConv.getTokens(value);
			   for(int n=0;n<Tokens.size();n++)
				{
					Token token =Tokens.get(n);
					
				}
			   if(!(colName.toLowerCase().contains("true")||colName.toLowerCase().contains("false")))
			   {
			   for(int i=0;i<paramlist.length;i++)
			   {
				   if(i==0)
				   {
				   updVal += "when("+colName+ "==" + paramlist[i]+","+paramlist[i+1]+")";
				   i++;
				   }
				   else 
				   {
					   if(i==paramlist.length-1){
					     updVal += ".otherwise("+ paramlist[i];    
					     String repeated = new String(new char[(paramlist.length-1)/2]).replace("\0", ")");
					     updVal +=repeated; 
					   }
					   else	
					   {   
					   updVal += ".otherwise(when("+colName+ "==" + paramlist[i]+","+paramlist[i+1]+")";  
					   i++;
					   if(i==paramlist.length-1){
						        
						     String repeated = new String(new char[(paramlist.length-1)/2]).replace("\0", ")");
						     updVal +=repeated; 
						   }
					   
					   }
				   }   
			   }
			   }   
			   else
			   {
			   for(int i=0;i<paramlist.length;i++)
			   {
				   if(i==0)
				   {
				   updVal += "when("+paramlist[i].replace(" = ", " == ")+","+paramlist[i+1]+")";
				   i++;
				   }
				   else 
				   {
					   if(i==paramlist.length-1){
					     updVal += ".otherwise("+ paramlist[i];    
					     String repeated = new String(new char[(paramlist.length-1)/2]).replace("\0", ")");
					     updVal +=repeated; 
					   }
					   else	
					   {   
					   updVal += ".otherwise(when("+paramlist[i].replace(" = ", " == ")+","+paramlist[i+1]+")";  
					   i++;
					   if(i==paramlist.length-1){
						        
						     String repeated = new String(new char[(paramlist.length-1)/2]).replace("\0", ")");
						     updVal +=repeated; 
						   }
					   
					   }
				   }   
			   }
			   }   
			   value = updVal;
			   
		   }
		   else if(value.startsWith("cast_String") ||value.startsWith("cast_numdecimal") ||value.startsWith("cast_strdec") ||value.startsWith("cast_strfloat")||value.startsWith("cast_strint") || value.startsWith("cast_Int")||value.startsWith("cast_bigint")||value.startsWith("isnumber")||value.startsWith("isdate")||value.startsWith("is_space"))
			   
		   {
			  String val = value.substring(value.indexOf("("));
			  String isNullVal = value.substring(0,value.indexOf("("));
			  
			  if(value.startsWith("cast_String"))
			     isNullVal = isNullVal.replace(isNullVal, ".cast(\"string\")");
			  else if(value.startsWith("cast_Int"))
				 isNullVal = isNullVal.replace(isNullVal, ".cast(\"int\")"); 
			  else if(value.startsWith("cast_bigint"))
				 isNullVal = isNullVal.replace(isNullVal, ".cast(\"bigint\")"); 
			  
			  else if(value.startsWith("cast_numdecimal"))
					 isNullVal = isNullVal.replace(isNullVal, ".cast(\"decimal\")");
			  else if(value.startsWith("cast_strdec"))
					 isNullVal = isNullVal.replace(isNullVal, ".cast(\"decimal\")");
			  else if(value.startsWith("cast_strfloat"))
					 isNullVal = isNullVal.replace(isNullVal, ".cast(\"float\")");
			  else if(value.startsWith("cast_strint"))
					 isNullVal = isNullVal.replace(isNullVal, ".cast(\"int\")");
			  else if(value.startsWith("isnumber"))
			     isNullVal = isNullVal.replace(isNullVal, ".cast(\"double\").isNotNull()");
			  else if(value.startsWith("is_space"))
			     isNullVal = isNullVal.replace(isNullVal,".rlike(\"^\\s*$\")");
			  else if(value.startsWith("isdate"))
			  {     
				  val = "to_date"+ val;
				  isNullVal = isNullVal.replace(isNullVal, ".cast(\"date\").isNotNull()");
			  }
			  
			  value = val + isNullVal;
		   }
		   else if(value.startsWith("cast_numstr"))
		  {
			   
			  String val = value.substring(value.indexOf("("));
		      String isNullVal = value.substring(0,value.indexOf("("));
				  
			isNullVal = isNullVal.replace(isNullVal, ".cast(\"String\")");
			value = val + isNullVal;
			  }
		   else if(value.startsWith("isnotnull"))
		   {
			   String val = value.substring(value.indexOf("("));
			   String isNullVal = value.substring(0,value.indexOf("("));
			
			   isNullVal = isNullVal.replace(isNullVal, ".isNotNull()");
				  
			   value = val + isNullVal;
		   }
		   
		   else if(value.startsWith("mod") || value.startsWith("rlike"))
		   {
			   String val = value.substring(value.indexOf("("));
			   String colName = val.substring(0,val.indexOf(","))+")";
			   String params ="(" +value.substring(value.indexOf(",")+1);
			  
			   String updVal="";
			   
			   if(value.startsWith("mod"))
				   updVal=colName+".mod"+params; 
			   
			   else if(value.startsWith(("rlike")))
				   updVal=colName+".rlike"+params;
				   
			   value = updVal;
			   
		   }
		   
		   else if(value.startsWith("isin"))
		   {
			   /*String isIncol =  value.substring(0,value.indexOf(","));
			   String isInCheck= value.substring(value.indexOf(",")+1);
			   isIncol = isIncol.replace("isin", "");
			   value = isIncol + ")"+".isin("+isInCheck;*/
		   }
		   else if(value.contains(" concat"))
		   {
			   String val = value.substring(0,value.indexOf("(")+1);
			   String concatVal = value.substring(value.indexOf("(")+1,value.lastIndexOf(")")+1);
			   value = val +"concat("+concatVal+")";
			   value = value.replace(" concat", ",");
		       
		   }
		   else if (value.startsWith("leftsubstring")) {
				value = value.replace("leftsubstring", "SUBSTRING");

				String params = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
				String paramList[] = params.split(",");
				value = value.replace(params, paramList[0] + ",0," + paramList[1]);
			}
		}
		else if (newToolId == ToolTypeConstants.ADFDATAFLOW)
		{
			value = checkForConcat(value);
			ArrayList<Token> tknLst = FunctionTypeConv.getTokens(value);
			String val = "";
			for(int i = 0 ; i < tknLst.size() ; i++)
			{			
				 if(tknLst.get(i).type.name().equalsIgnoreCase("VARWITHDOT"))
				{
					String colName = tknLst.get(i).data;
					if(colName.contains("."))
					{
						val += " " + colName.substring(colName.lastIndexOf(".")+1, colName.length());
					}
					else
						val += " " + colName;
				}
				else if(tknLst.get(i).type.name().equalsIgnoreCase("DATEFORMAT"))
				{
					val += "'"+ tknLst.get(i).data + "'";
					
				}
				else if(tknLst.get(i).data.equalsIgnoreCase("NULL"))
				{
					val += " "+ "''";
				}
				else if(tknLst.get(i).data.equalsIgnoreCase("replace"))
				{
					boolean removeCaseOp = false;
					for(int j=i ;!tknLst.get(j).type.name().equals("CLOSEPAREN"); j++)
					{
						if(removeCaseOp == false &&  tknLst.get(j).type.name().equals("OPENPAREN"))
						{
							if(tknLst.get(j+1).type.name().equals("INTEGER"))
							{					
								tknLst.remove(j+1);//remove case senstivity flag
								tknLst.remove(j+1);///remove comma
								removeCaseOp = true;
							}
						}
					}
					val += " " +tknLst.get(i).data;	
					
				}
				else if(tknLst.get(i).data.startsWith("addDays") || tknLst.get(i).data.equalsIgnoreCase("addDays"))
				{
					int j=0;
					for(j=i ; !tknLst.get(j).type.name().equals("CLOSEPAREN"); j++)
					{
						if(tknLst.get(j).data.equals("\"DD\"") || tknLst.get(j).data.equals("'DD'"))
						{
							tknLst.get(i).data = "addDays";
							val += "addDays";
							tknLst.remove(j+1);
							tknLst.remove(j);				
						}
						else if(tknLst.get(j).data.equals("\"MM\"") || tknLst.get(j).data.equals("'MM'"))
						{
							tknLst.get(i).data = "addMonths";
							val += "addMonths";
							tknLst.remove(j+1);
							tknLst.remove(j);						
						}
					}					
				}	
				else if(tknLst.get(i).data.startsWith("regexReplace") || tknLst.get(i).data.startsWith("regexMatch"))
				{
					boolean commaFlag = true;
					if(tknLst.get(i).data.startsWith("regexReplace"))
						val += "regexReplace";
					else
						val += "regexMatch";
					int j=0;
					for(j=i ; !tknLst.get(j).type.name().equals("CLOSEPAREN"); j++)
					{
						if(commaFlag ==true)
						{
							if(tknLst.get(j).type.name().equals("DECSEP"))
							{
								String regExValue = tknLst.get(j+1).data;
								if(!regExValue.startsWith("'("))
								{
									regExValue = regExValue.replace("'", "");
									tknLst.get(j+1).data = "'(" + regExValue.replace("[", "").replace("]", "").replace("\\", "\\\\") + ")'";
								}
								commaFlag = false;
							}
						}
					}
				}
				else if(tknLst.get(i).data.startsWith("isNull"))
				{	
					int j=0;
					for(j=i ; !tknLst.get(j).type.name().equals("CLOSEPAREN"); j++)
					{
						if(tknLst.get(j).type.name().equals("LOGICALOP"))
							val += ") "+ tknLst.get(j).data+ "isNull(";
						else if(tknLst.get(j).type.name().equalsIgnoreCase("VARWITHDOT"))
						{
							String colName = tknLst.get(j).data;
							if(colName.contains("."))
							{
								val += " " + colName.substring(colName.lastIndexOf(".")+1, colName.length());
							}
							else
								val += " " + colName;
						}
						else
							val += tknLst.get(j).data;				

					}
					val += " " +tknLst.get(j).data;	
					i = j;	
					continue;
				}
				else
				{
					val += " " +tknLst.get(i).data;
				}
			}
			value = val;
			
		}	
		else if (newToolId == ToolTypeConstants.APACHE_BEAM)
		{
			if (value.startsWith("leftsubstring")) {
				value = value.replace("leftsubstring", "SUBSTRING");

				String params = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
				String paramList[] = params.split(",");
				value = value.replace(params, paramList[0] + ",0," + paramList[1]);
			}
				else if(value.startsWith("CASTNUMTOSTRING"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+"AS STRING)";
				value = updval;
			}
			else if(value.startsWith("CASTDATETOINTEGER"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+"AS INTEGER)";
				value = updval;
			}
			/*else if(value.startsWith("CASTNUMTOSTRING"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+"AS STRING)";
				value = updval;
			}*/
			else if(value.startsWith("CASTNUMTODECIMAL"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+"AS DECIMAL)";
				value = updval;
			}
			else if(value.startsWith("CASTSTRTODECIMAL"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+"AS DECIMAL)";
				value = updval;
			}
			else if(value.startsWith("CASTSTRTOFLOAT"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+"AS FLOAT)";
				value = updval;
			}
			else if(value.startsWith("CASTSTRTOINTEGER"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+"AS INTEGER)";
				value = updval;
			}
			
			else if(value.startsWith("CASTTsToDate"))
			{
				String val = value.substring(value.indexOf("(")+1,value.length()-1);
				String updval = "CAST("+val+") AS DATE";
				value = updval;
			}
			else if (value.contains("dsPipe"))
				value = value.replace("dsPipe", "||");
			else if(value.startsWith("IF")||value.startsWith("dsError_IIF"))
			   {  
				   boolean checkIfThenElse = checkIfThenElse(value);
				  if(checkIfThenElse == true)
				  {
					   String ifCond =  value.substring(0,value.lastIndexOf(","));
					   String cond = value.substring(value.indexOf("("),value.indexOf(","));
					   String thenCond = ifCond.substring(ifCond.lastIndexOf(",")+1);
					   String elseCond = value.substring(value.lastIndexOf(",")+1);
					   value ="CASE WHEN"+cond + ")"+".THEN("+thenCond+").ELSE("+elseCond;
				  }
			   }
			  else if(value.startsWith("IsBlank"))
			   {
				   String colName = value.substring(value.indexOf("("));
				   value = value.replace(colName,"IS NULL"+colName+" OR "+colName+"= \"\""+ ").THEN(TRUE))");
				   value = value.replace("IsBlank","CASE WHEN(");
			   }
			  else if(value.startsWith("NullToValue"))
			   {
				   String colName = value.substring(value.indexOf("("),value.indexOf(","));
				   String returnValue = value.substring(value.indexOf(",")+1, value.indexOf(")"));
				   value = value.replace(colName,"IS NULL"+colName+") THEN "+returnValue);
				   value = value.replace("NullToValue","CASE WHEN(").replace(returnValue+","+returnValue, returnValue);
			   }
			  else if(value.startsWith("NullToZero"))
			   {
				   String colName = value.substring(value.indexOf("("));
				   value = value.replace(colName,"IS NULL"+colName+")");
				   value = value.replace("NullToZero","CASE WHEN(");
			   }
		}
		
		
		
		else if(newToolId == ToolTypeConstants.AZURESQL)
		{
			value = updatedStmts(value, "dsError_For");
			value = updatedStmts(value, "dsError_From");
			
			if(value.startsWith("CURRENT_TIMESTAMP") || value.startsWith("TIMESTAMP") || value.startsWith("DATETIME2"))
			{
				String val = value.substring(0,value.indexOf("("));
				value = val;
			}
			
			else if(value.startsWith("DATENAME"))	
			{  
				value = getUpdatedString(value);
				String val = value.substring(value.indexOf("(")+1,value.indexOf(" FROM "));
				String col=value.substring(value.indexOf(" FROM ")+6,value.indexOf(")"));
				value = val+"("+col+")";
			}
			
			else if(value.startsWith("PATINDEX"))
			{  
				String col1=value.substring(value.indexOf(",")+1,value.indexOf(")"));
				 String col2=value.substring(value.indexOf("(")+1,value.indexOf(","));
				 value ="PATINDEX("+col1+","+col2+")";   
			}
			
			else if(value.startsWith("CAST"))
			{
				value = getDataTypesFromDataTypConvertor(value, currentToolId, newToolId, curntFuns);
				value = value.replace("FORMAT 'Z(I)'", "VARCHAR(100)");
			}
			
			else if(value.startsWith("SUBSTRING"))
			{
				value = getUpdatedString(value);
				value = value.replace(" FROM ", ",").replace(" FOR ", ",");
			}
			else if(value.startsWith("DSUNKWN_FROM") || value.startsWith("DSUNKWN_FOR"))
			{
				value = value.replace("DSUNKWN_FROM", ",").replace("DSUNKWN_FOR", ",");
			}
			else if(value.startsWith("DATEADD"))
			{
				value = getUpdatedString(value);
				
				String tmp = "month";
				String val = value.substring(0, value.indexOf("("));
				String startTmp = value.substring(value.indexOf("(")+1, value.indexOf(","));
				String endTmp = value.substring(value.indexOf(",")+1, value.indexOf(")"));
				
				value = val +"("+ tmp + "," + endTmp + "," + startTmp + ")"  ;
				
			}
			else if(value.startsWith("CONVERT"))
			{
				value = getUpdatedString(value);
				//CONVERT ( '12/31/1900' , 'MM/DD/YYYY' )
				String tmp = "DATE";
				String val = value.substring(0, value.indexOf("("));
				String startTmp = value.substring(value.indexOf("(")+1, value.indexOf(","));
				
				value = val +"("+ tmp + "," + startTmp + ")"  ;
				
			}
			else if(value.startsWith("ISNULL"))
			{
				value = getUpdatedString(value);
				String startTmp = value.substring(0, value.indexOf(")"));
				
				value = startTmp + " , 0)"  ;
				
			}
			else
			{
				  ArrayList<Token> alltokens1 = Lexer.interpret(value);
				   String updatedVal = "";
				   boolean checkComma = false;
				   boolean format = false;
				   for(int i = 0 ; i < alltokens1.size(); i++)
				   {
					   String val  = alltokens1.get(i).data;
					   if(val.startsWith("dsError_"))
					   {
						   String updDateTyp = ""; 
						   if(val.contains("dsError_"))
						   {
							   String tempVal = val.substring(val.indexOf("dsError_")+9,val.length());
							   DataTypeConvertor dtc = new DataTypeConvertor();
							   String dataType = dtc.getTargetDataType(tempVal, currentToolId, ToolTypeConstants.AZURESQL);
							   updDateTyp = dataType;
						   }
						   if(updDateTyp != null)
						   {
							   alltokens1.get(i).data = updDateTyp;
						   }
						   else
						   {
							   alltokens1.get(i).data = val;
						   }
						  
					   }
					  
					   if(alltokens1.get(i).data.equalsIgnoreCase("format"))
					   {
						   if(!alltokens1.get(i+1).data.isEmpty())
						   {
							   format = true;
						   }
						   continue;  
					   }
					   else if(alltokens1.get(i).data.equalsIgnoreCase("date"))
					   {
						   if(alltokens1.get(i+1).data.equalsIgnoreCase(","))
						   {
							   checkComma = true;
						   }
						   updatedVal += alltokens1.get(i).data + " ";
					   }
					   else
					   {
						   if(checkComma)
						   {
							   checkComma = false;
							   continue;
						   }
						   if(format)
						   {
							   format = false;
							   continue;
							  
						   }
						   
						   updatedVal += alltokens1.get(i).data + " ";
					   }
					   
					   
				   }
				   
				   value = updatedVal;
			}
		}
		else if(newToolId == ToolTypeConstants.BIGQUERY)
		{
			value = updatedStmts(value, "dsError_For");
			value = updatedStmts(value, "dsError_From");
             value = updatedStmts(value, "char");
			
			if(value.startsWith("CHAR ( 26 )")){
				
				   String val = value.substring(0,value.indexOf("(")).trim();
				   val = val.replace("CHAR", "STRING");
				   value = val;
			   }
			
			if(value.contains("dsError_ :"))
			{
			    String val = value.replace("dsError_ : ",":");
				value = val;
			}
			
			if(value.startsWith("ifnull"))
			{
				String col=value.substring(value.indexOf("(")+1,value.indexOf(")"));
				value = "ifnull("+col+",0)";
			}
			
			if(value.contains("||") && !value.startsWith("CONCAT"))
			{
				value = getUpdatedConcatValue( newToolId, value);
			}
			else if(value.startsWith("SUBSTR"))
			{
				value = getUpdatedString(value);
				value = value.replace(" FROM ", ",").replace(" FOR ", ",");
				value = getupdatedSubstring(value,entry.getKey(),curntFuns);
			}
			else if(value.startsWith("DSUNKWN_FROM") || value.startsWith("DSUNKWN_FOR"))
			{
				value = value.replace("DSUNKWN_FROM", ",").replace("DSUNKWN_FOR", ",");
			}
			if(value.startsWith("CASE")) 
			{
				value = getUpdatedCaseStatement(value,curntFuns,entry.getKey());
			}
			if(value.startsWith("DECODE")) 
			{
				value = getConvertedDecodeFormat(value, currentToolId, newToolId, curntFuns);
			}
			if(value.startsWith("CAST"))
			{
				value = getDataTypesFromDataTypConvertor(value, currentToolId, newToolId, curntFuns);
			}
			if(value.startsWith("RTRIM") || value.startsWith("LTRIM"))
				   value = getupdatedTrim(value,entry.getKey(),curntFuns);
			if (value.contains("=") && (value.contains("WHEN") || value.contains("when")))
				value = getupdatedBinaryCast(value);
			
				
			
		}
		else if(newToolId == ToolTypeConstants.SNOWFLAKE)
		{
			value = updatedStmts(value, "dsError_For");
			value = updatedStmts(value, "dsError_From");
			
			if(value.startsWith("CAST") || value.contains(" : : "))
			{
				value = getDataTypesFromDataTypConvertor(value, currentToolId, newToolId, curntFuns);
				   
				value = value.replace(" : ", ":");
				   
			}	
			else if(value.startsWith("SUBSTR"))
			{
				value = getUpdatedString(value);
				value = value.replace(" FROM ", ",").replace(" FOR ", ",");
			}
			else if(value.startsWith("DSUNKWN_FROM") || value.startsWith("DSUNKWN_FOR"))
			{
				value = value.replace("DSUNKWN_FROM", ",").replace("DSUNKWN_FOR", ",");
			}		
			else if(value.contains("||") && !value.startsWith("CONCAT"))
			{
				//value = getUpdatedConcatValue( newToolId, value);
			}
			else if(value.contains("(.*?)"))
			{
				value = value.replace("(.*?)", "\\\\w+");
			}
			else if(value.startsWith("TRUNC"))
			{
				if(value.equalsIgnoreCase("TRUNC ( CURRENT_TIMESTAMP )") || value.equalsIgnoreCase("TRUNC ( SYSDATE() )") )
					value = "CURRENT_DATE";
			}
			else if(value.startsWith("CONVERT_TIMEZONE"))
			{
				ArrayList<Token> tkn = FunctionTypeConv.getTokens(value);
				String startVal = "",endVal = "",midVal = "",tmpVal = "";
				boolean flag = true;
				int cnt = 0;
				int strt = 0, mid = 0;
				for (int j = 0; j < tkn.size(); j++) 
				{
					if ((!tkn.get(j).data.equalsIgnoreCase(",")) && cnt == 2)
						endVal += tkn.get(j).data;
					if (tkn.get(j).data.equalsIgnoreCase(",") && cnt == 2)
						cnt++;
					
					if ((!tkn.get(j).data.equalsIgnoreCase(",")) && cnt == 1)
						midVal += tkn.get(j).data;
					if (tkn.get(j).data.equalsIgnoreCase(",") && cnt == 1)
						cnt++;
					
					if ((!tkn.get(j).data.equalsIgnoreCase(",")) && cnt == 0)
						startVal += tkn.get(j).data;
					if (tkn.get(j).data.equalsIgnoreCase(",") && cnt == 0)
						cnt++;
					
					
				}
				startVal = startVal.substring(startVal.indexOf("(")+1,startVal.length());
				endVal = endVal.substring(0,endVal.lastIndexOf(")"));
				value = "CONVERT_TIMEZONE ("+midVal + " , "+ endVal +" , "+startVal+" )";
	/*			if (value.contains(","))
				{
					String temp[] = value.split(",");
					if (temp.length == 3)
					{
						startVal = temp[0].substring(temp[0].indexOf("(")+1);
						midVal = temp[1];
						endVal = temp[2];
					}
				}
				value = "CONVERT_TIMEZONE ("+midVal + " , "+ endVal +" , "+startVal+" )";*/
			}
			else if(value.startsWith("CURRENT_TIMESTAMP"))
			{
				if (value.contains("("))
					value = value.substring(0, value.indexOf("("));
			}
		}
		
		else if(currentToolId == ToolTypeConstants.DATASTAGE || currentToolId == -1)
		{
			if(value.startsWith("[") && value.contains(","))
			{
				
				String getPrevValIndex = entry.getKey().substring(entry.getKey().indexOf("_")+1);
				String getPrevValName = entry.getKey().substring(0,entry.getKey().lastIndexOf("_"));
				int getPrevIndx = Integer.parseInt(getPrevValIndex)-1;
				String prevKeyVal = getPrevValName +"_"+getPrevIndx;
				String getPrevKeyVal = curntFuns.get(prevKeyVal);
				
				if((getPrevIndx+1) == Integer.parseInt(getPrevValIndex))
				{
					for (Entry<String, String> entry1 : curntFuns.entrySet())
					{
						if(entry1.getKey().equalsIgnoreCase(prevKeyVal))
						{
							if(entry1.getValue().startsWith("("))
							{
								String subStrVal = entry1.getValue();
							    entry1.setValue("dsSubString"+"("+subStrVal+",");
							}
						}
					}
				}
				if(getPrevKeyVal.startsWith("("))
				{
					value = value.replace("[", "").replace("]", "")+")";
				}
			}
			else if(value.contains("[") && !value.startsWith("[") &&  value.contains(","))
			{
				value = "dsSubString" + "(" + value.replace("[", ",").replace("]", ")").replace("dsError_", ""); 
			}
			else if(value.contains("?") || value.contains("dsElse")){
				value = value.replace("?", ",").replace("dsElse",",");
			}
			else if(value.startsWith("dsTrim"))
			{
				boolean checkIfThenElse = checkIfThenElse(value);
				if(checkIfThenElse)
				{
					String endVal = value.substring(value.lastIndexOf(",")+1,value.lastIndexOf(")")).trim();
					String startVal = value.substring(value.indexOf("("), value.lastIndexOf(","));
					if(endVal.equalsIgnoreCase("\"L\""))
					{
						value = "dsLtrim" + startVal + ")";
					}
				}
			}
			
		}
		
		else if(newToolId == ToolTypeConstants.TALEND)
		{
			boolean checkIf = checkIfThenElse(value);
			if(value.startsWith("dsIIf"))
			{
				value = value.replace("dsIIf", "");
				
				if(checkIf==true)
				{
					String first = value.substring(0,value.indexOf(","));
					String mid = value.substring(value.indexOf(",")+1,value.lastIndexOf(","));
					String last = value.substring(value.lastIndexOf(",")+1);
					value = first+"?"+mid+":"+last;
				}
			}
			else if(value.contains("=null ") || value.contains("=null")){
				value = "null";
			}
			else if (value.contains("LeftSubString")) {
				String inbr = value.substring(value.indexOf('(') + 1, value.indexOf(')'));
				String idx = inbr.substring(inbr.indexOf(',') + 1);
				String add = "0," + idx + ")";
				String x = value.substring(value.indexOf('('), value.indexOf(',')) + add;
				value = "StringHandling.substr" + x;

			}
		}
		else if(newToolId == ToolTypeConstants.INFORMATICA_PC)
		{
			if(value.startsWith("LTRIM(RTRIM)"))
			{
				value = value.replace("LTRIM(RTRIM)", "LTRIM(RTRIM")+")";
			}
			if(value.startsWith("SUBSTR"))
			{
				if(checkIfThenElse(value) == false)
				{
					value = value.replace(",", ",1,");
				}
			}
			else if(value.startsWith("RIGHT_SUBSTR"))
			{
				ArrayList<Token> tokens = FunctionTypeConv.getTokens(value);
				if(checkIfThenElse(value) == false)
				{
					String intVal = "";
					for(int i = 0 ; i < tokens.size();i++)
					{
						if(tokens.get(i).type.name().equalsIgnoreCase("INTEGER") || tokens.get(i).type.name().equalsIgnoreCase("INTEGERWITHDECIMAL"))
							intVal = tokens.get(i).data;
								
					}
					value = value.replace(",", ","+"-"+intVal+",").replace("RIGHT_SUBSTR", "SUBSTR");
				}
			}
			else if(value.startsWith("REPLACESTR"))
			{
				String intVal = value.substring(0,value.indexOf("("));
				String EndVal = value.substring(value.indexOf("(")+1);
				value = intVal + "(1 ," +  EndVal;
			}
		}
		
		else if(newToolId == ToolTypeConstants.SSIS)
		{
			if(value.contains("dsError_& lt ;") || value.contains("dsError_& gt ;"))
			{
				value = value.replace("dsError_& lt ;", "&lt;").replace("dsError_& gt ;", "&gt;");
			}
			if(value.startsWith("dsIIf"))
			{
				value = value.replace("dsIIf", "");
				String Cond = "";
				String thenCond = "";
				String elseCond = "";
				
				if(value.contains(","))
					 Cond = value.substring(0,value.indexOf(","));
				if(checkIfThenElse(value))
				{
					thenCond = value.substring(value.indexOf(",")+1, value.lastIndexOf(","));
					elseCond = value.substring(value.lastIndexOf(",")+1, value.length());
					value = Cond + " ? " + thenCond + " : " + elseCond;
				}
				else
				{
					thenCond = value.substring(value.indexOf(",")+1, value.length());
					value = Cond + " ? " + thenCond;
				}
				
				
			}
			
		}
		else if(newToolId == ToolTypeConstants.SQLSERVER)
		{
			value = updatedStmts(value, "dsError_For");
			value = updatedStmts(value, "dsError_From");
			
		    if (value.startsWith("DECODE"))
			{
		    	String temp = "";
				if (value.contains(","))
				{
					int cnt = 0;
					ArrayList<Token> tkn = FunctionTypeConv.getTokens(value);
					
					temp += "CASE WHEN " ;
					
					for(int i = 0 ; i < tkn.size() ; i++)
					{
						if (tkn.get(i).type.name().equalsIgnoreCase("OPENPAREN") || tkn.get(i).type.name().equalsIgnoreCase("CLOSEPAREN")
								|| tkn.get(i).data.equalsIgnoreCase("decode"))
							continue;
						
						if (tkn.get(i).data.equalsIgnoreCase("dsPipe"))
							temp += " + "; 
						else if ((tkn.get(i).type.name().equalsIgnoreCase("var") || tkn.get(i).type.name().equalsIgnoreCase("varwithdot")))
							temp += " ISNULL(" +tkn.get(i).data +" ,'') ";
						
						else if(tkn.get(i).type.name().equalsIgnoreCase("DECSEP"))
						{
							if(cnt == 0)
								if(tkn.get(i+1).type.name().equalsIgnoreCase("INTEGER"))
									temp += " = " ;
								else
									temp += " IS ";
							else if(cnt == 1)
							{
								temp += " THEN " ;
							}
							else if(cnt == 2)
							{
								temp += " ELSE " ;
							}
							
							cnt++;
						}
						else
							temp += " "+ tkn.get(i).data;
							
					}
					if (temp.length() > 0)
					{
						temp += " END ";
						value = temp;
					}
				}
				
			}
		    else if(value.startsWith("CAST"))
			{
				value = getDataTypesFromDataTypConvertor(value, currentToolId, newToolId, curntFuns);
			}
		    else if (value.contains("dsPipe"))
			{
				String temp = "";
				ArrayList<Token> tkn = FunctionTypeConv.getTokens(value);
				for (int i = 0; i < tkn.size(); i++) 
				{
					if (tkn.get(i).data.equalsIgnoreCase("dsPipe"))
						temp += " + "; 
					else if ((tkn.get(i).type.name().equalsIgnoreCase("var") || tkn.get(i).type.name().equalsIgnoreCase("varwithdot")))
						temp += " ISNULL(" +tkn.get(i).data +" ,'') ";
					else
						temp += " "+tkn.get(i).data;
				}
				value = temp;
			}
			
			else if (value.contains("COUNT"))
			{
				String temp = "";
				
				String alias = "";
				if (value.contains("as"))
				{
					temp = value.substring(0, value.indexOf("as"));
					temp += ") ";
					alias = value.substring(value.indexOf("as")+2, value.length()-1);
					temp += " AS "+ alias;
					value = temp;
				}
			}
			else if (value.contains("CONVERT"))
			{
				String temp = "";
				String paramTmp = "", numTmp = "";
				boolean dateFlag = false, paramFlag = false, closeFalg = false;
				if (value.contains("GETDATE()"))
					dateFlag = true;
				
				if (value.contains(",") || value.contains("["))
					paramFlag = true;
				
				ArrayList<Token> tkn = FunctionTypeConv.getTokens(value);
				for(int i = 0 ; i < tkn.size() ; i++)
				{
					if (paramTmp.length() > 0)
						break;
					if (paramFlag)
					{
						if (tkn.get(i).data.equalsIgnoreCase("'dd'") || tkn.get(i).data.equalsIgnoreCase("dd"))
						{
							paramTmp = " VARCHAR(10), "; 
							numTmp = "120";
							closeFalg = true;
						}
						else if (tkn.get(i).data.equalsIgnoreCase("'mm'") || tkn.get(i).data.equalsIgnoreCase("mm"))
						{
							paramTmp = " VARCHAR(7), "; 
							numTmp = "120)+'-01'";
						}
						else if (tkn.get(i).data.equalsIgnoreCase("'yy'") || tkn.get(i).data.equalsIgnoreCase("yy"))
						{
							paramTmp = " VARCHAR(3), ";
							numTmp = "120)+'-01-01'";
						}
						else if (tkn.get(i).data.equalsIgnoreCase("'hh'") || tkn.get(i).data.equalsIgnoreCase("'hh24'") || tkn.get(i).data.equalsIgnoreCase("hh") || tkn.get(i).data.equalsIgnoreCase("hh24"))
						{
							paramTmp = " VARCHAR(13), ";
							numTmp = "120)+':00:00'";
						}
						else if (tkn.get(i).data.equalsIgnoreCase("'mi'") || tkn.get(i).data.equalsIgnoreCase("mi"))
						{
							paramTmp = " VARCHAR(16), ";
							numTmp = "120)+':00'";
						}
					}
				}
				for(int i = 0 ; i < tkn.size() ; i++)
				{
					if(tkn.get(i).type.name().equalsIgnoreCase("OPENPAREN") && (!tkn.get(i-1).data.equalsIgnoreCase("getdate")))
					{
						temp += tkn.get(i).data + "DATETIME, CONVERT(";
						if (dateFlag && !paramFlag)
							temp += "DATE, ";
						if (paramFlag)
						{
							if (paramTmp.length() > 0)
								temp += paramTmp;
						}
					}
					else if(tkn.get(i).type.name().equalsIgnoreCase("CLOSEPAREN") && (!tkn.get(i-1).data.equalsIgnoreCase("(")))
					{
						temp += numTmp ;
						temp += tkn.get(i).data ;
						if (closeFalg)
							temp += tkn.get(i).data ;
					}
					else if (paramFlag && (tkn.get(i).data.equalsIgnoreCase("'dd'") || tkn.get(i).data.equalsIgnoreCase("dd") || tkn.get(i).data.equalsIgnoreCase("'mm'") || tkn.get(i).data.equalsIgnoreCase("mm")
							|| tkn.get(i).data.equalsIgnoreCase("'yy'") || tkn.get(i).data.equalsIgnoreCase("yy") || tkn.get(i).data.equalsIgnoreCase("'hh'") || tkn.get(i).data.equalsIgnoreCase("'hh24'") || tkn.get(i).data.equalsIgnoreCase("hh") || tkn.get(i).data.equalsIgnoreCase("hh24")
							|| tkn.get(i).data.equalsIgnoreCase("'mi'") || tkn.get(i).data.equalsIgnoreCase("mi")))
					{
						
					}
					else
						temp += " "+tkn.get(i).data;
				}
				value = temp;
			}
			else if (value.contains("dsError_greatest"))
			{
				ArrayList<String> tempVal = new ArrayList<String>();
				if (value.contains("(") && value.contains(")"))
				{
					String temp = "", alias = null;
					ArrayList<Token> tkn = FunctionTypeConv.getTokens(value);
					boolean startFlag = false, endFlag =false;;
					for (int i = 0; i < tkn.size(); i++) 
					{
						if (startFlag)
						{
							if (endFlag)
							{
								alias = tkn.get(i).data;
								break;
							}
							if (tkn.get(i).type.name().equalsIgnoreCase("CLOSEPAREN"))
								endFlag = true;
							
							if ((!(tkn.get(i).type.name().equalsIgnoreCase("OPENPAREN") || tkn.get(i).type.name().equalsIgnoreCase("DECSEP"))) && (!endFlag))
								tempVal.add(tkn.get(i).data);
						}
						if (tkn.get(i).data.equalsIgnoreCase("dsError_greatest"))
							startFlag = true;
					}
					temp = "CASE \n";
					int j = 0;
					String tmp = "";
					for (int i = 0; i < tempVal.size(); i++) 
					{
						if (i+1 == tempVal.size() && i > 0)
						{
							temp += " ELSE " + tempVal.get(j-1);
							break;
						}
						temp += " WHEN ";
						if (i > 0)
						{
							for (int k2 = i-1; k2 >= 0; k2--) 
							{
								tmp += tempVal.get(i) +" > " + tempVal.get(k2) +" AND ";
							}
						}
						if (tmp.length() > 0)
						{
							temp += tmp;
							tmp = "";
						}
						for (j = i+1; j < tempVal.size(); j++) 
						{
							temp += tempVal.get(i) +" > " + tempVal.get(j);
							if (tempVal.size() > 2 && j < tempVal.size()-1)
								temp += " AND ";
						}
						temp += " THEN " + tempVal.get(i)+"\n";
						if (j == tempVal.size() && i+1 == tempVal.size())
						{
							temp += " ELSE " + tempVal.get(j-1);
							break;
						}
					}
					temp += " END ";
//					System.out.println(temp);
					value = temp;
				}
				
			}
		
		}
		
		return value;
		
	}

	private static String getUpdatedCaseStatement(String value, HashMap<String, String> curntFuns, String key) 
	{
		// TODO Auto-generated method stub
		String [] condParams = value.split(",");
		if(condParams.length == 3 ) 
		{
			String caseCond = "";
			String cond = "";
			String tempCol = "";
			if(condParams[0].contains("nestedVar_")) 
			{
				cond = condParams[0].replace("CASE ( ", "").trim();
				cond = curntFuns.get(cond);
				if(!cond.contains(">") && !cond.contains("<") && !cond.contains("=")) 
				{
					tempCol = cond;
					if(tempCol.contains("(") && tempCol.contains(")")) 
					{
						tempCol = tempCol.substring(tempCol.indexOf("(")+1, tempCol.lastIndexOf(")"));
						 System.out.println("Cond---"+cond);
					}
					caseCond = "CASE WHEN "+cond+" THEN "+tempCol+"= "+condParams[1]+"Else "+tempCol+" = "+condParams[2].replace(")", "")+" END";
					return caseCond;
				}
				else 
					return value;
				
			}
			else 
				return value;
			
			
		}
		else if(condParams.length == 2)
		{   
			String caseCond = "";
		String cond = "";
		String tempCol = "";
			return value;
		}
		
		
		return value;
	}



	private static String getConvertedDecodeFormat(String value, int currentToolId, int newToolId,
			HashMap<String, String> curntFuns) {
		// TODO Auto-generated method stub
		if(newToolId == ToolTypeConstants.BIGQUERY) 
		{			 
			String rule = value.replace("DECODE", "").replace("(", "").replace(")", "");
			String [] decodeParameteres = rule.split(",");
			String decodeRule = "";
			decodeRule +="CASE  ";
			for(int i = 1; i < decodeParameteres.length; i++) 
			{
				if(i == (decodeParameteres.length-1))
					decodeRule +="ELSE "+decodeParameteres[i] +"END";
				else
					decodeRule +="WHEN "+decodeParameteres[0] +"="+ decodeParameteres[i] +"THEN" +decodeParameteres[0] +"="+ decodeParameteres[i];			
				i = i+1;
			}
			return decodeRule;
		}
		return value;
	}



	private static String getupdatedSubstring(String value, String key, HashMap<String, String> curntFuns) {
		// TODO Auto-generated method stub
		
		if(curntFuns.size() > 1) 
		{
			int nestvar = Integer.parseInt(key.substring(key.indexOf("_")+1,key.length()));
			String nestValue = curntFuns.get("nestedVar_"+(nestvar+1));
			if(nestValue.startsWith("PARSE_DATE"))
				return value.replace("19", "10");
			else
				return value;
			
		}
		return value;
	}



	private static String getupdatedTrim(String value, String key, HashMap<String, String> curntFuns) {
		// TODO Auto-generated method stub
		if(curntFuns.size() > 1) 
		{
			int nestvar = Integer.parseInt(key.substring(key.indexOf("_")+1,key.length()));
			String nestValue = curntFuns.get("nestedVar_"+(nestvar+1));
			if((value.startsWith("RTRIM") && nestValue.startsWith("LTRIM")) || (value.startsWith("LTRIM") && nestValue.startsWith("RTRIM"))) 
			{
				curntFuns.put("nestedVar_"+(nestvar+1), nestValue.replace("RTRIM", "TRIM").replace("LTRIM", "TRIM"));
				return value.replace("RTRIM", "").replace("LTRIM", "").replace("(", "").replace(")", "");
			}				
			else
				return value;
			
		}
		return value;
	}



	private static String getupdatedBinaryCast(String value) {
		String[] valueArr = value.split(" ");
		int equalCount = 0;
		HashMap<String,Integer> op=new HashMap<String,Integer>();
		for (int i = 0; i < valueArr.length; i++) {
			if(valueArr[i].equalsIgnoreCase("<=")||valueArr[i].equalsIgnoreCase("=") || valueArr[i].equalsIgnoreCase("<>") )
			{
				if(op.containsKey(valueArr[i]))
				{
					int count= op.get(valueArr[i]);
					count =count+1;
					op.put(valueArr[i], count);
				}
				else
				{
					op.put(valueArr[i],1);
				}
			
			equalCount++;
			}
		}

		if (equalCount > 0) 
		{
			for(Entry<String,Integer> entry:op.entrySet() )
			{
			int[] equalIndices = findIndices(valueArr,entry, equalCount);
			for (int equalIndex : equalIndices) 
			{
				String variableName = valueArr[equalIndex - 1];
				String actComparedValue = valueArr[equalIndex + 1];
				String condCheck ="";
				for(int i = (equalIndex - 2) ; i >= 0; i--) 
				{
					if(!valueArr[i].isEmpty()) 
					{
						condCheck = valueArr[i];
						break;
					}
				}
				

				if(condCheck.equalsIgnoreCase("when")) 
				{
					if (actComparedValue.startsWith("'") || actComparedValue.startsWith("\"")) 
					{
						StringBuffer sb = new StringBuffer();
						valueArr[equalIndex - 1] = "CAST (" + variableName + " AS STRING) ";
						for (int i = 0; i < valueArr.length; i++) 
						{
							sb.append(valueArr[i]);
							sb.append(" ");
						}
						value = sb.toString();
					} 
					else if (Character.isDigit(actComparedValue.charAt(0)) && actComparedValue.contains(".")) 
					{
						StringBuffer sb = new StringBuffer();
						valueArr[equalIndex - 1] = "CAST (" + variableName + " AS FLOAT64) ";
						for (int i = 0; i < valueArr.length; i++) 
						{
							sb.append(valueArr[i]);
							sb.append(" ");
						}
						value = sb.toString();
					} 
					else if (Character.isDigit(actComparedValue.charAt(0)) && !actComparedValue.contains(".")) 
					{
						StringBuffer sb = new StringBuffer();
						valueArr[equalIndex - 1] = "CAST (" + variableName + " AS INT64) ";
						for (int i = 0; i < valueArr.length; i++) 
						{
							sb.append(valueArr[i]);
							sb.append(" ");
						}
						value = sb.toString();
					} 
					else
						value = value;
				}
				
			}
			}
			return value;
		}
		
		else
		{
			return value;
		}
	}



	private static int[] findIndices(String arr[], Entry<String, Integer> entry, int numberOfIndices) {
		int[] indexArray = new int[entry.getValue()];
		if (arr == null) {
			return indexArray;
		}

		int len = arr.length;
		int i = 0;
		int j = 0;
		while (i < len) {
			if (arr[i].equalsIgnoreCase(entry.getKey())) {
				indexArray[j] = i;
				j = j + 1;
				i = i + 1;
			} else {
				i = i + 1;
			}
		}
		return indexArray;
	}



	private static String getUpdatedConcatValue(int newToolId, String value) 
	{
		if(newToolId == ToolTypeConstants.BIGQUERY || newToolId == ToolTypeConstants.SNOWFLAKE)
		{
			if(value.contains("("))
			{
				String strVal = value.substring(0,value.indexOf("("));
				String remainVals = value.substring(value.indexOf("(")+1, value.length());
				ArrayList<Token> tkn = FunctionTypeConv.getTokens(remainVals);
				if(value.contains(","))
				{
					String startVal = "";
					for(int i = 0 ; i < tkn.size() ; i++)
					{
						if(tkn.get(i).type.name().equalsIgnoreCase("DECSEP") || tkn.get(i).data.equalsIgnoreCase("as"))
						{
							break;
						}
						else
						{
							startVal += " "+tkn.get(i).data;
						}
					}
					
					if (startVal.contains(")"))
						startVal = startVal.substring(0, startVal.lastIndexOf(")"));
					
					String midVal = value.substring(value.indexOf(startVal)+startVal.length(), value.lastIndexOf(")"));
					startVal = startVal.replace("||", ",");
					midVal = "CONCAT ( " + startVal + " )"+ midVal;
					strVal = strVal + " ( " + midVal + " ) "; 
					value = strVal;
					
				}
				else
				{
					String startVal = "";
					for(int i = 0 ; i < tkn.size() ; i++)
					{
						if(tkn.get(i).data.equalsIgnoreCase("as"))
						{
							break;
						}
						else
						{
							startVal += " "+tkn.get(i).data;
						}
					}
					
					if (startVal.contains(")"))
						startVal = startVal.substring(0, startVal.lastIndexOf(")"));
					
					String midVal = value.substring(value.indexOf(startVal)+startVal.length(), value.lastIndexOf(")"));
					startVal = startVal.replace("||", ",");
					midVal = "CONCAT ( " + startVal + " )"+ midVal;
					strVal = strVal + " ( " + midVal + " ) "; 
					value = strVal;
					/*String midVal = value.substring(value.indexOf("(")+1,value.lastIndexOf(")"));
					midVal = midVal.replace("||", ",");
					midVal = "CONCAT ( " + midVal + " )";
					strVal = strVal + " ( " + midVal + " ) "; 
					value = strVal;*/
				}
				
			}
		}
		
		return value;
	}
	

	private static String getDataTypesFromDataTypConvertor(String value, int currentToolId, int newToolId, HashMap<String, String> curntFuns) 
	{

		ArrayList<Token> alltokens1 = Lexer.interpret(value);
		   String updatedVal = "";
		   boolean format = false;
		   boolean checkComma = false;
		   for(int i = 0 ; i < alltokens1.size(); i++)
		   {
			   if(alltokens1.get(i).data.contains("nestedVar_") && alltokens1.get(i).type.name().equalsIgnoreCase("VAR"))
			   {
				   String syntaxForm  = curntFuns.get(alltokens1.get(i).data);
				   
				   if(syntaxForm.startsWith("dsError_"))
				   {
					   String val = syntaxForm.substring(0,syntaxForm.indexOf("(")).trim();
					   String precScale = syntaxForm.substring(syntaxForm.indexOf("("),syntaxForm.length()).trim();
					   String updDateTyp = ""; 
					   if(val.contains("dsError_"))
					   {
						   val = val.substring(val.indexOf("dsError_")+9,val.length());
						   DataTypeConvertor dtc = new DataTypeConvertor();
						   String dataType = dtc.getTargetDataType(val, currentToolId, newToolId);
						   updDateTyp = dataType;
					   }
					   
					   String updVal = "";
					   if(newToolId == ToolTypeConstants.BIGQUERY && (updDateTyp.equalsIgnoreCase("NUMERIC") || updDateTyp.equalsIgnoreCase("STRING") ))
						   updVal = updDateTyp ;
					   else
						   updVal = updDateTyp + precScale;

					    
					   updatedVal += updVal + " ";
				   }
				   else
				   {
					   updatedVal += alltokens1.get(i).data + " ";
				   }
			   }
			   else
			   {
				   if(newToolId == ToolTypeConstants.AZURESQL)
				   {
					   String chkDate = "date";
					   Boolean chkForDate = chkForValueInToken(alltokens1, chkDate);
					   Boolean chkForTimestamp = chkForValueInToken(alltokens1,"timestamp");
					   if(alltokens1.get(i).data.equalsIgnoreCase("format") &&( chkForDate ||chkForTimestamp ))
					   {
						   if(!alltokens1.get(i+1).data.isEmpty())
						   {
							   format = true;
						   }
						   continue;  
					   }
					   else if(alltokens1.get(i).data.equalsIgnoreCase("timestamp"))
					   {
						   updatedVal += "DATETIME2" + " ";
					   }
					   else if(alltokens1.get(i).data.equalsIgnoreCase("date"))
					   {
						   if(alltokens1.get(i+1).data.equalsIgnoreCase(","))
						   {
							   checkComma = true;
						   }
						   updatedVal += alltokens1.get(i).data + " ";
					   }
					   else
					   {
						   if(format)
						   {
							   format = false;
							   continue;
						   }
						   if(checkComma)
						   {
							   checkComma = false;
							   continue;
						   }
						   updatedVal += alltokens1.get(i).data + " ";
					   }
				   }
				   else
				   {
					   updatedVal += alltokens1.get(i).data + " ";
				   }
			   }
			   
		   }
		   
		   value = updatedVal;
	
		return value;
	}

	private static String checkForConcat(String value) 
	{
		String val="";
		
		ArrayList<Token> tknLst = FunctionTypeConv.getTokens(value);
		boolean checkifFirstConcat =true;
		
		for(int i = 0 ; i < tknLst.size(); i++)
		{
			if(i+1 < tknLst.size() && tknLst.get(i+1).data.equals("concat") && checkifFirstConcat==true)
			{
				val += " concat(" +tknLst.get(i).data;
				checkifFirstConcat = false;
			}
			else if(i+2 < tknLst.size() && tknLst.get(i).data.equals("concat") && !tknLst.get(i+2).data.equals("concat") && checkifFirstConcat==false)
			{
				val += ","+ tknLst.get(i+1).data+")";
				i=i+1;			
				checkifFirstConcat = true;
			}
			else if(tknLst.get(i).data.equals("concat") && checkifFirstConcat==false)
			{
				val+= ",";			
			}
			else
				val += " " +tknLst.get(i).data;	
		}
		return val;
	}
	
	private static Boolean chkForValueInToken(ArrayList<Token> alltokens1, String chkDate) 
	{
		for(int i = 0 ; i < alltokens1.size(); i++)
		{
			if (alltokens1.get(i).data.equalsIgnoreCase(chkDate))
				return true;
		}
		return false;
	}



	private static boolean checkIfThenElse(String value) 
	{
		ArrayList<Token> alltokens1 = Lexer.interpret(value);
		int count = 0;
		for(int i = 0 ; i < alltokens1.size(); i++)
		{
			if(alltokens1.get(i).type.name().equalsIgnoreCase("DECSEP"))
			{
				count++;
			}
		}
		
		if(count > 1)
		{
			return true;
		}
		
		return false;
	}



	public static boolean checktokenHasLogicalExp(String data) 
	{
		if(data.equalsIgnoreCase("and") || data.equalsIgnoreCase("or")
				||  data.equalsIgnoreCase("not") ||  data.equalsIgnoreCase("xor") || 
				data.equalsIgnoreCase("dsOr") || data.equalsIgnoreCase("dsAnd") || data.equalsIgnoreCase("dsConcat") ||
				data.equalsIgnoreCase("dsNot") || data.equalsIgnoreCase("sysdate") || data.equalsIgnoreCase("SESSSTARTTIME")||
				data.equalsIgnoreCase("dsPipe"))
		{
		      return true;
		}
		return false;	
		
	}
	
	private static String getUpdatedString(String SQLValue) 
	{
		String SQLValFrom = updatedStmts(SQLValue, "From");
		String SQLValFor = updatedStmts(SQLValFrom, "For");
		
		 return SQLValFor;
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
				if (checkVal.equalsIgnoreCase("dsError_For") || checkVal.equalsIgnoreCase("dsError_From"))
					tknData.data = tknData.data.substring(tknData.data.indexOf("_")+1);
					
				tknData.data = tknData.data.toUpperCase();
				
				updVal += " "+tknData.data;
			}
			else
				updVal += " "+ tknData.data;
		}
				
		return updVal.trim();
	}


}