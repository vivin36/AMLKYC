package com.blockchain.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.utils.Numeric;

import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.contracts.Screeninglist;
import com.blockchain.protocol.web3.ContractsDeployer;
import com.blockchain.utils.Utils;
import com.blockchain.vo.ScreeninglistVO;

@Service
public class ScreeninglistServiceImpl implements IScreeninglistService {

	@Autowired
	private ContractsDeployer contractsDeployer;
	 

	@Override
	public ScreeninglistVO addToBlackListedCustomer(ScreeninglistVO screeninglistVO) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		
		ScreeninglistContract.addBlackListedCustomer(screeninglistVO.getAccountAddress(),
				Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getIdentificationNumber())), 
				Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getName())));
		return screeninglistVO;
	}

}
