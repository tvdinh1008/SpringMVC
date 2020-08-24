package com.tvdinh.model;

public class AbstractModel<T> {
	
	protected T pojo;// chính là đối tượng lớp DTO

	public T getPojo() {
		return pojo;
	}

	public void setPojo(T pojo) {
		this.pojo = pojo;
	}
	
	
	

}
