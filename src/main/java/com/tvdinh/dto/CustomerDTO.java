package com.tvdinh.dto;

import java.io.Serializable;



/*
 * Serializable là một cơ chế để ghi trạng thái của một đối tượng vào một byte stream.chủ yếu được sử dụng để truyền trạng thái của đối tượng qua mạng
 */
public class CustomerDTO implements Serializable{
	private static final long serialVersionUID = -6922440211921721035L;
	
    private Long customerId;
	private String name;
	private String username;
	private String email;
	private String password;
	private String phone;
	private String address;
	private int status;
	//private Long roleid;
	private RoleDTO roleDTO;
	
	
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}