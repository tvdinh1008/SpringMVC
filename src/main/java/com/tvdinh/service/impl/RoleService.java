package com.tvdinh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.RoleEntity;
import com.tvdinh.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	public void save(RoleEntity role)
	{
		roleDAO.save(role);
	}
	
	
}
