package co.dataswitch.nosqldbDTO;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import co.dataswitch.nosqldb.DBObject_new;
import co.dataswitch.nosqldbDTO.AttributeMap.sourceAttributeRef_json;
import co.dataswitch.nosqldbDTO.AttributeMap.transformationRule_json;


public class Mapping extends DBObject_new {
	
	private static final long serialVersionUID = 978966622751065303L;
	int dtoVersion = 1;
	String MappingName;
	String Mappingdesc;
	String mappingType;
	String status;
	String catalogObject = null;
	String created_date;
	String lastUpdDate;
	int numOfSrc=0;
	int numOfTgt=0;
	String entityJson;

	ArrayList<MappingTransformations> transformationList = new ArrayList<MappingTransformations>();
	ArrayList<AttributeMap> attributeMapList = new ArrayList<AttributeMap>();
	public int getnumOfSrc() {
		return numOfSrc;
	}

	public void setnumOfSrc(int numOfSrc) {
		this.numOfSrc = numOfSrc;
	}
	public int getnumOfTgt() {
		return numOfTgt;
	}

	public void setnumOfTgt(int numOfTgt) {
		this.numOfTgt = numOfTgt;
	}
	public String getlastUpdDate() {
		return lastUpdDate;
	}

	public void setlastUpdDate(String lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getcreated_date() {
		return created_date;
	}

	public void setcreated_date(String created_date) {
		this.created_date = created_date;
	}
	
	public String getCatalogObject() {
		return catalogObject;
	}
	public void setCatalogObject(String catalogObject) {
		this.catalogObject = catalogObject;
	}	
	
	public ArrayList<AttributeMap> getattributeMapList() {
		if(attributeMapList==null){
			attributeMapList = new ArrayList<AttributeMap>();
		}
		return attributeMapList;
	}
	public void setattributeMapList(ArrayList<AttributeMap> ruleList) {
		this.attributeMapList = ruleList;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<MappingTransformations> gettransformationList() {
		if(transformationList==null){
			transformationList = new ArrayList<MappingTransformations>();
		}
		return transformationList;
	}
	
	public String getMappingName() {
		return MappingName;
	}
	public void setMappingName(String mappingName) {
		MappingName = mappingName;
	}
	public String getMappingdesc() {
		return Mappingdesc;
	}
	public void setMappingdesc(String mappingdesc) {
		Mappingdesc = mappingdesc;
	}

	@Override
	public void setJsonData(JSONObject jsonObj) {
		int dataVersion = (int) (long) jsonObj.get("dtoVersion");
		this.MappingName = (String) jsonObj.get("MappingName");
		this.Mappingdesc = (String) jsonObj.get("Mappingdesc");
		this.status = (String) jsonObj.get("status");
		this.catalogObject = (String) jsonObj.get("catalogObject");
		this.created_date = (String) jsonObj.get("created_date");
		this.lastUpdDate = (String) jsonObj.get("lastUpdDate");
		this.numOfSrc = (int) (long) jsonObj.get("numOfSrc");
		this.numOfTgt = (int) (long) jsonObj.get("numOfTgt");
		this.mappingType = (String)  jsonObj.get("mappingType");
		this.entityJson = (String) jsonObj.get("entityJson");
		JSONParser parser = new JSONParser();
		try {
			org.json.simple.JSONArray transformationArr = (org.json.simple.JSONArray) parser.parse(jsonObj.get("transformationArr").toString());
			for(int p = 0; p < transformationArr.size(); p++)
			{
				org.json.simple.JSONObject transformationObj = (org.json.simple.JSONObject) transformationArr.get(p);
				MappingTransformations mt = new MappingTransformations();
				mt.setJsonData(transformationObj);
				this.gettransformationList().add(mt);
			}
			org.json.simple.JSONArray attributeArr = (org.json.simple.JSONArray) parser.parse(jsonObj.get("attributeArr").toString());
			for(int p = 0; p < attributeArr.size(); p++)
			{
				org.json.simple.JSONObject attributeMapObj = (org.json.simple.JSONObject) attributeArr.get(p);
				AttributeMap attribMap = new AttributeMap();
				attribMap.setJsonData(attributeMapObj);
				
				JSONArray srcRefArray = (JSONArray) attributeMapObj.get("sourceListArr");
				for(int q = 0; q < srcRefArray.size(); q++)
				{
					org.json.simple.JSONObject srcRefObj = (org.json.simple.JSONObject) srcRefArray.get(q);
					sourceAttributeRef_json srcRef = new sourceAttributeRef_json();
					srcRef.setJsonData(srcRefObj);
					attribMap.getSrcAtrRefList().add(srcRef);
				}
				JSONArray trfmRefArray = (JSONArray) attributeMapObj.get("transformationRuleArr");
				for(int q = 0; q < trfmRefArray.size(); q++)
				{
					org.json.simple.JSONObject trfmObj = (org.json.simple.JSONObject) trfmRefArray.get(q);
					transformationRule_json trfmRule = new transformationRule_json();
					trfmRule.setJsonData(trfmObj);
					attribMap.getTransformationRuleList().add(trfmRule);
				}
				this.getattributeMapList().add(attribMap);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public String getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(String entityJson) {
		this.entityJson = entityJson;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject mappingObject = new JSONObject();
		mappingObject.put("dtoVersion", dtoVersion);
		mappingObject.put("MappingName", MappingName);
		mappingObject.put("Mappingdesc", Mappingdesc);
		mappingObject.put("status", status);
		mappingObject.put("catalogObject", catalogObject);
		mappingObject.put("created_date", created_date);
		mappingObject.put("lastUpdDate", lastUpdDate);
		mappingObject.put("numOfSrc", numOfSrc);
		mappingObject.put("numOfTgt", numOfTgt);
		mappingObject.put("mappingType", mappingType);
		mappingObject.put("entityJson", entityJson);
		JSONArray transformationArr = new JSONArray();
		if(this.gettransformationList()!=null && this.gettransformationList().size() > 0){
			for (int i = 0; i < this.gettransformationList().size(); i++){
				JSONObject transformationObj = this.gettransformationList().get(i).getJsonData();
				transformationArr.add(transformationObj);
			}
		}
		mappingObject.put("transformationArr", transformationArr);
		JSONArray attributeArr = new JSONArray();
		if(this.getattributeMapList()!=null && this.getattributeMapList().size() > 0){
			for(int i = 0; i < this.getattributeMapList().size(); i++){
				attributeArr.add(this.getattributeMapList().get(i).getJsonData());
			}
		}
		mappingObject.put("attributeArr", attributeArr);
		return mappingObject;
	}
}
