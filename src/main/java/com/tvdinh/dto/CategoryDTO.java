package com.tvdinh.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO extends AbstractDTO implements Serializable{
	private static final long serialVersionUID = -1159283723191017000L;
	private String name;
	private String code;
	private String description;
	private List<ProductDTO> products=new ArrayList<ProductDTO>();
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
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
}
