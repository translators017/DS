
package com.dataSwitch.xml.TalendCodegen;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.talend.context.TalendfileContextType;
//import com.dataSwitch.xml.DScontrol.ControlMaster;
import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;
import com.dataSwitch.xml.ETLMapping.Datamapping;
//import com.dataSwitch.Talend.bean.ObjectFactory;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.TalendConfigurations.Talendconfig;
import com.dataSwitch.xml.TalendProp.XmiXMI;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataMappingVariables.DataMappingVariable;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow;

	public class CreateTalendCodegen 
	{

		static String datadir = System.getProperty("user.dir");
		private static String szTalendInputXMLFilePath;
		private static String szoutputFilePath;
		private static String szoutputLinkedServiceFilePath;
		private static  Connector Docspecs;
		public static  Talendconfig Config;	
		private  static ClassLoader ncl;
		private  static JAXBContext njc;
		private  static Unmarshaller nu;
		private  static File nf;
		static String configName = "Dev";
		
		public CreateTalendCodegen( String inputFile,String outputLinkedServiceFilePath)
		{
			szTalendInputXMLFilePath= inputFile;
			szoutputLinkedServiceFilePath = outputLinkedServiceFilePath;		
		}

		public static void main(String args[])
		{ 
			try
			{			
				String szConnectorXMLPath = datadir+"/data/";
				String szTalendXMLFileName = "claim.xml";
				String inputXml = szConnectorXMLPath + szTalendXMLFileName;
				String szoutputLinkedServiceFilePath=datadir+"/output/";
				Console console = new Console();
				CreateTalendCodegen createTalendJobscript =  new CreateTalendCodegen(inputXml, szoutputLinkedServiceFilePath);
				createTalendJobscript.TalendcodeGenStarts(console);
				File source = new File(datadir+"/TalendProject/"+"/"+"talend.project");
				String folderName = "OutputCode";
				Paths.get(szoutputLinkedServiceFilePath,folderName).toFile().mkdir();
				File  dest = new File(szoutputLinkedServiceFilePath,folderName+File.separator+"talend.project");
			    copyFileUsingJava7Files(source, dest);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		private static void copyFileUsingJava7Files(File source, File dest) throws IOException 
		{
			
			if(!dest.exists())
			{
		       Files.copy(source.toPath(), dest.toPath());
			}
		}
		public void   TalendcodeGenStarts(Console console)
		{
			try
			{
				Docspecs = unmarshallConnectorXml(szTalendInputXMLFilePath,console);
				LogHelper logHelperObj = new LogHelper();
				generateTalendCode( Docspecs,console,szoutputLinkedServiceFilePath,logHelperObj);
				/*String logOutput = szoutputLinkedServiceFilePath+"_log.txt";  
	            getAuditDetails.getAuditDetail(Docspecs,logOutput,console,logHelperObj);
	            createReportLog.mergeFiles(szoutputLinkedServiceFilePath);	*/
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//Unmarshalling the connectorspecs(as of now not required)
		private final static Connector unmarshallConnectorXml(String szDSInputXMLFilePath,Console console) throws Exception 
		{
			ncl = com.dataSwitch.xml.connectorspecs.ObjectFactory.class.getClassLoader();
			njc = JAXBContext.newInstance("com.dataSwitch.xml.connectorspecs", ncl);
			nu = (Unmarshaller) njc.createUnmarshaller();
			if( szDSInputXMLFilePath != null) 
			{
				nf = new File(szDSInputXMLFilePath);
			}
			else
			{
				throw new Exception("Valid xml name should be passed (along with path)");
			}
			Connector connector = (Connector)((javax.xml.bind.Unmarshaller) nu).unmarshal(nf);
			String version = connector.getSchemaVersion();
			System.out.println("version --"+ version);
			if (!(version.equals("3.0.0.12")))
			{ 
				System.out.println("ConnectorSpecs version is invalid...");
				console.put("Message", "ConnectorSpecs version is invalid...<br>");
			}
			return connector;
		}

		
		//unmarshalling the TalendProperty Config file	
		public static Talendconfig unmarshallTalendConfig(String szTalendConfiguration) throws Exception 
		{
			ncl = com.dataSwitch.xml.TalendConfigurations.ObjectFactory.class.getClassLoader();
			njc = JAXBContext.newInstance("com.dataSwitch.xml.TalendConfigurations", ncl);
			nu = (Unmarshaller) njc.createUnmarshaller();
			if( szTalendConfiguration != null) {
				nf = new File(szTalendConfiguration);
			}
			else
			{
				throw new Exception("Valid xml name should be passed (along with path)");
			}
			Talendconfig Config = (Talendconfig) ((javax.xml.bind.Unmarshaller) nu).unmarshal(nf);
			return Config;
		}

		public static  void generateTalendCode (Connector Docspecs , Console console,String szoutputLinkedServiceFilePath, LogHelper logHelperObj) throws Exception
		
		{   
			
			int countPropPipeline = 10;
			for (int i = 0; i < Docspecs.getDataMappings().getDataMapping().size(); i++)
			{	
				DataMapping dataMappingObj = Docspecs.getDataMappings().getDataMapping().get(i);
				
				ReportLogInfo reportObj = new ReportLogInfo();
				com.dataSwitch.xml.ETLMapping.Utils.fillReportLog(dataMappingObj, reportObj);
				
				ArrayList<TransRecDTO> recsList = new ArrayList<TransRecDTO>();
			    recsList = Datamapping.getMappingRecords(Docspecs, dataMappingObj ,true,true,ToolTypeConstants.TALEND);
			    
			    boolean checkCDChasInsrtDelete = createCDCStage.checkCDChasInsrtDelete(recsList);
                if(checkCDChasInsrtDelete == true){
                	createCDCStage.createPipelineForTgtStage(recsList);
                }
                
			    Utils.updateAndResolveFieldNameConflids(recsList);
			    Utils.checkIgnoreCodegenForExp(recsList);
			    recsList = Utils.removeDuplicateOutrecsFromRouter(recsList);
			    recsList = Utils.processChecksumStage(recsList);
			    String version = Datamapping.getVersion();
				System.out.println("version --"+version);
				if (!(version.equals("1.0.0.8")))
				{ 
					System.out.println("ETLMapping version is invalid...");
					console.put("Message", "ETLMapping version is invalid...<br>");
				}
				Utils.updateXpositionForStages(recsList);
				Utils.updateYpositionForPrevStages(recsList);
				Utils.updateYpositionForNextStages(recsList);
				String dataMapName = dataMappingObj.getName();
				if(dataMapName.contains("."))
				{
					dataMapName = dataMapName.substring(0,dataMapName.lastIndexOf("."));
				}
				TalendfileProcessType pipeline = new TalendfileProcessType();
				XmiXMI xmlProp = new XmiXMI();
				//WriteTalendJobJson(recsList,Docspecs ,pipeline,xmlProp,console,dataMapName, szoutputLinkedServiceFilePath,dataMappingObj,logHelperObj);
				WriteTalendJobJson(recsList,Docspecs ,pipeline,xmlProp,console,dataMapName, szoutputLinkedServiceFilePath,dataMappingObj,logHelperObj,reportObj,countPropPipeline++,checkCDChasInsrtDelete);
				marshalConnectorXML(pipeline,szoutputFilePath,Docspecs,szoutputLinkedServiceFilePath,dataMapName);
				marshalConnectorTalendPropXML(xmlProp,szoutputFilePath,Docspecs,szoutputLinkedServiceFilePath,dataMapName);
				
				com.dataSwitch.xml.ETLMapping.Utils.printReportLog(reportObj, Docspecs, szoutputLinkedServiceFilePath, console);
			}	
			
			createContextSet.fillParaSetDtls(Docspecs);
			
			HashMap<String,List<DataMappingVariable>> dataMapVar = createContextSet.getParamsetNames(Docspecs);
			int countContext = 10;
			int countPropContext = 10;
			for (Entry<String, List<DataMappingVariable>> entry : dataMapVar.entrySet())
			{
				String paramKey = entry.getKey();
				List<DataMappingVariable> paramValue = entry.getValue();
				TalendfileContextType cntxtFile = new TalendfileContextType();
				XmiXMI xmlProp = new XmiXMI();
				createContextSet.fillCntxtProps(Docspecs,xmlProp,paramKey,countContext++);
				createContextSet.FillContextFile(cntxtFile,paramValue,paramKey,countPropContext++);
				marshalConnectorTalendContextPropXML(xmlProp,Docspecs,szoutputLinkedServiceFilePath,paramKey);
				marshalConnectorTalendContextItemXML(cntxtFile,Docspecs,szoutputLinkedServiceFilePath,paramKey);
			}
			
			
			//sequence job starts
			int countSeq = 10;
			int countPropSeq = 10;
			
			if(Docspecs.getProcessFlows()!=null){
				for(int i = 0; i< Docspecs.getProcessFlows().getProcessFlow().size(); i++){
					
					ProcessFlow processFlow = Docspecs.getProcessFlows().getProcessFlow().get(i);
					boolean checkSeqHasSteps = CreateSeqJob.checkSeqHasSteps(processFlow);
					if(checkSeqHasSteps == false)
					{
						String processName = processFlow.getName()+"_Seq_"+i;
						TalendfileProcessType pipeline = new TalendfileProcessType();
						XmiXMI xmlProp = new XmiXMI();
						CreateSeqJob.fillSeqCompDetails(processFlow,pipeline,szoutputLinkedServiceFilePath,countSeq++);
						CreateSeqJob.fillSeqPropDetails(xmlProp,processFlow, processName,countPropSeq++);
						marshalTalendSeqPropXML(xmlProp,processName,Docspecs,szoutputLinkedServiceFilePath);
						marshalTalendSeqtItemXML(pipeline,processName,Docspecs,szoutputLinkedServiceFilePath);
					}
				}
			}
			
		}
		
		public static void marshalTalendSeqtItemXML(TalendfileProcessType pipeline, String processName,
				Connector docspecs, String szoutputLinkedServiceFilePath) {
			
			Marshaller jaxbMarshaller = null;
			try{

				JAXBContext njc = JAXBContext.newInstance(TalendfileProcessType.class);
				jaxbMarshaller = njc.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); 
				
			}
			catch(JAXBException e)
			{
				e.printStackTrace();
			}
			
			
			try {
				
				    String folderName = "OutputCode";
				    //String folderName = conSpecs.getName();
				    //System.out.println("folder name=======>"+folderName);
				    Paths.get(szoutputLinkedServiceFilePath, folderName).toFile().mkdir();
				
			    
				    String PipelineName  = processName;
					File file = new File(szoutputLinkedServiceFilePath,folderName+File.separator+PipelineName+".item"); 
			        jaxbMarshaller.marshal(pipeline, file);
			        
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

		public static void marshalTalendSeqPropXML(XmiXMI xmlProp, String processName, Connector docspecs,
				String szoutputLinkedServiceFilePath) {
			
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
				  String folderName = "OutputCode";
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

		public static  void WriteTalendJobJson (List<TransRecDTO> pipewiseRecsList, Connector Docspecs,TalendfileProcessType pipeline, XmiXMI xmlProp,
				Console console,String dataMapName,String szoutputLinkedServiceFilePath,DataMapping dataMappingObj, LogHelper logHelperObj, ReportLogInfo reportObj, int countPipeline, boolean checkCDChasInsrtDelete) throws Exception
		{
			Utils.fillPipelinerecords(Docspecs,pipeline, pipewiseRecsList,szoutputLinkedServiceFilePath,dataMappingObj,logHelperObj,reportObj,checkCDChasInsrtDelete);
			Utils.fillPipelineProps(Docspecs,xmlProp, pipewiseRecsList,dataMapName,countPipeline);
		}	
		
		//unmarshalling the connectorLogic Xml
		public static Connector unmarshallConnectorLogicXml(String connectorLogicXMLPath) throws Exception {

			ncl = com.dataSwitch.xml.connectorspecs.ObjectFactory.class.getClassLoader();
			njc = JAXBContext.newInstance("com.dataSwitch.xml.connectorspecs", ncl);
			nu = njc.createUnmarshaller();
			if (connectorLogicXMLPath != null) {
				nf = new File(connectorLogicXMLPath);
			} else {
				throw new Exception("Valid xml name should be passed (along with path )");
			}

			Connector Docspecs = (Connector) nu.unmarshal(nf);

			return Docspecs;
		}

		//marshalling the Talend Json
		private static void marshalConnectorXML(TalendfileProcessType TalendCodeGenStarts, String szoutputFilePath,Connector conSpecs,String szoutputLinkedServiceFilePath,String dataMapName )throws JAXBException, XMLStreamException, FileNotFoundException 
		{

			Marshaller jaxbMarshaller = null;
			try{

				JAXBContext njc = JAXBContext.newInstance(TalendfileProcessType.class);
				jaxbMarshaller = njc.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); 
				
			}
			catch(JAXBException e)
			{
				e.printStackTrace();
			}
			
			
			try {
				
				    String folderName = "OutputCode";
				    //String folderName = conSpecs.getName();
				    //System.out.println("folder name=======>"+folderName);
				    Paths.get(szoutputLinkedServiceFilePath, folderName).toFile().mkdir();
				
			    
				    String PipelineName  = dataMapName;
					File file = new File(szoutputLinkedServiceFilePath,folderName+File.separator+PipelineName+".item"); 
			        jaxbMarshaller.marshal(TalendCodeGenStarts, file);
			        
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
		
		private static void marshalConnectorTalendPropXML(XmiXMI TalendCodeGenForProp, String szoutputFilePath,Connector conSpecs,String szoutputLinkedServiceFilePath,String dataMapName)throws JAXBException, XMLStreamException, FileNotFoundException 
		{

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
				  String folderName = "OutputCode";
				   Paths.get(szoutputLinkedServiceFilePath,
				              folderName).toFile().mkdir();
			
				   String PipelineName  = dataMapName;
				File file = new File(szoutputLinkedServiceFilePath,
		              folderName+File.separator+PipelineName+".properties");
			
		        
		        jaxbMarshaller.marshal(TalendCodeGenForProp, file);
		  
				
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
		
		private static void marshalConnectorTalendContextPropXML(XmiXMI TalendCodeGenForProp, Connector conSpecs,String szoutputLinkedServiceFilePath,String cntxtGrpName)throws JAXBException, XMLStreamException, FileNotFoundException 
		{

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
				  String folderName = "OutputCode";
				   Paths.get(szoutputLinkedServiceFilePath,
				              folderName).toFile().mkdir();
			
				   String PipelineName  = cntxtGrpName;
				File file = new File(szoutputLinkedServiceFilePath,
		              folderName+File.separator+PipelineName+".properties");
			
		        
		        jaxbMarshaller.marshal(TalendCodeGenForProp, file);
		  
				
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
		
		private static void marshalConnectorTalendContextItemXML(TalendfileContextType TalendContextForProp, Connector conSpecs,String szoutputLinkedServiceFilePath,String cntxtGrpName)throws JAXBException, XMLStreamException, FileNotFoundException 
		{

			Marshaller jaxbMarshaller = null;
			try{

				JAXBContext njc = JAXBContext.newInstance(TalendfileContextType.class);
				jaxbMarshaller = njc.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);   
				
			}
			catch(JAXBException e)
			{
				e.printStackTrace();
			}
			
			
			try {
				  String folderName = "OutputCode";
				   Paths.get(szoutputLinkedServiceFilePath,
				              folderName).toFile().mkdir();
			
				   String PipelineName  = cntxtGrpName;
				File file = new File(szoutputLinkedServiceFilePath,
		              folderName+File.separator+PipelineName+".item");
			
		        
		        jaxbMarshaller.marshal(TalendContextForProp, file);
		  
				
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
		
	}

	
	
	
	
	
	
	
