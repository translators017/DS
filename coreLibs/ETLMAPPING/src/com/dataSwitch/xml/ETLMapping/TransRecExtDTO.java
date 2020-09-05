
package com.dataSwitch.xml.ETLMapping;

import java.util.*;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;

public class TransRecExtDTO extends TransRecDTO {

	public int seqNo = -1;

	public ArrayList<ColumnDTO> transColsList = new ArrayList<ColumnDTO>();

	public boolean isProcessed = false;

	public List<String> specialProp = new ArrayList<String>();

	public List<String> previousComp = new ArrayList<String>();
	public List<String> nextComp = new ArrayList<String>();
	public List<TransRecExtDTO> prevCompList = new ArrayList<TransRecExtDTO>();
	public List<TransRecExtDTO> nextCompList = new ArrayList<TransRecExtDTO>();
	public List<TransRecExtDTO> mergerdTransRecs = new ArrayList<TransRecExtDTO>();

}