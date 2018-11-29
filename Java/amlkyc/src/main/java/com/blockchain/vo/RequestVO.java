package com.blockchain.vo;

public class RequestVO {
	
	private RequestHeadVO head;
	private RequestBodyVO body;
	
	public RequestHeadVO getHead() {
		return head;
	}
	public void setHead(RequestHeadVO head) {
		this.head = head;
	}
	public RequestBodyVO getBody() {
		return body;
	}
	public void setBody(RequestBodyVO body) {
		this.body = body;
	}
}
