
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OtherTransInfo  extends BaseTransInfo
{

	public String transformType = null;
	public String transformName = null;
	
	public ArrayList<ColumnDTO> inputCols = new ArrayList<ColumnDTO>();
	public ArrayList<ColumnDTO> outputCols = new ArrayList<ColumnDTO>();
	
}