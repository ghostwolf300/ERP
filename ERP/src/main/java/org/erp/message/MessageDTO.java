package org.erp.message;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static enum Type{
		SUCCESS,
		FAILURE,
		WARNING,
		INFO
	}
	
	private int id;
	private Timestamp ts;
	private Type type;
	private String messageText;
	
	public MessageDTO() {
		
	}
	
	public MessageDTO(int id,Timestamp ts,Type type,String messageText) {
		this.id=id;
		this.ts=ts;
		this.type=type;
		this.messageText=messageText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type success) {
		this.type = success;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	
}
