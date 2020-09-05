package co.dataswitch.nosqldbDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.dataswitch.nosqldb.DBObject_new;
import co.dataswitch.nosqldbDTO.AttributeMap.sourceAttributeRef_json;
import co.dataswitch.nosqldbDTO.AttributeMap.transformationRule_json;


public class AttributeDesignDTO extends DBObject_new implements Serializable{

	
	private static final long serialVersionUID = -6595569108433116793L;

	//Original
	public int dtoVersion = 3;
	public String targetCatalogName;	
	public String targetEntityRef;
	public String targetAttributeRef;
	public String logicalTargetEntityRef;
	public String logicalTargetAttributeRef;
	public String derivedVarDetails;
	public String targetCompName;

	public List<sourceAttributeRef_json> srcAtrRefList;
	public List<transformationRule_json> transformationRuleList;

	/*int dtoVersion = 1;
	String targetCatalogName;	//empty string
	String targetEntityName;    //xpath or root entity depends 
	String targetAttributeName;
	String targetEntityLogicalName; //same as targetEntityName
	String targetAttributeLogicalName; //same as targetAttributeName21
	String derivedVarDetails; 
	String targetCompName;  //same as targetEntityName

	List<sourceAttributeRef_json> srcAtrRefList;
	List<transformationRule_json> transformationRuleList;*/

	public String getTargetCompName() {
		return targetCompName;
	}
	public void setTargetCompName(String targetCompName) {
		this.targetCompName = targetCompName;
	}
	public String getCatalogName() {
		return targetCatalogName;
	}
	public void setCatalogName(String targetCatalogName) {
		this.targetCatalogName = targetCatalogName;
	}
	
	public String getDerivedVarDetails() {
		return derivedVarDetails;
	}
	public void setDerivedVarDetails(String derivedVarDetails) {
		this.derivedVarDetails = derivedVarDetails;
	}
	public String getTargetEntityRef() {
		return targetEntityRef;
	}
	public void setTargetEntityRef(String targetEntityRef) {
		this.targetEntityRef = targetEntityRef;
	}
	public String getTargetAttributeRef() {
		return targetAttributeRef;
	}
	public void setTargetAttributeRef(String targetAttributeRef) {
		this.targetAttributeRef = targetAttributeRef;
	}
	
	public String getLogicalTargetEntityRef() {
		return logicalTargetEntityRef;
	}
	public void setLogicalTargetEntityRef(String logicalTargetEntityRef) {
		this.logicalTargetEntityRef = logicalTargetEntityRef;
	}
	public String getLogicalTargetAttributeRef() {
		return logicalTargetAttributeRef;
	}
	public void setLogicalTargetAttributeRef(String logicalTargetAttributeRef) {
		this.logicalTargetAttributeRef = logicalTargetAttributeRef;
	}

	public List<sourceAttributeRef_json> getSrcAtrRefList() {
		if(srcAtrRefList==null){
			srcAtrRefList = new ArrayList<sourceAttributeRef_json>();
		}
		return srcAtrRefList;
	}
	public List<transformationRule_json> getTransformationRuleList() {
		if(transformationRuleList==null){
			transformationRuleList = new ArrayList<transformationRule_json>();
		}
		return transformationRuleList;
	}


	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.targetEntityRef = (String) json.get("targetEntityRef");
		this.logicalTargetEntityRef = (String) json.get("logicalTargetEntityRef");
		this.targetAttributeRef = (String) json.get("targetAttributeRef");
		this.logicalTargetAttributeRef = (String) json.get("logicalTargetAttributeRef");
		this.derivedVarDetails = (String) json.get("derivedVarDetails");

		srcAtrRefList = new ArrayList<sourceAttributeRef_json>();
		JSONArray srcRefArray = (JSONArray) json.get("srcAtrRefList");
		for(int q = 0; q < srcRefArray.size(); q++)
		{
			org.json.simple.JSONObject srcRefObj = (org.json.simple.JSONObject) srcRefArray.get(q);
			sourceAttributeRef_json srcRef = new sourceAttributeRef_json();
			srcRef.setJsonData(srcRefObj);
			srcAtrRefList.add(srcRef);
		}
		
		transformationRuleList = new ArrayList<transformationRule_json>();
		JSONArray trfmRefArray = (JSONArray) json.get("transformationRuleList");
		for(int q = 0; q < trfmRefArray.size(); q++)
		{
			org.json.simple.JSONObject trfmObj = (org.json.simple.JSONObject) trfmRefArray.get(q);
			transformationRule_json trfmRule = new transformationRule_json();
			trfmRule.setJsonData(trfmObj);
			transformationRuleList.add(trfmRule);
		}	
		/*if(dataVersion == 1)
		{
			this.targetCatalogName = null;
			this.targetCompName = (String) json.get("targetEntityRef");
		}*/
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject attributeMapObj = new JSONObject();
		attributeMapObj.put("dtoVersion", dtoVersion);
		attributeMapObj.put("targetCatalogName", targetCatalogName);
		attributeMapObj.put("targetEntityRef", targetEntityRef);
		attributeMapObj.put("logicalTargetEntityRef", logicalTargetEntityRef);
		attributeMapObj.put("targetAttributeRef", targetAttributeRef);
		attributeMapObj.put("logicalTargetAttributeRef", logicalTargetAttributeRef);
		attributeMapObj.put("derivedVarDetails", derivedVarDetails);
		attributeMapObj.put("targetCompName", targetCompName);
		JSONArray trfmArray = new JSONArray();
		JSONArray srcRefArray = new JSONArray();
		if(transformationRuleList!=null && transformationRuleList.size() > 0){
			for (int i = 0; i < transformationRuleList.size(); i++){
				JSONObject trfmObj = this.transformationRuleList.get(i).getJsonData();
				trfmArray.add(trfmObj);
			}
		}
		
		if(srcAtrRefList!=null && srcAtrRefList.size() > 0){
			for(int i = 0; i < srcAtrRefList.size(); i++){
				JSONObject srcRefObj = srcAtrRefList.get(i).getJsonData();
				srcRefArray.add(srcRefObj);
			}
		}
		attributeMapObj.put("transformationRuleList", trfmArray);
		attributeMapObj.put("srcAtrRefList", srcRefArray);
		return attributeMapObj;
	}
}
