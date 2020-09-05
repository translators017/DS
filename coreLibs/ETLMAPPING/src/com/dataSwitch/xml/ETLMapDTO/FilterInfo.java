
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class FilterInfo extends BaseTransInfo
{
	public String filterCond = null;

	public ArrayList<ColumnDTO> conditionCols = new ArrayList<ColumnDTO>();

}