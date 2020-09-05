
package com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules;

import java.util.ArrayList;


public class ScriptKeywordTypeMapRules 
{

	public static ArrayList<ScriptTypeRule> teradataKeyWrdRules = null;
	public static ArrayList<ScriptTypeRule> oracleKeyWrdRules = null;
	
	
	public static ArrayList<ScriptTypeRule> teradataKeyWord()
	{
		if(teradataKeyWrdRules!=null)
			return teradataKeyWrdRules;

		teradataKeyWrdRules = new ArrayList<ScriptTypeRule>();
		
		teradataKeyWrdRules.add(new ScriptTypeRule("dsBegin", "tdBlock", "Begin", "End"));
		teradataKeyWrdRules.add(new ScriptTypeRule("dsFor", "tdLoop", "For", "End"));
		teradataKeyWrdRules.add(new ScriptTypeRule("dsIf", "tdLoop", "If", "End"));
		teradataKeyWrdRules.add(new ScriptTypeRule("dsWhile", "tdLoop", "While", "End"));
		teradataKeyWrdRules.add(new ScriptTypeRule("dsRepeat", "tdLoop", "Repeat", "End"));
		teradataKeyWrdRules.add(new ScriptTypeRule("dsLoop", "tdLoop", "Loop", "End"));
		
		return teradataKeyWrdRules;
	}
	
	public static ArrayList<ScriptTypeRule> oracleKeyWord()
	{
		if(oracleKeyWrdRules!=null)
			return oracleKeyWrdRules;
		
		oracleKeyWrdRules = new ArrayList<ScriptTypeRule>();
		
		oracleKeyWrdRules.add(new ScriptTypeRule("dsBegin", "oraBlock", "Begin", "End"));
		oracleKeyWrdRules.add(new ScriptTypeRule("dsFor", "oraLoop", "For", "End"));
		oracleKeyWrdRules.add(new ScriptTypeRule("dsException", "oraException", "Exception", "End"));
		oracleKeyWrdRules.add(new ScriptTypeRule("dsIf", "oraLoop", "If", "End"));
//		oracleKeyWrdRules.add(new ScriptTypeRule("dsLoop", "oraLoop", "Loop", "End"));
		
		return oracleKeyWrdRules;
	}
		
}


