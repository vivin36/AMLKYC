package com.blockchain.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import com.blockchain.adapter.EthResponseAdapter;
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

	@Autowired
	private EthResponseAdapter ethResponseAdapter;

	@Override
	public ScreeninglistVO addToBlackListedCustomer(ScreeninglistVO screeninglistVO) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();

		try {
			ScreeninglistContract.addBlackListedCustomer(screeninglistVO.getAccountAddress(),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getIdentificationNumber())),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(screeninglistVO.getName()))).send();
		} catch (Exception e) {
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
			throw new ApplicationException("Error in adding whitelisted customer");
		}
		return screeninglistVO;
	}

	@Override
	public List<ScreeninglistVO> getAllwhiteListCustomers() {
		Screeninglist screeninglistContract = contractsDeployer.getScreeningListContract();
		List<ScreeninglistVO> whiteListedCustomersList = new ArrayList<>();
		List<String> whiteListedCustNames = null, addresses = null, whiteListedIDs;
		ScreeninglistVO screeninglistVO = null;

		try {
			String value = ethResponseAdapter.getEthResponse(screeninglistContract,
					Screeninglist.FUNC_GETALLWHITELISTEDCUSTOMERNAMES);

			String customersIDList = ethResponseAdapter.getEthResponse(screeninglistContract,
					Screeninglist.FUNC_GETALLWHITELISTEDCUSTOMERIDENTIFICATIONNUMBER);

			whiteListedCustNames = Utils.hexToASCIIElems(value);
			whiteListedIDs = Utils.hexToASCIIElems(customersIDList);
			addresses = screeninglistContract.getAllWhiteListedCustomerAddress().send();
			for (int index = 0; index < addresses.size(); index++) {
				screeninglistVO = new ScreeninglistVO();
				screeninglistVO.setAccountAddress(addresses.get(index));
				screeninglistVO.setName(whiteListedCustNames.get(index));
				screeninglistVO.setIdentificationNumber(whiteListedIDs.get(index));
				whiteListedCustomersList.add(screeninglistVO);
				screeninglistVO = null;
			}
		} catch (Exception e) {
			throw new ApplicationException("Error in retrieving  WhiteListed customer");
		}
		return whiteListedCustomersList;
	}

	@Override
	public List<ScreeninglistVO> getAllblackListCustomers() {
		Screeninglist screeninglistContract = contractsDeployer.getScreeningListContract();

		List<ScreeninglistVO> blackListedCustomersList = new ArrayList<>();
		List<String> blackListedCustNames = null, addresses = null, blackListedIDs = null;
		ScreeninglistVO screeninglistVO = null;
		try {

			String value = ethResponseAdapter.getEthResponse(screeninglistContract,
					Screeninglist.FUNC_GETALLBLACKLISTEDCUSTOMERNAMES);

			String customersIDList = ethResponseAdapter.getEthResponse(screeninglistContract,
					Screeninglist.FUNC_GETALLBLACKLISTEDCUSTOMERIDENTIFICATIONNUMBER);

			blackListedCustNames = Utils.hexToASCIIElems(value);
			blackListedIDs = Utils.hexToASCIIElems(customersIDList);
			addresses = screeninglistContract.getAllBlackListedCustomerAddress().send();
			for (int index = 0; index < blackListedCustNames.size(); index++) {
				screeninglistVO = new ScreeninglistVO();
				screeninglistVO.setAccountAddress(addresses.get(index));
				screeninglistVO.setName(blackListedCustNames.get(index));
				screeninglistVO.setIdentificationNumber(blackListedIDs.get(index));
				blackListedCustomersList.add(screeninglistVO);
				screeninglistVO = null;
			}
		} catch (Exception e) {
			throw new ApplicationException("Error in retrieving  blacklisted customer");
		}
		return blackListedCustomersList;
	}

	@Override
	public boolean checkIsWhiteListed(String accountAddress) {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		Boolean response = false;
		try {
			response = ScreeninglistContract.checkIsWhiteListed(accountAddress).send();
		} catch (Exception e) {
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
		Screeninglist screeninglistContract = contractsDeployer.getScreeningListContract();
		TransactionReceipt reponse = null;
		try {
			reponse = screeninglistContract.removeWhiteListedCustomer(accountAddress).send();
		} catch (Exception e) {
			throw new ApplicationException("Error in removing from whitelisted customer");
		}

	}

	@Override
	public List<ScreeninglistVO> getAllwhiteListCustomersAddress() {

		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();
		List whiteListedCustomersAddress = null;
		try {
			whiteListedCustomersAddress = ScreeninglistContract.getAllWhiteListedCustomerAddress().send();
		} catch (Exception e) {
			throw new ApplicationException("Error in retrieving from whitelisted customer address");
		}
		return whiteListedCustomersAddress;
	}

	@Override
	public List<ScreeninglistVO> getAllblackListCustomersAddress() {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();

		List blackListedCustomersAddress = null;
		try {
			blackListedCustomersAddress = ScreeninglistContract.getAllBlackListedCustomerAddress().send();
		} catch (Exception e) {
			throw new ApplicationException("Error in retrieving from blacklisted customer address");
		}
		return blackListedCustomersAddress;
	}

}
