
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;
import com.dataSwitch.xml.ETLMapDTO.TransRuleDTO;
import com.dataSwitch.xml.ETLMapDTO.UnionInfo;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SetOperation.InputGroups.InputGroup;

public class UnionParser extends UnionInfo
{
	public static final void extractAllUsedSourceDataElementsFromCondition (String condition, List<TransRecExtDTO> recsList, ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{
		return;
	}

	public static final void processUnionTransformation (TransRecExtDTO transRec, DataTransformation dt)
	{
		UnionInfo transInfo = (UnionInfo) transRec.transInfo;

		if (transInfo == null)
		{
			transInfo = new UnionInfo();
		}

		transRec.transInfo = transInfo;

		transInfo.operation = dt.getSetOperation().getOperation();
		for(int d = 0; d < dt.getSetOperation().getInputGroups().getInputGroup().size(); d++)
		{
			InputGroup inputGrp = dt.getSetOperation().getInputGroups().getInputGroup().get(d);

			transInfo.inputGroup.add(inputGrp.getPrevComponentRef() + "." + inputGrp.getName());
		}
	}

	public static final void propagateInputElementsForUnionTransformations (List<TransRecExtDTO> recsList, DataMapping dataMapping,
			HashMap<String, String> srcTgtVariablePortMap, int codeGenToolId)
	{

		TransRecExtDTO transRec;
		String colName;

		ArrayList<TransRecExtDTO> unionRecsList = new ArrayList<TransRecExtDTO>();

		// Sort Unions Based on SeqNumber and process them in dependency order
		for( int i = 0; i < recsList.size(); i++)
		{
			transRec = recsList.get(i);
			if (!transRec.transType.equalsIgnoreCase("Union Transformation"))
				continue;

			int i1 = 0;
			for (i1 = 0; i1 < unionRecsList.size(); i1++ )
			{
				if (transRec.seqNo <= unionRecsList.get(i1).seqNo)
					break;
			}
			unionRecsList.add(i1, transRec);

		}

		for( int i = 0; i < dataMapping.getTargetDataElements().getTargetDataElement().size(); i++)
		{
			TargetDataElementType tgtDataElem = dataMapping.getTargetDataElements().getTargetDataElement().get(i);

			if (tgtDataElem.getTransformationRule() == null)
				continue;

			for (int l = 0; l < tgtDataElem.getTransformationRule().size(); l++)
			{
				TransformationRule rule = tgtDataElem.getTransformationRule().get(l);
				if (!rule.getRuleName().equalsIgnoreCase("Special - Union"))
					continue;

				transRec = Utils.getTransRecDTOByName (unionRecsList, rule.getComponentName());
				if (transRec == null)
				{
					System.out.println ("Invalid Union Trans Rule - Component Name - ***" + rule.getComponentName() + "***");
					continue;
				}

				List<String> inputElemList = Utils.getInputDataElements(rule);
				
				UnionInfo unionInfo = (UnionInfo)transRec.transInfo;
				TransRuleDTO transRule = null;
				List<ColumnDTO> srcColsList;
				ColumnDTO inColDTO;

				for (int j = 0; j < inputElemList.size(); j++)
				{
					String elemName = inputElemList.get(j);

					for (int k = 0; k < unionInfo.colDTOList.size(); k++)
					{
						srcColsList = unionInfo.colDTOList.get(k).transRule.srcColumns;

						if (srcColsList != null)
						{
							for (int k1 = 0; k1 < srcColsList.size(); k1++)
							{
								if (srcColsList.get(k1) == null)
									continue;

								if (srcColsList.get(k1).colStruct.name.equalsIgnoreCase(elemName))
								{
									transRule = unionInfo.colDTOList.get(k).transRule;
									break;
								}
							}
						}


						if (transRule != null)
							break;
					}
					if (transRule != null)
						break;
				}

				for (int j = 0; j < inputElemList.size(); j++)
				{
					String elemFullName = inputElemList.get(j);

					for (int k1 = 0; k1 < transRec.inRecsList.size(); k1++)
					{
						inColDTO = getInputSourceElement (Utils.getCompNamefromVar(elemFullName), Utils.getCompNamefromVar(elemFullName),
								elemFullName, transRec.inRecsList.get(k1).prevOutRec, true, codeGenToolId);

						if (inColDTO != null)
							addUnionInputElement(transRule, inColDTO);

					}
				}
			}
		}

		for( int i = 0; i < unionRecsList.size(); i++)
		{
			transRec = unionRecsList.get(i);
			ArrayList<ColumnDTO> transColList = ((UnionInfo)transRec.transInfo).colDTOList;

			for (int j = 0; j < transColList.size(); j++)
			{
				ArrayList<ColumnDTO> inputColList = (ArrayList<ColumnDTO>) transColList.get(j).transRule.srcColumns;

				for (int k = 0; k < transRec.inRecsList.size(); k++)
				{
					String prevOutRecName = transRec.inRecsList.get(k).prevOutRec.name;

					int l;
					for (l = 0; l < inputColList.size(); l++)
					{
						if (inputColList.get(l) == null)
							continue;

						if (inputColList.get(l).parentOutRec.name.equalsIgnoreCase(prevOutRecName))
							break;
					}
					if (l < inputColList.size())
						continue;

					colName = transColList.get(j).colStruct.name;
					int nIndex = colName.indexOf(".");
					if (nIndex >= 0)
						colName = colName.substring(nIndex + 1);

					ColumnDTO inColDTO = getInputSourceElement (null, colName, colName,
							transRec.inRecsList.get(k).prevOutRec, false, codeGenToolId);

					if (inColDTO != null)
						addUnionInputElement(transColList.get(j).transRule, inColDTO);
				}
			}

			updateUnionUsedPorts (transRec);

		}

		return;
	}

	private static void updateUnionUsedPorts (TransRecDTO unionRec)
	{
		ArrayList<ColumnDTO> transColList = ((UnionInfo)unionRec.transInfo).colDTOList;

		for (int j = 0; j < transColList.size(); j++)
		{
			ArrayList<ColumnDTO> inputSrcCols = (ArrayList<ColumnDTO>) transColList.get(j).transRule.srcColumns;

			for (int k = 0; k < inputSrcCols.size(); k++)
			{
				MapPorts.identifyAndUpdateUsedPorts (inputSrcCols.get(k));
			}
			transColList.get(j).isBeingUsed = 'Y';
		}
	}

	private static boolean addUnionInputElement (TransRuleDTO transRule, ColumnDTO inColDTO)
	{
		int k1;

		if (inColDTO == null)
			return false;

		if (transRule == null)
			return false;

		for (k1 = 0; k1 < transRule.srcColumns.size(); k1++)
		{
			ColumnDTO colDTO = transRule.srcColumns.get(k1);

			if (colDTO == null)
				continue;

			if (colDTO.colStruct.name.equalsIgnoreCase(inColDTO.colStruct.name))
			{
				if (colDTO.parentOutRec.name.equalsIgnoreCase(inColDTO.parentOutRec.name))
					break;
			}
		}
		if (k1 < transRule.srcColumns.size())
			return false;

		transRule.srcColumns.add(inColDTO);

		return true;
	}

	private static ColumnDTO getInputSourceElement (String compRef, String elemRef, String elemFullName,
			OutRecDTO outRec, boolean bWithFullElemName, int codeGenToolId)
	{
		ColumnDTO colDTO;
		String colName;
		TransRecDTO transRec = outRec.parentTransRec;

		for (int i = 0; i < outRec.columnList.size(); i++)
		{
			colDTO = outRec.columnList.get(i);

			if (bWithFullElemName)
			{
				if (elemFullName.equalsIgnoreCase(colDTO.colStruct.name))
					return colDTO;
			}
			else
			{
				colName = colDTO.colStruct.name;
				int nIndex = colDTO.colStruct.name.indexOf(".");
				if (nIndex >= 0)
					colName = colName.substring(nIndex + 1);
				if (elemRef.equalsIgnoreCase (colName))
					return colDTO;
			}
		}

		if ((transRec.transType.equalsIgnoreCase("Source")) || (transRec.transType.equalsIgnoreCase("LookupSource")))
		{

			if (compRef != null && compRef.equalsIgnoreCase(transRec.compName))
			{
				colDTO = Utils.getSrcTgtColumnDTOByName((SrcTgtInfo)transRec.transInfo, elemRef);

				if (colDTO != null)
				{
					ColumnDTO newColDTO = Utils.getColumnDeepClone(colDTO);
					newColDTO.colStruct.name = transRec.compName + "." + newColDTO.colStruct.name;

					Utils.changeToToolSpecificDataType(codeGenToolId, ((SrcTgtInfo)transRec.transInfo).fileType, newColDTO);

					newColDTO.parentOutRec = outRec;
					newColDTO.srcColumn = colDTO;

					outRec.columnList.add(newColDTO);

					return newColDTO;
				}
			}
		}

		if (transRec.inRecsList == null)
			return null;

		for (int i = 0; i < transRec.inRecsList.size(); i++)
		{
			OutRecDTO prevOutRec = transRec.inRecsList.get(i).prevOutRec;
			colDTO = getInputSourceElement (compRef, elemRef, elemFullName, prevOutRec, bWithFullElemName, codeGenToolId);
			if (colDTO == null)
				continue;

			ColumnDTO newColDTO = Utils.getColumnDeepClone(colDTO);

			newColDTO.parentOutRec = outRec;
			newColDTO.srcColumn = colDTO;

			prevOutRec.columnList.add(newColDTO);

			return newColDTO;
		}

		return null;
	}

}
