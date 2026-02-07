package com.yenmin.proton.setting.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yenmin.proton.setting.model.IntegrationModel;



@Repository
public interface IntegrationRepository extends MongoRepository<IntegrationModel, String> {

	IntegrationModel findByTitle(String title);

	List<IntegrationModel> findByCreatedby(String id);

	List<IntegrationModel> findByTitleAndCreatedby(String title, String id);

}
