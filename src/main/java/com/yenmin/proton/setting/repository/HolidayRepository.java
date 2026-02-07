package com.yenmin.proton.setting.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yenmin.proton.setting.model.Holiday;
@Repository
public interface HolidayRepository extends MongoRepository<Holiday, String> {

	List<Holiday> findByTitleIgnoreCaseContainingOrDateIgnoreCaseContainingOrderByTitleAsc(String title, String date);

	List<Holiday> findByTitleIgnoreCaseContainingOrDateIgnoreCaseContainingOrderByTitleDesc(String title, String date);

}
