package com.tvdinh.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class CategoryEntity extends BaseEntity{
	@Column(name = "name", columnDefinition = "nvarchar(250)")
	private String name;
	@Column(name="code", columnDefinition = "nvarchar(250) NOT NULL UNIQUE")
	private String code;
	@Column(name="description", columnDefinition = "NTEXT")
	private String description;
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<ProductEntity> products=new ArrayList<>();
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ProductEntity> getProducts() {
		return products;
	}
	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
	
}
