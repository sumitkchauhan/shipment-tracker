package com.turvo.shipment.rest.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Flattened DTO class to accept Shipment data over REST
 * 
 * @author schau32
 *
 */
public final class ShipmentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1853160982483303701L;
	private String shipmentId;
	private String shipmentName;
	private String shipperId;

	private List<ArrivalDTO> arrivals;

	public String getShipperId() {
		return shipperId;
	}

	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
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

	public List<ArrivalDTO> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<ArrivalDTO> arrivals) {
		this.arrivals = arrivals;
	}

}
