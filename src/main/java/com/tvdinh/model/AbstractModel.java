package com.tvdinh.model;

import java.util.List;

public class AbstractModel<T> {
	
	protected T pojo;// chính là đối tượng lớp DTO

	private Integer totalPage;//tổng số page có thể có
	private Long totalItem;//tổng số item có thể có
	private Integer currentPage;//
	private Integer maxPageItem;
	private List<T> listResult;
	
	
	public List<T> getListResult() {
		return listResult;
	}

	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Long totalItem) {
		this.totalItem = totalItem;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getMaxPageItem() {
		return maxPageItem;
	}

	public void setMaxPageItem(Integer maxPageItem) {
		this.maxPageItem = maxPageItem;
	}

	public T getPojo() {
		return pojo;
	}

	public void setPojo(T pojo) {
		this.pojo = pojo;
	}
	
	
	

}
