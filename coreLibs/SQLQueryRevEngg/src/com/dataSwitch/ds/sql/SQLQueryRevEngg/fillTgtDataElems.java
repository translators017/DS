
package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLRevEnggDTO.sqlDTO;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.TargetDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;
import com.dataSwitch.xml.connectorspecs.SourceDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;

public class fillTgtDataElems {

	public static void processTgtDataElems(TargetDataElements tgtDataElems, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, ArrayList<SQLObject> colsList, LinkedHashMap<String, SystemEntity> sysEntityMap) 
	{
		LinkedHashMap<String, sqlDTO> tgtElems = new LinkedHashMap<String, sqlDTO>();
		ArrayList<sqlDTO> srcElems = new ArrayList<sqlDTO>();
		
		for(Entry<String, sqlDTO> tgtElemnsMap : tgtDataElemnsMap.entrySet())
		{
			String keyVal = tgtElemnsMap.getKey();
			if(tgtElemnsMap.getValue().transType.equalsIgnoreCase("Target"))
			{
				getUpdatedSQLDtoForTgt(tgtElemnsMap.getValue(), sysEntityMap);
				tgtElems.put(keyVal, tgtElemnsMap.getValue());
			}
			else
			{
				srcElems.add(tgtElemnsMap.getValue());
			}
		}
		
		fillTgtDataElemsFromTgt(tgtElems, tgtDataElems,srcElems, colsList);
		
	}


	private static void fillTgtDataElemsFromTgt(LinkedHashMap<String, sqlDTO> tgtElems, TargetDataElements tgtDataElems,
			ArrayList<sqlDTO> srcElems, ArrayList<SQLObject> colsList) 
	{
		for(Entry<String, sqlDTO> sqlDtoMap : tgtElems.entrySet())
		{
			if(sqlDtoMap.getValue().sqlElems.size() > 0)
			{
				if(colsList.size() == sqlDtoMap.getValue().sqlElems.size())
				{
					for(int i = 0 ; i < sqlDtoMap.getValue().sqlElems.size(); i++)
					{
						SQLElement sqlElem = new SQLElement();
						
						if(sqlDtoMap.getValue().sqlElems.get(i) instanceof SQLElement)
							sqlElem = (SQLElement) sqlDtoMap.getValue().sqlElems.get(i);
						
						TargetDataElementType tgtElem = new TargetDataElementType();
						tgtElem.setTargetDatsetRef(sqlDtoMap.getValue().compName);
						tgtElem.setTargetDataElementRef(sqlElem.name);
						tgtDataElems.getTargetDataElement().add(tgtElem);
						
						String srcComp = "";
						String srcDataRefs = getSrcDataRefs(colsList, srcElems, srcComp, i);
				
						if(srcDataRefs != null && srcDataRefs.length() > 0)
						{
					        String compName = srcDataRefs.substring(0,srcDataRefs.indexOf("."));
					        String srcColName = srcDataRefs.substring(srcDataRefs.indexOf(".")+1, srcDataRefs.length());
					        SourceDataElementType srcElemTyp = new SourceDataElementType();
							srcElemTyp.setSourceFeedRef(compName);
							srcElemTyp.setSourceDataElementRef(srcColName);
							tgtElem.setDirectSourceDataElementRef(srcElemTyp);
					        
						}
						
					}
				}
			}
		}
		
	}

	private static String getSrcDataRefs(ArrayList<SQLObject> colsList, ArrayList<sqlDTO> srcElems, String srcCompDtl, int i) 
	{
		for(int j = 0 ; j < colsList.size() ; j++)
		{
			if(i == j)
			{
				SQLElement sqlElem = new SQLElement();
				
				if(colsList.get(j) instanceof SQLElement)
					sqlElem = (SQLElement) colsList.get(j) ;
				
				for(int j1 = 0 ;j1 < srcElems.size(); j1++)
				{
					sqlDTO sqlDTO = srcElems.get(j1);
					
					if(sqlDTO.subSQLType == null)
					{
						if(sqlDTO.transType.equalsIgnoreCase("Expression") ||
								sqlDTO.transType.equalsIgnoreCase("Aggregator") || sqlDTO.transType.equalsIgnoreCase("MainSource"))
						{
							if(sqlElem.aliasName != null)
							{
								for(int  k = 0 ;k < sqlDTO.sqlElems.size() ; k++)
								{
									SQLElement sqlElemSrc = new SQLElement();
									
									if(sqlDTO.sqlElems.get(k) instanceof SQLElement)
										sqlElemSrc = (SQLElement) sqlDTO.sqlElems.get(k) ;
									
									if(sqlElemSrc.aliasName != null)
									{
										if(sqlElem.aliasName.equalsIgnoreCase(sqlElemSrc.aliasName))
										{
											String srcComp = "";
											if(!sqlDTO.transType.equalsIgnoreCase("MainSource"))
											   srcComp = sqlDTO.compName+"."+sqlElemSrc.aliasName;
											else
											{
											   srcComp = sqlElemSrc.name.substring(0, sqlElemSrc.name.indexOf("."))+"."+sqlElemSrc.name.substring(sqlElemSrc.name.indexOf(".")+1);
											}
											srcCompDtl = srcComp;
										}
									}
								}
							}
						}
					
					} 
					else
					{
						ArrayList<Token> tkn = FunctionTypeConv.getTokens(sqlElem.name);
						String srcComp = processSubQuery.getSrcColsFromSubQuery(srcElems, tkn, srcCompDtl);
						srcCompDtl = srcComp;
					}
					
				}
			}
		}
		return srcCompDtl;
	}


	private static void getUpdatedSQLDtoForTgt(sqlDTO value, LinkedHashMap<String, SystemEntity> sysEntityMap)
	{
		if(value.sqlElems.size() == 0)
		{
			String orginalTblName = "";
			if(value.originalTableName.contains("."))
			{
			    orginalTblName = value.originalTableName.substring(value.originalTableName.lastIndexOf(".")+1,value.originalTableName.length()).toLowerCase();
			}
			else
				orginalTblName = value.originalTableName;
			
			if(sysEntityMap.get(orginalTblName.toLowerCase()) != null)
			{
				SystemEntity sysEnty = sysEntityMap.get(orginalTblName.toLowerCase());
				getUpdatedSqlDtoForTgt(sysEnty,value.sqlElems);
			}
			
		}
	}

	private static void getUpdatedSqlDtoForTgt(SystemEntity sysEnty, ArrayList<SQLObject> sqlElems) 
	{
		for(int i = 0 ; i < sysEnty.getRelationalDataElements().getDataElement().size() ;i++)
		{
			DataElement elem = sysEnty.getRelationalDataElements().getDataElement().get(i);
			SQLElement sqlElem = new SQLElement();
			sqlElem.name = elem.getName();
			sqlElems.add(sqlElem);
		}
		
	}

}