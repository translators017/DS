

package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLParser.SQLConverter;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.TargetDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;

public class MainSQLRevEngg
{
	public static void processSQLForETL(LinkedHashMap<Float, ArrayList<Token>> updatedMaps, int currentDbId, int newDbId, String tgtSQLSrciptFileName, String outpath,String selectedCatalogNames, String jsonPath, Console console)
	{
		Connector conSpecs = new Connector();
		CreateTransformation.fillConnHeaderDtls(tgtSQLSrciptFileName,conSpecs);
		ProcessFlows prcsFlows = new ProcessFlows();
		DataMappings dataMaps = new DataMappings();
		SystemCatalogs systemCatalogs = new SystemCatalogs();
		SystemCatalog sysCat = new SystemCatalog();
		DataMapping dataMap = new DataMapping();
		dataMap.setName(tgtSQLSrciptFileName);
		DataTransformations dataTrans = new DataTransformations();
		TargetDataElements tgtDataElems = new TargetDataElements();
		
		ArrayList<String> uniqueCompNamesList = new ArrayList<String>();
	
		int count = 0;
		boolean checkDDL = false;
		boolean checkQuery = false;
	
		if(!updatedMaps.isEmpty())
		{
			for(Entry<Float, ArrayList<Token>>  scrpts : updatedMaps.entrySet())
			{
				ArrayList<Token> Tokens = scrpts.getValue();
				
	            SQLQuery sqlObjt = SQLConverter.processScriptSQl(Tokens, currentDbId, newDbId);
	            if(CreateTransformation.fillConnXmlDtls(sqlObjt, currentDbId, conSpecs, dataMap, sysCat, jsonPath, uniqueCompNamesList, dataTrans, tgtDataElems, selectedCatalogNames, console))
	            	checkDDL = true;
	            
	            checkQuery = true;
	           
	            count++;
	            
			}
		}
		else
		{
			console.put("Message", "Conversion is not applicable for this input Script");
		}
	
		dataMaps.getDataMapping().add(dataMap);
		systemCatalogs.getSystemCatalog().add(sysCat);
		conSpecs.setSystemCatalogs(systemCatalogs);
		conSpecs.setDataMappings(dataMaps);
		conSpecs.setProcessFlows(prcsFlows);
		uniqueCompNamesList.clear();
	
	
		try {
			if(!(count == 1 && checkDDL))
			{
				if(checkQuery)
					marshallConnectorMethod(outpath,conSpecs,tgtSQLSrciptFileName);
				else
					console.put("Message", "Conversion is not applicable for the procedural language");
			}
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void marshallConnectorMethod(String specXmlFile,Connector conSpecs, String tgtSQLSrciptFileName) throws PropertyException {

        File outputFile = new File(specXmlFile+File.separator+tgtSQLSrciptFileName+"_connector.xml");

        try {

               System.out.println("specXmlFile=== " +specXmlFile);
               ClassLoader ncl = com.dataSwitch.xml.connectorspecs.ObjectFactory.class.getClassLoader();
               JAXBContext njc = JAXBContext.newInstance("com.dataSwitch.xml.connectorspecs",ncl);
               Marshaller marshaller = njc.createMarshaller();
               marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
               marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
               marshaller.setProperty( "jaxb.encoding", "US-ASCII" );
               marshaller.marshal(conSpecs, outputFile);
               System.out.println("outputFile::" + outputFile);
        }
        catch(Exception e) 
        {
               //logger.error(e);
               e.printStackTrace();
        }
	}


}