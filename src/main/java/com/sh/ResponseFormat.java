package com.sh;

public class ResponseFormat {
	private int statusCode;
	private String responseMessage;
	
	public int getStatusCode() {
		return statusCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	
	public void setStatusCode(int code) {
		statusCode = code;
	}
	public void getResponseMessage(String message) {
		responseMessage = message;
	}
}
