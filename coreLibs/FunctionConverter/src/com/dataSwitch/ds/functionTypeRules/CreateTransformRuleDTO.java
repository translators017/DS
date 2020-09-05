
package com.dataSwitch.ds.functionTypeRules;

import java.util.ArrayList;
import java.util.HashMap;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionparser.Lexer;
import com.dataSwitch.ds.functionparser.Token;

public class CreateTransformRuleDTO 
{

	public static TransformRuleDTO createTransformationRule (int toolID , String expressionRule)
	{
		ArrayList<ParameterListDTO> paramClass = new ArrayList<ParameterListDTO>();
		ParameterListDTO parameters = new ParameterListDTO("", "", "");
		TransformRuleDTO transRule = new TransformRuleDTO(expressionRule,"", paramClass, null);
		
		ArrayList<Token> alltokens = Lexer.interpret(expressionRule);
		
		ArrayList<ExprNode> expressionNodes = FunctionTypeConv.getExprNode(expressionRule);
		
		transRule.tokenList = alltokens;
		
		String convertedFunc = FunctionTypeConv.getCurrentFunctions(expressionRule, toolID, ToolTypeConstants.DS_TOOL);
		transRule.convertedRule = convertedFunc;
		
		if(expressionNodes.size() <=2 && convertedFunc.contains("("))
		{
			String dsRule = convertedFunc.substring(0,convertedFunc.indexOf("("));
			
			String transRuleName = getRuleName(dsRule);
			transRule.ruleType = transRuleName;
			
			boolean openParaCheck = false;
			
			if(!transRuleName.equalsIgnoreCase("General - Expression"))
			{
				int tokenCount = 0;
				for(Token tok : alltokens)
				{			
					if(openParaCheck)
					{
						if(tok.type.name().equals("DECSEP"))
							parameters = new ParameterListDTO("", "", "");
						else if(tok.type.name().equals("CLOSEPAREN"))
							break;
						else
						{							
							if(tok.type.name().equals("VARWITHDOT"))
							{
								
								if(parameters.value.isEmpty())
								{
									parameters.name = getParamName(tokenCount , transRuleName , toolID);
									tokenCount++;
									parameters.type = "DE";
									parameters.value += tok.data+" ";	
									transRule.paramClass.add(parameters);
								}
								else
									parameters.value += tok.data+" ";
							}
							else if(tok.type.name().equals("STRCONSTANTS"))
							{
								
								if(parameters.value.isEmpty())
								{
									parameters.name = getParamName(tokenCount , transRuleName , toolID);
									tokenCount++;
									parameters.type = "SC";
									parameters.value += tok.data+" ";	
									transRule.paramClass.add(parameters);
								}
								else
									parameters.value += tok.data+" ";
							}
							else if(tok.type.name().equals("INTEGER") || tok.type.name().equals("INTEGERWITHDECIMAL"))
							{
								if(parameters.value.isEmpty())
								{
									parameters.name = getParamName(tokenCount , transRuleName , toolID);
									tokenCount++;
									parameters.type = "NC";
									parameters.value += tok.data+" ";	
									transRule.paramClass.add(parameters);
								}
								else
									parameters.value += tok.data+" ";
							}
							else
							{
								if(parameters.value.isEmpty())
								{
									parameters.name = getParamName(tokenCount , transRuleName , toolID);
									tokenCount++;
									parameters.type = "Exp";
									parameters.value += tok.data+" ";
									transRule.paramClass.add(parameters);
								}
								else
								{
									parameters.type = "Exp";
									parameters.value += tok.data+" ";
								}
							}
						}	
					}
					else if(tok.type.name().equals("OPENPAREN"))
					{
						openParaCheck = true;
					}
				}
			}
			else
			{
				transRule = checkForGeneralExpDE(alltokens,transRule,parameters,convertedFunc);
			}		
		}
		else
		{
			transRule = checkForGeneralExpDE(alltokens,transRule,parameters,convertedFunc);	
		}
		
		return transRule;
	}

