
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ColumnStruct;
import com.dataSwitch.xml.ETLMapDTO.OtherTransInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.OtherTransformation;

public class OtherTransParser {

	
	public final static void processOtherTransformation (OtherTransformation other, TransRecExtDTO transRec, DataMapping dataMapping,HashMap<String, String> srcTgtVariablePortMap)
	{
		
		OtherTransInfo transInfo = (OtherTransInfo) transRec.transInfo;
		if (transInfo != null)
			return;

		transInfo = new OtherTransInfo();
		transRec.transInfo = transInfo;
		transInfo.transformType = other.getTransformationType();
		transInfo.transformName = other.getName();
		
		transInfo.inputCols = new ArrayList<ColumnDTO>();
		transInfo.outputCols = new ArrayList<ColumnDTO>();
		
		for (int i = 0; i < other.getInputDataElements().getInputDataElement().size(); i++) 
		{
			String inCompName = transRec.compName;
			String dataElementName = other.getInputDataElements().getInputDataElement().get(i).getName();
			ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec(transRec, inCompName, dataElementName);
			
			if(colDTO == null)
			{
				Iterator<Entry<String, String>> it = srcTgtVariablePortMap.entrySet().iterator();
				while (it.hasNext()) 
				{
					HashMap.Entry<String, String> pair = (HashMap.Entry<String, String>) it.next();
					String inColumn = pair.getKey();
					if(pair.getValue().equalsIgnoreCase(transRec.compName+"."+dataElementName))
					{
						colDTO = new ColumnDTO();
						colDTO.colStruct = new ColumnStruct();
						colDTO.colStruct.name = pair.getValue();
						
						String [] str = pair.getKey().split("\\.");
							inCompName = str[0];
							dataElementName = str[1];
							ColumnDTO colDTO1 = Utils.getColumnDTOByNameInTransRec(transRec, inCompName, dataElementName);
						colDTO.srcColumn = colDTO1;
					}
				} //End of while
			}
			transInfo.inputCols.add(colDTO);
		}

		return;
	}

	public static JSONObject getTransDtlsForOtherTrans(String otherTransProps) throws ParseException 
	{
		  
		Object obj = new JSONParser().parse(otherTransProps);
		JSONObject joProps = (JSONObject) obj;
		
		return joProps; 
		
	}
	
}