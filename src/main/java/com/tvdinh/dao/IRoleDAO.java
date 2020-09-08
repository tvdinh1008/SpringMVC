package com.tvdinh.dao;

import com.tvdinh.entity.RoleEntity;

public interface IRoleDAO extends GenericDAO<Long, RoleEntity>{
	RoleEntity findByCode(String code);
}
