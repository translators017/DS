package co.dataswitch.nosqldbDTO;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;

public class Catalogs extends DBObject_new {

	private static final long serialVersionUID = -3718502372429885721L;
	int dtoVersion = 2;
	String catalogName;
	String catalogDescription;
	String connectionName;
	String dbType;
	String userName;
	String password;
	String hostName;
	String schemaName;
	String directoryPath;
	String fileExtenstion;
	String headerRows;
	String textQualifier;
	String columnDelimiter;
	String rowDelimiter;
	String AccountName;
	String Port;
	int noOfEntities;
	String storageType;
	

	private ArrayList<Entity> EntityLst;

	public String getCatalogDescription() {
		return catalogDescription;
	}
	public void setCatalogDescription(String catalogDescription1) {
		catalogDescription = catalogDescription1;
	}

	public String getPort() {
		return Port;
	}
	public void setPort(String port2) {
		Port = port2;
	}

	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public int getNoOfEntities() {
		return noOfEntities;
	}
	public void setNoOfEntities(int noOfEntities) {
		this.noOfEntities = noOfEntities;
	}
	public ArrayList<Entity> getEntityLst() {
		if(EntityLst==null){
			EntityLst = new ArrayList<Entity>();
		}
		return EntityLst;
	}
	public void setEntityLst(ArrayList<Entity> entityLst) {
		EntityLst = entityLst;
	}


	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getDirectoryPath() {
		return directoryPath;
	}
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	public String getFileExtenstion() {
		return fileExtenstion;
	}
	public void setFileExtenstion(String fileExtenstion) {
		this.fileExtenstion = fileExtenstion;
	}
	public String getHeaderRows() {
		return headerRows;
	}
	public void setHeaderRows(String headerRows) {
		this.headerRows = headerRows;
	}
	public String getTextQualifier() {
		return textQualifier;
	}
	public void setTextQualifier(String textQualifier) {
		this.textQualifier = textQualifier;
	}
	public String getColumnDelimiter() {
		return columnDelimiter;
	}
	public void setColumnDelimiter(String columnDelimiter) {
		this.columnDelimiter = columnDelimiter;
	}
	public String getRowDelimiter() {
		return rowDelimiter;
	}
	public void setRowDelimiter(String rowDelimiter) {
		this.rowDelimiter = rowDelimiter;
	}
	
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");
		this.catalogName = (String) jsonObj.get("catalogName");
		this.catalogDescription = (String) jsonObj.get("catalogDescription");
		this.connectionName = (String) jsonObj.get("connectionName");
		this.dbType = (String) jsonObj.get("dbType");
		this.userName = (String) jsonObj.get("userName");
		this.password = (String) jsonObj.get("password");
		this.hostName = (String) jsonObj.get("hostName");
		this.schemaName = (String) jsonObj.get("schemaName");
		this.directoryPath = (String) jsonObj.get("directoryPath");
		this.fileExtenstion = (String) jsonObj.get("fileExtenstion");
		this.headerRows = (String) jsonObj.get("headerRows");
		this.textQualifier = (String) jsonObj.get("textQualifier");
		this.columnDelimiter = (String) jsonObj.get("columnDelimiter");
		this.rowDelimiter = (String) jsonObj.get("rowDelimiter");
		this.AccountName = (String) jsonObj.get("AccountName");
		this.Port = (String) jsonObj.get("Port");
		this.noOfEntities = (int) (long) jsonObj.get("noOfEntities");
		try {
			JSONArray entityJsonArray = (JSONArray) jsonObj.get("entityJsonArray");
			for(int p = 0; p < entityJsonArray.size(); p++)
			{
				org.json.simple.JSONObject entityObj = (org.json.simple.JSONObject) entityJsonArray.get(p);
				Entity en = new Entity();
				en.setJsonData(entityObj);
				this.getEntityLst().add(en);

				JSONArray attribJsonArray = (JSONArray) entityObj.get("attribJsonArray");
				for(int q = 0; q < attribJsonArray.size(); q++)
				{
					org.json.simple.JSONObject attribObj = (org.json.simple.JSONObject) attribJsonArray.get(q);
					Attribute attrib = new Attribute();
					attrib.setJsonData(attribObj);
					this.getEntityLst().get(p).getAttrList().add(attrib);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		if(dataVersion == 2){
			this.storageType = (String) jsonObj.get("storageType");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject ctlgjsonObj = new JSONObject();
		JSONArray entityJsonArray = new JSONArray();
		ctlgjsonObj.put("dtoVersion", dtoVersion);
		ctlgjsonObj.put("catalogName", catalogName);
		ctlgjsonObj.put("catalogDescription", catalogDescription);
		ctlgjsonObj.put("connectionName", connectionName);
		ctlgjsonObj.put("dbType", dbType);
		ctlgjsonObj.put("userName", userName);
		ctlgjsonObj.put("password", password);
		ctlgjsonObj.put("hostName", hostName);
		ctlgjsonObj.put("schemaName", schemaName);
		ctlgjsonObj.put("directoryPath", directoryPath);
		ctlgjsonObj.put("fileExtenstion", fileExtenstion);
		ctlgjsonObj.put("headerRows", headerRows);
		ctlgjsonObj.put("textQualifier", textQualifier);
		ctlgjsonObj.put("columnDelimiter", columnDelimiter);
		ctlgjsonObj.put("rowDelimiter", rowDelimiter);
		ctlgjsonObj.put("AccountName", AccountName);
		ctlgjsonObj.put("Port", Port);
		ctlgjsonObj.put("noOfEntities", noOfEntities);
		ctlgjsonObj.put("storageType", storageType);
		if(this.getEntityLst()!=null && this.getEntityLst().size() > 0){
			for (int i = 0; i < this.getEntityLst().size(); i++){

				JSONObject entityJsonObj = this.getEntityLst().get(i).getJsonData();
				JSONArray attribJsonArray = new JSONArray();
				if(this.getEntityLst().get(i).getAttrList()!=null && this.getEntityLst().get(i).getAttrList().size() > 0){
					for (int j = 0; j < this.getEntityLst().get(i).getAttrList().size(); j++){
						JSONObject attribJsonObj = this.getEntityLst().get(i).getAttrList().get(j).getJsonData();
						attribJsonArray.add(attribJsonObj);
					}
				}
				entityJsonObj.put("attribJsonArray", attribJsonArray);
				entityJsonArray.add(entityJsonObj);
			}
		}
		ctlgjsonObj.put("entityJsonArray", entityJsonArray);
		return ctlgjsonObj;
	}

	/* eg:
	{
		"dtoVersion" : 1,
		"catalogName" : "SourceCatalog",
		"catalogDescription" : "Demo",
		.
		.
		.
		.
		"entityJsonArray" : [
			{
				"entityPhyName" : "Dependent",
				.
				.
				"attribJsonArray" : [
					{
						"attribPhyName" : "Title",
						"attribLogName" : "Title",
						.
						.
						.
						.
 					},
 					{
						"attribPhyName" : "First_Name",
						"attribLogName" : "First Name",
						.
						.
						.
						.
 					},
				]
			},
			{
				"entityPhyName" : "Party",
				.
				. 
				"attribJsonArray" : [
					{
						.
						.
						.
					}
				]
			},

		]
	}
	 */
}
