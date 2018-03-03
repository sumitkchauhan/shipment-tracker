package com.turvo.shipment.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * Location class
 * @author schau32
 *
 */
@Entity
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1463483866176871871L;

	@Id
	private String id;

	private String name;

	@PrePersist
	public void initializeUUID() {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
	}
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
