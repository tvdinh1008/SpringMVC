package com.tvdinh.converter;

import com.tvdinh.dto.CustomerDTO;
import com.tvdinh.entity.CustomerEntity;

public class CustomerBeanUtil {
	
	public static CustomerDTO entityToDTO(CustomerEntity entity) {
		CustomerDTO dto=new CustomerDTO();
		dto.setCustomerId(entity.getCustomerId());
		dto.setUsername(entity.getUsername());
		
		
		dto.setRoleDTO(RoleBeanUtil.enityToDTO(entity.getRoleEntity()));
		return dto;
	}
	
	public static CustomerEntity dtotoEntity(CustomerDTO dto) {
		CustomerEntity entity=new CustomerEntity();
		entity.setCustomerId(dto.getCustomerId());
		
		entity.setRoleEntity(RoleBeanUtil.dtoToEnitity(dto.getRoleDTO()));
		return entity;
	}
}
