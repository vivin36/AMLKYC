package com.blockchain.business.service;

import java.util.List;

import com.blockchain.entity.InputPayment;
import com.blockchain.entity.TransferAmount;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

public interface IPaymentService {

	public WrappedResponseVO createTransferAmount(WrappedRequestVO wrappedRequestVO);
	
	public List<TransferAmount> getAllTransferAmountByStatus(String status);
	
	public TransferAmount updateTransferAmount(TransferAmount transferAmount);
	
	public List<InputPayment> getAllInputPaymentsByStatus(String code);
	
	public InputPayment updateInputPayment(InputPayment inputPayment);
	
	public WrappedResponseVO reduceAmount(WrappedRequestVO request);
	
	public WrappedResponseVO inputPayments(WrappedRequestVO request);


}
