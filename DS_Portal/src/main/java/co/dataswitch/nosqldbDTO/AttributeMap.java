package co.dataswitch.nosqldbDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.dataswitch.nosqldb.DBObject_new;


public class AttributeMap extends DBObject_new implements Serializable{

	
	private static final long serialVersionUID = -6595569108433116793L;
	int dtoVersion = 3;
	String targetCatalogName;
	List<sourceAttributeRef_json> srcAtrRefList;
	List<transformationRule_json> transformationRuleList;
	String targetEntityRef;
	String targetAttributeRef;
	String logicalTargetEntityRef;
	String logicalTargetAttributeRef;
	String derivedVarDetails;
	String targetCompName;
	
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



	public static class sourceAttributeRef_json extends DBObject_new implements Serializable{
		public int dtoVersion = 3;
		public String sourceCatalogName;
		public String sourceFeedRef;
		public String sourceDataElementRef;
		public String logicalSourceFeedRef;
		public String logicalSourceDataElementRef;
		public String sourceCompName;
		
		public String getSourceCompName() {
			return sourceCompName;
		}
		public void setSourceCompName(String sourceCompName) {
			this.sourceCompName = sourceCompName;
		}
		public String getSourceCatalogName() {
			return sourceCatalogName;
		}
		public void setSourceCatalogName(String sourceCatalogName) {
			this.sourceCatalogName = sourceCatalogName;
		}

		public String getSourceFeedRef() {
			return sourceFeedRef;
		}
		public void setSourceFeedRef(String sourceFeedRef) {
			this.sourceFeedRef = sourceFeedRef;
		}
		public String getSourceDataElementRef() {
			return sourceDataElementRef;
		}
		public void setSourceDataElementRef(String sourceDataElementRef) {
			this.sourceDataElementRef = sourceDataElementRef;
		}
		public String getLogicalSourceFeedRef() {
			return logicalSourceFeedRef;
		}
		public void setLogicalSourceFeedRef(String logicalSourceFeedRef) {
			this.logicalSourceFeedRef = logicalSourceFeedRef;
		}
		public String getLogicalSourceDataElementRef() {
			return logicalSourceDataElementRef;
		}
		public void setLogicalSourceDataElementRef(String logicalSourceDataElementRef) {
			this.logicalSourceDataElementRef = logicalSourceDataElementRef;
		}
		@Override
		public void setJsonData(JSONObject json) {
			int dataVersion = (int) (long) json.get("dtoVersion");
			this.sourceFeedRef = (String) json.get("sourceFeedRef");
			this.logicalSourceFeedRef = (String) json.get("logicalSourceFeedRef");
			this.sourceDataElementRef = (String) json.get("sourceDataElementRef");
			this.logicalSourceDataElementRef = (String) json.get("logicalSourceDataElementRef");
			if(dataVersion == 1)
			{
				this.sourceCatalogName = null;
				this.sourceCompName = (String) json.get("sourceFeedRef");
			}
			if(dataVersion == 2)
			{
				this.sourceCatalogName = (String) json.get("sourceCatalogName");
				this.sourceCompName = (String) json.get("sourceFeedRef");
			}
			if(dataVersion == 3)
			{
				this.sourceCatalogName = (String) json.get("sourceCatalogName");
				this.sourceCompName = (String) json.get("sourceCompName");
			}
		}
		@SuppressWarnings("unchecked")
		@Override
		public JSONObject getJsonData() {
			JSONObject srcRefObj = new JSONObject();
			srcRefObj.put("dtoVersion", dtoVersion);
			srcRefObj.put("sourceCatalogName", sourceCatalogName);
			srcRefObj.put("sourceFeedRef", sourceFeedRef);
			srcRefObj.put("logicalSourceFeedRef", logicalSourceFeedRef);
			srcRefObj.put("sourceDataElementRef", sourceDataElementRef);
			srcRefObj.put("logicalSourceDataElementRef", logicalSourceDataElementRef);
			srcRefObj.put("sourceCompName", sourceCompName);
			return srcRefObj;
		}
	}
	public static class transformationRule_json extends DBObject_new implements Serializable{
		int dtoVersion = 2;
		String ruleName;
		String componentName;
		String parameter;
		String prevComponentRef;
		String ruleDescription;
		String type;
		String ruledesctext;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getRuleDescription() {
			return ruleDescription;
		}
		public void setRuleDescription(String ruleDescription) {
			this.ruleDescription = ruleDescription;
		}
		public String getPrevComponentRef() {
			return prevComponentRef;
		}
		public void setPrevComponentRef(String prevComponentRef) {
			this.prevComponentRef = prevComponentRef;
		}
		public String getParameter() {
			return parameter;
		}
		public void setParameter(String parameter) {
			this.parameter = parameter;
		}
		public String getRuleName() {
			return ruleName;
		}
		public void setRuleName(String ruleName) {
			this.ruleName = ruleName;
		}
		public String getComponentName() {
			return componentName;
		}
		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}
		public String getRuledesctext() {
			return ruledesctext;
		}
		public void setRuledesctext(String ruledesctext) {
			this.ruledesctext = ruledesctext;
		}
		
