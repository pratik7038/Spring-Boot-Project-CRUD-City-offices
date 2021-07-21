package com.persistent.starter.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class City implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int CityId;
	
	private String CityName;

	private boolean active;
	
	public City() {}
	
	public City(int cityId, String cityName) {
		super();
		CityId = cityId;
		CityName = cityName;
	}
	
	public City(int cityId, String cityName, boolean active) {
		super();
		CityId = cityId;
		CityName = cityName;
		this.active = active;
	}

	public int getCityId() {
		return CityId;
	}
	public void setCityId(int cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}