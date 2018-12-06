package com.blockchain.dao;

import java.util.List;

import com.blockchain.entity.ReduceAmount;

public interface IReduceAmountDAO extends IGenericDAO<ReduceAmount, Long> {
	
	public List<ReduceAmount> getAllReduceAmountByStatus(String status);
}
