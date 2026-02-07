package com.yenmin.proton.setting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yenmin.proton.setting.Exception.ExceptionController;
import com.yenmin.proton.setting.model.IntegrationModel;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.repository.IntegrationRepository;
import com.yenmin.proton.setting.service.IntegrationService;

@RestController
@RequestMapping("/integration")

public class IntegrationController extends ExceptionController{

	@Autowired
	IntegrationRepository integrationRepo;
	@Autowired
	IntegrationService service;
	@Autowired
	ResourceBundleMessageSource messageSource;
	@Autowired
	RestTemplate restTemplate;

	@PostMapping(value = "/create-integration")
	public MessageResponse createintegration(@Valid @RequestBody IntegrationModel model) {

		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				messageSource.getMessage("create.integration", null, LocaleContextHolder.getLocale()),
				service.createintegration(model));

	}

	@PostMapping(value = "/update-integration")
	public MessageResponse updateintegration(@Valid @RequestBody IntegrationModel model) {
		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				messageSource.getMessage("update.integration", null, LocaleContextHolder.getLocale()),
				service.updateintegration(model));
	}

	@GetMapping(value = "/integration-details")
	public MessageResponse getIntegrationdetails(@RequestBody IntegrationModel model) {

		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				messageSource.getMessage("integration.details", null, LocaleContextHolder.getLocale()),
				service.getIntegrationdetails(model));
	}

	@GetMapping(value = "/user/integration-details/{title}")
	public MessageResponse getUserIntegrationDetails(@PathVariable("title") String title) {
		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				messageSource.getMessage("integration.details", null, LocaleContextHolder.getLocale()),
				service.getUserIntegrationdetails(title));
	}

	@GetMapping(value = "/user/integration-details")
	public MessageResponse getUserIntegration() {
		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				messageSource.getMessage("integration.details", null, LocaleContextHolder.getLocale()),
				service.getUserIntegration());
	}

	@DeleteMapping(value = "/integration-delete")
	public MessageResponse deleteIntegration(@RequestBody IntegrationModel model) {
		service.deleteIntegration(model);
		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				messageSource.getMessage("integration.delete", null, LocaleContextHolder.getLocale()));
	}

	@GetMapping(value = "/integration-list")
	public MessageResponse getAllIntegration() {
		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				service.getAllIntegration());
	}

	@DeleteMapping(value = "/delete-all")
	public MessageResponse deleteall() {
		integrationRepo.deleteAll();
		return new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
				messageSource.getMessage("integration.delete", null, LocaleContextHolder.getLocale()));
	}

}
