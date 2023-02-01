package com.ibm.pradip.ads;

import java.io.Serializable;

public class Email implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String destination;
	private String content;
	private String recipient;
	
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	
	

}
