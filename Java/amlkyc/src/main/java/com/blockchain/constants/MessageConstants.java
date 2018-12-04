package com.blockchain.constants;

public interface MessageConstants {
	
	String INPUT_PAYMENTS_MESSAGE_SUCCESS = "Amount credited successfully";
	
	String INPUT_PAYMENTS_MESSAGE_FAILURE = "Account Number is blacklisted!";
	
	String TRANSFER_FAILURE_MESSAGE_BENEFICIARY = "Beneficiary account number is blacklisted!";
	
	String TRANSFER_FAILURE_MESSAGE_REMITTER = "Remitter account number is blacklisted!";
	
	String TRANSFER_SUCCESS_MESSAGE = "Amount transferred successfully!";
	
	String REDUCE_AMOUNT_FAILURE_MESSAGE = "Account number is blacklisted!";
	
	String REDUCE_AMOUNT_SUCCESS_MESSAGE = "Amount debited successfully!";
}