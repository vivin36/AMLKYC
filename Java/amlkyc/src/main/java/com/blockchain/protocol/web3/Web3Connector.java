package com.blockchain.protocol.web3;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Component
public class Web3Connector implements InitializingBean {
	
	@Value("${web3.connection.url}")
	private String connectionURL;
	
	private Web3j web3j = null;
	
	public Web3j getConnection() {
		return web3j;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		web3j = Web3j.build(new HttpService(connectionURL));		
	}	
}
