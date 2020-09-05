package co.dataswitch.nosqldbDTO;

import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;


public class SystemMapping extends DBObject_new {
	
	private static final long serialVersionUID = -6501775842380034771L;
	int dtoVersion = 1;
	String systemName;
	String description;
	int noOfMappings;
	String domainName;
	String subDomainName;
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNoOfMappings() {
		return noOfMappings;
	}
	public void setNoOfMappings(int noOfMappings) {
		this.noOfMappings = noOfMappings;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getSubDomainName() {
		return subDomainName;
	}
	public void setSubDomainName(String subDomainName) {
		this.subDomainName = subDomainName;
	}
	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");
		this.systemName = (String) jsonObj.get("systemName");
		this.description = (String) jsonObj.get("description");
		this.noOfMappings = (int) (long) jsonObj.get("noOfMappings");
		this.domainName = (String) jsonObj.get("domainName");
		this.subDomainName = (String) jsonObj.get("subDomainName");
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject sysMapGroupJson = new JSONObject();
		sysMapGroupJson.put("dtoVersion", dtoVersion);
		sysMapGroupJson.put("systemName", systemName);
		sysMapGroupJson.put("description", description);
		sysMapGroupJson.put("noOfMappings", noOfMappings);
		sysMapGroupJson.put("domainName", domainName);
		sysMapGroupJson.put("subDomainName", subDomainName);
		return sysMapGroupJson;
	}
	
	
}
