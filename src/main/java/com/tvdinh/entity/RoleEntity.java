package com.tvdinh.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name="role")
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roleid")
	private Long roleId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@Column(name="createddate")
	@CreatedDate
	private Timestamp createdDate;
	
	@Column(name="modifieddate")
	@LastModifiedDate
	private Timestamp modifiedDate;
	
	@Column(name="createdby")
	@CreatedBy
	private Long createdBy;
	
	@Column(name="modifiedby")
	@LastModifiedBy
	private Long modifiedBy;
	
	/*
	 * ví dụ List:"a,v,d,s"=>role:admin truy vấn admin thì lấy tất cả list user có role admin
	
	 * FetchType.EAGER: Khi truy vấn role thì nó lấy cả list lên
	 * FetchType.LAZY: Khi truy vấn role thì khi cần nó mới lấy list lên
	 * mappedBy sẽ mapp đúng với bên CustomerEntity
	 */
	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY)
	private List<CustomerEntity> customerList;
	
	
	
	
	public List<CustomerEntity> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerEntity> customerList) {
		this.customerList = customerList;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

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
