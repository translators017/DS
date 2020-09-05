
package com.dataSwitch.ds.Script.StatementObjts;

import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class dsCatch extends iStateDetail
{
	public iStatement catchCondition;
	public iBlockStatement catchBlock = null;
}