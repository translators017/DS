
package com.dataSwitch.xml.ETLMapDTO;

import com.dataSwitch.xml.ETLMapDTO.TransRecDTO;

public class InRecDTO {

	public final static int LINK_TYPE_MASTER = 1; // Master or Primary Link
	public final static int LINK_TYPE_DETAIL = 2; // Lookup or Joiner Source or Reference
	public final static int LINK_TYPE_REJECT = 3; // Reject Link

	public String name = null;
	public String identifier = null;
	public String partner;
	public int linkType;	// 1 - Primary, 2 - Lookup/Reference/Join,    3- Reject Link	
	public int inGrpOrder; // ?..

	public TransRecDTO parentTransRec = null;

	public OutRecDTO prevOutRec = null; // Refer Columns
}