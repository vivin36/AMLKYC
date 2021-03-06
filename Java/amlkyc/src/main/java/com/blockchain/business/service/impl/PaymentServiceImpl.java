package com.blockchain.business.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blockchain.business.service.IPaymentService;
import com.blockchain.dao.IInputPaymentDAO;
import com.blockchain.dao.IReduceAmountDAO;
import com.blockchain.dao.ITransferAmountDAO;
import com.blockchain.entity.InputPayment;
import com.blockchain.entity.ReduceAmount;
import com.blockchain.entity.TransferAmount;
import com.blockchain.enums.Status;
import com.blockchain.exception.ApplicationException;
import com.blockchain.vo.ResponseBodyVO;
import com.blockchain.vo.ResponseHeadVO;
import com.blockchain.vo.ResponseVO;
import com.blockchain.vo.ResultInfoVO;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private ITransferAmountDAO transferAmountDAO;
	
	@Autowired
	private IReduceAmountDAO reduceAmountDAO;

	@Autowired
	private IInputPaymentDAO inputPaymentDAO;
	
	@Transactional
	@Override
	public WrappedResponseVO createTransferAmount(WrappedRequestVO wrappedRequestVO) {
		
		if(!isValidSignature(wrappedRequestVO.getSignature())) {
			throw new ApplicationException("Invalid signature received!");
		}
		
		TransferAmount transferAmount = new TransferAmount();
		
		transferAmount.setCurrency(wrappedRequestVO.getRequest().getBody().getAmount().getCurrency());
		transferAmount.setAmount(Integer.parseInt(wrappedRequestVO.getRequest().getBody().getAmount().getValue()));
		transferAmount.setDescription(wrappedRequestVO.getRequest().getHead().getDescription());
		transferAmount.setSenderAccountNumber(wrappedRequestVO.getRequest().getBody().getSenderAccountNumber());
		transferAmount.setSenderAccountId(wrappedRequestVO.getRequest().getBody().getSenderAccountId());
		transferAmount.setReceiverAccountNumber(wrappedRequestVO.getRequest().getBody().getReceiverAccountNumber());
		transferAmount.setReceiverAccountId(wrappedRequestVO.getRequest().getBody().getReceiverAccountId());
		transferAmount.setStatus(Status.PENDING.getCode());
		transferAmountDAO.create(transferAmount);
		
		return generateResponse(wrappedRequestVO);
	}

	@Override
	public List<TransferAmount> getAllTransferAmountByStatus(String status) {
		return transferAmountDAO.getAllTransferAmountByStatus(status);
	}

	@Transactional
	@Override
	public TransferAmount updateTransferAmount(TransferAmount transferAmount) {
		return transferAmountDAO.update(transferAmount);
	}

	@Transactional
	@Override
	public WrappedResponseVO reduceAmount(WrappedRequestVO wrappedRequestVO) {
		if(!isValidSignature(wrappedRequestVO.getSignature())) {
			throw new ApplicationException("Invalid signature received!");
		}
		
		ReduceAmount reduceAmount = new ReduceAmount();
		
		reduceAmount.setCurrency(wrappedRequestVO.getRequest().getBody().getAmount().getCurrency());
		reduceAmount.setAmount(Integer.parseInt(wrappedRequestVO.getRequest().getBody().getAmount().getValue()));
		reduceAmount.setDescription(wrappedRequestVO.getRequest().getHead().getDescription());
		reduceAmount.setAccountNumber(wrappedRequestVO.getRequest().getBody().getAccountNumber());
		reduceAmount.setAccountId(wrappedRequestVO.getRequest().getBody().getAccountId());
		reduceAmount.setRedeemRefNo(wrappedRequestVO.getRequest().getBody().getRedeemReferenceNo());
		reduceAmount.setStatus(Status.PENDING.getCode());
		reduceAmountDAO.create(reduceAmount);
		
		return generateResponse(wrappedRequestVO);
	}
	
	@Transactional
	@Override
	public List<ReduceAmount> getAllReduceAmountByStatus(String status) {
		return reduceAmountDAO.getAllReduceAmountByStatus(status); 
	}
	
	@Transactional
	@Override
	public ReduceAmount updateReduceAmount(ReduceAmount reduceAmount) {
		return reduceAmountDAO.update(reduceAmount);
	}
	
	@Transactional
	@Override
	public WrappedResponseVO inputPayments(WrappedRequestVO wrappedRequestVO) {
		if(!isValidSignature(wrappedRequestVO.getSignature())) {
			throw new ApplicationException("Invalid signature received!");
		}
		InputPayment inputPayment = new InputPayment();
		inputPayment.setAccountId(wrappedRequestVO.getRequest().getBody().getAccountId());
		inputPayment.setAccountNumber(wrappedRequestVO.getRequest().getBody().getAccountNumber());
		inputPayment.setInputRefNumber(wrappedRequestVO.getRequest().getBody().getInputReferenceNo());
		inputPayment.setCurrency(wrappedRequestVO.getRequest().getBody().getAmount().getCurrency());
		inputPayment.setAmount(Integer.parseInt(wrappedRequestVO.getRequest().getBody().getAmount().getValue()));
		inputPayment.setDescription(wrappedRequestVO.getRequest().getHead().getDescription());
		inputPayment.setStatus(Status.PENDING.getCode());
		inputPaymentDAO.create(inputPayment);
		return generateResponse(wrappedRequestVO);
	}
	
	@Override
	public List<InputPayment> getAllInputPaymentsByStatus(String code) {
		return inputPaymentDAO.getAllInputPaymentsByStatus(code);
	}
	
	@Transactional
	@Override
	public InputPayment updateInputPayment(InputPayment inputPayment) {
		return inputPaymentDAO.update(inputPayment);
	}
	
	private WrappedResponseVO generateResponse(WrappedRequestVO wrappedRequestVO) {
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
	
	private Boolean isValidSignature(String signature) {
		return true;
	}

	@Override
	public List<InputPayment> getAllInputPayments() {
		return inputPaymentDAO.findAll();
	}

	@Override
	public List<TransferAmount> getAllTransferAmounts() {
		return transferAmountDAO.findAll();
	}

	@Override
	public List<ReduceAmount> getAllRedemtions() {
		return reduceAmountDAO.findAll();
	}
}

