
package com.dataSwitch.xml.ETLMapping;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.dataSwitch.ds.functionTypeRules.CodeGenRules;
import com.dataSwitch.ds.functionTypeRules.RuleMapping;
import com.dataSwitch.xml.ETLMapDTO.AggInfo;
import com.dataSwitch.xml.ETLMapDTO.ChecksumInfo;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ColumnStruct;
import com.dataSwitch.xml.ETLMapDTO.ExprInfo;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SeqGenInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;
import com.dataSwitch.xml.ETLMapDTO.TransRuleDTO;
import com.dataSwitch.xml.ETLMapDTO.UnionInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.ConditionalGrouping.OutputGroup;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule;

public class Datamapping
{
	public static String getVersion(){
		String version = "1.0.0.8";  //version no. to be update each time changes are made
		return version;
	}

	/*public final static ArrayList<TransRecDTO> getMappingRecords(Connector docspecs, DataMapping dataMapping,  ControlMaster dsControl, int codeGenToolId)
	{
		return (getMappingRecords(docspecs, dataMapping,  codeGenToolId));
	}

	public final static ArrayList<TransRecDTO> getMappingRecords(Connector docspecs, DataMapping dataMapping,  ControlMaster dsControl,
			boolean bSetPrevCompForSeqGenerator, boolean bAggHasPassivePorts, int codeGenToolId)
	{
		return (getMappingRecords(docspecs, dataMapping,  bSetPrevCompForSeqGenerator, bAggHasPassivePorts, codeGenToolId));
	}*/
	
	
	public final static ArrayList<TransRecDTO> getMappingRecords(Connector docspecs, DataMapping dataMapping, int codeGenToolId)
	{
		if(!docspecs.getSchemaVersion().equalsIgnoreCase("3.0.0.12")){
			System.out.println("Invalid schema version");
			return null;
		}
		return (getMappingRecords(docspecs, dataMapping, false, true, codeGenToolId));
	}

	public final static ArrayList<TransRecDTO> getMappingRecords(Connector docspecs, DataMapping dataMapping,
			boolean bSetPrevCompForSeqGenerator, boolean bAggHasPassivePorts, int codeGenToolId)
	{
		ArrayList<TransRecExtDTO> recsListExt = getMappingRecordsExt(docspecs, dataMapping, bSetPrevCompForSeqGenerator, bAggHasPassivePorts, codeGenToolId);

		ArrayList<TransRecDTO> recsList = null;

		
		if (recsListExt != null)
		{
			recsList = new ArrayList<TransRecDTO>();
			for (int i = 0; i < recsListExt.size(); i++)
			{
				recsList.add((TransRecDTO) recsListExt.get(i));
			}
		}

		return recsList;
	}

