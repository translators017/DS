
package com.dataSwitch.ds.functionTypeRules;

import java.util.ArrayList;
import java.util.List;

public class CodeGenRules {

	//private static ArrayList<RuleMapping> codeGenRuleList = null;
	
	
	public static void main (String[] args)
	{
		String toolType = "Data Stage";
		List<RuleMapping> codeGenRuleList = getCodeGenerationRules(toolType);
		
		for (int i = 0; i < codeGenRuleList.size(); i++)
		{
			System.out.println("rules --"+codeGenRuleList.get(i).getTransformationType()+":::"+codeGenRuleList.get(i).getExpression()+":::"+codeGenRuleList.get(i).getTransformationRule().getRuleName());
		
			if (codeGenRuleList.get(i).getTransformationRule().getParameter() == null)
				continue;
			for (int j = 0; j < codeGenRuleList.get(i).getTransformationRule().getParameter().size(); j++) 
			{
				System.out.println("parameter -- "+codeGenRuleList.get(i).getTransformationRule().getParameter().get(j).getName()+":::"+codeGenRuleList.get(i).getTransformationRule().getParameter().get(j).getRequired());
			}
		}
	}
	
	
	public static List<RuleMapping> getCodeGenerationRules(String toolType)
	{
		ArrayList<RuleMapping> codeGenRuleList = new ArrayList<RuleMapping>();
		
		List<Parameter> singleParamList = new ArrayList<Parameter>();
		Parameter dataVariableParam = new Parameter("var", "true");
		singleParamList.add(dataVariableParam);
		
		//Aggregate functions
		
		List<Parameter> paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		Parameter param = new Parameter("Group By", "false");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Average", "Average of #var#",paramList) ,"AVG(#var#)","Aggregator"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Count", "Count of #var#", paramList) ,"COUNT(#var#)","Aggregator"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Average", "Average of #var#",paramList) ,"MEAN(#var#)","Aggregator"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Count", "Count of #var#", paramList) ,"RecCount()","Aggregator"));
			
		}
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - First", "First of #var#", paramList) ,"FIRST(#var#)","Aggregator"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Last", "Last of #var#", paramList) ,"LAST(#var#)","Aggregator"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Maximum", "Maximum of #var#", paramList) ,"MAX(#var#)","Aggregator"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Minimum", "Minimum of #var#", paramList) ,"MIN(#var#)","Aggregator"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Sum", "Sum of #var#", paramList) ,"SUM(#var#)","Aggregator"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - Variance", "Variance of #var#", paramList) ,"VARIANCE(#var#)","Aggregator"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregate - GroupBy", "Group by #var#", paramList) ,"","Aggregator"));
		
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Expression", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Aggregator - Expression", "", paramList) ,"#Expression#","Aggregator"));
		
		//Expression functions
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Return ASCII Code", "ASCII code of #var#",singleParamList) ,"CHRCODE(#var#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Return ASCII Character", "ASCII Character of #var#", singleParamList) ,"CHR(#var#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Convert to Lowercase", "Lowercase of #var#", singleParamList) ,"LOWER(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Convert to Uppercase", "Uppercase of #var#", singleParamList) ,"UPPER(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Length", "Length of #var#", singleParamList) ,"LENGTH(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Concatenation", "", paramList) ,"CONCAT(#var1# , #var2#)","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Convert to Lowercase", "Lowercase of #var#", singleParamList) ,"DownCase(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Convert to Uppercase", "Uppercase of #var#", singleParamList) ,"UpCase(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Length", "Length of #var#", singleParamList) ,"Len(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Concatenation", "", paramList) ,"#var1# : #var2#","Expression"));
			
		}
		codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Convert to InitCaps", "InitCaps of #var#", singleParamList) ,"INITCAP(#var#)","Expression"));
		
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		paramList.add(new Parameter("Starting Position", "true"));
		paramList.add(new Parameter("Number of Characters", "false"));
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Substring", "Substring of #var# from #Starting Position# till #Number of Characters# characters ", paramList) ,"SUBSTR(#var#,#Starting Position#,#Number of Characters#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Trim Leading Spaces", "Trim leading spaces of #var#", singleParamList) ,"LTRIM(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Trim Trailing Spaces", "Trim trailing spaces of #var#", singleParamList) ,"RTRIM(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Trim Leading and Trailing Spaces", "Trim leading and trailing spaces of #var#", singleParamList) ,"LTRIM(RTRIM(#var#))","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Substring", "Substring of #var# from #Starting Position# till #Number of Characters# characters ", paramList) ,"#var#[#Starting Position#,#Number of Characters#]","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Trim Leading Spaces", "Trim leading spaces of #var#", singleParamList) ,"TrimF(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Trim Trailing Spaces", "Trim trailing spaces of #var#", singleParamList) ,"TRIMB(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Trim Leading and Trailing Spaces", "Trim leading and trailing spaces of #var#", singleParamList) ,"Trim(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Trim Leading and Trailing Spaces", "Trim leading and trailing spaces of #var#", singleParamList) ,"TrimLeadingTrailing(#var#))","Expression"));
			
		}
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Number of Characters", "true");
		paramList.add(param);
		param = new Parameter("Padding String", "false");
		paramList.add(param);
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Left Padding", "Left pad #var# with string #Padding String# and length #Number of Characters#", paramList) ,"LPAD(#var#,#Number of Characters#,#Padding String#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Right Padding", "Right pad #var# with string #Padding String# and length #Number of Characters#", paramList) ,"RPAD(#var#,#Number of Characters#,#Padding String#)","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Left Padding", "Left pad #var# with string #Padding String# and length #Number of Characters#", paramList) ,"PadString(#var#,#Number of Characters#,#Padding String#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Left Padding", "Left pad #var# with string #Padding String# and length #Number of Characters#", paramList) ,"Fmt(#var#,#Number of Characters#,#Padding String#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Right Padding", "Right pad #var# with string #Padding String# and length #Number of Characters#", paramList) ,"PadString(#var#,#Number of Characters#,#Padding String#)","Expression"));
			
		}
		/*paramList = new ArrayList<Parameter>();
		param = new Parameter("Number of Characters", "true");
		paramList.add(param);
		param = new Parameter("Padding String", "false");
		paramList.add(param);*/
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Search Value", "true");
		paramList.add(param);
		param = new Parameter("Starting Position", "false");
		paramList.add(param);
		param = new Parameter("Number of Occurrence", "false");
		paramList.add(param);
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Search String", "Search #var# for #Search Value# from position #Starting Position# for #Number of Occurrence# occurence.", paramList) ,"INSTR(#var#,#Search Value#,#Starting Position#,#Number of Occurrence#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Old String", "true");
		paramList.add(param);
		param = new Parameter("New String", "true");
		paramList.add(param);
		param = new Parameter("Case Sensitivity Flag", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Replace String", "Replace #Old String# in #var# with #New String#. Case Sensitivity Flag is #Case Sensitivity Flag#", paramList) ,"REPLACESTR(#Case Sensitivity Flag#,#var#,#Old String#,#New String#)","Expression"));
		else
			codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Replace String", "Replace #Old String# in #var# with #New String#. Case Sensitivity Flag is #Case Sensitivity Flag#", paramList) ,"EREPLACE(#var#, #Old String#, #New String#)","Expression"));
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("String - Reverse", "Reverse of #var#", singleParamList) ,"REVERSE(#var#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Absolute", "Absolute of #var#", singleParamList) ,"ABS(#var#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Ceil", "Ceil of #var#", singleParamList) ,"CEIL(#var#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Floor", "Floor of #var#", singleParamList) ,"FLOOR(#var#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Cumulative Total", "Cumulative Total of #var#", singleParamList) ,"CUME(#var#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Rowset Count", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Moving Sum", "Sum (row-by-row) of #var# for #Rowset Count# rows", paramList) ,"MOVINGSUM(#var#,#Rowset Count#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Moving Average", "Average (row-by-row) of #var# for #Rowset Count# rows", paramList) ,"MOVINGAVG(#var#,#Rowset Count#)","Expression"));
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Moving Sum", "Sum (row-by-row) of #var# for #Rowset Count# rows", paramList) ,"NotAvailableInDS(#var#,#Rowset Count#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Moving Average", "Average (row-by-row) of #var# for #Rowset Count# rows", paramList) ,"NotAvailableInDS(#var#,#Rowset Count#)","Expression"));
		}
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Precision", "false");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Round", "Round #var# to #Precision# digits", paramList) ,"ROUND(#var# ,#Precision#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Truncate", "Truncate #var# to #Precision# digits", paramList) ,"TRUNC(#var# ,#Precision#)","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Round", "Round #var# to #Precision# digits", paramList) ,"DecimalToDecimal(#var# ,\"round_inf\")","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Numeric - Truncate", "Truncate #var# to #Precision# digits", paramList) ,"DecimalToDecimal(#var# ,\"trunc_zero\")","Expression"));
			
		}
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Part", "true");
		paramList.add(param);
		param = new Parameter("Expression", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Add to Date", "Add #Expression# #Date Part# to #var#", paramList) ,"ADD_TO_DATE(#var#,#Date Part#,#Expression#)","Expression"));
		else
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Add to Date", "Add #Expression# #Date Part# to #var#", paramList) ,"DateFromDaysSince2(#var#,#Date Part#,#Expression#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Part", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Difference between Dates", "Get length of time between #var1# and #var2# in date part #Date Part#", paramList) ,"DATE_DIFF(#var1#,#var2#,#Date Part#)","Expression"));
		else
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Difference between Dates", "Get length of time between #var1# and #var2# in date part #Date Part#", paramList) ,"DaysSinceFromDate2(#var1#,#var2#,#Date Part#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Part", "true");
		paramList.add(param);
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Get Date/Time Part", "Get date part #Date Part# from #var#", paramList) ,"GET_DATE_PART(#var#,#Date Part#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Part", "false");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Truncate", "Truncate date part #Date Part# from #var#", paramList) ,"TRUNC(#var#,#Date Part#)","Expression"));
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Truncate", "Truncate date part #Date Part# from #var#", paramList) ,"SecondsFromTime(#var#,#Date Part#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Truncate", "Truncate date part #Date Part# from #var#", paramList) ,"HoursFromTime(#var#,#Date Part#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Truncate", "Truncate date part #Date Part# from #var#", paramList) ,"SecondsSinceFromTimestamp(#var#,#Date Part#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Truncate", "Truncate date part #Date Part# from #var#", paramList) ,"SecondsSinceFromTimestamp2 (#var#,#Date Part#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Truncate", "Truncate date part #Date Part# from #var#", paramList) ,"TimestampFromSecondsSince (#var#,#Date Part#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Truncate", "Truncate date part #Date Part# from #var#", paramList) ,"TimestampFromTime2 (#var#,#Date Part#)","Expression"));
			
		}
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Part", "false");
		paramList.add(param);
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Date/Time - Round", "Round date part #Date Part# from #var#", paramList) ,"ROUND(#var# ,#Date Part#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Format", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Date", "Convert #var# to date with date format #Date Format#", paramList) ,"TO_DATE(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date to String", "Convert #var# to string with date format #Date Format#", paramList) ,"TO_CHAR(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Integer", "Convert #var# to integer", singleParamList) ,"TO_INTEGER(#var#)","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Date", "Convert #var# to date with date format #Date Format#", paramList) ,"StringToDate(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date to String", "Convert #var# to string with date format #Date Format#", paramList) ,"DateToString(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Integer", "Convert #var# to integer", singleParamList) ,"AsInteger(#var#)","Expression"));
			
		}
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Precision", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Decimal", "Convert #var# to decimal", paramList) ,"TO_DECIMAL(#var# ,#Precision#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Float", "Convert #var# to float", singleParamList) ,"TO_FLOAT(#var#)","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Decimal", "Convert #var# to decimal", paramList) ,"StringToDecimal(#var# ,#Precision#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Float", "Convert #var# to float", singleParamList) ,"AsFloat(#var#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - String to Float", "Convert #var# to float", singleParamList) ,"DecimalToString(%number%,[%\"[fix_zero][,suppress_zero]\"%]","Expression"));
			
		}
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Numeric to String", "Convert #var# to string", singleParamList) ,"TO_CHAR(#var#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Format", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date/Time to String", "Convert #var# to string with date format #Date Format#", paramList) ,"TO_CHAR(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date/Time to Integer", "Convert #var# to integer with date format #Date Format#", paramList) ,"TO_INTEGER(TO_CHAR(#var#,#Date Format#))","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date/Time to String", "Convert #var# to string with date format #Date Format#", paramList) ,"DateToString(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date/Time to String", "Convert #var# to string with date format #Date Format#", paramList) ,"TimeToString(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date/Time to Integer", "Convert #var# to integer with date format #Date Format#", paramList) ,"AsInteger(DateToDecimal(#var#,#Date Format#))","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date/Time to Integer", "Convert #var# to integer with date format #Date Format#", paramList) ,"DateToDecimal[#var#,[#Date Format#]]","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Conversion - Date/Time to Integer", "Convert #var# to integer with date format #Date Format#", paramList) ,"TimeToDecimal[#var#,[#Date Format#]]","Expression"));
			
		}
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Key", "true");
		paramList.add(param);
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Encoding - AES Encryption", "Encoding - AES Encryption on #var# with key #Key#", paramList) ,"AES_ENCRYPT(#var#,#Key#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Encoding - AES Decryption", "Encoding - AES Decryption on #var# with key #Key#", paramList) ,"AES_DECRYPT(#var# ,#Key#)","Expression"));
		
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Encoding - MD5 Checksum", "Encoding - MD5 Checksum on #var#", singleParamList) ,"MD5(#var#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Encoding - CRC32", "Encoding - CRC32 on #var#", singleParamList) ,"CRC32(#var#)","Expression"));
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Check - NULL Values", "Check null values for #var#", singleParamList) ,"ISNULL(#var#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Date Format", "true");
		paramList.add(param);
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Check - Date Values", "Check for date values in #var# for date format #Date Format#", paramList) ,"IS_DATE(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Check - Numeric Values", "Check if #var# is number", singleParamList) ,"IS_NUMBER(#var#)","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Check - Date Values", "Check for date values in #var# for date format #Date Format#", paramList) ,"AsInteger(#var#,#Date Format#)","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Check - Numeric Values", "Check if #var# is number", singleParamList) ,"AsInteger(#var#)","Expression"));
			
		}
		
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Condition", "true");
		paramList.add(param);
		param = new Parameter("Return Value If True", "true");
		paramList.add(param);
		param = new Parameter("Return Value If False", "false");
		paramList.add(param);
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Check - Condition", "if #Condition# then #Return Value If True# or #Return Value If False#", paramList) ,"IIF(#Condition#,#Return Value If True#,#Return Value If False#)","Expression"));
			
		}
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Check - Condition", "if #Condition# then #Return Value If True# or #Return Value If False#", paramList) ,"IF(#Condition#,#Return Value If True#,#Return Value If False#)","Expression"));
			
		}
		
		paramList = new ArrayList<Parameter>();
		paramList.add(dataVariableParam);
		param = new Parameter("Code Value", "true");
		paramList.add(param);
		param = new Parameter("Decoded Value", "true");
		paramList.add(param);
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Special - Decode", "Search for #var# and return #Decoded Value# if value is #Code Value#", paramList) ,"DECODE(#var#,#Code Value#,#Decoded Value#)","Expression"));
		
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Constant Value", "true");
		paramList.add(param);
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("General - Constant Value", "Constant value - #Constant Value#", paramList) ,"#Constant Value#","Expression"));
		
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Expression", "true");
		paramList.add(param);
		
		codeGenRuleList.add(new RuleMapping(new TransformationRule("General - Expression", "General expression value - #Expression#", paramList) ,"#Expression#","Expression"));
		/*paramList = new ArrayList<Parameter>();
		param = new Parameter("DescriptiveTransRule", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("General - DescriptiveTransRule", "Descriptive Trans Rule - #DescriptiveTransRule#", paramList) ,"#DescriptiveTransRule#","Expression"));
		*/
		
		if (toolType.equalsIgnoreCase("Informatica"))
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Audit / Generated - System Timestamp", "SYSTIMESTAMP", null) ,"SYSTIMESTAMP","Expression"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Audit / Generated - System Date", "SYSDATE", null) ,"SYSDATE","Expression"));
			
		}
		else
		{
			paramList = new ArrayList<Parameter>();
			param = new Parameter("Timestamp Format", "false");
			paramList.add(param);
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Audit / Generated - System Timestamp", "SYSTIMESTAMP in format #Timestamp Format#", paramList) ,"SYSTIMESTAMP(#Timestamp Format#)","Expression"));
			paramList = new ArrayList<Parameter>();
			param = new Parameter("Date Format", "false");
			paramList.add(param);
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Audit / Generated - System Date", "SYSDATE in format #Date Format#", paramList) ,"SYSDATE(#Date Format#)","Expression"));
			
		}
		
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Return Data Element", "true");
		paramList.add(param);
		param = new Parameter("Anchoring Component Name", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Special - Lookup", "", paramList) ,"LOOKUP","Data Transformation"));
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Transformation Name", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Data Flow - Joiner", "", paramList) ,"JOINER","Data Transformation"));
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Sort Order", "true");
		paramList.add(param);
		param = new Parameter("Use Distinct", "false");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Data Flow - Sorting", "", paramList) ,"SORTER","Sorter"));
		
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Starting Value", "true");
		paramList.add(param);
		if (toolType.equalsIgnoreCase("Informatica"))
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Special - Surrogate Key Generation", "Surrogate key generation with starting value #Starting Value#", paramList) ,"#Starting Value#","Sequence Generator"));
		else
		{
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Special - Surrogate Key Generation", "Surrogate key generation with starting value #Starting Value#", paramList) ,"SurrogateKeyGen(#var#)","Sequence Generator"));
			codeGenRuleList.add(new RuleMapping(new TransformationRule("Special - Surrogate Key Generation", "Surrogate key generation with starting value #Starting Value#", paramList) ,"SEQUENCE GENERATOR","Sequence Generator"));
			
		}
		
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Condition", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Data Flow - Filter", "", paramList) ,"FILTER","Filter"));
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Rank Port", "true");
		paramList.add(param);
		param = new Parameter("Rank Number", "true");
		paramList.add(param);
		param = new Parameter("Top / Bottom Flag", "true");
		paramList.add(param);
		param = new Parameter("Group By", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Special - Rank Index", "Select #Top / Bottom Flag# #Rank Number# based on #Rank Port# group by #Group By#", paramList) ,"RANK","Rank"));
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Transformation Name", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Database - Stored Procedure", "", paramList) ,"STORED PROCEDURE","Data Transformation"));
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Transformation Name", "true");
		paramList.add(param);
		param = new Parameter("Input Data Element Name", "true");
		paramList.add(param);
		param = new Parameter("Output Data Element Name", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Data Flow - Reusable Transformation", "", paramList) ,"MAPPLET","Reusable Transformation"));
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Transformation Name", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Special - Union", "Union", paramList) ,"UNION","Data Transformation"));
		paramList = new ArrayList<Parameter>();
		param = new Parameter("Transformation Name", "true");
		paramList.add(param);
		param = new Parameter("Output Group Name", "true");
		paramList.add(param);
		codeGenRuleList.add(new RuleMapping(new TransformationRule("Data Flow - Conditional Grouping", "", paramList) ,"ROUTER","Data Transformation"));
		
		
		return codeGenRuleList;
	}
	
	
}