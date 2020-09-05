
package com.dataSwitch.ds.functionTypeRules;

import java.util.ArrayList;

import com.dataSwitch.ds.functionparser.Token;

public class TransformRuleDTO 
{
	public String ruleType;
	public String convertedRule;

	public ArrayList<ParameterListDTO> paramClass;
	
	public ArrayList<Token> tokenList;
	
	public TransformRuleDTO(String ruleType,String convertedRule ,ArrayList<ParameterListDTO> paramClass,ArrayList<Token> tokenList) 
	{
		this.ruleType = ruleType;//fntype
		this.paramClass = paramClass; 
		this.tokenList = tokenList;
		this.convertedRule = convertedRule;
	}
	
}