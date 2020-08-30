package com.tvdinh.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<ID extends Serializable, T> {
	List<T> findAll();
	List<T> findRange(int firstResult, int maxResults);
	T update(T entity);
	void save(T entity);
	T findById(ID var1);
	Integer delete(List<ID> ids);
	Long count();
}
