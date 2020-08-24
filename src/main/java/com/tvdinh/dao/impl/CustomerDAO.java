package com.tvdinh.dao.impl;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.entity.CustomerEntity;


@Repository
public class CustomerDAO extends AbstractDAO<Long, CustomerEntity> implements ICustomerDAO {

}
