package com.yenmin.proton.setting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.proton.setting.Exception.ExceptionController;
import com.yenmin.proton.setting.pojo.HolidayRequest;
import com.yenmin.proton.setting.repository.HolidayRepository;
import com.yenmin.proton.setting.service.HolidayService;

@RestController
@RequestMapping("/holiday")

public class HolidayController extends ExceptionController {
	@Autowired
	HolidayRepository holidayRepository;
	@Autowired
	HolidayService holidayService;

	@PostMapping("/createholiday")
	@Validated
	public ResponseEntity<Object> createholiday(@RequestBody HolidayRequest holidayRequest) {

		return holidayService.createholiday(holidayRequest);
	}

	@GetMapping("/getholiday/{holiday}")
	public ResponseEntity<Object> getHoliday(@RequestParam(required = false) String order, @PathVariable("holiday") String title,
			@PathVariable("holiday") String date) {
		return holidayService.getHoliday(order, title, date);
	}

	@PutMapping("/updateholiday")
	public ResponseEntity<Object> updateholiday(@Valid @RequestBody HolidayRequest holidayRequest) {
		return holidayService.updateHoliday(holidayRequest);
	}

}