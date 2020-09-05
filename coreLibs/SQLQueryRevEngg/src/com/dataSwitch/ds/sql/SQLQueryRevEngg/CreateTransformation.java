
package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoin;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoinCond;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLRevEnggDTO.sqlDTO;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.ConnectorLogicDocumentation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset.TargetDataLoadOptions;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.TargetDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;

public class CreateTransformation 
{
	public static void fillConnHeaderDtls(String tgtSQLSrciptFileName, Connector conSpecs) 
	{
		conSpecs.setName(tgtSQLSrciptFileName);
		conSpecs.setDescription("SQLQuery To ETLCodeConnXml");
		conSpecs.setConnectorVersion(new BigDecimal("3"));
		ConnectorLogicDocumentation connectorLogic = new ConnectorLogicDocumentation();
		connectorLogic.setConnectorDefinition("3.0");
		conSpecs.setConnectorLogicDocumentation(connectorLogic);
	}


	public static boolean fillConnXmlDtls(SQLQuery sqlObjt, int currentDbId, Connector conSpecs, DataMapping dataMap,
			SystemCatalog sysCat, String jsonPath, ArrayList<String> uniqueCompNamesList, DataTransformations dataTrans, TargetDataElements tgtDataElems, String selectedCatalogNames, Console console) 
	{
		if(sqlObjt.dmlType != null)
		{
			if(sqlObjt.dmlType.equalsIgnoreCase("INSERT") || sqlObjt.dmlType.equalsIgnoreCase("UPDATE") 
					|| sqlObjt.dmlType.equalsIgnoreCase("DELETE"))
			{
				ArrayList<String> tables = new ArrayList<String>();
				LinkedHashMap<String, List<DataElement>> colsListMap  = new LinkedHashMap<String, List<DataElement>>();
				LinkedHashMap<String , sqlDTO> tgtDataElemnsMap = new LinkedHashMap<String , sqlDTO>();
   
				String getCatalogName = getCatalogueNameForMetadatas(jsonPath, sysCat, selectedCatalogNames);
				
				LinkedHashMap<String, SystemEntity> sysEntityMap = fillSysCatalog.prcsSystemCatalog(jsonPath, sysCat, selectedCatalogNames);
				processDataMappingDtls(sqlObjt, conSpecs, dataMap, currentDbId, tables, dataTrans, tgtDataElemnsMap,
						getCatalogName, uniqueCompNamesList, tgtDataElems, sysEntityMap);
				
				try {
					
					FillSystemCatalogs.processSysCatLog(sysCat ,tables, currentDbId, colsListMap, sysEntityMap);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//					if(tgtDataElemnsMap.size() > 0)
//					{
//						System.out.println(tgtDataElemnsMap);
//						fillTgtDataElems.processTgtDataElems(tgtDataElemnsMap);
//						//fillTargetDataElements.fillTgtElements(tgtDataElemnsMap, tgtDataElems, dataTrans, colsListMap);
//					}
				
				dataMap.setTargetDataElements(tgtDataElems);
				
				tgtDataElemnsMap.clear();
				colsListMap.clear();
				tables.clear();
			}
			else
			{
				console.put("Message","Code Conversion is not applicable for DDL Query");
				return true;
			}
		}
		
		return false;
		
		
	}
	
	private static String getCatalogueNameForMetadatas(String jsonPath, SystemCatalog sysCat, String selectedCatalogNames) 
	{
		ArrayList<Token> Tokens = FunctionTypeConv.getTokens(selectedCatalogNames);

		String cataLogName = "";
		
		for(int i = 0 ; i < Tokens.size() ; i++)
		{
            if(!Tokens.get(i).type.name().equalsIgnoreCase("STRCONSTANTS"))
            	continue;
            else
            {
            
			    JSONParser parser = new JSONParser();
				Object obj = null;
				FileReader fileRd = null;
				try 
				{
					String fileName = Tokens.get(i).data.substring(Tokens.get(i).data.indexOf("\"")+1, Tokens.get(i).data.length()-1);
					fileRd = new FileReader(jsonPath+fileName+".bin");
					obj = parser.parse(fileRd);
					JSONObject jsonObject = (JSONObject) obj;
					
					cataLogName = jsonObject.get("catalogName").toString();
				}
				
				catch(Exception ex) 
				{
					ex.printStackTrace();
				}
				try {
					fileRd.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
            }
		}
		return cataLogName;
	}


	private static void processDataMappingDtls(SQLQuery sqlObjt, Connector conSpecs, DataMapping dataMap, int currentDbId, ArrayList<String> tables, DataTransformations dataTrans, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap,
			String getCatalogName, ArrayList<String> uniqueCompNamesList, TargetDataElements tgtDataElems, LinkedHashMap<String, SystemEntity> sysEntityMap)
	{
		
		String tgtTable = "";
		
		SQLElement sqlElem = new SQLElement();
		if(sqlObjt.tableList.size() > 0)
		{
			if(sqlObjt.tableList.get(0) instanceof SQLElement)
				sqlElem = (SQLElement) sqlObjt.tableList.get(0);
			
			tgtTable = sqlElem.name;
		}
		
		String compName = Utils.getUpdatedCompName(tgtTable);
		compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
		
		LinkedHashMap<String, sqlDTO> sqlDtoList = new LinkedHashMap<String, sqlDTO>();
		
		String loadStartegy = null;
		if(sqlObjt.dmlType.equalsIgnoreCase("INSERT"))
		{
			loadStartegy = "insert";
			fillInsertDmlDetails(sqlObjt, tgtDataElemnsMap, compName, tgtTable);
			
			if(sqlObjt.colsList.size() > 0)
			{
				fillTransDtls(dataTrans, sqlObjt, tables, tgtDataElemnsMap, getCatalogName, uniqueCompNamesList, tgtDataElems, sqlDtoList, sysEntityMap, currentDbId);
			}
		}
		
		tables.add(tgtTable);
		
		if(tgtTable.length() > 0)
		{
			fillTgtTrans(dataTrans,tgtTable,sqlObjt, tgtDataElemnsMap, compName, getCatalogName, loadStartegy);
		}
		
		 getPrevCompForTgt(dataTrans, compName);
		
		dataMap.setDataTransformations(dataTrans);	 	
	}


	private static void fillInsertDmlDetails(SQLQuery sqlObjt, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap,
			String compName, String tgtTable)
	{
		sqlDTO sqlDto = new sqlDTO();
		sqlDto.originalTableName = tgtTable.trim();
		sqlDto.compName = compName;
		sqlDto.transType = "Target";
		
		SQLElement tgtElem = new SQLElement();
		if(sqlObjt.externalTables.size() > 0)
		{
			for(int i = 0 ; i < sqlObjt.externalTables.size() ; i++)
			{
				if(sqlObjt.externalTables.get(i) instanceof SQLElement)
					tgtElem = (SQLElement) sqlObjt.externalTables.get(i);
				sqlDto.sqlElems.add(tgtElem);
			}
		}
		
		tgtDataElemnsMap.put(compName, sqlDto);
		
	}


	private static void fillTransDtls(DataTransformations dataTrans, SQLQuery sqlObjt, ArrayList<String> tables, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, 
			String getCatalogName, ArrayList<String> uniqueCompNamesList, TargetDataElements tgtDataElems, LinkedHashMap<String, sqlDTO> sqlDtoList, LinkedHashMap<String, SystemEntity> sysEntityMap, int currentDbId)
	{
		for(int i = 0 ; i < sqlObjt.colsList.size() ; i++)
		{
			SQLQuery sqlQuery = new SQLQuery();
			
			if(sqlObjt.colsList.get(i) instanceof SQLQuery)
				sqlQuery = (SQLQuery) sqlObjt.colsList.get(i);
			
			if(sqlQuery.dmlType != null)
			{
				boolean checkSubQuery = false;
				
				boolean checkJoinerSubQuery = false;
				
				processSubQuery.fillTransFormationDtls(dataTrans,sqlQuery, tables, tgtDataElemnsMap, getCatalogName, uniqueCompNamesList, tgtDataElems, checkSubQuery, sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery);
				fillTgtDataElems.processTgtDataElems(tgtDataElems, tgtDataElemnsMap, sqlQuery.colsList, sysEntityMap);
			}
			
		}
		
	}

	private static void fillTgtTrans(DataTransformations dataTrans, String tgtTable, SQLQuery sqlObjt, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, String mappingName, String getCatalogName, String loadStartegy) 
	{
		
        DataTransformation dataTran = new DataTransformation();
        dataTran.setName(mappingName);
        dataTran.setTransformationType("Target Definition");
        TargetDataset tgtDataSet = new TargetDataset();
        tgtDataSet.setSystemCatalogRef(getCatalogName);
        String entityName = "";
        if(tgtTable.contains("."))
        	entityName = tgtTable.substring(tgtTable.lastIndexOf(".")+1, tgtTable.length());
        else
        	entityName = tgtTable;
        
        entityName = entityName.replace("$", "").replace("{", "").replace("}", "");
        
        tgtDataSet.setSystemEntityRef(entityName);
        tgtDataSet.setIncludePassThruTx(false);
        
        TargetDataLoadOptions tgtLdOptns = new TargetDataLoadOptions();
        tgtLdOptns.setLoadStrategy(loadStartegy);
        tgtDataSet.setTargetDataLoadOptions(tgtLdOptns);
        dataTran.setTargetDataset(tgtDataSet);
        dataTrans.getDataTransformation().add(dataTran);	
        
	}


	private static void getPrevCompForTgt(DataTransformations dataTrans, String tgtTable) 
	{
		
		if( dataTrans.getDataTransformation() != null)
		{
			for(int i = 0 ; i < dataTrans.getDataTransformation().size() ; i++)
			{
				DataTransformation dataTran = dataTrans.getDataTransformation().get(i);
				
				if(dataTran.getTransformationType() != null)
				{
					if(dataTran.getTransformationType().equalsIgnoreCase("Target Definition"))
					{
						String compName = dataTran.getName();
						
						if(tgtTable.equalsIgnoreCase(compName))
						{
							String prevCompName = dataTrans.getDataTransformation().get(i-1).getName();
							dataTran.setPrevComponentRef(prevCompName);
						}
					}
				}
			}
		}
	}
}