	private static String getParamName(int tokenCount, String transRuleName, int toolID) 
	{
		
		if(transRuleName.equalsIgnoreCase("String - Convert to Lowercase") || 
			transRuleName.equalsIgnoreCase("String - Convert to Uppercase")	|| 
			transRuleName.equalsIgnoreCase ("String - Length") || transRuleName.equalsIgnoreCase ("String - Concatenation") ||
			transRuleName.equalsIgnoreCase ("String - Convert to InitCaps") || transRuleName.equalsIgnoreCase ("String - Trim Leading Spaces")||
			transRuleName.equalsIgnoreCase ("String - Trim Trailing Spaces") || transRuleName.equalsIgnoreCase("String - Trim Leading and Trailing Spaces")||
			transRuleName.equalsIgnoreCase("String - Reverse") || transRuleName.equalsIgnoreCase("Numeric - Absolute") || transRuleName.equalsIgnoreCase("Numeric - Ceil") ||
			transRuleName.equalsIgnoreCase("Numeric - Floor") || transRuleName.equalsIgnoreCase("Numeric - Cumulative Total") ||
			transRuleName.equalsIgnoreCase("Conversion - String to Integer") || transRuleName.equalsIgnoreCase("Conversion - String to Float")
			|| transRuleName.equalsIgnoreCase("Conversion - Numeric to String") || transRuleName.equalsIgnoreCase("Check - NULL Values") ||
			 transRuleName.equalsIgnoreCase("Check - Numeric Values") || transRuleName.equalsIgnoreCase("Special - Surrogate Key Generation"))
		{
			return "var";	
		}
		else if(transRuleName.equalsIgnoreCase("String - Substring"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount ==1)
				return "Starting Position";
			else if(tokenCount ==2)
				return "Number of Characters";
			else
				return "var";
		}
		else if(transRuleName.equalsIgnoreCase("String - Left Padding") || transRuleName.equalsIgnoreCase("String - Right Padding"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount ==1)
				return "Number of Characters";
			else if(tokenCount ==2)
				return "Padding String";
			else
				return "var";
		}
		else if(transRuleName.equalsIgnoreCase("String - Search String"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount ==1)
				return "Search Value";
			else if(tokenCount ==2)
				return "Starting Position";
			else if(tokenCount ==4)
				return "Number of Occurrence";	
			else
				return "var";
		}
		else if(transRuleName.equalsIgnoreCase("String - Replace String"))
		{
			if(toolID == ToolTypeConstants.INFORMATICA_PC)
			{
				if(tokenCount == 0)
					return "Case Sensitivity Flag";
				else if(tokenCount ==1)
					return "var";
				else if(tokenCount ==2)
					return "Old String";
				else if(tokenCount ==3)
					return "New String";	
				else
					return "var";		
			}
			else
			{
				if(tokenCount == 0)
					return "var";
				else if(tokenCount ==1)
					return "Old String";
				else if(tokenCount ==2)
					return "New String";
				else
					return "var";				
			}		
		}
		else if(transRuleName.equalsIgnoreCase("Numeric - Moving Sum") || transRuleName.equalsIgnoreCase("Numeric - Moving Average"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount ==1)
				return "Rowset Count";
			else
				return "var";				
		}
		else if(transRuleName.equalsIgnoreCase("Numeric - Round") || transRuleName.equalsIgnoreCase("Numeric - Truncate"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount ==1)
				return "Precision";
			else
				return "var";
		}
		else if(transRuleName.equalsIgnoreCase("Date/Time - Add to Date"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount ==1)
				return "Date Part";
			else if(tokenCount ==2)
				return "Expression";
			else
				return "var";
		}
		else if(transRuleName.equalsIgnoreCase("Date/Time - Difference between Dates"))
		{
			if(tokenCount == 0)
				return "var1";
			else if(tokenCount == 1)
				return "var2";
			else if(tokenCount == 2)
				return "Date Part";
			else
				return "var";	
		}
		else if(transRuleName.equalsIgnoreCase("Date/Time - Get Date/Time Part"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount == 1)
				return "Date Part";
			else
				return "var";			
		}
		else if(transRuleName.equalsIgnoreCase("Date/Time - Truncate") || transRuleName.equalsIgnoreCase("Date/Time - Round"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount == 1)
				return "Date Part";
			else
				return "var";	
		}
		else if(transRuleName.equalsIgnoreCase("Conversion - String to Date") || transRuleName.equalsIgnoreCase("Conversion - Date to String") ||
				transRuleName.equalsIgnoreCase("Conversion - Date/Time to String") || transRuleName.equalsIgnoreCase("Conversion - Date/Time to Integer") ||
				transRuleName.equalsIgnoreCase("Check - Date Values") )
		{			
			if(tokenCount == 0)
				return "var";
			else if(tokenCount == 1)
				return "Date Format";
			else
				return "var";	
		}
		else if(transRuleName.equalsIgnoreCase("Check - Condition"))
		{
			if(tokenCount == 0)
				return "Condition";
			else if(tokenCount == 1)
				return "Return Value If True";
			else if(tokenCount == 2)
				return "Return Value If False";
			else
				return "var";	
		}
		else if(transRuleName.equalsIgnoreCase("Special - Decode"))
		{
			if(tokenCount == 0)
				return "var";
			else if(tokenCount == 1)
				return "Code Value";
			else if(tokenCount == 2)
				return "Decoded Value";
			else
				return "var";	
		}
				
		return "var";
	}

