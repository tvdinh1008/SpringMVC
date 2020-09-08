package com.tvdinh.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.entity.RoleEntity;

@Repository
public class RoleDAO extends AbstractDAO<Long, RoleEntity> implements IRoleDAO{

	@Override
	public RoleEntity findByCode(String code) {
		RoleEntity result=null;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			StringBuilder sql=new StringBuilder("");
			sql.append("Select * from role Where code=:code");
			Query q=entityManager.createNativeQuery(sql.toString(),RoleEntity.class);
			q.setParameter("code", code);
			result=(RoleEntity)q.getSingleResult();
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
