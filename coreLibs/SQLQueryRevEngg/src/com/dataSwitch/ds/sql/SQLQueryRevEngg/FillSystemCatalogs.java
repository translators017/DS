
package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemConnection;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;

public class FillSystemCatalogs 
{

	public static void processSysCatLog(SystemCatalog sysCat, ArrayList<String> tables, int currentDbId, LinkedHashMap<String, List<DataElement>> colsListMap, LinkedHashMap<String, SystemEntity> sysEntityMap) throws IOException 
	{
		SystemEntities sysEnties  = new SystemEntities();
		for(int i = 0 ;i < tables.size(); i ++) 
		{
			ArrayList<DataElement> columns = new ArrayList<DataElement>();
			
			String tableName = "";
			String SchemaName = "";
			if(tables.get(i).contains("."))
			{
				tableName = tables.get(i).substring(tables.get(i).lastIndexOf(".")+1, tables.get(i).length());
				SchemaName = tables.get(i).substring(0, tables.get(i).lastIndexOf("."));
			}
			else
				tableName = tables.get(i);
			
			tableName = tableName.replace("$", "").replace("{", "").replace("}", "");
			
			fillSysCatalogFromEntity(tableName.toLowerCase(), sysCat, sysEnties, tables.get(i), sysEntityMap, SchemaName);
			//Metadata.getEntityFromJson(tableName, currentDbId, sysEnties,jsonPath,columns, colsListMap, sysCat, sysConn);
		}
		
		sysCat.setSystemEntities(sysEnties);	
	}

	private static void fillSysCatalogFromEntity(String tableName, SystemCatalog sysCat, SystemEntities sysEnties,
			String originalTableName, LinkedHashMap<String, SystemEntity> sysEntityMap, String schemaName)
	{
		for(Entry<String, SystemEntity> entry : sysEntityMap.entrySet())
		{
			if(entry.getKey().equalsIgnoreCase(tableName))
			{
				SystemEntity sysEntity = entry.getValue();
				if(sysEntity.getPhysicalProperties() != null)
				{
				    if(sysEntity.getPhysicalProperties().getRelationalDatabaseProperties() != null)
				    {
				    	if(sysEntity.getPhysicalProperties().getRelationalDatabaseProperties().getSchemaName() != null)
				    	{
				    		sysEntity.getPhysicalProperties().getRelationalDatabaseProperties().setSchemaName(schemaName);
				    	}
				    }
				}
				if(!sysEnties.getSystemEntity().contains(sysEntity))
					sysEnties.getSystemEntity().add(sysEntity);
			}
		}
		
	}

}