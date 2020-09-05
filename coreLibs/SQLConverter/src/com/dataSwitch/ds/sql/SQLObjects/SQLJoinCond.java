
package com.dataSwitch.ds.sql.SQLObjeds;

import java.util.ArrayList;

public class SQLJoinCond extends SQLObject
{
   public SQLObject mstrTable = null;
   public SQLObject childTable = null;
   public SQLObject mstrElem = null;
   public SQLObject childElem = null;
   public SQLObject operator = null;
   public String condValue = null;
}