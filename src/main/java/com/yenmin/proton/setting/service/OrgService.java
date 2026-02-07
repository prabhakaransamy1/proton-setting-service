package com.yenmin.proton.setting.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yenmin.proton.setting.model.Country;
import com.yenmin.proton.setting.model.Organization;
import com.yenmin.proton.setting.model.Packages;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.pojo.OrgRequest;
import com.yenmin.proton.setting.repository.OrganizationRepository;
import com.yenmin.proton.setting.repository.PackageRepository;

@Service
public class OrgService {

	@Autowired
	private OrganizationRepository orgrepo;
	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	private PackageRepository packrepo;
	@Autowired
	ResourceBundleMessageSource messageSource;

	@Value("${currentUser.profile}")
	private String userUrl;

	/**
	 * To save organization details given by super user (RequestBody contains fields
	 * like organization name,address,etc..)
	 **/
	public ResponseEntity<Object> addOrg(@Valid OrgRequest orgRequest) {
		/**
		 * To get SuperUser Detail Rest Template is used.
		 * 
		 */
		/*
		 * MessageResponse userResponse = restTemplate.getForObject(userUrl,
		 * MessageResponse.class); User user = userResponse.getUser();
		 */

		Organization organization = new Organization(orgRequest.getName(), orgRequest.getOrgDomain(),
				orgRequest.getLogo(), orgRequest.getAddress(), orgRequest.getPhone(), orgRequest.getWebsite());
		Set<Packages> pacRef = new HashSet<>();
		Packages packages = packrepo.findById(orgRequest.getPackages()).orElse(null);
		if (packages == null) {
			return new MessageResponse().failure("pak.notfound", null);

		}

		pacRef.add(packages);
		organization.setPackages(pacRef);
		organization.setOid("5ee99f888eafbd4ecf275761");
		System.out.println("hi" + organization);
		orgrepo.save(organization);

		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("org.create", null, LocaleContextHolder.getLocale())));

	}

	/** To get organization detail via user id **/
	public ResponseEntity<Object> getOrg() {
		// MessageResponse userResponse = restTemplate.getForObject(userUrl,
		// MessageResponse.class);
		// User user = userResponse.getUser();

		Organization organization = orgrepo.findByOid("5ee99f888eafbd4ecf275761");
		if (organization == null) {
			throw new NoSuchElementException(
					messageSource.getMessage("org.null", null, LocaleContextHolder.getLocale()));
		}
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("org.detail", null, LocaleContextHolder.getLocale()), organization));

	}

	/**
	 * To update a saved organization entry given by super user from the database
	 * (RequestBody contains fields like organization name,address,etc..)
	 **/
	public ResponseEntity<Object> updateOrg(@Valid OrgRequest updateOrg) {
		// MessageResponse userResponse = restTemplate.getForObject(userUrl,
		// MessageResponse.class);
		// User user = userResponse.getUser();

		Organization organization = orgrepo.findByOid("5ee99f888eafbd4ecf275761");
		if (organization == null) {
			throw new NoSuchElementException(
					messageSource.getMessage("org.null", null, LocaleContextHolder.getLocale()));
		}
		organization.setName(updateOrg.getName() != null ? updateOrg.getName() : organization.getName());
		organization.setOrgDomain(
				updateOrg.getOrgDomain() != null ? updateOrg.getOrgDomain() : organization.getOrgDomain());
		organization.setLogo(updateOrg.getLogo() != null ? updateOrg.getLogo() : organization.getLogo());
		organization.setAddress(updateOrg.getAddress() != null ? updateOrg.getAddress() : organization.getAddress());
		organization.setPhone(updateOrg.getPhone() != null ? updateOrg.getPhone() : organization.getPhone());
		organization.setWebsite(updateOrg.getWebsite() != null ? updateOrg.getWebsite() : organization.getWebsite());
		Set<Packages> pacRef = new HashSet<>();
		Packages packages = packrepo.findById(updateOrg.getPackages()).orElse(null);
		pacRef.add(packages);
		organization.setPackages(pacRef);

		orgrepo.save(organization);
		return ResponseEntity
				.ok(new MessageResponse(messageSource.getMessage("code.ok", null, LocaleContextHolder.getLocale()),
						messageSource.getMessage("org.update", null, LocaleContextHolder.getLocale()), organization));

	}

	public ResponseEntity<Object> getJsonContent() {

		File file = null;

		try {
			file = ResourceUtils.getFile("classpath:countries.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Read File Content
		String countriesjString = null;
		try {
			countriesjString = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Country country = new Country();

		try {
			country = new ObjectMapper().readValue(countriesjString, Country.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     List<Country> countries = country.getCountriesMap().get("countries");
		return ResponseEntity.ok(countries);
		
	

	}

}
