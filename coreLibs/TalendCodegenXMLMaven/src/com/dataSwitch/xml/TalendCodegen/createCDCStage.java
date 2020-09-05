
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;

public class createCDCStage {

	public static boolean checkCDChasInsrtDelete(ArrayList<TransRecDTO> recsList) 
	{
		for(int i = 0 ; i < recsList.size(); i++)
		{
			TransRecExtDTO recorderPipe = (TransRecExtDTO) recsList.get(i);
			SrcTgtInfo srctgtdb = new SrcTgtInfo();
			if (recorderPipe.transInfo instanceof SrcTgtInfo)
			{
				srctgtdb = (SrcTgtInfo) recorderPipe.transInfo;
			}
			
			if(recorderPipe.transType.equalsIgnoreCase("Other Transformation"))
			{
				if(srctgtdb.fileType.equalsIgnoreCase("Change Data Capture"))
				{
					List<String> propsList = new ArrayList<String>();
					HashMap<String,List<String>> mapInsrtDelete = new HashMap<String,List<String>>();
					for(int i1 = 0; i1 < srctgtdb.transDtls.size(); i1++)
					{
						String cdcProps = srctgtdb.transDtls.get(i1);
						String changeCodeProps = cdcProps.substring(0,  srctgtdb.transDtls.get(i1).indexOf(":")).trim();
						if(changeCodeProps.equalsIgnoreCase("KeepDelete") || changeCodeProps.equalsIgnoreCase("KeepInsert"))
						{
							  propsList.add(changeCodeProps);	
						}
					}
					
					for(int i1 = 0; i1 < srctgtdb.transDtls.size(); i1++)
					{
						String cdcProps = srctgtdb.transDtls.get(i1);
						String changeCodeProps = cdcProps.substring(0,  srctgtdb.transDtls.get(i1).indexOf(":")).trim();
						String changeCodePropVal = cdcProps.substring(srctgtdb.transDtls.get(i1).indexOf(":")+1).trim();
						if(changeCodeProps.equalsIgnoreCase("KeepDelete") || changeCodeProps.equalsIgnoreCase("KeepInsert"))
						{
							mapInsrtDelete.put(changeCodePropVal, propsList);	
						}
					}
					if(mapInsrtDelete.get("Keep") != null)
					{
						List<String> mapValues = mapInsrtDelete.get("Keep");
						if(mapValues.size() > 1)
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean checkCDChasDelete(TransRecExtDTO recorderPipe,SrcTgtInfo srctgtdb) 
	{
		
			if(recorderPipe.transType.equalsIgnoreCase("Other Transformation"))
			{
				if(srctgtdb.fileType.equalsIgnoreCase("Change Data Capture"))
				{
					List<String> propsList = new ArrayList<String>();
					HashMap<String,List<String>> mapInsrtDelete = new HashMap<String,List<String>>();
					for(int i1 = 0; i1 < srctgtdb.transDtls.size(); i1++)
					{
						String cdcProps = srctgtdb.transDtls.get(i1);
						String changeCodeProps = cdcProps.substring(0,  srctgtdb.transDtls.get(i1).indexOf(":")).trim();
						if(changeCodeProps.equalsIgnoreCase("KeepDelete") || changeCodeProps.equalsIgnoreCase("KeepInsert"))
						{
							  propsList.add(changeCodeProps);	
						}
					}
					
					for(int i1 = 0; i1 < srctgtdb.transDtls.size(); i1++)
					{
						String cdcProps = srctgtdb.transDtls.get(i1);
						String changeCodeProps = cdcProps.substring(0,  srctgtdb.transDtls.get(i1).indexOf(":")).trim();
						String changeCodePropVal = cdcProps.substring(srctgtdb.transDtls.get(i1).indexOf(":")+1).trim();
						if(changeCodeProps.equalsIgnoreCase("keepDelete") || changeCodeProps.equalsIgnoreCase("KeepInsert"))
						{
							mapInsrtDelete.put(changeCodePropVal, propsList);	
						}
					}
					if(mapInsrtDelete.get("Keep") != null)
					{
						List<String> mapValues = mapInsrtDelete.get("Keep");
						if(mapValues.size() == 1)
						{
							if(mapValues.contains("KeepDelete"))
							  return true;
						}	
					}
				}
			}
		return false;
	}

	public static void createPipelineForTgtStage(ArrayList<TransRecDTO> recsList) 
	{
		for(int i = 0 ; i < recsList.size(); i++)
		{
			
			TransRecExtDTO recorderPipe = (TransRecExtDTO) recsList.get(i);
			SrcTgtInfo srctgtdb = new SrcTgtInfo();
			
			if (recorderPipe.transInfo instanceof SrcTgtInfo)
			{
				srctgtdb = (SrcTgtInfo) recorderPipe.transInfo;
			}
			
			if(recorderPipe.transType.equalsIgnoreCase("Other Transformation"))
			{
				if(srctgtdb.fileType.equalsIgnoreCase("Change Data Capture"))
				{
					List<TransRecExtDTO> updatedRecList = createCDCStage.updateRecorderPipe(recorderPipe);
					for(int  i1 = 0 ; i1 < updatedRecList.size() ;i1++)
					{
						TransRecExtDTO transRec = updatedRecList.get(i1);
						recsList.add(transRec);
					}
					break;
				}
			}
		}
	}

	
	private static List<TransRecExtDTO> updateRecorderPipe (TransRecExtDTO recorderPipe) 
	{
		List<TransRecExtDTO> recordPipeList = new ArrayList<TransRecExtDTO>();
		TransRecExtDTO rcddPipe1 = new TransRecExtDTO();
		rcddPipe1 = cloneRecorderPipe(rcddPipe1,recorderPipe);
		recordPipeList.add(rcddPipe1);
		
		for(int i = 0 ; i <  recorderPipe.prevCompList.size(); i++)
		{
			TransRecExtDTO recordPipe = recorderPipe.prevCompList.get(i);
			TransRecExtDTO rcddPipe = new TransRecExtDTO();
			rcddPipe1 = cloneRecorderPipe(rcddPipe,recordPipe);
			recordPipeList.add(rcddPipe);	
		    updateRecorderPipeTillSrc(recordPipe,recordPipeList);;
		}
		return recordPipeList;
	}
	
	private static  void updateRecorderPipeTillSrc(TransRecExtDTO recordPipe,List<TransRecExtDTO> recordPipeList) 
	{
		for(int i = 0 ; i <  recordPipe.prevCompList.size(); i++)
		{
			TransRecExtDTO recordNewPipe = recordPipe.prevCompList.get(i);
			
			TransRecExtDTO rcdPipe = new TransRecExtDTO();
			rcdPipe = cloneRecorderPipe(rcdPipe,recordNewPipe);
			recordPipeList.add(rcdPipe);	
			updateRecorderPipeTillSrc(recordNewPipe,recordPipeList);
		}
	}

	public static TransRecExtDTO cloneRecorderPipe(TransRecExtDTO newRecPipe, TransRecExtDTO recorderPipe) 
	{
		newRecPipe.compName = recorderPipe.compName+"_dsCDC";
		newRecPipe.inRecsList = recorderPipe.inRecsList;
		newRecPipe.ignoreCodegen = recorderPipe.ignoreCodegen;
		newRecPipe.mergerdTransRecs = recorderPipe.mergerdTransRecs;
		newRecPipe.nextComp = recorderPipe.nextComp;
		newRecPipe.nextCompList = recorderPipe.nextCompList;
		newRecPipe.outRecsList = recorderPipe.outRecsList;
		newRecPipe.prevCompList = recorderPipe.prevCompList;
		newRecPipe.previousComp = recorderPipe.previousComp;
		newRecPipe.rejectOption = recorderPipe.rejectOption;
		newRecPipe.seqNo = recorderPipe.seqNo;
		newRecPipe.setOperation = recorderPipe.setOperation;
		newRecPipe.transColsList = recorderPipe.transColsList;
		newRecPipe.transInfo = recorderPipe.transInfo;
		newRecPipe.transType = recorderPipe.transType;
		int xLevelPos = Integer.parseInt(recorderPipe.xLevelPos) + 20;
		newRecPipe.xLevelPos = Integer.toString(xLevelPos);
		int yLevelPos = Integer.parseInt(recorderPipe.yLevelPos) + 20;
		newRecPipe.yLevelPos = Integer.toString(yLevelPos);
		newRecPipe.isProcessed = recorderPipe.isProcessed;
		
		return newRecPipe;
	}


	public static void swapBeforeAfterStageNames(TransRecExtDTO recordperPipe, SrcTgtInfo srctgtdb, boolean checkCDChasInsrtDelete) 
	{
		
		String beforeStage = "";
		String afterStage = "";
		
		
		if(srctgtdb.transDtls.size() > 0)
		{
			for(int i = 0 ; i < srctgtdb.transDtls.size() ; i++)
			{
				String propNames = srctgtdb.transDtls.get(i);
				String propName= propNames.substring(0,propNames.indexOf(":")).trim();
				String propValue = propNames.substring(propNames.indexOf(":")+1).trim();
				if(propName.equalsIgnoreCase("BeforeStage"))
				{
					beforeStage = propValue;
					
				}
				else if(propName.equalsIgnoreCase("AfterStage"))
				{
					afterStage = propValue;
				}
			}
			
			if(checkCDChasInsrtDelete == true){
				for(int i = 0 ; i < srctgtdb.transDtls.size() ; i++)
				{
					String propNames = srctgtdb.transDtls.get(i);
					if(propNames.equalsIgnoreCase("BeforeStage : "+ beforeStage))
					{
						srctgtdb.transDtls.remove(i);
						srctgtdb.transDtls.add(i,"AfterStage : "+ beforeStage+"_dsCDC");
					}
					else if(propNames.equalsIgnoreCase("AfterStage : "+ afterStage))
					{
						srctgtdb.transDtls.remove(i);
						srctgtdb.transDtls.add(i,"BeforeStage : "+ afterStage+"_dsCDC");
					}
				}
			}
			else{
				for(int i = 0 ; i < srctgtdb.transDtls.size() ; i++)
				{
					String propNames = srctgtdb.transDtls.get(i);
					if(propNames.equalsIgnoreCase("BeforeStage : "+ beforeStage))
					{
						srctgtdb.transDtls.remove(i);
						srctgtdb.transDtls.add(i,"AfterStage : "+ beforeStage);
					}
					else if(propNames.equalsIgnoreCase("AfterStage : "+ afterStage))
					{
						srctgtdb.transDtls.remove(i);
						srctgtdb.transDtls.add(i,"BeforeStage : "+ afterStage);
					}
				}
			}
			
		}
		
		
	}

	

}