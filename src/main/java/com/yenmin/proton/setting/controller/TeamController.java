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
import com.yenmin.proton.setting.pojo.TeamRequest;
import com.yenmin.proton.setting.service.TeamService;

@RestController
@RequestMapping("/team")
@Validated
public class TeamController extends ExceptionController {
	@Autowired
	ResourceBundleMessageSource messageSource;
	@Autowired
	TeamService service;

	@PostMapping(value = "/addteam")
	public ResponseEntity<Object> createTeam(@RequestBody TeamRequest teamRequest) {

		return service.addTeam(teamRequest);
	}

	@PutMapping(value = "/updateteam")
	public ResponseEntity<Object> updateTeam(@Valid @RequestBody TeamRequest teamRequest) {

		return service.updateTeam(teamRequest);

	}

	@GetMapping(value = "/getteam")
	public ResponseEntity<Object> getTeam(
			@RequestParam @NotBlank(message = "teamId:Must not be blank or null") String teamId) {

		return service.getTeam(teamId);

	}

	@DeleteMapping(value = "/deleteteam")
	public ResponseEntity<Object> deleteTeam(@Valid @RequestBody TeamRequest teamRequest) {

		return service.deleteTeam(teamRequest);

	}

}
