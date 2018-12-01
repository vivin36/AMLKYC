package com.blockchain.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.blockchain.dao.IRemittanceDAO;
import com.blockchain.entity.Remittance;

@Repository
public class RemittanceDAOImpl extends GenericDAOImpl<Remittance, Long> implements IRemittanceDAO {

	@Override
	public Remittance saveRemittance(Remittance remittance) {
		return create(remittance);
	}

	@Override
	public Remittance updateRemittance(Remittance remittance) {
		return update(remittance);
	}

	@Override
	public List<Remittance> getAllRemittancesByStatus(String status) {
		final Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("status", status);
		
		return queryForList("remittance.getAllRemittancesByStatus", parameterMap);
	}
}
