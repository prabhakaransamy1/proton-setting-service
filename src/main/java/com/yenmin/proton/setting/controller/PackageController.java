package com.yenmin.proton.setting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.proton.setting.pojo.PackageRequest;
import com.yenmin.proton.setting.service.PackageService;

@RestController
@RequestMapping("/package")

public class PackageController {
	@Autowired
	PackageService service;

	@GetMapping("/getpackages")
	public ResponseEntity<Object> getPackages() {
		return service.getPackages();

	}

	@PostMapping("/addpackage")
	public ResponseEntity<Object> addPackages(@RequestBody PackageRequest packageRequest) {

		return service.addPackages(packageRequest);

	}
}
