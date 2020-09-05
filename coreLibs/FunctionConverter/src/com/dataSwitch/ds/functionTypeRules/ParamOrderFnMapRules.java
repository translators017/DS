
package com.dataSwitch.ds.functionTypeRules;

import java.util.ArrayList;
import java.util.List;

public class ParamOrderFnMapRules {

	private static ArrayList<ParamOrderFnRule> datastageFnTypeRules = null;
	private static ArrayList<ParamOrderFnRule> ssisFnTypeRules = null;
	private static ArrayList<ParamOrderFnRule> apacheBeamFnTypeRules = null;
	private static ArrayList<ParamOrderFnRule> pySparkRules = null;
	private static ArrayList<ParamOrderFnRule> talendFnTypeRules = null;
	private static ArrayList<ParamOrderFnRule> apacheBeamPythonFnTypRules = null;
	public static List<ParamOrderFnRule> getDatastageRules()
	{
		if(datastageFnTypeRules!=null)
			return datastageFnTypeRules;
		datastageFnTypeRules = new ArrayList<ParamOrderFnRule>();

		datastageFnTypeRules.add(new ParamOrderFnRule("AGG_COUNT(#var#,[#Group By Columns#])","RecCount()"));
		datastageFnTypeRules.add(new ParamOrderFnRule("DT_ADDTODATE(#var#,#Date Part#,#number#)","DaysSinceFromDate2(#var#,#number#)"));
		datastageFnTypeRules.add(new ParamOrderFnRule("DT_DIFF(#var1#,#var2#,#Date Part#)","DaysSinceFromDate2(#var1#,#var2#)"));
		datastageFnTypeRules.add(new ParamOrderFnRule("STR_RIGHTPAD(#var#,#Number of Characters#,#Padding String#)","PadString(#var#,#Padding String#,#Number of Characters#)"));
		datastageFnTypeRules.add(new ParamOrderFnRule("STR_SUBSTR(#var#,#Starting Position#,#Number of Characters#)","#var# [#Starting Position#, #Number of Characters#]"));//(differ)

		return datastageFnTypeRules;
	}

	public static List<ParamOrderFnRule> getSsisRules()
	{
		if(ssisFnTypeRules!=null)
			return ssisFnTypeRules;

		ssisFnTypeRules = new ArrayList<ParamOrderFnRule>();

		ssisFnTypeRules.add(new ParamOrderFnRule("DT_ADDTODATE(#var#,#Date Part#,#number#)","DATEADD(#Date Part#, #number#, #var#)"));

		return ssisFnTypeRules;
	}


	public static List<ParamOrderFnRule> getApacheBeamRules()
	{
		if(apacheBeamFnTypeRules!=null)
			return apacheBeamFnTypeRules;

		apacheBeamFnTypeRules = new ArrayList<ParamOrderFnRule>();


		apacheBeamFnTypeRules.add(new ParamOrderFnRule("CONV_DATETOSTR(#var#,#Date Format#)","cast(#var# as VARCHAR)"));
		apacheBeamFnTypeRules.add(new ParamOrderFnRule("CONV_NUMTODEC(#format#,#var#)","cast(#var# as DECIMAL)"));
		apacheBeamFnTypeRules.add(new ParamOrderFnRule("CONV_STRTOFLOAT(#var#)","cast(#var# as FLOAT)"));
		apacheBeamFnTypeRules.add(new ParamOrderFnRule("DT_ADDTODATE(#var#,#Date Part#,#number#)","TIMESTAMPADD(#Date Part#, #number#, #var#)"));
		apacheBeamFnTypeRules.add(new ParamOrderFnRule("DT_DIFF(#var1#,#var2#,#Date Part#)","TIMESTAMPDIFF(#Date Part#,#var1#,#var2#)"));

		return apacheBeamFnTypeRules;
	}

	public static List<ParamOrderFnRule> getTalendRules()
	{
		if(talendFnTypeRules!=null)
			return talendFnTypeRules;

		talendFnTypeRules = new ArrayList<ParamOrderFnRule>();

		talendFnTypeRules.add(new ParamOrderFnRule("CONV_DATETOSTR(#var#,#Date Format#)","#var#.toString()"));
		talendFnTypeRules.add(new ParamOrderFnRule("CONV_NUMTOSTR(#var#,[#Number Format#])","#var#.toString()"));

		return talendFnTypeRules;
	}
	public static List<ParamOrderFnRule> getPySparkRules()
	{
		if(pySparkRules!=null)
			return pySparkRules;

		pySparkRules = new ArrayList<ParamOrderFnRule>();

		pySparkRules.add(new ParamOrderFnRule("DT_ADDTODATE(#var#,#Date Part#,#number#)","date_add(#var#, #number#)"));

		return pySparkRules;
	}

	public static List<ParamOrderFnRule> getApacheBeamPythonRules()
	{
		if(apacheBeamPythonFnTypRules!=null)
			return apacheBeamPythonFnTypRules;

		apacheBeamPythonFnTypRules = new ArrayList<ParamOrderFnRule>();

		apacheBeamPythonFnTypRules.add(new ParamOrderFnRule("STR_INITCAP(#var#)","#var#.title()"));
		apacheBeamPythonFnTypRules.add(new ParamOrderFnRule("STR_LOWER(#var#)","#var#.lower()"));
		apacheBeamPythonFnTypRules.add(new ParamOrderFnRule("STR_LTRIM(#var#)","#var#.lstrip()"));
		apacheBeamPythonFnTypRules.add(new ParamOrderFnRule("STR_RTRIM(#var#)","#var#.rstrip()"));
		apacheBeamPythonFnTypRules.add(new ParamOrderFnRule("STR_TRIM(#var#)","#var#.strip()"));
		apacheBeamPythonFnTypRules.add(new ParamOrderFnRule("STR_UPPER(#var#)","#var#.upper()"));

		return apacheBeamPythonFnTypRules;
	}
}