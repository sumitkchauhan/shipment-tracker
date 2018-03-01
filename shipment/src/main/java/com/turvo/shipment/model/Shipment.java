package com.turvo.shipment.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "uuid")
	@Column(name = "id")
	private String shipmentId;
	@Column(name = "name")
	private String shipmentName;

	@ElementCollection
	@CollectionTable(name="arrival", joinColumns=@JoinColumn(name="shipment_id"))
	private List<Arrival> arrivals;

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

}
