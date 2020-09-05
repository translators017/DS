
package com.dataSwitch.ds.ScriptTranspiler.ScriptMapRules;

public class ScriptTypeRule
{

	private String genKeywordType;

	private String dbKeyWordType;

	private String startKeyWord;
	
	private String EndKeyWord;


	public ScriptTypeRule(String genKeywordType1, String dbKeyWordType1, String startKeyWord1, String EndKeyWord1) 
	{
		genKeywordType = genKeywordType1;
		dbKeyWordType = dbKeyWordType1;
		startKeyWord = startKeyWord1;
		EndKeyWord = EndKeyWord1;
	}

	public String getGenKeywordType() {
		return genKeywordType;
	}

	public void setGenKeywordType(String genKeywordType) {
		this.genKeywordType = genKeywordType;
	}

	public String getDbKeyWordType() {
		return dbKeyWordType;
	}

	public void setDbKeyWordType(String dbKeyWordType) {
		this.dbKeyWordType = dbKeyWordType;
	}

	public String getStartKeyWord() {
		return startKeyWord;
	}

	public void setStartKeyWord(String startKeyWord) {
		this.startKeyWord = startKeyWord;
	}

	public String getEndKeyWord() {
		return EndKeyWord;
	}

	public void setEndKeyWord(String endKeyWord) {
		EndKeyWord = endKeyWord;
	}

	public String getgenKeywordType() {
		return genKeywordType;
	}

	public void setgenKeywordType(String genKeywordType) {
		this.genKeywordType = genKeywordType;
	}

	public String getDbDataType() {
		return startKeyWord;
	}

	public void setDbDataType(String dbDataType) {
		this.EndKeyWord = dbDataType;
	}

	

}