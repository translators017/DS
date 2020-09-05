
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.FilterInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;

public class FilterParser extends FilterInfo
{
	public final static void extractAllUsedSourceDataElementsFromCondition (String condition, List<TransRecExtDTO> recsList, ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{
		RouterParser.extractAllUsedSourceDataElementsFromCondition (condition, recsList, srcCompNames, srcCompsColumnList);
	}

	public final static void processFilterTransformation (TransRecExtDTO transRec, DataTransformation dt)
	{
		FilterParser transInfo = (FilterParser) transRec.transInfo;
		if (transInfo != null)
			return;

		transInfo = new FilterParser();
		transRec.transInfo = transInfo;

		transInfo.filterCond = dt.getFilter().getFilterCondition();

		String[] elemList = RouterParser.getDataElements(transInfo.filterCond);

		for (int i = 0; i < elemList.length; i++)
		{
			int nIndex = elemList[i].indexOf(".");
			String compName = "";
			String dataElementName = elemList[i];

			if (nIndex != -1)
			{
				compName = elemList[i].substring(0, nIndex);
				dataElementName = elemList[i].substring(nIndex + 1);
			}
			compName = compName.trim();
			dataElementName = dataElementName.trim();

			if (compName.length() <= 0)
			{
				String elemName = "." + dataElementName.toLowerCase();
				for (int k = 0; k < transRec.transColsList.size(); k++)
				{
					String colName = transRec.transColsList.get(k).colStruct.name;
					if (colName.toLowerCase().endsWith(elemName))
					{
						compName = colName.substring(0, colName.indexOf("."));
						break;
					}
				}
			}

			ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec(transRec, compName, dataElementName);
			if (colDTO != null)
				transInfo.conditionCols.add(colDTO);
		}
	}

}