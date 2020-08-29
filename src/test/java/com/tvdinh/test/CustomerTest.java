package com.tvdinh.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.dao.impl.CustomerDAO;

import com.tvdinh.entity.CustomerEntity;
import com.tvdinh.entity.RoleEntity;



public class CustomerTest {
	
	
	@Test
	public void findAll()
	{
		//List<> là interface khởi tạo qua class là arraylist
		ICustomerDAO iCustomerDAO=new CustomerDAO();
		List<CustomerEntity> list=iCustomerDAO.findAll();
		System.out.println(list.size());
	}
	
	@Test
	public void checkFindID()
	{
		ICustomerDAO iCustomerDAO=new CustomerDAO();
		CustomerEntity cus=iCustomerDAO.findById(2L);
		System.out.println(cus);
	}
	
	@Test
	public void checkSave()
	{	
		ICustomerDAO iCustomerDAO=new CustomerDAO();
		CustomerEntity cus=new CustomerEntity();
		cus.setName("Trần Văn Định");
		cus.setUsername("dinh");
		cus.setPassword("123");
		
		RoleEntity role=new RoleEntity();
		role.setId(2L);
		cus.setRoleEntity(role);
	
		iCustomerDAO.save(cus);
	}
	
	 @Test 
	 public void checkUpdateCustomer() { 
	 	ICustomerDAO iCustomerDAO=new CustomerDAO();
	 	CustomerEntity cus=new CustomerEntity();
	 	cus.setId(1l);
	 	cus.setName("Trần Văn Định nhé");
		cus.setUsername("dinh");
		cus.setPassword("123");
	 	cus.setEmail("tvdinh@gmail.com");
	 	cus.setPhone("0456778");
	 	cus=iCustomerDAO.update(cus);
	 	
	 }
	 
	 @Test
	 public void checkDelete()
	 {
		 List<Long> listID=new ArrayList<Long>();
		// listID.add(1);
		// listID.add(2);
		 ICustomerDAO iCustomerDAO=new CustomerDAO();
		 Integer count=iCustomerDAO.delete(listID);
		 System.out.println(count);
	 }
	
}