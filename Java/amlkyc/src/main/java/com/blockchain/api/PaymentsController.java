package com.blockchain.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IPaymentsService;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

@Component
@Path("payments")
public class PaymentsController {
	
	@Autowired
	private IPaymentsService paymentsService;

	@Path("transfer")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public WrappedResponseVO createRemittance(WrappedRequestVO request) {
		return paymentsService.createRemittance(request);
	}
	
	@Path("redeem")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public WrappedResponseVO reduceAmount(WrappedRequestVO request) {
		return paymentsService.reduceAmount(request);
	}
}
