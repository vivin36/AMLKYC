package com.blockchain.scheduler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blockchain.adapter.IDDAAdapter;
import com.blockchain.business.service.IPaymentService;
import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.constants.MessageConstants;
import com.blockchain.entity.InputPayment;
import com.blockchain.entity.ReduceAmount;
import com.blockchain.entity.TransferAmount;
import com.blockchain.enums.Status;
import com.blockchain.vo.AmountVO;
import com.blockchain.vo.IDDANotificationVO;
import com.blockchain.vo.IDDARequestBodyVO;
import com.blockchain.vo.RequestBodyVO;
import com.blockchain.vo.RequestHeadVO;
import com.blockchain.vo.WrappedIDDANotificationVO;

@Component
public class PaymentScheduler {

	@Autowired
	private IScreeninglistService screeninglistService;

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private IDDAAdapter iddaAdapter;
	
	@Value("${idda.notification.target}")
	private String target;
	
	@Value("${idda.notification.path}")
	private String path;
	
	@Scheduled(cron = "0/30 * * * * ?")
	public synchronized void inputPayments() {

		List<InputPayment> pendingInputPayments = paymentService.getAllInputPaymentsByStatus(Status.PENDING.getCode());

		for (InputPayment inputPayment : pendingInputPayments) {

			if (screeninglistService.checkIsBlackListed(inputPayment.getAccountId())) {
				inputPayment.setComments(MessageConstants.INPUT_PAYMENTS_MESSAGE_FAILURE);
				inputPayment.setStatus(Status.FAILURE.getCode());
			} else {
				inputPayment.setComments(MessageConstants.INPUT_PAYMENTS_MESSAGE_SUCCESS);
				inputPayment.setStatus(Status.SUCCESS.getCode());
				
				RequestHeadVO requestHeadVO = new RequestHeadVO();
				requestHeadVO.setVersion("1.0.0");
				requestHeadVO.setOperation("input");
				requestHeadVO.setClientId("211020000000000000044");
				requestHeadVO.setRequestTime(LocalDate.now().toString());
				
				IDDARequestBodyVO iddaRequestBodyVO = new IDDARequestBodyVO();
				AmountVO amountVO = new AmountVO();
				amountVO.setCurrency(inputPayment.getCurrency());
				amountVO.setValue(String.valueOf(inputPayment.getAmount()));
				iddaRequestBodyVO.setAmount(amountVO);
				iddaRequestBodyVO.setCompletedTime(String.valueOf(inputPayment.getModTs()));
				iddaRequestBodyVO.setInputReferenceNo(inputPayment.getInputRefNumber());
				
				IDDANotificationVO iddaNotificationVO = new IDDANotificationVO();
				iddaNotificationVO.setHead(requestHeadVO);
				iddaNotificationVO.setBody(iddaRequestBodyVO);
				
				WrappedIDDANotificationVO wrappedIDDANotificationVO = new WrappedIDDANotificationVO();
				wrappedIDDANotificationVO.setRequest(iddaNotificationVO);
				
				iddaAdapter.post(wrappedIDDANotificationVO, target, path, 
						MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
			}
			paymentService.updateInputPayment(inputPayment);
		}

	}

	@Scheduled(cron = "0/45 * * * * ?")
	public synchronized void transferAmount() {
		
		List<TransferAmount> pendingTransfers = paymentService.getAllTransferAmountByStatus(Status.PENDING.getCode());
		
		for (TransferAmount transferAmount : pendingTransfers) {
			
			if(screeninglistService.checkIsBlackListed(transferAmount.getSenderAccountId())) {
				transferAmount.setComments(MessageConstants.TRANSFER_FAILURE_MESSAGE_REMITTER);
				transferAmount.setStatus(Status.FAILURE.getCode());				
			} else if (screeninglistService.checkIsBlackListed(transferAmount.getReceiverAccountId())) {
				transferAmount.setComments(MessageConstants.TRANSFER_FAILURE_MESSAGE_BENEFICIARY);
				transferAmount.setStatus(Status.FAILURE.getCode());				
			} else {
				transferAmount.setComments(MessageConstants.TRANSFER_SUCCESS_MESSAGE);
				transferAmount.setStatus(Status.SUCCESS.getCode());
				WrappedIDDANotificationVO wrappedIDDANotificationVO = new WrappedIDDANotificationVO();
				
				IDDANotificationVO iDDANotificationVO = new IDDANotificationVO();
				
				RequestHeadVO requestHeadVO = new RequestHeadVO();
				requestHeadVO.setVersion("1.0.0");
				requestHeadVO.setClientId("4H00000010000002");
				requestHeadVO.setRequestTime(LocalDate.now().toString());
				requestHeadVO.setRequestMsgId("1234567asdfasdf1123fda");
				
				IDDARequestBodyVO iddaRequestBodyVO = new IDDARequestBodyVO();
				AmountVO amount = new AmountVO();
				amount.setCurrency(transferAmount.getCurrency());
				amount.setValue(String.valueOf( transferAmount.getAmount()) );
				iddaRequestBodyVO.setSenderId("1020000000002160");
				iddaRequestBodyVO.setReceiverId("1020000000002170");
				iddaRequestBodyVO.setTxId("12345379817323");
				iddaRequestBodyVO.setSenderAmount(amount);
				iddaRequestBodyVO.setReceiverAmount(amount);
				iddaRequestBodyVO.setTransCurrency("PHP");
				iddaRequestBodyVO.setCompletedTime(String.valueOf(transferAmount.getModTs()));
				
				iDDANotificationVO.setBody(iddaRequestBodyVO);
				iDDANotificationVO.setHead(requestHeadVO);
				
				wrappedIDDANotificationVO.setSignature("signature string");
				wrappedIDDANotificationVO.setRequest(iDDANotificationVO);
				
				iddaAdapter.post(wrappedIDDANotificationVO, target, path, 
						MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_JSON_TYPE);
			}
			
			paymentService.updateTransferAmount(transferAmount);
		}
	}

	@Scheduled(cron = "0/30 * * * * ?")
	public synchronized void reduceAmount() throws IOException {

		List<ReduceAmount> pendingReduceAmounts = paymentService.getAllReduceAmountByStatus(Status.PENDING.getCode());

		for (ReduceAmount reduceAmount : pendingReduceAmounts) {

			if (screeninglistService.checkIsBlackListed(reduceAmount.getAccountId())) {
				reduceAmount.setComments(MessageConstants.REDUCE_AMOUNT_FAILURE_MESSAGE);
				reduceAmount.setStatus(Status.FAILURE.getCode());
			} else {
				reduceAmount.setComments(MessageConstants.REDUCE_AMOUNT_SUCCESS_MESSAGE);
				reduceAmount.setStatus(Status.SUCCESS.getCode());
			}
			paymentService.updateReduceAmount(reduceAmount);
		}

	}
}
