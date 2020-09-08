package com.tvdinh.converter;

import com.tvdinh.dto.CustomerDTO;
import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.CustomerEntity;
import com.tvdinh.entity.RoleEntity;

public class CustomerBeanUtil {
	
	public static CustomerDTO entityToDTO(CustomerEntity entity) {
		CustomerDTO dto=new CustomerDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		dto.setEmail(entity.getEmail());
		dto.setAddress(entity.getAddress());
		dto.setStatus(entity.getStatus());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		try {
			for(RoleEntity role:entity.getRoles())
			{
				dto.getRoles().add(RoleBeanUtil.enityToDTO(role));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}
	
	public static CustomerEntity dtotoEntity(CustomerDTO dto) {
		CustomerEntity entity=new CustomerEntity();
		entity.setId(dto.getId());
		entity.setUsername(dto.getUsername());
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		entity.setEmail(dto.getEmail());
		entity.setAddress(dto.getAddress());
		entity.setStatus(dto.getStatus());
		try {
			for(RoleDTO role:dto.getRoles())
			{
				entity.getRoles().add(RoleBeanUtil.dtoToEnitity(role));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return entity;
	}
}
