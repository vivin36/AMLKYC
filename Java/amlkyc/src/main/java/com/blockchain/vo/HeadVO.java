package com.blockchain.vo;

public abstract class HeadVO {
	
	private String version;
	private String description;
	private String clientId;
	private String requestMsgId;
	private String reserve;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getRequestMsgId() {
		return requestMsgId;
	}
	public void setRequestMsgId(String requestMsgId) {
		this.requestMsgId = requestMsgId;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}	
}
