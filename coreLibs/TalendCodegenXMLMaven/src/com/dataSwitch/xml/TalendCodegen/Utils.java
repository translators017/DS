
package com.dataSwitch.xml.TalendCodegen;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import com.dataSwitch.xml.Talend.*;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata.Column;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.InPutTables;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.OutPutTables;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.OutPutTables.MapperTableEntries;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.VarTables;
import com.dataSwitch.xml.TalendProp.XmiXMI;
import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.talend.context.TalendfileContextType;
import com.dataSwitch.xml.ETLMapDTO.*;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.ConnectionObjeds;
import com.dataSwitch.xml.connectorspecs.Connector.ConnectionObjeds.ConnectionObject;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataMappingVariables.DataMappingVariable;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow;
public class Utils {

	public static TalendfileProcessType fillPipelinerecords(Connector docspecs,TalendfileProcessType pipeline , List<TransRecDTO> pipewiseRecsList,String szoutputLinkedServiceFilePath,DataMapping dataMappingObj, LogHelper logHelperObj, ReportLogInfo reportObj, boolean checkCDChasInsrtDelete) 
	{
		    pipeline.setXmiVersion("2.0");
		    pipeline.setXmlnsXmi("http://www.omg.org/XMI");
		    pipeline.setXmlnsXsi("http://www.w3.org/2001/XMLSchema-instance");
		    pipeline.setXmlnsTalendMapper("http://www.talend.org/mapper");
		    pipeline.setXmlnsTalendfile("platform:/resource/org.talend.model/model/TalendFile.xsd");
		    pipeline.setDefaultContext("Default");
		    pipeline.setJobType("Standard");
		    TalendfileProcessType.Context cntxt = new TalendfileProcessType.Context();
		    cntxt.setName("Default");
		    cntxt.setConfirmationNeeded("false");
		    List<String> cntxtParamsList = new ArrayList<String>();
		    boolean checkBoolean = false;
		    boolean specialCase = false;
		    String beforeStageName = "";
		    
		    int countContext = 10;
		    HashMap<String,List<DataMappingVariable>> dataMapVarMap = createContextSet.getParamsetNames(docspecs);
		    
		    for (Entry<String, List<DataMappingVariable>> entry : dataMapVarMap.entrySet())
			{
				
				List<DataMappingVariable> paramValue = entry.getValue();
				String paramKey = entry.getKey();
				
				for(int i = 0; i< paramValue.size(); i++){
					
					DataMappingVariable dataMapVar = paramValue.get(i);
					String paramCheck = dataMapVar.getName().substring(0,dataMapVar.getName().lastIndexOf("."));
					
					if(!cntxtParamsList.contains(paramKey)){
						cntxtParamsList.add(paramKey);
					}
					
					if(paramCheck.equalsIgnoreCase(paramKey)){
						
						String paramName = dataMapVar.getName().replace(".", "_").replace("$", "");
						cntxtParamsList.add(paramName);
						String value = "";
						if(dataMapVar.getInitialValue() != null){
							value = dataMapVar.getInitialValue();
						}
						else{
							value = "dummy value";
						}
			    		String datatype = dataMapVar.getDatatype();
			    		DataTypeConvertor dtc = new DataTypeConvertor();
			    		String targetDType = dtc.getTargetDataType(datatype,ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND);
			    		
			    		TalendfileProcessType.Context.ContextParameter cntxtParam = new  TalendfileProcessType.Context.ContextParameter();
			    		cntxtParam.setComment("");
			    		cntxtParam.setname(paramName);
			    		cntxtParam.setRepositoryContextId("_CL1KkC-QEemZ8oa6e"+countContext+"oEg");
			    		cntxtParam.setpromptNeeded("false");
			    		cntxtParam.setprompt(paramName+"?");
			    		cntxtParam.setvalue(value);
			    		cntxtParam.settype(targetDType);
			    		cntxt.getContextParameter().add(cntxtParam);
					
					}
					
				}
				countContext++;
				
			}	
		    pipeline.setContext(cntxt);	
		   /* for(int i = 0 ; i < docspecs.getDataMappings().getDataMapping().size();i++)
		    {
		    	
				int countContext = 10;
				int countPropContext = 10;
		    	
		    	DataMapping dataMap = docspecs.getDataMappings().getDataMapping().get(i);
		    	if(dataMap.getDataMappingVariables() != null)
		    	{
			    	for(int j = 0 ; j < dataMap.getDataMappingVariables().getDataMappingVariable().size();j++)
			    	{
			    		DataMappingVariable dataMapVar = dataMap.getDataMappingVariables().getDataMappingVariable().get(j);
			    		TalendfileProcessType.Context.ContextParameter cntxtParam = new  TalendfileProcessType.Context.ContextParameter();
			    		cntxtParam.setComment("");
			    		cntxtParam.setname(dataMapVar.getName());
			    		cntxtParamsList.add(dataMapVar.getName());
			    		cntxtParam.setpromptNeeded("false");
			    		cntxtParam.setprompt("");
			    		cntxtParam.setvalue(dataMapVar.getInitialValue());
			    		String datatype = dataMapVar.getDatatype();
			    		DataTypeConvertor dtc = new DataTypeConvertor();
			    		String targetDType = dtc.getTargetDataType(0,21,datatype);
			    		cntxtParam.settype(targetDType);
			    		if(dataMappingObj.getName().equalsIgnoreCase(dataMap.getName()))
			    		{
			    			cntxt.getContextParameter().add(cntxtParam);
			    		}
			    	}
		    	}
		    }
		    pipeline.setContext(cntxt);*/
		    
		    specialCase = checkSplCaseMapping(pipewiseRecsList,checkBoolean,specialCase,pipeline,docspecs);
		    
		    ProcessFlow subroutineProcessFlow = Utils.checkSubRoutine(docspecs);
		    if(subroutineProcessFlow !=null)
		    {
		    	CreateSubroutine.createSubroutine(subroutineProcessFlow,pipeline,docspecs,cntxtParamsList);
		    }
		    
		    for(int i = 0 ; i < pipewiseRecsList.size(); i++)
			{
				TransRecExtDTO recordperPipe =  (TransRecExtDTO) pipewiseRecsList.get(i);
				if(recordperPipe.ignoreCodegen == true)
					continue;
				TalendfileProcessType.Node compNode = new TalendfileProcessType.Node();
				SrcTgtInfo srctgtdb = new SrcTgtInfo();
				if (recordperPipe.transInfo instanceof SrcTgtInfo)
				{
					srctgtdb = (SrcTgtInfo) recordperPipe.transInfo;
				}
				
				if(recordperPipe.transType.equalsIgnoreCase("Source") || recordperPipe.transType.equalsIgnoreCase("LookupSource"))
				{
					LookupInfo lkp = null;
					if(recordperPipe.nextCompList.get(0).transType.equalsIgnoreCase("Lookup")) {
						lkp = (LookupInfo) recordperPipe.nextCompList.get(0).transInfo;
					}
					createSource.fillOutColsforSrc(pipeline,recordperPipe,docspecs,compNode,srctgtdb,szoutputLinkedServiceFilePath,dataMappingObj,cntxtParamsList,reportObj,lkp);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Expression"))
				{
					createExpr.fillExpTrans(recordperPipe,docspecs,compNode,srctgtdb,szoutputLinkedServiceFilePath,cntxtParamsList,dataMappingObj,logHelperObj,reportObj);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Aggregator"))
				{
					createAggregator.fillColsforAgg(recordperPipe, docspecs, compNode, srctgtdb, pipeline,reportObj);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Conditional Grouping"))
				{
					createRouter.fillRouterTrans(recordperPipe,docspecs,compNode,srctgtdb,dataMappingObj,cntxtParamsList,szoutputLinkedServiceFilePath,logHelperObj,reportObj,checkCDChasInsrtDelete);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Filter Transformation"))
				{
					createFltr.fillFltrTrans(recordperPipe,docspecs,compNode,srctgtdb,szoutputLinkedServiceFilePath,checkCDChasInsrtDelete);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Union Transformation"))
				{
					createUnion.fillColsforUnion(recordperPipe, docspecs, compNode, srctgtdb);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Joiner Transformation"))
				{
					//createJoiner.fillJoinerTrans(recordperPipe,docspecs,compNode,srctgtdb);
					createJnrAstMap.fillJoinerAstMapTrans(recordperPipe, docspecs, compNode, srctgtdb);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Multi Joiner Transformation"))
				{
					//createJoiner.fillJoinerTrans(recordperPipe,docspecs,compNode,srctgtdb);
					createMultiJnrAstMap.fillMultiJoinerAstMapTrans(recordperPipe, docspecs, compNode, srctgtdb);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Lookup"))
				{
					//createLookup.fillLkpTrans(recordperPipe,docspecs,compNode,srctgtdb);
					createLkpAstMap.fillLookupAstMapTrans(recordperPipe, docspecs, compNode, srctgtdb);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Sorter"))
				{
					createSorter.fillSortTrans(recordperPipe,docspecs,compNode,srctgtdb,dataMappingObj);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Remove Duplicates"))
				{
					createRemoveDup.fillRemDupTrans(recordperPipe,docspecs,compNode,srctgtdb);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Sequence Generator"))
				{
					createSeqgen.fillSeqGenTrans(recordperPipe,docspecs,compNode,srctgtdb);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("MD5"))
				{
				    createChecksum.fillCheckSumTrans(recordperPipe, docspecs, compNode,srctgtdb);	
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Other Transformation"))
				{
				    
				    if(srctgtdb.fileType.equalsIgnoreCase("Change Data Capture")){
				    	
				    	if(createCDCStage.checkCDChasDelete(recordperPipe, srctgtdb) == true){
					    	createCDCStage.swapBeforeAfterStageNames(recordperPipe,srctgtdb,checkCDChasInsrtDelete);
					    }
				    	else if(checkCDChasInsrtDelete == true && recordperPipe.compName.contains("_dsCDC")){
				    		createCDCStage.swapBeforeAfterStageNames(recordperPipe,srctgtdb,checkCDChasInsrtDelete);
				    	}
				    	
				    	beforeStageName = Utils.getBeforeStageNameForCDC(recordperPipe, srctgtdb);
				    }
				    
				    createOtherTrans.checkTransType(recordperPipe, docspecs, compNode,srctgtdb,szoutputLinkedServiceFilePath,cntxtParamsList,reportObj,dataMappingObj,checkCDChasInsrtDelete,pipeline);
				}
				else if(recordperPipe.transType.equalsIgnoreCase("Target"))
				{
					createTgt.fillInputColsforTgt(recordperPipe,docspecs,compNode,srctgtdb,pipeline,specialCase,dataMappingObj,cntxtParamsList);
				}   
				if(!recordperPipe.transType.equalsIgnoreCase("Target") && specialCase == false)
				{
					getCompMappingConn(recordperPipe, docspecs,pipeline,srctgtdb,pipewiseRecsList,beforeStageName,checkCDChasInsrtDelete);
				}
			     pipeline.getNode().add(compNode);
			}
		    
		    
		    
		    for(int i = 0; i < cntxtParamsList.size(); i++){
		    	
		    	TalendfileProcessType.Context.ContextParameter cntxtParam = new  TalendfileProcessType.Context.ContextParameter();
	    		
		    	String name = "";
		    	String dataType = "";
		    	
		    	if(cntxtParamsList.get(i).contains("@")){
		    		name = cntxtParamsList.get(i).substring(0,cntxtParamsList.get(i).indexOf('@'));
		    		dataType = cntxtParamsList.get(i).substring(cntxtParamsList.get(i).indexOf('@')+1);
		    	}
		    	else{
		    		name = cntxtParamsList.get(i);
		    		dataType = "id_String";
		    	}
		    	
		    	cntxtParam.setComment("");
	    		cntxtParam.setname(name);
	    		cntxtParam.setpromptNeeded("false");
	    		cntxtParam.setprompt("");
	    		cntxtParam.setvalue("");
	    		/*String datatype = dataMapVar.getDatatype();
	    		DataTypeConvertor dtc = new DataTypeConvertor();
	    		String targetDType = dtc.getTargetDataType(0,21,datatype);*/
	    		cntxtParam.settype(dataType);
	    		//cntxt.getContextParameter().add(cntxtParam);
		    
		    }
		    
		    //pipeline.setContext(cntxt);
		    
		return pipeline;
	}


	private static boolean checkSplCaseMapping(List<TransRecDTO> pipewiseRecsList, boolean checkBoolean, boolean specialCase, TalendfileProcessType pipeline, Connector docspecs) 
	{
		boolean checkSrcTgtStages = checkTransExceptSrcTgt(pipewiseRecsList);
		
		    for(int i = 0 ; i < pipewiseRecsList.size(); i++)
			{
				TransRecExtDTO recordperPipe =  (TransRecExtDTO) pipewiseRecsList.get(i);
				
				SrcTgtInfo srctgtdb = new SrcTgtInfo();
				if (recordperPipe.transInfo instanceof SrcTgtInfo)
				{
					srctgtdb = (SrcTgtInfo) recordperPipe.transInfo;
				}
				if(checkSrcTgtStages == false)
				{
					if(recordperPipe.transType.equalsIgnoreCase("Source") && recordperPipe.compName.equalsIgnoreCase(srctgtdb.primarySystemEntityRefRef))
					{
						checkBoolean  = checkMuliSrctoMultiTgt(recordperPipe,srctgtdb,pipeline,docspecs,pipewiseRecsList);
						
						if(checkBoolean == true)
						{
							specialCase = true;
						}
					}
				}
			}
		    
		    return specialCase;
	}


	
	public static ProcessFlow checkSubRoutine(Connector docspecs){
		
		//boolean checkSubroutine = false;
		ProcessFlow processFlow = null;
		if(docspecs.getProcessFlows()!=null){
			for(int i=0; i<docspecs.getProcessFlows().getProcessFlow().size(); i++){
				if(docspecs.getProcessFlows().getProcessFlow().get(i).getName().equalsIgnoreCase("subroutine")){
					//checkSubroutine = true;
					processFlow = docspecs.getProcessFlows().getProcessFlow().get(i);
					break;
				}
			}
		}
		
		
		return processFlow;
	}
	
	public static boolean getBooleanForRcp(DataMapping dataMappingObj) 
	{
		boolean checkBoolRcp = false;
		
		/*if(dataMappingObj.getName().contains("."))
		{
			String dataMapRcpCheck = dataMappingObj.getName().substring(dataMappingObj.getName().lastIndexOf(".")+1);
			if(dataMapRcpCheck.equalsIgnoreCase("RCP"))
			{
				checkBoolRcp = true;
			}
		}*/
		
		/*if(dataMappingObj.getDescription()!=null && dataMappingObj.getDescription().equalsIgnoreCase("RCP - RunTime Enabled")){
			checkBoolRcp = true;
		}*/
		
		if(dataMappingObj.getAdditionalProperties() != null){
			if(dataMappingObj.getAdditionalProperties().getDatastage().getJobType().equalsIgnoreCase("RCP - RunTime Enabled")){
				checkBoolRcp = true;
			}
		}
		
		
		return checkBoolRcp;
	}
	
	private static boolean checkTransExceptSrcTgt(List<TransRecDTO> pipewiseRecsList) 
	{
		  boolean checkSrcTgtStages = false;
		  for(int i = 0 ; i < pipewiseRecsList.size(); i++)
			{
				TransRecExtDTO recordperPipe =  (TransRecExtDTO) pipewiseRecsList.get(i);	
				if(!(recordperPipe.transType.equalsIgnoreCase("Source") || recordperPipe.transType.equalsIgnoreCase("Target")))	
				{
					checkSrcTgtStages = true;
				}
			}
		return checkSrcTgtStages;
	}


	private static boolean checkMuliSrctoMultiTgt(TransRecExtDTO recordperPipe, SrcTgtInfo srctgtdb, TalendfileProcessType pipeline, Connector docspecs, List<TransRecDTO> pipewiseRecsList) 
	{
		boolean checkBool = false;
		if(recordperPipe.transType.equalsIgnoreCase("Source") && recordperPipe.compName.equalsIgnoreCase(srctgtdb.primarySystemEntityRefRef))
		{
			if(recordperPipe.outRecsList != null)
			{
				if(srctgtdb.primarySystemEntityRefRef != null)
				{
					if(recordperPipe.compName.equalsIgnoreCase(srctgtdb.primarySystemEntityRefRef))
					{
						for(int i1 = 0 ; i1 < recordperPipe.outRecsList.size(); i1++)
						{
							if(recordperPipe.outRecsList.get(i1).nextInRec.parentTransRec.transType.equalsIgnoreCase("Target"))
							{
								checkBool =true;
							}
						}
					}
				}
			}
			
			if(checkBool == true)
			{
				fillCustomizedtMap(recordperPipe, docspecs, srctgtdb, pipeline,pipewiseRecsList,srctgtdb.primarySystemEntityRefRef);
			}
		}
		return checkBool;
	}


	public static XmiXMI fillPipelineProps(Connector docspecs,XmiXMI pipelineprop, List<TransRecDTO> pipewiseRecsList,String dataMapName, int countPipeline) 
	{
		String jobName = dataMapName;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.ENGLISH);
        pipelineprop.setXmiVersion("2.0");
        pipelineprop.setXmlnsXmi("http://www.omg.org/XMI");
        pipelineprop.setXmlnsTalendProperties("http://www.talend.org/properties");
        XmiXMI.TalendPropertiesProperty tpropsprop = new XmiXMI.TalendPropertiesProperty();
        XmiXMI.TalendPropertiesProperty.Author author = new XmiXMI.TalendPropertiesProperty.Author();
        tpropsprop.setXmiId("_CL1KkS-QEemZ8oa6e"+ countPipeline + "oEg");
        tpropsprop.setCreationDate(""+sdf.format(date));
        tpropsprop.setId("_CL1KkC-QEemZ8oa6e"+ countPipeline +"oEg");
        tpropsprop.setLabel(jobName);
        tpropsprop.setModificationDate(""+sdf.format(date));
        tpropsprop.setVersion(0.1);
        tpropsprop.setItem("_CL1Kky-QEemZ8oa6e"+ countPipeline +"oEg");
        tpropsprop.setDisplayName(jobName);
        author.setHref("../../../../../talend.project#_eYczcD8JEemQqtJiSuRJJw");
        tpropsprop.setAuthor(author);
        pipelineprop.setTalendPropertiesProperty(tpropsprop);
        XmiXMI.TalendPropertiesItemState tItemstate = new XmiXMI.TalendPropertiesItemState();
        tItemstate.setXmiId("_VyMHQD8UEemQqtJiSuRJJw");
        tItemstate.setPath("DataSwitch_Jobs");
        pipelineprop.setTalendPropertiesItemState(tItemstate);
        XmiXMI.TalendPropertiesProcessItem processItem = new XmiXMI.TalendPropertiesProcessItem();
        processItem.setXmiId("_CL1Kky-QEemZ8oa6e"+ countPipeline +"oEg");
        processItem.setProperty("_CL1KkS-QEemZ8oa6e" +  countPipeline + "oEg");
        processItem.setState("_VyMHQD8UEemQqtJiSuRJJw");
        XmiXMI.TalendPropertiesProcessItem.Process process = new XmiXMI.TalendPropertiesProcessItem.Process();
        process.setHref(jobName+".item#/");
        processItem.setProcess(process);
        pipelineprop.setTalendPropertiesProcessItem(processItem);   
        return pipelineprop;    
	}
	
	public static final Node getcompDetails (Node compNode,String CompName, Double CompVersion,String xPos,String yPos) 
	{
		compNode.setComponentName(CompName);
		compNode.setComponentVersion(CompVersion);
		compNode.setPosX(xPos);
		compNode.setPosY(yPos);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		return compNode;
	}
	
	public static void fillPropforExp_tMap(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		String compName = recordperPipe.compName;
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(getPropDtlsforExp("EXTERNAL","MAP",""));
		elemParam.add(getPropDtlsforExp("CLOSED_LIST","LINK_STYLE","AUTO"));
		elemParam.add(getPropDtlsforExp("DIRECTORY","TEMPORARY_DATA_DIRECTORY",""));
		elemParam.add(getPropDtlsforExp("IMAGE","PREVIEW","_CL1KkC-QEemZ8oa6e99oEg-"+compName+"-PREVIEW.bmp"));
		elemParam.add(getPropDtlsWithShwforExp("CHECK","DIE_ON_ERROR","true","false"));
		elemParam.add(getPropDtlsWithShwforExp("CHECK","LKUP_PARALLELIZE","false","false"));
		if(recordperPipe.transType.equalsIgnoreCase("Joiner Transformation"))
		{
			elemParam.add(getPropDtlsWithShwforExp("TEXT","LEVENSHTEIN","0","false"));
			elemParam.add(getPropDtlsWithShwforExp("TEXT","JACCARD","0","false"));
		}
		elemParam.add(getPropDtlsWithShwforExp("CHECK","ENABLE_AUTO_CONVERT_TYPE","false","false"));
		elemParam.add(getPropDtlsforExp("TEXT","ROWS_BUFFER_SIZE","2000000"));
		elemParam.add(getPropDtlsforExp("CHECK","CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL","false"));
		elemParam.add(getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
	}
	
	public static final  ElementParameter getPropDtlsforExp (String Field,String Name, String Value) 
	{
		ElementParameter elemParam = new ElementParameter();
		elemParam.setField(Field);
		elemParam.setName(Name);
		elemParam.setValue(Value);
		return elemParam;
	}
	
	public static final  ElementParameter.ElementValue getPropElemforTrans (String elementRef, String Value) 
	{
		ElementParameter.ElementValue elemParamValue = new ElementParameter.ElementValue();
		elemParamValue.setElementRef(elementRef);
		elemParamValue.setValue(Value);
		return elemParamValue;
	}
	
	public static final  ElementParameter getPropDtlsWithShwforExp (String Field,String Name, String Value,String Show) 
	{
		ElementParameter elemParam = new ElementParameter();
		elemParam.setField(Field);
		elemParam.setName(Name);
		elemParam.setValue(Value);
		elemParam.setShow(Show);
		return elemParam;
	}
	
	public static final List<String> getPrevCompName(TransRecExtDTO recordperPipe)
	{
		ArrayList<String> prevComp = new ArrayList<String>();
		for(int i = 0 ; i < recordperPipe.prevCompList.size();i++)
		{
			TransRecExtDTO prevCompName =  recordperPipe.prevCompList.get(i);
			String prevcomp = prevCompName.compName + "/" + prevCompName.transType;
			prevComp.add(prevcomp);
		}
		return prevComp;	
	}
	
	public static final List<String> getSrcColsList(TransRecExtDTO recordperPipe, ColumnDTO coldto)
	{
		ArrayList<String> SrCols = new ArrayList<String>();
		if(coldto.transRule.srcColNamesList != null)
		{
			for( int j = 0 ; j < coldto.transRule.srcColNamesList.size(); j++)
			{
				SrCols.add(coldto.transRule.srcColNamesList.get(j));
			}
		}
		return SrCols;	
	}
	
	public static final List<String> getSrcColsListforParam(TransRecExtDTO recordperPipe, ColumnDTO coldto)
	{
		ArrayList<String> SrCols = new ArrayList<String>();
		if(coldto.transRule.srcColNamesList != null)
		{
			for( int j = 0 ; j < coldto.transRule.srcColNamesList.size(); j++)
			{
				String srcColsName = coldto.transRule.srcColNamesList.get(j);
				String srcColName = srcColsName.substring(srcColsName.indexOf(".")+1, srcColsName.length());
				SrCols.add(srcColName);
			}
		}
		return SrCols;	
	}
	
	public static final List<String> getNextCompName(TransRecExtDTO recordperPipe, boolean checkCDChasInsrtDelete, SrcTgtInfo srctgtdb)
	{
		ArrayList<String> nextComp = new ArrayList<String>();
		
		for(int i = 0 ; i < recordperPipe.nextCompList.size();i++)
		{
			TransRecExtDTO nextCompName =  recordperPipe.nextCompList.get(i);
			String nxtComp = nextCompName.compName;
			if(recordperPipe.compName.contains("_dsCDC") && checkCDChasInsrtDelete == true)
			{
				nxtComp = nxtComp+"_dsCDC";
			}
			if(recordperPipe.nextCompList.get(i).rejectOption != null && !recordperPipe.nextCompList.get(i).rejectOption.equalsIgnoreCase("Ignore"))
			{
				nxtComp = nextCompName.compName+"/Reject/"+recordperPipe.nextCompList.get(i).rejectOption;	
			}
			nextComp.add(nxtComp);
		}
		return nextComp;	
	}
	
	public static final String checKExpIsIgnoreComp(TransRecExtDTO recordperPipe)
	{
		String checkExprTrans = "";
		for(int i = 0 ; i < recordperPipe.nextCompList.size();i++)
		{
			TransRecExtDTO nextCompName =  recordperPipe.nextCompList.get(i);
			if(nextCompName.ignoreCodegen == true && nextCompName.transType.equalsIgnoreCase("Expression"))
			{
				boolean exprFlag = true;
				checkExprTrans += exprFlag+";"+ nextCompName.compName;
			}
		}
		return checkExprTrans;
	}
	
	public static final String getdtlComp(TransRecExtDTO recordperPipe)
	{
		String dtlCompName = "";
		String compName = recordperPipe.compName;
		
		for(int j = 0 ; j < recordperPipe.nextCompList.size() ; j++)
		{
			TransRecExtDTO transRec = recordperPipe.nextCompList.get(j);
			
			if(transRec.transType.equalsIgnoreCase("Joiner Transformation"))
			{
				JoinerInfo jn  = (JoinerInfo) recordperPipe.nextCompList.get(j).transInfo;
				
				for(int i = 0 ; i < jn.detailCols.size(); i++)
				{
					ColumnDTO colsName = jn.detailCols.get(i);
					String colName = colsName.colStruct.name;
					for(int i1 = 0 ; i1 < transRec.inRecsList.size();i1++)
					{
						InRecDTO inrec = transRec.inRecsList.get(i1);
						for(int k = 0 ; k < inrec.prevOutRec.columnList.size(); k++)
						{
						    if(inrec.prevOutRec.columnList.get(k).colStruct.name.equalsIgnoreCase(colName))
						    {
						    	dtlCompName = inrec.prevOutRec.name.substring(0, inrec.prevOutRec.name.lastIndexOf("Out"));
						    	break;
						    }
						}
					}
				}
			}
			
			if(transRec.transType.equalsIgnoreCase("Multi Joiner Transformation")) {
				
				MultiJoinerInfo jn  = (MultiJoinerInfo) recordperPipe.nextCompList.get(j).transInfo;
				for(int i = 0; i<jn.detailElements.size(); i++) {
					DetailElementDTO dtlElem = jn.detailElements.get(i);
					for(int k=0; k<dtlElem.detailCols.size(); k++) {
						ColumnDTO coldto = dtlElem.detailCols.get(k);
						String dtlNm = coldto.parentOutRec.parentTransRec.compName;
						//String dtlNm = dtlElem.detailElemList.get(k);
						//dtlNm = dtlNm.substring(0,dtlNm.indexOf('.'));
						if(dtlNm.equalsIgnoreCase(compName)) {
							dtlCompName = compName;
							break;
						}
						
						/*ColumnDTO dtlCol = dtlElem.detailCols.get(0);
						System.out.println(dtlCol.parentOutRec.parentTransRec.compName+"===>"+dtlCol.aliasName);
						String dtlElemName = dtlCol.parentOutRec.parentTransRec.compName;
						
						if(dtlElemName.equalsIgnoreCase(compName)) {
							dtlCompName = compName;
							break;
						}*/
					}
				}
			}
			/*if(recordperPipe.nextCompList.get(j).transType.equalsIgnoreCase("Joiner Transformation"))
			{
				JoinerInfo jn  = (JoinerInfo) recordperPipe.nextCompList.get(j).transInfo;
				
				for(int j1 = 0 ; j1 < jn.detailElemList.size(); j1++)
				{
					String jnrDtlsName = jn.detailElemList.get(j1);
					dtlCompName = jnrDtlsName.substring(0,jnrDtlsName.indexOf("."));	
				}
				
			}*/
		}
		
		
		return dtlCompName;
	}
	
	public static final String getdtlCompFromRtr(TransRecExtDTO recordperPipe, String compName)
	{
		String dtlCompName = "";
		
		if(recordperPipe.transType.equalsIgnoreCase("Joiner Transformation"))
		{
			JoinerInfo jn  = (JoinerInfo) recordperPipe.transInfo;
			dtlCompName = getDtlCompForJnr(jn,compName);	
		}
		
		if(recordperPipe.transType.equalsIgnoreCase("Multi Joiner Transformation")) {
			MultiJoinerInfo jn  = (MultiJoinerInfo) recordperPipe.transInfo;
			for(int i = 0; i<jn.detailElements.size(); i++) {
				DetailElementDTO dtlElem = jn.detailElements.get(i);
				for(int k=0; k<dtlElem.detailElemList.size(); k++) {
					String dtlElemName1 = dtlElem.detailElemList.get(k);
					String dtlElemName = dtlElemName1.substring(0,dtlElemName1.indexOf('.'));
					if(dtlElemName.equalsIgnoreCase(compName)) {
						dtlCompName = compName;
						break;
					}
				}
			}
		}
			
		return dtlCompName;
	}
	
	
	public static String getDtlCompForJnr(JoinerInfo jn, String compName) 
	{
		String dtlCompName = "";
		for (int i = 0; i < jn.detailCols.size(); i++) 
		{
			ColumnDTO coldto = jn.detailCols.get(i);
			if(coldto.parentOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional grouping"))
			{
			 	String RtrCompName = coldto.parentOutRec.name.substring(0, coldto.parentOutRec.name.indexOf("Out"));
			 	if(RtrCompName.equalsIgnoreCase(compName))
			 	{
			 		dtlCompName = RtrCompName;
			 	}
			}
			
		}
		return dtlCompName;
	}
	

	public static String getMstrCompForJnr(JoinerInfo jn, String compName) 
	{
		String mstrCompName = "";
		for (int i = 0; i < jn.masterCols.size(); i++) 
		{
			ColumnDTO coldto = jn.masterCols.get(i);
			if(coldto.parentOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional grouping") || coldto.parentOutRec.parentTransRec.transType.equalsIgnoreCase("Filter Transformation"))
			{
			 	mstrCompName = "Case_"+compName;
			}
			else
			{
				mstrCompName = "rw_" + coldto.parentOutRec.name.substring(0, coldto.parentOutRec.name.indexOf("Out"));
			}
			
		}
		return mstrCompName;
	}
	
	public static String getMstrCompForMultiJnr(MultiJoinerInfo jnr, String compName) 
	{
		String mstrCompName = "";
		DetailElementDTO dtlElem = jnr.detailElements.get(0);
		for(int i=0; i<dtlElem.masterCols.size(); i++) {
			ColumnDTO coldto = dtlElem.masterCols.get(i);
			if(coldto.parentOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional grouping") || coldto.parentOutRec.parentTransRec.transType.equalsIgnoreCase("Filter Transformation")) { 
				mstrCompName = "Case_"+compName;
				break;
			}
			else {
				mstrCompName = "rw_" + coldto.parentOutRec.name.substring(0,coldto.parentOutRec.name.indexOf("Out"));
				break;
			}
		}
		return mstrCompName;
	}
	
	
	public static String getMstrCompForLkp(LookupInfo lkp, String compName) 
	{
		String mstrCompName = "";
		for (int i = 0; i < lkp.masterCols.size(); i++) 
		{
			ColumnDTO coldto = lkp.masterCols.get(i);
			
			if(coldto.parentOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional grouping") || coldto.parentOutRec.parentTransRec.transType.equalsIgnoreCase("Filter Transformation"))
			{
			 	mstrCompName = "Case_"+compName;
			}
			else
			{
				mstrCompName = "rw_" + coldto.parentOutRec.name.substring(0, coldto.parentOutRec.name.indexOf("Out"));
			}
			
			
		}
		return mstrCompName;
	}
	


	public static final List<String> getlookupLinkfortMap(TransRecExtDTO recordperPipe)
	{
		
		List<String>  lkpcompList = new ArrayList<String>();
		for(int j1 = 0 ; j1 < recordperPipe.nextCompList.size() ; j1++)
		{
			if(recordperPipe.nextCompList.get(j1).transType.equalsIgnoreCase("Expression"))
			{
				int count=0;
				for(int j3 = 0 ; j3 < recordperPipe.nextCompList.get(j1).inRecsList.size(); j3++)
				{
					 String lkpcompName = recordperPipe.nextCompList.get(j1).inRecsList.get(j3).prevOutRec.parentTransRec.compName + "." +count;
					 if(recordperPipe.nextCompList.get(j1).inRecsList.get(j3).prevOutRec.columnList.size()>0){
						 lkpcompList.add(lkpcompName); 
						 count++;
					 }	 
				}
		    }
		}
		
		return lkpcompList;
	}
	
	public static final String getcheckfortMap(TransRecExtDTO recordperPipe)
	{
		
	      String lkpcompName = "";
			for(int j = 0 ; j < recordperPipe.nextCompList.size() ; j++)
			{
				if(recordperPipe.nextCompList.get(j).transType.equalsIgnoreCase("Expression"))
				{
					lkpcompName = recordperPipe.nextCompList.get(j).compName;
				}
			}
			
			return lkpcompName;
	}
	
	public static final String getTalendDataType(int ToolId,int dataBaseId,String srcdataType)
	{
		DataTypeConvertor dtc = new DataTypeConvertor();
		String targetDType = dtc.getTargetDataType(srcdataType,ToolTypeConstants.TALEND,dataBaseId);
		String tgtDataType = "";
		if(targetDType != null)
		{
			tgtDataType = targetDType.toUpperCase();
		}
		return tgtDataType;
	}
	
	public static final String getParamValWithContext(String property, Connector docspecs, List<String> cntxtParamsList)
	{
		
		property = property.replace("-rejeds\\(2)0", "").replace(" \\(2)", "").replace("_SK.sk\\(2)0", "");
		
		List<String> propTokens = new ArrayList<String>();
		
		if(property.contains("#")){
			
			String[] temp = property.split("#");
			
			for(int i=0 ; i<temp.length; i++){
				if(!temp[i].equals("") && !temp[i].equals(" ")){
					propTokens.add(temp[i]);
				}/*
				else{
					propTokens.add("+");
				}*/
			}
			
			String updatedProp = "";
			
			for(int i=0; i<propTokens.size(); i++){
				String token = propTokens.get(i);
				
				if(token.contains(".")){
					String paramSet = token.substring(0,token.indexOf('.'));
					String paramName = token.substring(token.indexOf('.')+1,token.length());
					paramSet = paramSet.replace("$", "");
					paramName = paramName.replace("$", "").replace(".","_");
					String param = paramSet+"_"+paramName;
					if(cntxtParamsList.contains(paramSet)){
						updatedProp += "context."+paramSet+"_"+paramName+"+";
						
						if(!cntxtParamsList.contains(param)){
							cntxtParamsList.add(param);
						}
					}
					else{
						updatedProp += "\""+token+"\"+";
					}
				}
				else{
					if(cntxtParamsList.contains(token)){
						updatedProp += "context."+token+"+";
					}
					else{
						updatedProp += "\""+token+"\"+";
					}
					
				}
			}
			
			if(updatedProp.charAt(updatedProp.length()-1)=='+'){
				updatedProp = updatedProp.substring(0, updatedProp.length()-1);
			}
			
//			updatedProp = updatedProp.replace(".", "_");
			//updatedProp = updatedProp.replaceAll(":", "\":\"+");
			//updatedProp = updatedProp.replaceAll("/", "\"/\"+");
			
			return updatedProp;
		}
		else{
			
			if(property.contains(".")){
				String paramSet = property.substring(0,property.indexOf('.'));
				String paramName = property.substring(property.indexOf('.')+1,property.length());
				paramSet = paramSet.replace("$", "");
				paramName = paramName.replace("$", "").replace(".", "_");
				String param = paramSet+"_"+paramName;
				
				if(cntxtParamsList.contains(paramSet)){
					property="";
					property += "context."+paramSet+"_"+paramName;
					
					if(!cntxtParamsList.contains(param)){
						cntxtParamsList.add(param);
					}
				}
			}
			
			return property;
		}
	
	}
	
	public static HashMap<String, String> getUnamePwd(Connector docspecs, SrcTgtInfo srctgtdb){
		HashMap<String,String> uNamePwd = new HashMap<String,String>();

		if(docspecs.getConnectionObjeds() != null){
		ConnectionObjeds connObject = docspecs.getConnectionObjeds();
		List<ConnectionObject> connObjectList = connObject.getConnectionObject();
		
		
			for(int i=0; i<connObjectList.size();i++){
				ConnectionObject conObj = connObjectList.get(i);
				if(conObj.getName().equalsIgnoreCase(srctgtdb.connectionName)){
					
					if(conObj.getUsername()!=null){
						uNamePwd.put("username", conObj.getUsername());
					}
					if(conObj.getPort()!=null){
						uNamePwd.put("port", Integer.toString(conObj.getPort()));
					}
					
					if(conObj.getHostname()!=null){
						uNamePwd.put("host", conObj.getHostname());
					}
					if(conObj.getPassword()!=null){
						uNamePwd.put("pwd",conObj.getPassword());
					}
					
					if(conObj.getDatabaseConnection() != null)
					{
						if(conObj.getDatabaseConnection().getDatabaseConnectionString().length() > 0)
						{
							if(conObj.getDatabaseConnection().getDatabaseConnectionString().contains("schemaName:"))
							{
								uNamePwd.put("schemaName",conObj.getDatabaseConnection().getDatabaseConnectionString().substring(conObj.getDatabaseConnection().getDatabaseConnectionString().indexOf(":")+1,
										conObj.getDatabaseConnection().getDatabaseConnectionString().length()).trim());
							}
						}
					}
				}
			}
		}
		
		return uNamePwd;
		
	}
	
	
	private static String checkNxtComptransType(String nxtCompNameRtr, List<TransRecDTO> pipewiseRecsList)
	{
		    String transType = ""; 
		    for(int i = 0 ; i < pipewiseRecsList.size(); i++)
			{
				TransRecExtDTO recordperPipe =  (TransRecExtDTO) pipewiseRecsList.get(i);
				if(recordperPipe.compName.equalsIgnoreCase(nxtCompNameRtr) && recordperPipe.transType.equalsIgnoreCase("Union Transformation"))
				{
					transType = "IsUnion";
				}
			}
			return transType;
		
	}
	
	
	public static String checkNxtCDC(String nxtCompNameRtr, List<TransRecDTO> pipewiseRecsList){
		
		String isCDC = "";
		
		for(int i = 0 ; i < pipewiseRecsList.size(); i++)
		{
			TransRecExtDTO recordperPipe =  (TransRecExtDTO) pipewiseRecsList.get(i);
			if(recordperPipe.compName.equalsIgnoreCase(nxtCompNameRtr) && recordperPipe.transType.equalsIgnoreCase("Other Transformation"))
			{
				SrcTgtInfo srctgtdb = new SrcTgtInfo();
				if (recordperPipe.transInfo instanceof SrcTgtInfo)
				{
					srctgtdb = (SrcTgtInfo) recordperPipe.transInfo;
				}
				if(srctgtdb.fileType.equalsIgnoreCase("Change Data Capture")){
					isCDC = "IsCDC";
				}
				
			}
		}
		
		return isCDC;
	}
	
	private static boolean getInrecCount(String nxtCompNameRtr, String compName, List<TransRecDTO> pipewiseRecsList){
		boolean flag = false;
		
		for(int i=0; i< pipewiseRecsList.size(); i++){
			TransRecExtDTO recordperPipe =  (TransRecExtDTO) pipewiseRecsList.get(i);
			if(recordperPipe.compName.equalsIgnoreCase(nxtCompNameRtr) && recordperPipe.inRecsList.size()<=1 && recordperPipe.prevCompList.get(0).compName == compName){
				flag=true;
				break;
			}
		}
		
		return flag;
	}
	
	
	private static String getBeforeStageNameForCDC(TransRecExtDTO recordperPipe, SrcTgtInfo srctgtdb) {
		
		String beforeStageName = "";
		
		if(srctgtdb.transDtls.size() > 0)
		{
			for(int i = 0 ; i < srctgtdb.transDtls.size() ; i++)
			{
				String propNames = srctgtdb.transDtls.get(i);
				String propName= propNames.substring(0,propNames.indexOf(":")).trim();
				String propValue = propNames.substring(propNames.indexOf(":")+1).trim();
				if(propName.equalsIgnoreCase("BeforeStage"))
				{
					beforeStageName = propValue;
					break;
				}
			}
		}
		
		return beforeStageName;
	}
	
	public static void getCompMappingConn(TransRecExtDTO recordperPipe,Connector docspecs,TalendfileProcessType pipeline,SrcTgtInfo srctgtdb,List<TransRecDTO> pipewiseRecsList, String beforeStageName, boolean checkCDChasInsrtDelete)
	{
		
	    String compName = recordperPipe.compName;
		
		
		List<String> NextComp = getNextCompName(recordperPipe,checkCDChasInsrtDelete,srctgtdb);
		String NextCompName = NextComp.toString().replace("[", "").replace("]", "");
		if(NextCompName.contains("/Reject/"))
		{
			getCompMappingObjForReject(NextCompName,compName, docspecs, pipeline);
			NextCompName = getCompMappingConnForReject(NextCompName,recordperPipe,docspecs,pipeline).trim();
		}
		
		String comp = getdtlComp(recordperPipe);
		
		
		List<String> lkpLink = getlookupLinkfortMap(recordperPipe); 
		String lkpLinkchk = getcheckfortMap(recordperPipe);
		
		if(!recordperPipe.transType.equalsIgnoreCase("Conditional Grouping"))
		{
			String ans = "isNotTarget";
			boolean checkTgtColumnReorder = true;
			boolean isNextAggHasExp = false;
			for(int i = 0 ; i < recordperPipe.nextCompList.size() ; i++)
			{
				TransRecExtDTO Nexttrans = recordperPipe.nextCompList.get(i);
				if(Nexttrans.rejectOption != null && Nexttrans.transType.equalsIgnoreCase("Target")){
					if(Nexttrans.transType.equalsIgnoreCase("Target") && ! Nexttrans.rejectOption.equalsIgnoreCase("Capture - Bad Data File"))
					{
						SrcTgtInfo srctgt = (SrcTgtInfo) Nexttrans.transInfo;
						if(srctgt.fileExtension != null)
						{
							boolean flag  = compareLists(Nexttrans, srctgt);
							//ans = flag.toString();
							checkTgtColumnReorder = flag;
							//System.out.println(ans );
							if(flag == false)
							{
								TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
								conn.setconnectorName("FLOW");
								conn.setlabel("rw_"+"ColmReorder_"+Nexttrans.compName);
								conn.setsource("ColmReorder_"+Nexttrans.compName);
								conn.settarget(Nexttrans.compName);
								conn.setlineStyle("0"); 
								conn.setmetaname("ColmReorder_"+Nexttrans.compName);
								conn.setoffsetLabelX("0");
								conn.setoffsetLabelY("0");
								TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
								elem.setName("UNIQUE_NAME");
								elem.setfield("TEXT");
								elem.setshow("false");
								elem.setvalue("rw_"+"ColmReorder_"+Nexttrans.compName);
								TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
								elem1.setName("MONITOR_CONNECTION");
								elem1.setfield("CHECK");
								elem1.setvalue("false");
								conn.getElementParameter().add(elem1);
								conn.getElementParameter().add(elem);
								pipeline.getConnection().add(conn);
							}
						}
					}
				}	
				if(Nexttrans.transType.equalsIgnoreCase("Aggregator")) {
					AggInfo agg = (AggInfo) Nexttrans.transInfo;
					for(int k=0;k<agg.colDTOList.size();k++)
					{
						ColumnDTO aggCols = agg.colDTOList.get(k);
						if(aggCols.isBeingUsed == 89)
						{
							String ruleName = aggCols.transRule.transRuleName;
							if(ruleName.equalsIgnoreCase("Aggregator - Expression")) {
								isNextAggHasExp = true;
								break;
							}
						}
					}
				}
			}
			
			 StringTokenizer tokenizer = new StringTokenizer(NextCompName, ",");
			 int cnt = 0 ; 
			 int cnt1 = 0;
			 int tempCount = 0;
			 
			 while (tokenizer.hasMoreTokens())
		     { 
		        String nxtCompNameRtr = tokenizer.nextToken().trim();
		        String checkUnion = checkNxtComptransType(nxtCompNameRtr,pipewiseRecsList);
		        boolean nextCompInrecSizeIsOne = getInrecCount(nxtCompNameRtr,compName,pipewiseRecsList);
		        
		        if(recordperPipe.outRecsList.get(tempCount).columnList.size() > 0 || recordperPipe.transType.equalsIgnoreCase("Source") || nextCompInrecSizeIsOne==true){
			        
			        tempCount++;
		        
		        int count = cnt++;
			    TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
			    
			    String expCmp = checKExpIsIgnoreComp(recordperPipe);
			    
			    if(checkCDChasInsrtDelete == true)
			    {
			    	if(recordperPipe.transType.equalsIgnoreCase("Other Transformation") && srctgtdb.fileType.equalsIgnoreCase("Change Data Capture"))
			    	{
			    		getConnForUnionTransCDC(recordperPipe.compName,nxtCompNameRtr,pipeline,expCmp);
			    		
			    		if(!recordperPipe.compName.contains("_dsCDC"))
			    			nxtCompNameRtr = "dsUnion_"+recordperPipe.compName+"_dsCDC";
			    		else
			    			nxtCompNameRtr = "dsUnion_"+recordperPipe.compName;
			    		
			    		conn.setlineStyle("10"); 
			    	}
			    }
			    
				if(!recordperPipe.transType.equalsIgnoreCase("Remove Duplicates"))
				{
					conn.setconnectorName("FLOW");
				}
				else
				{
					conn.setconnectorName("UNIQUE");
				}
				if(!recordperPipe.transType.equalsIgnoreCase("Filter Transformation"))
				{
					if(count != 0)
					{
					  conn.setlabel("rw_"+compName+count);
					}
					else
					{
					  conn.setlabel("rw_"+compName);	
					}
				}
				else
				{
					conn.setlabel("Case_"+nxtCompNameRtr);
				}
				conn.setsource(compName);
				
				if(expCmp.contains("true;"))
				{
					String nxtCompName = expCmp.substring(expCmp.lastIndexOf(";")+1);
					if(nxtCompNameRtr.equalsIgnoreCase(nxtCompName))
					{
						nxtCompNameRtr = nxtCompNameRtr+"_Exp";
					}
				}
				if(checkTgtColumnReorder == false)
				{
					conn.settarget("ColmReorder_"+nxtCompNameRtr);
				}
				else if(isNextAggHasExp == true) {
					conn.settarget("aggexp_"+nxtCompNameRtr);
				}
				else
				{
					conn.settarget(nxtCompNameRtr);
				}
				
				//for cdc
				
				if(compName.equalsIgnoreCase(beforeStageName) || compName.equalsIgnoreCase(beforeStageName+"_dsCDC"))
				{
					conn.setlineStyle("8"); 
				}
				if(comp.equalsIgnoreCase(compName))
				{
					conn.setlineStyle("8"); 
				}
				else if(recordperPipe.transType.equalsIgnoreCase("LookupSource"))
				{
					
					conn.setlineStyle("8"); 
				}
				else if(lkpLinkchk.equalsIgnoreCase(nxtCompNameRtr) && !compName.equalsIgnoreCase(lkpLinkchk))
				{
					 String lkpLinkName = lkpLink.toString().replace("[", "").replace("]", "");
					 StringTokenizer tMaptokenizer = new StringTokenizer(lkpLinkName, ",");
					 while (tMaptokenizer.hasMoreTokens())
				     { 
						 String lkpName = tMaptokenizer.nextToken().trim();
						 String tmapSegregate = lkpName.substring(0, lkpName.indexOf(".")); 
						 String tlkpcount   = lkpName.substring(lkpName.indexOf(".")+1, lkpName.length()); 
						if(tmapSegregate.equalsIgnoreCase(compName))
						{
							if(tlkpcount.equalsIgnoreCase("0"))
							{
								conn.setlineStyle("0");
							}
							else
							{
								conn.setlineStyle("8");
							}
						}
						
				     }
				}
				else if(checkUnion.equalsIgnoreCase("IsUnion"))
				{
					conn.setlineStyle("10");
				}
				else
				{
					if(checkCDChasInsrtDelete == false && !(recordperPipe.transType.equalsIgnoreCase("Other Transformation") && srctgtdb.fileType.equalsIgnoreCase("Change Data Capture")))
						conn.setlineStyle("0"); 
				}
				if(count != 0 )
				{
					conn.setmetaname(compName+count);
				}
				else
				{
					conn.setmetaname(compName);
				}
				
				
			conn.setoffsetLabelX("0");
			conn.setoffsetLabelY("0");
			TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
			elem.setName("UNIQUE_NAME");
			elem.setfield("TEXT");
			elem.setshow("false");
			if(count != 0)
			{
			  elem.setvalue("rw_"+compName+count);
			}
			else
			{
			  elem.setvalue("rw_"+compName);
			}
			TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
			elem1.setName("MONITOR_CONNECTION");
			elem1.setfield("CHECK");
			elem1.setvalue("false");
			conn.getElementParameter().add(elem1);
			conn.getElementParameter().add(elem);
			pipeline.getConnection().add(conn);
		   }
		   else{
			   tempCount++;	
			   cnt++;
		   }
		   }
		}
		/*if(NextCompName.contains(","))
		{*/
		if(recordperPipe.transType.equalsIgnoreCase("Conditional Grouping"))
		{
		
			StringTokenizer tokenizer = new StringTokenizer(NextCompName, ",");
		/* while (tokenizer.hasMoreTokens())
	     {
	        String nxtCompNameRtr = tokenizer.nextToken();*/
		 	//String nxtCompNameRtr = tokenizer.nextToken();
	        String ans = "isNotTarget";
	        boolean checkTgtColumnReorder = true;
			for(int i = 0 ; i < recordperPipe.nextCompList.size() ; i++)
			{
				TransRecExtDTO Nexttrans = recordperPipe.nextCompList.get(i);
				String dtlCompName = getdtlCompFromRtr(Nexttrans,recordperPipe.compName);
				String nxtCompNameRtr = Nexttrans.compName;
				
				if(recordperPipe.compName.contains("_dsCDC") && checkCDChasInsrtDelete == true)
					nxtCompNameRtr = nxtCompNameRtr+"_dsCDC";
				
				SrcTgtInfo srctgt = new SrcTgtInfo();
				if (Nexttrans.transInfo instanceof SrcTgtInfo)
				{
					srctgt = (SrcTgtInfo) Nexttrans.transInfo;
				}
				//SrcTgtInfo srctgt = (SrcTgtInfo) Nexttrans.transInfo;
				if(Nexttrans.transType.equalsIgnoreCase("Target") && srctgt.fileExtension != null)
				{
					
					//System.out.println(Nexttrans.compName+"$$$$$");
					
					if(srctgt.fileExtension != null)
					{
						boolean flag = compareLists(Nexttrans, srctgt);
						//ans = flag.toString();
						checkTgtColumnReorder = flag;
						if(flag == false)
						{
							TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
							conn.setconnectorName("FLOW");
							conn.setlabel("rw_"+"ColmReorder_"+Nexttrans.compName);
							conn.setsource("ColmReorder_"+Nexttrans.compName);
							conn.settarget(Nexttrans.compName);
							conn.setlineStyle("0"); 
							conn.setmetaname("ColmReorder_"+Nexttrans.compName);
							conn.setoffsetLabelX("0");
							conn.setoffsetLabelY("0");
							TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
							elem.setName("UNIQUE_NAME");
							elem.setfield("TEXT");
							elem.setshow("false");
							elem.setvalue("rw_"+"ColmReorder_"+Nexttrans.compName);
							TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
							elem1.setName("MONITOR_CONNECTION");
							elem1.setfield("CHECK");
							elem1.setvalue("false");
							conn.getElementParameter().add(elem1);
							conn.getElementParameter().add(elem);
							pipeline.getConnection().add(conn);
						}
						
						TalendfileProcessType.Connection conn1 = new TalendfileProcessType.Connection();
				        conn1.setconnectorName("FLOW");
				        conn1.setlabel("Case_"+nxtCompNameRtr.trim());
				        conn1.setsource(compName);
					        if(/*!ans.equalsIgnoreCase("isNotTarget")*/checkTgtColumnReorder == false && flag==false){
					        	conn1.settarget("ColmReorder_"+nxtCompNameRtr.trim());
							}else{
								conn1.settarget(nxtCompNameRtr.trim());
							}
				        String checkUnion = checkNxtComptransType(nxtCompNameRtr.trim(),pipewiseRecsList);
					    if(checkUnion.equalsIgnoreCase("IsUnion"))
					    {
					       conn1.setlineStyle("10");
					    }
					    else
					    {
				           conn1.setlineStyle("0");
					    }
				        //conn1.settarget(nxtCompNameRtr.trim());
				        conn1.setmetaname(compName);
				        conn1.setoffsetLabelX("0");
				        conn1.setoffsetLabelY("0");
						TalendfileProcessType.Connection.ElementParameter elem2 = new TalendfileProcessType.Connection.ElementParameter();
						elem2.setName("UNIQUE_NAME");
						elem2.setfield("TEXT");
						elem2.setshow("false");
						elem2.setvalue("rw_"+compName);
						TalendfileProcessType.Connection.ElementParameter elem3 = new TalendfileProcessType.Connection.ElementParameter();
						elem3.setName("MONITOR_CONNECTION");
						elem3.setfield("CHECK");
						elem3.setvalue("false");	
						conn1.getElementParameter().add(elem2);
						conn1.getElementParameter().add(elem3);	
						pipeline.getConnection().add(conn1);
						
					}
					
				}
				else{
					
					
					TalendfileProcessType.Connection conn1 = new TalendfileProcessType.Connection();
			        conn1.setconnectorName("FLOW");
			        conn1.setlabel("Case_"+nxtCompNameRtr.trim());
			        conn1.setsource(compName);
				    conn1.settarget(nxtCompNameRtr.trim());
			        String checkUnion = checkNxtComptransType(nxtCompNameRtr.trim(),pipewiseRecsList);
			        String checkCDC = checkNxtCDC(nxtCompNameRtr.trim(),pipewiseRecsList);
			   
			        
				    if(checkUnion.equalsIgnoreCase("IsUnion"))
				    {
				       conn1.setlineStyle("10");
				    }
				    else if(dtlCompName.equalsIgnoreCase(recordperPipe.compName))
				    {
				    	conn1.setlineStyle("8");
				    }
				    else if(compName.equalsIgnoreCase(beforeStageName) && checkCDC.equalsIgnoreCase("IsCDC"))
				    {
				    	conn1.setlineStyle("8"); 
				    }
				    else
				    {
			           conn1.setlineStyle("0");
				    }
			        //conn1.settarget(nxtCompNameRtr.trim());
			        conn1.setmetaname(compName);
			        conn1.setoffsetLabelX("0");
			        conn1.setoffsetLabelY("0");
					TalendfileProcessType.Connection.ElementParameter elem2 = new TalendfileProcessType.Connection.ElementParameter();
					elem2.setName("UNIQUE_NAME");
					elem2.setfield("TEXT");
					elem2.setshow("false");
					elem2.setvalue("rw_"+compName);
					TalendfileProcessType.Connection.ElementParameter elem3 = new TalendfileProcessType.Connection.ElementParameter();
					elem3.setName("MONITOR_CONNECTION");
					elem3.setfield("CHECK");
					elem3.setvalue("false");	
					conn1.getElementParameter().add(elem2);
					conn1.getElementParameter().add(elem3);	
					pipeline.getConnection().add(conn1);
					
				}
			}
		
		}
	}
	


	private static void getConnForUnionTransCDC(String compName, String nxtCompName, TalendfileProcessType pipeline, String expCmp) 
	{
		if(compName.contains("_dsCDC"))
		{
			if(nxtCompName.contains("_dsCDC"))
				nxtCompName = nxtCompName.substring(0,nxtCompName.lastIndexOf("_dsCDC"));
			if(expCmp.contains("true;"))
			{
				String nxtCompName1 = expCmp.substring(expCmp.lastIndexOf(";")+1);
				if(nxtCompName.equalsIgnoreCase(nxtCompName1))
				{
					nxtCompName = nxtCompName+"_Exp";
				}
			}
			TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
			conn.setconnectorName("FLOW");
			conn.setlabel("rw_"+"dsUnion_"+compName);
			conn.setsource("dsUnion_"+compName);
		    conn.settarget(nxtCompName);
			conn.setlineStyle("0"); 
			conn.setmetaname("dsUnion_"+compName);
			conn.setoffsetLabelX("0");
			conn.setoffsetLabelY("0");
			TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
			elem.setName("UNIQUE_NAME");
			elem.setfield("TEXT");
			elem.setshow("false");
			elem.setvalue("rw_"+"dsUnion_"+compName);
			TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
			elem1.setName("MONITOR_CONNECTION");
			elem1.setfield("CHECK");
			elem1.setvalue("false");
			conn.getElementParameter().add(elem1);
			conn.getElementParameter().add(elem);
			pipeline.getConnection().add(conn);
		}
	}


	public static String getCompMappingConnForReject(String NextCompName,TransRecExtDTO recordperPipe,Connector docspecs,TalendfileProcessType pipeline)
	{
		 StringTokenizer tokenizer = new StringTokenizer(NextCompName, ",");
		 while (tokenizer.hasMoreTokens())
	     {
			 String nextCompforReject = tokenizer.nextToken();
			 if(nextCompforReject.contains("/Reject/"))
			 {
				 System.out.println(nextCompforReject +"DBUG:::::The Pipeline has Reject Link");
			 }
			 else
			 {
				 NextCompName = nextCompforReject;
			 }
	     }
		 return NextCompName;
	}
	
	public static TalendfileProcessType getCompMappingObjForReject(String NextCompName,String compName,Connector docspecs,TalendfileProcessType pipeline)
	{
		 StringTokenizer tokenizer = new StringTokenizer(NextCompName, ",");
		 while (tokenizer.hasMoreTokens())
	     {
			 String nextCompforReject = tokenizer.nextToken();
			 if(nextCompforReject.contains("/Reject/"))
			 {
				 NextCompName = nextCompforReject.substring(0, nextCompforReject.indexOf("/Reject/"));
					TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
					conn.setconnectorName("REJECT");
					conn.setlineStyle("0"); 
					conn.setmetaname("REJECT");
					conn.setoffsetLabelX("0");
					conn.setlabel("rjt_"+NextCompName);
					conn.setoffsetLabelY("0");
					conn.setsource(compName);
					conn.settarget(NextCompName);
					TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
					elem.setName("UNIQUE_NAME");
					elem.setfield("TEXT");
					elem.setshow("false");
					elem.setvalue("rjt_"+NextCompName);
					TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
					elem1.setName("MONITOR_CONNECTION");
					elem1.setfield("CHECK");
					elem1.setvalue("false");
					conn.getElementParameter().add(elem1);
					conn.getElementParameter().add(elem);
					pipeline.getConnection().add(conn);
			 }
	     }
		 return pipeline;
	}
	
	public final static void updateYpositionForPrevStages(List<TransRecDTO> pipewiseRecsList)
	{
		List<TransRecExtDTO> recsList = null;
		TransRecExtDTO rec = new TransRecExtDTO();
		for (int pipeIndex = 0; pipeIndex < pipewiseRecsList.size(); pipeIndex++)
		{
			recsList = new ArrayList<TransRecExtDTO>();
			recsList.add((TransRecExtDTO) pipewiseRecsList.get(pipeIndex));
			for (int i = 0; i < recsList.size(); i++)
			{
				rec = recsList.get(i);
				ArrayList<TransRecDTO> sorcColumns = new ArrayList<TransRecDTO>();
				
				if (rec.prevCompList.size() > 1)
				{
					for(int j = 0; j < rec.prevCompList.size(); j++)
					{
						TransRecDTO prevRec = rec.prevCompList.get(j);	
						if (rec.prevCompList.size() > 1)
						{
							sorcColumns.add(prevRec);
						}
					}
				}
				assignYPos (sorcColumns);
			}			
		}
		
	}
	
	public final static void updateYpositionForNextStages(List<TransRecDTO> pipewiseRecsList)
	{
		List<TransRecExtDTO> recsList = null;
		TransRecExtDTO rec = new TransRecExtDTO();
		for (int pipeIndex = 0; pipeIndex < pipewiseRecsList.size(); pipeIndex++)
		{
			recsList = new ArrayList<TransRecExtDTO>();
			recsList.add((TransRecExtDTO) pipewiseRecsList.get(pipeIndex));
			
			ArrayList<TransRecDTO> sorcColumns = new ArrayList<TransRecDTO>();
			ArrayList<TransRecDTO> tgtColumns = new ArrayList<TransRecDTO>();
			
			for (int i = 0; i < recsList.size(); i++)
			{
				rec = recsList.get(i);
				
				if (rec.nextCompList.size() > 1)
				{
					for(int j = 0; j < rec.nextCompList.size(); j++)
					{
						TransRecDTO prevRec = rec.nextCompList.get(j);	
						if (rec.nextCompList.size() > 1)
						{
							sorcColumns.add(prevRec);
						}
					}
				}

				if (rec.transType.equalsIgnoreCase("Target"))
				{
					tgtColumns.add(rec);
				}
			}	
			if (tgtColumns.size() > 0)
				assignYPos (tgtColumns);
			if (sorcColumns.size() > 0)
				assignYPos (sorcColumns);
		}
	}
	
	public final static void updateYpositionForTargetStages(List<List<TransRecDTO>> pipewiseRecsList)
	{
		List<TransRecDTO> recsList;
		TransRecDTO rec;
		
		TransRecExtDTO rec1 = new TransRecExtDTO();
		
		ArrayList<TransRecDTO> tgtColumns = new ArrayList<TransRecDTO>();
		
		for (int pipeIndex = 0; pipeIndex < pipewiseRecsList.size(); pipeIndex++)
		{
			recsList = pipewiseRecsList.get(pipeIndex);
			for (int i = 0; i < recsList.size(); i++)
			{
				rec = recsList.get(i);
				if (rec.transInfo instanceof SrcTgtInfo)
				{
				
					if (rec1.transType.equalsIgnoreCase("Target"))
					{
						tgtColumns.add(rec);
					}
				}
			}	assignYPos (tgtColumns);		
		}
	}

	public final static void  assignYPos (ArrayList<TransRecDTO> sorcColumns)
	{
		if (sorcColumns == null)
			return;
		
		int tmp = 0, tmpPos = 0;
//		System.out.println("sorcColumns  "+sorcColumns.size());
		for(int i = 0; i < sorcColumns.size(); i++)
		{
			TransRecDTO trans = sorcColumns.get(i);
			if (tmp == 0)
			{
				trans.yLevelPos = Integer.toString(100);
				tmpPos = Integer.parseInt(trans.yLevelPos);
			}
			else
			{
				trans.yLevelPos = Integer.toString(tmpPos + 50);
				tmpPos = Integer.parseInt(trans.yLevelPos);
			}
			
			tmp++;
		}

		return;
	}
	
	public final static void  updateXpositionForStages(List<TransRecDTO> pipewiseRecsList)
	{
		List<TransRecExtDTO> recsList = null;
		TransRecExtDTO transRec = new TransRecExtDTO(), maxRec;
		int xPostionForTarget = 0;

		for (int pipeIndex = 0; pipeIndex < pipewiseRecsList.size(); pipeIndex++)
		{
			recsList = new ArrayList<TransRecExtDTO>();
			recsList.add((TransRecExtDTO) pipewiseRecsList.get(pipeIndex));
			int maxSeqNo = -2;
			maxRec = null;
			for (int i = 0; i < recsList.size(); i++)
			{
				transRec = recsList.get(i);
				if (Integer.parseInt(transRec.xLevelPos) != -1)
					continue;
				if(maxSeqNo < transRec.seqNo)
				{
					maxSeqNo = transRec.seqNo;
					maxRec = transRec;
				}
			}
			xPostionForTarget = maxSeqNo*250+100;
			assignXPos (maxRec, xPostionForTarget);
			boolean bUnassignedRecs = false; 
			for (int i = 0; i < recsList.size(); i++)
			{
				transRec = recsList.get(i);
				if (Integer.parseInt(transRec.xLevelPos) == -1)
				{
					bUnassignedRecs = true;
					break;
				}
			}
			if (bUnassignedRecs)
				pipeIndex--;
		}
	}
	
	public final static void  assignXPos (TransRecDTO transRec, int nStageXPos)
	{
		
		TransRecExtDTO rec = new TransRecExtDTO();
		rec = (TransRecExtDTO)transRec;
		
		if (rec == null)
			return;
		if (Integer.parseInt(rec.xLevelPos) != -1)
			return;
		rec.xLevelPos = Integer.toString(nStageXPos);
		
		for(int i = 0; i < rec.prevCompList.size(); i++)
		{
			TransRecExtDTO prevRec = rec.prevCompList.get(i);
			
			if ((rec.seqNo == (prevRec.seqNo - 1)) || (rec.prevCompList.size() == 1))
				assignXPos(prevRec, Integer.parseInt(rec.xLevelPos)-250);
			else
				assignXPos(prevRec, Integer.parseInt(rec.xLevelPos)-250);
		}

		for(int i = 0; i < rec.nextCompList.size(); i++)
		{
			TransRecDTO nextRec = rec.nextCompList.get(i);
			
			assignXPos(nextRec, Integer.parseInt(rec.xLevelPos)+250);
		}

		return;
	}
	
	public static ArrayList<TransRecDTO> processChecksumStage(ArrayList<TransRecDTO> recsList){
		ArrayList<TransRecDTO> pRecsList = recsList;
		ArrayList<String> colNames = new ArrayList<String>();
		for(int i=0;i<pRecsList.size();i++)
		{
			   TransRecExtDTO record = (TransRecExtDTO) pRecsList.get(i);
			   if(record.transType.equalsIgnoreCase("Expression") && record.transInfo instanceof ChecksumInfo){
					 ChecksumInfo expInfo = (ChecksumInfo) record.transInfo;
					 boolean isCheckSum = false;
					 for(int j=0;j<expInfo.colDTOList.size() && !isCheckSum;j++){
							ColumnDTO colDTO = expInfo.colDTOList.get(j);
							if(colDTO == null)
								   continue;
							if(colDTO.transRule.transRuleName.contains("MD5")){
								   record.transType = "MD5";
								   isCheckSum = true;
							}

					 }
					 if(record.transType.equalsIgnoreCase("MD5") && record.transInfo instanceof ChecksumInfo){
							TransRecExtDTO prevRecord = record.prevCompList.get(0);
							if(prevRecord.transType.equalsIgnoreCase("MD5"))
								   prevRecord = prevRecord.prevCompList.get(0);
							for(int k=0;k<prevRecord.outRecsList.get(0).columnList.size();k++){
								   colNames.add(prevRecord.outRecsList.get(0).columnList.get(k).colStruct.name);
							}
							for(int x=0;x<record.outRecsList.get(0).columnList.size();x++){
										ChecksumInfo info = (ChecksumInfo) record.transInfo;
								   ArrayList<String> inputCols = info.colDTOList.get(0).transRule.srcColNamesList;
								   if(!inputCols.contains(record.outRecsList.get(0).columnList.get(x).colStruct.name)){
										  record.outRecsList.get(0).columnList.remove(x);
										  x--;
								   }
							}
							record.outRecsList.get(0).columnList.get(record.outRecsList.get(0).columnList.size()-1).colStruct.keyType = "MD5";
					 }
			   }
		}
		return pRecsList;
}
	
	public static ArrayList<TransRecDTO> removeDuplicateOutrecsFromRouter( ArrayList<TransRecDTO> recsList){
        for (int i = 0; i < recsList.size(); i++) 
        {
               TransRecExtDTO recordperPipe =  (TransRecExtDTO) recsList.get(i);
               if(recordperPipe.transType.equalsIgnoreCase("Conditional Grouping")){
                     ArrayList<String> checkDuplicateCompNames = new ArrayList<String>();
                     for(int j=0;j<recordperPipe.nextCompList.size();j++){
                            String nextCompName = recordperPipe.nextCompList.get(j).compName;
                            if(checkDuplicateCompNames.contains(nextCompName)){
                                   recordperPipe.nextCompList.remove(j);
                                   j--;
                            }
                            checkDuplicateCompNames.add(nextCompName);
                            if(recordperPipe.nextCompList.get(j).transType.equalsIgnoreCase("Other Transformation")){
                                   for(int k=0;k<recordperPipe.outRecsList.size();k++){
                                          if(recordperPipe.outRecsList.get(k).columnList.size()==0){
                                                 recordperPipe.outRecsList.remove(k);
                                                 k--;
                                          }
                                   }
                            }
                     }
                     recsList.set(i, recordperPipe);
               }
        }
        return recsList;
 }	
	
	public static void fillExpTransforColumnSort(TransRecExtDTO recordperPipe,Connector docspecs,SrcTgtInfo srctgtdb,TalendfileProcessType pipeline)
	{
		 TalendfileProcessType.Node compNode = new TalendfileProcessType.Node();
		  String Exptranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = "ColmReorder_"+recordperPipe.compName;
		  String xPos = recordperPipe.xLevelPos;
		  int xPos1 = Integer.parseInt(xPos) - 100;
		  String xpos2 = Integer.toString(xPos1);
		  String yPos = recordperPipe.yLevelPos;
		  List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		  String prevcompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevCompName =  prevcompName.substring(0,prevcompName.indexOf("/"));
		  String prevCompNametrans = prevcompName.substring(prevcompName.indexOf("/")+1 , prevcompName.length());
		  Utils.getcompDetails(compNode,Exptranstype, compVersion,xpos2,yPos);
			ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
			elemParam.add(Utils.getPropDtlsforExp("EXTERNAL","MAP",""));
			elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","LINK_STYLE","AUTO"));
			elemParam.add(Utils.getPropDtlsforExp("DIRECTORY","TEMPORARY_DATA_DIRECTORY",""));
			elemParam.add(Utils.getPropDtlsforExp("IMAGE","PREVIEW","_CL1KkC-QEemZ8oa6e99oEg-"+compName+"-PREVIEW.bmp"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","DIE_ON_ERROR","true","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","LKUP_PARALLELIZE","false","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","ENABLE_AUTO_CONVERT_TYPE","false","false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","ROWS_BUFFER_SIZE","2000000"));
			elemParam.add(Utils.getPropDtlsforExp("CHECK","CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL","false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
			compNode.getElementParameter().addAll(elemParam);
		  Node.NodeData ndData = new Node.NodeData();
		  ndData.setXsiType("TalendMapper:MapperData");
		  Node.NodeData.UIProperties ndUI = new Node.NodeData.UIProperties();
		  ndUI.setshellMaximized("true");
		  ndData.setUIProperties(ndUI);
		  Node.NodeData.VarTables ndVars = new Node.NodeData.VarTables();
		  ndVars.setname("Var");
		  ndVars.setminimized("true");
		  ndVars.setsizeState("INTERMEDIATE");
		  ndData.setVarTables(ndVars);
		  Node.NodeData.OutPutTables outTbl = null;
		  outTbl = new Node.NodeData.OutPutTables();
		  outTbl.setsizeState("INTERMEDIATE");
		  outTbl.setname("rw_"+compName);
		  Node.Metadata mtData = new Node.Metadata();
		  mtData.setConnector("FLOW");
		  mtData.setName("rw_"+compName);
		  mtData.setLabel("rw_"+compName);
		  HashMap<String,String> colmMap = new HashMap<String,String>();
		  
		  for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		  {
			  InRecDTO inrec = recordperPipe.inRecsList.get(j);
			  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
			  {
				  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String colsName = colsList.colStruct.name;
				  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  colmMap.put(colName.toLowerCase(), colName);
			  }
		  }		  
		  
		  for (int i1 = 0; i1 < srctgtdb.srcTgtColsList.size(); i1++) 
		  {
		     ColumnDTO srcCols =  srctgtdb.srcTgtColsList.get(i1);
		     if(srcCols.srcColumn == null)
		    	continue; 
		     String srctgtColsName = srcCols.colStruct.name;
		     
		     int length = srcCols.colStruct.length;
		     int precision = srcCols.colStruct.precision;
		     String dataType = srcCols.colStruct.dataType;
		     DataTypeConvertor dtc = new DataTypeConvertor();
	    	 dataType = dtc.getTargetDataType(dataType,18,20);
		     Metadata.Column colm = new Metadata.Column();
		     colm.setName(srctgtColsName);
			 
		     boolean isNullable = srcCols.colStruct.isNullable;
			 String nullStatus = String.valueOf(isNullable);
			 String comment = srcCols.colStruct.description;
			 
			 if(comment!=null){
				 colm.setComment(comment);
			 }
			 else{
				 colm.setComment("");
			 }
		     
			 colm.setKey("false");
			 if(srcCols.colStruct.length==0){
				 colm.setLength(srcCols.colStruct.precision);
				 colm.setPrecision(srcCols.colStruct.Scale);
			 }
			 else{
				 colm.setLength(srcCols.colStruct.length);
				 colm.setPrecision(srcCols.colStruct.Scale);
			 }
			 
			 if(nullStatus!=null){
				 colm.setNullable(nullStatus);
			 }
			 else{
				 colm.setNullable("true");
			 }
			 
			 colm.setPattern("");
			 //colm.setPrecision(precision);
			 colm.setType(dataType);
			 colm.setUsefulColumn("true");
			 mtData.getColumn().add(colm);
			 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
			 mapTbl.setName(srctgtColsName);
			 
			 if(colmMap.get(srctgtColsName.toLowerCase())!=null){
		    	 srctgtColsName = colmMap.get(srctgtColsName.toLowerCase());
		     }		     
			 
			 mapTbl.setType(dataType);
			 mapTbl.setNullable(nullStatus);
			 
			 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
			 {
			   mapTbl.setexpression("Case_"+recordperPipe.compName+"."+srctgtColsName);
			 }
			 else
			 {
                mapTbl.setexpression("rw_"+prevCompName+"."+srctgtColsName);
			 }
			 outTbl.getMapperTableEntries().add(mapTbl);
		 }  
		  
		 ndData.getOutPutTables().add(outTbl);
		 compNode.getMetadata().add(mtData);
		 
		  for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		  {
			  InRecDTO inrec = recordperPipe.inRecsList.get(j);
			  Node.NodeData.InPutTables inTbl = new Node.NodeData.InPutTables();
			  inTbl.setsizeState("INTERMEDIATE");
			  inTbl.setlookupMode("LOAD_ONCE");
			  inTbl.setmatchingMode("UNIQUE_MATCH");
			  inTbl.setname("rw_"+prevCompName);
			  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
			  {
				  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String colsName = colsList.colStruct.name;
				  String dataType = colsList.colStruct.dataType;
				  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  mapTbl.setName(colName);
				  mapTbl.setType(dataType);
				  
				  boolean isNullable = colsList.colStruct.isNullable;
				  String nullStatus = String.valueOf(isNullable);
				  
				  mapTbl.setNullable(nullStatus);
				  inTbl.getMapperTableEntries().add(mapTbl);
			  }
			  ndData.getInPutTables().add(inTbl);
		  }
		  
		  compNode.setNodeData(ndData);
		  pipeline.getNode().add(compNode);
	}
	
	public static boolean compareLists(TransRecExtDTO recordperPipe, SrcTgtInfo srctgt) 
	{
		
		List<String> CheckSrcOrderList = new ArrayList<String>();
		for (int i1 = 0; i1 < srctgt.srcTgtColsList.size(); i1++) 
		{
		     ColumnDTO srcCols =  srctgt.srcTgtColsList.get(i1);
		   
		     if(srcCols.srcColumn == null)
		    	continue; 
		     String srcColsName = srcCols.colStruct.name;
		     CheckSrcOrderList.add(srcColsName);
		}
		
		/*List<String> nextOrderColsList = new ArrayList<String>();
		for (int i = 0; i < recordperPipe.outRecsList.size(); i++) 
		{
			OutRecDTO outRec =  recordperPipe.outRecsList.get(i);
			for (int j = 0; j <  outRec.columnList.size(); j++) 
			{
				ColumnDTO colsDetails = outRec.columnList.get(j);
				String ColsName = colsDetails.colStruct.name;
				String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				nextOrderColsList.add(colName);
			}
		}*/
		
		List<String> nextOrderColsList = new ArrayList<String>();
		for (int i = 0; i < recordperPipe.inRecsList.size(); i++) 
		{
			InRecDTO inRec =  recordperPipe.inRecsList.get(i);
			for (int j = 0; j <  inRec.prevOutRec.columnList.size(); j++) 
			{
				ColumnDTO colsDetails = inRec.prevOutRec.columnList.get(j);
				String ColsName = colsDetails.colStruct.name;
				String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				nextOrderColsList.add(colName);
			}
		}
		if(nextOrderColsList.size() == CheckSrcOrderList.size())
		{
			int count=0;
			int size=nextOrderColsList.size();
			for(int i=0;i<size;i++)
			{
				if(nextOrderColsList.get(i).equals(CheckSrcOrderList.get(i))){
					
					count++;
				}
			}
			if(count==size){
				return true;
			}else{
				return false;
			}
		}
	
		return false;
	}

	public static void fillCustomizedtMap(TransRecExtDTO recordperPipe,Connector docspecs,SrcTgtInfo srctgtdb,TalendfileProcessType pipeline, List<TransRecDTO> pipewiseRecsList, String custCompName)
	{
		 TalendfileProcessType.Node compNode = new TalendfileProcessType.Node();
		  String Exptranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = "Customized_"+recordperPipe.compName;
		  String masterComp = "";
		  if(srctgtdb.primarySystemEntityRefRef != null)
		  {
			 masterComp = srctgtdb.primarySystemEntityRefRef;
		  }
		  String xPos = recordperPipe.xLevelPos;
		  int xPos1 = Integer.parseInt(xPos) - 100;
		  String xpos2 = Integer.toString(xPos1);
		  String yPos = recordperPipe.yLevelPos;
		    Utils.getcompDetails(compNode,Exptranstype, compVersion,xpos2,yPos);
			ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
			elemParam.add(Utils.getPropDtlsforExp("EXTERNAL","MAP",""));
			elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","LINK_STYLE","AUTO"));
			elemParam.add(Utils.getPropDtlsforExp("DIRECTORY","TEMPORARY_DATA_DIRECTORY",""));
			elemParam.add(Utils.getPropDtlsforExp("IMAGE","PREVIEW","_CL1KkC-QEemZ8oa6e99oEg-"+compName+"-PREVIEW.bmp"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","DIE_ON_ERROR","true","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","LKUP_PARALLELIZE","false","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","ENABLE_AUTO_CONVERT_TYPE","false","false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","ROWS_BUFFER_SIZE","2000000"));
			elemParam.add(Utils.getPropDtlsforExp("CHECK","CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL","false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
			compNode.getElementParameter().addAll(elemParam);
		  Node.NodeData ndData = new Node.NodeData();
		  ndData.setXsiType("TalendMapper:MapperData");
		  Node.NodeData.UIProperties ndUI = new Node.NodeData.UIProperties();
		  ndUI.setshellMaximized("true");
		  ndData.setUIProperties(ndUI);
		  Node.NodeData.VarTables ndVars = new Node.NodeData.VarTables();
		  ndVars.setname("Var");
		  ndVars.setminimized("true");
		  ndVars.setsizeState("INTERMEDIATE");
		  ndData.setVarTables(ndVars);
		  
		   
		  for(int i = 0 ; i < pipewiseRecsList.size(); i++)
	      {
				TransRecExtDTO ExtRecordperPipe =  (TransRecExtDTO) pipewiseRecsList.get(i);
				SrcTgtInfo extSrctgtdb = new SrcTgtInfo();
				if (ExtRecordperPipe.transInfo instanceof SrcTgtInfo)
				{
					extSrctgtdb = (SrcTgtInfo) ExtRecordperPipe.transInfo;
				}
				int DataBaseType = 0 ;
				if(extSrctgtdb.fileExtension != null)
				{
				   DataBaseType = 18;
				}
				else
				{
					System.out.println(recordperPipe.compName + "==="+extSrctgtdb.fileType);
					
					if(extSrctgtdb.fileType.equalsIgnoreCase("Relational - Netezza"))
					{
						DataBaseType = 4;
					}
					else if(extSrctgtdb.fileType.equalsIgnoreCase("Relational - Teradata"))
					{
						DataBaseType = 3;
					}
					else if(extSrctgtdb.fileType.equalsIgnoreCase("Relational - DB2 LUW"))
					{
						DataBaseType = 2;
					}
					else if(extSrctgtdb.fileType.equalsIgnoreCase("Relational - Oracle"))
					{
						DataBaseType = 1;
					}
					else if(extSrctgtdb.fileType.equalsIgnoreCase("Relational - ODBC"))
					{
						DataBaseType = 1;
					}
					else if(extSrctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server"))
					{
						DataBaseType = 5;
					}
				}
				if(ExtRecordperPipe.transType.equalsIgnoreCase("Source"))
			    {
					  Node.NodeData.InPutTables inTbl = new Node.NodeData.InPutTables();
					  inTbl.setsizeState("INTERMEDIATE");
					  inTbl.setlookupMode("LOAD_ONCE");
					  inTbl.setmatchingMode("UNIQUE_MATCH");
					  inTbl.setname("rw_"+ExtRecordperPipe.compName);
					  for (int i1 = 0; i1 < extSrctgtdb.srcTgtColsList.size(); i1++) 
					  {
						  ColumnDTO colsList =  extSrctgtdb.srcTgtColsList.get(i1);
						  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
						  String colsName = colsList.colStruct.name;
						  String dataType = colsList.colStruct.dataType;
						  DataTypeConvertor dtc = new DataTypeConvertor();
					      dataType = dtc.getTargetDataType(dataType,DataBaseType,ToolTypeConstants.TALEND);
						  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
						  mapTbl.setName(colName);
						  mapTbl.setType(dataType);
						  
						  boolean isNullable = colsList.colStruct.isNullable;
						  String nullStatus = String.valueOf(isNullable);
						  mapTbl.setNullable(nullStatus);
						  inTbl.getMapperTableEntries().add(mapTbl);
					  }
					  ndData.getInPutTables().add(inTbl);
					  compNode.setNodeData(ndData);
					  
				        TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
						conn.setconnectorName("FLOW");
						conn.setlabel("rw_"+ExtRecordperPipe.compName);
						conn.setsource(ExtRecordperPipe.compName);
						conn.settarget("Customized_" + extSrctgtdb.primarySystemEntityRefRef);
						if(ExtRecordperPipe.compName.equalsIgnoreCase(extSrctgtdb.primarySystemEntityRefRef))
						{
						   conn.setlineStyle("0"); 
						}
						else
						{
						   conn.setlineStyle("8");
						}
						conn.setmetaname("rw_"+ExtRecordperPipe.compName);
						conn.setoffsetLabelX("0");
						conn.setoffsetLabelY("0");
						TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
						elem.setName("UNIQUE_NAME");
						elem.setfield("TEXT");
						elem.setshow("false");
						elem.setvalue("rw_"+ExtRecordperPipe.compName);
						TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
						elem1.setName("MONITOR_CONNECTION");
						elem1.setfield("CHECK");
						elem1.setvalue("false");
						conn.getElementParameter().add(elem1);
						conn.getElementParameter().add(elem);
						pipeline.getConnection().add(conn);  
					  
				}
				else if(ExtRecordperPipe.transType.equalsIgnoreCase("Target"))
				{
					 
					  for(int j = 0 ; j < ExtRecordperPipe.inRecsList.size() ; j++)
					  {
						  InRecDTO inrec = ExtRecordperPipe.inRecsList.get(j);
						  if(inrec.prevOutRec.columnList.size() <= 0)
							  continue;
						  String prevComp = inrec.prevOutRec.name;
						  Node.NodeData.OutPutTables outTbl = new Node.NodeData.OutPutTables();
						  outTbl.setsizeState("INTERMEDIATE");
						  outTbl.setname("rw_"+prevComp);
						  Node.Metadata mtData = new Node.Metadata();
						  mtData.setConnector("FLOW");
						  mtData.setName("rw_"+prevComp);
						  mtData.setLabel("rw_"+prevComp);
						  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
						  {
							 ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
							 String colsName = colsList.colStruct.name;
							 String srcCol = colsList.srcColumn.colStruct.name;
							 String srcComp = srcCol.substring(0, srcCol.indexOf("."));
							 String srcColName = srcCol.substring(srcCol.indexOf(".")+1 ,srcCol.length());
							 String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
							 int length = colsList.colStruct.length;
							 int precision = colsList.colStruct.precision;
						     String dataType = colsList.colStruct.dataType;
						     Metadata.Column colm = new Metadata.Column();
						     colm.setName(colName);
							 
						     boolean isNullable = colsList.colStruct.isNullable;
							 String nullStatus = String.valueOf(isNullable);
							 String comment = colsList.colStruct.description;
							 
							 if(comment!=null){
								 colm.setComment(comment);
							 }
							 else{
								 colm.setComment("");
							 }
						     
							 colm.setKey("false");
							 if(colsList.colStruct.length==0){
								 colm.setLength(colsList.colStruct.precision);
								 colm.setPrecision(colsList.colStruct.Scale);
							 }
							 else{
								 colm.setLength(colsList.colStruct.length);
								 colm.setPrecision(colsList.colStruct.Scale);
							 }
							 
							 if(nullStatus!=null){
								 colm.setNullable(nullStatus);
							 }
							 else{
								 colm.setNullable("true");
							 }
							 
							 colm.setPattern("");
							 colm.setType(dataType);
							 colm.setUsefulColumn("true");
							 mtData.getColumn().add(colm);
							 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
							 mapTbl.setName(colName);
							  
							 mapTbl.setType(dataType);
							 mapTbl.setNullable(nullStatus);
							 mapTbl.setexpression("rw_"+srcComp+"."+srcColName);
							 outTbl.getMapperTableEntries().add(mapTbl);
						  }
						   
						   TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
							conn.setconnectorName("FLOW");
							conn.setlabel("rw_"+prevComp);
							conn.setsource("Customized_" + custCompName);
							conn.settarget(ExtRecordperPipe.compName);
							conn.setlineStyle("0"); 
							conn.setmetaname("rw_"+prevComp);
							conn.setoffsetLabelX("0");
							conn.setoffsetLabelY("0");
							TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
							elem.setName("UNIQUE_NAME");
							elem.setfield("TEXT");
							elem.setshow("false");
							elem.setvalue("rw_"+prevComp);
							TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
							elem1.setName("MONITOR_CONNECTION");
							elem1.setfield("CHECK");
							elem1.setvalue("false");
							conn.getElementParameter().add(elem1);
							conn.getElementParameter().add(elem);
							pipeline.getConnection().add(conn); 
						  
						  
						  ndData.getOutPutTables().add(outTbl);
						  compNode.getMetadata().add(mtData);
					  }		
				}	
		  }
		  pipeline.getNode().add(compNode);
	}


	public static Column createExtraColForRCP() 
	{
		Metadata.Column tlndCol = new  Metadata.Column();
		tlndCol.setName("Dynamic_Column");
		tlndCol.setKey("false");
		tlndCol.setLength(-1);
		tlndCol.setPrecision(-1);
		tlndCol.setNullable("true");
		tlndCol.setPattern("");
		tlndCol.setUsefulColumn("true");
		tlndCol.setType("id_Dynamic");
		return tlndCol;
	}


	public static MapperTableEntries createOpMapTblEnteries(String prevComp, String compName, String parentTransRec ) 
	{
		 MapperTableEntries mapTbl = new MapperTableEntries ();
		 mapTbl.setName("Dynamic_Column");
		 mapTbl.setType("id_Dynamic");
		 mapTbl.setNullable("true");
		 if(parentTransRec.equalsIgnoreCase("Filter Transformation") || parentTransRec.equalsIgnoreCase("Conditional Grouping"))
		 {
		   mapTbl.setexpression("Case_"+compName+"."+"Dynamic_Column");
		 }
		 else
		 {
            mapTbl.setexpression("rw_"+ prevComp+"."+"Dynamic_Column");
		 }
		 return mapTbl;
	}


	public static InPutTables.MapperTableEntries createIpMapTblEnteries() 
	{
		  InPutTables.MapperTableEntries ipTbl = new InPutTables.MapperTableEntries();
		  ipTbl.setName("Dynamic_Column");
		  ipTbl.setType("id_Dynamic");
		  ipTbl.setNullable("true");
		return ipTbl;
	}
	
	
	public static void updateAndResolveFieldNameConflids (ArrayList<TransRecDTO> pipewiseRecsList) 
	{
		for (int j = 0; j < pipewiseRecsList.size(); j++) 
		{
			TransRecDTO rec = pipewiseRecsList.get(j);	

			if(rec.transType.equalsIgnoreCase("Expression") || rec.transType.equalsIgnoreCase("Aggregator"))
				findLocalAndOutVariables (rec);

			for (int k = 0; k < rec.outRecsList.size(); k++)
			{
				for (int j1 = 0; j1 < rec.outRecsList.get(k).columnList.size(); j1++) 
				{
					ColumnDTO colDTO = rec.outRecsList.get(k).columnList.get(j1);

					//addColumnPrecisionScale (colDTO); 
					addColumnAliasName (colDTO);
				}
			}

			if (rec.transInfo instanceof ExprInfo)
			{
				ExprInfo transInfo = (ExprInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);
					//addColumnPrecisionScale (colDTO); 
					addColumnAliasName (colDTO);
				}
			}

			if (rec.transInfo instanceof AggInfo)
			{
				AggInfo transInfo = (AggInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);
					//addColumnPrecisionScale (colDTO); 
					addColumnAliasName (colDTO);
				}
			}

			if (rec.transInfo instanceof SeqGenInfo)
			{
				SeqGenInfo transInfo = (SeqGenInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);
					//addColumnPrecisionScale (colDTO); 
					addColumnAliasName (colDTO);
				}
			}

			if (rec.transInfo instanceof UnionInfo)
			{
				UnionInfo transInfo = (UnionInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);
					//addColumnPrecisionScale (colDTO); 
					addColumnAliasName (colDTO);
				}
			}
		}

		for (int j = 0; j < pipewiseRecsList.size(); j++) 
		{
			TransRecDTO rec = pipewiseRecsList.get(j);	

			if (rec.transType.equalsIgnoreCase("Source") || rec.transType.equalsIgnoreCase("LookupSource") || rec.transType.equalsIgnoreCase("Target"))
				continue;

			List<String> unqFieldnames = new ArrayList<String>();

			if (rec.transInfo instanceof UnionInfo)
			{
				UnionInfo transInfo = (UnionInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);

					updateUniqueFieldName (colDTO, unqFieldnames, pipewiseRecsList, rec); 
				}
			}

			if(rec.transType.equalsIgnoreCase("Lookup"))
			{
				for (int k = 0; k < rec.inRecsList.size(); k++) 
				{
					if (!rec.inRecsList.get(k).prevOutRec.parentTransRec.transType.equalsIgnoreCase("LookupSource"))
						continue;
					
					for (int k2 = 0; k2 < rec.inRecsList.get(k).prevOutRec.columnList.size(); k2++) 
					{
						ColumnDTO colDTO = rec.inRecsList.get(k).prevOutRec.columnList.get(k2);

						updateUniqueFieldName (colDTO, unqFieldnames, pipewiseRecsList, rec); 
					}
				}
				for (int k = 0; k < rec.inRecsList.size(); k++) 
				{
					if (rec.inRecsList.get(k).prevOutRec.parentTransRec.transType.equalsIgnoreCase("LookupSource"))
						continue;
					
					for (int k2 = 0; k2 < rec.inRecsList.get(k).prevOutRec.columnList.size(); k2++) 
					{
						ColumnDTO colDTO = rec.inRecsList.get(k).prevOutRec.columnList.get(k2);

						updateUniqueFieldName (colDTO, unqFieldnames, pipewiseRecsList, rec); 
					}
				}
			}
			else
			{
				for (int k = 0; k < rec.inRecsList.size(); k++) 
				{
					for (int k2 = 0; k2 < rec.inRecsList.get(k).prevOutRec.columnList.size(); k2++) 
					{
						ColumnDTO colDTO = rec.inRecsList.get(k).prevOutRec.columnList.get(k2);

						updateUniqueFieldName (colDTO, unqFieldnames, pipewiseRecsList, rec); 
					}
				}
			}
			
			if (rec.transInfo instanceof ExprInfo)
			{
				ExprInfo transInfo = (ExprInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);

					updateUniqueFieldName (colDTO, unqFieldnames, pipewiseRecsList, rec); 
				}
			}

			if (rec.transInfo instanceof AggInfo)
			{
				AggInfo transInfo = (AggInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);

					updateUniqueFieldName (colDTO, unqFieldnames, pipewiseRecsList, rec); 
				}
			}

			if (rec.transInfo instanceof SeqGenInfo)
			{
				SeqGenInfo transInfo = (SeqGenInfo)rec.transInfo;

				for (int k = 0; k < transInfo.colDTOList.size(); k++) 
				{
					ColumnDTO colDTO = transInfo.colDTOList.get(k);

					updateUniqueFieldName (colDTO, unqFieldnames, pipewiseRecsList, rec); 
				}
			}

			if (rec.transType.equalsIgnoreCase("Conditional Grouping"))
			{
				RouterInfo transInfo = (RouterInfo) rec.transInfo; 
				HashMap<String, List<OutRecDTO>> rtrgrpOutRecList = transInfo.grpOutRecList;
				
				int count = 1;
				
				List<String> rtrOutList = new ArrayList<String>();
				
				for (Entry<String, List<OutRecDTO>> entry : rtrgrpOutRecList.entrySet()) 
				{
					for (int i = 0; i < entry.getValue().size(); i++) 
					{
						for (int k = 0; k < rec.outRecsList.size(); k++)
						{
							OutRecDTO outRec = rec.outRecsList.get(k);
							if (entry.getValue().get(i).name.equalsIgnoreCase(outRec.name))
							{
								rtrOutList.add(outRec.name);
								outRec.outRecGroupIndex = count;
								
							}
						}
					}
					count++;
				}
				
				
				if (rtrOutList.size() < rec.outRecsList.size())
				{
					for (int k = 0; k < rec.outRecsList.size(); k++)
					{
						OutRecDTO outRec = rec.outRecsList.get(k);
					
						if (!rtrOutList.contains(outRec.name))
						{
							outRec.outRecGroupIndex = rec.outRecsList.size();
							
						}
						
					}
				}
			}
		}
	}


	public static void updateUniqueFieldName (ColumnDTO colDTO, List<String> unqFieldnames, ArrayList<TransRecDTO> pipewiseRecsList, TransRecDTO rec) 
	{
		if (!unqFieldnames.contains(colDTO.aliasName))
		{
			unqFieldnames.add(colDTO.aliasName);
			return;
		}

		String szField = "";
		int index = 0;
		while (true)
		{
			index++;
			szField = colDTO.aliasName + index;

			if (!unqFieldnames.contains(szField))
			{
				unqFieldnames.add(szField);
				colDTO.aliasName = szField;
				return;
			}
		}

	}
	
	public static void addColumnPrecisionScale (ColumnDTO colDTO) 
	{
		String datatype = colDTO.colStruct.dataType;

		String precScale = "";
		int precision;
		int scale;
		if(datatype != null)
		{
			colDTO.colStruct.dataType = datatype.toLowerCase();
			//precScale = switchHelper.getInfaPrecisionScale(datatype);
			if(!precScale.substring(0,precScale.indexOf("-")).equalsIgnoreCase("n"))
			{
				precision = Integer.parseInt(precScale.substring(0,precScale.indexOf("-")));
				colDTO.colStruct.precision = precision;
			}
			if(!precScale.substring(precScale.indexOf("-")+1,precScale.length()).equalsIgnoreCase("n"))
			{
				scale = Integer.parseInt(precScale.substring(precScale.indexOf("-")+1,precScale.length()));
				colDTO.colStruct.Scale = scale;
			}
		}
		else
		{
			colDTO.colStruct.dataType = "string";
			colDTO.colStruct.precision = 10;
			colDTO.colStruct.Scale = 0;
		}

	}


	public static void addColumnAliasName (ColumnDTO colDTO) 
	{
		String szAliasName = colDTO.colStruct.name;
		int nIndex = szAliasName.indexOf('.');
		if (nIndex >= 0)
			szAliasName = szAliasName.substring(nIndex+1);

		//colDTO.aliasName = szAliasName.toUpperCase();
		colDTO.aliasName = szAliasName;
	}
	
	public static void findLocalAndOutVariables (TransRecDTO rec) 
	{
		for (int k = 0; k < rec.inRecsList.size(); k++) 
		{
			ArrayList<ColumnDTO> intColsList = rec.inRecsList.get(k).prevOutRec.columnList;

			for (int k2 = 0; k2 < intColsList.size(); k2++) 
			{
				ColumnDTO colDTO = intColsList.get(k2);
				colDTO.defaultValue = "INPUT";
				
				String tfield = colDTO.colStruct.name.split("\\.")[1];
				
				for (int i = 0; i < rec.outRecsList.size(); i++) 
				{
					String nextComp = rec.outRecsList.get(i).nextInRec.parentTransRec.transType;
					/*if (rec.outRecsList.get(i).nextInRec.parentTransRec.transType.equalsIgnoreCase("Target"))
						nextComp = "Target";*/
					
					
					ArrayList<ColumnDTO> outColsList = rec.outRecsList.get(i).columnList;
					for (int j = 0; j < outColsList.size(); j++) 
					{
						ColumnDTO colDTO1 = outColsList.get(j);

						String ofield = colDTO1.colStruct.name.split("\\.")[1];
						if (tfield.equalsIgnoreCase(ofield))
						{
							colDTO.defaultValue = "INPUT/OUTPUT";
							break;
						}

						if (nextComp.equalsIgnoreCase("Target") || nextComp.equalsIgnoreCase("Other Transformation"))
						{
							ofield = colDTO1.srcColumn.colStruct.name.split("\\.")[1];
							if (tfield.equalsIgnoreCase(ofield))
							{
								colDTO.defaultValue = "INPUT/OUTPUT";
								break;
							}
						}
					}
				}
			}
		}


		if (rec.transInfo instanceof ExprInfo)
		{
			ExprInfo transInfo = (ExprInfo)rec.transInfo;

			for (int k = 0; k < transInfo.colDTOList.size(); k++) 
			{
				ColumnDTO colDTO = transInfo.colDTOList.get(k);
				colDTO.defaultValue = "LOCAL VARIABLE";

				String tfield = colDTO.colStruct.name.split("\\.")[1];

				for (int i = 0; i < rec.outRecsList.size(); i++) 
				{
					String nextComp = "";
					if (rec.outRecsList.get(i).nextInRec.parentTransRec.transType.equalsIgnoreCase("Target"))
						nextComp = "Target";
					
					if (rec.outRecsList.get(i).nextInRec.parentTransRec.transType.equalsIgnoreCase("Other Transformation"))
						nextComp = "Other Transformation";

					ArrayList<ColumnDTO> outColsList = rec.outRecsList.get(i).columnList;
					for (int j = 0; j < outColsList.size(); j++) 
					{
						ColumnDTO colDTO1 = outColsList.get(j);

						String ofield = colDTO1.colStruct.name.split("\\.")[1];
						if (tfield.equalsIgnoreCase(ofield))
						{
							colDTO.defaultValue = "OUTPUT";
							break;
						}

						if (nextComp.equalsIgnoreCase("Target") || nextComp.equalsIgnoreCase("Other Transformation"))
						{
							ofield = colDTO1.srcColumn.colStruct.name.split("\\.")[1];
							if (tfield.equalsIgnoreCase(ofield))
							{
								colDTO.defaultValue = "OUTPUT";
								break;
							}
						}
					}
				}
			}
		}
		else if (rec.transInfo instanceof AggInfo)
		{
			AggInfo transInfo = (AggInfo)rec.transInfo;

			for (int k = 0; k < transInfo.colDTOList.size(); k++) 
			{
				ColumnDTO colDTO = transInfo.colDTOList.get(k);
				colDTO.defaultValue = "LOCAL VARIABLE";

				String tfield = colDTO.colStruct.name.split("\\.")[1];

				for (int i = 0; i < rec.outRecsList.size(); i++) 
				{
					String nextComp = "";
					if (rec.outRecsList.get(i).nextInRec.parentTransRec.transType.equalsIgnoreCase("Target"))
						nextComp = "Target";

					ArrayList<ColumnDTO> outColsList = rec.outRecsList.get(i).columnList;
					for (int j = 0; j < outColsList.size(); j++) 
					{
						ColumnDTO colDTO1 = outColsList.get(j);

						String ofield = colDTO1.colStruct.name.split("\\.")[1];
						if (tfield.equalsIgnoreCase(ofield))
						{
							colDTO.defaultValue = "OUTPUT";
							break;
						}

						if (nextComp.equalsIgnoreCase("Target"))
						{
							ofield = colDTO1.srcColumn.colStruct.name.split("\\.")[1];
							if (tfield.equalsIgnoreCase(ofield))
							{
								colDTO.defaultValue = "OUTPUT";
								break;
							}
						}
					}
				}
			}
		}
	}


	public static void checkIgnoreCodegenForExp(ArrayList<TransRecDTO> recsList) 
	{
		for (int i = 0; i < recsList.size(); i++) 
		{
			TransRecExtDTO recorderPerPipe = (TransRecExtDTO) recsList.get(i);
			 
			if(recorderPerPipe.transType.equalsIgnoreCase("Expression"))
			{
				String compName = recorderPerPipe.compName;
				  for(int i1 = 0 ; i1 < recorderPerPipe.nextCompList.size() ; i1++)
				  {
					TransRecExtDTO nxtTransRec = recorderPerPipe.nextCompList.get(i1);	
					
					if(nxtTransRec.transType.equalsIgnoreCase("Conditional Grouping"))
					{
						if(nxtTransRec.compName.contains("_Exp"))
						{
							String nxtCompName = nxtTransRec.compName.substring(0, nxtTransRec.compName.lastIndexOf("_Exp"));
							if(nxtCompName.equalsIgnoreCase(compName))
							{
								recorderPerPipe.ignoreCodegen = true;
								nxtTransRec.mergerdTransRecs.add(recorderPerPipe);
							}
						}
					}
				 }
			}
		}
	}
	
	
	public static String getUpdatedDataTypeforSnowflakes(String dataType) 
	{
		if(dataType.equalsIgnoreCase("String")||dataType.equalsIgnoreCase("Character"))
		{
			dataType = "string";
		}
		else if(dataType.equalsIgnoreCase("byte[]"))
		{
			dataType = "bytes";
		}
		else if(dataType.equalsIgnoreCase("Boolean"))
		{
			dataType = "boolean";
		}
		else if(dataType.equalsIgnoreCase("Short"))
		{
			dataType = "type"+";"+"int";
		}
		else if(dataType.equalsIgnoreCase("Date"))
		{
			dataType = "type"+";"+"long";
		}
		else if(dataType.equalsIgnoreCase("Double"))
		{
			dataType = dataType.toLowerCase(); 
		}
		else if(dataType.equalsIgnoreCase("Float"))
		{
			dataType = "float";
		}
		else if(dataType.equalsIgnoreCase("BigDecimal"))
		{
			dataType = "type"+";"+"string";
		}
		else if(dataType.equalsIgnoreCase("Integer"))
		{
			dataType = "int";
		}
		else if(dataType.equalsIgnoreCase("Long"))
		{
			dataType = "long";
		}
		else if(dataType.equalsIgnoreCase("Object"))
		{
			dataType = "string";
		}
		return dataType;
	}


	public static String getUpdatedQuery(String overrideSQL, Connector docspecs, List<String> cntxtParamsList) {
		
		List<Token> token = FunctionTypeConv.getTokens(overrideSQL);
		String updatedQ = "";
		
		for(Token tk: token){
			String param = tk.data;
			
			
			if(tk.type.name().equals("STRCONSTANTS"))
			{
				if(param.contains("#") ){
					String updatedParam = param.substring(param.indexOf('#')+1, param.lastIndexOf('#'));
					String before = param.substring(0,param.indexOf('#'));
					String after = param.substring(param.lastIndexOf('#')+1);
					if(updatedParam.contains(".")){
						String check = updatedParam.substring(0, updatedParam.indexOf("."));
						if(cntxtParamsList.contains(check)){
							String first = "context."+check;
							String second = updatedParam.substring(updatedParam.indexOf(".")+1);
							param = "";
							param = before + first + "_" + second  + after;
							
							String queryParam = check + "_" + second;
							
							if(!cntxtParamsList.contains(queryParam)){
								cntxtParamsList.add(queryParam);
							}
						}
					}
				}
			}
				
			updatedQ += param+" ";
			
			
		}
		updatedQ = updatedQ.replace(" . ", ".");
		
		return updatedQ;
	}
}

