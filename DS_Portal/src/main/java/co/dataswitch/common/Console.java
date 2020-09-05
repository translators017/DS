package co.dataswitch.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Console implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -204175294099338692L;
	List<String> messages;
	
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

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