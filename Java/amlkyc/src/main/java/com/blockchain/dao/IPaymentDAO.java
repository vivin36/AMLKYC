package com.blockchain.dao;

import java.util.List;

import com.blockchain.entity.TransferAmount;

public interface IPaymentDAO extends IGenericDAO<TransferAmount, Long> {

	public TransferAmount saveTransferAmount(TransferAmount transferAmount);
	
	public TransferAmount updateTransferAmount(TransferAmount transferAmount);

	public List<TransferAmount> getAllTransferAmountByStatus(String status);	
}
