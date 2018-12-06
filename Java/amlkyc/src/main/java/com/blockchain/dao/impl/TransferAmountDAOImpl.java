package com.blockchain.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blockchain.dao.ITransferAmountDAO;
import com.blockchain.entity.TransferAmount;

@Repository
public class TransferAmountDAOImpl extends GenericDAOImpl<TransferAmount, Long> implements ITransferAmountDAO {

	@Override
	public List<TransferAmount> getAllTransferAmountByStatus(String status) {
		final Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("status", status);
		
		return queryForList("payment.getAllTransfersByStatus", parameterMap);
	}
}
