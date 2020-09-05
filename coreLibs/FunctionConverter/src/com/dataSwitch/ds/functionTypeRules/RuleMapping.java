
package com.dataSwitch.ds.functionTypeRules;

public class RuleMapping {

	private TransformationRule transformationRule;

	private String expression;
	
	private String transformationType;
	
	public RuleMapping(TransformationRule transformationRule,String expression,String transformationType) {

		this.transformationRule = transformationRule;
		this.expression = expression; 
		this.transformationType = transformationType;
	}

	public TransformationRule getTransformationRule() {
		return transformationRule;
	}

	public void setTransformationRule(TransformationRule transformationRule) {
		this.transformationRule = transformationRule;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getTransformationType() {
		return transformationType;
	}

	public void setTransformationType(String transformationType) {
		this.transformationType = transformationType;
	}

}