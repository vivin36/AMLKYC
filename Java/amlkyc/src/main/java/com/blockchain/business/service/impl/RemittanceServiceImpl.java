package com.blockchain.business.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blockchain.business.service.IRemittanceService;
import com.blockchain.dao.IRemittanceDAO;
import com.blockchain.entity.Remittance;
import com.blockchain.enums.Status;
import com.blockchain.vo.ResponseBodyVO;
import com.blockchain.vo.ResponseHeadVO;
import com.blockchain.vo.ResponseVO;
import com.blockchain.vo.ResultInfoVO;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

@Service
public class RemittanceServiceImpl implements IRemittanceService {

	@Autowired
	private IRemittanceDAO remittanceDAO;
	
	@Transactional
	@Override
	public WrappedResponseVO createRemittance(WrappedRequestVO wrappedRequestVO) {
		
		Remittance remittance = new Remittance();
		
		remittance.setDescription(wrappedRequestVO.getRequest().getHead().getDescription());
		remittance.setSenderAccountNumber(wrappedRequestVO.getRequest().getBody().getSenderAccountNumber());
		remittance.setReceiverAccountNumber(wrappedRequestVO.getRequest().getBody().getReceiverAccountNumber());
		remittance.setStatus(Status.PENDING.getCode());
		remittanceDAO.create(remittance);
		
		WrappedResponseVO wrappedResponseVO = new WrappedResponseVO();
		ResponseVO responseVO = new ResponseVO();
		ResponseHeadVO responseHeadVO = new ResponseHeadVO();
		responseHeadVO.setVersion("3.0.1");
		responseHeadVO.setDescription("Received the request");
		responseHeadVO.setClientId("4H00000010000002");
		responseHeadVO.setResponseTime(LocalDate.now().toString());
		responseHeadVO.setRequestMsgId(wrappedRequestVO.getRequest().getHead().getRequestMsgId());
		ResponseBodyVO responseBodyVO = new ResponseBodyVO();
		ResultInfoVO resultInfoVO = new ResultInfoVO();
		resultInfoVO.setResultStatus(Status.RECEIVED.getCode());
		resultInfoVO.setResultCodeId("0000000000");
		resultInfoVO.setResultDescription(Status.RECEIVED.toString());
		resultInfoVO.setResultComments("Received");
		responseBodyVO.setResultInfo(resultInfoVO);
		responseVO.setHead(responseHeadVO);
		responseVO.setBody(responseBodyVO);
		wrappedResponseVO.setResponse(responseVO);
		
		return wrappedResponseVO;
	}

	@Override
	public List<Remittance> getAllRemittancesByStatus(String status) {
		
		return remittanceDAO.getAllRemittancesByStatus(status);
	}

	@Transactional
	@Override
	public Remittance updateRemittance(Remittance remittance) {
		return remittanceDAO.update(remittance);
	}
}
