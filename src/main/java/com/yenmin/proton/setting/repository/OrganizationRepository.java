package com.yenmin.proton.setting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yenmin.proton.setting.model.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {

	Organization findByOid(String id);

}
