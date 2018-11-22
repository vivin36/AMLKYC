package com.blockchain.business.service;

import com.blockchain.vo.CustomerVO;

public interface ICustomerService {

	public CustomerVO createCustomer(CustomerVO customerVO);

	public CustomerVO updateCustomer(CustomerVO customerVO);

	public CustomerVO getCustomerByAddress(String address);
	
}
