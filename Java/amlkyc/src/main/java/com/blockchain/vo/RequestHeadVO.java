package com.blockchain.vo;

public class RequestHeadVO extends HeadVO {

	private String requestTime;
	private String operation;
	
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}	
}
