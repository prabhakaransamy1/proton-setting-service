package com.yenmin.proton.setting.pojo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.yenmin.proton.setting.service.NullOrNotBlank;

public class TeamRequest {
	@NotBlank(message = "teamId cannot be blank or null")
	private String teamId;
	@NullOrNotBlank(message = "title cannot be blank")
	private String title;

	private List<String> members;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
