package com.tvdinh.converter;

import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.RoleEntity;

public class RoleBeanUtil {
	
	public static RoleDTO enityToDTO(RoleEntity entity) {
		RoleDTO dto=new RoleDTO();
		try {
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCode(entity.getCode());
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return dto;
	}
	public static RoleEntity dtoToEnitity(RoleDTO dto) {
		RoleEntity entity=new RoleEntity();
		try {
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return entity;
	}
	
}
