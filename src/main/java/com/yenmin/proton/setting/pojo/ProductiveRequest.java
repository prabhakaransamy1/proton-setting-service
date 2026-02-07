package com.yenmin.proton.setting.pojo;

import javax.validation.constraints.NotBlank;

import com.yenmin.proton.setting.service.NullOrNotBlank;

public class ProductiveRequest {
	@NotBlank(message = "app:Must not be blank or null")
	private String app;
	@NullOrNotBlank(message = "target:Must not be blank")
	private String target;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
