package com.blockchain.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

	@Override
	public Response toResponse(ApplicationException exception) {		
		return Response.status(500)
				.entity(exception.getMessage())
				.type("text/plain").
				build();
	}
}
