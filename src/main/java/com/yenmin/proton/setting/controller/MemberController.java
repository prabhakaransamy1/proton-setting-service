package com.yenmin.proton.setting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.proton.setting.pojo.TeamRequest;
import com.yenmin.proton.setting.service.TeamService;

@RestController
@RequestMapping("/team/member")
public class MemberController {

	@Autowired
	ResourceBundleMessageSource messageSource;
	@Autowired
	TeamService service;

	@PostMapping(value = "/addmember")
	public ResponseEntity<Object> addMember(@Valid @RequestBody TeamRequest teamRequest) {

		return service.addMember(teamRequest);
	}

	@DeleteMapping(value = "/deletemember")
	public ResponseEntity<Object> updateMember(@Valid @RequestBody TeamRequest teamRequest) {

		return service.deleteMember(teamRequest);

	}

}
