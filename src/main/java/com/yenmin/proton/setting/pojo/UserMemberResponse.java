package com.yenmin.proton.setting.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMemberResponse {

	private String userId;
	private String username;
	public UserMemberResponse() {
		super();
	}
	public UserMemberResponse(String userId, String username, String image) {
		super();
		this.userId = userId;
		this.username = username;
		this.image = image;
	}
	private String image;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
