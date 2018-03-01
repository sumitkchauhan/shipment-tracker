package com.turvo.shipment.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "shipper")
public class Shipper {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	private String id;

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@ElementCollection
	@CollectionTable(name="shipper_channel", joinColumns=@JoinColumn(name="shipper_id"))
	private List<Address> addresses;

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
