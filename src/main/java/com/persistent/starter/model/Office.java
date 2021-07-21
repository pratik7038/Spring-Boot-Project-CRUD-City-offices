package com.persistent.starter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.qos.logback.core.net.SyslogOutputStream;

@Entity
@Table
public class Office implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int OfficeId;
	
	private String OfficeAddress;

	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private City city;
	
	public Office() {}

	public Office(int officeId, String officeAddress) {
		super();
		OfficeId = officeId;
		OfficeAddress = officeAddress;
	}
	

	public Office(int officeId, String officeAddress, City city) {
		super();
		OfficeId = officeId;
		OfficeAddress = officeAddress;
		this.city = city;
	}

	public int getOfficeId() {
		return OfficeId;
	}

	public void setOfficeId(int officeId) {
		OfficeId = officeId;
	}

	public String getOfficeAddress() {
		return OfficeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		OfficeAddress = officeAddress;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	@Override
	public String toString()
	{
		
		return "id : "+OfficeId +" Address : "+OfficeAddress;
	}
	
}
