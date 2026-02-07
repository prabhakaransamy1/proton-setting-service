  package com.yenmin.proton.setting.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.yenmin.proton.setting.service.NullOrNotBlank;
@Document(collection ="pt_packages")
public class Packages {
	
	@Id
	private String id;
	@NotNull
	@Size(min=3,max=15)
	private String title;
	@Size(min=3,max=25)
	@NullOrNotBlank
	private String desc;
	@NotEmpty
	private List<Object> attributes = new ArrayList<>();
	@NotNull
	private Double price;
	private Double offer;
	@Field("last_modified_date")
	private Date lastModifiedDate;
	@Field("created_date")
	private Date createdDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Packages() {
		super();
	}
	
	public Packages(String title, String desc, List<Object> attributes, Double price, Double offer) {
		super();
		this.title = title;
		this.desc = desc;
		this.attributes = attributes;
		this.price = price;
		this.offer = offer;
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
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "Packages [id=" + id + ", title=" + title + ", attributes=" + attributes + ", price=" + price
				+ ", offer=" + offer + ", lastModifiedDate=" + lastModifiedDate + ", createdDate=" + createdDate + "]";
	}
	
	

}


