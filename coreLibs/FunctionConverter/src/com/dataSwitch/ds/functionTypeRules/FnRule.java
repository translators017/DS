
package com.dataSwitch.ds.functionTypeRules;

import com.dataSwitch.ds.functionTypeRules.FnMapRules.dsFuunctionType;

public class FnRule {

	private dsFuunctionType genFnType;

	private String toolFnType;
	
	private String applicability;
	
	private dsFuunctionType recommendedType;
	
	private String isParamOrderSame;
	
	private char functionOrVariableFlag = 'F';
	
	private String return_datatype;
	
	private String paramList;
	
	public FnRule(dsFuunctionType genFnType,String toolFnType,dsFuunctionType recommendedType,String applicability,String paramOrderChange) {

		this.genFnType = genFnType;//fntype
		this.toolFnType = toolFnType; 
		this.recommendedType = recommendedType;
		this.applicability = applicability;
		this.isParamOrderSame = paramOrderChange;
		this.functionOrVariableFlag = 'F';
		this.return_datatype = "String";
		this.paramList = "#var";
	}
	
	public FnRule(dsFuunctionType genFnType,String toolFnType,dsFuunctionType recommendedType,String applicability,String paramOrderChange, char functionOrVariableFlag) {

		this.genFnType = genFnType;//fntype
		this.toolFnType = toolFnType; 
		this.recommendedType = recommendedType;
		this.applicability = applicability;
		this.isParamOrderSame = paramOrderChange;
		this.functionOrVariableFlag = functionOrVariableFlag;
		this.return_datatype = "String";
		this.paramList = "#var";
	}
	
	public FnRule(dsFuunctionType genFnType,String toolFnType,dsFuunctionType recommendedType,String applicability,String paramOrderChange, char functionOrVariableFlag, String return_datatype, String paramList) {

		this.genFnType = genFnType;//fntype
		this.toolFnType = toolFnType; 
		this.recommendedType = recommendedType;
		this.applicability = applicability;
		this.isParamOrderSame = paramOrderChange;
		this.functionOrVariableFlag = functionOrVariableFlag;
		this.return_datatype = return_datatype;
		this.paramList = paramList;
	}

	public dsFuunctionType getGenFnType() {
		return genFnType;
	}

	public void setGenFnType(dsFuunctionType genDataType) {
		this.genFnType = genDataType;
	}

	public String getToolFnType() {
		return toolFnType;
	}

	public void setDbDataType(String dbDataType) {
		this.toolFnType = dbDataType;
	}
	
	public dsFuunctionType getRecommendedType() {
		return recommendedType;
	}

	public void setRecommendedType(dsFuunctionType recommendedType) {
		this.recommendedType = recommendedType;
	}

	public String getApplicability() {
		return applicability;
	}

	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}
	
	public String getIsParamOrderSame() {
		return isParamOrderSame;
	}

	public void setIsParamOrderSame(String isParamOrderSame) {
		this.isParamOrderSame = isParamOrderSame;
	}
	

}