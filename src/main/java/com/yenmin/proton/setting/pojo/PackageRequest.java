package com.yenmin.proton.setting.pojo;

import java.util.ArrayList;
import java.util.List;


public class PackageRequest {
	
	private String title;
	private String desc;
	
	private List<Object> attributes = new ArrayList<>();
	private Double price;
	private Double offer;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getOffer() {
		return offer;
	}
	public void setOffer(Double offer) {
		this.offer = offer;
	}
	

}
