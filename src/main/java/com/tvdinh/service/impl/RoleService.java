package com.tvdinh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.converter.RoleBeanUtil;
import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.RoleEntity;
import com.tvdinh.service.IRoleService;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleDAO roleDAO;

	@Override
	public void save(RoleEntity role) {
		roleDAO.save(role);
	}

	@Override
	public List<RoleDTO> findAll() {
		List<RoleDTO> list=new ArrayList<RoleDTO>();
		for(RoleEntity item:roleDAO.findAll()) {
			list.add(RoleBeanUtil.enityToDTO(item));
		}
		return list;
	}

	@Override
	public RoleDTO findById(Long id) {
		return RoleBeanUtil.enityToDTO(roleDAO.findById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> findAllMap() {
		Map<String,String> result=new HashedMap();
		for(RoleEntity item:roleDAO.findAll()) {
			result.put(item.getCode(), item.getName());
		}
		return result;
	}

	@Override
	public RoleDTO findByCode(String code) {
		return RoleBeanUtil.enityToDTO(roleDAO.findByCode(code));
	}
	
}
