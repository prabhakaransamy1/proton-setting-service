package com.yenmin.proton.setting.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yenmin.proton.setting.model.Setting;
import com.yenmin.proton.setting.pojo.MessageResponse;
import com.yenmin.proton.setting.pojo.MySetting;
import com.yenmin.proton.setting.pojo.ProductiveRequest;
import com.yenmin.proton.setting.repository.SettingRepository;

@Service
@Validated
public class SettingService {
	@Autowired
	SettingRepository srepo;

	private MongoTemplate mongoTemplate;

	@Autowired
	ResourceBundleMessageSource messageSource;

	public SettingService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * To save the particular model's setting in a collection based on the given
	 * model and setting(RequestBody is Json String)
	 **/
	public ResponseEntity<Object> addSetting(String jString) {
		Setting setting = new Setting();
		MySetting mySetting = new MySetting();
		String model = "general";
		String key = "";
		Object object = "";
		Map<String, Object> map = new HashMap<>();
		try {
			mySetting = new ObjectMapper().readValue(jString, MySetting.class);
		} catch (JsonMappingException e1) {

			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			setting = srepo.findByOrganizationId("5f1d6fb22200a208863a3787");
			setting.getNotification().put("template", jString);
			srepo.save(setting);
			String[] params = { "Template" };
			return new MessageResponse().success("setting.success", params);

		}

		SettingConfig settingConfig = new SettingConfig();
		setting = srepo.findByOrganizationId("5f1d6fb22200a208863a3787");

		Iterator<Map.Entry<String, Object>> itr = mySetting.getSetting().entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			if (entry.getKey().equals("model")) {
				model = (String) entry.getValue();

				String[] params = { (String) entry.getValue() };
				if (!settingConfig.validate((String) entry.getValue())) {
					throw new NullPointerException(
							messageSource.getMessage("setting.nomodel", params, LocaleContextHolder.getLocale()));

				}
			} else {

				key = entry.getKey();
				object = entry.getValue();
				map.put(key, object);

			}
		}
		if (key.isBlank() && object.toString().isBlank()) {
			throw new NullPointerException(
					messageSource.getMessage("setting.input", null, LocaleContextHolder.getLocale()));

		}

		Query query = new Query();
		query.addCriteria(Criteria.where(model).exists(true));

		if (mongoTemplate.findOne(query, Setting.class) != null) {
			setting = mongoTemplate.findOne(query, Setting.class);
			map = settingConfig.dynamicGetter(setting, model);
			map.put(key, object);
			srepo.save(setting);
			String[] params = { key };
			return new MessageResponse().success("setting.update", params);

		}

