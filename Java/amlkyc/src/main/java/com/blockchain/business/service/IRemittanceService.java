package com.blockchain.business.service;

import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

public interface IRemittanceService {

	public WrappedResponseVO createRemittance(WrappedRequestVO wrappedRequestVO); 
}
