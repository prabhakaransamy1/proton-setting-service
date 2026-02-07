package com.yenmin.proton.setting.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pt_team")
public class Team {

	@Id
	private String id;

	@Field("organizationId")
	@NotBlank(message = "Organization id must not to be null")
	private String oid;

	@NotBlank
	@NotNull
	private String title;

	private Set<Object> members = new LinkedHashSet<>();

	@Field("created_by")
	private String createdby;

	@Field("created_date")
	private String createdDate;

	@Field("last_modified_by")
	private String lastModifiedBy;

	@Field("last_modified_date")
	private String lastModifiedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getoid() {
		return oid;
	}

	public void setoid(String oid) {
		this.oid = oid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	
	

	public Set<Object> getMembers() {
		return members;
	}

	public void setMembers(Set<Object> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", oid=" + oid + ", title=" + title + ", members=" + members + ", createdby="
				+ createdby + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + "]";
	}

}
