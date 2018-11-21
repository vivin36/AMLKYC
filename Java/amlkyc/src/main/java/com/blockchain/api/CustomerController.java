package com.blockchain.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.ICustomerService;
import com.blockchain.vo.CustomerVO;

@Component
@Path("/customers")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CustomerVO createCustomer(CustomerVO customerVO) {
		return customerService.createCustomer(customerVO);
	}
	
}
