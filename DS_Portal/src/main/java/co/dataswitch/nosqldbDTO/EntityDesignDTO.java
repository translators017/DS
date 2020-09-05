package co.dataswitch.nosqldbDTO;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import co.dataswitch.nosqldb.DBObject_new;



public class EntityDesignDTO extends DBObject_new {
	
	private static final long serialVersionUID = 978966622751065303L;
	public int dtoVersion = 1;
	public String entityName;
	
	public ArrayList<AttributeDesignDTO> attributesList = new ArrayList<AttributeDesignDTO>();
	
	public String setEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	public ArrayList<AttributeDesignDTO> getattributeMapList() {
		if(attributesList==null){
			attributesList = new ArrayList<AttributeDesignDTO>();
		}
		return attributesList;
	}
	public void setattributeMapList(ArrayList<AttributeDesignDTO> ruleList) {
		this.attributesList = ruleList;
	}

	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");
		this.entityName = (String) jsonObj.get("entityName");
		System.out.println("inside entityDesignData.name"+this.entityName);
		org.json.simple.JSONArray attributeArr = (JSONArray) jsonObj.get("attributeArr");
		for(int p = 0; p < attributeArr.size(); p++)
		{
			org.json.simple.JSONObject attributeMapObj = (org.json.simple.JSONObject) attributeArr.get(p);
			System.out.println("inside attributeMapObj"+attributeMapObj);
			AttributeDesignDTO attribMap = new AttributeDesignDTO();
			attribMap.setJsonData(attributeMapObj);
			System.out.println("inside attribMap"+attribMap);
			this.attributesList.add(attribMap);
			System.out.println("inside attributesList"+this.attributesList);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject mappingObject = new JSONObject();
		mappingObject.put("dtoVersion", dtoVersion);
		mappingObject.put("entityName", entityName);

		JSONArray attributeArr = new JSONArray();
		if(attributesList!=null && attributesList.size() > 0){
			for(int i = 0; i < attributesList.size(); i++){
				attributeArr.add(attributesList.get(i).getJsonData());
			}
		}
		mappingObject.put("attributeArr", attributeArr);
		return mappingObject;
	}
}
