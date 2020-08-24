package com.tvdinh.converter;

import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.RoleEntity;

public class RoleBeanUtil {
	
	public static RoleDTO enityToDTO(RoleEntity entity) {
		RoleDTO dto=new RoleDTO();
		dto.setRoleId(entity.getRoleId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		return dto;
	}
	public static RoleEntity dtoToEnitity(RoleDTO dto) {
		RoleEntity entity=new RoleEntity();
		entity.setRoleId(dto.getRoleId());
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());	
		return entity;
	}
	
}
