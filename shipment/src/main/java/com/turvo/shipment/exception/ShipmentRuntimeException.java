package com.turvo.shipment.exception;

/**
 * Core unchecked exception class
 * 
 * @author schau32
 *
 */
public class ShipmentRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 233447807247334673L;

	public ShipmentRuntimeException(String mssg) {
		super(mssg);
	}

}
