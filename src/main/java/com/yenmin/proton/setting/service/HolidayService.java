package com.yenmin.proton.setting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.yenmin.proton.setting.model.Holiday;
import com.yenmin.proton.setting.pojo.HolidayRequest;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.repository.HolidayRepository;

@Service
public class HolidayService {

	@Autowired
	HolidayRepository holidayRepository;
	@Autowired
	ResourceBundleMessageSource messageSource;

	/**
	 * To create holiday entry for the particular day of the month (In RequestBody
	 * we need to pass title and date)
	 **/
	public ResponseEntity<Object> createholiday(HolidayRequest holidayRequest) {

		Holiday holiday = new Holiday(holidayRequest.getTitle(), holidayRequest.getDate());
		holiday.setCreatedDate(new Date());
		holiday.setCreatedBy("5ee99f888eafbd4ecf275761");
		holidayRepository.save(holiday);
		return new MessageResponse()
				.success("holiday.create", null);
	}

	/** To delete holiday entry in the database using "id" passed **/
	public ResponseEntity<Object> deleteHoliday(@RequestParam(name = "id") String id) {
		holidayRepository.deleteById(id);
		return new MessageResponse()
				.success("holiday.delete", null);
	}

	/**
	 * To get List of holiday entries from the database using title,date by "order"
	 * defined
	 **/
	public ResponseEntity<Object> getHoliday(String order, String title, String date) {
		List<Holiday> holidays = new ArrayList<>();
		if (order == null && title == null && date == null) {

			holidays = holidayRepository.findAll();
			return new MessageResponse().success("holiday.details", null, holidays);
		}
		order=order==null?"ascending":order;
		if ("ascending".equals(order)) {
			holidays = holidayRepository.findByTitleIgnoreCaseContainingOrDateIgnoreCaseContainingOrderByTitleAsc(title,
					date);
		}
		if ("descending".equals(order)) {
			holidays = holidayRepository
					.findByTitleIgnoreCaseContainingOrDateIgnoreCaseContainingOrderByTitleDesc(title, date);
		}
		if (holidays.isEmpty()) {
			return new MessageResponse()
					.failure("holiday.nodetail",null);

		}

		return new MessageResponse()
				.success("holiday.detail", null, holidays);
	}

	/**
	 * To update particular field of a entry in the database by irrespective of
	 * changes passed
	 **/

	public ResponseEntity<Object> updateHoliday(HolidayRequest holidayRequest) {

		Holiday holiday = holidayRepository.findById(holidayRequest.getHolidayId()).orElse(null);
		if (holiday == null) {
			throw new NullPointerException(
					messageSource.getMessage("holiday.null", null, LocaleContextHolder.getLocale()));
		}
		holiday.setTitle(holidayRequest.getTitle() != null ? holidayRequest.getTitle() : holiday.getTitle());
		holiday.setDate(holidayRequest.getDate() != null ? holidayRequest.getDate() : holiday.getDate());
		holiday.setLastModifiedDate(new Date());
		holiday.setLastModifiedBy("5ee99f888eafbd4ecf275761");
		holidayRepository.save(holiday);
		return new MessageResponse()
				.success("holiday.update", null, holiday);
	}

}