	private static TransformRuleDTO checkForGeneralExpDE(ArrayList<Token> alltokens, TransformRuleDTO transRule,
			ParameterListDTO parameters, String convertedFunc) 
	{
		
		transRule.ruleType = "General - Expression";
		if(alltokens.size() ==1)
		{
			for(Token tok : alltokens)
			{
				if(tok.type.name().equals("VARWITHDOT"))
				{
					parameters.name = "Expression";
					parameters.type = "DE";
					parameters.value = tok.data;	
					transRule.paramClass.add(parameters);
				}
				else if (tok.type.name().equals("VARWITHDOLLAR")&& tok.data.startsWith("$"))
				{
					parameters.name = "Expression";
					parameters.type = "DE";
					parameters.value = tok.data;	
					transRule.paramClass.add(parameters);
				}
				else if(tok.type.name().equals("STRCONSTANTS"))
				{
					parameters.name = "Expression";
					parameters.type = "SC";
					parameters.value = tok.data;	
					transRule.paramClass.add(parameters);
				}
				else if(tok.type.name().equals("INTEGER") || tok.type.name().equals("INTEGERWITHDECIMAL"))
				{
					parameters.name = "Expression";
					parameters.type = "NC";
					parameters.value = tok.data;	
					transRule.paramClass.add(parameters);
				}
				else
				{
					parameters.name = "Expression";
					parameters.type = "Exp";
					parameters.value = tok.data;
					transRule.paramClass.add(parameters);
				}
			}				
		}
		else
		{
			parameters.name = "Expression";
			parameters.type = "Exp";
			parameters.value = convertedFunc; 
			transRule.paramClass.add(parameters);
		}

	
		return transRule;
	}

