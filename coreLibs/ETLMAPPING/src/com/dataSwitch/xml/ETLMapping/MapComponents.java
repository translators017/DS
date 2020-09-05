
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.dataSwitch.ds.functionTypeRules.RuleMapping;

import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.ConditionalGrouping.OutputGroup;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SetOperation.InputGroups.InputGroup;
import com.dataSwitch.xml.connectorspecs.InputDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule;

public class MapComponents
{

	public final static void fillTransformationsDataFlow (DataMapping datamapping, List<TransRecExtDTO> recsList, List<RuleMapping> codeGenRuleList) throws Exception
	{

		ArrayList<String> compNames = new ArrayList<String>();
		TransRecExtDTO record;
		HashMap<String, ArrayList<String>> tgtPrevCompsList = new HashMap<String, ArrayList<String>>();
		String tgtName;
		ArrayList<String> tgtPrevComps = null;

		// To avoid null pointer exception in downstream
		if (datamapping.getDataTransformations() == null)
		{
			DataTransformations dtTrans = new DataTransformations();
			datamapping.setDataTransformations(dtTrans);
		}

		// Add Target components and respective Source Components from Source Elements List
		for(int k = 0; k < datamapping.getTargetDataElements().getTargetDataElement().size(); k++)
		{
			TargetDataElementType tgtDataElement = datamapping.getTargetDataElements().getTargetDataElement().get(k);

			tgtName = tgtDataElement.getTargetDatsetRef();

			TransRecExtDTO tgtRecObj = Utils.getTransRecDTOByName(recsList, tgtName);
			if (tgtRecObj == null)
				continue;

			tgtPrevComps = tgtPrevCompsList.get(tgtName);
			if (tgtPrevComps == null) // RecsList has only source and target components at this level
			{
				tgtPrevComps = new ArrayList<String>();
				tgtPrevCompsList.put (tgtName, tgtPrevComps);
			}
			for(int r2 = 0; r2 < tgtDataElement.getTransformationRule().size(); r2++)
			{
				TransformationRule rule = tgtDataElement.getTransformationRule().get(r2);
				List<String> inputDataElements = Utils.getInputDataElements(rule);
				
				populatePrevSourceElementSrcCompsForTarget(datamapping, recsList, tgtName, tgtPrevComps, inputDataElements);
			}

			//getting the component name and identifier and adding them in recsList
			for(int r1 = 0; r1 < tgtDataElement.getTransformationRule().size(); r1++)
			{
				TransformationRule transRule = tgtDataElement.getTransformationRule().get(r1);

				if (!tgtPrevComps.contains(transRule.getComponentName()))
					tgtPrevComps.add(transRule.getComponentName());
			}
		}

		ArrayList<TransRecExtDTO> dataTransList = new ArrayList<TransRecExtDTO>();
		if (datamapping.getDataTransformations() != null)
		{
			for(int k2 = 0; k2 < datamapping.getDataTransformations().getDataTransformation().size(); k2++)
			{
				DataTransformation dt = datamapping.getDataTransformations().getDataTransformation().get(k2);

				if (dt.getTransformationType().equalsIgnoreCase("Source Definition"))
					continue;

				record = Utils.getTransRecDTOByName(recsList, dt.getName());
				if (record == null)
				{
					Map<String,ArrayList<String>> specProp = new HashMap<String,ArrayList<String>>();
					if(dt.getSpecialProperties() != null) 
					{
						Utils.readingSpecialProp(specProp,dt);
					}
					record = new TransRecExtDTO();
					record.compName = dt.getName();
					record.transType = dt.getTransformationType();
					for (Map.Entry mapElement : specProp.entrySet()) 
					{
						if(mapElement.getKey().toString().equalsIgnoreCase(dt.getName())) 
						{
							record.specialProp = (ArrayList<String>) mapElement.getValue();
						}
					}

					if(dt.getRejectOption().length() > 0)
						record.rejectOption = dt.getRejectOption();
					recsList.add(record);
					compNames.add(dt.getName());

					addPreviousCompDataTrans (datamapping, dt, record.previousComp, recsList);
					dataTransList.add(record);
				}
				else
				{
					if (dt.getTransformationType().equalsIgnoreCase("Target Definition"))
						addPreviousCompDataTrans (datamapping, dt, record.previousComp, recsList);

					System.out.println ("ERROR: MapComponents: LineNo: 112 - Duplicate component name for DataTransformation - " + dt.getName());
					continue;
				}
			}
		}

		// Update nextCompRef
		for(int k1 = 0; k1 < datamapping.getDataTransformations().getDataTransformation().size(); k1++)
		{
			DataTransformation dt = datamapping.getDataTransformations().getDataTransformation().get(k1);

			List<String> nextCompNames = getNextCompNames(dt);

			if (nextCompNames == null)
				continue;

			ArrayList<String> newNextNames = new ArrayList<String>();
			for(int i = 0; i < nextCompNames.size(); i++)
			{
				String szNextCompNames = nextCompNames.get(i);
				String namesList[] = szNextCompNames.split("\\;");
				if ((namesList == null) || (namesList.length <= 0))
					continue;
				for (int j = 0; j < namesList.length; j++)
					newNextNames.add(namesList[j]);
			}

			String szCompName = dt.getName();

			for (int rec1 = 0; rec1 < recsList.size(); rec1++)
			{
				record = recsList.get(rec1);
				if (newNextNames.contains(record.compName))
				{
					if (!record.previousComp.contains(szCompName))
						record.previousComp.add(szCompName);
					System.out.println ("ERROR: ToDO: MapComponents: LineNo: 157 - PrevComps are not getting added to the tgtPrevComps list"+szCompName+" "+record.compName);
				}
			}
		}

		populatePrevCompList(recsList, datamapping);
		for (TransRecExtDTO rec : recsList)
		{
			int nSeqNo = getSequenceNumber(rec);
			rec.seqNo = nSeqNo;
		}

		// Update prevCompRef for the Targets
		for (Map.Entry<String, ArrayList<String>> entry : tgtPrevCompsList.entrySet())
		{
			tgtName = entry.getKey();
			tgtPrevComps = entry.getValue();
			if (tgtPrevCompsList.size() == 1)
			{
				for (int i = 0; i < dataTransList.size(); i++)
					tgtPrevComps.add (dataTransList.get(i).compName);
			}

			TransRecExtDTO tgtRec = Utils.getTransRecDTOByName(recsList, tgtName);
			if ((tgtRec.previousComp != null) && (tgtRec.previousComp.size() > 0))
				continue;

			int nMaxSeqNo = -1;
			for (String prevCompName : tgtPrevComps)
			{
				record = Utils.getTransRecDTOByName(recsList, prevCompName);

				if(record == null)
					break;
				if (nMaxSeqNo < record.seqNo)
				{
					nMaxSeqNo = record.seqNo;
					tgtRec.prevCompList = new ArrayList<TransRecExtDTO>();
					tgtRec.prevCompList.add(record);
					tgtRec.previousComp = new ArrayList<String>();
					tgtRec.previousComp.add(record.compName);
				}
				else if (nMaxSeqNo == record.seqNo)
				{
					tgtRec.prevCompList.add(record);
					tgtRec.previousComp.add(record.compName);
				}
			}
			tgtRec.seqNo = nMaxSeqNo + 1;
		}
		populateNextCompList(recsList, datamapping);

		removeOtherTransformationPrevNextLinks (recsList);
	}

