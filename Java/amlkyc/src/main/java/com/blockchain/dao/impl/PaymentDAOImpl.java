package com.blockchain.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blockchain.dao.IPaymentDAO;
import com.blockchain.entity.TransferAmount;

@Repository
public class PaymentDAOImpl extends GenericDAOImpl<TransferAmount, Long> implements IPaymentDAO {

	@Override
	public TransferAmount saveTransferAmount(TransferAmount transferAmount) {
		return create(transferAmount);
	}

	@Override
	public TransferAmount updateTransferAmount(TransferAmount transferAmount) {
		return update(transferAmount);
	}

	@Override
	public List<TransferAmount> getAllTransferAmountByStatus(String status) {
		final Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("status", status);
		
		return queryForList("payment.getAllTransfersByStatus", parameterMap);
	}
}
