package com.turvo.shipment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

/**
 * Model class for Shipment
 * 
 * @author schau32
 *
 */
@Entity
public class Shipment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1471781182420458934L;

	@Id
	@Column(name = "id")
	private String shipmentId;
	@Column(name = "name")
	private String shipmentName;
	
	@ManyToOne
	@JoinColumn(name="shipper_id")
	private Shipper shipper;

	@ElementCollection
	@CollectionTable(name="arrival", joinColumns=@JoinColumn(name="shipment_id"))
	private List<Arrival> arrivals = new ArrayList<>();

	@PrePersist
	public void initializeUUID() {
		if (shipmentId == null) {
			shipmentId = UUID.randomUUID().toString();
		}
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getShipmentName() {
		return shipmentName;
	}

	public void setShipmentName(String shipmentName) {
		this.shipmentName = shipmentName;
	}

	public List<Arrival> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<Arrival> arrivals) {
		this.arrivals = arrivals;
	}

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

}
