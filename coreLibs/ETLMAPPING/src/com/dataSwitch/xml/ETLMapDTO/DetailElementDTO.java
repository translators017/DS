
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class DetailElementDTO
{
	public String detailCompRef = null; // Inner Join, Outer Join, ...
	public String joinType = null;
	public boolean useSortedInput = false;

	public ArrayList<String> masterElemList = new ArrayList<String>();
	public ArrayList<String> detailElemList = new ArrayList<String>();

	public ArrayList<ColumnDTO> masterCols = new ArrayList<ColumnDTO>();
	public ArrayList<ColumnDTO> detailCols = new ArrayList<ColumnDTO>();
}