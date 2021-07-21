package com.persistent.starter.controller;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.starter.model.City;
import com.persistent.starter.service.cityService;


@RestController
public class cityController {

	@Autowired
	public cityService cityservice;

	@RequestMapping("/city")
	public HashMap<String,Object> getCity(@RequestBody int id)
	{ 
		HashMap<String, Object> mp = new HashMap<>();
		
		boolean activeStatus = cityservice.getCity(id).isActive();
		
		if(cityservice.isExist(id))
		{
			mp.put("Message",activeStatus ? "Successfully fetched the City with given City Id : "+id :" City Inactive ");
			mp.put("Data", cityservice.getCity(id));
			mp.put("Status",activeStatus ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value());
			mp.put("Success", activeStatus);
			return mp;

		}
		else
		{
			mp.put("Message","City with given City Id : "+id);
			mp.put("Success", false);
			return mp;
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/city/add")
	public HashMap<String, Object> addCity(@RequestBody City c)
	{
		HashMap<String, Object> mp = new HashMap<>();
		if(cityservice.isExist(c.getCityId()))
		{
			mp.put("Message", "City Already Exists");
			mp.put("Status", HttpStatus.CONFLICT.value());
			mp.put("Success", false);
		}
		else
		{
			cityservice.addcity(c);	
			mp.put("Message", "Given City Added Successfully");
			mp.put("Status", HttpStatus.CREATED.value());
			mp.put("Success", true);
		}
		return mp;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/city/update")
	public HashMap<String,Object> update_city(@RequestBody City c)
	{
		HashMap<String,Object> mp = new HashMap<String ,Object>();

		if(cityservice.isExist(c.getCityId()))
		{
			cityservice.updateCity(c);
			mp.put("Message", "City Updated Successfully");
			mp.put("Success", true);
			mp.put("Status", HttpStatus.OK.value());
		}
		else
		{
			mp.put("Message", "City doesn't exist.. Please add the city");
			mp.put("Success", false);
		}
		
		return mp;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value= "/city/delete")
	public HashMap<String, Object> delete(@RequestBody int id) 
			{
		HashMap<String , Object> ret = new HashMap<>();
		
		if(cityservice.isExist(id))
		{
			cityservice.deletecity(id);
			ret.put("message", "Successfully Deleted");
	        ret.put("status", HttpStatus.OK.value());
	        ret.put("success", true);
			return ret;
		}
		else
		{
			ret.put("Message", "This City Doesn't Exist");
			return ret;
		}
			
	}
	
	@RequestMapping("/city/list")
	public HashMap<String, Object> getAllCities() {
        List<City> data = cityservice.getlist();
        HashMap<String, Object> mp = new HashMap<>();
        mp.put("message", "Successfully fetched list of all cities.");
        mp.put("status", HttpStatus.OK.value());
        mp.put("success", true);
        mp.put("data", data);
        return mp;
    }

	@PostMapping("/city/activate")
	public HashMap<String, Object> activateCity(@RequestBody HashMap<String, Object> hmp)
	{
		HashMap<String, Object> mp = new HashMap<String, Object>();
		
		int cityId = (int) hmp.get("cityId");
		
		boolean status = (boolean) hmp.get("status");
		
		
		if(cityservice.isExist(cityId))
		{
		
			City city = cityservice.getCity(cityId);
			
			city.setActive(status);
			
			cityservice.updateCity(city);
			
			mp.put("message", "Done successfully");
			
			status = true;	
	        mp.put("success", status);
	        mp.put("status", HttpStatus.OK.value());   

		}
		else
		{
			mp.put("message", "City Doesnt exist ");
			status = false;
	        mp.put("status", HttpStatus.NOT_FOUND.value());
	        mp.put("success", status);

		}
		
	      return mp;  
	}	
	
	
	
}