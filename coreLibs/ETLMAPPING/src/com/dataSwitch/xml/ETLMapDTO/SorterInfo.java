
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class SorterInfo extends BaseTransInfo
{
	public ArrayList<String> keyNamesList = new ArrayList<String>();
	public ArrayList<String> sortOrdersList = new ArrayList<String>();

	public ArrayList<ColumnDTO> keyCols = new ArrayList<ColumnDTO>();
}