package com.persistent.starter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.starter.model.City;
import com.persistent.starter.model.Office;
import com.persistent.starter.repository.cityRepository;
import com.persistent.starter.repository.officeRepository;

@Service
public class officeService {
	
	@Autowired
	private officeRepository officerepository;
	
	@Autowired
	private cityRepository cityrepository ;
	
	//private List<Office> obj = new ArrayList<>(Arrays.asList(new Office(30,"beed")));
	
	public officeService() {
	}

	public Office getOffice(int id)
	{
		if(officerepository.existsById(id))
		return officerepository.findById(id).get() ;
		
		return new Office(00,"No office exists");
	}
	
	public List<Office> getlist()
	{
		List<Office> lst = new ArrayList<>(officerepository.findAll());
		
		//officerepository.findAll().forEach(lst::add);
		
		return lst;
	}

	public void addoffice(Office office) {
		
		officerepository.save(office);
			
	}

	public void update_office( Office o) {
	
		officerepository.save(o);
		
	}

	public void delete_office(int id) {

		officerepository.deleteById(id);
	}

	public boolean IsExists(int officeId) {
		// TODO Auto-generated method stub
		if(officerepository.existsById(officeId))
			return true;
		else
			return false;
	}

	public List<Office> getofficelist(int cityId) {
		// TODO Auto-generated method stub
		return officerepository.findByCityId(cityId);
	}
	
//	public List<Office> listoffice(int id)
//	{
//		List<Office> lst = new ArrayList<>();
//		
//		lst = officerepository.findByCityId(id);
//		return lst;
//}
	
	
}
