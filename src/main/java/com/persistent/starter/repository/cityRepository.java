package com.persistent.starter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.persistent.starter.model.City;

public interface cityRepository extends JpaRepository<City, Integer>{	

}
