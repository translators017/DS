
package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemConnection;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.PhysicalProperties;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.PhysicalProperties.RelationalDatabaseProperties;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;

public class fillSysCatalog
{

	public static LinkedHashMap<String, SystemEntity> prcsSystemCatalog(String jsonPath, SystemCatalog sysCat, String selectedCatalogNames) 
	{
		 SystemConnection sysConn = new SystemConnection();
		 LinkedHashMap<String, SystemEntity>  sysCatalog = getEntityFromJson(jsonPath, sysCat, sysConn, selectedCatalogNames);
		 sysCat.setSystemConnection(sysConn);
		
		return sysCatalog;
	}

	private static LinkedHashMap<String, SystemEntity> getEntityFromJson(String jsonPath, SystemCatalog sysCat, SystemConnection sysConn, String selectedCatalogNames) 
	{
		// TODO Auto-generated method stub
		 LinkedHashMap<String, SystemEntity> catalogMap = new LinkedHashMap<String, SystemEntity>();
		
		 ArrayList<Token> Tokens = FunctionTypeConv.getTokens(selectedCatalogNames);

			
			for(int t = 0 ; t < Tokens.size() ; t++)
			{
				if(!Tokens.get(t).type.name().equalsIgnoreCase("STRCONSTANTS"))
	            	continue;
				else
				{
					JSONParser parser = new JSONParser();
					Object obj = null;
					FileReader fileRd = null;
					try 
					{
						String fileName = Tokens.get(t).data.substring(Tokens.get(t).data.indexOf("\"")+1, Tokens.get(t).data.length()-1);
						fileRd = new FileReader(jsonPath+fileName+".bin");
						obj = parser.parse(fileRd);
						JSONObject jsonObject = (JSONObject) obj;
						
		
						//String dnName = Utils.getDBName(currentDbId);
						String dnName = jsonObject.get("dbType").toString();
						String cataLogName = jsonObject.get("catalogName").toString();
						String connName = jsonObject.get("connectionName").toString();
						if(dnName.equalsIgnoreCase("Relational - Snowflake") ||
								dnName.equalsIgnoreCase("Snowflake") || dnName.equalsIgnoreCase("Snowflake - Cloud"))
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
							ArrayList<DataElement> columns = new ArrayList<DataElement>();
							
							JSONArray attrList = (JSONArray) entityJsonObj.get("attribJsonArray");
							for (int j = 0; j < attrList.size(); j++) 
							{
								JSONObject attrJsonObj =  (JSONObject) attrList.get(j);
		
							     fillDataElemFromCatalog (attrJsonObj, columns);
							     //String keyEntity = cataLogName +"."+physicalName;
							     String keyEntity = physicalName;
							     catalogMap.put(keyEntity.toLowerCase(), fillSystemEntity(columns,dnName, physicalName, cataLogName)) ;
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
				
			}
			
			return catalogMap;
		
	}
	
	private static SystemEntity fillSystemEntity(ArrayList<DataElement> columns, String dnName, String physicalName, String cataLogName) 
	{
		SystemEntity sysEntity = new SystemEntity();
		sysEntity.setName(physicalName);
		sysEntity.setSystemEntityType(dnName);
		sysEntity.setDataAcquisitionMethod("Pull (Batch)");
		PhysicalProperties pp = new PhysicalProperties();
		pp.setCodepage("UTF-8");
		RelationalDatabaseProperties rp = new RelationalDatabaseProperties();
		rp.setSchemaName(cataLogName);
		pp.setRelationalDatabaseProperties(rp);	
		sysEntity.setPhysicalProperties(pp);
		RelationalDataElements rde = new RelationalDataElements();
		rde.getDataElement().addAll(columns);
		sysEntity.setRelationalDataElements(rde);
		
		return sysEntity;

	}

	private static void fillDataElemFromCatalog(JSONObject attrJsonObj, ArrayList<DataElement> columns) 
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