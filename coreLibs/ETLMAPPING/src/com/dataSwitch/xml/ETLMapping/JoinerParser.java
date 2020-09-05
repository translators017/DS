
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.JoinerInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner.JoinCondition;

public class JoinerParser extends JoinerInfo
{
	public static final void extractAllUsedSourceDataElements (Joiner jnr, List<TransRecExtDTO> recsList, ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{

		for(int d = 0; d < jnr.getJoinCondition().size(); d++)
		{
			JoinCondition joincondition = jnr.getJoinCondition().get(d);
			String srcDataRef = joincondition.getMasterDataElement().getComponentRef();
			String srcDataElementName = joincondition.getMasterDataElement().getDataElementName();
			if (srcDataRef.length() <= 0)
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

			srcDataRef = joincondition.getDetailDataElement().getComponentRef();
			srcDataElementName = joincondition.getDetailDataElement().getDataElementName();
			if(srcDataElementName.contains("__a"))
			{
                String nameWithALias[] = srcDataElementName.split("__a");
                srcDataElementName = nameWithALias[0];

			}
			if (srcDataRef.length() <= 0)
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

	public static final void processJoinerTransformation (Joiner jnr, TransRecExtDTO transRec)
	{
		JoinerParser transInfo = (JoinerParser) transRec.transInfo;
		if (transInfo != null)
			return;

		transInfo = new JoinerParser();
		transRec.transInfo = transInfo;

		transInfo.joinType = jnr.getJoinType();
		transInfo.useSortedInput = jnr.isUseSortedInput();
		transInfo.masterElemList = new ArrayList<String>();
		transInfo.detailElemList = new ArrayList<String>();

		for(int d = 0; d < jnr.getJoinCondition().size(); d++)
		{
			JoinCondition joincondition = jnr.getJoinCondition().get(d);

			String srcDataRef = joincondition.getMasterDataElement().getComponentRef();
			String srcDataElementName = joincondition.getMasterDataElement().getDataElementName();

			if (srcDataRef.length() <= 0)
			{
				String elemName = "." + srcDataElementName.toLowerCase();
				for (int k = 0; k < transRec.transColsList.size(); k++)
				{
					String colName = transRec.transColsList.get(k).colStruct.name;
					if (colName.toLowerCase().endsWith(elemName))
					{
						srcDataRef = colName.substring(0, colName.indexOf("."));
						break;
					}
				}
			}
			transInfo.masterElemList.add (srcDataRef + "." + srcDataElementName);
			ColumnDTO inColDTO = Utils.getColumnDTOByNameInTransRec(transRec, srcDataRef, srcDataElementName);
			if ((inColDTO != null) && (!transInfo.masterCols.contains(inColDTO)))
			{
				transInfo.masterCols.add(inColDTO);
			}

			srcDataRef = joincondition.getDetailDataElement().getComponentRef();
			srcDataElementName = joincondition.getDetailDataElement().getDataElementName();
			transInfo.detailElemList.add (srcDataRef + "." + srcDataElementName);
			if (srcDataElementName.contains("__a"))
			{
                String nameWithALias[] = srcDataElementName.split("__a");
                srcDataElementName = nameWithALias[0];

			}

			if (srcDataRef.length() <= 0)
			{
				String elemName = "." + srcDataElementName.toLowerCase();
				for (int k = 0; k < transRec.transColsList.size(); k++)
				{
					String colName = transRec.transColsList.get(k).colStruct.name;
					if (colName.toLowerCase().endsWith(elemName))
					{
						srcDataRef = colName.substring(0, colName.indexOf("."));
						break;
					}
				}
			}
			inColDTO = Utils.getColumnDTOByNameInTransRec(transRec, srcDataRef, srcDataElementName);
			if ((inColDTO != null) && (!transInfo.detailCols.contains(inColDTO)))
			{
				transInfo.detailCols.add(inColDTO);
			}
		}
	}

}