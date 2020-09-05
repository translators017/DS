package co.dataswitch.nosqldbDTO;

import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;


//import com.google.gson.Gson;

public class SchemaDesignDTO extends DBObject_new { //SchemaDesignDTO - SchemaEntityDesigner
	
	private static final long serialVersionUID = -6501775842380034771L;
	int dtoVersion = 1;
	String schemaDesign; //schemaDesign
	
	public String getSchemaDesign() {
		return schemaDesign;
	}
	public void setSchemaDesign(String schemaDesignJson) {		
		this.schemaDesign = schemaDesignJson;
	}
	
	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");		
		this.schemaDesign = (String) jsonObj.get("schemaDesign");
		//Gson gson = new Gson();
		//schemaDesign = gson.toJson(schemaDesign);

	    //JSONObject jsonObj = new JSONObject("{\"firstname\":\"John\",\"lastname\":\"Lee\"}");
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject sysMapGroupJson = new JSONObject();
		sysMapGroupJson.put("dtoVersion", dtoVersion);
		sysMapGroupJson.put("schemaDesign", schemaDesign);

		return sysMapGroupJson;
	}

}
