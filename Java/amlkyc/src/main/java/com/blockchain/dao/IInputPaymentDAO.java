/**
 * 
 */
package com.blockchain.dao;

import java.util.List;

import com.blockchain.entity.InputPayment;

public interface IInputPaymentDAO extends IGenericDAO<InputPayment, Long> {
	
	public InputPayment saveInputPayment(InputPayment inputPayment);
	
	public InputPayment updateTransferAmount(InputPayment inputPayment);

	public List<InputPayment> getAllInputPaymentsByStatus(String status);

}
