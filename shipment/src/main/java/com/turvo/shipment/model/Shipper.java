package com.turvo.shipment.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;

@Entity(name = "shipper")
public class Shipper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 206185062419496924L;

	@Id
	private String id;

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "shipper_channel", joinColumns = @JoinColumn(name = "shipper_id"))
	private List<Address> addresses;

	@PrePersist
	public void initializeUUID() {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
	}

	@Override
	public String toString() {
		return addresses.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
