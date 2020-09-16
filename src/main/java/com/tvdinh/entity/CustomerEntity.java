package com.tvdinh.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity{
	
	@Column(name="name", columnDefinition = "nvarchar(250)")
	private String name;
	
	@Column(name="username", columnDefinition = "nvarchar(250) NOT NULL UNIQUE")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address", columnDefinition = "nvarchar(250)")
	private String address;
	
	@Column(name="status")
	private int status;
	
	/*
	 * bảng: customer_role sẽ là bảng trung gian vì 1 user có nhiều Role và 1 role cũng cho nhiều user
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="customer_role", joinColumns = @JoinColumn(name = "userid",nullable = false,updatable = false),
	inverseJoinColumns = @JoinColumn(name = "roleid",nullable = false,updatable = false))
	private List<RoleEntity> roles = new ArrayList<>();
	
	
	
//	@ManyToOne(optional = false) //kiểm tra ràng buộc nếu không nó cho insert cả null
//	@JoinColumn(name="roleid")
//	private RoleEntity roleEntity;
//	
	
//	public RoleEntity getRoleEntity() {
//		return roleEntity;
//	}
//
//	public void setRoleEntity(RoleEntity roleEntity) {
//		this.roleEntity = roleEntity;
//	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
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

	/*
	 * public Long getRoleid() { return roleid; }
	 * 
	 * public void setRoleid(Long roleid) { this.roleid = roleid; }
	 */
	

}