	public final static ArrayList<TransRecExtDTO> getMappingRecordsExt (Connector docspecs, DataMapping dataMapping,
			boolean bSetPrevCompForSeqGenerator, boolean bAggHasPassivePorts, int codeGenToolId)
	{
		ArrayList<TransRecExtDTO> recsList = new ArrayList<TransRecExtDTO>();
		
		List<RuleMapping> codeGenRuleList = CodeGenRules.getCodeGenerationRules("DataStage");

		try
		{
			Utils.updateLookupTransInDataMapping(dataMapping);

			List<DataTransformation> otherTransList = Utils.getOtherTransList(dataMapping);

			Utils.updateOtherTransInDataMapping(dataMapping, otherTransList);

			if (bSetPrevCompForSeqGenerator)
				populateSeqGenPrevCompName (dataMapping);

			SourceStage.fillSourceCustomStage(docspecs, dataMapping, recsList);

			TargetStage.fillTargetCustomStage(docspecs, dataMapping, recsList);

			//Fill Temperory Expression
			if(dataMapping.getDataTransformations().getDataTransformation().size() == 2)
				Utils.addPassThroughExpresssion(docspecs, dataMapping, recsList);
			
			MapComponents.fillTransformationsDataFlow(dataMapping, recsList, codeGenRuleList);

			MapPorts.consolidateAllUsedSrcDataElementsAndPropagate (dataMapping, recsList, codeGenToolId); // fill column list

			HashMap<String, String> srcTgtVariablePortMap = new HashMap<String, String>();

			createLocalAndOutVariablePortsAndPropagate (recsList, dataMapping,  codeGenRuleList, docspecs, srcTgtVariablePortMap, codeGenToolId); // docspecs To fetch the datatype, length

			mapTargetDataElementsAndMarkUsedPorts (recsList, dataMapping,  codeGenRuleList, srcTgtVariablePortMap);

			UnionParser.propagateInputElementsForUnionTransformations (recsList, dataMapping, srcTgtVariablePortMap, codeGenToolId);

			//addMissingColumns(recsList);


			cleanUpUnusedPorts(recsList);

			if (!bAggHasPassivePorts)
				identifyAndAddJoinerForAggregatorPassiveColumns(recsList);

			updateTargetColumnNameInPrevComp(recsList);

			suffixNumbersWithOutRecDuplicateColumnNames(recsList);

			Utils.combineTransRecObj(recsList);

			Utils.removeDataMappingUpdates(dataMapping, otherTransList);
			//cleanUpUnusedTransformations(recsList);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}

		return recsList;
	}

	private static void addMissingColumns(ArrayList<TransRecExtDTO> recsList) 
	{
		// TODO Auto-generated method stub
		TransRecExtDTO transRec;
		ArrayList<ColumnDTO> colList;

		for( int i = 0; i < recsList.size(); i++)
		{
			transRec = recsList.get(i);
			if(transRec.compName.equalsIgnoreCase("CP_UNC_LP"))
				System.out.println("Test");
			if (transRec.transInfo instanceof SrcTgtInfo)
				continue;

			if(transRec.transType.equalsIgnoreCase("Conditional Grouping"))
			{
				transRec.transColsList = null;
				for (int j = 0; j < transRec.outRecsList.size(); j++)
				{
					OutRecDTO outRec = transRec.outRecsList.get(j);
					colList = outRec.columnList;
					for (int k = colList.size() - 1; k >= 0; k--)
					{
						if (colList.get(k).isBeingUsed == 'N' && colList.get(k).srcColumn.isBeingUsed =='Y')
						{
							colList.get(k).isBeingUsed = 'Y';

						}

					}
				}
			}

		}


	}

	private static void cleanUpUnusedTransformations(ArrayList<TransRecExtDTO> recsList) 
	{
		// TODO Auto-generated method stub
		TransRecExtDTO transRec;

		for( int i = 0; i < recsList.size(); i++)
		{
			transRec = recsList.get(i);
			if(transRec.nextComp.size()==0)
			{
				if (!transRec.transType.equalsIgnoreCase("Target"))
					recsList.remove(transRec);

			}
		}

	}

	private static final void createLocalAndOutVariablePortsAndPropagate (List<TransRecExtDTO> recsList, DataMapping dataMapping,
			List<RuleMapping> codeGenRuleList, Connector docspecs, HashMap<String, String> srcTgtVariablePortMap, int codeGenToolId)
	{
		TransRecExtDTO transRec, tgtRec;
		TargetDataElementType tgtDataElem;

		boolean isVariable = false;
		String colName;

		// Add Port Columns for TransRules and Propagate them till targets
		for (int i = 0; i < dataMapping.getTargetDataElements().getTargetDataElement().size(); i++)
		{
			tgtDataElem = dataMapping.getTargetDataElements().getTargetDataElement().get(i);

			if (tgtDataElem.getTransformationRule().size() <= 0)
				continue;


			tgtRec = Utils.getTransRecDTOByName(recsList, tgtDataElem.getTargetDatsetRef());

			isVariable = true;

			ColumnDTO prevTransColDTO = null;
			for (int j = tgtDataElem.getTransformationRule().size() - 1; j >= 0; j--)
			{
				TransformationRule rule = tgtDataElem.getTransformationRule().get(j);

				/*if (rule.getRuleName().equalsIgnoreCase("Special - Union"))
					continue;*/

				colName = getPortColumnName (tgtDataElem, rule, j, isVariable, codeGenRuleList, dataMapping);

				transRec = Utils.getTransRecDTOByName(recsList, rule.getComponentName());

				ColumnDTO outColDTO = Datamapping.getOutVariableColumnObj (codeGenToolId, colName, rule, tgtDataElem, recsList);
				outColDTO.colStruct.name = rule.getComponentName() + "." + outColDTO.colStruct.name;

				addTransRuleIntoTransInfoColumns (codeGenRuleList, outColDTO, transRec, rule);

				rule.setTempElemName(outColDTO.colStruct.name);

				if (j == tgtDataElem.getTransformationRule().size() - 1) // Tgt element's input Port Name
				{
					String tgtName = tgtDataElem.getTargetDatsetRef() + "." + tgtDataElem.getTargetDataElementRef();

					srcTgtVariablePortMap.put(tgtName, outColDTO.colStruct.name);
				}

				outColDTO.transRule.srcColumns.add(prevTransColDTO);
				prevTransColDTO = outColDTO;

			}
		}

		fillInputSrcPortsForTransformationRules (recsList, dataMapping, codeGenRuleList, srcTgtVariablePortMap);

		// Process Data Transformation related properties
		DataTransformation dt;
		if(dataMapping.getDataTransformations() == null)
			return;

		for( int j = 0; j < dataMapping.getDataTransformations().getDataTransformation().size(); j++)
		{
			dt = dataMapping.getDataTransformations().getDataTransformation().get(j);
			transRec = Utils.getTransRecDTOByName(recsList, dt.getName());

			if (dt.getTransformationType().equalsIgnoreCase("Union Transformation") )
			{
				transRec.setOperation = dt.getSetOperation().getOperation(); // Union All, ...
				UnionParser.processUnionTransformation (transRec, dt);
			}
			else if (dt.getTransformationType().equalsIgnoreCase("Conditional Grouping") )
				RouterParser.processRouterTransformation (transRec, dt);
			else if (dt.getTransformationType().equalsIgnoreCase("Filter Transformation") )
				FilterParser.processFilterTransformation (transRec, dt);
			else if(dt.getTransformationType().equalsIgnoreCase("Joiner Transformation"))
				JoinerParser.processJoinerTransformation (dt.getJoiner(), transRec);
			else if(dt.getTransformationType().equalsIgnoreCase("Multi Joiner Transformation"))
				MultiJoinerParser.processJoinerTransformation(dt.getMultijoiner(), transRec);
			else if (dt.getTransformationType().equalsIgnoreCase("Lookup"))
				LookupParser.processLookupTransformation (dt.getLookup(), transRec, dataMapping);
			else if (dt.getTransformationType().equalsIgnoreCase("Sorter"))
				SorterParser.processSorterRule (transRec, recsList, dt);
			else if (dt.getTransformationType().equalsIgnoreCase("Rank"))
				RankParser.processRankRule (transRec, recsList, dt);
			else if (dt.getTransformationType().equalsIgnoreCase("Remove Duplicates"))
				RemDupParser.processRemoveDuplicateRule(transRec, recsList, dt);
		}
	}

	private static String getPortColumnName(TargetDataElementType tgtDataElem, TransformationRule rule, int rulePos, boolean isVariable, List<RuleMapping> codeGenRuleList, DataMapping dataMapping)
	{
		String colName;

		colName = tgtDataElem.getTargetDataElementRef();
		int suffix = tgtDataElem.getTransformationRule().size() - 1 - rulePos;
		if (isVariable)
		{
			if (suffix == 0)
				return colName;
		}

		if (suffix > 0)
			colName = colName + suffix;

		if (rule.getRuleName().equalsIgnoreCase("Lookup"))
		{
			if ((rule.getParameter() != null) && (rule.getParameter().size() > 0))
			{
				if (rule.getParameter().size() > 1)
				{
					colName = rule.getParameter().get(1).getValue(); // Return Element Name
					return colName;
				}
				else
				{
					String lkpName = rule.getParameter().get(0).getValue(); // Lookup Trans Name

					for (int i = 0; i < dataMapping.getDataTransformations().getDataTransformation().size(); i++)
					{
						DataTransformation dt = dataMapping.getDataTransformations().getDataTransformation().get(i);

						if (!lkpName.equalsIgnoreCase(dt.getName()))
							continue;
						colName = dt.getLookup().getReturnDataElement().get(0).getDataElementName();
						return colName;
					}
				}
			}

			return colName;
		}

		RuleMapping ruleMapObj = Utils.getTransformationRuleMappingObj (codeGenRuleList, rule.getRuleName());

		String szTransType = ruleMapObj.getTransformationType();
		if (szTransType.equalsIgnoreCase("Data Transformation"))
			szTransType = ruleMapObj.getExpression();

		List<String> inputDataElements = Utils.getInputDataElements(rule);
		
		if (szTransType.equalsIgnoreCase("Expression"))
		{
			
			
			if (inputDataElements.size() == 1)
				
				return Utils.getElemNamefromVar(inputDataElements.get(0));

			return colName;
		}

		if (szTransType.equalsIgnoreCase("Aggregator"))
		{
			ArrayList<String> srcElemNames = new ArrayList<String> ();

			for (int i = 0; i < inputDataElements.size(); i++)
			{
				srcElemNames.add(Utils.getElemNamefromVar(inputDataElements.get(i)).toLowerCase());
			}

			if ((rule.getParameter() != null) && (rule.getParameter().size() > 0))
			{
				String [] grpByColumnNameList;
				for (int j = 0; j < rule.getParameter().size(); j++)
				{
					String szGrpByCols = rule.getParameter().get(j).getValue();
					grpByColumnNameList = szGrpByCols.split(";");
					for (int i = 0; i < grpByColumnNameList.length; i++)
					{
						if (grpByColumnNameList[i] == null)
							continue;
						if (grpByColumnNameList[i].trim().length() <= 0)
							continue;

						srcElemNames.remove(grpByColumnNameList[i].trim().toLowerCase());
					}
				}
			}
			if (srcElemNames.size() == 1)
				return srcElemNames.get(0);

			return colName;
		}

		if (szTransType.equalsIgnoreCase("Sequence Generator"))
		{
			if (inputDataElements.size() == 1){

				return Utils.getElemNamefromVar(inputDataElements.get(0));
			}
			return colName;
		}
		if (szTransType.equalsIgnoreCase("Rank"))
		{
			if (inputDataElements.size() == 1){

				return Utils.getElemNamefromVar(inputDataElements.get(0));
			}
			return colName;
		}


		return null;
	}

	public static final ColumnDTO getOutVariableColumnObj (int codeGenToolId, String portName, TransformationRule rule,
			TargetDataElementType tgtDataElem, List<TransRecExtDTO> recsList)
	{
		ColumnDTO colDTO = new ColumnDTO();

		if (portName.equalsIgnoreCase(tgtDataElem.getTargetDataElementRef()))
		{
			// If Variable Port
			if ((tgtDataElem.getDataType() != null) && (tgtDataElem.getDataType().trim().length() > 0))
			{
				colDTO.colStruct = new ColumnStruct();
				colDTO.colStruct.name = portName;
				colDTO.colStruct.dataType = tgtDataElem.getDataType();
				colDTO.colStruct.length = tgtDataElem.getPrecision();
				colDTO.colStruct.precision = tgtDataElem.getPrecision();
				colDTO.colStruct.Scale = tgtDataElem.getScale();

				Utils.changeToToolSpecificDataType (codeGenToolId, "DS_TOOL", colDTO);
				return colDTO;
			}

			String szTgtName = tgtDataElem.getTargetDatsetRef();
			String szTgtColName = tgtDataElem.getTargetDataElementRef();

			TransRecExtDTO tgtRecDTO = Utils.getTransRecDTOByName(recsList, szTgtName);

			if(tgtRecDTO.transInfo instanceof SrcTgtInfo)
			{
				ColumnDTO tgtColDTO = Utils.getSrcTgtColumnDTOByName((SrcTgtInfo)tgtRecDTO.transInfo, szTgtColName);
				if (tgtColDTO != null)
				{
					ColumnDTO tempCol = Utils.getColumnDeepClone(tgtColDTO);
					colDTO.colStruct = tempCol.colStruct;
					Utils.changeToToolSpecificDataType(codeGenToolId, ((SrcTgtInfo)tgtRecDTO.transInfo).fileType, colDTO);

					return colDTO;
				}
			}
		}

		colDTO.colStruct = new ColumnStruct();
		TransRecExtDTO recDTO = null;
		
		List<String> inputDataElements = Utils.getInputDataElements(rule);

		for (int l = 0; (l < inputDataElements.size()); l++)
		{
			String srcElem = inputDataElements.get(l);

			String srcRefName = Utils.getCompNamefromVar(srcElem);
			String srcColName = Utils.getElemNamefromVar(srcElem);

			TransRecExtDTO tmprecDTO = Utils.getTransRecDTOByName(recsList, srcRefName);

			if(tmprecDTO.transType.equalsIgnoreCase("Source")){
				recDTO = tmprecDTO;
			}

			if (portName.equalsIgnoreCase(srcColName))
			{
				TransRecExtDTO srcRecDTO = Utils.getTransRecDTOByName(recsList, srcRefName);
				if(srcRecDTO.transInfo instanceof SrcTgtInfo){
					ColumnDTO srcColDTO = Utils.getSrcTgtColumnDTOByName ((SrcTgtInfo)srcRecDTO.transInfo, srcColName);
					if (srcColDTO != null)
					{
						ColumnDTO tempCol = Utils.getColumnDeepClone(srcColDTO);
						colDTO.colStruct = tempCol.colStruct;

						Utils.changeToToolSpecificDataType(codeGenToolId, ((SrcTgtInfo)srcRecDTO.transInfo).fileType, colDTO);

						return colDTO;
					}
				}else if(srcRecDTO.transInfo instanceof ExprInfo){
					ColumnDTO srcColDTO = Utils.getExprColumnDTOByName ((ExprInfo)srcRecDTO.transInfo, srcRecDTO.compName+"."+srcColName);
					if (srcColDTO != null)
					{
						ColumnDTO tempCol = Utils.getColumnDeepClone(srcColDTO);
						colDTO.colStruct = tempCol.colStruct;

						colDTO.colStruct.name = tempCol.colStruct.name.substring(tempCol.colStruct.name.indexOf(".")+1,tempCol.colStruct.name.length()); 

						if(recDTO!=null)
							Utils.changeToToolSpecificDataType(codeGenToolId, ((SrcTgtInfo)recDTO.transInfo).fileType, colDTO);
						else{
							colDTO.colStruct.dataType = "String";
							Utils.changeToToolSpecificDataType (codeGenToolId, "DS_TOOL", colDTO);
						}
						return colDTO;
					}
				}else if(srcRecDTO.transInfo instanceof AggInfo){
					ColumnDTO srcColDTO = Utils.getAggColumnDTOByName((AggInfo)srcRecDTO.transInfo, srcRecDTO.compName+"."+srcColName);
					if (srcColDTO != null)
					{
						ColumnDTO tempCol = Utils.getColumnDeepClone(srcColDTO);
						colDTO.colStruct = tempCol.colStruct;

						colDTO.colStruct.name = tempCol.colStruct.name.substring(tempCol.colStruct.name.indexOf(".")+1,tempCol.colStruct.name.length()); 


						if(recDTO!=null)
							Utils.changeToToolSpecificDataType(codeGenToolId, ((SrcTgtInfo)recDTO.transInfo).fileType, colDTO);
						else{
							colDTO.colStruct.dataType = "String";
							Utils.changeToToolSpecificDataType (codeGenToolId, "DS_TOOL", colDTO);
						}

						return colDTO;
					}
				}else if(srcRecDTO.transInfo instanceof SeqGenInfo){
					ColumnDTO srcColDTO = Utils.getSeqGenColumnDTOByName ((SeqGenInfo)srcRecDTO.transInfo, srcRecDTO.compName+"."+srcColName);
					if (srcColDTO != null)
					{
						ColumnDTO tempCol = Utils.getColumnDeepClone(srcColDTO);
						colDTO.colStruct = tempCol.colStruct;

						colDTO.colStruct.name = tempCol.colStruct.name.substring(tempCol.colStruct.name.indexOf(".")+1,tempCol.colStruct.name.length()); 

						if(recDTO!=null)
							Utils.changeToToolSpecificDataType(codeGenToolId, ((SrcTgtInfo)recDTO.transInfo).fileType, colDTO);
						else{
							colDTO.colStruct.dataType = "String";
							Utils.changeToToolSpecificDataType (codeGenToolId, "DS_TOOL", colDTO);
						}

						return colDTO;
					}
				}
			}
		}

		System.out.println("ERROR: - Unknonw Port - Being filled with default value as string");

		colDTO.colStruct.name = portName;
		colDTO.colStruct.dataType = "String";
		colDTO.colStruct.length = 10;

		Utils.changeToToolSpecificDataType (codeGenToolId, "DS_TOOL", colDTO);

		return colDTO;
	}

	public static final void addTransRuleIntoTransInfoColumns (List<RuleMapping> codeGenRuleList, ColumnDTO varColDTO, TransRecExtDTO transRec, TransformationRule rule)
	{
		ArrayList<ColumnDTO> transInfoColumnsList = null;

		RuleMapping ruleMapObj = Utils.getTransformationRuleMappingObj (codeGenRuleList, rule.getRuleName());
		String szTransType = ruleMapObj.getTransformationType();
		if (szTransType.equalsIgnoreCase("Data Transformation"))
			szTransType = ruleMapObj.getExpression();

		boolean isAggregator = false;

		if (szTransType.equalsIgnoreCase("Expression"))
		{
			if (ruleMapObj.getTransformationRule().getRuleName().equalsIgnoreCase("Encoding - MD5 Checksum"))
			{
				ChecksumInfo transInfo = (ChecksumInfo) transRec.transInfo;
				if (transInfo == null)
				{
					transInfo = new ChecksumInfo();
					transRec.transInfo = transInfo;
				}
				transInfoColumnsList = transInfo.colDTOList;
			}
			else
			{
				ExprInfo transInfo = (ExprInfo) transRec.transInfo;
				if (transInfo == null)
				{
					transInfo = new ExprInfo();
					transRec.transInfo = transInfo;
				}

				transInfoColumnsList = transInfo.colDTOList;
			}
		}
		else if (szTransType.equalsIgnoreCase("Aggregator"))
		{
			AggInfo transInfo = (AggInfo) transRec.transInfo;

			if (transInfo == null)
			{
				transInfo = new AggInfo();
			
				transRec.transInfo = transInfo;

			}
			isAggregator = true;
			transInfoColumnsList = transInfo.colDTOList;

		}
		else if (szTransType.equalsIgnoreCase("Sequence Generator"))
		{
			SeqGenInfo transInfo = (SeqGenInfo) transRec.transInfo;
			if (transInfo == null)
			{
				transInfo = new SeqGenInfo();
				transRec.transInfo = transInfo;
			}

			transInfoColumnsList = transInfo.colDTOList;
		}
		else if (szTransType.equalsIgnoreCase("UNION"))
		{
			UnionInfo transInfo = (UnionInfo) transRec.transInfo;
			if (transInfo == null)
			{
				transInfo = new UnionInfo();
				transRec.transInfo = transInfo;
			}

			transInfoColumnsList = transInfo.colDTOList;
		}
		else if (szTransType.equalsIgnoreCase("Rank"))
		{
			RankParser transInfo = (RankParser) transRec.transInfo;
			if (transInfo == null)
			{
				transInfo = new RankParser();
				transRec.transInfo = transInfo;
			}
			transInfoColumnsList = transInfo.colDTOList;

		}
		//		else if (szTransType.equalsIgnoreCase("Sorter"))
		//			SorterParser.processSorterRule (transRec, ruleMapObj, rule, tgtDataElem, j, recsList);
		//		else if (szTransType.equalsIgnoreCase("Rank"))
		//			RemoveDupParser.processRankRule (colName, transRec, ruleMapObj, rule, tgtDataElem, j, recsList);
		//		else if (szTransType.equalsIgnoreCase("Rank"))
		//			RemoveDupInfo.processRankRule (transRec, ruleMapObj, rule, tgtDataElem, j, aliasNamesMap, recsList);
		//		else if (szTransType.equalsIgnoreCase("Lookup")) // Return Elements are propagated already


		String portName = varColDTO.colStruct.name;

		int	i, k = 0;
		while (true)
		{
			for (i = 0; i < transInfoColumnsList.size(); i++)
			{
				if (transInfoColumnsList.get(i).colStruct.name.equalsIgnoreCase(portName))
					break;
			}
			if (i >= transInfoColumnsList.size()) // No Duplicate
				break;
			k++;
			portName = portName + k;
		}

		if (!portName.equalsIgnoreCase(varColDTO.colStruct.name))
		{
			varColDTO.colStruct.name = portName;
			portName = portName.substring(portName.indexOf(".") + 1);
			rule.setTempElemName(portName);
		}

		transInfoColumnsList.add(varColDTO);

		TransRuleDTO ruleDTO = new TransRuleDTO();
		varColDTO.transRule = ruleDTO;

		ruleDTO.transRuleName = rule.getRuleName();
		ruleDTO.errorDetails = rule.getErrorDetails();
		ruleDTO.originalExpression = rule.getOriginalExpression();

		for (i = 0; i < rule.getParameter().size(); i++)
		{
			ruleDTO.paramNamesList.add(rule.getParameter().get(i).getName());
			ruleDTO.paramValuesList.add(rule.getParameter().get(i).getValue());
			ruleDTO.paramTypesList.add(rule.getParameter().get(i).getType());
		}

		List<String> inputDataElements = Utils.getInputDataElements(rule);

		for (i = 0; i < inputDataElements.size(); i++)
		{
			String srcElem = inputDataElements.get(i);
			
			String inputCompRef = Utils.getCompNamefromVar(srcElem);
			String inputElemRef = Utils.getElemNamefromVar(srcElem);

			ruleDTO.srcColNamesList.add (srcElem);

			if (!isAggregator)
				continue;
			for (int j = 0; j < rule.getParameter().size(); j++)
			{
				if (rule.getParameter().get(j).getValue().equalsIgnoreCase(srcElem))
				{
					if(rule.getParameter().get(j).getName().equalsIgnoreCase("Group By"))
					{
						ruleDTO.grpByColNamesList.add(srcElem);
						break;
					}
					else
					{
						ruleDTO.aggOnNames = (srcElem);
						ColumnDTO srcColumn = Utils.getColumnDTOByNameInTransRec(transRec, ruleDTO.aggOnNames);

						ruleDTO.aggOnCols = srcColumn;
						break;
					}
				}
			}
		}

		// Cleanup grpByColumns in srcColumns list
		for (i = 0; i < ruleDTO.grpByColNamesList.size(); i++)
		{
			//			if (ruleDTO.srcColNamesList.size() <= 1) // Need atleast one agg Column
			//				break;

			String elemName = ruleDTO.grpByColNamesList.get(i);
			if (ruleDTO.srcColNamesList.contains(elemName))
				ruleDTO.srcColNamesList.remove(elemName);
		}

		MapPorts.propagatePortToTargets (transRec, varColDTO);
	}


	private static final void fillInputSrcPortsForTransformationRules (List<TransRecExtDTO> recsList, DataMapping dataMapping,
			List<RuleMapping> codeGenRuleList, HashMap<String, String> srcTgtVariablePortMap)
	{
		TransRecExtDTO transRec;

		ArrayList<ColumnDTO> colList;
		ColumnDTO srcColumn;

		// Process Transformation Rules and Propagate
		for( int i = 0; i < recsList.size(); i++)
		{
			transRec = recsList.get(i);

			if (transRec.transInfo == null)//If tgt Col doesn't have TransformationRule
				continue;

			colList = null;
			boolean aggregator = false;

			if (transRec.transType.equalsIgnoreCase("Expression"))
			{
				if(transRec.transInfo instanceof ExprInfo)
					colList = ((ExprInfo)transRec.transInfo).colDTOList;
				else if(transRec.transInfo instanceof ChecksumInfo)
					colList = ((ChecksumInfo)transRec.transInfo).colDTOList;
			}
			else if (transRec.transType.equalsIgnoreCase("Aggregator"))
			{
				colList = ((AggInfo)transRec.transInfo).colDTOList;
				aggregator = true;
			}
			else if (transRec.transType.equalsIgnoreCase("Sequence Generator"))
			{
				colList = ((SeqGenInfo)transRec.transInfo).colDTOList;
			}
			else if (transRec.transType.equalsIgnoreCase("Union Transformation"))
			{
				colList = ((UnionInfo)transRec.transInfo).colDTOList;
			}

			else if (transRec.transType.equalsIgnoreCase("Rank"))
			{
				colList = ((RankParser)transRec.transInfo).colDTOList;
			}
			if (colList == null)
				continue;
			if(transRec.compName.equalsIgnoreCase("CP_UNC_LP"))
			{
				System.out.println("Test");
			}

			for (int j = 0; j < colList.size(); j++)
			{
				TransRuleDTO rule = colList.get(j).transRule;

				for (int k = 0; k < rule.srcColNamesList.size(); k++)
				{
					srcColumn = Utils.getColumnDTOByNameInTransRec(transRec, rule.srcColNamesList.get(k));

					rule.srcColumns.add(srcColumn);
				}
				if (!aggregator)
					continue;
				for (int k = 0; k < rule.grpByColNamesList.size(); k++)
				{
					srcColumn = Utils.getColumnDTOByNameInTransRec(transRec, rule.grpByColNamesList.get(k));
					rule.grpByColumns.add(srcColumn);
				}
			}

			// Filter, Router, Joiner, Lookup - Source columns involved in keys and condition fields are processed
			// in the processing methods of their respective process() methods

			/*

			else if (szTransType.equalsIgnoreCase("Lookup"))
			{
				LookupParser.identifyAndAddSrcColumnsForTransRule (transRec, ruleMapObj, rule, tgtDataElem, recsList);
			}
			else if (szTransType.equalsIgnoreCase("Sorter"))
			{
				SorterParser.identifyAndAddSrcColumnsForTransRule (transRec, ruleMapObj, rule, tgtDataElem, recsList);//veni
			}
			else if (szTransType.equalsIgnoreCase("Rank"))
			{
				RemoveDupInfo.identifyAndAddSrcColumnsForTransRule (transRec, ruleMapObj, rule, tgtDataElem, recsList);
			}

			 */
		}

		return;
	}

	private static final void mapTargetDataElementsAndMarkUsedPorts (List<TransRecExtDTO> recsList, DataMapping dataMapping,
			List<RuleMapping> codeGenRuleList, HashMap<String, String> srcTgtVariablePortMap) throws Exception
	{
		ColumnDTO colDTO;
		TargetDataElementType tgtDataElem;

		// Process Transformation Rules and Propagate
		for( int i = 0; i < dataMapping.getTargetDataElements().getTargetDataElement().size(); i++)
		{
			tgtDataElem = dataMapping.getTargetDataElements().getTargetDataElement().get(i);

			TransRecExtDTO tgtRecExt = Utils.getTransRecDTOByName (recsList, tgtDataElem.getTargetDatsetRef());
			if (tgtRecExt == null)
			{
				System.out.println ("ERROR: Target DataSet Object(" + tgtDataElem.getTargetDatsetRef() + ") - Not Found");
				continue;
			}

			//Anushree

			ColumnDTO tgtColDTO = null;


			if (tgtRecExt.transType.equalsIgnoreCase("Target"))
			{
				SrcTgtInfo srcTgtInfo = (SrcTgtInfo)tgtRecExt.transInfo;

				tgtColDTO = Utils.getSrcTgtColumnDTOByName (srcTgtInfo, tgtDataElem.getTargetDataElementRef());
				if (tgtColDTO == null)
				{
					System.out.println ("ERROR: TgtColumn (" + tgtDataElem.getTargetDataElementRef() + ") Not Found in Target DataSet Object(" + tgtDataElem.getTargetDatsetRef() + ")");
					//throw new Exception(("ERROR: TgtColumn (" + tgtDataElem.getTargetDataElementRef() + ") Not Found in Target DataSet Object(" + tgtDataElem.getTargetDatsetRef() + ")"));
					continue;
				}


				String inputElemName = "";
				if (tgtDataElem.getDirectSourceDataElementRef() != null)
				{
					inputElemName = tgtDataElem.getDirectSourceDataElementRef().getSourceFeedRef() + "." +
							tgtDataElem.getDirectSourceDataElementRef().getSourceDataElementRef();

				}
				else
				{
					inputElemName = tgtDataElem.getTargetDatsetRef() + "." +  tgtDataElem.getTargetDataElementRef();
					inputElemName = srcTgtVariablePortMap.get(inputElemName);
					if (inputElemName == null)
					{
						TransformationRule rule = tgtDataElem.getTransformationRule().get(0);

						if (rule.getRuleName().equalsIgnoreCase("Special - Union"))
						{
							List<String> inputDataElements = Utils.getInputDataElements(rule);
							inputElemName = inputDataElements.get(0);
						}
					}
				}


				// get column object from TransRecColsList
				colDTO = Utils.getColumnDTOByNameInTransRec (tgtRecExt, inputElemName);
				if (colDTO == null)
					continue;


				tgtColDTO.srcColumn = colDTO;
				tgtColDTO.isBeingUsed = 'Y';

				MapPorts.identifyAndUpdateUsedPorts (colDTO);

			}
			else
			{
				if (tgtRecExt.transType.equalsIgnoreCase("Expression"))
				{
					if(tgtRecExt.transInfo instanceof ExprInfo){
						ExprInfo srcTgtInfo = (ExprInfo)tgtRecExt.transInfo;
						ArrayList<ColumnDTO> columnList = srcTgtInfo.colDTOList;
						tgtColDTO = Utils.getColumnDTOByName (columnList, tgtDataElem.getTargetDatsetRef()+"."+tgtDataElem.getTargetDataElementRef());

					}
					else if(tgtRecExt.transInfo instanceof ChecksumInfo)
					{
						ChecksumInfo chkInfo = (ChecksumInfo)tgtRecExt.transInfo;
						ArrayList<ColumnDTO> columnList = chkInfo.colDTOList;
						tgtColDTO = Utils.getColumnDTOByName (columnList, tgtDataElem.getTargetDatsetRef()+"."+tgtDataElem.getTargetDataElementRef());
					}

				}
				else if (tgtRecExt.transType.equalsIgnoreCase("Aggregator"))
				{
					AggInfo srcTgtInfo = (AggInfo)tgtRecExt.transInfo;
					ArrayList<ColumnDTO> columnList = srcTgtInfo.colDTOList;
					tgtColDTO = Utils.getColumnDTOByName (columnList, tgtDataElem.getTargetDatsetRef()+"."+tgtDataElem.getTargetDataElementRef());

				}
				else if (tgtRecExt.transType.equalsIgnoreCase("Sequence Generator"))
				{
					SeqGenInfo srcTgtInfo = (SeqGenInfo)tgtRecExt.transInfo;
					ArrayList<ColumnDTO> columnList = srcTgtInfo.colDTOList;
					tgtColDTO = Utils.getColumnDTOByName (columnList, tgtDataElem.getTargetDatsetRef()+"."+tgtDataElem.getTargetDataElementRef());

				}
				else if (tgtRecExt.transType.equalsIgnoreCase("Union Transformation"))
				{
					UnionInfo srcTgtInfo = (UnionInfo)tgtRecExt.transInfo;
					ArrayList<ColumnDTO> columnList = srcTgtInfo.colDTOList;
					tgtColDTO = Utils.getColumnDTOByName (columnList, tgtDataElem.getTargetDatsetRef()+"."+tgtDataElem.getTargetDataElementRef());

				}

				if (tgtColDTO == null)
					continue;

				String inputElemName = "";
				if (tgtDataElem.getDirectSourceDataElementRef() != null)
				{
					inputElemName = tgtDataElem.getDirectSourceDataElementRef().getSourceFeedRef() + "." +
							tgtDataElem.getDirectSourceDataElementRef().getSourceDataElementRef();

				}
				else
				{
					inputElemName = tgtDataElem.getTargetDatsetRef() + "." +  tgtDataElem.getTargetDataElementRef();

					for (int j = 0; j < tgtDataElem.getTransformationRule().size(); j++) 
					{
						TransformationRule rule = tgtDataElem.getTransformationRule().get(j);
						List<String> inputDataElements = Utils.getInputDataElements(rule);
						
						for (int k = 0; k < inputDataElements.size(); k++)
						{
							inputElemName = inputDataElements.get(k);

							colDTO = Utils.getColumnDTOByNameInTransRec (tgtRecExt, inputElemName);
							if (colDTO == null)
								continue;

							tgtColDTO.srcColumn = colDTO;
							tgtColDTO.isBeingUsed = 'Y';

							MapPorts.identifyAndUpdateUsedPorts (colDTO);
						}
						//}
					}
				}
			}
		}

		for( int j = 0; j < dataMapping.getDataTransformations().getDataTransformation().size(); j++)
		{
			DataTransformation dt = dataMapping.getDataTransformations().getDataTransformation().get(j);
			TransRecExtDTO transRec = Utils.getTransRecDTOByName(recsList, dt.getName());

			if (dt.getTransformationType().equalsIgnoreCase("Lookup"))
			{
				LookupParser.processLookupUsedColumns(recsList, dt.getLookup(), transRec, dataMapping);
			}
		}

		return;
	}

	private static final void cleanUpUnusedPorts (List<TransRecExtDTO> recsList)
	{
		TransRecExtDTO transRec;
		ArrayList<ColumnDTO> colList;

		for( int i = 0; i < recsList.size(); i++)
		{
			transRec = recsList.get(i);

			if (transRec.transInfo instanceof SrcTgtInfo)
				continue;
			transRec.transColsList = null;

			for (int j = 0; j < transRec.outRecsList.size(); j++)
			{
				OutRecDTO outRec = transRec.outRecsList.get(j);
				colList = outRec.columnList;
				for (int k = colList.size() - 1; k >= 0; k--)
				{

					if (colList.get(k).isBeingUsed == 'N')
						colList.remove(k);
				}
			}
		}
	}

	//Ashiya
	private static final void updateTargetColumnNameInPrevComp (List<TransRecExtDTO> recsList)
	{

		TransRecExtDTO tgtRecExt = new TransRecExtDTO();

		for( int i = 0; i < recsList.size(); i++)
		{
			tgtRecExt = recsList.get(i);

			if (!tgtRecExt.transType.equalsIgnoreCase("Target"))
				continue;

			SrcTgtInfo srcTgt = (SrcTgtInfo) tgtRecExt.transInfo;
			if (srcTgt.srcTgtColsList.size() <= 0)
				continue;

			for( int j = 0; j < srcTgt.srcTgtColsList.size(); j++)
			{
				ColumnDTO col = srcTgt.srcTgtColsList.get(j);
				if (col.srcColumn == null)
					continue;

				String colName = col.srcColumn.colStruct.name;//col.srcColumn.srcColumn.colStruct.name;
				String srcCol = colName.substring(colName.indexOf(".") + 1, colName.length());
				if (srcCol.equalsIgnoreCase(col.colStruct.name))
					continue;

				int count=0;
				for(int m=j;m<srcTgt.srcTgtColsList.size();m++)
				{   
					ColumnDTO colname1 = srcTgt.srcTgtColsList.get(m);
					if(!colname1.colStruct.name.equalsIgnoreCase(srcCol))
					{
						if (srcTgt.srcTgtColsList.get(m).srcColumn == null)
							continue;

						if(srcTgt.srcTgtColsList.get(m).srcColumn.colStruct.name.equalsIgnoreCase(colName)){
							count++;

						}}
				}

				//compare srccol with inrecslist/prevoutrec/collist
				for (int k = 0; k < tgtRecExt.inRecsList.size(); k++)
				{   
					int flag=0; 

					InRecDTO inRec = tgtRecExt.inRecsList.get(k);
					for (int l = 0; l < inRec.prevOutRec.columnList.size(); l++)
					{  

						ColumnDTO colDTO=inRec.prevOutRec.columnList.get(l);

						ColumnStruct colStruct1=new ColumnStruct();
						ColumnDTO tempcol = new ColumnDTO();
						tempcol.colStruct = new ColumnStruct();
						if (colDTO.colStruct.name.equalsIgnoreCase(col.srcColumn.colStruct.name))
						{   
							if(count==1){

								tempcol.colStruct.name=tgtRecExt.compName+"."+col.colStruct.name;
								tempcol.colStruct.dataType = colDTO.colStruct.dataType;
								tempcol.colStruct.sqlType = colDTO.colStruct.sqlType;
								tempcol.colStruct.length = colDTO.colStruct.length;
								tempcol.colStruct.precision = colDTO.colStruct.precision;
								tempcol.colStruct.Scale = colDTO.colStruct.Scale;
								tempcol.colStruct.description = colDTO.colStruct.description;
								tempcol.colStruct.intialValue = colDTO.colStruct.intialValue;
								tempcol.colStruct.keyType = colDTO.colStruct.keyType;
								tempcol.colStruct.isNullable = colDTO.colStruct.isNullable;
								tempcol.isBeingUsed=colDTO.isBeingUsed;
								tempcol.parentOutRec=colDTO.parentOutRec;
								tempcol.srcColumn=colDTO.srcColumn;
								if(flag==0){
									inRec.prevOutRec.columnList.add(tempcol);
									flag++;
								}
								int occur=0;
								for(int n=0;n<srcTgt.srcTgtColsList.size();n++)
								{ 
									ColumnDTO cols=srcTgt.srcTgtColsList.get(n);
									String cols2=colDTO.colStruct.name.substring(colDTO.colStruct.name.indexOf(".")+1);
									if(cols.colStruct.name.equalsIgnoreCase(cols2))
										occur++;
								}
								if(occur==0)
									inRec.prevOutRec.columnList.remove(colDTO);

							}

							else{


								tempcol.colStruct.name=tgtRecExt.compName+"."+col.colStruct.name;
								tempcol.colStruct.dataType = colDTO.colStruct.dataType;
								tempcol.colStruct.sqlType = colDTO.colStruct.sqlType;
								tempcol.colStruct.length = colDTO.colStruct.length;
								tempcol.colStruct.precision = colDTO.colStruct.precision;
								tempcol.colStruct.Scale = colDTO.colStruct.Scale;
								tempcol.colStruct.description = colDTO.colStruct.description;
								tempcol.colStruct.intialValue = colDTO.colStruct.intialValue;
								tempcol.colStruct.keyType = colDTO.colStruct.keyType;
								tempcol.colStruct.isNullable = colDTO.colStruct.isNullable;
								tempcol.isBeingUsed=colDTO.isBeingUsed;
								tempcol.parentOutRec=colDTO.parentOutRec;
								tempcol.srcColumn=colDTO.srcColumn;
								if(flag==0){
									inRec.prevOutRec.columnList.add(tempcol);
									flag++;
								}
							}
						}
					}
				}
			}
		}

	}


	private static final void populateSeqGenPrevCompName (DataMapping dataMapping)
	{
		String seqGenName;

		for(int i = 0; i < dataMapping.getDataTransformations().getDataTransformation().size(); i++)
		{
			DataTransformation dtSeqGen = dataMapping.getDataTransformations().getDataTransformation().get(i);
			if (!dtSeqGen.getTransformationType().equalsIgnoreCase("Sequence Generator"))
				continue;
			if (!(dtSeqGen.getPrevComponentRef().length() <= 0))
				continue;

			seqGenName = dtSeqGen.getName();

			List<DataTransformation> seqGenTgtTransList = new ArrayList<DataTransformation>();

			for(int k1 = 0; k1 < dataMapping.getDataTransformations().getDataTransformation().size(); k1++)
			{
				DataTransformation dt = dataMapping.getDataTransformations().getDataTransformation().get(k1);
				if (dt.getPrevComponentRef() != null)
				{
					if (dt.getPrevComponentRef().toUpperCase().contains(seqGenName.toUpperCase()))
					{
						seqGenTgtTransList.add(dt);
					}
				}
			}

			for (int k1 = 0; k1 < seqGenTgtTransList.size(); k1++)
			{
				StringTokenizer tokenizer = new StringTokenizer(seqGenTgtTransList.get(k1).getPrevComponentRef(), ";");

				List<String> prevCompNames = new ArrayList<String>();
				while (tokenizer.hasMoreTokens())
				{
					prevCompNames.add(tokenizer.nextToken());
				}

				if (prevCompNames.size() <= 1)
					continue;

				DataTransformation dtSeqGenNew = null;
				String newSeqGenName = "";
				if (k1 == seqGenTgtTransList.size() - 1)  // For the last element use the same SeqGenerator object
				{
					dtSeqGenNew = dtSeqGen;
					newSeqGenName = dtSeqGen.getName();
				}
				else
				{
					newSeqGenName = dtSeqGen.getName() + (k1+1);
					dtSeqGenNew = new DataTransformation();
					dtSeqGenNew.setName(newSeqGenName);
					dtSeqGenNew.setTransformationType("Sequence Generator");
					dataMapping.getDataTransformations().getDataTransformation().add(dtSeqGenNew);
				}

				String newTgtPrevCompRef = "";
				String seqGenNewPrevCompName = "";
				for(int j = 0; j < prevCompNames.size(); j++)
				{
					if (prevCompNames.get(j).equalsIgnoreCase(dtSeqGen.getName()))
						continue;
					if (seqGenNewPrevCompName.length() <= 0)
					{
						seqGenNewPrevCompName = prevCompNames.get(j);
						continue;
					}
					if (newTgtPrevCompRef.length() > 0)
						newTgtPrevCompRef += ";";
					newTgtPrevCompRef += prevCompNames.get(j);
				}

				dtSeqGenNew.setPrevComponentRef(seqGenNewPrevCompName);

				String prevCompRef = newTgtPrevCompRef;
				if (prevCompRef.length() <= 0)
					prevCompRef = dtSeqGenNew.getName();
				else
					prevCompRef = prevCompRef + ";" + dtSeqGenNew.getName();
				seqGenTgtTransList.get(k1).setPrevComponentRef(prevCompRef);

				changeNextComponentRefToSeqGenerator(dataMapping, seqGenNewPrevCompName, seqGenTgtTransList.get(k1).getName(), dtSeqGenNew.getName());
			}
		}

		return;
	}

	public static void changeNextComponentRefToSeqGenerator(DataMapping dataMapping, String compName,
			String tgtCompName, String newTgtCompName)
	{
		for (int i = 0; i < dataMapping.getDataTransformations().getDataTransformation().size(); i++)
		{
			DataTransformation dt = dataMapping.getDataTransformations().getDataTransformation().get(i);

			if (!dt.getName().equalsIgnoreCase(compName))
				continue;

			if (dt.getTransformationType().equalsIgnoreCase("Conditional Grouping"))
			{
				for (int j = 0; j < dt.getConditionalGrouping().getOutputGroup().size(); j++)
				{
					OutputGroup outputGrp = dt.getConditionalGrouping().getOutputGroup().get(j);
					if (!outputGrp.getNextComponentRef().contains(tgtCompName))
						continue;
					StringTokenizer tokenizer = new StringTokenizer(outputGrp.getNextComponentRef(), ";");

					String nextCompName, nextCompNamesRef = "";
					while (tokenizer.hasMoreTokens())
					{
						nextCompName = tokenizer.nextToken();
						if (nextCompNamesRef.length() > 0)
							nextCompNamesRef += ";";
						if (nextCompName.equalsIgnoreCase(tgtCompName))
							nextCompNamesRef += newTgtCompName;
						else
							nextCompNamesRef += nextCompName;
					}
					outputGrp.setNextComponentRef(nextCompNamesRef);
				}
			}
			else
			{
				if ((dt.getNextComponentRef() != null) && (dt.getNextComponentRef().contains(tgtCompName)))
				{
					StringTokenizer tokenizer = new StringTokenizer(dt.getNextComponentRef(), ";");

					String nextCompName, nextCompNamesRef = "";
					while (tokenizer.hasMoreTokens())
					{
						nextCompName = tokenizer.nextToken();
						if (nextCompNamesRef.length() > 0)
							nextCompNamesRef += ";";
						if (nextCompName.equalsIgnoreCase(tgtCompName))
							nextCompNamesRef += newTgtCompName;
						else
							nextCompNamesRef += nextCompName;
					}
					dt.setNextComponentRef(nextCompNamesRef);
				}
			}

			return;
		}

		return;
	}

	private static void suffixNumbersWithOutRecDuplicateColumnNames(ArrayList<TransRecExtDTO> recsList)
	{

		for (int i = 0; i < recsList.size(); i++)
		{
			TransRecExtDTO transRec = recsList.get(i);

			for (int j = 0; j < transRec.outRecsList.size(); j++)
			{
				OutRecDTO outRec = transRec.outRecsList.get(j);


				for (int k = 0; k < outRec.columnList.size(); k++)
				{
					ColumnDTO columnDTO = outRec.columnList.get(k);

					String colName = columnDTO.colStruct.name;
					String elemName = colName.substring(colName.indexOf(".") + 1 ,colName.length());

					boolean bDuplicate = false;
					ArrayList<ColumnDTO> dupColsList = new ArrayList<ColumnDTO>();

					for (int k1 = k + 1; k1 < outRec.columnList.size(); k1++)
					{
						ColumnDTO nextColumnDTO = outRec.columnList.get(k1);

						String nextColName = nextColumnDTO.colStruct.name;
						String nextElemName = nextColName.substring(nextColName.indexOf(".") + 1);
						//if (elemName.equalsIgnoreCase(nextElemName))
						if (colName.equalsIgnoreCase(nextColName))
						{
							bDuplicate = true;
							dupColsList.add(nextColumnDTO);
						}

					}
					if (bDuplicate)
						dupColsList.add(columnDTO);

					ColumnDTO colDTO = null, colDTO1 = null;
					String stageType = "", stageType1 = "";
					for (int l = 0; l < dupColsList.size()-1; l++)
					{
						if (l == 0)
						{
							colDTO = dupColsList.get(l);
							stageType = getStageType(colDTO.colStruct.name.substring(0, colDTO.colStruct.name.indexOf(".")), recsList);
						}
						else
						{
							colDTO1 = dupColsList.get(l);
							stageType1 = getStageType(colDTO1.colStruct.name.substring(0, colDTO1.colStruct.name.indexOf(".")), recsList);
						}
						//}
						if (stageType1.equalsIgnoreCase("Source"))
							outRec.columnList.remove(colDTO1);
						else
							outRec.columnList.remove(colDTO);

					}
				}

			}
		}
	}

	private static String getStageType(String compName, ArrayList<TransRecExtDTO> recsList)
	{
		String stageType = "";
		for (int i = 0; i < recsList.size(); i++)
		{
			TransRecExtDTO transRec = recsList.get(i);
			if (!transRec.compName.equalsIgnoreCase(compName))
				continue;
			stageType = transRec.transType;
			return stageType;
		}
		return null;
	}


	private static void identifyAndAddJoinerForAggregatorPassiveColumns (ArrayList<TransRecExtDTO> recsList)
	{
		String colName;

		for (int i = 0; i < recsList.size(); i++)
		{
			TransRecExtDTO transRec = recsList.get(i);

			if (transRec.transInfo  == null)
				continue;
			if (!(transRec.transInfo instanceof AggInfo))
				continue;

			for (int j = 0; j < transRec.outRecsList.size(); j++)
			{
				OutRecDTO outRec = transRec.outRecsList.get(j);

				ArrayList<ColumnDTO> activeCols = new ArrayList<ColumnDTO>();
				ArrayList<String> activeColNames = new ArrayList<String>();
				ArrayList<ColumnDTO> grpByKeyCols = new ArrayList<ColumnDTO>();
				ArrayList<String> grpByKeyColNames = new ArrayList<String>();

				ArrayList<ColumnDTO> passiveCols = new ArrayList<ColumnDTO>();
				ArrayList<String> passiveColNames = new ArrayList<String>();

				// Extract Active Cols from the output group
				for (int k = 0; k < outRec.columnList.size(); k++)
				{
					ColumnDTO colDTO = outRec.columnList.get(k);

					if (colDTO.srcColumn.transRule == null)
						continue;

					colName = colDTO.srcColumn.colStruct.name;
					int nIndex = colName.indexOf(".");
					if (nIndex >= 0)
						colName = colName.substring(nIndex + 1);
					colName = colName.toLowerCase();
					activeColNames.add(colName);
					activeCols.add(colDTO);

					for (int k1 = 0; k1 < colDTO.srcColumn.transRule.srcColumns.size(); k1++)
					{
						if (colDTO.srcColumn.transRule.srcColumns.get(k1) == null)
							continue;
						colDTO = colDTO.srcColumn.transRule.srcColumns.get(k1);
						colName = colDTO.colStruct.name;
						nIndex = colName.indexOf(".");
						if (nIndex >= 0)
							colName = colName.substring(nIndex + 1);
						colName = colName.toLowerCase();
						activeColNames.add(colName);
						activeCols.add(colDTO);
					}

					for (int k1 = 0; k1 < colDTO.srcColumn.transRule.grpByColumns.size(); k1++)
					{
						//						colDTO = colDTO.srcColumn.transRule.grpByColumns.get(k1);
						ColumnDTO colDTO1 = colDTO.srcColumn.transRule.grpByColumns.get(k1);
						colName = colDTO1.colStruct.name;
						nIndex = colName.indexOf(".");
						if (nIndex >= 0)
							colName = colName.substring(nIndex + 1);
						colName = colName.toLowerCase();
						activeColNames.add(colName);
						activeCols.add(colDTO1);

						grpByKeyColNames.add(colName);
						grpByKeyCols.add(colDTO1);
					}

				}

				// Extract Agg Passive Cols from the output group
				for (int k = 0; k < outRec.columnList.size(); k++)
				{
					ColumnDTO colDTO = outRec.columnList.get(k);

					colName = colDTO.srcColumn.colStruct.name;
					int nIndex = colName.indexOf(".");
					if (nIndex >= 0)
						colName = colName.substring(nIndex + 1);
					colName = colName.toLowerCase();

					if (activeColNames.contains(colName))
						continue;

					passiveColNames.add (colName);
					passiveCols.add (colDTO);
				}

				addJoinerForAggPassiveColumns (recsList, transRec, outRec, grpByKeyCols, grpByKeyColNames, passiveCols, passiveColNames);
			}
		}
	}

	private static void addJoinerForAggPassiveColumns (ArrayList<TransRecExtDTO> recsList, TransRecExtDTO aggRec, OutRecDTO aggOutRec,
			ArrayList<ColumnDTO> grpByKeyCols, ArrayList<String> grpByKeyColNames,
			ArrayList<ColumnDTO> passiveCols, ArrayList<String> passiveColNames)
	{
		if (passiveColNames.size() <= 0)
			return;

		if (aggRec.inRecsList.size() <= 0)
			return;

		TransRecExtDTO jnrRec = new TransRecExtDTO();
		jnrRec.compName = Utils.getNewComponentName (aggRec.compName, recsList);
		jnrRec.transType = "Joiner Transformation";
		recsList.add(jnrRec);

		OutRecDTO prevOutRec = aggRec.inRecsList.get(0).prevOutRec;

		jnrRec.prevCompList.add((TransRecExtDTO)prevOutRec.parentTransRec);
		jnrRec.prevCompList.add(aggRec);
		jnrRec.nextCompList.add((TransRecExtDTO)aggOutRec.nextInRec.parentTransRec);

		// Add Joiner In and Out Recs
		InRecDTO jnrInRec1 = new InRecDTO();
		InRecDTO jnrInRec2 = new InRecDTO();
		jnrRec.inRecsList.add(jnrInRec1);
		jnrRec.inRecsList.add(jnrInRec2);
		OutRecDTO jnrOutRec = new OutRecDTO();
		jnrRec.outRecsList.add(jnrOutRec);

		String linkName = jnrRec.compName + "Out";
		jnrOutRec.name = linkName;
		jnrOutRec.parentTransRec = jnrRec;
		jnrOutRec.nextInRec = aggOutRec.nextInRec;

		jnrOutRec.nextInRec.prevOutRec = jnrOutRec;
		jnrOutRec.nextInRec.name = linkName;


		OutRecDTO newPrevOutRec = new OutRecDTO();

		linkName = prevOutRec.parentTransRec.compName + "Out";
		linkName = linkName + prevOutRec.parentTransRec.outRecsList.size();

		jnrInRec1.name = linkName;
		jnrInRec1.parentTransRec = jnrRec;
		jnrInRec1.prevOutRec = newPrevOutRec;
		jnrInRec1.linkType = InRecDTO.LINK_TYPE_MASTER;

		newPrevOutRec.name = linkName;
		newPrevOutRec.parentTransRec = prevOutRec.parentTransRec;
		newPrevOutRec.nextInRec = jnrInRec1;
		prevOutRec.parentTransRec.outRecsList.add(newPrevOutRec);


		jnrInRec2.name = aggOutRec.name;
		jnrInRec2.parentTransRec = jnrRec;
		jnrInRec2.prevOutRec = aggOutRec;
		jnrInRec2.linkType = InRecDTO.LINK_TYPE_DETAIL;

		aggOutRec.nextInRec = jnrInRec2;

		addAggJoinerMasterInputColumns (newPrevOutRec, prevOutRec, jnrOutRec, grpByKeyCols, grpByKeyColNames, passiveCols, passiveColNames);
		addAggJoinerDetailInputColumns (aggOutRec, jnrOutRec, grpByKeyCols, grpByKeyColNames, passiveCols, passiveColNames);

		JoinerParser transInfo = new JoinerParser();
		jnrRec.transInfo = transInfo;

		transInfo.joinType = "Outer Join";
		transInfo.useSortedInput = false;
		transInfo.masterElemList = new ArrayList<String>();
		transInfo.detailElemList = new ArrayList<String>();

		for (int i = 0; i < grpByKeyColNames.size(); i++)
		{
			String joinColName = "." + grpByKeyColNames.get(i);

			for (int j = 0; j < newPrevOutRec.columnList.size(); j++)
			{
				ColumnDTO colDTO = newPrevOutRec.columnList.get(j);
				if (colDTO.colStruct.name.toLowerCase().endsWith(joinColName))
				{
					transInfo.masterElemList.add(colDTO.colStruct.name);
					transInfo.masterCols.add(colDTO);
					break;
				}
			}

			for (int j = 0; j < aggOutRec.columnList.size(); j++)
			{
				ColumnDTO colDTO = aggOutRec.columnList.get(j);
				if (colDTO.colStruct.name.toLowerCase().endsWith(joinColName))
				{
					transInfo.detailElemList.add(colDTO.colStruct.name);
					transInfo.detailCols.add(colDTO);
					break;
				}
			}
		}
	}

	private static void addAggJoinerMasterInputColumns (OutRecDTO newOutRec, OutRecDTO prevOutRec, OutRecDTO jnrOutRec,
			ArrayList<ColumnDTO> grpByKeyCols, ArrayList<String> grpByKeyColNames,
			ArrayList<ColumnDTO> passiveCols, ArrayList<String> passiveColNames)
	{
		String colName;
		ColumnDTO colDTO, colDTO1, colDTO2;

		for (int i = 0; i < prevOutRec.columnList.size(); i++)
		{
			colDTO = prevOutRec.columnList.get(i);

			if (colDTO.srcColumn == null)
				continue;

			colName = colDTO.srcColumn.colStruct.name;
			int nIndex = colName.indexOf(".");
			if (nIndex >= 0)
				colName = colName.substring(nIndex + 1);
			colName = colName.toLowerCase();

			if (passiveColNames.contains(colName))
			{
				colDTO1 = Utils.getColumnClone(colDTO.srcColumn, newOutRec);
				colDTO1.srcColumn = colDTO.srcColumn;
				newOutRec.columnList.add (colDTO1);

				colDTO2 = Utils.getColumnClone(colDTO1, jnrOutRec);
				colDTO2.srcColumn = colDTO1;
				jnrOutRec.columnList.add (colDTO2);
			}
			else if (grpByKeyColNames.contains(colName))
			{
				colDTO1 = Utils.getColumnClone(colDTO.srcColumn, newOutRec);
				colDTO1.srcColumn = colDTO.srcColumn;
				newOutRec.columnList.add (colDTO1);
			}
		}
	}

	private static void addAggJoinerDetailInputColumns (OutRecDTO aggOutRec, OutRecDTO jnrOutRec,
			ArrayList<ColumnDTO> grpByKeyCols, ArrayList<String> grpByKeyColNames,
			ArrayList<ColumnDTO> passiveCols, ArrayList<String> passiveColNames)
	{
		String colName;
		ColumnDTO colDTO, colDTO1;
		ArrayList<String> missingGrpByColNames = new ArrayList<String>();

		missingGrpByColNames.addAll(grpByKeyColNames);
		for (int i = 0; i < aggOutRec.columnList.size(); i++)
		{
			colDTO = aggOutRec.columnList.get(i);

			if (colDTO.srcColumn == null)
				continue;

			colName = colDTO.srcColumn.colStruct.name;
			int nIndex = colName.indexOf(".");
			if (nIndex >= 0)
				colName = colName.substring(nIndex + 1);
			colName = colName.toLowerCase();

			if (passiveColNames.contains(colName))
			{
				aggOutRec.columnList.remove(i);
				i--;
				continue;
			}

			if (missingGrpByColNames.contains(colName))
				missingGrpByColNames.remove(colName);

			colDTO1 = Utils.getColumnClone(colDTO, jnrOutRec);
			colDTO1.srcColumn = colDTO;
			jnrOutRec.columnList.add (colDTO1);
		}

		for (int i = 0; i < grpByKeyCols.size(); i++)
		{
			colDTO = grpByKeyCols.get(i);

			if (colDTO.srcColumn == null)
				continue;

			colName = colDTO.srcColumn.colStruct.name;
			int nIndex = colName.indexOf(".");
			if (nIndex >= 0)
				colName = colName.substring(nIndex + 1);
			colName = colName.toLowerCase();

			if (missingGrpByColNames.contains(colName))
			{
				colDTO1 = Utils.getColumnClone(colDTO, jnrOutRec);
				colDTO1.srcColumn = colDTO;
				jnrOutRec.columnList.add (colDTO1);
			}
		}
	}

	public static final void removeTransComponent (List<TransRecExtDTO> recsList, String removeStageName)
	{
		if(removeStageName==null)
			return;
		TransRecExtDTO removeStage = Utils.getTransRecDTOByName(recsList, removeStageName);
		if(removeStage==null)
			return;
		if(removeStage.prevCompList==null)
			return;
		for(TransRecExtDTO preComp : removeStage.prevCompList){
			preComp.nextComp.addAll(removeStage.nextComp);
			preComp.nextCompList.addAll(removeStage.nextCompList);
			preComp.outRecsList.addAll(removeStage.outRecsList);
			if(removeStage.transColsList!=null)
				preComp.transColsList.addAll(removeStage.transColsList);
			if(preComp.outRecsList!=null){
				for(OutRecDTO preOutRec : preComp.outRecsList){
					preOutRec.parentTransRec = preComp;
					for(ColumnDTO col : preOutRec.columnList){
						String comp = col.colStruct.name.substring(0, col.colStruct.name.indexOf("."));
						if(comp.equalsIgnoreCase(removeStage.compName)){
							col.colStruct.name = preComp.compName+"."+col.srcColumn.colStruct.name.substring(col.srcColumn.colStruct.name.indexOf(".")+1);
						}
					}
				}
			}
			if(preComp.transColsList!=null)
				for(ColumnDTO col : preComp.transColsList){
					String comp = col.colStruct.name.substring(0, col.colStruct.name.indexOf("."));
					if(comp.equalsIgnoreCase(removeStage.compName)){
						col.colStruct.name = preComp.compName+"."+col.srcColumn.colStruct.name.substring(col.srcColumn.colStruct.name.indexOf(".")+1);
					}

				}
		}

		if(removeStage.nextCompList==null)
			return;
		for(TransRecExtDTO preComp : removeStage.nextCompList){
			preComp.prevCompList = removeStage.prevCompList;
			preComp.previousComp = removeStage.previousComp;
			preComp.inRecsList = removeStage.inRecsList;

		}
	}
}
