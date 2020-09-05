
package com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules;

import java.util.ArrayList;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;

public class ScriptKeywordsForDbs 
{
	public static ArrayList<ScriptTypeRule>  getRespectiveKeyWordForDbs(int currentDbId)
	{
		if(currentDbId == ToolTypeConstants.TERADATA)
			return ScriptKeywordTypeMapRules.teradataKeyWord();
		
		else if(currentDbId == ToolTypeConstants.ORACLE)
			return ScriptKeywordTypeMapRules.oracleKeyWord();
		
		return null;
	}
}