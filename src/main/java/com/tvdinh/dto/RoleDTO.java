package com.tvdinh.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable{
	private static final long serialVersionUID = 5746428186712626046L;
	
	
	private Long roleId;
	private String name;
	private String code;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
