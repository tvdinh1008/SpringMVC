package com.tvdinh.dto;

import java.io.Serializable;

public class RoleDTO extends AbstractDTO implements Serializable{
	private static final long serialVersionUID = 5746428186712626046L;
	
	private String name;
	private String code;
	
	
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