		settingConfig.dynamicSetter(setting, map, model);
		srepo.save(setting);
		String[] params = { key };
		return new MessageResponse().success("setting.success", params);

	}

	/**
	 * To get a setting from a collection in the database based on the given model
	 * and setting's key otherwise model's all setting(RequestParam are model and
	 * key)
	 **/
	public ResponseEntity<Object> getSetting(String model, String key) {
		Map<String, Object> map = new HashMap<>();
		SettingConfig settingConfig = new SettingConfig();
		Query query = new Query();
		query.addCriteria(Criteria.where(model).exists(true));
		if (!settingConfig.validate(model)) {
			String[] params = { model };
			return new MessageResponse().failure("setting.nomodel", params);

		}
		Setting setting = mongoTemplate.findOne(query, Setting.class);
		if (setting == null) {
			String[] params = { model };
			throw new NullPointerException(
					messageSource.getMessage("setting.notfound", params, LocaleContextHolder.getLocale()));

		}
		map = settingConfig.dynamicGetter(setting, model);

		if (map.get(key) != null) {
			Map<String, Object> rmap = new HashMap<>();
			rmap.put(key, map.get(key));

			String[] params = { key, model };
			return new MessageResponse().success("setting.found", params, rmap);

		}
		if (key == null) {
			String[] params = { model };
			return new MessageResponse().success("setting.total", params, map);

		}
		String[] params = { key, model };
		return new MessageResponse().failure("setting.nokey", params);

	}

	/**
	 * To delete a setting from a collection in the database based on the given
	 * model and setting's key (RequestParam are model and key)
	 **/
	public ResponseEntity<Object> deleteSetting(SettingConfig settingConfig) {
		Map<String, Object> map = new HashMap<>();
		Query query = new Query();
		query.addCriteria(Criteria.where(settingConfig.getModel()).exists(true));
		if (!settingConfig.validate(settingConfig.getModel())) {
			String[] params = { settingConfig.getModel() };
			return new MessageResponse().failure("setting.nomodel", params);

		}
		Setting setting = mongoTemplate.findOne(query, Setting.class);

		map = settingConfig.dynamicGetter(setting, settingConfig.getModel());

		if (map.get(settingConfig.getKey()) != null) {
			map.remove(settingConfig.getKey());
			setting = settingConfig.dynamicSetter(setting, map, settingConfig.getModel());
			srepo.save(setting);
			String[] params = { settingConfig.getKey() };
			return new MessageResponse().success("setting.deleted", params);
		}

		String[] params = { settingConfig.getKey(), settingConfig.getModel() };
		return new MessageResponse().failure("setting.nokey", params);

	}

	/**
	 * To save a app in a collection under productivityApp map and categorized based
	 * on the target category specified(RequestBody app,target)
	 **/
	public ResponseEntity<Object> addApp(@Valid ProductiveRequest prodReq) {
		String types[] = { "productive", "unproductive", "neutral", "uncategorized" };
		Setting setting = srepo.findByOrganizationId("5f1d6fb22200a208863a3787");
		prodReq.setTarget("uncategorized");
		Set<String> apps = new LinkedHashSet<>();
		Map<String, Set<String>> map = new HashMap<>();
		map = setting.getProductiveApp() == null ? new HashMap<>() : setting.getProductiveApp();
		if (!Arrays.asList(types).contains(prodReq.getTarget())) {
			return new MessageResponse().failure("prod.target", null);

		}
		for (String type : types) {

			Query query = new Query();
			query.addCriteria(
					new Criteria().andOperator(Criteria.where("organizationId").is("5f1d6fb22200a208863a3787"),
							(Criteria.where("productiveApp." + type).in(prodReq.getApp()))));

			if (mongoTemplate.findOne(query, Setting.class) != null) {

				return new MessageResponse().failure("prod.exist", null);

			}

			if (prodReq.getTarget() != null && prodReq.getTarget().equals(type)) {
				apps = map.get(type) == null ? new LinkedHashSet<>() : map.get(type);
				apps.add(prodReq.getApp());
				map.put(type, apps);

			}

		}
		setting.setProductiveApp(map);
		srepo.save(setting);
		return new MessageResponse().success("prod.add", null);

	}

	/**
	 * To change a target of the app in a collection under productivityApp map based
	 * on the target category specified(RequestBody app,target)
	 **/
	public ResponseEntity<Object> changeTargetApp(@Valid ProductiveRequest prodReq) {
		String types[] = { "productive", "unproductive", "neutral", "uncategorized" };
		Setting setting = srepo.findByOrganizationId("5f1d6fb22200a208863a3787");

		Set<String> apps = new LinkedHashSet<>();
		Map<String, Set<String>> map = new HashMap<>();
		map = setting.getProductiveApp() == null ? new HashMap<>() : setting.getProductiveApp();
		if (prodReq.getTarget() == null) {
			return new MessageResponse().failure("prod.notarget", null);

		}
		for (String type : types) {
			Query query = new Query();
			query.addCriteria(
					new Criteria().andOperator(Criteria.where("organizationId").is("5f1d6fb22200a208863a3787"),
							(Criteria.where("productiveApp." + type).in(prodReq.getApp()))));

			if (mongoTemplate.findOne(query, Setting.class) != null) {

				map.get(type).remove(prodReq.getApp());
				apps = map.get(prodReq.getTarget()) == null ? new LinkedHashSet<>() : map.get(prodReq.getTarget());
				apps.add(prodReq.getApp());
				map.put(prodReq.getTarget(), apps);
				srepo.save(setting);
				return new MessageResponse().success("prod.update", null);
			}
		}
		return new MessageResponse().failure("prod.notfound", null);

	}

	/**
	 * To get a productivity collection based on the organization id
	 **/
	public ResponseEntity<Object> getProductivity() {
		Setting setting = srepo.findByOrganizationId("5f1d6fb22200a208863a3787");
		if (setting == null) {
			throw new NullPointerException(
					messageSource.getMessage("setting.null", null, LocaleContextHolder.getLocale()));

		}
		return new MessageResponse().success("prod.detail", null, setting.getProductiveApp());

	}

	/**
	 * To check whether the given app exist under a setting collection of a
	 * particular organization(RequestBody app,target)
	 **/
	public ResponseEntity<Object> checkProductivity(@Valid String app) {
		String types[] = { "productive", "unproductive", "neutral", "uncategorized" };

		for (String type : types) {
			Query query = new Query();
			query.addCriteria(
					new Criteria().andOperator(Criteria.where("organizationId").is("5f1d6fb22200a208863a3787"),
							(Criteria.where("productiveApp." + type).in(app))));

			if (mongoTemplate.findOne(query, Setting.class) != null) {

				String[] params = { app, type };

				return new MessageResponse().success("prod.found", params);
			}

		}

		return new MessageResponse().failure("prod.notfound", null);

	}

}
