package com.blockchain.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public CustomerVO updateCustomer(CustomerVO customerVO) {
		return customerService.updateCustomer(customerVO);
	}
	
	@Path("/{address}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public CustomerVO getCustomerByAddress(@PathParam("address") String address) {
		return customerService.getCustomerByAddress(address);
	}
	
	@Path("/addresses")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<String> getAllCustomerAddresses() {
		return customerService.getAllCustomerAddresses();
	}
}
