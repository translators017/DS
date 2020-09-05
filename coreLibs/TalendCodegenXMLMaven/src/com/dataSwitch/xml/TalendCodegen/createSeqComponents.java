
package com.dataSwitch.xml.TalendCodegen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Context;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata.Column;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata.Column.AdditionalField;
import com.dataSwitch.xml.TalendProp.XmiXMI;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow.Steps.Step;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow.Steps.Step.OtherTask.Parameter;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow.Steps.Step.PreviousSteps;

public class createSeqComponents {

	public static void fillPropForWaitFile(Step step, TalendfileProcessType pipeline) {
		
		String compName = step.getName();
		String wait = "";
		String fileName = "";
		String directory = "";
		String fileMask = "";
		if(step.getEventRaise()!=null){
			wait = step.getEventRaise().getWaitPeriod();
			fileName = step.getEventRaise().getFileName();
			directory = fileName.substring(0,fileName.lastIndexOf(File.separator)).replace(File.separator, "/");
			fileMask = "*"+fileName.substring(fileName.lastIndexOf('.'),fileName.length());
		}
		
		
		
		Node compNode = new Node();
		compNode.setComponentName("tWaitForFile");
		compNode.setComponentVersion(0.102);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("192");
		compNode.setPosY("96");
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","WAIT",wait));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","MAX_ITERATIONS",""));
		elemParam.add(Utils.getPropDtlsforExp("DIRECTORY","DIRECTORY","\""+directory+"\""));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","FILEMASK","\""+fileMask+"\""));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","INCLUDE_SUBDIR","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","CASE_SENSITIVE","true"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","INCLUDE_PRESENT","false"));
		elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","ACTION_ON","fileall"));
		elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","THEN","exitloop"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","WAIT_RELEASE","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","INTERVAL_CHECK","500","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","NON_UPDATE","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		//mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		Column col1 = new Column(); Column col2 = new Column(); Column col3 = new Column(); Column col4 = new Column(); Column col5 = new Column(); Column col6 = new Column(); Column col7 = new Column(); 
		col1.setDefaultValue(""); col2.setDefaultValue(""); col3.setDefaultValue(""); col4.setDefaultValue(""); col5.setDefaultValue(""); col6.setDefaultValue(""); col7.setDefaultValue("");
		col1.setKey("false"); col2.setKey("false"); col3.setKey("false"); col4.setKey("false"); col5.setKey("false"); col6.setKey("false"); col7.setDefaultValue("");
		col1.setLength(0); col2.setLength(255); col3.setLength(255); col4.setLength(255); col5.setLength(255); col6.setLength(255); col7.setLength(255);
		col1.setName("CURRENT_ITERATION");col2.setName("PRESENT_FILE"); col3.setName("DELETED_FILE"); col4.setName("CREATED_FILE"); col5.setName("UPDATED_FILE"); col6.setName("FILENAME"); col7.setName("NOT_UPDATED_FILE");
		col1.setNullable("true"); col2.setNullable("true"); col3.setNullable("true"); col4.setNullable("true"); col5.setNullable("true"); col6.setNullable("true"); col7.setNullable("true");
		col1.setPrecision(0); col2.setPrecision(0); col3.setPrecision(0); col4.setPrecision(0); col5.setPrecision(0); col6.setPrecision(0); col7.setPrecision(0);
		col1.setsourceType(""); col2.setsourceType(""); col3.setsourceType(""); col4.setsourceType(""); col5.setsourceType(""); col6.setsourceType(""); col7.setsourceType("");
		col1.setType("id_Integer"); col2.setType("id_String"); col3.setType("id_String"); col4.setType("id_String"); col5.setType("id_String"); col6.setType("id_String"); col7.setType("id_String");
		col1.setoriginalLength("-1"); col2.setoriginalLength("-1"); col3.setoriginalLength("-1"); col4.setoriginalLength("-1"); col5.setoriginalLength("-1"); col6.setoriginalLength("-1"); col7.setoriginalLength("-1");
		col1.setUsefulColumn("true"); col2.setUsefulColumn("true"); col3.setUsefulColumn("true"); col4.setUsefulColumn("true"); col5.setUsefulColumn("true"); col6.setUsefulColumn("true"); col7.setUsefulColumn("true");
		mtdata.getColumn().add(col1); mtdata.getColumn().add(col2); mtdata.getColumn().add(col3); mtdata.getColumn().add(col4); mtdata.getColumn().add(col5); mtdata.getColumn().add(col6); mtdata.getColumn().add(col7);
		
		compNode.getMetadata().add(mtdata);
		
		pipeline.getNode().add(compNode);
	}

	public static void fillPropForSysCommand(Step step, TalendfileProcessType pipeline) {

		Node compNode = new Node();
		compNode.setComponentName("tSystem");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("210");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String command = step.getCommandTask().getCommand().get(0).getName();
		String directory = "D:/Development_Avecto/TOS_BD-20180411_1414-V7.0.1/TOS_BD-20180411_1414-V7.0.1/workspace";
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","ROOTDIR","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("DIRECTORY","DIRECTORY","\""+directory+"\"","false"));
		elemParam.add(Utils.getPropDtlsforExp("RADIO","USE_SINGLE_COMMAND","true"));
		elemParam.add(Utils.getPropDtlsforExp("MEMO_JAVA","COMMAND","\""+command+"\""));
		elemParam.add(Utils.getPropDtlsforExp("RADIO","USE_ARRAY_COMMAND","false"));
		
		ElementParameter elemParam2 = new ElementParameter();
		elemParam2.setField("TABLE"); elemParam2.setName("ARRAY_COMMAND"); elemParam2.setShow("false");
		
		ArrayList<ElementParameter> elemParam3 = new ArrayList<ElementParameter>();
		elemParam3.add(Utils.getPropDtlsforExp("CLOSED_LIST","OUTPUT","OUTPUT_TO_CONSOLE"));
		elemParam3.add(Utils.getPropDtlsforExp("CLOSED_LIST","ERROROUTPUT","OUTPUT_TO_CONSOLE"));
		
		ElementParameter elemParam4 = new ElementParameter();
		elemParam4.setField("TABLE"); elemParam4.setName("PARAMS");
		
		ElementParameter elemParam5 = new ElementParameter();
		elemParam5.setField("TEXT"); elemParam5.setName("CONNECTION_FORMAT"); elemParam5.setValue("row");
		
		compNode.getElementParameter().addAll(elemParam);
		compNode.getElementParameter().add(elemParam2);
		compNode.getElementParameter().addAll(elemParam3);
		compNode.getElementParameter().add(elemParam4);
		compNode.getElementParameter().add(elemParam5);
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		//mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		compNode.getMetadata().add(mtdata);
		
		pipeline.getNode().add(compNode);
		
	}

	public static void fillPropForRunJob(Step step, TalendfileProcessType pipeline) {
		
		Node compNode = new Node();
		compNode.setComponentName("tRunJob");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("290");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String jobName = step.getDataLoad().getDataMappingRef();
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_DYNAMIC_JOB","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","CONTEXT_JOB","","false"));
		elemParam.add(Utils.getPropDtlsforExp("PROCESS_TYPE","PROCESS",jobName));
		elemParam.add(Utils.getPropDtlsforExp("TECHNICAL","PROCESS:PROCESS_TYPE_CONTEXT","Default"));
		elemParam.add(Utils.getPropDtlsforExp("TECHNICAL","PROCESS:PROCESS_TYPE_PROCESS","_DCzHUM_MEemmOo563lVOKw"));
		elemParam.add(Utils.getPropDtlsforExp("TECHNICAL","PROCESS:PROCESS_TYPE_VERSION","Latest"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","CONTEXT_NAME","Default","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_INDEPENDENT_PROCESS","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","DIE_ON_CHILD_ERROR","true"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","TRANSMIT_WHOLE_CONTEXT","false"));
		
		ElementParameter elemParam2 = new ElementParameter();
		elemParam2.setField("TABLE"); elemParam2.setName("CONTEXTPARAMS");
		
		ArrayList<ElementParameter> elemParam3 = new ArrayList<ElementParameter>();
		
		elemParam3.add(Utils.getPropDtlsforExp("CHECK","PROPAGATE_CHILD_RESULT","false"));
		elemParam3.add(Utils.getPropDtlsforExp("CHECK","PRINT_PARAMETER","false"));
		elemParam3.add(Utils.getPropDtlsWithShwforExp("CHECK","TRANSMIT_ORIGINAL_CONTEXT","true","false"));
		elemParam3.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
		compNode.getElementParameter().add(elemParam2);
		compNode.getElementParameter().addAll(elemParam3);
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		//mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		compNode.getMetadata().add(mtdata);
		
		pipeline.getNode().add(compNode);
		
	}

	public static void fillPropForLoop(Step step, TalendfileProcessType pipeline) {
		
		Node compNode = new Node();
		compNode.setComponentName("tLoop");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("360");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String from = "1";
		String to = "";
		String increment = "";
		
		for(int i = 0; i<step.getOtherTask().getParameter().size(); i++){
			Parameter param = step.getOtherTask().getParameter().get(i);
			if(param.getName().equalsIgnoreCase("NumericLoopIncrement")){
				increment = param.getValue();
			}
			else if(param.getName().equalsIgnoreCase("NumericLoopEnd")){
				to = param.getValue();
			}
		}
		
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("RADIO","FORLOOP","true"));
		elemParam.add(Utils.getPropDtlsforExp("RADIO","WHILELOOP","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","FROM",from));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","TO",to));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","STEP",increment));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","INCREASE","true"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","DECLARATION","int i=0","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","CONDITION","i<10","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","ITERATION","i++","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
		
		pipeline.getNode().add(compNode);
		
	}

	public static void fillPropForMail(Step step, TalendfileProcessType pipeline) {
		
		Node compNode = new Node();
		compNode.setComponentName("tSendMail");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("360");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String from = step.getEmailTask().getSenderEmailID();
		String to = step.getEmailTask().getRecipientEmailID();
		String subject = step.getEmailTask().getSubject();
		String msg = step.getEmailTask().getMessage();
		String smtpHost = step.getEmailTask().getSmtpServerName();
		String smtpPort = "25";
		String username = "username";
		String password = "password";
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","TO","\""+to+"\""));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","FROM","\""+from+"\""));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","NEED_PERSONAL_NAME","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","PERSONAL_NAME","","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CC",""));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","BCC",""));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","SUBJECT","\""+subject+"\""));
		elemParam.add(Utils.getPropDtlsforExp("MEMO_MESSAGE","MESSAGE","\""+msg+"\""));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","SSL","false"));
		
		ElementParameter elemParam2 = new ElementParameter();
		elemParam2.setField("TABLE"); elemParam2.setName("ATTACHMENTS");
		
		ElementParameter elemParam3 = new ElementParameter();
		elemParam3.setField("TABLE"); elemParam3.setName("HEADERS");
		
		ArrayList<ElementParameter> elemParam4 = new ArrayList<ElementParameter>();
		elemParam4.add(Utils.getPropDtlsforExp("TEXT","SMTP_HOST","\""+smtpHost+"\""));
		elemParam4.add(Utils.getPropDtlsforExp("TEXT","SMTP_PORT",smtpPort));
		elemParam4.add(Utils.getPropDtlsforExp("CHECK","SSL","false"));
		elemParam4.add(Utils.getPropDtlsforExp("CHECK","STARTTLS","false"));
		elemParam4.add(Utils.getPropDtlsforExp("CLOSED_LIST","IMPORTANCE","Normal"));
		elemParam4.add(Utils.getPropDtlsforExp("CHECK","NEED_AUTH","true"));
		elemParam4.add(Utils.getPropDtlsforExp("TEXT","AUTH_USERNAME",username));
		elemParam4.add(Utils.getPropDtlsforExp("PASSWORD","AUTH_PASSWORD",password));
		elemParam4.add(Utils.getPropDtlsforExp("CHECK","DIE_ON_ERROR","true"));
		elemParam4.add(Utils.getPropDtlsforExp("CLOSED_LIST","TEXT_SUBTYPE","plain"));
		elemParam4.add(Utils.getPropDtlsforExp("ENCODING_TYPE","ENCODING","\"ISO-8859-15\""));
		elemParam4.add(Utils.getPropDtlsforExp("TECHNICAL","ENCODING:ENCODING_TYPE","\"ISO-8859-15\""));
		elemParam4.add(Utils.getPropDtlsforExp("CHECK","SET_LOCALHOST","false"));
		elemParam4.add(Utils.getPropDtlsWithShwforExp("TEXT","LOCALHOST","\"localhost\"","false"));
		elemParam4.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
		compNode.getElementParameter().add(elemParam2);
		compNode.getElementParameter().add(elemParam3);
		compNode.getElementParameter().addAll(elemParam4);
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		//mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		compNode.getMetadata().add(mtdata);
		
		pipeline.getNode().add(compNode);
		
	}

	public static void fillPropForRoutine(Step step, TalendfileProcessType pipeline, HashMap<String, String> paramMap, String szoutputLinkedServiceFilePath) {
		
		Node compNode = new Node();
		compNode.setComponentName("tJava");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("360");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String routine = paramMap.get("Routinename").replace('.', '_');
		String routine2 = "routines."+routine;
		
		String param = paramMap.get("ParametersName").replace('[', '(').replace(']', ')');
		String routineCall = routine2+param+";";
		
		if(paramMap.containsKey("code")){
			String code = paramMap.get("code");
			code = "package routines; \n" + "/*"+code+"*/";
			if(code!=null || !code.equalsIgnoreCase("")){
				int countPropRoutine = 10;
				String processName = routine;
				XmiXMI xmlProp = new XmiXMI();
			    CreateSeqJob.fillRoutinePropDetails(xmlProp, processName,countPropRoutine++);
				CreateSeqJob.fillRoutineItemDetails(code,processName,szoutputLinkedServiceFilePath);
				CreateSeqJob.marshalTalendRoutinePropXML(xmlProp,processName,szoutputLinkedServiceFilePath);
			}
		}
		
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("MEMO_JAVA","CODE",routineCall));
		elemParam.add(Utils.getPropDtlsforExp("MEMO_IMPORT","IMPORT","//import java.util.List;"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		compNode.getElementParameter().addAll(elemParam);
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		//mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		Column col = new Column();
		col.setComment(""); col.setKey("false"); col.setLength(-1); col.setName("newColumn"); col.setNullable("true"); col.setPattern(""); col.setPrecision(-1); col.setsourceType(""); col.setType("id_String"); col.setoriginalLength("-1"); col.setUsefulColumn("true");
		AdditionalField addfield = new AdditionalField();
		addfield.setkey("FUNCTION_INFO");
		addfield.setvalue("{\"NAME\":\"getAsciiRandomString\",\"PARAMETERS\":[{\"PARAMETER_NAME\":\"length\",\"PARAMETER_VALUE\":\"6\"}],\"PARAMETER_CLASS_NAME\":\"TalendString\"}");
		col.setAdditionalField(addfield);
		mtdata.getColumn().add(col);
		
		compNode.getMetadata().add(mtdata);
		
		pipeline.getNode().add(compNode);
	}

	public static void fillPropForUserVariable(Step step, TalendfileProcessType pipeline, List<String> cntxtParamsList) {
		
		Node compNode = new Node();
		compNode.setComponentName("tJava");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("360");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String code = "";
		
		for(int i = 0; i<step.getAssignmentTask().getVariableAssignment().size(); i++){
			String varName = step.getAssignmentTask().getVariableAssignment().get(i).getUserDefinedVariable();
			String value = step.getAssignmentTask().getVariableAssignment().get(i).getExpression();
			
			value = FunctionTypeConv.getCurrentFunctions(value, ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND );
			
			code += "context."+varName+" = "+value+";\n";
			cntxtParamsList.add(varName);
		}
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("MEMO_JAVA","CODE",code));
		elemParam.add(Utils.getPropDtlsforExp("MEMO_IMPORT","IMPORT","//import java.util.List;"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		compNode.getElementParameter().addAll(elemParam);
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		//mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		Column col = new Column();
		col.setComment(""); col.setKey("false"); col.setLength(-1); col.setName("newColumn"); col.setNullable("true"); col.setPattern(""); col.setPrecision(-1); col.setsourceType(""); col.setType("id_String"); col.setoriginalLength("-1"); col.setUsefulColumn("true");
		AdditionalField addfield = new AdditionalField();
		addfield.setkey("FUNCTION_INFO");
		addfield.setvalue("{\"NAME\":\"getAsciiRandomString\",\"PARAMETERS\":[{\"PARAMETER_NAME\":\"length\",\"PARAMETER_VALUE\":\"6\"}],\"PARAMETER_CLASS_NAME\":\"TalendString\"}");
		col.setAdditionalField(addfield);
		mtdata.getColumn().add(col);
		
		compNode.getMetadata().add(mtdata);
		
		pipeline.getNode().add(compNode);
		
	}

	public static void fillPropForSequencer(Step step, TalendfileProcessType pipeline) {
		
		Node compNode = new Node();
		compNode.setComponentName("tParallelize");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("360");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String seqCond = step.getSequenceTask().getSequencerCondition();
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","WAIT_FOR",seqCond));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","SLEEPTIME","100"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","DIE_ON_ERROR","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
		
		pipeline.getNode().add(compNode);
	}

	public static void fillPropForTDie(Step step, TalendfileProcessType pipeline) 
	{
		Node compNode = new Node();
		compNode.setComponentName("tDie");
		compNode.setComponentVersion(0.102);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("360");
		compNode.setPosY("96");
		
		String compName = step.getName();
		String msg = step.getControlTask().getStopOption();
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","MESSAGE","\""+msg+"\""));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CODE","4"));
		elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","PRIORITY","5"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","EXIT_JVM","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		//mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		Column col = new Column();
		col.setComment(""); col.setKey("false"); col.setLength(-1); col.setName("newColumn"); col.setNullable("true"); col.setPattern(""); col.setPrecision(-1); col.setsourceType(""); col.setType("id_String"); col.setoriginalLength("-1"); col.setUsefulColumn("true");
		AdditionalField addfield = new AdditionalField();
		addfield.setkey("FUNCTION_INFO");
		addfield.setvalue("{\"NAME\":\"getAsciiRandomString\",\"PARAMETERS\":[{\"PARAMETER_NAME\":\"length\",\"PARAMETER_VALUE\":\"6\"}],\"PARAMETER_CLASS_NAME\":\"TalendString\"}");
		col.setAdditionalField(addfield);
		mtdata.getColumn().add(col);
		
		compNode.getMetadata().add(mtdata);
		
		pipeline.getNode().add(compNode);
	}
	
	
	public static void fillPropForTAssertCatcher(Step step, TalendfileProcessType pipeline,
			HashMap<String, String> paramMap) 
	{
		String compName = step.getName();
		Node compNode = new Node();
		compNode.setComponentName("tAssertCatcher");
		compNode.setComponentVersion(0.101);
		compNode.setOffsetLabelX(0);
		compNode.setOffsetLabelY(0);
		compNode.setPosX("990");
		compNode.setPosY("560");
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","CATCH_JAVA_EXCEPTION","true"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","GET_ORIGINAL_EXCEPTION","true"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","CATCH_TASSERT","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		
		compNode.getElementParameter().addAll(elemParam);
		
		Metadata mtData = new Metadata();
		mtData.setConnector("FLOW");
		mtData.setName(compName);
		
		ArrayList<Column> colmList = new ArrayList<Column>();
		colmList.add(getMetaDataColmDtls("","false",0,"moment","true",0,"","id_Date","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",20,"pid","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",50,"project","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",255,"job","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",5,"language","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",50,"origin","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",10,"status","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",255,"substatus","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",255,"description","true",0,"","id_String","-1","true"));
		colmList.add(getMetaDataColmDtls("","false",0,"exception","true",0,"","id_String","-1","true"));
		
		
		mtData.getColumn().addAll(colmList);
		
		compNode.getMetadata().add(mtData);
		pipeline.getNode().add(compNode);	
	}
	
	private static Column getMetaDataColmDtls(String defaultValue,String key,int length,String name,String nullable,int precision,String sourceType,String type,String originalLength,String usefulColumn) 
	{
		Column colmn = new Column();
		colmn.setName(defaultValue);
		colmn.setKey(key);
		colmn.setLength(length);
		colmn.setName(name);
		colmn.setNullable(nullable);
		colmn.setPrecision(precision);
		colmn.setsourceType(sourceType);
		colmn.setType(type);
		if(type.equalsIgnoreCase("id_Date"))
			colmn.setPattern("\"yyyy-MM-dd HH:mm:ss\"");
		colmn.setoriginalLength(originalLength);
		colmn.setUsefulColumn(usefulColumn);
		
		return colmn;
		
	}



}