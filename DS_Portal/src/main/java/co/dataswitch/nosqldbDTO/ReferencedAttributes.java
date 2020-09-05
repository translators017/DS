package co.dataswitch.nosqldbDTO;

import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;


public class ReferencedAttributes extends DBObject_new{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5923831112980696350L;
	int dtoVersion = 1;
	String catalogName;
	String entityName;
	String attributeName;
	
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");
		this.catalogName = (String) jsonObj.get("catalogName");
		this.entityName = (String) jsonObj.get("entityName");
		this.attributeName = (String) jsonObj.get("attributeName");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("dtoVersion", dtoVersion);
		jsonObj.put("catalogName", catalogName);
		jsonObj.put("entityName", entityName);
		jsonObj.put("attributeName", attributeName);
		return jsonObj;
	}
}