	private static String getRuleName(String dsRule) 
	{
		if(dsRule.equalsIgnoreCase("dsIIf"))
			return "Check - Condition";
		else if(dsRule.equalsIgnoreCase("dsLower"))
			return "String - Convert to Lowercase";
		else if(dsRule.equalsIgnoreCase("dsUpper"))
			return "String - Convert to Uppercase";
		else if(dsRule.equalsIgnoreCase("dsLength"))
			return "String - Length";
		else if(dsRule.equalsIgnoreCase("dsConcat"))
			return "String - Concatenation";
		else if(dsRule.equalsIgnoreCase("dsInitCap"))
			return "String - Convert to InitCaps";
		else if(dsRule.equalsIgnoreCase("dsSubString"))
			return "String - Substring";
		else if(dsRule.equalsIgnoreCase("dsTrim"))
			return "String - Trim Leading and Trailing Spaces";
		else if(dsRule.equalsIgnoreCase("dsLtrim"))
			return "String - Trim Leading Spaces";
		else if(dsRule.equalsIgnoreCase("dsRtrim"))
			return "String - Trim Trailing Spaces";
		else if(dsRule.equalsIgnoreCase("dsRightPad"))
			return "String - Right Padding";
		else if(dsRule.equalsIgnoreCase("dsLeftPad"))
			return "String - Left Padding";
		else if(dsRule.equalsIgnoreCase("dsSearch"))
			return "String - Search String";
		else if(dsRule.equalsIgnoreCase("dsReplace"))
			return "String - Replace String";
		else if(dsRule.equalsIgnoreCase("dsStrReverse"))
			return "String - Reverse";
		else if(dsRule.equalsIgnoreCase("dsAbsolute"))
			return "Numeric - Absolute";
		else if(dsRule.equalsIgnoreCase("dsNumCeil"))
			return "Numeric - Ceil";
		else if(dsRule.equalsIgnoreCase("dsNumFloor"))
			return "Numeric - Floor";
		else if(dsRule.equalsIgnoreCase("dsNumCume"))
			return "Numeric - Cumulative Total";
		else if(dsRule.equalsIgnoreCase("dsNumMovingSum"))
			return "Numeric - Moving Sum";
		else if(dsRule.equalsIgnoreCase("dsMovningAvg"))
			return "Numeric - Moving Average";
		else if(dsRule.equalsIgnoreCase("dsNumRound"))
			return "Numeric - Round";
		else if(dsRule.equalsIgnoreCase("dsNumTrunc"))
			return "Numeric - Truncate";
		else if(dsRule.equalsIgnoreCase("dsAddToDate"))
			return "Date/Time - Add to Date";
		else if(dsRule.equalsIgnoreCase("dsGetDate"))
			return "Date/Time - Get Date/Time Part";
		else if(dsRule.equalsIgnoreCase("dsDiff"))
			return "Date/Time - Difference between Dates";
		else if(dsRule.equalsIgnoreCase("dsDateTrunc"))
			return "Date/Time - Truncate";
		else if(dsRule.equalsIgnoreCase("dsRound"))
			return "Date/Time - Round";
		else if(dsRule.equalsIgnoreCase("dsStrToDate"))
			return "Conversion - String to Date";
		else if(dsRule.equalsIgnoreCase("dsDateToStr"))
			return "Conversion - Date to String";
		else if(dsRule.equalsIgnoreCase("dsStrToInt"))
			return "Conversion - String to Integer";
		else if(dsRule.equalsIgnoreCase("dsStrToDec"))
			return "Conversion - String to Decimal";
		else if(dsRule.equalsIgnoreCase("dsStrToFloat"))
			return "Conversion - String to Float";
		else if(dsRule.equalsIgnoreCase("dsTimestampToString"))
			return "Conversion - Date/Time to String";
		else if(dsRule.equalsIgnoreCase("dsDateToInt"))
			return "Conversion - Date/Time to Integer";
		else if(dsRule.equalsIgnoreCase("dsCheckNull"))
			return "Check - NULL Values";
		else if(dsRule.equalsIgnoreCase("dsCheckDate"))
			return "Check - Date Values";
		else if(dsRule.equalsIgnoreCase("dsCheckNum"))
			return "Check - Numeric Values";
		else if(dsRule.equalsIgnoreCase("dsTimeStamp"))
			return "Audit / Generated - System Timestamp";
		else if(dsRule.equalsIgnoreCase("dsDate"))
			return "Audit / Generated - System Date";
		else if(dsRule.equalsIgnoreCase("dsSurrogateKey"))
			return "Special - Surrogate Key Generation";
		else if(dsRule.equalsIgnoreCase("dsSum"))
			return "Aggregate - Sum";
		else if(dsRule.equalsIgnoreCase("dsAvg"))
			return "Aggregate - Average";
		else if(dsRule.equalsIgnoreCase("dsCount"))
			return "Aggregate - Count";
		else if(dsRule.equalsIgnoreCase("dsCountDistinct"))
			return "Aggregate - CountDistinct";
		else if(dsRule.equalsIgnoreCase("dsFirstValue"))
			return "Aggregate - First";
		else if(dsRule.equalsIgnoreCase("dsGroupBy"))
			return "Aggregate - GroupBy";
		else if(dsRule.equalsIgnoreCase("dsLast"))
			return "Aggregate - Last";
		else if(dsRule.equalsIgnoreCase("dsMax"))
			return "Aggregate - Maximum";
		else if(dsRule.equalsIgnoreCase("dsMin"))
			return "Aggregate - Minimum";
		else if(dsRule.equalsIgnoreCase("dsVariance"))
			return "Aggregate - Variance";
		else
			return "General - Expression";
	}
	
