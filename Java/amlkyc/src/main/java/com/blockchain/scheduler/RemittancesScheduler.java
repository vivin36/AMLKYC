package com.blockchain.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IRemittanceService;
import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.constants.MessageConstants;
import com.blockchain.entity.Remittance;
import com.blockchain.enums.Status;

@Component
public class RemittancesScheduler {
	
	@Autowired
	private IScreeninglistService screeninglistService;
	
	@Autowired
	private IRemittanceService remittanceService;
	
	@Scheduled(cron = "0/30 * * * * ?")
	public synchronized void remittance() {
		
		List<Remittance> pendingRemittances = remittanceService.getAllRemittancesByStatus(Status.PENDING.getCode());
		
		for (Remittance remittance : pendingRemittances) {
			
			if(screeninglistService.checkIsBlackListed(remittance.getSenderAccountNumber())) {
				remittance.setComments(MessageConstants.REMITTANCE_FAILURE_MESSAGE_REMITTER);
				remittance.setStatus(Status.FAILURE.getCode());				
			} else if (screeninglistService.checkIsBlackListed(remittance.getReceiverAccountNumber())) {
				remittance.setComments(MessageConstants.REMITTANCE_FAILURE_MESSAGE_BENEFICIARY);
				remittance.setStatus(Status.FAILURE.getCode());				
			} else {
				remittance.setComments(MessageConstants.REMITTANCE_SUCCESS_MESSAGE);
				remittance.setStatus(Status.SUCCESS.getCode());
			}
			
			remittanceService.updateRemittance(remittance);
		}
	}
}
