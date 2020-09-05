
package com.dataSwitch.ds.coreLib.util;

import java.util.ArrayList;
import java.util.List;

public class Console{
	List<String> messages;
	
	public Console(){
		this.messages = new ArrayList<String>();
	}
	
	public void put(String msgType,String msg){
		this.messages.add(msgType + ":::" + msg);
	}
	
	public List<String> get(){
		return this.messages;
	}	
}