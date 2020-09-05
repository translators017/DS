
package com.dataSwitch.ds.sql.SQLObjeds;

import java.util.ArrayList;

public class DDLQuery extends SQLObject
{
	public SQLObject tableName = null;;
	public ArrayList<SQLObject> colsList = new ArrayList<SQLObject>();
	
	public String otherDetails = null;
	public String tableType = null;
}