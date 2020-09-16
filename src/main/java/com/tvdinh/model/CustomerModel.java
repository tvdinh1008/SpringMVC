package com.tvdinh.model;

import com.tvdinh.dto.CustomerDTO;

public class CustomerModel extends AbstractModel<CustomerDTO>{
	
	public CustomerModel() {
		this.pojo=new CustomerDTO();
	}
	
	private String confirmPassword;
	private String roleCode[];
	
	
	public String[] getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String[] roleCode) {
		this.roleCode = roleCode;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
