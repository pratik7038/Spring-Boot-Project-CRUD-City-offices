package com.persistent.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.persistent.starter.model.City;
import com.persistent.starter.model.Office;

@Repository
public interface officeRepository extends JpaRepository<Office, Integer>{

	@Query
   public List<Office> findByCityId(int cityId);
	
}
