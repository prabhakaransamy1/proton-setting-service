package com.yenmin.proton.setting.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.proton.setting.Exception.ExceptionController;
import com.yenmin.proton.setting.pojo.OrgRequest;
import com.yenmin.proton.setting.service.OrgService;

@RestController
@RequestMapping("/organization")
public class OrganizationController extends ExceptionController {

	@Autowired
	OrgService service;

	@PostMapping("/addorg")
	public ResponseEntity<Object> addOrg(@Valid @RequestBody OrgRequest orgRequest) throws IOException {

		return service.addOrg(orgRequest);

	}

	@GetMapping("/getorg")
	public ResponseEntity<Object> getOrg() {
		return service.getOrg();

	}

	@PutMapping("/updateorg")
	public ResponseEntity<Object> updateOrg(@Valid @RequestBody OrgRequest updateOrg) {

		return service.updateOrg(updateOrg);

	}

	@GetMapping("/getcountry")

	public ResponseEntity<Object> getCountry() {

		return service.getJsonContent();

	}
}

//}
