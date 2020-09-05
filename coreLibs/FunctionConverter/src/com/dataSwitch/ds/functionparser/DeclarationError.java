
package com.dataSwitch.ds.functionparser;

public class DeclarationError extends Exception{
    public DeclarationError(){
        super();
    }
    public DeclarationError(String s){
        super(s);
    }
}