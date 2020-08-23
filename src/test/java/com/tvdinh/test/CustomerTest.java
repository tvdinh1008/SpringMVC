package com.tvdinh.test;




import org.testng.annotations.Test;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.dao.impl.CustomerDAO;

import com.tvdinh.entity.CustomerEntity;



public class CustomerTest {
	
	
	@Test
	public void checkFindAll()
	{
		//List<> là interface khởi tạo qua class là arraylist
		ICustomerDAO iCustomerDAO=new CustomerDAO();
		//List<CustomerEntity> list=iCustomerDAO.findAll();
		CustomerEntity cus=new CustomerEntity();
		cus.setName("Trần Văn Định");
		cus.setUsername("dinh");
		cus.setPassword("123");
		iCustomerDAO.save(cus);
		System.out.println(cus);
		/*
		 * RoleDAO role=new RoleDAO(); role.setName("Trần Văn Định");
		 * 
		 * String k=role.getName(); System.out.println(k);
		 */
	}
	
	
	 @Test 
	 public void checkUpdateCustomer() { 
	 	ICustomerDAO iCustomerDAO=new CustomerDAO();
	 	CustomerEntity cus=new CustomerEntity();
	 	cus.setId(1L);
	 	cus.setName("Trần Văn Định nhé");
		cus.setUsername("dinh");
		cus.setPassword("123");
	 	cus.setEmail("tvdinh@gmail.com");
	 	cus.setPhone("0456778");
	 	cus=iCustomerDAO.update(cus);
	 	
	 }
	 
	
}
