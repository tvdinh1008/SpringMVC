package com.tvdinh.dao;


import java.util.List;

import com.tvdinh.entity.CustomerEntity;

public interface ICustomerDAO extends GenericDAO<Long, CustomerEntity>{
	CustomerEntity findOneUsernameAndStatus(String username,int status);
	List<CustomerEntity> findAllCus();
}
