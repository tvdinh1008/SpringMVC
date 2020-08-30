package com.tvdinh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.converter.CustomerBeanUtil;
import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.dto.CustomerDTO;
import com.tvdinh.entity.CustomerEntity;
import com.tvdinh.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	/*
	 * Tầng trên gọi tầng dưới
	 */
	@Autowired
	private ICustomerDAO customerDAO;

	@Override
	public CustomerDTO findOneUserNameAndStatus(String username, int status) {
		CustomerEntity customerEntity=customerDAO.findOneUsernameAndStatus(username, status);
		return CustomerBeanUtil.entityToDTO(customerEntity);
	}
	
}
 