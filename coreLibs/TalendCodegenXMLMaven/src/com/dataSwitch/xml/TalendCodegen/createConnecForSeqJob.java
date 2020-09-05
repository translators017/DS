
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;

import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Connection;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Connection.ElementParameter;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow.Steps.Step.PreviousSteps;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;

public class createConnecForSeqJob {

	public static void createConnection(String compName, PreviousSteps prevSteps, TalendfileProcessType pipeline, HashMap<String, SeqJobHelper> stepMap) {
		
		for(int i = 0 ; i<prevSteps.getPreviousStep().size(); i++)
		{
			
			String source = prevSteps.getPreviousStep().get(i).getStepRef();
			String target = compName;
			
			SeqJobHelper seqHelperObj = stepMap.get(source);
			
			Connection conn = new Connection();
			conn.setmetaname(source);
			conn.setoffsetLabelX("0");
			conn.setoffsetLabelY("0");
			conn.setsource(source);
			conn.settarget(target);
			
			if(seqHelperObj!=null){
				
				if(seqHelperObj.hasCondition.equalsIgnoreCase("yes"))
				{
					
					String condition = seqHelperObj.filterCondition;
					condition = FunctionTypeConv.getCurrentFunctions(condition,ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND);
					
					conn.setconnectorName("RUN_IF");
					conn.setlabel("If");
					conn.setlineStyle("6");
					
					ElementParameter elemParam = new ElementParameter();
					elemParam.setfield("MEMO_JAVA"); elemParam.setName("CONDITION"); elemParam.setvalue(condition);
					ElementParameter elemParam2 = new ElementParameter();
					elemParam2.setfield("TEXT"); elemParam2.setName("UNIQUE_NAME"); elemParam2.setvalue("If"+i); elemParam2.setshow("false");
					
					conn.getElementParameter().add(elemParam);
					conn.getElementParameter().add(elemParam2);
				}
				
				else if(seqHelperObj.stepType.equalsIgnoreCase("loop"))
				{
					conn.setconnectorName("ITERATE");
					conn.setlabel("Iterate");
					conn.setlineStyle("7");
					
					ElementParameter elemParam = new ElementParameter();
					elemParam.setfield("CHECK"); elemParam.setName("ENABLE_PARALLEL"); elemParam.setvalue("false");
					ElementParameter elemParam2 = new ElementParameter();
					elemParam2.setfield("TEXT"); elemParam2.setName("NUMBER_PARALLEL"); elemParam2.setvalue("2"); elemParam2.setshow("false");
					ElementParameter elemParam3 = new ElementParameter();
					elemParam3.setfield("TEXT"); elemParam3.setName("UNIQUE_NAME"); elemParam3.setvalue("iterate"+i); elemParam3.setshow("false");
					
					conn.getElementParameter().add(elemParam);
					conn.getElementParameter().add(elemParam2);
					conn.getElementParameter().add(elemParam3);
				}
				
				else if(seqHelperObj.stepType.equalsIgnoreCase("parallelize"))
				{
					conn.setconnectorName("PARALLELIZE");
					conn.setlabel("Parallelize");
					conn.setlineStyle("13");
					
					ElementParameter elemParam = new ElementParameter();
					elemParam.setfield("TEXT"); elemParam.setName("UNIQUE_NAME"); elemParam.setvalue("Parallelize"+i); elemParam.setshow("false");
					
					conn.getElementParameter().add(elemParam);
				}
				
				else
				{
					conn.setconnectorName("FLOW");
					conn.setlabel("rw_"+source);
					conn.setlineStyle("0");
					
					ElementParameter elemParam = new ElementParameter();
					elemParam.setfield("CHECK"); elemParam.setName("MONITOR_CONNECTION"); elemParam.setvalue("false");
					ElementParameter elemParam2 = new ElementParameter();
					elemParam2.setfield("TEXT"); elemParam2.setName("UNIQUE_NAME"); elemParam2.setvalue("rw_"+source); elemParam2.setshow("false");
					
					conn.getElementParameter().add(elemParam);
					conn.getElementParameter().add(elemParam2);
				}
			}
			
			
			
			if(seqHelperObj!=null){
				pipeline.getConnection().add(conn);
			}
			
			
		}
		
	}

}