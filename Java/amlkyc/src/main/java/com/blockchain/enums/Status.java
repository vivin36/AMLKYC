package com.blockchain.enums;

public enum Status {

	RECEIVED("R"), PENDING("P"), SUCCESS("S"), FAILURE("F");
	
	private String code;
	
	public String getCode() {
		return this.code;
	}
	
	Status(String code) {
		this.code = code;
	}
}
