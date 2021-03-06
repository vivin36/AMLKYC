package com.blockchain.vo;

public class IDDARequestBodyVO {
	
	private ResultOnChainVO resultOnChain;
	private String senderId;
	private String receiverId;
	private String txId;
	private AmountVO senderAmount;
	private AmountVO receiverAmount;
	private String transCurrency;
	private String inputReferenceNo;
	private String extendInfo;
	private AmountVO amount;
	private String completedTime;
	
	public ResultOnChainVO getResultOnChain() {
		return resultOnChain;
	}
	public void setResultOnChain(ResultOnChainVO resultOnChain) {
		this.resultOnChain = resultOnChain;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	public AmountVO getSenderAmount() {
		return senderAmount;
	}
	public void setSenderAmount(AmountVO senderAmount) {
		this.senderAmount = senderAmount;
	}
	public AmountVO getReceiverAmount() {
		return receiverAmount;
	}
	public void setReceiverAmount(AmountVO receiverAmount) {
		this.receiverAmount = receiverAmount;
	}
	public String getTransCurrency() {
		return transCurrency;
	}
	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}
	public String getInputReferenceNo() {
		return inputReferenceNo;
	}
	public void setInputReferenceNo(String inputReferenceNo) {
		this.inputReferenceNo = inputReferenceNo;
	}
	public String getExtendInfo() {
		return extendInfo;
	}
	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public AmountVO getAmount() {
		return amount;
	}
	public void setAmount(AmountVO amount) {
		this.amount = amount;
	}
	public String getCompletedTime() {
		return completedTime;
	}
	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}	
}
