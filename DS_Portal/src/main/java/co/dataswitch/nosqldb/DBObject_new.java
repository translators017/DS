
package co.dataswitch.nosqldb;

import java.io.Serializable;

import org.json.simple.JSONObject;

	public abstract class DBObject_new implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int dtoVersion;
	
	public abstract void setJsonData(JSONObject json);
	
	public abstract JSONObject getJsonData();
	
	
	
}