
package com.dataSwitch.xml.ETLMapDTO;


public class ColumnDTO
{
	public ColumnStruct colStruct = null;

	public byte isBeingUsed = 'N';

	public OutRecDTO parentOutRec = null;

	public String aliasName = null;

	public String defaultValue = null;		//
	public ColumnDTO srcColumn = null;		// PassThrough Ports  (or)
		//  (or)
	public TransRuleDTO  transRule = null;	// Expression/ Aggregator /Other TransRules
	
}