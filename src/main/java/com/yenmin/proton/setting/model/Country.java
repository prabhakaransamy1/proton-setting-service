package com.yenmin.proton.setting.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Country {
	
	private String id;
   

   private String sortname;
	
	private String name;
 
	private String phoneCode;
	
	private Map<String, List<Country> > countriesMap = new HashMap<>();

	@JsonAnySetter
	public void set(String code, List<Country> countries) {

		this.countriesMap.put(code, countries);

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Map<String, List<Country>> getCountriesMap() {
		return countriesMap;
	}

	public void setCountriesMap(Map<String, List<Country>> countriesMap) {
		this.countriesMap = countriesMap;
	}

	public Country(String id, String sortname, String name, String phoneCode, Map<String, List<Country>> countriesMap) {
		super();
		this.id = id;
		this.sortname = sortname;
		this.name = name;
		this.phoneCode = phoneCode;
		this.countriesMap = countriesMap;
	}

	public Country() {
		super();
	}


	

}
