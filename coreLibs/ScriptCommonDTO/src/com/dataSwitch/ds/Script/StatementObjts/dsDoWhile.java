
package com.dataSwitch.ds.Script.StatementObjts;

import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class dsDoWhile extends iStateDetail
{
	public iStatement condition;
	public iBlockStatement doBlock = null;
}