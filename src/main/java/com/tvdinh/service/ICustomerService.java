package com.tvdinh.service;

import com.tvdinh.dto.CustomerDTO;

public interface ICustomerService {
	CustomerDTO findOneUserNameAndStatus(String username,int status);
}