		@Override
		public void setJsonData(JSONObject json) {
			int dataVersion = (int) (long) json.get("dtoVersion");
			this.ruleName = (String) json.get("ruleName");
			this.componentName = (String) json.get("componentName");
			this.parameter = (String) json.get("parameter");
			this.prevComponentRef = (String) json.get("prevComponentRef");
			if(dataVersion == 1){
				this.ruleDescription = "";
				this.type = "String";
			}
			if(dataVersion == 2){
				this.ruleDescription = (String) json.get("ruleDescription");
				this.type = (String) json.get("type");
			}
			if(dataVersion == 3){
				this.ruleDescription = (String) json.get("ruleDescription");
				this.type = (String) json.get("type");
				this.ruledesctext = (String) json.get("ruledesctext");
			}
		}
		@SuppressWarnings("unchecked")
		@Override
		public JSONObject getJsonData() {
			JSONObject trfmObj = new JSONObject();
			trfmObj.put("dtoVersion", dtoVersion);
			trfmObj.put("ruleName", ruleName);
			trfmObj.put("componentName", componentName);
			trfmObj.put("parameter", parameter);
			trfmObj.put("prevComponentRef", prevComponentRef);
			trfmObj.put("ruleDescription", ruleDescription);
			trfmObj.put("type", type);
			trfmObj.put("ruledesctext", ruledesctext);
			return trfmObj;
		}
	}
	
	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.targetEntityRef = (String) json.get("targetEntityRef");
		this.logicalTargetEntityRef = (String) json.get("logicalTargetEntityRef");
		this.targetAttributeRef = (String) json.get("targetAttributeRef");
		this.logicalTargetAttributeRef = (String) json.get("logicalTargetAttributeRef");
		this.derivedVarDetails = (String) json.get("derivedVarDetails");
		JSONParser parser = new JSONParser();
		try {
			org.json.simple.JSONArray sourceListArr = (org.json.simple.JSONArray) parser.parse(json.get("sourceListArr").toString());
			for(int p = 0; p < sourceListArr.size(); p++)
			{
				org.json.simple.JSONObject sourceListObj = (org.json.simple.JSONObject) sourceListArr.get(p);
				sourceAttributeRef_json srcRef = new sourceAttributeRef_json();
				srcRef.setJsonData(sourceListObj);
			}
			org.json.simple.JSONArray transformationRuleArr = (org.json.simple.JSONArray) parser.parse(json.get("transformationRuleArr").toString());
			for(int p = 0; p < transformationRuleArr.size(); p++)
			{
				org.json.simple.JSONObject transformationRuleObj = (org.json.simple.JSONObject) transformationRuleArr.get(p);
				transformationRule_json trfm = new transformationRule_json();
				trfm.setJsonData(transformationRuleObj);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(dataVersion == 1)
		{
			this.targetCatalogName = null;
			this.targetCompName = (String) json.get("targetEntityRef");
		}
		if(dataVersion == 2)
		{
			this.targetCatalogName = (String) json.get("targetCatalogName");
			this.targetCompName = (String) json.get("targetEntityRef");
		}
		if(dataVersion == 3)
		{
			this.targetCatalogName = (String) json.get("targetCatalogName");
			this.targetCompName = (String) json.get("targetCompName");
		}
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
		if(this.getTransformationRuleList()!=null && this.getTransformationRuleList().size() > 0){
			for (int i = 0; i < this.getTransformationRuleList().size(); i++){
				JSONObject trfmObj = this.getTransformationRuleList().get(i).getJsonData();
				trfmArray.add(trfmObj);
			}
		}
		
		if(this.getSrcAtrRefList()!=null && this.getSrcAtrRefList().size() > 0){
			for(int i = 0; i < this.getSrcAtrRefList().size(); i++){
				JSONObject srcRefObj = this.getSrcAtrRefList().get(i).getJsonData();
				srcRefArray.add(srcRefObj);
			}
		}
		attributeMapObj.put("transformationRuleArr", trfmArray);
		attributeMapObj.put("sourceListArr", srcRefArray);
		return attributeMapObj;
	}
}
