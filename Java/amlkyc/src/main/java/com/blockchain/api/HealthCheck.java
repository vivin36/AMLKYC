package com.blockchain.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/healthcheck")
public class HealthCheck {
	
	@GET
	public Response getHealthCheck() {
		return Response.status(200).entity("Service up!").build();
	}	
}
