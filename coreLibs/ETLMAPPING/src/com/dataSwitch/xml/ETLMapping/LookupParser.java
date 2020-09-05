
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ColumnStruct;
import com.dataSwitch.xml.ETLMapDTO.LookupInfo;
import com.dataSwitch.xml.ETLMapDTO.LookupSourceDTO;
import com.dataSwitch.xml.connectorspecs.InputDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource.LookupCondition.JoinCondition;

public class LookupParser extends LookupInfo
{
	public final static void extractAllUsedSourceDataElements (Lookup lkp, List<TransRecExtDTO> recsList, ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{
		List<JoinCondition> joinList;
		JoinCondition cond;
		String lookupSrcDataRef = null;

		for (int i = 0; i < lkp.getLookupSource().size(); i++)
		{
			//String srcDataRef = lkp.getLookupSource().get(i).getSourceDatasetRef();
			String srcDataRef = lkp.getLookupSource().get(i).getLkpSourceComponentRef();

			lookupSrcDataRef = srcDataRef;

			joinList = lkp.getLookupSource().get(i).getLookupCondition().getJoinCondition();

			for (int j = 0; j < joinList.size(); j++)
			{
				cond = joinList.get(j);

				String lkpElementName = cond.getLookupDataElement().getLookupDataElementRef();

				MapPorts.cloneAndAddSourceDataElement (srcDataRef, lkpElementName,
													recsList, srcCompNames, srcCompsColumnList);

				String inCompName = cond.getInputDataElement().getInterimComponentRef();
				String inElementName = cond.getInputDataElement().getInterimDataElementRef();

				int nIndex = inElementName.indexOf(".");
				if (nIndex != -1)
				{
					if (inCompName.trim().length() <= 0)
						inCompName = inElementName.substring(0, nIndex);
					inElementName = inElementName.substring(nIndex + 1);
				}

				if (inCompName.trim().length() <= 0)
				{
					String elemName = "." + inElementName.toLowerCase();
					for (int i1 = 0; i1 < srcCompNames.size(); i1++)
					{
						ArrayList<ColumnDTO> srcColList = srcCompsColumnList.get(srcCompNames.get(i1));

						for (int k = 0; k < srcColList.size(); k++)
						{
							String colName = srcColList.get(k).colStruct.name;
							if (colName.toLowerCase().endsWith(elemName))
							{
								inCompName = colName.substring(0, colName.indexOf("."));
								break;
							}
						}
					}
				}
				MapPorts.cloneAndAddSourceDataElement (inCompName, inElementName,
													recsList, srcCompNames, srcCompsColumnList);
			}
		}

		if (lookupSrcDataRef == null)
			return;

		for (int i = 0; i < lkp.getReturnDataElement().size(); i++)
		{
			String srcDataElementName = lkp.getReturnDataElement().get(i).getDataElementName();

			MapPorts.cloneAndAddSourceDataElement (lookupSrcDataRef, srcDataElementName,
															recsList, srcCompNames, srcCompsColumnList);
		}

		return;
	}

	public final static void processLookupTransformation (Lookup lkp, TransRecExtDTO transRec, DataMapping dataMapping)
	{
		String lookupSrcDataRef = null;

		LookupInfo transInfo = (LookupInfo) transRec.transInfo;
		if (transInfo != null)
			return;

		transInfo = new LookupInfo();
		transRec.transInfo = transInfo;

		transInfo.returnDataList = new ArrayList<String>();
		
		transInfo.lookupType = lkp.getLookupType();
		transInfo.cachingType = lkp.getCachingType();
		transInfo.multiMatchPolicy = lkp.getMultipleMatchPolicy();
		transInfo.isResuable = lkp.isIsReusable();
		
		for (int i = 0; i < lkp.getLookupSource().size(); i++)
		{
			LookupSourceDTO lkpSrcDTO = new LookupSourceDTO();
			//String srcDataRef = lkp.getLookupSource().get(i).getSourceDatasetRef();
			String srcDataRef = lkp.getLookupSource().get(i).getLkpSourceComponentRef();

			if(lkp.getLookupSource().get(i).getLookupOverrideSQL() != null)
			{
				lkpSrcDTO.sqlOverride = lkp.getLookupSource().get(i).getLookupOverrideSQL();
			}
			if(lkp.getLookupSource().get(i).getLookupSourceFilterSQL() != null)
			{
				lkpSrcDTO.sourceFilter = lkp.getLookupSource().get(i).getLookupSourceFilterSQL();
			}
			
			lookupSrcDataRef = srcDataRef;
			List<JoinCondition> joinList = lkp.getLookupSource().get(i).getLookupCondition().getJoinCondition();

			for (int j = 0; j < joinList.size(); j++)
			{
				JoinCondition cond = joinList.get(j);

				String inCompName = cond.getInputDataElement().getInterimComponentRef();
				String dataElementName = cond.getInputDataElement().getInterimDataElementRef();
				lkpSrcDTO.masterElemList.add (dataElementName);

				int nIndex = dataElementName.indexOf(".");
				if (nIndex != -1)
				{
					if (inCompName.trim().length() <= 0)
						inCompName = dataElementName.substring(0, nIndex);
					dataElementName = dataElementName.substring(nIndex + 1);
				}

				ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec(transRec, inCompName, dataElementName);
				transInfo.masterCols.add(colDTO);

				dataElementName = cond.getLookupDataElement().getLookupDataElementRef();
				lkpSrcDTO.lookupElemList.add (srcDataRef + "." + dataElementName);
				lkpSrcDTO.operatorList.add(cond.getOperator());
				lkpSrcDTO.multiCondOperatorList.add(cond.getMultipleConditionOperator());
				colDTO = Utils.getColumnDTOByNameInTransRec(transRec, srcDataRef, dataElementName);
				transInfo.lookUpCols.add(colDTO);
			}
			transInfo.lkpSrcList.add(lkpSrcDTO);
		}

		if (lookupSrcDataRef == null)
			return;

		for (int i = 0; i < lkp.getReturnDataElement().size(); i++)
		{
			String dataElementName = lkp.getReturnDataElement().get(i).getDataElementName();

			transInfo.returnDataList.add (lookupSrcDataRef + "." + dataElementName);
			ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec(transRec, lookupSrcDataRef, dataElementName);
			
			if(colDTO == null)
			{
				colDTO = new ColumnDTO();
				colDTO.colStruct = new ColumnStruct();
				colDTO.colStruct.name = dataElementName;
				colDTO.colStruct.dataType = "string";
				colDTO.colStruct.length = 10;
				colDTO.colStruct.precision = 10;
				colDTO.colStruct.Scale = 0;
				colDTO.isBeingUsed = 'Y';
				
			}
			
			transInfo.returnCols.add(colDTO);
		}

		// For master Columns of Unconnected lookup
		for (int i = 0; (i < dataMapping.getTargetDataElements().getTargetDataElement().size()); i++)
		{
			TargetDataElementType tgtDataElem = dataMapping.getTargetDataElements().getTargetDataElement().get(i);

			for (int k = 0; (k < tgtDataElem.getTransformationRule().size()); k++)
		    {
		    	TransformationRule rule = tgtDataElem.getTransformationRule().get(k);
		    	
		    	List<String> inputDataElements = Utils.getInputDataElements(rule);
		    	
				if (inputDataElements == null || inputDataElements.isEmpty())
					continue;
				if (!rule.getRuleName().contains("Lookup"))
					continue;
				if (!rule.getParameter().get(0).getValue().equalsIgnoreCase(transRec.compName))
					continue;
				for (int j = 0; (j < inputDataElements.size()); j++)
				{
					String src = inputDataElements.get(j);

					String inputCompRef = Utils.getCompNamefromVar(src);
					String inputElemRef = Utils.getElemNamefromVar(src);
					
					ColumnDTO inColDTO = Utils.getColumnDTOByNameInTransRec(transRec, inputCompRef,  inputElemRef);

					if (inColDTO != null)
						transInfo.masterCols.add(inColDTO);
				}
		    }
		}

		return;
	}
	
	public final static void processLookupUsedColumns (List<TransRecExtDTO> recsList, Lookup lkp, TransRecExtDTO transRec, DataMapping dataMapping)
	{
		for (int i = 0; i < lkp.getLookupSource().size(); i++)
		{
			List<JoinCondition> joinList = lkp.getLookupSource().get(i).getLookupCondition().getJoinCondition();

			for (int j1 = 0; j1 < joinList.size(); j1++)
			{
				JoinCondition cond = joinList.get(j1);
				
				String inCompName = cond.getInputDataElement().getInterimComponentRef();
				String dataElementName = cond.getInputDataElement().getInterimDataElementRef();
			
				TransRecExtDTO tgtRecExt = Utils.getTransRecDTOByName (recsList, inCompName);
				
				
				ColumnDTO tgtColDTO = Utils.getColumnDTOByNameInTransRec(transRec, inCompName, dataElementName);
				
				if(tgtColDTO != null)
				{
					MapPorts.identifyAndUpdateUsedPorts (tgtColDTO);
					tgtColDTO.isBeingUsed = 'Y';
					
				}
			}
		}
	}
	
	
	
	
	
}