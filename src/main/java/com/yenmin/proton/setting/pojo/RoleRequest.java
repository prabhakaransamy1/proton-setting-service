package com.yenmin.proton.setting.pojo;

import javax.validation.constraints.NotBlank;

import com.yenmin.proton.setting.model.Permission;



public class RoleRequest {
	@NotBlank(message="roleId:must not be blank or null")
	private String roleId;
	private String organizationid;
	private String title;
	private String description;
	private Permission permission;

	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
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

	

}
