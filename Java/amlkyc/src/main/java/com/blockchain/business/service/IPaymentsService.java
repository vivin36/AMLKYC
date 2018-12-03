package com.blockchain.business.service;

import java.util.List;

import com.blockchain.entity.Remittance;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

public interface IPaymentsService {

	public WrappedResponseVO createRemittance(WrappedRequestVO wrappedRequestVO);
	
	public List<Remittance> getAllRemittancesByStatus(String status);
	
	public Remittance updateRemittance(Remittance remittance);

	public WrappedResponseVO inputPayments(WrappedRequestVO request);
}
