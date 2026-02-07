package com.yenmin.proton.setting.model;

import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "pt_setting")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Setting {
	@Id
	private String id;
	private String organizationId;
	private Map<String, Object> general;
	private Map<String, Object> user;
	private Map<String, Object> client;
	private Map<String, Object> project;
	private Map<String, Object> timesheet;
	private Map<String, Object> attendance;
	private Map<String, Object> notification;
	private Map<String, Object> report;
	private Map<String, Set<String>> productiveApp;

	@Field("created_date")
	private String createdDate;
	@Field("last_modified_date")
	private String lastModifiedDate = "";
	@Field("created_by")
	private String createdBy;

	public Setting() {
		super();
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

	public Map<String, Object> getUser() {
		return user;
	}

	public void setUser(Map<String, Object> user) {
		this.user = user;
	}

	public Map<String, Object> getClient() {
		return client;
	}

	public void setClient(Map<String, Object> client) {
		this.client = client;
	}

	public Map<String, Object> getProject() {
		return project;
	}

	public void setProject(Map<String, Object> project) {
		this.project = project;
	}

	public Map<String, Object> getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Map<String, Object> timesheet) {
		this.timesheet = timesheet;
	}

	public Map<String, Object> getAttendance() {
		return attendance;
	}

	public void setAttendance(Map<String, Object> attendance) {
		this.attendance = attendance;
	}

	public Map<String, Object> getNotification() {
		return notification;
	}

	public void setNotification(Map<String, Object> notification) {
		this.notification = notification;
	}

	public Map<String, Object> getReport() {
		return report;
	}

	public void setReport(Map<String, Object> report) {
		this.report = report;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public Map<String, Object> getGeneral() {
		return general;
	}

	public void setGeneral(Map<String, Object> general) {
		this.general = general;
	}

	

	

	public Map<String, Set<String>> getProductiveApp() {
		return productiveApp;
	}

	public void setProductiveApp(Map<String, Set<String>> productiveApp) {
		this.productiveApp = productiveApp;
	}

	@Override
	public String toString() {
		return "Setting [id=" + id + ", organizationId=" + organizationId + ", general=" + general + ", user=" + user
				+ ", client=" + client + ", project=" + project + ", timesheet=" + timesheet + ", attendance="
				+ attendance + ", notification=" + notification + ", report=" + report + ", createdDate=" + createdDate
				+ ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy + "]";
	}

}
