
package com.dataSwitch.ds.sql.SQLObjeds;

import java.util.ArrayList;

public class SQLJoin extends SQLObject
{
	public String joinType = null;
	public ArrayList<SQLObject> joinCond = new ArrayList<SQLObject>();
	public SQLObject joinCondDtls = null;
	
	public String masterExprCompName;
	public String detailExprCompName;
	public String masterFilterCompName;
	public String detailFilterCompName;
	
	public SQLJoin multiJoinTbl = null;
}