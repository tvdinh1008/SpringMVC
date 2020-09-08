package com.tvdinh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.RoleEntity;
import com.tvdinh.repository.RoleRepository;
import com.tvdinh.service.IRoleService;

@Service
public class RoleService implements IRoleService{
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private IRoleDAO roleDAO;

	@Override
	public void save(RoleEntity role) {
		roleDAO.save(role);
	}
	
	
	
}
