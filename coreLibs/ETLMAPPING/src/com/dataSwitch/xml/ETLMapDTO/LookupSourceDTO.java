
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class LookupSourceDTO {

	public String sqlOverride = "";
	public String sourceFilter = "";
	
	public ArrayList<String> masterElemList = new ArrayList<String>();
	public ArrayList<String> lookupElemList = new ArrayList<String>();
	public ArrayList<String> operatorList = new ArrayList<String>();

	public ArrayList<String> multiCondOperatorList = new ArrayList<String>();
	
	
	


}