
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.DetailElementDTO;
import com.dataSwitch.xml.ETLMapDTO.FilterInfo;
import com.dataSwitch.xml.ETLMapDTO.JoinerInfo;
import com.dataSwitch.xml.ETLMapDTO.LookupInfo;
import com.dataSwitch.xml.ETLMapDTO.MultiJoinerInfo;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.RouterInfo;
import com.dataSwitch.xml.ETLMapDTO.SorterInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapDTO.UnionInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.ConditionalGrouping.OutputGroup;
import com.dataSwitch.xml.connectorspecs.InputDataElementType;
import com.dataSwitch.xml.connectorspecs.SourceDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule;

public class MapPorts
{

	public final static void consolidateAllUsedSrcDataElementsAndPropagate(DataMapping datamapping, List<TransRecExtDTO> recsList, int codeGenToolId)
	{
		HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList = new HashMap<String, ArrayList<ColumnDTO>>();
		ArrayList<String> srcCompNames = new ArrayList<String>();

		TransRecExtDTO srcRecord;
		TargetDataElementType tgtDataElem;
		ArrayList<ColumnDTO> srcColList = null;

		//all sources which is there in sourceDataElement is getting added in srcColList
		for( int i = 0; i < datamapping.getTargetDataElements().getTargetDataElement().size(); i++)
		{
			// target Column
			tgtDataElem = datamapping.getTargetDataElements().getTargetDataElement().get(i);

			if (tgtDataElem.getDirectSourceDataElementRef() != null)
			{
				SourceDataElementType directSrcElem = tgtDataElem.getDirectSourceDataElementRef();

				cloneAndAddSourceDataElement (directSrcElem.getSourceFeedRef(), directSrcElem.getSourceDataElementRef(),
						recsList, srcCompNames, srcCompsColumnList);

				continue;
			}

			// all source columns
			for (int l = 0; l < tgtDataElem.getTransformationRule().size(); l++)
			{
				TransformationRule rule = tgtDataElem.getTransformationRule().get(l);

				List<String> srcElemList = Utils.getInputDataElements(rule);
				
				//multiple sourceElement list inside a TargetDataElement
				for (int j = 0; j < srcElemList.size(); j++)
				{
					String srcElem = srcElemList.get(j);
					cloneAndAddSourceDataElement (Utils.getCompNamefromVar(srcElem), Utils.getElemNamefromVar(srcElem),
							recsList, srcCompNames, srcCompsColumnList);
					/*if (rule.getRuleName().equalsIgnoreCase("Special - Union"))
						break;*/
				}
				//adding all coulumns against the sourcedataelementRef
			}
		}

		// Consolidate all used source columns from keys and conditions of other transformations
		// Extract Used Source and Lookup Source Columns from Conditions and Return Data Elements
		// Getting all the sources used in condition for data transformation like union,switch,filter,joiner,lookup
		DataTransformation dt;
		if (datamapping.getDataTransformations() != null)
		{
			for( int i = 0; i < datamapping.getDataTransformations().getDataTransformation().size(); i++)
			{
				dt = datamapping.getDataTransformations().getDataTransformation().get(i);
				String condition = null;
				if (dt.getTransformationType().equalsIgnoreCase("Union Transformation") )
				{
					 condition = dt.getSetOperation().getOperation(); // "Union All"
					 UnionParser.extractAllUsedSourceDataElementsFromCondition (condition, recsList, srcCompNames, srcCompsColumnList);
				}
				else if (dt.getTransformationType().equalsIgnoreCase("Conditional Grouping") )
				{
					if(dt.getConditionalGrouping() !=null)
					{
						for (int j = 0; j < dt.getConditionalGrouping().getOutputGroup().size(); j++)
						{
							OutputGroup grp = dt.getConditionalGrouping().getOutputGroup().get(j);
							if (condition == null)
								condition = grp.getFilterCondition();
							else
								condition = condition + " AND " + grp.getFilterCondition();
						}
						RouterParser.extractAllUsedSourceDataElementsFromCondition (condition, recsList, srcCompNames, srcCompsColumnList);
					}
				}
				else if (dt.getTransformationType().equalsIgnoreCase("Filter Transformation") )
				{
					condition = dt.getFilter().getFilterCondition();
					FilterParser.extractAllUsedSourceDataElementsFromCondition (condition, recsList, srcCompNames, srcCompsColumnList);
				}
				else if(dt.getTransformationType().equalsIgnoreCase("Joiner Transformation"))
				{
					JoinerParser.extractAllUsedSourceDataElements (dt.getJoiner(), recsList, srcCompNames, srcCompsColumnList);
				}
				else if(dt.getTransformationType().equalsIgnoreCase("Multi Joiner Transformation"))
				{
					MultiJoinerParser.extractAllUsedSourceDataElements (dt.getMultijoiner(), recsList, srcCompNames, srcCompsColumnList);
				}
				else if (dt.getTransformationType().equalsIgnoreCase("Lookup"))
				{
					LookupParser.extractAllUsedSourceDataElements (dt.getLookup(), recsList, srcCompNames, srcCompsColumnList);
				}
				else if (dt.getTransformationType().equalsIgnoreCase("Sorter"))
				{
					SorterParser.extractAllUsedSourceDataElements (dt.getSorter(), recsList, srcCompNames, srcCompsColumnList);
				}
			}
		}


		//iterating over all Sources in TargetDataElements
		for (int i = 0; i < srcCompNames.size(); i++)
		{
			srcRecord = Utils.getTransRecDTOByName(recsList, srcCompNames.get(i));
			srcColList = srcCompsColumnList.get(srcRecord.compName);

			String datatype = ((SrcTgtInfo)srcRecord.transInfo).fileType;
			//System.out.println("Test---"+((SrcTgtInfo)srcRecord.transInfo).dbType);
			if(((SrcTgtInfo)srcRecord.transInfo).dbType != null)
			{
				if(((SrcTgtInfo)srcRecord.transInfo).dbType.equalsIgnoreCase("Other Transformation"))
				{
					datatype = "DS_TOOL";
				
				}
			}
				
							
			Utils.changeToToolSpecificDataType (codeGenToolId, datatype, srcColList);
			
			MapPorts.propagateAllSourcePortsToTargets (srcRecord, srcColList);
		}
	}

