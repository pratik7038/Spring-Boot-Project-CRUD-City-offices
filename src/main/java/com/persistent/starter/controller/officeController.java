package com.persistent.starter.controller;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.starter.model.City;
import com.persistent.starter.model.Office;
import com.persistent.starter.service.cityService;
import com.persistent.starter.service.officeService;

@RestController
public class officeController {

	@Autowired
	public officeService officeservice;
	
	@Autowired
	public cityService cityservice;
	
	@RequestMapping("/office")
	public HashMap<String, Object> getOffice(@RequestBody int id)
	{
		HashMap<String, Object> res = new HashMap<>();
		
		if(officeservice.IsExists(id))
		{
			res.put("Data", officeservice.getOffice(id));
			res.put("Message", "Successfully Fetched the office with Id "+id);
			res.put("Success", true);
			res.put("Status", HttpStatus.OK.value());
		}
		else
		{
			res.put("Message", "The city with Id "+id+" Doesn't exists");
			res.put("Success", false);
			res.put("Status", HttpStatus.OK.value());
		}
		
		return res;
	}
	
	@RequestMapping("/office/list")
	public HashMap<String, Object> getlist()
	{
		HashMap<String, Object> res = new HashMap<>();
		
		res.put("Message", "Successfully fetched all the offices accross all the cities");
		res.put("Data", officeservice.getlist());
		res.put("Status", HttpStatus.OK.value());
		res.put("Success",true);

		return res;
	}
	
	@PostMapping("/office/add")
	public HashMap<String, Object> addOffice(@RequestBody Office o)
	{
		HashMap<String , Object> res = new HashMap<>();
		if(officeservice.IsExists(o.getOfficeId()))
		{
			res.put("Message" , "The Office"+ o.toString() +"Already Exists");
			res.put("Success", false);
			res.put("Status", HttpStatus.OK.value());
		}
		else
		{
			officeservice.addoffice(o);	
			res.put("Message" , "The Office " + o.toString() + " Added Successfully");
			res.put("Success", true);
			res.put("Status", HttpStatus.OK.value());
		}
		return res;
	}
		
	
	@RequestMapping(method = RequestMethod.PUT, value="/office/update")
	public HashMap<String, Object> update_put(@RequestBody Office o)
	{
		HashMap<String, Object> res = new HashMap<>();
		
		int cityid = o.getCity().getCityId();
		if( !cityservice.isExist(cityid))
		{
			res.put("Message", "The City can't get updated as it doesn't exists, please add the city first");
			res.put("Status", HttpStatus.OK.value());
			res.put("Success", true);
			return res;
		}
		if(officeservice.IsExists(o.getOfficeId()))
		{
			officeservice.update_office(o);
			res.put("Message", "The Office with the given Id Updated Successfully");
			res.put("Status", HttpStatus.OK.value());
			res.put("Success", true);
		}
		else
		{
			res.put("Message", "The Office with the given Id Doesn't Exist");
			res.put("Status", HttpStatus.OK.value());
			res.put("Success", false);
			
		}
		return res;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value= "/office/delete")
	public HashMap<String, Object> delete(@RequestBody int id)
	{
		HashMap<String, Object> res = new HashMap<>();
		
		if(officeservice.IsExists(id))
		{
			officeservice.delete_office(id);
			
			{
				res.put("Message", "Successfully deleted the Office with the given Id "+id);
				res.put("Status", HttpStatus.OK.value());
				res.put("Success", true);
			}
			
		}
		
		else {
			res.put("Message", "The office doesn't exist with id "+id);
			res.put("Status", HttpStatus.OK.value());
			res.put("Success", false);
			
			
		}
		
		return res;
	}	
	
	
	@RequestMapping("/offices-of-city")
	public HashMap<String, Object> getoffices(@RequestBody int cityId)
	{
		HashMap<String, Object> res = new HashMap<>();
		
		List<Office> data = officeservice.getofficelist(cityId);
		
		return res;
		
	}
	
	
}
