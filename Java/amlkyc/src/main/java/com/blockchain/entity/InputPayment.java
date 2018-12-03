package com.blockchain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "IP")
public class InputPayment extends Payment {

	private static final long serialVersionUID = 1L;
	
	private String accountNumber;
	private String accountId;
	private String inputRefNumber;
	
	@Column(name="ACCOUNT_NUMBER")
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Column(name="ACCOUNT_ID")
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name="INPUT_REF_NUMBER")
	public String getInputRefNumber() {
		return inputRefNumber;
	}
	
	public void setInputRefNumber(String inputRefNumber) {
		this.inputRefNumber = inputRefNumber;
	}		
}
