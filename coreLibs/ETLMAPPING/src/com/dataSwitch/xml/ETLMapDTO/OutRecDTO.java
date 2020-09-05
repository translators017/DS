
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;

public class OutRecDTO {
	

	public String name = null;
	public String identifier = null;
	public String partner;
	
	public TransRecDTO parentTransRec = null;
	public int outRecGroupIndex = 1;

	public ArrayList<ColumnDTO> columnList =   new ArrayList<ColumnDTO>();

	public InRecDTO nextInRec = null;
	//For OutputRecs
	public String yLevelPos = "-1"; //multiply by 100
	
}