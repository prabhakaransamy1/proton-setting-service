package com.yenmin.proton.setting.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.proton.setting.Exception.ExceptionController;
import com.yenmin.proton.setting.pojo.RoleRequest;
import com.yenmin.proton.setting.repository.OrganizationRepository;
import com.yenmin.proton.setting.repository.RoleRepository;
import com.yenmin.proton.setting.service.RoleService;

@RestController
@RequestMapping("/role")
@Validated
public class RoleController extends ExceptionController{
	@Autowired
	RoleRepository repo;
	@Autowired
	OrganizationRepository orgrepo;

	@Autowired
	ResourceBundleMessageSource messageSource;

	@Autowired
	RoleService service;

	@PostMapping(value = "/create-role")
	public ResponseEntity<Object> createRole(@RequestBody RoleRequest roleRequest) {
		return service.createRole(roleRequest);

	}

	@PutMapping(value = "/update-role")
	public ResponseEntity<Object> updateRole(@Valid @RequestBody RoleRequest roleRequest) {
		return service.updateRole(roleRequest);

	}

	@DeleteMapping(value = "/delete-role")
	public ResponseEntity<Object> deleterole(@NotBlank @RequestParam String roleId) {
		return service.deleteRole(roleId);

	}

	@GetMapping(value = "/role-details")
	public ResponseEntity<Object> getRoleDetails(@NotBlank @RequestParam String roleId) {
		return service.getRoleDetails(roleId);

	}

	@DeleteMapping(value = "/delete-all")
	public ResponseEntity<Object> deleteAll() {
		return service.deleteAll();

	}
}
