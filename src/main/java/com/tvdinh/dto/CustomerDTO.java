package com.tvdinh.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/*
 * Serializable là một cơ chế để ghi trạng thái của một đối tượng vào một byte stream.chủ yếu được sử dụng để truyền trạng thái của đối tượng qua mạng
 */
public class CustomerDTO extends AbstractDTO implements Serializable{
	private static final long serialVersionUID = -6922440211921721035L;
	
	private String name;
	private String username;
	private String email;
	private String password;
	private String phone;
	private String address;
	private int status;
	private List<RoleDTO> roles=new ArrayList<>();
	
	
	public List<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
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
	

}
