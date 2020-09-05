
package com.dataSwitch.xml.TalendCodegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.TalendProp.XmiXMI;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow.Steps.Step;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow.Steps.Step.PreviousSteps;

public class CreateSeqJob {

	public static void fillSeqCompDetails(ProcessFlow processFlow, TalendfileProcessType pipeline, String szoutputLinkedServiceFilePath, int countSeq) {
		
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
	    
	    HashMap<String,SeqJobHelper> stepMap = new HashMap<String,SeqJobHelper>();
	    stepMap = CreateSeqJob.fillStepMap(stepMap,processFlow);
	    
	    for(int i = 0; i< processFlow.getSteps().getStep().size(); i++){
	    	
	    	Step step = processFlow.getSteps().getStep().get(i);
	    	String stepName = step.getName();
	    	if(step.getStepType().equalsIgnoreCase("Filter Task"))
	    		continue;
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Event Wait")){
	    		createSeqComponents.fillPropForWaitFile(step,pipeline);
	    	}
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Command Task")){
	    		createSeqComponents.fillPropForSysCommand(step,pipeline);
	    	}
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Data Load")){
	    		createSeqComponents.fillPropForRunJob(step,pipeline);
	    	}
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Email Task")){
	    		createSeqComponents.fillPropForMail(step,pipeline);
	    	}
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Assignment Task")){
	    		createSeqComponents.fillPropForUserVariable(step,pipeline,cntxtParamsList);
	    	}
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Sequence Task"))
	    	{
	    		createSeqComponents.fillPropForSequencer(step,pipeline);
	    	}
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Control Task"))
	    	{
	    		createSeqComponents.fillPropForTDie(step,pipeline);
	    	}
	    	
	    	else if(step.getStepType().equalsIgnoreCase("Other Task"))
	    	{
	    		
	    		HashMap<String,String> paramMap = new HashMap<String,String>();
	    		
	    		if(step.getOtherTask().getParameter()!=null){
	    			for(int j=0; j<step.getOtherTask().getParameter().size(); j++){
		    			String name = step.getOtherTask().getParameter().get(j).getName();
		    			String value = step.getOtherTask().getParameter().get(j).getValue();
		    			paramMap.put(name, value);
		    		}
	    			if(step.getOtherTask().getTaskType()!=null){
	    				paramMap.put(stepName,step.getOtherTask().getTaskType() );
	    			}
	    		}
	    		
	    		if(step.getOtherTask()!=null)
	    		{
	    			
	    			stepName = step.getName();
	    			
	    			if(paramMap.get(stepName).equalsIgnoreCase("EndLoopActivity"))
		    			continue;
		    		
		    		else if(paramMap.get(stepName).equalsIgnoreCase("StartLoopActivity"))
		    		{
		    			createSeqComponents.fillPropForLoop(step,pipeline);
		    		}
	    			
		    		else if(paramMap.get(stepName).equalsIgnoreCase("Routine  Activity") )
		    		{
		    			createSeqComponents.fillPropForRoutine(step,pipeline,paramMap,szoutputLinkedServiceFilePath);
		    		}
	    			
		    		else if(paramMap.get(stepName).equalsIgnoreCase("Exception Activity") )
		    		{
		    			createSeqComponents.fillPropForTAssertCatcher(step,pipeline,paramMap);
		    		}
	    		}
	    		
	    		
	    	}
	    	
	    	if(step.getPreviousSteps()!=null && step.getPreviousSteps().getPreviousStep().size()!=0){
	    		String compName = step.getName();
	    		PreviousSteps prevSteps = step.getPreviousSteps();
				createConnecForSeqJob.createConnection(compName,prevSteps,pipeline,stepMap);
	    	}
	    }
		
		
	    for(int i=0; i<cntxtParamsList.size(); i++){
	    	String paramName = cntxtParamsList.get(i);
	    	TalendfileProcessType.Context.ContextParameter cntxtParam = new  TalendfileProcessType.Context.ContextParameter();
    		cntxtParam.setComment("");
    		cntxtParam.setname(paramName);
    		cntxtParam.setpromptNeeded("false");
    		cntxtParam.setprompt("");
    		cntxtParam.setvalue("");
    		String datatype = "id_String";
    		cntxtParam.settype(datatype);
    		cntxt.getContextParameter().add(cntxtParam);
	    }
	    
	    pipeline.setContext(cntxt);
	}

	private static HashMap<String, SeqJobHelper> fillStepMap(HashMap<String, SeqJobHelper> stepMap, ProcessFlow processFlow) {
		
		
		 for(int i = 0; i< processFlow.getSteps().getStep().size(); i++){
		    	
		    	Step step = processFlow.getSteps().getStep().get(i);
		    	SeqJobHelper seqHelper = new SeqJobHelper();
		    	String stepName = step.getName();
		    	if(step.getStepType().equalsIgnoreCase("Filter Task")){
		    		
		    	}
		    		
		    	else if(step.getStepType().equalsIgnoreCase("Event Wait")){
		    		seqHelper.stepType = "wait";
		    	
		    		stepMap.put(stepName, seqHelper);
		    	}
		    	
		    	else if(step.getStepType().equalsIgnoreCase("Command Task")){
		    		seqHelper.stepType = "system_command";
		    		stepMap.put(stepName, seqHelper);
		    	}
		    	
		    	else if(step.getStepType().equalsIgnoreCase("Data Load")){
		    		seqHelper.stepType = "run_job";
		    		stepMap.put(stepName, seqHelper);
		    	}
		    	
		    	else if(step.getStepType().equalsIgnoreCase("Email Task")){
		    		seqHelper.stepType = "mail";
		    		stepMap.put(stepName, seqHelper);
		    	}
		    	
		    	else if(step.getStepType().equalsIgnoreCase("Assignment Task")){
		    		seqHelper.stepType = "assignment";
		    		stepMap.put(stepName, seqHelper);
		    	}
		    	
		    	else if(step.getStepType().equalsIgnoreCase("Sequence Task")){
		    		seqHelper.stepType = "parallelize";
		    		stepMap.put(stepName, seqHelper);
		    	}
		    	
		    	else if(step.getStepType().equalsIgnoreCase("Control Task"))
		    	{
		    		seqHelper.stepType = "die";
		    		stepMap.put(stepName, seqHelper);
		    	}
		    	
		    	else if(step.getStepType().equalsIgnoreCase("Other Task")){
		    		
		    		HashMap<String,String> paramMap = new HashMap<String,String>();
		    		if(step.getOtherTask().getParameter()!=null)
		    		{
		    			for(int j = 0; j < step.getOtherTask().getParameter().size(); j++)
		    			{
			    			String name = step.getOtherTask().getParameter().get(j).getName();
			    			String value = step.getOtherTask().getParameter().get(j).getValue();
			    			paramMap.put(name, value);
			    		}
		    			if(step.getOtherTask().getTaskType()!=null){
		    				paramMap.put(stepName,step.getOtherTask().getTaskType());
		    			}
		    		}
		    		
		    		if(step.getOtherTask()!=null)
		    		{
		    			if(paramMap.get(stepName).equalsIgnoreCase("EndLoopActivity"))
			    			continue;
			    		
			    		else if(paramMap.get(stepName).equalsIgnoreCase("StartLoopActivity")){
			    			seqHelper.stepType = "loop";
				    		stepMap.put(stepName, seqHelper);
			    		}
		    			
			    		else if(paramMap.get(stepName).equalsIgnoreCase("Routine  Activity") )
			    		{
			    			seqHelper.stepType = "routine";
			    			stepMap.put(stepName, seqHelper);
			    		}
		    			
			    		else if(paramMap.get(stepName).equalsIgnoreCase("Exception Activity") )
			    		{
			    			seqHelper.stepType = "exception_Handler";
			    			stepMap.put(stepName, seqHelper);
			    		}
		    		}
		    		
		    		
		    	}
		    	
		    	
		    }
		
		 
		 stepMap = CreateSeqJob.updateStepMap(stepMap,processFlow);
		 
		return stepMap;
	}

	private static HashMap<String, SeqJobHelper> updateStepMap(HashMap<String, SeqJobHelper> stepMap,ProcessFlow processFlow) {
		
		for(int i = 0; i< processFlow.getSteps().getStep().size(); i++){
	    	
	    	Step step = processFlow.getSteps().getStep().get(i);
	    	
	    	if(step.getStepType().equalsIgnoreCase("Filter Task")){
	    		if(step.getPreviousSteps()!=null && step.getPreviousSteps().getPreviousStep().size()!=0){
	    			String prevStepName = step.getPreviousSteps().getPreviousStep().get(0).getStepRef();
	    			SeqJobHelper seqHelper = stepMap.get(prevStepName);
	    			if(step.getFilterTask()!=null && step.getFilterTask().getFilterCondition()!=null && !step.getFilterTask().getFilterCondition().equalsIgnoreCase("")){
	    				seqHelper.hasCondition = "yes";
	    				String condition = step.getFilterTask().getFilterCondition();
	    				seqHelper.filterCondition = condition;
	    			}
	    		}
	    	}
		}
		return stepMap;
	}

	public static void fillSeqPropDetails(XmiXMI pipelineprop, ProcessFlow processFlow, String processName, int countPropSeq) {
		
		String jobName = processName;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.ENGLISH);
        pipelineprop.setXmiVersion("2.0");
        pipelineprop.setXmlnsXmi("http://www.omg.org/XMI");
        pipelineprop.setXmlnsTalendProperties("http://www.talend.org/properties");
        XmiXMI.TalendPropertiesProperty tpropsprop = new XmiXMI.TalendPropertiesProperty();
        XmiXMI.TalendPropertiesProperty.Author author = new XmiXMI.TalendPropertiesProperty.Author();
        tpropsprop.setXmiId("_KNP"+countPropSeq+"NVjEem8zPVGzoyeSA");
        tpropsprop.setCreationDate(""+sdf.format(date));
        tpropsprop.setId("_KNO"+countPropSeq+"NVjEem8zPVGzoyeSA");
        tpropsprop.setLabel(jobName);
        tpropsprop.setModificationDate(""+sdf.format(date));
        tpropsprop.setVersion(0.1);
        tpropsprop.setItem("_KNP"+countPropSeq+"tVjEem8zPVGzoyeSA");
        tpropsprop.setDisplayName(jobName);
        author.setHref("../../../../../talend.project#_eYczcD8JEemQqtJiSuRJJw");
        tpropsprop.setAuthor(author);
        pipelineprop.setTalendPropertiesProperty(tpropsprop);
        XmiXMI.TalendPropertiesItemState tItemstate = new XmiXMI.TalendPropertiesItemState();
        tItemstate.setXmiId("_Hu9_MNXlEemb"+countPropSeq+"VWnESdsQ");
        tItemstate.setPath("DS_SeqJob");
        pipelineprop.setTalendPropertiesItemState(tItemstate);
        XmiXMI.TalendPropertiesProcessItem processItem = new XmiXMI.TalendPropertiesProcessItem();
        processItem.setXmiId("_KNP"+countPropSeq+"tVjEem8zPVGzoyeSA");
        processItem.setProperty("_KNP"+countPropSeq+"NVjEem8zPVGzoyeSA");
        processItem.setState("_Hu9_MNXlEemb"+countPropSeq+"VWnESdsQ");
        XmiXMI.TalendPropertiesProcessItem.Process process = new XmiXMI.TalendPropertiesProcessItem.Process();
        process.setHref(jobName+".item#/");
        processItem.setProcess(process);
        pipelineprop.setTalendPropertiesProcessItem(processItem);   
	}

	public static boolean checkSeqHasSteps(ProcessFlow processFlow) 
	{
		if(processFlow.getSteps() != null)
		{
			if(processFlow.getSteps().getStep().size() == 1)
			{
				if(processFlow.getSteps().getStep().size() == 1){
					
					Step stp = processFlow.getSteps().getStep().get(0);
			    	
			    	if(stp.getStepType().equalsIgnoreCase("Data Load") || stp.getName().equalsIgnoreCase(processFlow.getName()))
			    	{
			    		return true;
			    	}
					
				}
				
			}
		}
		return false;
	}

	public static void fillRoutinePropDetails(XmiXMI pipelineprop, String processName, int countPropRoutine) {
		
		String jobName = processName;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.ENGLISH);
        pipelineprop.setXmiVersion("2.0");
        pipelineprop.setXmlnsXmi("http://www.omg.org/XMI");
        pipelineprop.setXmlnsTalendProperties("http://www.talend.org/properties");
        XmiXMI.TalendPropertiesProperty tpropsprop = new XmiXMI.TalendPropertiesProperty();
        XmiXMI.TalendPropertiesProperty.Author author = new XmiXMI.TalendPropertiesProperty.Author();
        tpropsprop.setXmiId("_3pGeIOv5Eemke6fqAmiOMA");
        tpropsprop.setCreationDate(""+sdf.format(date));
        tpropsprop.setId("_3o7fAOv5Eemke6fqAmiOMA");
        tpropsprop.setLabel(jobName);
        tpropsprop.setModificationDate(""+sdf.format(date));
        tpropsprop.setVersion(0.1);
        tpropsprop.setItem("_3pGeIuv5Eemke6fqAmiOMA");
        tpropsprop.setDisplayName(jobName);
        author.setHref("../../../../../talend.project#_eYczcD8JEemQqtJiSuRJJw");
        tpropsprop.setAuthor(author);
        pipelineprop.setTalendPropertiesProperty(tpropsprop);
        XmiXMI.TalendPropertiesItemState tItemstate = new XmiXMI.TalendPropertiesItemState();
        tItemstate.setXmiId("_3pGeIev5Eemke6fqAmiOMA");
        tItemstate.setPath("DS_CustomRoutines");
        pipelineprop.setTalendPropertiesItemState(tItemstate);
        XmiXMI.TalendPropertiesRoutineItem routineItem = new XmiXMI.TalendPropertiesRoutineItem();
        routineItem.setXmiId("_3pGeIuv5Eemke6fqAmiOMA");
        routineItem.setProperty("_3pGeIOv5Eemke6fqAmiOMA");
        routineItem.setState("_3pGeIev5Eemke6fqAmiOMA");
        XmiXMI.TalendPropertiesRoutineItem.Content content = new XmiXMI.TalendPropertiesRoutineItem.Content();
        content.setHref(jobName+".item#/");
        routineItem.setContent(content);
        pipelineprop.setTalendPropertiesRoutineItem(routineItem);
		
	}

	public static void marshalTalendRoutinePropXML(XmiXMI xmlProp, String processName, String szoutputLinkedServiceFilePath) {
		
		Marshaller jaxbMarshaller = null;
		try{

			JAXBContext njc = JAXBContext.newInstance(XmiXMI.class);
			jaxbMarshaller = njc.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);   
			
		}
		catch(JAXBException e)
		{
			e.printStackTrace();
		}
		
		
		try {
			  String folderName = "TalendXMLFile";
			   Paths.get(szoutputLinkedServiceFilePath,
			              folderName).toFile().mkdir();
		
			   String PipelineName  = processName;
			File file = new File(szoutputLinkedServiceFilePath,
	              folderName+File.separator+PipelineName+".properties");
		
	        
	        jaxbMarshaller.marshal(xmlProp, file);
	  
			
			if(file.exists()){
	        	file.setWritable(true);
	        	file.setExecutable(true);
	        	file.setReadable(true);
	        }
		  
	      
		} catch (JAXBException e) {
			
		System.out.println("Illegal error");
			e.printStackTrace();
		}
		
	}

	public static void fillRoutineItemDetails(String code, String processName, String szoutputLinkedServiceFilePath) {
		
		FileWriter fr = null;
        BufferedWriter br = null;
		
        String folderName = "TalendXMLFile";
	    Paths.get(szoutputLinkedServiceFilePath,folderName).toFile().mkdir();

	    String PipelineName  = processName;
	    File file = new File(szoutputLinkedServiceFilePath,
        folderName+File.separator+PipelineName+".item");
        
		try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            br.write(code);
          
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	     
		
	}

}