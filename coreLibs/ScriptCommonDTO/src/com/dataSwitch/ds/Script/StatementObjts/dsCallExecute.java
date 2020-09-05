
package com.dataSwitch.ds.Script.StatementObjts;

import java.util.ArrayList;

import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;


public class dsCallExecute extends iStateDetail
{
	public StatType type; // Call, Exec
	public String procName;
	public ArrayList<SQLExpr> argsList =  new ArrayList<SQLExpr>();
}