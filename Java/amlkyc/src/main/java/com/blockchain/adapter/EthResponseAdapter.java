package com.blockchain.adapter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;

import com.blockchain.protocol.web3.ContractsDeployer;
import com.blockchain.protocol.web3.Web3Connector;

@Component
public class EthResponseAdapter implements InitializingBean {
	
	@Autowired
	private ContractsDeployer contractsDeployer;
	
	@Autowired
	private Web3Connector web3connector;

	private TransactionManager transactionManager;
	
	public String getEthResponse(Contract contract, String contractFuncName) throws IOException {
		
		Function function = new Function(contractFuncName, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray<Bytes32>>() {}));
		
		String encodedFunction = FunctionEncoder.encode(function);
		
		EthCall ethCall = web3connector.getConnection().ethCall(Transaction.createEthCallTransaction(
                transactionManager.getFromAddress(), 
                contract.getContractAddress(), 
                encodedFunction), DefaultBlockParameterName.LATEST).send();
		
		function = null;
		
		return ethCall.getValue();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		transactionManager = new RawTransactionManager(web3connector.getConnection(), contractsDeployer.getCredentials());
	}
}
