
package com.dataSwitch.ds.Script.StatementObjts;

import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;

public class dsException extends iStateDetail
{
	public String exceptionName;
	public String exceptionMessage;
	
	public iStatement condition;
	public iBlockStatement thenBlock = null;
	
}