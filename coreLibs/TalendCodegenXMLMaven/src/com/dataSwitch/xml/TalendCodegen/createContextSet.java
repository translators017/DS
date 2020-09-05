
package com.dataSwitch.xml.TalendCodegen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.talend.context.TalendfileContextType;
import com.dataSwitch.talend.context.TalendfileContextType.ContextParameter;
//import com.dataSwitch.xml.DScontrol.ControlMaster;
import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;
import com.dataSwitch.xml.ETLMapping.Datamapping;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.TalendConfigurations.Talendconfig;
import com.dataSwitch.xml.TalendProp.XmiXMI;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataMappingVariables.DataMappingVariable;

public class createContextSet {

	public static final void fillParaSetDtls(Connector docspecs)
	{
		HashMap<String,List<DataMappingVariable>> dataMapVar = getParamsetNames(docspecs);
	}
	
	public static final List<DataMappingVariable> getDataMapVarLists (Connector docspecs)
	{
		List<DataMappingVariable> parmtrsetlist = new ArrayList<DataMappingVariable>();
		for(int i = 0 ; i < docspecs.getDataMappings().getDataMapping().size();i++)
		{

			DataMapping dataMap = docspecs.getDataMappings().getDataMapping().get(i);
			if(dataMap.getDataMappingVariables() != null)
			{
				for(int j = 0 ; j < dataMap.getDataMappingVariables().getDataMappingVariable().size();j++)
				{
					DataMappingVariable dataMapVar = dataMap.getDataMappingVariables().getDataMappingVariable().get(j);
					if(dataMapVar.getVariableType()!=null && dataMapVar.getVariableType().equalsIgnoreCase("Datastage Mapping ParameterSet Variable"))
					{
						if(dataMapVar.getName().contains("."))
						{
							parmtrsetlist.add(dataMapVar);
						}
					}
				}
			}
		}
		return parmtrsetlist;
	}
	
	public static final HashMap<String,List<DataMappingVariable>> getParamsetNames(Connector docspecs)
	{
		HashMap<String,List<DataMappingVariable>> paramSetMap = new HashMap<String,List<DataMappingVariable>>();
		List<DataMappingVariable> DataMapVarLists = getDataMapVarLists(docspecs);
		for(int i = 0 ; i < docspecs.getDataMappings().getDataMapping().size();i++)
		{
			DataMapping dataMap = docspecs.getDataMappings().getDataMapping().get(i);
			if(dataMap.getDataMappingVariables() != null)
			{
				for(int j = 0 ; j < dataMap.getDataMappingVariables().getDataMappingVariable().size();j++)
				{
					DataMappingVariable dataMapVar = dataMap.getDataMappingVariables().getDataMappingVariable().get(j);
					if(dataMapVar.getVariableType()!=null && dataMapVar.getVariableType().equalsIgnoreCase("Datastage Mapping ParameterSet Variable"))
					{
						if(dataMapVar.getName().contains("."))
						{
							String paramSetName = dataMapVar.getName().substring(0,dataMapVar.getName().lastIndexOf('.'));
							paramSetMap.put(paramSetName, DataMapVarLists);
						}
					}
				}
			}
		}
		return paramSetMap;
	}
	
	public static void FillContextFile(TalendfileContextType pipeline,List<DataMappingVariable> parmtrsetlist,String paramKey, int countProp)
	{	
			int t = 100;
			pipeline.setName("Default");
			pipeline.setXmiId("_C2H"+countProp+"MPhEemeHZUMEnhBDQ");
			pipeline.setXmiVersion("2.0");
			pipeline.setXmlnsTalendfile("platform:/resource/org.talend.model/model/TalendFile.xsd");
			pipeline.setXmlnsXmi("http://www.omg.org/XMI");
			for(int j = 0 ; j < parmtrsetlist.size();j++)
			{
				DataMappingVariable dataMapVar = parmtrsetlist.get(j);
				String paramCheck = dataMapVar.getName().substring(0,dataMapVar.getName().lastIndexOf("."));
				if(paramCheck.equalsIgnoreCase(paramKey))
				{
					ContextParameter contxtpram = new ContextParameter();
					String name = dataMapVar.getName().replace("$", "").replace(".","_");
					//String name= dataMapVar.getName().substring(dataMapVar.getName().indexOf('.')+1,dataMapVar.getName().length()).replace("$", "");
					contxtpram.setXmiId("_C2H"+ t++ +"PhEemeHZUMEnhBDQ");
					contxtpram.setName(name);
					contxtpram.setComment("");
					contxtpram.setPrompt(name+"?");
					contxtpram.setPromptNeeded(false);
					DataTypeConvertor dtc = new DataTypeConvertor();
					String dataType = dataMapVar.getDatatype();
					dataType = dtc.getTargetDataType(dataType, ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND);
					contxtpram.setType(dataType);
					if(dataMapVar.getInitialValue() != null)
						contxtpram.setValue(dataMapVar.getInitialValue());
					else
						contxtpram.setValue("dummy value");
					pipeline.getContextParameter().add(contxtpram);
				}
			}
	}


	public static XmiXMI fillCntxtProps(Connector docspecs, XmiXMI pipelineprop, String paramKey, int count) 
	{
		String jobName = paramKey;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.ENGLISH);
        pipelineprop.setXmiVersion("2.0");
        pipelineprop.setXmlnsXmi("http://www.omg.org/XMI");
        pipelineprop.setXmlnsTalendProperties("http://www.talend.org/properties");
        XmiXMI.TalendPropertiesProperty tpropsprop = new XmiXMI.TalendPropertiesProperty();
        XmiXMI.TalendPropertiesProperty.Author author = new XmiXMI.TalendPropertiesProperty.Author();
        tpropsprop.setXmiId("_CL1KkS-QEemZ8oa6e"+count+"oEg");
        tpropsprop.setCreationDate(""+sdf.format(date));
        tpropsprop.setId("_CL1KkC-QEemZ8oa6e"+count+"oEg");
        tpropsprop.setLabel(jobName);
        tpropsprop.setModificationDate(""+sdf.format(date));
        tpropsprop.setVersion(0.1);
        tpropsprop.setItem("_CL1Kky-QEemZ8oa6e"+count+"oEg");
        tpropsprop.setDisplayName(jobName);
        author.setHref("../../../../../talend.project#_eYczcD8JEemQqtJiSuRJJw");
        tpropsprop.setAuthor(author);
        pipelineprop.setTalendPropertiesProperty(tpropsprop);
        XmiXMI.TalendPropertiesItemState tItemstate = new XmiXMI.TalendPropertiesItemState();
        tItemstate.setXmiId("_VyMHQD8UEemQqtJiSuRJJw");
        tItemstate.setPath("DS_Context");
        pipelineprop.setTalendPropertiesItemState(tItemstate);
        XmiXMI.TalendPropertiesContextItem cntxtItem = new XmiXMI.TalendPropertiesContextItem();
        cntxtItem.setXmiId("_CL1Kky-QEemZ8oa6e"+count+"oEg");
        cntxtItem.setProperty("_CL1KkS-QEemZ8oa6e"+count+"oEg");
        cntxtItem.setState("_VyMHQD8UEemQqtJiSuRJJw");
        XmiXMI.TalendPropertiesContextItem.Context cntxt = new XmiXMI.TalendPropertiesContextItem.Context();
        cntxt.setHref(jobName+".item#"+"_C2H"+count+"MPhEemeHZUMEnhBDQ");
        cntxtItem.setProcess(cntxt);
        pipelineprop.setTalendPropertiesContextItem(cntxtItem);   
        return pipelineprop;  	
	}
}