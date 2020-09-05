

package com.dataSwitch.ds.functionparser;

public enum TokenType{
	BEGINNING("^begin:"),
    END("^end:"),
    COMMENT("^#.*\n$"),
    VARJAP("(?:[\\u4E00-\\u9FAF_]+)"),                   //http://www.unicode.org/charts/index.html  *** To refer uniode characters for all the languages
    INFAUDF("[A-Za-z0-9_]*+:+(UDF+(?:\\.\\w+)+)"),
    INFAUNLKP("[A-Za-z0-9_]*+:+([LKP]*+[lkp]*+(?:\\.\\w+)+)"),
    INFAUNSP("[A-Za-z0-9_]*+:+([SP]*+[sp]*+(?:\\.\\w+)+)"),
    WHITESPACE("[ \t\f\r\n]+"),
    INTDECLARATION("int "),
   // VARWITHNOT("[A-Za-z]+[']+[A-Za-z]"),
    INTEGERWITHDECIMAL("(\\+|-)?([0-9]+(\\.[0-9]+))"),
    APOSSTRCONSTANTS("‘([^\\\\’]+|\\.)*’|’[A-Za-z0-9_]’|’[A-Za-z0-9_]'"),
    VARWITHDOTQUOTES("[\"]+[A-Za-z0-9_]+[\"]+[.]+[[.]*[\"][A-Za-z0-9_][\"]]*"),
    STRCONSTANTS("\"([^\\\"]+|\\.)*\"|'([^\\\']+|\\.)*'|`([^\\`]+|\\.)*`"),
    VARWITHDOTBRACKET("[\\[]+[A-Za-z0-9_]+[\\]]+[.]+[[.]*[\\[][A-Za-z0-9_][\\]]]*"),
   // VARWITHDOT("[A-Za-z0-9_]+[.]+[[.]*+[A-Za-z0-9_]+]*]*"),
    VARWITHDOT("[%]*[\\[]*+[A-Za-z0-9_]+[\\]]*+[.]+[[.]*[\\[]*[\"]*[A-Za-z0-9_][\\]]*[\"]*]*"),
    VARCURLYBRACES("\\$\\{(.*?)\\}+[?:\\.[$]*+[{]*+[A-Za-z0-9_]+[}]*]*"),
    VARWITHDOLLAR("[$]+[$]*+[A-Za-z]+[A-Za-z0-9_]*+[?:\\.+[$]*+[$]*+[A-Za-z0-9_]]*"),
    VARWITHAT("[A-Za-z0-9_]*+[@]+[A-Za-z]+[A-Za-z0-9_]*"),
    VARWITHPERCENT("[%]+[A-Za-z]+[A-Za-z0-9_]*"),
    BOOLOP("\\+=|\\-=|<>|<=|>=|<|>|!=|==|^="),
   // LOGICALOP("AND|And|Or|OR|dsAnd|dsOR|dsNot|dsConcat|and|or|XOR|&&|NOT|not|Not|!|&|:|[||]{2}|\\|"),
   // VARWITHDOT("(\\w+(?:\\.\\w+)+)"),
    TIMEFORMAT("(\\+|-)?([0-9]+(\\:[0-9]+)+)"),
    //DATEFORMAT("(\\+|-)?([0-9]+([/]*+[-]*+[0-9]+)+([/]*+[-]*+[0-9]+))"),
    DATEFORMAT("(\\\\+|-)?([0-9]*[A-Za-z0-9_]*+([/|-][[0-9]*[A-Za-z0-9_]*]+)([/|-][[0-9]*[A-Za-z0-9_]*]+))"),
    LOGICALOP("&&|!|&|:|[||]{2}|\\|"),
    INTEGER("-?[0-9]+"),
    VARWITHSTAR("[A-Za-z0-9_]+[.]+[*]"),
    VAR("[\\[]*[.]*+(?:\\w+)+[\\]]*"),
    SPLCHARS("[?.[#]*]+"),
    JOINTYPE("\\(\\+\\)"),
    OPENPAREN("[\\(]"),
    OPENBRACKET("[\\[|\\{]"),
    CLOSEPAREN("[\\)]"),
    CLOSEBRACKET("[\\]|\\}]"),
    BINARYOP("[*|/|+|\\-|=|%]"),
    DECSEP(","),
    EOL(";");
    public final String pattern;
    private TokenType(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public String toString(){
        return String.format(pattern);
    }
    
}