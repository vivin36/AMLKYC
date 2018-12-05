package com.blockchain.adapter;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IDDAAdapter {
	
	@Autowired
	private RestClient restClient;

	public <T> Response post(T object, String target, String path, 
			MediaType acceptMediaType, MediaType requestMediaType)throws IOException {
		
		Response response = restClient
							.getClient()
							.target(target)
							.path(path)
							.request()
							.accept(acceptMediaType)
							.post(Entity.entity(object, requestMediaType));
		
		return response;		
	}
}
