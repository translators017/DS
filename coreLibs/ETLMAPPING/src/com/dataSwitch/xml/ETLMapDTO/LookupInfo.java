
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class LookupInfo  extends BaseTransInfo
{
	public String cachingType = null;
	public String multiMatchPolicy = null;
	public boolean isResuable = false;
	public String lookupType = ""; // 		Connected/Unconnected

	
	public ArrayList<String> returnDataList = null;

	public ArrayList<ColumnDTO> returnCols = new ArrayList<ColumnDTO>();
	
	public ArrayList<ColumnDTO> masterCols = new ArrayList<ColumnDTO>();
	public ArrayList<ColumnDTO> lookUpCols = new ArrayList<ColumnDTO>();
	
	public ArrayList<LookupSourceDTO> lkpSrcList = new ArrayList<LookupSourceDTO>();
}