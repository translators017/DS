
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ReportLogInfo 
{
	public String jobName = "";
	public String jobType = "";

	public int totalEntityTransCount = 0;
	public int totalAttributeTransCount = 0;
	public int totalTransCount = 0;
	public int totalsqlTranCount = 0;
	
	public String complexityType = "";
	public String otherReportDtls = null;
	//public int manualChangesCount = 0;
	public HashMap<String, String> transTypeList = new HashMap<String, String>();
	public HashMap<String, ArrayList<String>> manualChanges = new HashMap<String, ArrayList<String>>();
	public ArrayList<String>codesnippet=new ArrayList<String>();
	//public float automationPercentage = 0;
	
	public ArrayList<String> getTransManualChangesList (String transName, String transType, ReportLogInfo reportObj)
	{
		ArrayList<String> list = null;	
		for (Entry<String, ArrayList<String>> entry : reportObj.manualChanges.entrySet()) 
		{	 
			if(entry.getKey().equalsIgnoreCase(transName))
			{
				list=entry.getValue();
			}
			
		}
		if (list == null)
		{
			list = new ArrayList<String>();
			manualChanges.put(transName, list);
			transTypeList.put(transName, transType);
		}
		return list;
	}
}