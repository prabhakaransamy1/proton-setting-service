package com.yenmin.proton.setting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yenmin.proton.setting.model.Packages;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.pojo.PackageRequest;
import com.yenmin.proton.setting.repository.PackageRepository;

@Service
public class PackageService {

	@Autowired
	private PackageRepository packrepo;
	@Autowired
	ResourceBundleMessageSource messageSource;

	/**
	 * To save package details in the database(RequestBody contains fields like
	 * title,description,price,etc..)
	 **/
	public ResponseEntity<Object> addPackages(PackageRequest packageRequest) {
		Packages packages = new Packages(packageRequest.getTitle(), packageRequest.getDesc(),
				packageRequest.getAttributes(), packageRequest.getPrice(), packageRequest.getOffer());
		packages.setCreatedDate(new Date());
		packrepo.save(packages);

		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("pak.create", null, LocaleContextHolder.getLocale())));

	}

	/** To get particular all of the package exist **/
	public ResponseEntity<Object> getPackages() {
		List<Packages> packages = packrepo.findAll();
		if (packrepo.findAll().isEmpty()) {
			return new MessageResponse().failure("pak.notfound", null);
		}
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("pak.detail", null, LocaleContextHolder.getLocale()), packages));

	}

}
