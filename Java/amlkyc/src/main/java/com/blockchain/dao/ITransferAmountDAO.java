package com.blockchain.dao;

import java.util.List;

import com.blockchain.entity.TransferAmount;

public interface ITransferAmountDAO extends IGenericDAO<TransferAmount, Long> {

	public List<TransferAmount> getAllTransferAmountByStatus(String status);
}
