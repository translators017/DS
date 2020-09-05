
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class RankInfo  extends BaseTransInfo {

	public String rankNumber = ""; 

	public String rankFlag = "";
	public String rankPort = "";
	
	public ArrayList<String> rankGroupElemList = new ArrayList<String>();
	public ArrayList<ColumnDTO> colDTOList = new ArrayList<ColumnDTO>();

//	public ArrayList<ColumnDTO> rankGroupCols = new ArrayList<ColumnDTO>();
}