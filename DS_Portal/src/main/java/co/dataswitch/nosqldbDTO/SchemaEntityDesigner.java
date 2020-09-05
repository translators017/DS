package co.dataswitch.nosqldbDTO;

import org.json.simple.JSONObject;


import co.dataswitch.nosqldb.DBObject_new;

public class SchemaEntityDesigner extends DBObject_new { //SchemaDesignDTO - SchemaEntityDesigner
	
	private static final long serialVersionUID = -6501775842380034771L;
	int dtoVersion = 1;
	String tgtEntityDesign; //schemaDesign
	
	public String getTgtEntityDesign() {
		return tgtEntityDesign;
	}
	public void setTgtEntityDesign(String tgtEntityDesignJson) {		
		this.tgtEntityDesign = tgtEntityDesignJson;
	}
	
	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");		
		this.tgtEntityDesign = (String) jsonObj.get("tgtEntityDesign");
		//Gson gson = new Gson();
		//tgtEntityDesign = gson.toJson(tgtEntityDesign);

	    //JSONObject jsonObj = new JSONObject("{\"firstname\":\"John\",\"lastname\":\"Lee\"}");
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject sysMapGroupJson = new JSONObject();
		sysMapGroupJson.put("dtoVersion", dtoVersion);
		sysMapGroupJson.put("tgtEntityDesign", tgtEntityDesign);

		return sysMapGroupJson;
	}

}
