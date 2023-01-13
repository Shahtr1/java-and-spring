package com.example.demo;

import java.util.Date;

public class ExceptionResonse {
	private Date timestamp;
	private String message;
	private String details;
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public ExceptionResonse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	
}