	private static void removeOtherTransformationPrevNextLinks (List<TransRecExtDTO> recsList)
	{
		for (int i = 0; i < recsList.size(); i++)
		{
			TransRecExtDTO transRec = recsList.get(i);

			if (!transRec.transType.equalsIgnoreCase("Other Transformation"))
				continue;

			for (int j = 0; j < transRec.prevCompList.size(); j++)
			{
				List<TransRecExtDTO> nextList = transRec.prevCompList.get(j).nextCompList;
				for (int k = 0; k < nextList.size(); k++)
				{
					if (transRec.compName.equalsIgnoreCase (nextList.get(k).compName))
					{
						nextList.remove(k);
						k--;
					}
				}
			}

			for (int j = 0; j < transRec.nextCompList.size(); j++)
			{
				List<TransRecExtDTO> prevList = transRec.nextCompList.get(j).prevCompList;
				for (int k = 0; k < prevList.size(); k++)
				{
					if (transRec.compName.equalsIgnoreCase (prevList.get(k).compName))
					{
						prevList.remove(k);
						k--;
					}
				}
			}

			for (int j = 0; j < transRec.inRecsList.size(); j++)
			{
				OutRecDTO prevOutRec = transRec.inRecsList.get(j).prevOutRec;

				if (prevOutRec == null)
					continue;
				List<OutRecDTO> outRecList = prevOutRec.parentTransRec.outRecsList;

				for (int k = 0; k < outRecList.size(); k++)
				{
					OutRecDTO outRec = outRecList.get(k); 
					if (outRec.nextInRec == null)
						continue;
					if (transRec.compName.equalsIgnoreCase (outRec.nextInRec.parentTransRec.compName))
					{
						outRecList.remove(k);
						k--;
					}
				}
			}

			for (int j = 0; j < transRec.outRecsList.size(); j++)
			{
				InRecDTO prevInRec = transRec.outRecsList.get(j).nextInRec;

				if (prevInRec == null)
					continue;
				List<InRecDTO> inRecList = prevInRec.parentTransRec.inRecsList;

				for (int k = 0; k < inRecList.size(); k++)
				{
					InRecDTO inRec = inRecList.get(k); 
					if (inRec.prevOutRec == null)
						continue;
					if (transRec.compName.equalsIgnoreCase (inRec.prevOutRec.parentTransRec.compName))
					{
						inRecList.remove(k);
						k--;
					}
				}
			}
		}

	}

