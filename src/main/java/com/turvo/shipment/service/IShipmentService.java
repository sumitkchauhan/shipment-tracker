package com.turvo.shipment.service;

import com.turvo.shipment.model.Shipment;
import com.turvo.shipment.rest.dto.ShipmentDTO;

public interface IShipmentService {

	String createOrUpdateShipment(Shipment shipment);

	String createOrUpdateShipment(ShipmentDTO shipmentDto);
}
