package com.tvdinh.test;

import java.sql.Timestamp;

import org.testng.annotations.Test;

import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.dao.impl.RoleDAO;
import com.tvdinh.entity.RoleEntity;

public class RoleTest {
	
	@Test
	public void checkSave()
	{
		IRoleDAO roleDAO=new RoleDAO();
		RoleEntity role=new RoleEntity();
		//role.setCode("ADMIN");
		//role.setName("admin");
		role.setCode("USER");
		role.setName("user");
		role.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		roleDAO.save(role);
	}
}
