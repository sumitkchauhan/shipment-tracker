package com.turvo.shipment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ShipmentNotification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5691821634449446614L;
	private String shipmentId;
	private String lastSeenLocId;
	private List<String> nextDestinations;
	@JsonFormat(pattern = "dd/MM/yyyy, hh:mm:ss")
	private Date lastSeenDate;

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getLastSeenLocId() {
		return lastSeenLocId;
	}

	public void setLastSeenLocId(String lastSeenLocId) {
		this.lastSeenLocId = lastSeenLocId;
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
		return "[" + shipmentId + ", " + lastSeenLocId + "]";
	}

}
