package com.tvdinh.dao.impl;

import javax.persistence.Query;

import com.tvdinh.dao.ICategoryDAO;
import com.tvdinh.entity.CategoryEntity;

public class CategoryDAO extends AbstractDAO<Long, CategoryEntity> implements ICategoryDAO{
	@Override
	public CategoryEntity findByCode(String code) {
		CategoryEntity result=null;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			StringBuilder sql=new StringBuilder("");
			sql.append("Select * from category Where code=:code");
			Query q=entityManager.createNativeQuery(sql.toString(),CategoryEntity.class);
			q.setParameter("code", code);
			result=(CategoryEntity)q.getSingleResult();
			entityManager.getTransaction().commit();
		} catch(Exception e) {   
			entityManager.getTransaction().rollback();
			logger.error(e.getMessage(),e);
		} finally {
			close();
		}
		return result;
	}
}
