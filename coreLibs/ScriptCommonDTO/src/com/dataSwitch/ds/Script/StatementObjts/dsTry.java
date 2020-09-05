
package com.dataSwitch.ds.Script.StatementObjts;

import java.util.ArrayList;

import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.dsCatch;

public class dsTry extends iStateDetail
{
	public iBlockStatement tryBlock = null;
	ArrayList<dsCatch> catchList = new ArrayList<>();
	public iBlockStatement finallyBlock = null;
}