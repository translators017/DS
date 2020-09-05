
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class UnionInfo extends BaseTransInfo
{
	public ArrayList<String> inputGroup = new ArrayList<String>();

	public ArrayList<ColumnDTO> colDTOList = new ArrayList<ColumnDTO>();

	public String operation = "";
}