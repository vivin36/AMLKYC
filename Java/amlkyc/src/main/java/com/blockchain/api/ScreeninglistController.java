package com.blockchain.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.vo.ScreeninglistVO;

@Component
@Path("/screeninglists")
public class ScreeninglistController {

	@Autowired
	private IScreeninglistService screeninglistService;

	@Path("/blacklists")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ScreeninglistVO addtoBlackListCustomer(ScreeninglistVO screeninglistVO) {
		return screeninglistService.addToBlackListedCustomer(screeninglistVO);
	}

	@Path("/blacklists")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ScreeninglistVO> getAllblackListCustomers() {
		return screeninglistService.getAllblackListCustomersAddress();
	}

	@Path("/whitelists")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ScreeninglistVO> getAllwhiteListCustomers() {
		return screeninglistService.getAllwhiteListCustomersAddress();
	}

	@Path("/whitelists")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ScreeninglistVO createWhiteListCustomers(ScreeninglistVO screeninglistVO) {
		return screeninglistService.addToWhiteListedCustomer(screeninglistVO);	
	}
	
	@Path("/whitelists/{accountAddress}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public boolean checkIsWhiteListed(@PathParam("accountAddress") String accountAddress) {
		return screeninglistService.checkIsWhiteListed(accountAddress);
	}
	
	@Path("/blacklists/{accountAddress}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public boolean checkIsBlacklists(@PathParam("accountAddress") String accountAddress) {
		return screeninglistService.checkIsBlackListed(accountAddress);
	}
	
	@Path("/blacklists/{accountAddress}")
	@DELETE
	public String removeFromBlackList(@PathParam("accountAddress") String accountAddress) {
		 screeninglistService.removeFromBlackList(accountAddress);
		 return accountAddress;
	}
	
	@Path("/whitelists/{accountAddress}")
	@DELETE
	public String removeFromWhiteList(@PathParam("accountAddress") String accountAddress) {
		 screeninglistService.removeFromWhiteList(accountAddress);
		 return accountAddress;
	}




}
