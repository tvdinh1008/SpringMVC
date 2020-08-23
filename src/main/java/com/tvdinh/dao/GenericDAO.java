package com.tvdinh.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<ID extends Serializable, T> {
	
	List<T> findAll();
	T update(T entity);
	void save(T entity);
	T findById(ID var1);
	Integer delete(List<ID> ids);
}