	public final static void cloneAndAddSourceDataElement (String srcCompName, String srcElemName, List<TransRecExtDTO> recsList,
							ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{
		TransRecExtDTO srcRecord;
		ColumnDTO colDTO;
		ArrayList<ColumnDTO> srcColList = null;
		String newColName;

		srcRecord = Utils.getTransRecDTOByName(recsList, srcCompName);
		if (srcRecord == null)
			return;
		if ((!srcRecord.transType.equalsIgnoreCase("Source")) &&
				(!srcRecord.transType.equalsIgnoreCase("LookupSource")))
		{
			return;
		}

		if (!srcCompNames.contains(srcRecord.compName))
		{
			srcColList = new ArrayList<ColumnDTO>();
			srcCompNames.add(srcRecord.compName);
			srcCompsColumnList.put(srcRecord.compName, srcColList);
		}
		else
		{
			srcColList = srcCompsColumnList.get(srcRecord.compName);
		}

		newColName = srcCompName + "." + srcElemName;
		for (int k = 0; k < srcColList.size(); k++)
		{
			if (srcColList.get(k).colStruct.name.equalsIgnoreCase(newColName))
				return;
		}

		colDTO = Utils.getSrcTgtColumnDTOByName((SrcTgtInfo)srcRecord.transInfo, srcElemName);

		if (colDTO != null)
		{
			ColumnDTO newCol = Utils.getColumnDeepClone(colDTO);
			newCol.colStruct.name = srcRecord.compName + "." + newCol.colStruct.name;
			srcColList.add(newCol);
		}

		return;
	}

	public final static void propagatePortToTargets (TransRecExtDTO curRecord, ColumnDTO outColDTO)
	{
		ArrayList<ColumnDTO> outColList = new ArrayList<ColumnDTO>();

		outColList.add(outColDTO);

		propagateAllSourcePortsToTargets (curRecord, outColList);
	}

	public final static void propagateAllSourcePortsToTargets (TransRecExtDTO curRecord, ArrayList<ColumnDTO> curColList)
	{
		ArrayList<ColumnDTO> newOutCols;
		ColumnDTO colDTO;

		if (curRecord.outRecsList.size() <= 0)
			return;

		for (int i = 0; i < curRecord.outRecsList.size(); i++)
		{
			OutRecDTO outRec = curRecord.outRecsList.get(i);

			newOutCols = new ArrayList<ColumnDTO>();

			for (int j = 0; j < curColList.size(); j++)
			{
				colDTO = Utils.getColumnClone(curColList.get(j), outRec);
				colDTO.srcColumn = curColList.get(j);
				newOutCols.add (colDTO);
			}

			outRec.columnList.addAll(newOutCols);


			TransRecExtDTO nextParentTransRec = (TransRecExtDTO) outRec.nextInRec.parentTransRec;

			nextParentTransRec.transColsList.addAll(newOutCols);
			propagateAllSourcePortsToTargets (nextParentTransRec, newOutCols);
		}
	}

	public final static boolean propagatePortUpToUnion (TransRecExtDTO curRecord, ColumnDTO inColDTO,
												TransRecExtDTO unionRec)
	{
		if (inColDTO == null)
			return false;

		if ((curRecord.compName.equals(unionRec.compName)) && (curRecord.transType.equals(unionRec.transType)))
		{
			String colName = inColDTO.colStruct.name;
			int index = colName.indexOf(".");
			if (index >= 0)
				colName = unionRec.compName + "." + colName.substring(index + 1);
			else
				colName = unionRec.compName + "." + colName;

			UnionInfo info = (UnionInfo)unionRec.transInfo;

			ArrayList<ColumnDTO> colsList = info.colDTOList;
			for (int i = 0; i < colsList.size(); i++)
			{
				ColumnDTO colDTO = colsList.get(i);

				if (!colDTO.colStruct.name.equalsIgnoreCase(colName))
					continue;

				colDTO.transRule.srcColNamesList.add(colName);
				colDTO.transRule.srcColumns.add(colDTO);

				return true;
			}

			return false;
		}

		boolean bPortAddedToUnion = false;
		for (int i = 0; i < curRecord.outRecsList.size(); i++)
		{
			OutRecDTO outRec = curRecord.outRecsList.get(i);

			ColumnDTO outColDTO = Utils.getColumnClone(inColDTO, outRec);
			outColDTO.srcColumn = inColDTO;

			TransRecExtDTO nextParentTransRec = (TransRecExtDTO) outRec.nextInRec.parentTransRec;
			if (propagatePortUpToUnion (nextParentTransRec, outColDTO, unionRec))
			{
				nextParentTransRec.transColsList.add(outColDTO);
				outRec.columnList.add(outColDTO);
				bPortAddedToUnion = true;
			}
		}

		return bPortAddedToUnion;
	}

	public final static void identifyAndUpdateUsedPorts(String compName, String columnName, TransRecExtDTO transRec)
	{
		ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec (transRec, compName, columnName);

		if (colDTO == null)
		{
			System.out.println ("ERROR: Data Element **" + compName + "." + columnName +
 									"** not found in Component **" + transRec.compName + "**");
			return;
		}
		if (colDTO.isBeingUsed == 'Y')
			return;

		colDTO.isBeingUsed = 'Y';

		if (colDTO.parentOutRec == null)
			return;

		identifyAndUpdateUsedPorts (compName, columnName, (TransRecExtDTO) colDTO.parentOutRec.parentTransRec);

		return;
	}

	public final static void identifyAndUpdateUsedPorts (ColumnDTO colDTO)
	{
//		ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec (transRec, compName, columnName);
//
//		if (colDTO == null)
//		{
//			System.out.println ("ERROR: Data Element **" + compName + "." + columnName +
// 									"** not found in Component **" + transRec.compName + "**");
//			return;
//		}


		if(colDTO == null)
			return;

		if (colDTO.isBeingUsed == 'Y')
			return;

		colDTO.isBeingUsed = 'Y';

		if (colDTO.srcColumn != null)
			identifyAndUpdateUsedPorts (colDTO.srcColumn);

		if (colDTO.transRule != null)
		{
			ArrayList<ColumnDTO> ruleColsList = new ArrayList<ColumnDTO>();
			if (colDTO.transRule.srcColumns != null)
				ruleColsList.addAll (colDTO.transRule.srcColumns);
			if (colDTO.transRule.grpByColumns != null)
				ruleColsList.addAll (colDTO.transRule.grpByColumns);
			for (int j = 0; j < ruleColsList.size(); j++)
			{
				identifyAndUpdateUsedPorts (ruleColsList.get(j));
			}
		}

		if (colDTO.parentOutRec == null)
			return;

		OutRecDTO outRec = colDTO.parentOutRec;
		TransRecExtDTO transRec = (TransRecExtDTO) outRec.parentTransRec;
		if (transRec.isProcessed)
			return;

		transRec.isProcessed = true;

		
		ArrayList<ColumnDTO> colsList = new ArrayList<ColumnDTO>();

		if (transRec.transInfo instanceof FilterInfo)
			colsList.addAll (((FilterInfo)transRec.transInfo).conditionCols);
		else if (transRec.transInfo instanceof JoinerInfo)
		{
			colsList.addAll (((JoinerInfo)transRec.transInfo).masterCols);
			colsList.addAll (((JoinerInfo)transRec.transInfo).detailCols);
		}
		else if (transRec.transInfo instanceof MultiJoinerInfo)
		{
			for(DetailElementDTO detailEle : ((MultiJoinerInfo)transRec.transInfo).detailElements){
			colsList.addAll (detailEle.masterCols);
			colsList.addAll (detailEle.detailCols);
			}
		}
		else if (transRec.transInfo instanceof LookupInfo)
		{
			colsList.addAll (((LookupInfo)transRec.transInfo).masterCols);
			colsList.addAll (((LookupInfo)transRec.transInfo).lookUpCols);
		}
		else if (transRec.transInfo instanceof RouterInfo)
		{
			for (int i = 0; i < ((RouterInfo)transRec.transInfo).grpConditionCols.size(); i++)
				colsList.addAll (((RouterInfo)transRec.transInfo).grpConditionCols.get(i));
		}
		else if (transRec.transInfo instanceof SorterInfo)
			colsList.addAll (((SorterInfo)transRec.transInfo).keyCols);

		for (int i = 0; i < colsList.size(); i++)
			identifyAndUpdateUsedPorts (colsList.get(i));

		return;
	}

}