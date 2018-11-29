package com.blockchain.vo;

public class WrappedResponseVO {
	
	private ResponseVO response;
	private String signature;
	
	public ResponseVO getResponse() {
		return response;
	}

	public void setResponse(ResponseVO response) {
		this.response = response;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}	
}
