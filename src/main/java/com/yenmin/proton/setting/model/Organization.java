package com.yenmin.proton.setting.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pt_organization")
public class Organization {
	@Id
	private String id;
	@NotBlank
	private String name;
	@Field("org_domain") 
	private String orgDomain;
	
	private String logo;
	@NotBlank
	private String address;
	@NotBlank
	private String phone;

	private String website;
	@Field("userId")
	private String oid;
	
	@DBRef
	@Field("package")
	@NotEmpty
	private Set<Packages> packages = new HashSet<>();
	@Field("created_date")
	private Date createdDate;
	@Field("last_modified_date")
	private Date lastModifiedDate;

	public Organization() {
	}

	public Organization(String name, String orgDomain, String logo, String address, String phone, String website) {

		this.name = name;
		this.orgDomain = orgDomain;
		this.logo = logo;
		this.address = address;
		this.phone = phone;
		this.website = website;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgDomain() {
		return orgDomain;
	}

	public void setOrgDomain(String orgDomain) {
		this.orgDomain = orgDomain;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Set<Packages> getPackages() {
		return packages;
	}

	public void setPackages(Set<Packages> packages) {
		this.packages = packages;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
