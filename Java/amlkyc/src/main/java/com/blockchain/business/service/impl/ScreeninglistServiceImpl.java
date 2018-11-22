package com.blockchain.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.utils.Numeric;

import com.blockchain.api.ScreeninglistController;
import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.contracts.Screeninglist;
import com.blockchain.exception.ApplicationException;
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
			throw new ApplicationException("Error in adding blacklisted customer");
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
			throw new ApplicationException("Error in adding whitelisted customer");
		}
		return screeninglistVO;
	}


	@Override
	public List<ScreeninglistVO> getAllwhiteListCustomers() {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		try {
			Tuple3<List<String>, List<byte[]>, List<byte[]>> whiteListedCustomers = ScreeninglistContract.getWhiteListedCustomers().send();
		} catch (Exception e) {		
			e.printStackTrace();
			throw new ApplicationException("Error in fetching whitelisted customer");
		}
		
		return null;
	}


	@Override
	public List<ScreeninglistVO> getAllblackListCustomers() {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		try {
			Tuple3<List<String>, List<byte[]>, List<byte[]>> blackListedCustomer  = ScreeninglistContract.getBlackListedCustomers().send();
		} catch (Exception e) {		
			e.printStackTrace();
			throw new ApplicationException("Error in fetching blacklisted customer");
		}
		
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
			throw new ApplicationException("Error in checking whitelisted customer");
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
			throw new ApplicationException("Error in checking blacklisted customer");
		}
		return response;
	}


	@Override
	public String removeFromBlackList(String accountAddress) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		TransactionReceipt reponse = null;
		try {
			 reponse = ScreeninglistContract.removeBlackListedCustomer(accountAddress).send();
		} catch (Exception e) {			
			e.printStackTrace();
			throw new ApplicationException("Error in removing from blacklisted customer");
		}
		return reponse.getTransactionHash();
	}


	@Override
	public void removeFromWhiteList(String accountAddress) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		TransactionReceipt reponse = null;
		try {
			 reponse = ScreeninglistContract.removeWhiteListedCustomer(accountAddress).send();
		} catch (Exception e) {		
			e.printStackTrace();
			throw new ApplicationException("Error in removing from whitelisted customer");
		}
		
	}

}
