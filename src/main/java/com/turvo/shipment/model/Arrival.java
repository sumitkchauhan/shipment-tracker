package com.turvo.shipment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Arrival implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8856477755397128324L;
	@ManyToOne
	@JoinColumn(name = "DESTINATION_ID")
	private Location destination;
	private Date expectedTime;

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public Date getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(Date expectedTime) {
		this.expectedTime = expectedTime;
	}

}
