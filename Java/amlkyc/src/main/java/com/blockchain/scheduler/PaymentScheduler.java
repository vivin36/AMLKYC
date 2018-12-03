package com.blockchain.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IPaymentService;
import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.constants.MessageConstants;
import com.blockchain.entity.TransferAmount;
import com.blockchain.enums.Status;

@Component
public class PaymentScheduler {
	
	@Autowired
	private IScreeninglistService screeninglistService;
	
	@Autowired
	private IPaymentService paymentService;
	
	@Scheduled(cron = "0/30 * * * * ?")
	public synchronized void remittance() {
		
		List<TransferAmount> pendingTransfers = paymentService.getAllTransferAmountByStatus(Status.PENDING.getCode());
		
		for (TransferAmount transferAmount : pendingTransfers) {
			
			if(screeninglistService.checkIsBlackListed(transferAmount.getSenderAccountNumber())) {
				transferAmount.setComments(MessageConstants.REMITTANCE_FAILURE_MESSAGE_REMITTER);
				transferAmount.setStatus(Status.FAILURE.getCode());				
			} else if (screeninglistService.checkIsBlackListed(transferAmount.getReceiverAccountNumber())) {
				transferAmount.setComments(MessageConstants.REMITTANCE_FAILURE_MESSAGE_BENEFICIARY);
				transferAmount.setStatus(Status.FAILURE.getCode());				
			} else {
				transferAmount.setComments(MessageConstants.REMITTANCE_SUCCESS_MESSAGE);
				transferAmount.setStatus(Status.SUCCESS.getCode());
			}
			
			paymentService.updateTransferAmount(transferAmount);
		}
	}
}
