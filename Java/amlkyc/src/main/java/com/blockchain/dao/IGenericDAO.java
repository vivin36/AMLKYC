package com.blockchain.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, PK extends Serializable> {
	
	public T create(T entity);
	
	public T update(T entity);
	
	public T find(PK id);
	
	public List<T> findAll();
	
	public void delete(PK id);
}
