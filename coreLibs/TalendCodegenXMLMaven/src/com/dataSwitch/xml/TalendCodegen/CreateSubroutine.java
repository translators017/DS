
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*import com.dataSwitch.ds.functionTypeRules.FnConverter;
import com.dataSwitch.ds.functionTypeRules.TestTypeConv;*/
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ExprInfo;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.OutPutTables;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.VarTables;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Subjob;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow;

public class CreateSubroutine {
	
	public static void createSubroutine(ProcessFlow processFlow, TalendfileProcessType pipeline, Connector docspecs, List<String> cntxtParamsList){
		
		//TalendfileProcessType.Node compNode = new TalendfileProcessType.Node();
		if(processFlow.getDescription().equalsIgnoreCase("After-job subroutine")){
			
			TalendfileProcessType.Node compNode = new TalendfileProcessType.Node();
			compNode.setComponentName("tPostjob");
			compNode.setComponentVersion(0.102);
			compNode.setOffsetLabelX(0);
			compNode.setOffsetLabelY(0);
			compNode.setPosX("192");
			compNode.setPosY("400");
			
			ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT", "UNIQUE_NAME", "tPostjob1", "false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT", "CONNECTION_FORMAT", "row"));
			
			compNode.getElementParameter().addAll(elemParam);
			pipeline.getNode().add(compNode);
			
			
			TalendfileProcessType.Subjob subjob = new TalendfileProcessType.Subjob();
			Subjob.ElementParameter subjobElemParam1 = new Subjob.ElementParameter();
			subjobElemParam1.setfield("CHECK"); subjobElemParam1.setname("SHOW_SUBJOB_TITLE"); subjobElemParam1.setvalue("true"); subjobElemParam1.setshow("false");
			Subjob.ElementParameter subjobElemParam2 = new Subjob.ElementParameter();
			subjobElemParam2.setfield("TEXT"); subjobElemParam2.setname("UNIQUE_NAME"); subjobElemParam2.setvalue("tPostjob_"+processFlow.getDescription()); subjobElemParam2.setshow("false");
			Subjob.ElementParameter subjobElemParam3 = new Subjob.ElementParameter();
			subjobElemParam3.setfield("COLOR"); subjobElemParam3.setname("SUBJOB_TITLE_COLOR"); subjobElemParam3.setvalue("230;100;0");
			Subjob.ElementParameter subjobElemParam4 = new Subjob.ElementParameter();
			subjobElemParam4.setfield("COLOR"); subjobElemParam4.setname("SUBJOB_COLOR"); subjobElemParam4.setvalue("255;220;180");
			
			/*
			elemParam1.add(Utils.getPropDtlsWithShwforExp("TEXT", "UNIQUE_NAME", "tPostjob_"+processFlow.getDescription(), "false"));
			elemParam1.add(Utils.getPropDtlsforExp("COLOR", "SUBJOB_TITLE_COLOR", "230;100;0"));
			elemParam1.add(Utils.getPropDtlsforExp("COLOR", "SUBJOB_COLOR", "255;220;180"));*/
			subjob.getElementParameter().add(subjobElemParam1);
			subjob.getElementParameter().add(subjobElemParam2);
			subjob.getElementParameter().add(subjobElemParam3);
			subjob.getElementParameter().add(subjobElemParam4);
			pipeline.setSubjob(subjob);
			
			
			if(processFlow.getSteps().getStep().get(0).getName().equalsIgnoreCase("ExecSH")){
				
				TalendfileProcessType.Node node = new TalendfileProcessType.Node();
				node.setComponentName("tSSH");
				node.setComponentVersion(0.102);
				node.setOffsetLabelX(0);
				node.setOffsetLabelY(0);
				node.setPosX("416");
				node.setPosY("400");
				
				String command = processFlow.getSteps().getStep().get(0).getOtherTask().getParameter().get(0).getValue();
				
				command = Utils.getParamValWithContext(command, docspecs, cntxtParamsList);
				
				String sshNodeName = processFlow.getSteps().getStep().get(0).getName();
				
				ArrayList<ElementParameter> elemParam2 = new ArrayList<ElementParameter>();
				elemParam2.add(Utils.getPropDtlsWithShwforExp("TEXT", "UNIQUE_NAME", sshNodeName, "false"));
				elemParam2.add(Utils.getPropDtlsforExp("TEXT", "HOST", "\"ssh_server\""));
				elemParam2.add(Utils.getPropDtlsforExp("TEXT", "PORT", "22"));
				elemParam2.add(Utils.getPropDtlsforExp("TEXT", "USER", "\"USERNAME\""));
				elemParam2.add(Utils.getPropDtlsforExp("CLOSED_LIST", "AUTH_METHOD", "Password"));
				elemParam2.add(Utils.getPropDtlsforExp("PASSWORD", "PASSWORD", "wiXuUBxiyoc5L4zXsOuoEQ=="));
				elemParam2.add(Utils.getPropDtlsforExp("CHECK", "PTY", "false"));
				elemParam2.add(Utils.getPropDtlsforExp("CLOSED_LIST", "STANDARDOUTPUT", "TO_CONSOLE"));
				elemParam2.add(Utils.getPropDtlsforExp("CLOSED_LIST", "ERROROUTPUT", "TO_CONSOLE"));
				elemParam2.add(Utils.getPropDtlsWithShwforExp("FILE", "PRIVATEKEY", "\"/.ssh/id_dsa\"", "false"));
				elemParam2.add(Utils.getPropDtlsWithShwforExp("PASSWORD", "PASSPHRASE", "wiXuUBxiyoc5L4zXsOuoEQ==", "false"));
				elemParam2.add(Utils.getPropDtlsforExp("TEXT", "COMMANDSEPARATOR", "\";\""));
				
				ElementParameter elemParam3 = new ElementParameter();
				elemParam3.setField("TABLE");
				elemParam3.setName("COMMANDS");
				ElementParameter.ElementValue elemValue = new ElementParameter.ElementValue();
				elemValue.setElementRef("COMMAND");
				elemValue.setValue(command);
				elemParam3.getElementValue().add(elemValue);
				
				ArrayList<ElementParameter> elemParam4 = new ArrayList<ElementParameter>();
				elemParam4.add(Utils.getPropDtlsforExp("CHECK", "USE_TIMEOUT", "false"));
				elemParam4.add(Utils.getPropDtlsWithShwforExp("TEXT", "TIMEOUT", "60", "false"));
				elemParam4.add(Utils.getPropDtlsforExp("TEXT", "CONNECTION_FORMAT", "row"));
				
				node.getElementParameter().addAll(elemParam2);
				node.getElementParameter().add(elemParam3);
				node.getElementParameter().addAll(elemParam4);
				pipeline.getNode().add(node);
				
				
				TalendfileProcessType.Connection connection = new TalendfileProcessType.Connection();
				connection.setconnectorName("COMPONENT_OK");
				connection.setlabel("OnComponentOk");
				connection.setlineStyle("3");
				connection.setmetaname("tPostjob1");
				connection.setoffsetLabelX("0");
				connection.setoffsetLabelY("0");
				connection.setsource("tPostjob1");
				connection.settarget(sshNodeName);
				TalendfileProcessType.Connection.ElementParameter elemParam5 = new TalendfileProcessType.Connection.ElementParameter();
				elemParam5.setfield("TEXT");
				elemParam5.setName("UNIQUE_NAME");
				elemParam5.setvalue("OnComponentOk1");
				elemParam5.setshow("false");
				connection.getElementParameter().add(elemParam5);
				pipeline.getConnection().add(connection);
				
			}
			
			
		}
		
	}
	
}