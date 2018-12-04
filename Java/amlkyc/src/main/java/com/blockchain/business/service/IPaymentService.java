package com.blockchain.business.service;

import java.util.List;

import com.blockchain.entity.ReduceAmount;
import com.blockchain.entity.TransferAmount;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

public interface IPaymentService {

	public WrappedResponseVO createTransferAmount(WrappedRequestVO wrappedRequestVO);
	
	public List<TransferAmount> getAllTransferAmountByStatus(String status);
	
	public TransferAmount updateTransferAmount(TransferAmount transferAmount);

	public WrappedResponseVO reduceAmount(WrappedRequestVO request);
	
	List<ReduceAmount> getAllReduceAmountByStatus(String status);
	
	ReduceAmount updateReduceAmount(ReduceAmount reduceAmount);
	
	public WrappedResponseVO inputPayments(WrappedRequestVO request);
}
