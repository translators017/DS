
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;
import java.util.List;

public class TransRecDTO  {

	public String compName = null;
	public String transType = null; // Source, Target, LookupSource, Exp, Agg, Lkp, RTR, ...

	public String identifier = null;

	public String xLevelPos = "-1"; //multiply by 200
	public String yLevelPos = "100"; //multiply by 100


	// create that prevComp and add that to previousCompList
	public List<InRecDTO> inRecsList =   new ArrayList<InRecDTO>();;
	public List<OutRecDTO> outRecsList = new ArrayList<OutRecDTO>();

	public String setOperation = null; // Union All
	public BaseTransInfo  transInfo = null;
	
	public String rejectOption = null;
	
	public boolean ignoreCodegen = false;
	 
}