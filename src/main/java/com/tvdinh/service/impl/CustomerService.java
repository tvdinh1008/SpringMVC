package com.tvdinh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	/*
	 * Tầng trên gọi tầng dưới
	 */
	@Autowired
	private ICustomerDAO customerDAO;
	
	
	

}
 