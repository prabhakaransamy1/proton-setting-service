package com.yenmin.proton.setting.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.proton.setting.Exception.ExceptionController;
import com.yenmin.proton.setting.pojo.ProductiveRequest;
import com.yenmin.proton.setting.service.SettingService;

@RestController
@RequestMapping("/setting/productive")
@Validated
public class ProductiveController extends ExceptionController {

	@Autowired
	SettingService service;

	@PostMapping("/add_app")
	public ResponseEntity<Object> addApp(@Valid @RequestBody ProductiveRequest prodReq) {
		return service.addApp(prodReq);

	}

	@PostMapping("/target_app")
	public ResponseEntity<Object> changeTargetApp(@Valid @RequestBody ProductiveRequest prodReq) {
		return service.changeTargetApp(prodReq);

	}

	@GetMapping("/get_producitivity")
	public ResponseEntity<Object> getProductivity() {
		return service.getProductivity();

	}

	@GetMapping("/check_producitivity")
	public ResponseEntity<Object> checkProductivity(@NotBlank @RequestParam String app) {
		return service.checkProductivity(app);
	}
}
