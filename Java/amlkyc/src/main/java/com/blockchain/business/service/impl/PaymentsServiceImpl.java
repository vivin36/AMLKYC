package com.blockchain.business.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blockchain.business.service.IPaymentsService;
import com.blockchain.dao.IPaymentsDAO;
import com.blockchain.entity.Remittance;
import com.blockchain.enums.Status;
import com.blockchain.vo.ResponseBodyVO;
import com.blockchain.vo.ResponseHeadVO;
import com.blockchain.vo.ResponseVO;
import com.blockchain.vo.ResultInfoVO;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentsServiceImpl implements IPaymentsService {

	@Autowired
	private IPaymentsDAO paymentsDAO;
	
	@Transactional
	@Override
	public WrappedResponseVO createRemittance(WrappedRequestVO wrappedRequestVO) {
		
		Remittance remittance = new Remittance();
		
		remittance.setDescription(wrappedRequestVO.getRequest().getHead().getDescription());
		remittance.setSenderAccountNumber(wrappedRequestVO.getRequest().getBody().getSenderAccountNumber());
		remittance.setReceiverAccountNumber(wrappedRequestVO.getRequest().getBody().getReceiverAccountNumber());
		remittance.setStatus(Status.PENDING.getCode());
		paymentsDAO.create(remittance);
		
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
		
		return paymentsDAO.getAllRemittancesByStatus(status);
	}

	@Transactional
	@Override
	public Remittance updateRemittance(Remittance remittance) {
		return paymentsDAO.update(remittance);
	}

	@Override
	public WrappedResponseVO reduceAmount(WrappedRequestVO wrappedRequestVO) {
		
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
	public WrappedResponseVO inputPayments(WrappedRequestVO request) {
	
		return null;
	}
}
