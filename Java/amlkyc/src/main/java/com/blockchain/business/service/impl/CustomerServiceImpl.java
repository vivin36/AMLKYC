package com.blockchain.business.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.tuples.generated.Tuple4;
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
					new BigInteger(customerVO.getCustomerType()), customerVO.getIsParentCustomer()).send();
		} catch (Exception e) {
			throw new ApplicationException("Error in creating customer details!");
		}
		customerVO.setAddress(walletFile.getAddress());

		return customerVO;
	}

	@Override
	public CustomerVO updateCustomer(CustomerVO customerVO) {
		
		Customer customerContract = contractsDeployer.getCustomerContract();
		
		try {
			customerContract.updateCustomerDetails(customerVO.getAddress(),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(customerVO.getAccount())),
					Numeric.hexStringToByteArray(Utils.ASCIIToHex(customerVO.getName())),
					new BigInteger(customerVO.getCustomerType()), customerVO.getIsParentCustomer()).send();
		} catch (Exception e) {
			throw new ApplicationException("Error in updating customer details!");			
		}
		
		return customerVO;
	}

	@Override
	public CustomerVO getCustomerByAddress(String address) {
		
		CustomerVO customerVO = new CustomerVO();
		Customer customerContract = contractsDeployer.getCustomerContract();		
		
		try {
			Tuple4<byte[], byte[], BigInteger, Boolean> txnResponse = customerContract.getCustomerDetails(address).send();
			customerVO.setAddress(address);
			customerVO.setAccount(Utils.hexToASCII(Numeric.toHexString(txnResponse.getValue1())));
			customerVO.setName(Utils.hexToASCII(Numeric.toHexString(txnResponse.getValue2())));
			customerVO.setCustomerType(String.valueOf(txnResponse.getValue3()));
			customerVO.setIsParentCustomer(txnResponse.getValue4());
		} catch (Exception e) {
			throw new ApplicationException("Error in retrieving customer details!");
		}
		
		return customerVO;
	}

	@Override
	public List<String> getAllCustomerAddresses() {
		
		Customer customerContract = contractsDeployer.getCustomerContract();

		List<String> addressResp = null;
		
		try {
			addressResp = customerContract.getAllAddresses().send();
		} catch (Exception e) {
			throw new ApplicationException("Error in retrieving addresses of all users");
		}
		
		return addressResp;
	}	

}
