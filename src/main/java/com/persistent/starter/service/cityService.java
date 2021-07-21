package com.persistent.starter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.persistent.starter.model.City;
import com.persistent.starter.repository.cityRepository;

@Service
public class cityService {
	
	@Autowired
	private cityRepository cityrepository;
	
	public cityService() {
	}
	
	public City getCity(int id)
	{
		if(cityrepository.existsById(id))
		return cityrepository.findById(id).get() ;
		else 
		return new City();
		
	}
	
	public boolean isExist(int id)
	{
		//if(cityrepository.existsById(c.getCityId()))
		if(cityrepository.existsById(id))	
			return true;
		else
			return false;
	}
	
	public List<City> getlist()
	{
		List<City> lst = new ArrayList<>(cityrepository.findAll());
		//cityrepository.findAll().forEach(lst::add);	
		return lst;
	}
	
	//add a new city
	public void addcity(City city) {
		cityrepository.save(city);
	}
	
	public void updateCity( City c) {
		
		cityrepository.save(c);
	}

	public void deletecity(int id) {
			
			cityrepository.deleteById(id);
			
	        System.out.println("successfuly deleted");

	}
}