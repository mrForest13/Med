package com.app.transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class TransactionScript {

	private HttpServletRequest request; 
	private HttpServletResponse response;

	public TransactionScript(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	public TransactionScript() {}
	
	public abstract void run() throws Exception;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
}
