package com.turvo.shipment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.shipment.model.Shipment;
import com.turvo.shipment.rest.dto.ShipmentDTO;
import com.turvo.shipment.service.IShipmentService;

@RestController
@RequestMapping("/shipment")
public class ShipmentRestController {
	
/*	@Autowired
	private IShipmentService shipmentSvc;
	@RequestMapping(method=RequestMethod.POST)
	public String createShipment(ShipmentDTO shipmentDto) {
		return shipmentSvc.createOrUpdateShipment(shipment);
	}
	
	private Shipment mapShipment(ShipmentDTO shipmentDto) {
		
	}*/
	
}
