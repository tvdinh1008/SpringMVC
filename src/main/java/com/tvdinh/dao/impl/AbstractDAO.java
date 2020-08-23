package com.tvdinh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tvdinh.dao.GenericDAO;

import javassist.tools.rmi.ObjectNotFoundException;

public class AbstractDAO<ID extends Serializable, T> implements GenericDAO<ID,T>{
	
	protected EntityManagerFactory factory;
	protected EntityManager entityManager;
	private Class<T> persistenceClass; //model.class
	
	
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.persistenceClass=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	//lấy tên class truyền vào
	public String getPersistenceClassName() {
		return persistenceClass.getSimpleName();
	}
	
	
	protected void openEntityManager() {
		factory=Persistence.createEntityManagerFactory("persistence-data");//persistenceUnitName là file để mapp giữ java và database
		entityManager=factory.createEntityManager();
	}
	
	protected void close() {
		entityManager.close();
		factory.close();
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T update(T entity) {
		T result=null;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			Object object=entityManager.merge(entity);
			entityManager.getTransaction().commit();
			result=(T) object;
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return result;
	}

	@Override
	public void save(T entity) {
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID var1) {
		T result=null;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			Object object=entityManager.find(persistenceClass, var1);
			entityManager.getTransaction().commit();
			result=(T) object;
			if(result==null)
			{
				throw new ObjectNotFoundException("not found "+ var1, null);
			}
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return result;
	}

}
