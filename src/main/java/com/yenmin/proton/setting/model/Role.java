package com.yenmin.proton.setting.model;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.yenmin.proton.setting.service.NullOrNotBlank;

@Document(collection = "pt_roles")
public class Role{
	@Id
	private String id;
	@Field("oid")
	private String organizationId;
	@NotBlank
	private String title;
	@NullOrNotBlank
	private String description;
	@Field("permission")
	private Permission permission;
	@Field("last_modified_by")
	private String lastModifiedBy;
	@Field("last_modified_date")
	private String lastModifiedDate;
	@Field("created_by")
	private String createdBy;
	@Field("created_date")
	private String createdDate;

	public Role(String id, String organizationId, String title, Permission permission, String description) {
		this.id = id;
		this.organizationId = organizationId;
		this.title = title;
		this.description = description;
		this.permission = permission;
	}
	

	public Role() {
		
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
