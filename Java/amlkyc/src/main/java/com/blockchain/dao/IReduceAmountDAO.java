package com.blockchain.dao;

import java.util.List;

import com.blockchain.entity.ReduceAmount;

public interface IReduceAmountDAO extends IGenericDAO<ReduceAmount, Long> {
	
	public ReduceAmount saveReduceAmount(ReduceAmount reduceAmount);

	public ReduceAmount updateReduceAmount(ReduceAmount reduceAmount);
	
	public List<ReduceAmount> getAllReduceAmountByStatus(String status);
}
