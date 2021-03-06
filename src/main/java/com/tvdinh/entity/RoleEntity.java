package com.tvdinh.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="role")
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity extends BaseEntity{

	@Column(name = "name", columnDefinition = "nvarchar(250)")
	private String name;
	
	@Column(name="code", columnDefinition = "nvarchar(250) NOT NULL UNIQUE")
	private String code;
	
	/*
	 * ví dụ List:"a,v,d,s"=>role:admin truy vấn admin thì lấy tất cả list user có role admin
	
	 * FetchType.EAGER: Khi truy vấn role thì nó lấy cả list lên
	 * FetchType.LAZY: Khi truy vấn role thì khi cần nó mới lấy list lên
	 * mappedBy sẽ mapp đúng với bên CustomerEntity
	 */
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<CustomerEntity> customers = new ArrayList<>();
	
//	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY)
//	private List<CustomerEntity> customerList;
//	
//	public List<CustomerEntity> getCustomerList() {
//		return customerList;
//	}
//
//	public void setCustomerList(List<CustomerEntity> customerList) {
//		this.customerList = customerList;
//	}

	public String getName() {
		return name;
	}

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
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
