package com.blockchain.vo;

public class RequestBodyVO {

	private String senderAccountNumber;
	private String senderAccountId;
	private String receiverAccountNumber;
	private String receiverAccountId;
	private String transactionId;
	private String accountNumber;
	private String accountId;
	private String redeemReferenceNo;
	private AmountVO amount;
	private String transTime;
	private String extendInfo;
	private String inputReferenceNo;
	
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	public String getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(String senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}
	public String getReceiverAccountId() {
		return receiverAccountId;
	}
	public void setReceiverAccountId(String receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getRedeemReferenceNo() {
		return redeemReferenceNo;
	}
	public void setRedeemReferenceNo(String redeemReferenceNo) {
		this.redeemReferenceNo = redeemReferenceNo;
	}
	public AmountVO getAmount() {
		return amount;
	}
	public void setAmount(AmountVO amount) {
		this.amount = amount;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getInputReferenceNo() {
		return inputReferenceNo;
	}
	public void setInputReferenceNo(String inputReferenceNo) {
		this.inputReferenceNo = inputReferenceNo;
	}
	
}
