
package com.dataSwitch.ds.sql.SQLObjeds;

import java.util.ArrayList;

public class SQLQuery extends SQLObject
{

	public boolean withInParanthesis = false;
	public String dmlType;
	public boolean hiddenSQL = false;
	public String predicate = null;
	public int currentDbId;
	public int newDbId;
	
	public ArrayList<SQLObject> colsList = new ArrayList<SQLObject>();
	public ArrayList<SQLObject> tableList = new ArrayList<SQLObject>();
	
	public SQLPartition partition = null;
	public SQLExpr whereCond = null;
	public SQLJoin joinCond = null;
	public SQLExpr havingCond = null;
	
	public ArrayList<SQLObject> groupByCols = new ArrayList<SQLObject>();
	public ArrayList<SQLObject> OrderBy = new ArrayList<SQLObject>(); 
	
	public ArrayList<SQLObject> updateCols = new ArrayList<SQLObject>(); 
	
	public SQLUnion setOperation = null;
	public String aliasName = null;
	
	public String exprCompName;
	public String aggrCompName;
	public String sortCompName;
	
	public ArrayList<SQLObject> externalTables = new ArrayList<SQLObject>();
	
	public SQLExpr callProcOrFunc = null;
	
	public DDLQuery ddlQuery = null;
	
} 