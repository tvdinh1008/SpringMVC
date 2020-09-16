package com.tvdinh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import com.tvdinh.dao.GenericDAO;

import javassist.tools.rmi.ObjectNotFoundException;

public class AbstractDAO<ID extends Serializable, T> implements GenericDAO<ID, T> {

	protected EntityManagerFactory factory;
	protected EntityManager entityManager;
	private Class<T> persistenceClass; // model.class
	protected final Logger logger = Logger.getLogger(this.getClass());// chú ý file config: resouces/log4j.properties

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	// lấy tên class truyền vào
	public String getPersistenceClassName() {
		return persistenceClass.getSimpleName();
	}

	protected void openEntityManager() {
		factory = Persistence.createEntityManagerFactory("persistence-data");// persistenceUnitName là file để mapp giữ
																				// java và database
		entityManager = factory.createEntityManager();
	}

	protected void close() {
		entityManager.close();
		factory.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		List<T> result = new ArrayList<T>();
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			String sql = "select t from " + getPersistenceClassName() + " t";
			result = entityManager.createQuery(sql).getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error(e.getMessage(), e);
		} finally {
			close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T update(T entity) {
		T result = null;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			Object object = entityManager.merge(entity);
			entityManager.getTransaction().commit();
			result = (T) object;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			// System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			close();
		}
		return result;
	}

	@Override
	public T save(T entity) {
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			// System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			close();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID var1) {
		T result = null;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			Object object = entityManager.find(persistenceClass, var1);
			entityManager.getTransaction().commit();
			result = (T) object;
			if (result == null) {
				throw new ObjectNotFoundException("not found " + var1, null);
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			// System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			close();
		}
		return result;
	}

	/*
	 * Khi có quan hệ ví dụ n-n thì nó không xóa đc
	 */
	@Override
	public Integer delete(ID[] ids) {
		Integer count = 0;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			for (ID id : ids) {
				T entity = (T) entityManager.find(persistenceClass, id);
				entityManager.remove(entity);
				count++;
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			// System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			close();
		}
		return count;// nếu count==list.size thì chứng tỏ xóa thành công
	}

	@Override
	public Long count() {
		Long count = (long) 0;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			String sql = "select count(t) from " + getPersistenceClassName() + " t";
			count = (long) entityManager.createQuery(sql).getSingleResult();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error(e.getMessage(), e);
		} finally {
			close();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findRange(int firstResult, int maxResults) {
		List<T> result = new ArrayList<T>();
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			String sql = "select t from " + getPersistenceClassName() + " t";
			result = entityManager.createQuery(sql).setFirstResult(firstResult).setMaxResults(maxResults)
					.getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error(e.getMessage(), e);
		} finally {
			close();
		}
		return result;
	}

	public List<T> findUsername(String username) {

		return null;
	}

	/*
	 * property:key tên trường muốn tìm kiếm. value: giá trị tìm kiếm
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object[] findByProperties(Map<String, Object> property, Integer offset, Integer limit, String sortExpression,
			String sortDirection) {
		List<T> result = new ArrayList<T>();
		Long count = 0l;
		openEntityManager();
		entityManager.getTransaction().begin();
		try {
			String sql = "select t from " + getPersistenceClassName() + " t";
			result = entityManager.createQuery(sql).setFirstResult(offset).setMaxResults(limit).getResultList();

			String params[] = new String[property.size()];
			Object values[] = new Object[property.size()];
			int i = 0;
			for (Map.Entry item : property.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}

			StringBuilder sql1 = new StringBuilder("SELECT t FROM " + getPersistenceClassName() + " t");
			for (int j = 0; j < property.size(); j++) {
				if (j == 0) {
					sql1.append(" where ").append(params[j]).append("=:" + params[j]);
				} else {
					sql1.append(" and ").append(params[j]).append("=:" + params[j]);
				}
			}
			if(StringUtils.isNotBlank(sortExpression) && StringUtils.isNotBlank(sortDirection)) {
				sql1.append(" ORDER BY ").append(sortExpression);
				sql1.append(" "+ (sortDirection.equals("ASC")?"ASC":"DESC"));
			}
			Query q = entityManager.createQuery(sql1.toString()).setFirstResult(offset).setMaxResults(limit);
			for(int j=0;j<property.size();j++) {
				q.setParameter(params[j], values[j]);
			}
			result=q.getResultList();
			
			String sql2 = "SELECT count(t) FROM " + getPersistenceClassName() + " t";
			count = (Long) entityManager.createQuery(sql2).getSingleResult();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			logger.error(e.getMessage(), e);
		} finally {
			close();
		}
		return new Object[] { count, result };
	}
}
