package com.turvo.shipment.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1463483866176871871L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
