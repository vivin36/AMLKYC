package com.blockchain.business.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.utils.Numeric;

import com.blockchain.business.service.ICustomerService;
import com.blockchain.contracts.Customer;
import com.blockchain.exception.ApplicationException;
import com.blockchain.protocol.web3.ContractsDeployer;
import com.blockchain.utils.Utils;
import com.blockchain.vo.CustomerVO;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ContractsDeployer contractsDeployer;

	@Override
	public CustomerVO createCustomer(CustomerVO customerVO) {
		ECKeyPair keyPair = null;
		WalletFile walletFile = null;

		Customer customerContract = contractsDeployer.getCustomerContract();

		try {
			String password = "";
			keyPair = Keys.createEcKeyPair();
			walletFile = Wallet.createStandard(password, keyPair);
			
			customerContract.createCustomerDetails(walletFile.getAddress(),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(customerVO.getAccount())),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(customerVO.getName())),
					BigInteger.valueOf(customerVO.getCustomerType()), customerVO.getIsParentCustomer()).send();
		} catch (Exception e) {
			throw new ApplicationException("Error in creating customer details");
		}
		customerVO.setAddress(walletFile.getAddress());

		return customerVO;
	}

}
