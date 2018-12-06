package com.blockchain.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blockchain.dao.IInputPaymentDAO;
import com.blockchain.entity.InputPayment;

@Repository
public class InputPaymentDAOImpl extends GenericDAOImpl<InputPayment, Long> implements IInputPaymentDAO {

	@Override
	public List<InputPayment> getAllInputPaymentsByStatus(String status) {
		final Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("status", status);
		
		return queryForList("payment.getAllInputPaymentsByStatus", parameterMap);
	}
	
}
