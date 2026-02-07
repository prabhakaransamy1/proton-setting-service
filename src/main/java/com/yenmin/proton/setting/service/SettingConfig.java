package com.yenmin.proton.setting.service;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import com.yenmin.proton.setting.model.Setting;

/**
 * This class has basic configuration for setting service.
 */
public class SettingConfig {

	@NotBlank(message = "model:must not be blank or null")
	private String model ;
	@NotBlank(message = "key:must not be blank or null")
	private String key;
	private String jString;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getjString() {
		return jString;
	}

	public void setjString(String jString) {
		this.jString = jString;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean validate(String key) {

		switch (key) {

		case "general":
			return true;

		case "user":
			return true;

		case "client":
			return true;

		case "project":
			return true;

		case "timesheet":
			return true;

		case "attendance":
			return true;

		case "notification":
			return true;

		case "report":
			return true;

		default:
			return false;

		}

	}

	public Map<String, Object> dynamicGetter(Setting setting, String model) {

		switch (model) {

		case "user":
			return setting.getUser();

		case "client":
			return setting.getClient();

		case "project":
			return setting.getProject();

		case "timesheet":
			return setting.getTimesheet();

		case "attendance":
			return setting.getAttendance();

		case "notification":
			return setting.getNotification();

		case "report":
			return setting.getReport();

		default:
			return setting.getGeneral();

		}

	}

	public Setting dynamicSetter(Setting setting, Map<String, Object> map, String model) {

		switch (model) {

		case "user":
			setting.setUser(map);
			return setting;
		case "client":
			setting.setClient(map);
			return setting;
		case "project":
			setting.setProject(map);
			return setting;
		case "timesheet":
			setting.setTimesheet(map);
			return setting;
		case "attendance":
			setting.setAttendance(map);
			return setting;
		case "notification":
			setting.setNotification(map);
			return setting;
		case "report":
			setting.setReport(map);
			return setting;
		default:
			setting.setGeneral(map);
			return setting;
		}

	}

}
