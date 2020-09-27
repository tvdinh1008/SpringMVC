package com.tvdinh.dao;

import com.tvdinh.entity.CategoryEntity;

public interface ICategoryDAO extends GenericDAO<Long, CategoryEntity>{
	public CategoryEntity findByCode(String code);
}
