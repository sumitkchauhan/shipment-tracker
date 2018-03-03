package com.turvo.shipment.service;

import com.turvo.shipment.model.Shipper;

/**
 * 
 * Shipment Service Interface
 */
public interface IShipperService {
	/**
	 * Creates or updates shipper in its entirety, which means all the addresses
	 * will be recreated
	 */
	String createShipper(Shipper shipper);

}
