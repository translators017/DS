
package com.dataSwitch.ds.functionTypeRules;

import java.util.List;

public class TransformationRule {

	
	private String ruleName;

	private String ruleDescription;
	
	private List<Parameter> parameter;
	
	public TransformationRule(String ruleName,String ruleDescription,List<Parameter> parameter) {

		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription; 
		this.parameter = parameter;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	
	public List<Parameter> getParameter() {
		return parameter;
	}

	public void setParameter(List<Parameter> parameter) {
		this.parameter = parameter;
	}

}
