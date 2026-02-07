package com.yenmin.proton.setting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yenmin.proton.setting.model.Role;



public interface RoleRepository extends MongoRepository<Role, String> {

	

}
