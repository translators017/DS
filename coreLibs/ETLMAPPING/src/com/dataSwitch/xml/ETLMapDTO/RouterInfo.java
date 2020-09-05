
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouterInfo extends BaseTransInfo
{
	//public HashMap<String, OutRecDTO> grpOutRecList = new HashMap<String, OutRecDTO>();
	
	public HashMap<String, List<OutRecDTO>> grpOutRecList = new HashMap<String,List<OutRecDTO>>();

	public ArrayList<String> grpNamesList = new ArrayList<String>();
	public ArrayList<String> routerCondList = new ArrayList<String>();
	public ArrayList<ArrayList<ColumnDTO>> grpConditionCols = new ArrayList<ArrayList<ColumnDTO>>();
	
}