	public static String getDsRules(String transRuleName) 
	{
		// TODO Auto-generated method stub

		if(transRuleName.equalsIgnoreCase("Check - Condition"))
			return "dsIIf";
		else if(transRuleName.equalsIgnoreCase("String - Convert to Lowercase"))
			return "dsLower";
		else if(transRuleName.equalsIgnoreCase("String - Convert to Uppercase"))
			return "dsUpper";
		else if(transRuleName.equalsIgnoreCase("String - Length"))
			return "dsLength";
		else if(transRuleName.equalsIgnoreCase("String - Concatenation"))
			return "dsConcat";
		else if(transRuleName.equalsIgnoreCase("String - Convert to InitCaps"))
			return "dsInitCap";
		else if(transRuleName.equalsIgnoreCase("String - Substring"))
			return "dsSubString";
		else if(transRuleName.equalsIgnoreCase("String - Trim Leading and Trailing Spaces"))
			return "dsTrim";
		else if(transRuleName.equalsIgnoreCase("String - Trim Leading Spaces"))
			return "dsLtrim";
		else if(transRuleName.equalsIgnoreCase("String - Trim Trailing Spaces"))
			return "dsRtrim";
		else if(transRuleName.equalsIgnoreCase("String - Right Padding"))
			return "dsRightPad";
		else if(transRuleName.equalsIgnoreCase("String - Left Padding"))
			return "dsLeftPad";
		else if(transRuleName.equalsIgnoreCase("String - Search String"))
			return "dsSearch";
		else if(transRuleName.equalsIgnoreCase("String - Replace String"))
			return "dsReplace";
		else if(transRuleName.equalsIgnoreCase("String - Reverse"))
			return "dsStrReverse";
		else if(transRuleName.equalsIgnoreCase("Numeric - Absolute"))
			return "dsAbsolute";
		else if(transRuleName.equalsIgnoreCase("Numeric - Ceil"))
			return "dsNumCeil";
		else if(transRuleName.equalsIgnoreCase("Numeric - Floor"))
			return "dsNumFloor";
		else if(transRuleName.equalsIgnoreCase("Numeric - Cumulative Total"))
			return "dsNumCume";
		else if(transRuleName.equalsIgnoreCase("Numeric - Moving Sum"))
			return "dsNumMovingSum";
		else if(transRuleName.equalsIgnoreCase("Numeric - Moving Average"))
			return "dsMovningAvg";
		else if(transRuleName.equalsIgnoreCase("Numeric - Round"))
			return "dsNumRound";
		else if(transRuleName.equalsIgnoreCase("Numeric - Truncate"))
			return "dsNumTrunc";
		else if(transRuleName.equalsIgnoreCase("Date/Time - Add to Date"))
			return "dsAddToDate";
		else if(transRuleName.equalsIgnoreCase("Date/Time - Difference between Dates"))
			return "dsDiff";
		else if(transRuleName.equalsIgnoreCase("Date/Time - Truncate"))
			return "dsDateTrunc";
		else if(transRuleName.equalsIgnoreCase("Date/Time - Round"))
			return "dsRound";
		else if(transRuleName.equalsIgnoreCase("Conversion - String to Date"))
			return "dsStrToDate";
		else if(transRuleName.equalsIgnoreCase("Conversion - Date to String"))
			return "dsDateToStr";
		else if(transRuleName.equalsIgnoreCase("Conversion - String to Integer"))
			return "dsStrToInt";
		else if(transRuleName.equalsIgnoreCase("Conversion - String to Decimal"))
			return "dsStrToDec";
		else if(transRuleName.equalsIgnoreCase("Conversion - String to Float"))
			return "dsStrToFloat";
		else if(transRuleName.equalsIgnoreCase("Conversion - Date/Time to String"))
			return "dsTimestampToString";
		else if(transRuleName.equalsIgnoreCase("Conversion - Date/Time to Integer"))
			return "dsDateToInt";
		else if(transRuleName.equalsIgnoreCase("Check - NULL Values"))
			return "dsCheckNull";
		else if(transRuleName.equalsIgnoreCase("Check - Date Values"))
			return "dsCheckDate";
		else if(transRuleName.equalsIgnoreCase("Check - Numeric Values"))
			return "dsCheckNum";
		else if(transRuleName.equalsIgnoreCase("Audit / Generated - System Timestamp"))
			return "dsTimeStamp";
		else if(transRuleName.equalsIgnoreCase("Audit / Generated - System Date"))
			return "dsDate";
		else if(transRuleName.equalsIgnoreCase("Special - Surrogate Key Generation"))
			return "dsSurrogateKey";
		else
			return "General - Expression";
	
		
	}
	
}