	private static void populatePrevSourceElementSrcCompsForTarget (DataMapping dataMapping, List<TransRecExtDTO> recsList,
			String tgtName, ArrayList<String> tgtPrevComps, List<String> inputDataElements)
	{
		String srcFeedCompRef, srcElemRef;

		if (inputDataElements == null)
			return;

		for (int i = 0; i < inputDataElements.size(); i++)
		{
			
			srcFeedCompRef = Utils.getCompNamefromVar(inputDataElements.get(i));

			TransRecExtDTO srcRecObj = Utils.getTransRecDTOByName(recsList, srcFeedCompRef);

			// Adding a Source / Aggregator / Expression / SeqGenrator component as targetDataSets's prev component
			if (!tgtPrevComps.contains(srcFeedCompRef))
				tgtPrevComps.add(srcFeedCompRef);

			if (srcRecObj != null)
				continue;

			// SourceComp is an Expression / Aggregator
			srcElemRef = Utils.getElemNamefromVar(inputDataElements.get(i));

			for (int j = 0; j < dataMapping.getTargetDataElements().getTargetDataElement().size(); j++)
			{
				TargetDataElementType tgtElem = dataMapping.getTargetDataElements().getTargetDataElement().get(j);
				if (!srcFeedCompRef.equalsIgnoreCase(tgtElem.getTargetDatsetRef()))
					continue;
				if (!srcElemRef.equalsIgnoreCase(tgtElem.getTargetDataElementRef()))
					continue;
				for(int r2 = 0; r2 < tgtElem.getTransformationRule().size(); r2++)
				{
					TransformationRule rule = tgtElem.getTransformationRule().get(r2);
					List<String> inputDataElementsNew = Utils.getInputDataElements(rule);
					populatePrevSourceElementSrcCompsForTarget (dataMapping, recsList, tgtName, tgtPrevComps, inputDataElementsNew);
				}
				//				populatePrevSourceElementSrcCompsForTarget (dataMapping, recsList, tgtName, tgtPrevComps, tgtElem.getSourceDataElementRef());
			}
		}
	}

