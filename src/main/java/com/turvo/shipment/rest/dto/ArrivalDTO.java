package com.turvo.shipment.rest.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Flattened arrival DTO class
 * 
 * @author schau32
 *
 */
public class ArrivalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43261271422164588L;

	private String locationId;

	@JsonFormat(pattern = "dd/MM/yyyy, hh:mm:ss")
	private Date date;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
