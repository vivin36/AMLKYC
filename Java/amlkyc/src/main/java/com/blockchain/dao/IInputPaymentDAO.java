/**
 * 
 */
package com.blockchain.dao;

import java.util.List;

import com.blockchain.entity.InputPayment;

public interface IInputPaymentDAO extends IGenericDAO<InputPayment, Long> {
	
	public List<InputPayment> getAllInputPaymentsByStatus(String status);

}
