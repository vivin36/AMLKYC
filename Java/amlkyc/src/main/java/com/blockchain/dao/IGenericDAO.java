package com.blockchain.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericDAO<T extends Serializable, PK extends Serializable> {
	
	public T create(T entity);
	
	public T update(T entity);
	
	public T find(PK id);
	
	public List<T> findAll();
	
	public void delete(T entity);
	
	public T queryForObject(String queryIdentifier, Map<String, ? extends Serializable> parameterMap);
	
	public List<T> queryForList(String queryIdentifier, Map<String, ? extends Serializable> parameterMap);
}
