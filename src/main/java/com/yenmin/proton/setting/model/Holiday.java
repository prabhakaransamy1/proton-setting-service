package com.yenmin.proton.setting.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "pt_holiday")
public class Holiday {

	@Id
	private String id;

	private String oid;
	@NotBlank
	private String title;
	@NotBlank
	private String date;
	private boolean status = true;
	private String lastModifiedBy;
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date lastModifiedDate;
	private String createdBy;
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date createdDate;

	public Holiday() {
		super();
	}

	public Holiday(@NotEmpty(message = "title is required") String title,
			@NotEmpty(message = "date is required") String date) {
		super();
		this.title = title;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Holiday(@NotEmpty(message = "title is required") String title,
			@NotEmpty(message = "date is required") String date, boolean status) {
		super();
		this.title = title;
		this.date = date;
		this.status = status;
	}

}
