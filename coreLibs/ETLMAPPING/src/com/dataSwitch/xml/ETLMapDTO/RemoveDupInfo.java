
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class RemoveDupInfo extends BaseTransInfo
{
	public ArrayList<ColumnDTO> keyCols = new ArrayList<ColumnDTO>();
	public ArrayList<String> keyColsList = new ArrayList<String>();
	public ArrayList<String> FirstOrLastValueList = new ArrayList<String>();
}