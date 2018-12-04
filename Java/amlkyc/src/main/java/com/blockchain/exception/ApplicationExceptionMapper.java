package com.blockchain.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		
		String msg = null;
		
		if(exception instanceof ApplicationException) {
			msg = exception.getMessage();
		} else {
			msg = "Internal Server Error!";
		}
		return Response.status(500).entity(msg).type("text/plain").build();
	}
}
