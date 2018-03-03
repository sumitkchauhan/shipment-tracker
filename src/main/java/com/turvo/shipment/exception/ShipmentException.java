package com.turvo.shipment.exception;

/**
 * Core Checked Exception class
 * @author schau32
 *
 */
public class ShipmentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6473579906209911059L;

	public ShipmentException(String mssg) {
		super(mssg);
	}
}
