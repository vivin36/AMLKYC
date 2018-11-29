package com.blockchain.vo;

public class WrappedRequestVO {
	
	private RequestVO request;
	private String signature;

	public RequestVO getRequest() {
		return request;
	}

	public void setRequest(RequestVO request) {
		this.request = request;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}	
}
