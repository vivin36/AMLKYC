package com.blockchain.protocol.web3;

import java.math.BigInteger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;

import com.blockchain.contracts.Customer;
import com.blockchain.contracts.Screeninglist;

@Component
public class ContractsDeployer implements InitializingBean {
	
	@Value("${coinbasePrivateKey}")
	private String coinbasePrivateKey;
	
	@Value("${gasPrice}")
	private String gasPrice;
	
	@Value("${gasLimit}")
	private String gasLimit;
	
	@Autowired
	private Web3Connector web3Connector;
	
	private Customer customerContract = null;
	private Screeninglist ScreeningListContract = null;

	private Credentials credentials = null;
	
	public Credentials getCredentials() {
		return credentials;
	}
	
	public String getGasPrice() {
		return gasPrice;
	}
	
	public String getGasLimit() {
		return gasLimit;
	}
	
	public Customer getCustomerContract() {
		return customerContract;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception  {
		credentials = Credentials.create(coinbasePrivateKey);
		customerContract = Customer.deploy(web3Connector.getConnection(), credentials, 
				new BigInteger(gasPrice), new BigInteger(gasLimit)).send();
		
		ScreeningListContract = Screeninglist.deploy(web3Connector.getConnection(), credentials,
				new BigInteger(gasPrice), new BigInteger(gasLimit)).send();
		
	}

	public Screeninglist getScreeningListContract() {
		return ScreeningListContract;
	}

	public void setScreeningListContract(Screeninglist screeningListContract) {
		ScreeningListContract = screeningListContract;
	}

}
