package com.yenmin.proton.setting.repository;

import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yenmin.proton.setting.model.Setting;

@Repository
public interface SettingRepository extends MongoRepository<Setting, String> {

	Setting findByOrganizationId(String string);

	Setting save(Map<String, Object> setting);

}
