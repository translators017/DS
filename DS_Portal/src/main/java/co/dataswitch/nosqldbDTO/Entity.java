package co.dataswitch.nosqldbDTO;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;


public class Entity extends DBObject_new {

	private static final long serialVersionUID = -6737562915704945179L;
	int dtoVersion = 3;
	String physicalName;
	String logicalName;
	String description;
	String catalogName;
	String entityType;
	String entityDbType;
	String storageType;
	String directoryPath;
	String fileExtenstion;
	String headerRows;
	String textQualifier;
	String columnDelimiter;
	String rowDelimiter;
	String xsdRefName;
	String recordIdentifier;
	private ArrayList<Attribute> AttrList;
	
	public String getPhysicalName() {
		return physicalName;
	}
	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}
	public String getLogicalName() {
		return logicalName;
	}
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}
	
	public ArrayList<Attribute> getAttrList() {
		if(AttrList==null){
			AttrList = new ArrayList<Attribute>();
		}
		return AttrList;
	}
	public void setAttrList(ArrayList<Attribute> attrList) {
		
		AttrList = attrList;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getStorageType() {
		return storageType;
	}
	public void setStorageType(String storageType) {
		this.storageType = storageType;
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
	public String getRecordIdentifier() {
		return recordIdentifier;
	}
	public void setRecordIdentifier(String recordIdentifier) {
		this.recordIdentifier = recordIdentifier;
	}
	public String getXsdRefName() {
		return xsdRefName;
	}
	public void setXsdRefName(String xsdRefName) {
		this.xsdRefName = xsdRefName;
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
	public String getEntityDbType() {
		return entityDbType;
	}
	public void setEntityDbType(String entityDbType) {
		this.entityDbType = entityDbType;
	}
	
	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.physicalName = (String) json.get("entityPhyName");
		this.logicalName = (String) json.get("entityLogName");
		this.description = (String) json.get("entityDesc");
		this.catalogName = (String) json.get("catalogName");
		if(dataVersion < 3)
		{
			this.entityType = null;
			this.directoryPath = null;
			this.fileExtenstion = null;
			this.headerRows = null;
			this.textQualifier = null;
			this.columnDelimiter = null;
			this.xsdRefName = null;	
			this.recordIdentifier = null;
		}
		if(dataVersion == 3)
		{
			this.entityType = (String) json.get("entityType");
			this.directoryPath = (String) json.get("directoryPath");
			this.fileExtenstion = (String) json.get("fileExtenstion");
			this.headerRows = (String) json.get("headerRows");
			this.textQualifier = (String) json.get("textQualifier");
			this.columnDelimiter = (String) json.get("columnDelimiter");
			this.rowDelimiter = (String) json.get("rowDelimiter");
			this.xsdRefName = (String) json.get("xsdRefName");	
			this.recordIdentifier = (String) json.get("recordIdentifier");	
			this.storageType = (String) json.get("storageType");
			this.entityDbType = (String) json.get("entityDbType");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject entityJsonObj = new JSONObject();
		entityJsonObj.put("dtoVersion", dtoVersion);
		entityJsonObj.put("entityPhyName", physicalName);
		entityJsonObj.put("entityLogName", logicalName);
		entityJsonObj.put("entityDesc", description);
		entityJsonObj.put("catalogName", catalogName);
		entityJsonObj.put("entityType", entityType);
		entityJsonObj.put("storageType", storageType);
		entityJsonObj.put("directoryPath", directoryPath);
		entityJsonObj.put("fileExtenstion", fileExtenstion);
		entityJsonObj.put("headerRows", headerRows);
		entityJsonObj.put("textQualifier", textQualifier);
		entityJsonObj.put("columnDelimiter", columnDelimiter);
		entityJsonObj.put("rowDelimiter", rowDelimiter);
		entityJsonObj.put("xsdRefName", xsdRefName);
		entityJsonObj.put("recordIdentifier", recordIdentifier);
		entityJsonObj.put("entityDbType", entityDbType);
		return entityJsonObj;
	}
	
}
