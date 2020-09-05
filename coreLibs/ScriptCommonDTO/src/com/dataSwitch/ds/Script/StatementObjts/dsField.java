
package com.dataSwitch.ds.Script.StatementObjts;

import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;

public class dsField extends iStateDetail
{
	public String name;
	public String dataType;
	public int precision;
	public int scale;
	public boolean isKey;
	public boolean isNull;
	public String checkConstraint;
	public String defaultValue;
	public SQLExpr defaultValueExpr;

}