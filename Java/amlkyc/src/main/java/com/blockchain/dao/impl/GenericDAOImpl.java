package com.blockchain.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.blockchain.dao.IGenericDAO;

public abstract class GenericDAOImpl<T extends Serializable, PK extends Serializable> implements IGenericDAO<T, PK> {
	
	@PersistenceContext
    protected EntityManager em;

    private final Class<T> type;
    
    @SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		final ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.type = (Class<T>) paramType.getActualTypeArguments()[0];
	}

	@Override
	public T create(T entity) {
		this.em.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		this.em.merge(entity);
		return entity;
	}

	@Override
	public T find(PK id) {
		return this.em.find(this.type, id);
	}

	@Override
	public List<T> findAll() {
		return this.em.createQuery("from " + this.type.getName(), this.type).getResultList();
	}

	@Override
	public void delete(T entity) {
		this.em.remove(this.em.contains(entity) ? entity: this.em.merge(entity));		
	}
	
	@Override
	public T queryForObject(String queryIdentifier, Map<String, ? extends Serializable> parameterMap) {
		final TypedQuery<T> query = this.em.createNamedQuery(queryIdentifier, this.type);
		for(Map.Entry<String, ? extends Serializable> parameter : parameterMap.entrySet()) {
			query.setParameter(parameter.getKey(), parameter.getValue());
		}
		return query.getSingleResult();
	}
	
	@Override
	public List<T> queryForList(String queryIdentifier, Map<String, ? extends Serializable> parameterMap) {
		final TypedQuery<T> query = this.em.createNamedQuery(queryIdentifier, this.type);
		for(Map.Entry<String, ? extends Serializable> parameter : parameterMap.entrySet()) {
			query.setParameter(parameter.getKey(), parameter.getValue());
		}
		return query.getResultList();
	}
}
