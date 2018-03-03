package com.turvo.shipment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShipmentMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093435899585528648L;

	private Shipment shipment;
	private Location lastSeenLocation;
	private List<String> nextDestinations;
	private Date lastSeenDate;

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Location getLastSeenLocation() {
		return lastSeenLocation;
	}

	public void setLastSeenLocation(Location lastSeenLocation) {
		this.lastSeenLocation = lastSeenLocation;
	}

	public List<String> getNextDestinations() {
		return nextDestinations;
	}

	public void setNextDestinations(List<String> nextDestinations) {
		this.nextDestinations = nextDestinations;
	}

	public Date getLastSeenDate() {
		return lastSeenDate;
	}

	public void setLastSeenDate(Date lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}

	@Override
	public String toString() {
		return "[" + shipment.getShipmentId() + ", " + lastSeenLocation.getName() + "]";
	}
}
