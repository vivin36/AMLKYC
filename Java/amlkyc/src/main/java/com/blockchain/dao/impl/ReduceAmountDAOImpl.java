package com.blockchain.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blockchain.dao.IReduceAmountDAO;
import com.blockchain.entity.ReduceAmount;

@Repository
public class ReduceAmountDAOImpl extends GenericDAOImpl<ReduceAmount, Long> implements IReduceAmountDAO {

	@Override
	public List<ReduceAmount> getAllReduceAmountByStatus(String status) {
		final Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("status", status);
		
		return queryForList("payment.getAllReduceAmountByStatus", parameterMap);
	}
}
