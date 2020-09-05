
package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource.LookupCondition;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource.LookupCondition.JoinCondition;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource.LookupCondition.JoinCondition.InputDataElement;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource.LookupCondition.JoinCondition.LookupDataElement;

public class Utils 
{
	public static final String getUniqueCompName(String newCompName, ArrayList<String> uniqueCompNamesLst)
	{
		if(newCompName == null)
			return null;
		
		String compName = newCompName.toLowerCase();
		int cnt = 1;
		
		newCompName = compName;
		
		if(uniqueCompNamesLst.size() > 0)
		{
			while(uniqueCompNamesLst.contains(newCompName))
			{
				newCompName = compName + cnt;
				cnt++;
			}
		}
		
		uniqueCompNamesLst.add(newCompName);
		
		newCompName = newCompName.replace("{", "").replace("}", "").replace(".", "_").replace("$", "").trim();
		
		return newCompName;
		
	}

	public static String getUpdatedCompName(String tgtTable) 
	{
		if(tgtTable == null)
			return null;
		
		if(tgtTable.contains("."))
			tgtTable = tgtTable.substring(tgtTable.lastIndexOf(".")+1, tgtTable.length());
		
		tgtTable = tgtTable.replace("{", "").replace("}", "").replace(".", "_").replace("$", "").trim();
		
		
		return tgtTable;
	}
	

	public static String getStructuredVal(String colName) 
	{
		colName = colName.replace("( ", "(").replace(" (", "(").replace(" )", ")")
					.replace("[ ", "[").replace(" [", "[").replace(" ]", "]").replace(" .", ".").replace(". ", ".")
					.replace(", ", ",").replace(" ,",",").replace("  "," ").replace("   "," ").trim();
		
		return colName;
	}
 
}