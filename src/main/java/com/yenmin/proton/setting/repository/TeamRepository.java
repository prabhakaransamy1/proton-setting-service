package com.yenmin.proton.setting.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yenmin.proton.setting.model.Team;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

	

	List<Team> findByOid(String oid);

	List<Team> findByIdOrOid(String teamId, String string);

	

}
