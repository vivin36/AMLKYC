package com.blockchain.adapter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class RestClient implements InitializingBean {

	private Client client = null;
	
	public Client getClient() {
		return client;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		client = ClientBuilder.newClient().register(JacksonFeature.class);		
	}
}
