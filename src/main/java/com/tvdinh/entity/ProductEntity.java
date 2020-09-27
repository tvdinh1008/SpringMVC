package com.tvdinh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductEntity extends BaseEntity{
	@Column(name="name", columnDefinition = "nvarchar(250)")
	private String name;
	@Column(name="title", columnDefinition = "NTEXT" )
	private String title;
	@Column(name="description", columnDefinition = "NTEXT")
	private String description;
	@Column(name="unit", columnDefinition = "nvarchar(20)")
	private String unit;
	@Column(name="unit_price")
	private Float unit_price;
	@Column(name="promotion_price")
	private Float promotion_price;
	@Column(name="image")
	private String image;
	@Column(name="image1")
	private String image1;
	@Column(name="image2")
	private String image2;
	@Column(name="image3")
	private String image3;
	@Column(name="status")
	private Integer status;
	@Column(name="total")
	private Integer total;
	@Column(name="total_sold")
	private Integer total_sold;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="category_id")
	private CategoryEntity category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Float unit_price) {
		this.unit_price = unit_price;
	}

	public Float getPromotion_price() {
		return promotion_price;
	}

	public void setPromotion_price(Float promotion_price) {
		this.promotion_price = promotion_price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal_sold() {
		return total_sold;
	}

	public void setTotal_sold(Integer total_sold) {
		this.total_sold = total_sold;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	
	
}
