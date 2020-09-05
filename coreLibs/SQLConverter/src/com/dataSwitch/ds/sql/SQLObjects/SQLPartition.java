
package com.dataSwitch.ds.sql.SQLObjeds;

import java.util.ArrayList;

public class SQLPartition extends SQLObject
{
    public ArrayList<SQLObject> colsList = new ArrayList<SQLObject>();
    public ArrayList<String> partitionValue = new ArrayList<String>();
}