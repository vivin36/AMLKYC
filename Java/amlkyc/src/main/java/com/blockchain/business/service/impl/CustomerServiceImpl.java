package com.blockchain.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.utils.Numeric;

import com.blockchain.adapter.EthResponseAdapter;
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
	
	@Autowired
	private EthResponseAdapter ethResponseAdapter;

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
	public List<CustomerVO> getAllCustomerDetails() {
		
		List<CustomerVO> customerDetailsList = new ArrayList<>();
		List<String> addresses = null, customerAccounts = null, customerNames = null;
		
		CustomerVO customerVO = null;
		
		String hexValueResp = null;
		
		Customer customerContract = contractsDeployer.getCustomerContract();
		
		try {			
			addresses = customerContract.getAllAddresses().send();
			hexValueResp = ethResponseAdapter.getEthResponse(customerContract, Customer.FUNC_GETALLCUSTOMERACCOUNTS);
			customerAccounts = Utils.hexToASCIIElems(hexValueResp);
			hexValueResp = ethResponseAdapter.getEthResponse(customerContract, Customer.FUNC_GETALLCUSTOMERNAMES);
			customerNames = Utils.hexToASCIIElems(hexValueResp);
			
			for(int index = 0; index < addresses.size(); index++) {
				customerVO = new CustomerVO();
				customerVO.setAddress(addresses.get(index));
				customerVO.setName(customerNames.get(index));
				customerVO.setAccount(customerAccounts.get(index));
				customerDetailsList.add(customerVO);
				customerVO = null;
			}
		} catch (Exception e) {
			throw new ApplicationException("Error in retrieving details of all customers");
		}
		
		return customerDetailsList;
	}	

}
