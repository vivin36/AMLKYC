package com.blockchain.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IPaymentService;
import com.blockchain.entity.InputPayment;
import com.blockchain.entity.ReduceAmount;
import com.blockchain.entity.TransferAmount;
import com.blockchain.vo.WrappedRequestVO;
import com.blockchain.vo.WrappedResponseVO;

@Component
@Path("payments")
public class PaymentController {
	
	@Autowired
	private IPaymentService paymentService;
	
	@Path("credit")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public WrappedResponseVO inputPayments(WrappedRequestVO request) {
		return paymentService.inputPayments(request);
	}

	@Path("transfer")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public WrappedResponseVO createTransferAmount(WrappedRequestVO request) {
		return paymentService.createTransferAmount(request);
	}
	
	@Path("redeem")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public WrappedResponseVO reduceAmount(WrappedRequestVO request) {
		return paymentService.reduceAmount(request);
	}
	
	@Path("credit")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<InputPayment> getAllInputPayments() {
		return paymentService.getAllInputPayments();
	}
	
	@Path("transfer")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<TransferAmount> getAllTransferAmounts() {
		return paymentService.getAllTransferAmounts();
	}
	
	@Path("redeem")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ReduceAmount> getAllRedemtions() {
		return paymentService.getAllRedemtions();
	}
}
