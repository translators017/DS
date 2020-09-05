
package com.dataSwitch.ds.Script.StatementObjts;

import java.util.ArrayList;

import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iStateDetail;

public class iStatement 
{
//	enum statType type;
	
	public StatType type;
    public int originalLineNo;
    
    public byte isBeingUsed = 78;
    
    public ArrayList<Token> tokenList = new ArrayList<Token>();
    
    public iStateDetail statDetail;
    
    public String originalLine;
    
}
