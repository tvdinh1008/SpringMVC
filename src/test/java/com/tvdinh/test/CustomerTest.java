package com.tvdinh.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	public void findAllCus()
	{
		//List<> là interface khởi tạo qua class là arraylist
		ICustomerDAO iCustomerDAO=new CustomerDAO();
		List<CustomerEntity> list=iCustomerDAO.findAllCus();
		list=iCustomerDAO.findRange(1, 1);
		System.out.println(list.size());
	}
	

	@Test
	public void checkFindUsernameAndStatus()
	{
		ICustomerDAO iCustomerDAO=new CustomerDAO();
		CustomerEntity cus=iCustomerDAO.findOneUsernameAndStatus("dinh", 1);
		System.out.println(cus);
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
		cus.setUsername("dinh1");
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		cus.setPassword(passwordEncoder.encode("123"));
		
		cus.setStatus(1);
		RoleEntity role=new RoleEntity();
		role.setId(2L);
		cus.getRoles().add(role);
		iCustomerDAO.save(cus);
	}
	
	 @Test 
	 public void checkUpdateCustomer() { 
	 	ICustomerDAO iCustomerDAO=new CustomerDAO();
	 	CustomerEntity cus=new CustomerEntity();
	 	cus.setId(2l);
	 	cus.setName("Trần Minh Châu");
		cus.setUsername("chau");
		cus.setPassword("123");
	 	cus.setEmail("tvdinh@gmail.com");
	 	cus.setPhone("0456778");
	 	cus.setStatus(1);
	 	RoleEntity role=new RoleEntity();
		role.setId(2L);
	 	cus.getRoles().add(role);
	 	cus=iCustomerDAO.update(cus);
	 }
	 
	 @Test
	 public void checkDelete()
	 {
		 List<Long> listID=new ArrayList<Long>();
		// listID.add(1);
		 listID.add(4L);
		 ICustomerDAO iCustomerDAO=new CustomerDAO();
		 Integer count=iCustomerDAO.delete(listID);
		 System.out.println(count);
	 }
	
}
