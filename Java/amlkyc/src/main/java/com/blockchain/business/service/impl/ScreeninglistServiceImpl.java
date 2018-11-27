package com.blockchain.business.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.utils.Numeric;

import com.blockchain.adapter.EthResponseAdapter;
import com.blockchain.api.ScreeninglistController;
import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.contracts.Screeninglist;
import com.blockchain.exception.ApplicationException;
import com.blockchain.protocol.web3.ContractsDeployer;
import com.blockchain.utils.Utils;
import com.blockchain.vo.CustomerVO;
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
			Tuple3<List<String>, List<byte[]>, List<byte[]>> whiteListedCustomers = ScreeninglistContract
					.getWhiteListedCustomers().send();
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
			Tuple3<List<String>, List<byte[]>, List<byte[]>> blackListedCustomer = ScreeninglistContract
					.getBlackListedCustomers().send();
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
		Screeninglist screeninglistContract = contractsDeployer.getScreeningListContract();
		TransactionReceipt reponse = null;
		try {
			reponse = screeninglistContract.removeWhiteListedCustomer(accountAddress).send();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Error in removing from whitelisted customer");
		}

	}

	@Override
	public List<ScreeninglistVO> getAllwhiteListCustomersAddress() {
		Screeninglist screeninglistContract = contractsDeployer.getScreeningListContract();

		final Function getWhitelistedCustNames = new Function("getAllWhiteListedCustomerNames", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bytes32>>() {
				}));

		final Function getWhiteListedCustID = new Function("getAllWhiteListedCustomerIdentificationNumber",
				Arrays.<Type>asList(), Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bytes32>>() {
				}));

		List<ScreeninglistVO> whiteListedCustomersList = new ArrayList<>();
		List<String> whiteListedCustNames = null, addresses = null, whiteListedIDs;
		ScreeninglistVO screeninglistVO = null;

		try {
			String encodedFunction = FunctionEncoder.encode(getWhitelistedCustNames);
			String value = ethResponseAdapter.getEthResponse(screeninglistContract, encodedFunction);

			encodedFunction = FunctionEncoder.encode(getWhiteListedCustID);
			String customersIDList = ethResponseAdapter.getEthResponse(screeninglistContract, encodedFunction);

			whiteListedCustNames = Utils.hexToASCIIElem(value);
			whiteListedIDs = Utils.hexToASCIIElem(customersIDList);
			addresses = screeninglistContract.getAllWhiteListedCustomerAddress().send();
			for (int index = 0; index < addresses.size(); index++) {
				screeninglistVO = new ScreeninglistVO();
				screeninglistVO.setAccountAddress(addresses.get(index));
				screeninglistVO.setName(whiteListedCustNames.get(index));
				screeninglistVO.setIdentificationNumber(whiteListedIDs.get(index));
				whiteListedCustomersList.add(screeninglistVO);
				screeninglistVO = null;
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return whiteListedCustomersList;
	}

	@Override
	public List<ScreeninglistVO> getAllblackListCustomersAddress() {
		Screeninglist ScreeninglistContract = contractsDeployer.getScreeningListContract();

		List blackListedCustomersAddress = null;
		try {
			blackListedCustomersAddress = ScreeninglistContract.getAllBlackListedCustomerAddress().send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blackListedCustomersAddress;
	}

}
