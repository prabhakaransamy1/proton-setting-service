package com.yenmin.proton.setting.model;

import java.util.*;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pt_pm_integration")
public class IntegrationModel {

	@Id
	private String id;

	@Field("Organization")
	@NotBlank(message = "Organization id must not to be null")
	private String oId;

	@NotBlank(message = "Title must not title")
	private String title;

	@Field("api_details")
	private List<Object> apiDetailsModel = new ArrayList<>();

	@NotBlank(message = "Status must not to be null")
	private String status;

	@Field("created_by")
	private String createdby;

	@Field("created_date")
	private String createdDate;

	@Field("last_modified_by")
	private String lastModifiedBy;

	@Field("last_modified_date")
	private String lastModifiedDate;

	public List<Object> getApiDetailsModel() {
		return apiDetailsModel;
	}

	public void setApiDetailsModel(List<Object> apiDetailsModel) {
		this.apiDetailsModel = apiDetailsModel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
