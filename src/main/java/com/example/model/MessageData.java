package com.example.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageData implements Serializable {
	
	private Integer messageId;
	
	private String message_desc;
	
	private String message;
	
	private Timestamp date;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessage_desc() {
		return message_desc;
	}

	public void setMessage_desc(String message_desc) {
		this.message_desc = message_desc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "MessageData [getMessageId()=" + getMessageId() + ", getMessage_desc()=" + getMessage_desc()
				+ ", getMessage()=" + getMessage() + "]";
	}
	
}
