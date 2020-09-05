
package com.dataSwitch.ds.Script.StatementObjts;

import java.util.ArrayList;

import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class dsProc extends iStateDetail
{
	public boolean create;
	public boolean replace;
	public int type; // Procedure, Function, Package
	public String name;
	public String returnDataType;
	public ArrayList<iStatement> argsList = new ArrayList<iStatement>();
	public iBlockStatement procBlockStatements = null;
	public iBlockStatement asBlock = null;
	public int accessType; // public, protected,private
	public boolean virtual;
//	boolean abstract;
}