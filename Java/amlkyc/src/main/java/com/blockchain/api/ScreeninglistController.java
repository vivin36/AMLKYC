package com.blockchain.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blockchain.business.service.IScreeninglistService;
import com.blockchain.vo.CustomerVO;
import com.blockchain.vo.ScreeninglistVO;

@Component
@Path("/screeninglists")
public class ScreeninglistController {
	
	@Autowired
	private IScreeninglistService  screeninglistService;
	
	@Path("/blacklists")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ScreeninglistVO addtoBlackListCustomer(ScreeninglistVO screeninglistVO) {
		return screeninglistService.addToBlackListedCustomer(screeninglistVO);
	}
	
	 
}
