
package com.dataSwitch.ds.sql.SQLRevEnggDTO;

import java.util.ArrayList;

import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;

public class sqlDTO  
{
	public boolean subQuery = false;
    public String compName = null;
    public String nextCompName = null;
    public String originalTableName = null;
    public String transType = null;
    public String subSQLType = null;
    public String aliasName = null;
    public ArrayList<SQLObject> sqlElems = new ArrayList<SQLObject>(); 
    public ArrayList<sqlDTO> sqlDtoList = new ArrayList<sqlDTO>();
}