package com.blockchain.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IPaymentService;
import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.constants.MessageConstants;
import com.blockchain.entity.ReduceAmount;
import com.blockchain.entity.TransferAmount;
import com.blockchain.enums.Status;

@Component
public class PaymentScheduler {
	
	@Autowired
	private IScreeninglistService screeninglistService;
	
	@Autowired
	private IPaymentService paymentService;
	
	@Scheduled(cron = "0/45 * * * * ?")
	public synchronized void transferAmount() {
		
		List<TransferAmount> pendingTransfers = paymentService.getAllTransferAmountByStatus(Status.PENDING.getCode());
		
		for (TransferAmount transferAmount : pendingTransfers) {
			
			if(screeninglistService.checkIsBlackListed(transferAmount.getSenderAccountNumber())) {
				transferAmount.setComments(MessageConstants.TRANSFER_FAILURE_MESSAGE_REMITTER);
				transferAmount.setStatus(Status.FAILURE.getCode());				
			} else if (screeninglistService.checkIsBlackListed(transferAmount.getReceiverAccountNumber())) {
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
	public synchronized void reduceAmount() {
		
		List<ReduceAmount> pendingReduceAmounts = paymentService.getAllReduceAmountByStatus(Status.PENDING.getCode());
		
		for (ReduceAmount reduceAmount : pendingReduceAmounts) {
			
			if(screeninglistService.checkIsBlackListed(reduceAmount.getAccountNumber())) {
				reduceAmount.setComments(MessageConstants.REDUCE_AMOUNT_SUCCESS_MESSAGE);
				reduceAmount.setStatus(Status.FAILURE.getCode());				
			} else {
				reduceAmount.setComments(MessageConstants.REDUCE_AMOUNT_FAILURE_MESSAGE);
				reduceAmount.setStatus(Status.SUCCESS.getCode());
			}
			
			paymentService.updateReduceAmount(reduceAmount);
		}
		
	}
}
