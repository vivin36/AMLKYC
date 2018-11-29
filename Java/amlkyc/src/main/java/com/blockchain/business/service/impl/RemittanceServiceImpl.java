package com.blockchain.business.service.impl;

import org.springframework.stereotype.Service;

import com.blockchain.business.service.IRemittanceService;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

@Service
public class RemittanceServiceImpl implements IRemittanceService {

	@Override
	public WrappedResponseVO createRemittance(WrappedRequestVO wrappedRequestVO) {
		
		WrappedResponseVO wrappedResponseVO = new WrappedResponseVO();
		
		return wrappedResponseVO;
	}

}
