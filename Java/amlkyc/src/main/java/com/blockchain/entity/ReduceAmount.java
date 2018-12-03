package com.blockchain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "RA")
public class ReduceAmount extends Payment {

	private static final long serialVersionUID = 1L;
	
	private String accountNumber;
	private String accountId;
	private String redeemRefNo;

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
	
	@Column(name="REDEEM_REF_NUMBER")
	public String getRedeemRefNo() {
		return redeemRefNo;
	}
	
	public void setRedeemRefNo(String redeemRefNo) {
		this.redeemRefNo = redeemRefNo;
	}	
}
