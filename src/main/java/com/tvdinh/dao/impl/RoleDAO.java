package com.tvdinh.dao.impl;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.entity.RoleEntity;

@Repository
public class RoleDAO extends AbstractDAO<Long, RoleEntity> implements IRoleDAO{
	
}
