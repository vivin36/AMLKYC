package com.blockchain.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blockchain.dao.IInputPaymentDAO;
import com.blockchain.dao.ITransferAmountDAO;
import com.blockchain.entity.InputPayment;
import com.blockchain.entity.TransferAmount;

@Repository
public class InputPaymentDAOImpl extends GenericDAOImpl<InputPayment, Long> implements IInputPaymentDAO {

	@Override
	public InputPayment saveInputPayment(InputPayment inputPayment) {	
		return create(inputPayment);
	}

	@Override
	public InputPayment updateTransferAmount(InputPayment inputPayment) {		
		return update(inputPayment);
	}

	@Override
	public List<InputPayment> getAllInputPaymentsByStatus(String status) {
		final Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("status", status);
		
		return queryForList("payment.getAllInputPaymentsByStatus", parameterMap);
	}
	
}
