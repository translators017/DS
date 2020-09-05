
package com.dataSwitch.ds.functionTypeRules;

public class Parameter {

	private String name;

	private String required;
	
	public Parameter(String name,String required) {

		this.name = name;//fntype
		this.required = required; 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}
	
	
	
}