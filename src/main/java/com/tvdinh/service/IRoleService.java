package com.tvdinh.service;

import java.util.List;
import java.util.Map;

import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.RoleEntity;

public interface IRoleService {
	void save(RoleEntity role);
	List<RoleDTO> findAll();
	RoleDTO findById(Long id);
	RoleDTO findByCode(String code);
	Map<String, String> findAllMap();
}
