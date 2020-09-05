package co.dataswitch.nosqldbDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;

public class ProcessGroup extends DBObject_new{

	private static final long serialVersionUID = 4910695222992811647L;
	
	public int dtoVersion=1;
	public String groupName;
	public String processType;
	public String selectedTechnology;
	public String lastModified;
	public ProcessConfig config;
	public List<processRun> processRunList = new ArrayList<>();
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getSelectedTechnology() {
		return selectedTechnology;
	}

	public void setSelectedTechnology(String selectedTechnology) {
		this.selectedTechnology = selectedTechnology;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.groupName = (String) json.get("groupName");
		this.processType = (String) json.get("processType");
		this.selectedTechnology = (String) json.get("selectedTechnology");
		this.lastModified = (String) json.get("lastModified");
		JSONObject config = (JSONObject) json.get("config");
		ProcessConfig configObj = new ProcessConfig();
		configObj.setJsonData(config);
		this.config = configObj;
		
		try {
			JSONArray processRunArray = (JSONArray) json.get("processRunArray");
			for(int p = 0; p < processRunArray.size(); p++)
			{
				org.json.simple.JSONObject runObj = (org.json.simple.JSONObject) processRunArray.get(p);
				processRun run = new processRun();
				run.setJsonData(runObj);
				this.processRunList.add(run);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject processGroupObj = new JSONObject();
		JSONArray processRunArray = new JSONArray();
		processGroupObj.put("dtoVersion", dtoVersion);
		processGroupObj.put("groupName", groupName);
		processGroupObj.put("processType", processType);
		processGroupObj.put("selectedTechnology", selectedTechnology);
		processGroupObj.put("lastModified", lastModified);
		processGroupObj.put("config", config.getJsonData());
		System.out.println("size--"+this.processRunList.size());
		if(this.processRunList!=null && this.processRunList.size() > 0){
			for (int i = 0; i < this.processRunList.size(); i++){
				JSONObject runObj = this.processRunList.get(i).getJsonData();
				processRunArray.add(runObj);
			}
		}
		processGroupObj.put("processRunArray", processRunArray);
		return processGroupObj;
	}

}
