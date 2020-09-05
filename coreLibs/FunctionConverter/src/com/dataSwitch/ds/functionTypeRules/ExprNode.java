
package com.dataSwitch.ds.functionTypeRules;

import java.util.LinkedHashMap;

public class ExprNode 
{

	  public char type = '1'; // 1 – Operand, 2-Operator, 3-Function Name
      public String value = null; 

      public LinkedHashMap<String,ExprNode> args = null; // Applicable for type 2 or 3 only
}