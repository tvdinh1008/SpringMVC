package com.tvdinh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.converter.CustomerBeanUtil;
import com.tvdinh.converter.RoleBeanUtil;
import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.dto.CustomerDTO;
import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.CustomerEntity;
import com.tvdinh.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	/*
	 * Tầng trên gọi tầng dưới
	 */
	@Autowired
	private ICustomerDAO customerDAO;
	@Autowired
	private IRoleDAO roleDAO;
	
	@Override
	public CustomerDTO findOneUserNameAndStatus(String username, int status) {
		CustomerEntity customerEntity=customerDAO.findOneUsernameAndStatus(username, status);
		return CustomerBeanUtil.entityToDTO(customerEntity);
	}

	@Override
	public CustomerDTO save(CustomerDTO customerDTO) {
		CustomerDTO cus=null;
		try {
			if(customerDTO.getStatus()==0) {
				customerDTO.setStatus(1);
			}
			if(customerDTO.getRoles().size()==0)
			{
				RoleDTO role=RoleBeanUtil.enityToDTO(roleDAO.findByCode("TT"));	
				customerDTO.getRoles().add(role);
			}
			cus=CustomerBeanUtil.entityToDTO(customerDAO.save(CustomerBeanUtil.dtotoEntity(customerDTO)));
		} catch (Exception e) {
			System.out.println(e.getMessage());//TH lưu lỗi
		}
		return cus;
	}

	/*
	 * Hàm này trả về các phần tử trong 1 page và tổng số item
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findByProperties(Map<String, Object> property, Integer offset, Integer limit,
			String sortExpression, String sortDirection) {
		
		List<CustomerDTO> result=new ArrayList<CustomerDTO>();
		
		Object[] objects=customerDAO.findByProperties(property, offset, limit, sortExpression, sortDirection);
		for(CustomerEntity item:(List<CustomerEntity>)objects[1]) {
			CustomerDTO dto=CustomerBeanUtil.entityToDTO(item);
			result.add(dto);
		}
		objects[1]=result;
		return objects;
	}
	
}
 