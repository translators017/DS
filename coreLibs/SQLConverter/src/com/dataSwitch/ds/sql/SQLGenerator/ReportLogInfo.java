
package com.dataSwitch.ds.sql.SQLGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReportLogInfo 
{
	public String scriptName = "";
	public String dbType = "";

	public int sqlTotalTransCount = 0;
	public int statementCount = 0;
	public ArrayList<String> totalAttributeTransCount = new ArrayList<String>();
	public int totalTransCount = 0;
	
	public String complexityType = "";
	public String otherReportDtls = null;
	
	public ArrayList<String> manualChanges = new ArrayList<String>();
	
	public List<String> stmtsAdd = new ArrayList<String>();
	
}