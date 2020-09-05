
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.SorterInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Sorter;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Sorter.SorterKey;

public class SorterParser extends SorterInfo
{
	public static final void extractAllUsedSourceDataElements (Sorter sorter, List<TransRecExtDTO> recsList, 
							ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{

		for (int i = 0; i < sorter.getSorterKey().size(); i++)
		{
			SorterKey key = sorter.getSorterKey().get(i);

			String srcDataRef = key.getKeyComponentRef();
			String srcDataElementName = key.getSortKeyElementRef();

			int nIndex = srcDataElementName.indexOf(".");
			if (nIndex != -1)
			{
				if (srcDataRef.trim().length() <= 0)
					srcDataRef = srcDataElementName.substring(0, nIndex);
				srcDataElementName = srcDataElementName.substring(nIndex + 1);
			}

			if (srcDataRef.trim().length() <= 0)
			{
				String elemName = "." + srcDataElementName.toLowerCase();
				for (int i1 = 0; i1 < srcCompNames.size(); i1++)
				{
					ArrayList<ColumnDTO> srcColList = srcCompsColumnList.get(srcCompNames.get(i1));

					for (int k = 0; k < srcColList.size(); k++)
					{
						String colName = srcColList.get(k).colStruct.name;
						if (colName.toLowerCase().endsWith(elemName))
						{
							srcDataRef = colName.substring(0, colName.indexOf("."));
							break;
						}
					}
				}
			}
			MapPorts.cloneAndAddSourceDataElement (srcDataRef, srcDataElementName,
												recsList, srcCompNames, srcCompsColumnList);
		}

	}

	public static final void processSorterRule (TransRecExtDTO transRec, List<TransRecExtDTO> recsList, DataTransformation dt)
	{
		SorterParser transInfo = (SorterParser) transRec.transInfo;
		if (transInfo != null)
			return;

		transInfo = new SorterParser();
		transRec.transInfo = transInfo;

		for (int i = 0; i < dt.getSorter().getSorterKey().size(); i++)
		{
			SorterKey key = dt.getSorter().getSorterKey().get(i);

			String srcDataRef = key.getKeyComponentRef();
			String srcDataElementName = key.getSortKeyElementRef();

			int nIndex = srcDataElementName.indexOf(".");
			if (nIndex != -1)
			{
				if (srcDataRef.trim().length() <= 0)
					srcDataRef = srcDataElementName.substring(0, nIndex);
				srcDataRef = srcDataElementName.substring(nIndex + 1);
			}

			ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec(transRec, srcDataRef, srcDataElementName);
			transInfo.keyCols.add(colDTO);

			String sortOrder = key.getSortOrder();
			if (sortOrder == null)
				sortOrder = "ASCENDING";

			transInfo.sortOrdersList.add(sortOrder);
		}
	}
}