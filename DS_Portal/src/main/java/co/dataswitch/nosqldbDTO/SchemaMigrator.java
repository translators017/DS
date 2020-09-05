package co.dataswitch.nosqldbDTO;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;

public class SchemaMigrator extends DBObject_new {

	private static final long serialVersionUID = -3718502372429885721L;
	int dtoVersion = 3;
	String projectName;
	String projectDescription;
	String dbType;
	String catalogName;
	String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}


	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");
		this.projectName = (String) jsonObj.get("projectName");
		this.projectDescription = (String) jsonObj.get("projectDescription");
		this.dbType = (String) jsonObj.get("projectDB");
		this.catalogName = (String) jsonObj.get("catalogName");
		
		if(dataVersion == 3)
			this.status  = (String) jsonObj.get("status");
		else
			this.status = "mapped";
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject ctlgjsonObj = new JSONObject();
		ctlgjsonObj.put("dtoVersion", dtoVersion);
		
		ctlgjsonObj.put("projectName", projectName);
		ctlgjsonObj.put("projectDescription", projectDescription);
		ctlgjsonObj.put("projectDB", dbType);
		ctlgjsonObj.put("catalogName", catalogName);
		ctlgjsonObj.put("status", status);
		return ctlgjsonObj;
	}

	
}
