package com.yenmin.proton.setting.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class MySetting {

	private String id;

	private Map<String, Object> setting = new HashMap<>();

	@JsonAnySetter
	public void set(String code, Object object) {

		this.setting.put(code, object);

	}

	public MySetting() {
		super();
	}

	public Object get(String code) {
		return this.setting.get(code);
	}

	public Map<String, Object> getSetting() {
		return setting;
	}

	public void setSetting(Map<String, Object> setting) {
		this.setting = setting;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Setting [id=" + id + ", setting=" + setting + "]";
	}

}
