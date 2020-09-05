
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dataSwitch.xml.ETLMapDTO.BaseTransInfo;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.RouterInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.ConditionalGrouping.OutputGroup;

public class RouterParser extends BaseTransInfo
{
	public final static void extractAllUsedSourceDataElementsFromCondition (String condition, List<TransRecExtDTO> recsList, ArrayList<String> srcCompNames, HashMap<String, ArrayList<ColumnDTO>> srcCompsColumnList)
	{
		String[] elemList = getDataElements (condition);

		for (int i = 0; i < elemList.length; i++)
		{
			int nIndex = elemList[i].indexOf(".");
			String srcDataRef = "", srcDataElementRef = "";

			if (nIndex == -1)
			{
				if (srcCompNames.size() == 1)
					srcDataRef = srcCompNames.get(0);
				srcDataElementRef = elemList[i];
			}
			else
			{
				srcDataRef = elemList[i].substring(0, nIndex);
				srcDataElementRef = elemList[i].substring(nIndex + 1);
			}
			srcDataRef = srcDataRef.trim();
			srcDataElementRef = srcDataElementRef.trim();

			if (srcDataRef.length() <= 0)
			{
				String elemName = "." + srcDataElementRef.toLowerCase();
				for (int i1 = 0; i1 < srcCompNames.size(); i1++)
				{
					ArrayList<ColumnDTO> srcColList = srcCompsColumnList.get(srcCompNames.get(i1));

					for (int k = 0; k < srcColList.size(); k++)
					{
						String colName = srcColList.get(k).colStruct.name;
						if (colName.toLowerCase().endsWith(elemName))
						{
							srcDataRef = srcCompNames.get(i1);
							break;
						}
					}
				}
			}
			if (srcDataElementRef.length() <= 0)//for filter bnk_details.bnk_id&gt;1000
				continue;
			
			MapPorts.cloneAndAddSourceDataElement (srcDataRef, srcDataElementRef,
											recsList, srcCompNames, srcCompsColumnList);
		}

		//To avoid duplicate entry in outRecsList
		/*for (int j = 0; j < srcColList.size(); j++)
		{System.out.println("srcColList.size()==========="+srcColList.size());
//			System.out.println("srcColList.get(j).colStruct.name.size()==========="+srcColList.get(j).colStruct.name+" "+newCol.colStruct.name);
			for (int k = 0; k < newColList.size(); k++)
			{
				if (!srcColList.get(j).colStruct.name.contains(newColList.get(k).colStruct.name))
				{System.out.println("srcColList.ge   yyyy "+srcColList.get(j).colStruct.name);
//					srcColList.get(j).colStruct.name.add(newCol.colStruct.name);
				}
				else
				{System.out.println("srcColList.ge   nnnn "+srcColList.get(j).colStruct.name);
//					srcColList.get(j).colStruct.name.add(newCol.colStruct.name);
				}
			}
		}*/

		return;
	}

	public final static String[] getDataElements (String condition)
	{
		condition = condition.replaceAll("\\(|\\)", " ");
		String patternFinal=  "([ \t](AND|OR)[ \t])|([<>=])" ;
		Pattern pat = Pattern.compile(patternFinal);
		Matcher m = pat.matcher(condition);
		String[] elemList = null;
		while (m.find())
		{
			String mystr = m.group();
			condition = condition.replaceAll(mystr," ");
		}
		elemList = condition.split(" ");

		return elemList;
	}

	public final static void processRouterTransformation (TransRecExtDTO transRec, DataTransformation dt)
	{
		String grpName, condition;

		RouterInfo transInfo = (RouterInfo) transRec.transInfo;
		if (transInfo != null)
			return;
		transInfo = new RouterInfo();
		transRec.transInfo = transInfo;
		if(dt.getConditionalGrouping() !=null)
			{
					for (int j = 0; j < dt.getConditionalGrouping().getOutputGroup().size(); j++)
					{
						OutputGroup grp = dt.getConditionalGrouping().getOutputGroup().get(j);
			
						grpName = grp.getGroupName();
						condition = grp.getFilterCondition();
			
						transInfo.grpNamesList.add (grpName);
						transInfo.routerCondList.add(condition);
			
						String szNextCompName;
						List<OutRecDTO> outRecList = new ArrayList<OutRecDTO>();
						for (int i = 0; i < transRec.outRecsList.size(); i++)
						{
							szNextCompName = transRec.outRecsList.get(i).nextInRec.parentTransRec.compName;
							
							List<String> nextCompList = new ArrayList<String>();
							if (grp.getNextComponentRef().contains(";"))
								{
									String [] str = grp.getNextComponentRef().split(";");
								for (int k = 0; k < str.length; k++)
								{
									if (!str[k].equalsIgnoreCase(""))
										{
											nextCompList.add(str[k]);
										}
									}
								}
							else
							{
								nextCompList.add(grp.getNextComponentRef());
							}
			
							for (int k = 0; k < nextCompList.size(); k++) 
							{
								if (szNextCompName.equalsIgnoreCase(nextCompList.get(k)))
								{
									outRecList.add(transRec.outRecsList.get(i));
									
								}
							}
			
			/*if (szNextCompName.equalsIgnoreCase(grp.getNextComponentRef()))
			{
				transInfo.grpOutRecList.put(grpName, transRec.outRecsList.get(i));
				break;
			}*/
			}
			
					transInfo.grpOutRecList.put(grpName, outRecList);
					
					
					ArrayList<ColumnDTO> conditionCols = new ArrayList<ColumnDTO>();
					transInfo.grpConditionCols.add(conditionCols);
					
					
					String[] elemList = getDataElements(condition);
					
					
					
					for (int i = 0; i < elemList.length; i++)
					{
						String compName = "";
					String dataElementName = elemList[i];
					
					int nIndex = elemList[i].indexOf(".");
					
					if (nIndex != -1)
					{
						compName = elemList[i].substring(0, nIndex);
						dataElementName = elemList[i].substring (nIndex + 1);
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
					
							ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec (transRec, compName, dataElementName);
							if (colDTO != null)
								conditionCols.add(colDTO);
						}
						transInfo.grpConditionCols.add(conditionCols);
					}
			}
	
		}
}