
package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemConnection;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.PhysicalProperties;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.PhysicalProperties.RelationalDatabaseProperties;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;

public class Metadata 
{

	public static void getEntityFromJson(String tblName, int currentDbId, SystemEntities sysEnties, String jsonPath, ArrayList<DataElement> columns, LinkedHashMap<String, List<DataElement>> colsListMap, SystemCatalog sysCat, SystemConnection sysConn) 
	{
		// TODO Auto-generated method stub
	
		JSONParser parser = new JSONParser();
		Object obj = null;
		FileReader fileRd = null;
		try 
		{
			fileRd = new FileReader(jsonPath);
			obj = parser.parse(fileRd);
			JSONObject jsonObject = (JSONObject) obj;
			

			//String dnName = Utils.getDBName(currentDbId);
			String dnName = jsonObject.get("dbType").toString();
			String cataLogName = jsonObject.get("catalogName").toString();
			String connName = jsonObject.get("connectionName").toString();
			if(dnName.equalsIgnoreCase("Relational - Snowflake") || dnName.equalsIgnoreCase("Snowflake"))
			{
				dnName = "Relational - Snowflake DW";
			}
			sysCat.setName(cataLogName);
			
			sysConn.setName(connName);
			sysConn.setConnectionType(dnName);
			
			
			JSONArray entityList = (JSONArray) jsonObject.get("entityJsonArray");
			for (int i = 0; i < entityList.size(); i++) 
			{
				JSONObject entityJsonObj =  (JSONObject) entityList.get(i);
				String physicalName = (String)entityJsonObj.get("entityPhyName");
				if (physicalName.equalsIgnoreCase(tblName)) 
				{
					JSONArray attrList = (JSONArray) entityJsonObj.get("attribJsonArray");
					for (int j = 0; j < attrList.size(); j++) 
					{
						JSONObject attrJsonObj =  (JSONObject) attrList.get(j);

					   fillDataElemFromCatalog (attrJsonObj, columns, tblName, currentDbId);
					}
					
					fillSystemEntity(tblName,columns,sysEnties,dnName, colsListMap);
				}
				
			}
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

	private static void fillSystemEntity(String tblName, ArrayList<DataElement> columns, SystemEntities sysEnties,
			String dnName, LinkedHashMap<String, List<DataElement>> colsListMap) 
	{
		SystemEntity se = new SystemEntity();
		se.setName(tblName);
		se.setSystemEntityType(dnName);
		se.setDataAcquisitionMethod("Pull (Batch)");
		PhysicalProperties pp = new PhysicalProperties();
		pp.setCodepage("UTF-8");
		RelationalDatabaseProperties rp = new RelationalDatabaseProperties();
		rp.setSchemaName("schema");
		pp.setRelationalDatabaseProperties(rp);	
		se.setPhysicalProperties(pp);
		RelationalDataElements rde = new RelationalDataElements();
		rde.getDataElement().addAll(columns);
		colsListMap.put(tblName, columns);
		se.setRelationalDataElements(rde);
		sysEnties.getSystemEntity().add(se);

	}

	private static void fillDataElemFromCatalog(JSONObject attrJsonObj, ArrayList<DataElement> columns, String tblName,
			 int currentDbId) 
	{
		// TODO Auto-generated method stub
		String attrPhysicalName = ((String)attrJsonObj.get("physicalName"));
		attrPhysicalName = attrPhysicalName.trim();

		if(!attrPhysicalName.equals("KEY"))
		{
		
			DataElement dataElem = new DataElement();
			dataElem.setName(attrPhysicalName);
			
			String dataType = (String)attrJsonObj.get("datatype");
			dataElem.setDatatype(dataType);
			String length = "";
			dataElem.setKeyType("none");
			if(!dataType.contains("date") && !dataType.contains("time") && !dataType.contains("DATE") && !dataType.contains("TIME")){
				if ((String) attrJsonObj.get("length") != null)
					if (!((String) attrJsonObj.get("length")).equalsIgnoreCase(""))
						length  = attrJsonObj.get("length").toString();
						if(!length.equalsIgnoreCase("")){
							if(length.contains(",")){
								dataElem.setLength(20);
							}
							else{
								dataElem.setLength(Integer.parseInt(length));
							}
							
						}
						else{
							dataElem.setLength(20);
						}
			}
			 
				if ((String) attrJsonObj.get("precision") != null)
				            if(!((String) attrJsonObj.get("precision")).equalsIgnoreCase(""))
									dataElem.setPrecision(Integer.parseInt((String) attrJsonObj.get("precision")));
							if ((String) attrJsonObj.get("scale") != null)
								if (!((String) attrJsonObj.get("scale")).equalsIgnoreCase(""))
									dataElem.setScale(Integer.parseInt((String) attrJsonObj.get("scale"))); 
				 

			
			columns.add(dataElem);
		}
		
	 }

}