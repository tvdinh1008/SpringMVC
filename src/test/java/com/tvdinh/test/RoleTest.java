package com.tvdinh.test;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.dao.impl.RoleDAO;
import com.tvdinh.entity.RoleEntity;
import com.tvdinh.repository.RoleRepository;
import com.tvdinh.service.impl.RoleService;

public class RoleTest {
	
	private RoleRepository roleRepository;
	
	
	@Test
	public void checkSave()
	{
		RoleService rep=new RoleService();
		IRoleDAO roleDAO=new RoleDAO();
		RoleEntity role=new RoleEntity();
		//role.setCode("ADMIN");
		//role.setName("admin");
		role.setCode("USER");
		role.setName("user");
		//role.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		roleRepository=rep.getRoleRepository();
		roleDAO.save(role);
	}
}
