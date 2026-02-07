package com.yenmin.proton.setting.pojo;

import javax.validation.constraints.NotBlank;

public class HolidayRequest {
	@NotBlank(message="holidayId: must not be blank or null")
	private String holidayId;
	private String title;

	private String date;

	

	public String getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
