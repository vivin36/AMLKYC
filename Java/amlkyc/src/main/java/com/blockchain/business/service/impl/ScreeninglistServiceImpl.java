package com.blockchain.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.utils.Numeric;

import com.blockchain.api.ScreeninglistController;
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
		
		try {
			ScreeninglistContract.addBlackListedCustomer(screeninglistVO.getAccountAddress(),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getIdentificationNumber())), 
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getName()))).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screeninglistVO;
	}


	@Override
	public ScreeninglistVO addToWhiteListedCustomer(ScreeninglistVO screeninglistVO) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		
		try {
			ScreeninglistContract.addWhiteListedCustomer(screeninglistVO.getAccountAddress(),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getIdentificationNumber())), 
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getName()))).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screeninglistVO;
	}


	@Override
	public List<ScreeninglistVO> getAllwhiteListCustomers() {
		return null;
	}


	@Override
	public List<ScreeninglistVO> getAllblackListCustomers() {
		return null;
	}


	@Override
	public boolean checkIsWhiteListed(String accountAddress) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		Boolean response = false;
		try {
			response = ScreeninglistContract.checkIsWhiteListed(accountAddress).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}


	@Override
	public boolean checkIsBlackListed(String accountAddress) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		Boolean response = false;
		try {
			response = ScreeninglistContract.checkIsBlackListed(accountAddress).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
