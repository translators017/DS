
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.DetailElementDTO;
import com.dataSwitch.xml.ETLMapDTO.JoinerInfo;
import com.dataSwitch.xml.ETLMapDTO.MultiJoinerInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner.JoinCondition;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent.JoinCondition.DetailDataElement;

public class MultiJoinerParser extends MultiJoinerInfo
{
	public static final void extractAllUsedSourceDataElements (Multijoiner multijoiner, List<TransRecExtDTO> recsList, ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{

		for(DetailComponent dc : multijoiner.getDetailComponent())
		{
		for(int d = 0; d < dc.getJoinCondition().size(); d++)
		{
			DetailComponent.JoinCondition joincondition = dc.getJoinCondition().get(d);
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
	}

	public static final void processJoinerTransformation (Multijoiner multijoiner, TransRecExtDTO transRec)
	{
		MultiJoinerParser transInfo = (MultiJoinerParser) transRec.transInfo;
		if (transInfo != null)
			return;

		transInfo = new MultiJoinerParser();
		transInfo.masterCompRef = multijoiner.getMasterComponentRef();
		transRec.transInfo = transInfo;

		for(DetailComponent dc : multijoiner.getDetailComponent())
		{
			DetailElementDTO detailEle = new DetailElementDTO();

			for(int d = 0; d < dc.getJoinCondition().size(); d++)
			{
				detailEle.joinType = dc.getJoinType();
				detailEle.detailCompRef = dc.getComponentRef();
				detailEle.useSortedInput = dc.isUseSortedInput();
				//detailEle.masterElemList = new ArrayList<String>();
				//detailEle.detailElemList = new ArrayList<String>();

				DetailComponent.JoinCondition joincondition = dc.getJoinCondition().get(d);

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
				detailEle.masterElemList.add (srcDataRef + "." + srcDataElementName);
				ColumnDTO inColDTO = Utils.getColumnDTOByNameInTransRec(transRec, srcDataRef, srcDataElementName);
				if ((inColDTO != null) && (!detailEle.masterCols.contains(inColDTO)))
				{
					detailEle.masterCols.add(inColDTO);
				}

				srcDataRef = joincondition.getDetailDataElement().getComponentRef();
				srcDataElementName = joincondition.getDetailDataElement().getDataElementName();
				detailEle.detailElemList.add (srcDataRef + "." + srcDataElementName);
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
				if ((inColDTO != null) && (!detailEle.detailCols.contains(inColDTO)))
				{
					detailEle.detailCols.add(inColDTO);
				}
			}

			transInfo.detailElements.add(detailEle);
		}
	}

}