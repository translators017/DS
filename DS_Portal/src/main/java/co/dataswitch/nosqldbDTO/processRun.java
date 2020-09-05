package co.dataswitch.nosqldbDTO;

import java.sql.Timestamp;
import java.util.UUID;

import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;

public class processRun extends DBObject_new{
	
	private static final long serialVersionUID = 4832245356214001993L;
	
	public int dtoVersion=1;
	public String runId;
	public String fileName;
	public String status;
	public String errorMessage;
	public String startTime;
	public String endTime;
	public String complexityType;
	public String automationPercentage;
	public int sqlQueryCount;
	public int entityTransformationCount;
	public int attributeTransformationCount;
	public int totalTransformationCount;
	public int unsuccessfulTransformationCount;
	
	

	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.runId = (String) json.get("runId");
		this.fileName = (String) json.get("fileName");
		this.status = (String) json.get("status");
		this.errorMessage = (String) json.get("errorMessage");
		this.startTime = (String) json.get("startTime");
		this.endTime = (String) json.get("endTime");
		this.complexityType = (String) json.get("complexityType");
		this.automationPercentage = (String) json.get("automationPercentage");
		this.sqlQueryCount = (int) (long) json.get("sqlQueryCount");
		this.entityTransformationCount = (int) (long) json.get("entityTransformationCount");
		this.attributeTransformationCount = (int) (long) json.get("attributeTransformationCount");
		this.totalTransformationCount = (int) (long) json.get("totalTransformationCount");
		this.unsuccessfulTransformationCount = (int) (long) json.get("unsuccessfulTransformationCount");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject processRunObj = new JSONObject();
		processRunObj.put("dtoVersion", dtoVersion);
		processRunObj.put("runId", runId);
		processRunObj.put("fileName", fileName);
		processRunObj.put("status", status);
		processRunObj.put("errorMessage", errorMessage);
		processRunObj.put("startTime", startTime);
		processRunObj.put("endTime", endTime);
		processRunObj.put("complexityType",complexityType);
		processRunObj.put("automationPercentage",automationPercentage);
		processRunObj.put("sqlQueryCount",sqlQueryCount);
		processRunObj.put("entityTransformationCount",entityTransformationCount);
		processRunObj.put("attributeTransformationCount",attributeTransformationCount);
		processRunObj.put("totalTransformationCount",totalTransformationCount);
		processRunObj.put("unsuccessfulTransformationCount",unsuccessfulTransformationCount);
		return processRunObj;
	}

}
