package com.tvdinh.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.entity.CustomerEntity;

@Repository
public class CustomerDAO extends AbstractDAO<Long, CustomerEntity> implements ICustomerDAO {

	@Override
	public CustomerEntity findOneUsernameAndStatus(String username, int status) {
		CustomerEntity result=null;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			
			StringBuilder sql=new StringBuilder("");
			sql.append("Select * from customer Where username=:username and status=:status");
			
			Query q=entityManager.createNativeQuery(sql.toString(),CustomerEntity.class);
			q.setParameter("username", username);
			q.setParameter("status", status);
			result=(CustomerEntity)q.getSingleResult();
			//cố tình lấy roles thì nó sẽ tạo ra 1 câu truy vấn tiếp(Tức sẽ có 2 câu truy vấn) khi sử dụng lazy.
			//Còn sử dụng EAGER thì 1 câu truy vấn và nó sẽ đổ luôn dữ liệu roles luôn
			result.getRoles().size(); 
			entityManager.getTransaction().commit();
		} catch(Exception e) {   
			entityManager.getTransaction().rollback();
			logger.error(e.getMessage(),e);
		} finally {
			close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> findAllCus() {
		List<CustomerEntity> result=new ArrayList<>();
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			String sql="select t from "+ getPersistenceClassName()+ " t";
			result=entityManager.createQuery(sql).getResultList();
			if(result.size()!=0)
			{
				result.get(0).getRoles().size();//do để Fetch.LAZY lên để load roles thì cần getter cái roles
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error(e.getMessage(),e);
		} finally {
			close();
		}
		return result;
	}
}
