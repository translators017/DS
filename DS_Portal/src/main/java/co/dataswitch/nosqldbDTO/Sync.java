package co.dataswitch.nosqldbDTO;

import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;


public class Sync extends DBObject_new{
	
	
	private static final long serialVersionUID = -7381496323339255781L;
	final static int dtoVersion = 1;
	String toolName;
	String toolType;
	String toolUsername;
	String toolPassword;
	String toolHost;
	String toolPort;
	
	
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	public String getToolUsername() {
		return toolUsername;
	}
	public void setToolUsername(String toolUsername) {
		this.toolUsername = toolUsername;
	}
	public String getToolPassword() {
		return toolPassword;
	}
	public void setToolPassword(String toolPassword) {
		this.toolPassword = toolPassword;
	}
	public String getToolHost() {
		return toolHost;
	}
	public void setToolHost(String toolHost) {
		this.toolHost = toolHost;
	}
	public String getToolPort() {
		return toolPort;
	}
	public void setToolPort(String toolPort) {
		this.toolPort = toolPort;
	}
	
	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");
		this.toolName = (String) jsonObj.get("toolName");
		this.toolType = (String) jsonObj.get("toolType");
		this.toolUsername = (String) jsonObj.get("toolUsername");
		this.toolPassword = (String) jsonObj.get("toolPassword");
		this.toolPort = (String) jsonObj.get("toolPort");
		this.toolHost = (String) jsonObj.get("toolHost");
		if(dataVersion == 2)
		{
			
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("dtoVersion", dtoVersion);
		jsonObj.put("toolName", toolName);
		jsonObj.put("toolType", toolType);
		jsonObj.put("toolUsername", toolUsername);
		jsonObj.put("toolPassword", toolPassword);
		jsonObj.put("toolHost", toolHost);
		jsonObj.put("toolPort", toolPort);
		return jsonObj;
	}
	

}
