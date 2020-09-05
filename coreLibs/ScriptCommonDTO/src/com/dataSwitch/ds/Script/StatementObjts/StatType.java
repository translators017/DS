
package com.dataSwitch.ds.Script.StatementObjts;

public enum StatType{
	BLANKSTAT("dsBlank"),
	COMMENTSTAT("dsComment"),
	FORLOOP("dsFor"),
	BEGINBLOCK("dsBegin"),
	LOOPBLOCK("dsLoop"),
    IFBLOCK("dsIf"),
    ELSEBLOCK("dsElse"),
    ELSEIFBLOCK("dsElseIf"),
    THENBLOCK("dsThen"),
	SQLQUERY("dsSQLQuery"),
	UNKNOWNSTAT("dsUnKwnStmt"),
	DECLARESTAT("dsDeclaration"),
	CONDSTAT("dsCondition"),
	ARGSSTAT("dsArguments"),
	PROCSTAT("dsProc"),
	WHILELOOP("dsWhile"),
	DOBLOCK("dsDoBlock"),
	CALLSTAT("dsCallExecute"),
	LOCKACCESS("dsLockAccess"),
	ASSGNSTAT("dsAssign"),
	COMMIT("dsCommit"),
	CURSOR("dsCurser"),
	EXCEPTIONBLOCK("dsException"),
	WHEN("dsWhen"),
	RAISE("dsRaise"),
	START("dsStart"),
	EXECUTE("dsExecute"),
	END("dsEnd");
	
    public final String statType;
    private StatType(String statType) {
        this.statType = statType;
    }
    @Override
    public String toString(){
        return String.format(statType);
    }
    
}