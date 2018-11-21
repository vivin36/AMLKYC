package com.blockchain.vo;

public class CustomerVO {
	
	private String address;
	private String account;
	private String name;
	private Integer customerType;
	private Boolean isParentCustomer;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCustomerType() {
		return customerType;
	}
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	public Boolean getIsParentCustomer() {
		return isParentCustomer;
	}
	public void setIsParentCustomer(Boolean isParentCustomer) {
		this.isParentCustomer = isParentCustomer;
	}		
}
