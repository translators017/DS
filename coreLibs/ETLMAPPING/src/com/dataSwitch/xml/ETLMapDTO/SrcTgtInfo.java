
package com.dataSwitch.xml.ETLMapDTO;

import java.util.*;


public class SrcTgtInfo extends BaseTransInfo
{
	public String dbType = null; // Oracle, SQLServer, ..., FlatFile-Fixed, Delimited

	public ArrayList<ColumnDTO> srcTgtColsList = new ArrayList<ColumnDTO>();

	public ArrayList<String> transDtls = new ArrayList<String>();
	
	public ArrayList<String> specialProp = new ArrayList<String>();

	
	// DB props
	public String tableName = null;
	public String schemaName = null; //DB 
	public String connectionName = null; //DB 
	public String source_Desc = null;
	public String catalogName = null;
	
	// File Props
	public String fileType = null; // Fixed Width, Delimited, hashFile
	public String fileExtension = null;
	public String directoryPath = null;
	public String delimiter = null;
	
	//NOSQL DB Props
	public ArrayList<String> recordMarkerList = new ArrayList<String>();


	// Queries
	public String overrideSQL = null;
	public String filterSQL = null;
	public String insertSQL = null;
	public String updateSQL = null;
	
	//PRESQL & PostSQL
	public String preSQL = null;
	public String postSQL = null;

	public String loadStrategy = null;
	
	public String primarySystemEntityRefRef = null;
	public String joinCondition = null;
	
	public ArrayList<String> updateCols = new ArrayList<String>();
	public ArrayList<String> updateKeyCols = new ArrayList<String>();


}