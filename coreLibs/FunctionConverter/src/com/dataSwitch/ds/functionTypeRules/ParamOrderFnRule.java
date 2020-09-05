
package com.dataSwitch.ds.functionTypeRules;

public class ParamOrderFnRule {
	
	private String genFnType;
	private String toolFnType;

	public ParamOrderFnRule(String genFnType,String toolFnType) {
		this.genFnType = genFnType;
		this.toolFnType = toolFnType;
	}
	public String getGenFnType() {
		return genFnType;
	}

	public void setGenFnType(String genDataType) {
		this.genFnType = genDataType;
	}

	public String getToolFnType() {
		return toolFnType;
	}

	public void setDbDataType(String dbDataType) {
		this.toolFnType = dbDataType;
	}

}