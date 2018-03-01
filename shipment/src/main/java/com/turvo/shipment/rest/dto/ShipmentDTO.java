package com.turvo.shipment.rest.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;

public final class ShipmentDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1853160982483303701L;
	private String shipmentId;
	private String shipmentName;
	
	private LinkedHashSet<String> destinationIds;

}
