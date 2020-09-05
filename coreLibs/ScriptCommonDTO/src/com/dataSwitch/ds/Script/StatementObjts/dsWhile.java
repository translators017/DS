
package com.dataSwitch.ds.Script.StatementObjts;

import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class dsWhile extends iStateDetail
{
	public iStatement condition;
	public iBlockStatement whileBlock = null;
}