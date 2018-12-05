package com.blockchain.scheduler;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IPaymentService;
import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.constants.MessageConstants;
import com.blockchain.entity.InputPayment;
import com.blockchain.entity.ReduceAmount;
import com.blockchain.entity.TransferAmount;
import com.blockchain.enums.Status;

@Component
public class PaymentScheduler {
	
	@Autowired
	private IScreeninglistService screeninglistService;
	
	@Autowired
	private IPaymentService paymentService;
	
	@Scheduled(cron = "0/30 * * * * ?")
	public synchronized void inputPayments() {
		
		List<InputPayment> pendingInputPayments = paymentService.getAllInputPaymentsByStatus(Status.PENDING.getCode());
		
		for(InputPayment inputPayment : pendingInputPayments) {
			
			if(screeninglistService.checkIsBlackListed(inputPayment.getAccountId())) {
				inputPayment.setComments(MessageConstants.INPUT_PAYMENTS_MESSAGE_FAILURE);
				inputPayment.setStatus(Status.FAILURE.getCode());	
			} else {
				inputPayment.setComments(MessageConstants.INPUT_PAYMENTS_MESSAGE_SUCCESS);
				inputPayment.setStatus(Status.SUCCESS.getCode());
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
			}
			
			paymentService.updateTransferAmount(transferAmount);
		}
	}
	
	@Scheduled(cron = "0/30 * * * * ?")
	public synchronized void reduceAmount() throws IOException {
		
		List<ReduceAmount> pendingReduceAmounts = paymentService.getAllReduceAmountByStatus(Status.PENDING.getCode());
		
		for (ReduceAmount reduceAmount : pendingReduceAmounts) {
			
			if(screeninglistService.checkIsBlackListed(reduceAmount.getAccountId())) {
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