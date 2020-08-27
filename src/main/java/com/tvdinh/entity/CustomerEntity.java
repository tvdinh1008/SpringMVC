package com.tvdinh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/*
 * Để sử dụng 4 cái @LastModifiedDate,@CreatedDate,@CreatedBy,@LastModifiedBy thì cần cấu hình
 */


/*
    FetchType mặc định JPA: EAGER tự động tìm các bảng liên quan đến khóa và đổ dữ liệu vào, LAZY không thuộc tính đó =null
	OneToMany: LAZY
	ManyToOne: EAGER
	ManyToMany: LAZY
	OneToOne: EAGER
 */

@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity{
	
	@Column(name="name", columnDefinition = "nvarchar(250)")
	private String name;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;
	
	@Column(name="status")
	private int status;
	
	
	@ManyToOne(optional = false) //kiểm tra ràng buộc nếu không nó cho insert cả null
	@JoinColumn(name="roleid")
	private RoleEntity roleEntity;
	
	
	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
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
