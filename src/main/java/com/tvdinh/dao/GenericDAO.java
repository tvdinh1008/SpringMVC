package com.tvdinh.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<ID extends Serializable, T> {
	List<T> findAll();
	List<T> findRange(int firstResult, int maxResults);
	T update(T entity);
	T save(T entity);
	T findById(ID var1);
	Integer delete(ID[] ids);
	Long count();
	Object[] findByProperties(Map<String, Object> property, Integer offset, Integer limit, String sortExpression, String sortDirection, String JOIN_FETCH);
}
