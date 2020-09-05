package co.dataswitch.nosqldbDTO;

import java.io.Serializable;

import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;


public class MappingTransformations extends DBObject_new implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2442018056028550271L;
	
	int dtoVersion = 1;
	String dataMappingName;
	String elementName;
	String transformationType;
	JSONObject transformationDetails;
	
	String componentName;
	String prevComponentName;
	String nextComponentName;
	
	public JSONObject getTransformationDetails() {
		return transformationDetails;
	}
	public void setTransformationDetails(JSONObject transformationDetails) {
		this.transformationDetails = transformationDetails;
	}
	
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	public String getDataMappingName() {
		return dataMappingName;
	}
	public void setDataMappingName(String dataMappingName) {
		this.dataMappingName = dataMappingName;
	}
	public String getTransformationType() {
		return transformationType;
	}
	public void setTransformationType(String transformationType) {
		this.transformationType = transformationType;
	}
	
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getPrevComponentName() {
		return prevComponentName;
	}
	public void setPrevComponentName(String prevComponentName) {
		this.prevComponentName = prevComponentName;
	}
	public String getNextComponentName() {
		return nextComponentName;
	}
	public void setNextComponentName(String nextComponentName) {
		this.nextComponentName = nextComponentName;
	}
	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.dataMappingName = (String) json.get("dataMappingName");
		this.elementName = (String) json.get("elementName");
		this.transformationType = (String) json.get("transformationType");
		this.transformationDetails = (JSONObject) json.get("transformationDetails");
		this.componentName = (String) json.get("componentName");
		this.prevComponentName = (String) json.get("prevComponentName");
		this.nextComponentName = (String) json.get("nextComponentName");
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject transformationObj = new JSONObject();
		transformationObj.put("dtoVersion", dtoVersion);
		transformationObj.put("dataMappingName", dataMappingName);
		transformationObj.put("elementName", elementName);
		transformationObj.put("transformationType", transformationType);
		transformationObj.put("transformationDetails", transformationDetails);
		transformationObj.put("componentName", componentName);
		transformationObj.put("prevComponentName", prevComponentName);
		transformationObj.put("nextComponentName", nextComponentName);
		return transformationObj;
	}
	
}
