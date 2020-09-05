
package com.dataSwitch.ds.Script.StatementObjts;

import java.util.ArrayList;

import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;
import com.dataSwitch.ds.Script.StatementObjts.dsCase;

public class dsSwitch extends iStateDetail
{
	public iStatement controlIdentifier;
	public ArrayList<dsCase> casesList = new ArrayList<>();
}