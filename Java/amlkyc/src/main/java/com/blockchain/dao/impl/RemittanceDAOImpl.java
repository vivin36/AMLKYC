package com.blockchain.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.blockchain.dao.IRemittanceDAO;
import com.blockchain.entity.Remittance;

@Repository
public class RemittanceDAOImpl extends GenericDAOImpl<Remittance, Long> implements IRemittanceDAO {

	public RemittanceDAOImpl() {
		setType(Remittance.class);
	}
	
	@Override
	public Remittance saveRemittance(Remittance remittance) {
		return create(remittance);
	}

	@Override
	public Remittance updateRemittance(Remittance remittance) {
		return update(remittance);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Remittance> getAllRemittancesByStatus(String status) {
		Query query = em.createQuery("FROM Remittance r WHERE r.status = :status");
		query.setParameter("status", status);
		
		return query.getResultList();
	}
}
