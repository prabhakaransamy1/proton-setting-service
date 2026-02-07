package com.yenmin.proton.setting.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.proton.setting.Exception.ExceptionController;
import com.yenmin.proton.setting.service.NullOrNotBlank;
import com.yenmin.proton.setting.service.SettingConfig;
import com.yenmin.proton.setting.service.SettingService;

@RestController
@RequestMapping("/setting")
@Validated
public class SettingController extends ExceptionController {

	@Autowired
	SettingService service;

	@PostMapping("/addsetting")
	public ResponseEntity<Object> addOrUpdate(@RequestBody String jString) {
		return service.addSetting(jString);

	}

	@GetMapping("/getsetting")
	public ResponseEntity<Object> getSetting(@NotBlank @RequestParam String model,
			@RequestParam(required = false) @NullOrNotBlank(message = "Must not be blank") String key) {
		return service.getSetting(model, key);
	}

	@DeleteMapping("/deletesetting")
	public ResponseEntity<Object> deleteSetting(@Valid @RequestBody SettingConfig settingConfig) {
		return service.deleteSetting(settingConfig);
	}

}
