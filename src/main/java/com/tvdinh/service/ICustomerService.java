package com.tvdinh.service;

import java.util.Map;

import com.tvdinh.dto.CustomerDTO;

public interface ICustomerService {
	CustomerDTO findOneUserNameAndStatus(String username,int status);
	CustomerDTO save(CustomerDTO customerDTO);
	CustomerDTO findById(Long id);
	Object[] findByProperties(Map<String, Object> property, Integer offset, Integer limit, String sortExpression, String sortDirection);
	Boolean deleteById(Long[] ids);
}
