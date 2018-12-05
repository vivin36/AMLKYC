package com.blockchain.vo;

public class WrappedIDDANotificationVO {
	
	private IDDANotificationVO request;
	private String signature;
	
	public IDDANotificationVO getRequest() {
		return request;
	}
	public void setRequest(IDDANotificationVO request) {
		this.request = request;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
