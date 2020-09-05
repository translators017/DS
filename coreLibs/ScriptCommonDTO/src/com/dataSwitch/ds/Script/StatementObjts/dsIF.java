
package com.dataSwitch.ds.Script.StatementObjts;

import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class dsIF extends iStateDetail
{
	public iStatement condition;
	public iBlockStatement elseIfBlock = null;
	public iBlockStatement thenBlock = null;
	public iBlockStatement elseBlock = null;
}