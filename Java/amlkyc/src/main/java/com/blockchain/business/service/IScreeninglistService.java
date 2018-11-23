package com.blockchain.business.service;

import java.util.List;

import com.blockchain.vo.ScreeninglistVO;

public interface IScreeninglistService {

	public ScreeninglistVO addToBlackListedCustomer(ScreeninglistVO screeninglistVO);
	public ScreeninglistVO addToWhiteListedCustomer(ScreeninglistVO screeninglistVO);
	public List<ScreeninglistVO> getAllwhiteListCustomers();
	public List<ScreeninglistVO> getAllblackListCustomers();
	public boolean checkIsWhiteListed(String accountAddress);
	public boolean checkIsBlackListed(String accountAddress);
	public String removeFromBlackList(String accountAddress);
	public void removeFromWhiteList(String accountAddress);
	public List<ScreeninglistVO> getAllwhiteListCustomersAddress();
	public List<ScreeninglistVO> getAllblackListCustomersAddress();
}
