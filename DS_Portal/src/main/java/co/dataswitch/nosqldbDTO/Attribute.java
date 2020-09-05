package co.dataswitch.nosqldbDTO;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import co.dataswitch.nosqldb.DBObject_new;


public class Attribute extends DBObject_new {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4587374979773576712L;
	int dtoVersion = 5;
	String physicalName;
	String logicalName;
	String description;
	String datatype;
	String length;
	String precision;
	String length_precision;
	String scale;
	String nullable;
	String dataFormat;
	String defaultValue;
	String checkConstraint;
	String keyType;
	String xpath;
	List<ReferencedAttributes> refAttribList;
	String primaryTableName;
	String primaryColumnName;
	
	public String getPhysicalName() {
		return physicalName;
	}
	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}
	public String getLogicalName() {
		return logicalName;
	}
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getLength_precision() {
		return length_precision;
	}
	public void setLength_precision(String length_precision) {
		this.length_precision = length_precision;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public String getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getCheckConstraint() {
		return checkConstraint;
	}
	public void setCheckConstraint(String checkConstraint) {
		this.checkConstraint = checkConstraint;
	}

	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	

	public List<ReferencedAttributes> getRefAttribList() {
		if(refAttribList == null)
		{
			refAttribList = new ArrayList<ReferencedAttributes>();
		}	
		return refAttribList;
	}
	public void setRefAttribList(List<ReferencedAttributes> refAttribList) {
		this.refAttribList = refAttribList;
	}
	public String getxpath() {
		return xpath;
	}
	public void setxpath(String xpath) {
		this.xpath = xpath;
	}
	
	public String getPrimaryTableName() {
		return primaryTableName;
	}
	public void setPrimaryTableName(String primaryTableName) {
		this.primaryTableName = primaryTableName;
	}
	public String getprimaryColumnName() {
		return primaryColumnName;
	}
	public void setprimaryColumnName(String primaryColumnName) {
		this.primaryColumnName = primaryColumnName;
	}
	
	@Override
	public void setJsonData(JSONObject json) {
		int dataVersion = (int) (long) json.get("dtoVersion");
		this.physicalName = (String) json.get("physicalName");
		this.logicalName = (String) json.get("logicalName");
		this.description = (String) json.get("description");
		this.datatype = (String) json.get("datatype");
		this.scale = (String) json.get("scale");
		this.nullable = (String) json.get("nullable");
		this.dataFormat = (String) json.get("dataFormat");
		this.defaultValue = (String) json.get("defaultValue");
		this.checkConstraint = (String) json.get("checkConstraint");
		this.keyType = (String) json.get("keyType");
			
		
		if(dataVersion == 5)
		{
			this.length_precision = (String) json.get("length_precision");		
			this.xpath = (String) json.get("xpath");
			this.primaryTableName = (String) json.get("primaryTableName");
			this.primaryColumnName = (String) json.get("primaryColumnName");
		}
		if(dataVersion <= 4)
		{
			try {
				JSONArray refAttribArray = (JSONArray) json.get("refAttribArray");
				for(int p = 0; p < refAttribArray.size(); p++)
				{
					org.json.simple.JSONObject refAttribObj = (org.json.simple.JSONObject) refAttribArray.get(p);
					ReferencedAttributes refAttrib = new ReferencedAttributes();
					refAttrib.setJsonData(refAttribObj);
					this.getRefAttribList().add(refAttrib);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			this.length = (String) json.get("length");
			this.precision = (String) json.get("precision");
			if(this.length != null && this.length.equalsIgnoreCase("")) {
				this.length_precision = (String) json.get("length");
			}
			if(this.precision != null && this.precision.equalsIgnoreCase("")) {
				this.length_precision = (String) json.get("precision");
			}
			
			
			this.xpath = (String) json.get("xpath");
			this.primaryTableName = (String) json.get("primaryTableName");
			this.primaryColumnName = (String) json.get("primaryColumnName");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJsonData() {
		JSONObject attribJsonObj = new JSONObject();
		attribJsonObj.put("dtoVersion", dtoVersion);
		attribJsonObj.put("physicalName",physicalName);
		attribJsonObj.put("logicalName",logicalName);
		attribJsonObj.put("description",description);
		attribJsonObj.put("datatype",datatype);
		attribJsonObj.put("length",length);
		attribJsonObj.put("precision",precision);
		attribJsonObj.put("length_precision",length_precision);
		attribJsonObj.put("scale",scale);
		attribJsonObj.put("nullable",nullable);
		attribJsonObj.put("dataFormat",dataFormat);
		attribJsonObj.put("defaultValue",defaultValue);
		attribJsonObj.put("checkConstraint",checkConstraint);
		attribJsonObj.put("keyType",keyType);
		attribJsonObj.put("xpath",xpath);
		attribJsonObj.put("primaryTableName",primaryTableName);
		attribJsonObj.put("primaryColumnName",primaryColumnName);
		JSONArray refAttribJsonArray = new JSONArray();
		if(this.getRefAttribList()!=null && this.getRefAttribList().size() > 0){
			for (int i = 0; i < this.getRefAttribList().size(); i++){
				JSONObject refAttribJsonObj = this.getRefAttribList().get(i).getJsonData();
				refAttribJsonArray.add(refAttribJsonObj);
			}
		}
		attribJsonObj.put("refAttribArray", refAttribJsonArray);
		return attribJsonObj;
	}


}
