package co.dataswitch.nosqldbDTO;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;

public class ProcessConfig extends DBObject_new{
	
	private static final long serialVersionUID = 197615048354917208L;
	
	public int dtoVersion=1;
	public String processGroupName;
	public String currentCode;
	public String currentTech;
	public String currentScript;
	public String sourceCatalogList;
	public String targetCode;
	public String targetTech;
	public String targetProcessFlow;
	public String targetScript;
	public String schemaMappingName;
	JSONArray newConnDtls;

	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.processGroupName = (String) json.get("processGroupName");
		this.currentCode = (String) json.get("currentCode");
		this.currentTech = (String) json.get("currentTech");
		this.currentScript = (String) json.get("currentScript");
		this.sourceCatalogList = (String) json.get("sourceCatalogList");
		this.targetCode = (String) json.get("targetCode");
		this.targetTech = (String) json.get("targetTech");
		this.targetProcessFlow = (String) json.get("targetProcessFlow");
		this.targetScript = (String) json.get("targetScript");
		this.schemaMappingName = (String) json.get("schemaMappingName");
		this.newConnDtls = (JSONArray) json.get("newConnDtls");
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject processConfigObj = new JSONObject();
		processConfigObj.put("dtoVersion", dtoVersion);
		processConfigObj.put("processGroupName", processGroupName);
		processConfigObj.put("currentCode", currentCode);
		processConfigObj.put("currentTech", currentTech);
		processConfigObj.put("currentScript", currentScript);
		processConfigObj.put("sourceCatalogList", sourceCatalogList);
		processConfigObj.put("targetCode", targetCode);
		processConfigObj.put("targetTech",targetTech);
		processConfigObj.put("targetProcessFlow",targetProcessFlow);
		processConfigObj.put("targetScript",targetScript);
		processConfigObj.put("schemaMappingName",schemaMappingName);
		processConfigObj.put("newConnDtls",newConnDtls);
		return processConfigObj;
	}

}
