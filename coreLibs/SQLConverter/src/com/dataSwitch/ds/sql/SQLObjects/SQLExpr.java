
package com.dataSwitch.ds.sql.SQLObjeds;

import java.util.ArrayList;

public class SQLExpr extends SQLObject
{
      public boolean withInParanthesis;
      public String type;
      public String value = null;
      
      public ArrayList<SQLObject> args = new ArrayList<SQLObject>();
      public String aliasName = null;
}