package com.blockchain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TA")
public class TransferAmount extends Payment {

	private static final long serialVersionUID = 1L;
	
	private String receiverAccountId;
	private String receiverAccountNumber;
	private String senderAccountId;
	private String senderAccountNumber;
	
	@Column(name="RECEIVER_ACCOUNT_ID")
	public String getReceiverAccountId() {
		return receiverAccountId;
	}
	
	public void setReceiverAccountId(String receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
	
	@Column(name="RECEIVER_ACCOUNT_NUMBER")
	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	
	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}
	
	@Column(name="SENDER_ACCOUNT_ID")
	public String getSenderAccountId() {
		return senderAccountId;
	}
	
	public void setSenderAccountId(String senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	
	@Column(name="SENDER_ACCOUNT_NUMBER")
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
}
