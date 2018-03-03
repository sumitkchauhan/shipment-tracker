package com.turvo.shipment.service;

import com.turvo.shipment.model.ShipmentNotification;

/**
 * Accepts notifications which might be eventually delivered synchronously or
 * asynchronously
 * 
 * @author schau32
 *
 */
public interface INotifier {

	void notify(ShipmentNotification shipmentNotification);
}
