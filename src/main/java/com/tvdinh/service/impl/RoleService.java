package com.tvdinh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.entity.RoleEntity;
import com.tvdinh.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public void save(RoleEntity role)
	{
		roleRepository.save(role);
	}
	
	
}
