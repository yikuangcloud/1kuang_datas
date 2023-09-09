package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class Carbon extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	/** 自定义图表id */
    private Integer id;
	
	private String city;
	
	private String date;
	
	private float price;
	
	private float turnover;
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTurnover() {
		return turnover;
	}

	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}
	
	
}
