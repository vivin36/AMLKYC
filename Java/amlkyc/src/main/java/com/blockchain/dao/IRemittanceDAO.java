package com.blockchain.dao;

import java.util.List;

import com.blockchain.entity.Remittance;

public interface IRemittanceDAO extends IGenericDAO<Remittance, Long> {

	public Remittance saveRemittance(Remittance remittance);
	
	public Remittance updateRemittance(Remittance remittance);

	public List<Remittance> getAllRemittancesByStatus(String status);	
}