	private static final void splitAndUpdateCompNamesList (String szCompNamesList, List<String> prevCompList)
	{
		StringTokenizer tokenizer = new StringTokenizer(szCompNamesList, ";");
		String szCompName;

		while (tokenizer.hasMoreTokens())
		{
			szCompName = tokenizer.nextToken();
			if (!prevCompList.contains(szCompName))
				prevCompList.add(szCompName);
		}
	}

	private static final void addPreviousCompDataTrans (DataMapping dataMapping,
			DataTransformation dt, List<String> prevCompList, List<TransRecExtDTO> recsList)
	{
		String prevCompName;

		//getting all prevComp from Rules

		prevCompName = dt.getPrevComponentRef();
		splitAndUpdateCompNamesList (prevCompName, prevCompList);

		if (dt.getTransformationType().equalsIgnoreCase("Joiner Transformation") ||
				dt.getTransformationType().equalsIgnoreCase("Multi Joiner Transformation"))
		{
			prevCompName = dt.getPrevComponentRef();
			if(prevCompName.contains("."))
				prevCompName = prevCompName.substring(0, prevCompName.indexOf("."));
			splitAndUpdateCompNamesList (prevCompName, prevCompList);
			
		}
		else if(dt.getTransformationType().equalsIgnoreCase("Lookup"))
		{
			for(int d = 0; d < dt.getLookup().getLookupSource().size(); d++)
			{
				LookupSource lkpSource = dt.getLookup().getLookupSource().get(d);

				//prevCompName = lkpSource.getSourceDatasetRef();
				prevCompName = lkpSource.getLkpSourceComponentRef();
				splitAndUpdateCompNamesList (prevCompName, prevCompList);

				for(int j = 0; j < lkpSource.getLookupCondition().getJoinCondition().size(); j++)
				{
					prevCompName = lkpSource.getLookupCondition().getJoinCondition().get(j).getInputDataElement().getInterimDataElementRef();
					if (prevCompName.contains("."))
					{
						prevCompName = prevCompName.substring (0, prevCompName.indexOf("."));
						splitAndUpdateCompNamesList (prevCompName, prevCompList);
					}
				}
			}
		}
		else if (dt.getTransformationType().equalsIgnoreCase("Filter Transformation") )
		{
			if (dt.getFilter().getFilterPosition().equalsIgnoreCase("Pre-Target"))
			{
				String tgtCompName = dt.getFilter().getTargetDatasetRef();
				TransRecExtDTO tgtRec = Utils.getTransRecDTOByName(recsList, tgtCompName);
				if ((tgtRec != null) && (!tgtRec.previousComp.contains(dt.getName())))
					tgtRec.previousComp.add (dt.getName());
			}
		}
		else if(dt.getTransformationType().equalsIgnoreCase("Union Transformation") )
		{
			for(int d = 0; d < dt.getSetOperation().getInputGroups().getInputGroup().size(); d++)
			{
				InputGroup inputGrp = dt.getSetOperation().getInputGroups().getInputGroup().get(d);
				prevCompName = inputGrp.getPrevComponentRef();
				if(prevCompName.contains("."))
					prevCompName = prevCompName.substring(0, prevCompName.indexOf("."));
				splitAndUpdateCompNamesList (prevCompName, prevCompList);
			}
		}

		return;
	}

	private static final List<String> getNextCompNames (DataTransformation datatransformation)
	{
		ArrayList<String> nextCompRef = new ArrayList<String>();

		if (datatransformation.getTransformationType().equalsIgnoreCase("Conditional Grouping"))
		{
			if(datatransformation.getConditionalGrouping()!=null)
			{

				for(int d1 = 0; d1 < datatransformation.getConditionalGrouping().getOutputGroup().size(); d1++)
				{
					OutputGroup outputGroup = datatransformation.getConditionalGrouping().getOutputGroup().get(d1);
					if(outputGroup.getNextComponentRef().contains("."))
					{
						String NextComponent = outputGroup.getNextComponentRef();
						nextCompRef.add (NextComponent.substring(0, NextComponent.indexOf(".")));
					}
					else
					{
						nextCompRef.add (outputGroup.getNextComponentRef());
					}

				}

				return nextCompRef;

			}
			return nextCompRef;
		}
		else if (datatransformation.getTransformationType().equalsIgnoreCase("Filter Transformation") )
		{
			nextCompRef.add(datatransformation.getNextComponentRef());
			return nextCompRef;
		}
		else if (datatransformation.getTransformationType().equalsIgnoreCase("Union Transformation") )
		{
			nextCompRef.add(datatransformation.getNextComponentRef());
			return nextCompRef;
		}

		return null;
	}

