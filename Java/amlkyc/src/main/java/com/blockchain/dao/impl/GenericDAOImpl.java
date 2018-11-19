package com.blockchain.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.blockchain.dao.IGenericDAO;

public class GenericDAOImpl<T, PK extends Serializable> implements IGenericDAO<T, PK> {
	
	@PersistenceContext
    protected EntityManager em;

    private Class<T> type;
    
    public void setType( Class<T> type) {
        this.type = type;
     }

	@Override
	public T create(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		em.merge(entity);
		return entity;
	}

	@Override
	public T find(PK id) {
		return em.find(type, id);
	}

	@Override
	public List<T> findAll() {
		return em.createQuery("from " + type.getName()).getResultList();
	}

	@Override
	public void delete(PK id) {
		em.remove(em.getReference(type, id));		
	}
	

}
