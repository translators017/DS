
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class JoinerInfo extends BaseTransInfo
{
	public String joinType = null; // Inner Join, Outer Join, ...
	public boolean useSortedInput = false;

	public ArrayList<String> masterElemList = null;
	public ArrayList<String> detailElemList = null;

	public ArrayList<ColumnDTO> masterCols = new ArrayList<ColumnDTO>();
	public ArrayList<ColumnDTO> detailCols = new ArrayList<ColumnDTO>();
}