
package com.dataSwitch.ds.sql.SQLObjeds;

import java.util.ArrayList;

public class ExternalTable extends SQLObject
{
	public String tableAliasName = null;
	public ArrayList<SQLObject> colsList = new ArrayList<SQLObject>();
	public SQLObject dmlQuery = null;
	public String loopType = null;
}