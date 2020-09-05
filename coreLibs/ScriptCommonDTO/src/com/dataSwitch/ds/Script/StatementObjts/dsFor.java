
package com.dataSwitch.ds.Script.StatementObjts;

import java.util.ArrayList;

import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class dsFor extends iStateDetail
{
	public ArrayList<iStatement> initializeSt = new ArrayList<iStatement>();
	public iStatement condition;
	public ArrayList<iStatement> incrementList = new ArrayList<iStatement>();
	public iBlockStatement forBlock = null;
}