	private final static int getSequenceNumber(TransRecExtDTO rec)
	{
		int i;

		if (rec.seqNo >= 0)
			return rec.seqNo;

		if (rec.prevCompList.size() <= 0)
		{
			if (rec.transType.toLowerCase().equalsIgnoreCase("target"))
				return -1;

			if (rec.transType.toLowerCase().equalsIgnoreCase("source"))
				return 0;
			if (rec.transType.toLowerCase().equalsIgnoreCase("lookupsource"))
				return 0;
			if (rec.transType.toLowerCase().equalsIgnoreCase("SequenceGenerator"))
				return 0;

			System.out.println ("ERROR: ToDO: MapComponents: LineNo: 326 - Transformation:" + rec.compName + " - "+ rec.transType + " without PrevComponent(s)");

			return 1;
		}

		int nMaxSeqNo = 0;
		int nSeqNo = 0;
		for (i = 0; i < rec.prevCompList.size(); i++)
		{
			TransRecExtDTO prevRec = rec.prevCompList.get(i);
			nSeqNo = getSequenceNumber (prevRec);
			prevRec.seqNo = nSeqNo;

			if (nMaxSeqNo < nSeqNo)
				nMaxSeqNo = nSeqNo;
		}
		nMaxSeqNo++;

		return nMaxSeqNo;
	}

	private final static void populatePrevCompList (List<TransRecExtDTO> recsList, DataMapping datapipeline)
	{
		TransRecExtDTO record1, record;

		for (int i = 0; i < recsList.size(); i ++)
		{
			record = recsList.get(i);


			for( int x = 0; x < record.previousComp.size(); x ++)
			{
				String compName = "";
				if(record.previousComp.get(x).contains("."))
					compName = record.previousComp.get(x).substring(0,record.previousComp.get(x).indexOf("."));
				else
					compName = record.previousComp.get(x);
					
				record1 = Utils.getTransRecDTOByName(recsList, compName);
				//if (record1 != null)
				if (record1 == null)
					System.out.println ("ERROR: ToDO: MapComponents: LineNo: 354 - Handle Null for Previous component");
				else
					if(!record.prevCompList.contains(record1))
						record.prevCompList.add(record1);
			}
		}
	}

	private final static void populateNextCompList (List<TransRecExtDTO> recsList, DataMapping datapipeline)
	{
		TransRecExtDTO prevRecord, record;
		OutRecDTO outRec;
		InRecDTO inRec;

		for (int i = 0; i < recsList.size(); i ++)
		{
			record = recsList.get(i);

			for( int x = 0; x < record.prevCompList.size(); x ++)
			{
				prevRecord = record.prevCompList.get(x);
				if (prevRecord.nextComp.contains(record.compName))
					continue;

				if (record.transType.equalsIgnoreCase("Source") && prevRecord.transType.equalsIgnoreCase("Conditional Grouping"))
					continue;

				prevRecord.nextComp.add(record.compName);
				prevRecord.nextCompList.add(record);

				inRec = new InRecDTO();
				outRec = new OutRecDTO();

				String linkName = prevRecord.compName + "Out";
				if (prevRecord.nextCompList.size() > 1)
					linkName += (prevRecord.nextCompList.size() - 1);

				inRec.name = linkName;
				inRec.parentTransRec = record;
				inRec.prevOutRec = outRec;
				record.inRecsList.add(inRec);

				outRec.name = linkName;
				outRec.parentTransRec = prevRecord;
				outRec.nextInRec = inRec;
				prevRecord.outRecsList.add(outRec);
			}
		}
	}

}