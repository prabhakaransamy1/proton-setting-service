package com.yenmin.proton.setting.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yenmin.proton.setting.model.Packages;


public interface PackageRepository extends MongoRepository<Packages, String> {

	List<Packages> findByTitleContaining(String packages);
	
	Packages findByTitle(String packages);

}
