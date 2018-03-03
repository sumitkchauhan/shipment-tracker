package com.turvo.shipment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.shipment.rest.dto.ShipmentDTO;
import com.turvo.shipment.service.IShipmentService;

@RestController
@RequestMapping("/shipment")
public class ShipmentRestController {
	@Autowired
	private IShipmentService shipmentSvc;

	@RequestMapping(method = RequestMethod.POST)
	public String createShipment(@RequestBody ShipmentDTO shipmentDto) {
		shipmentDto.setShipmentId(null);
		return shipmentSvc.createOrUpdateShipment(shipmentDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String createShipment(@PathVariable("id") String id, @RequestBody ShipmentDTO shipmentDto) {
		shipmentDto.setShipmentId(id);
		return shipmentSvc.createOrUpdateShipment(shipmentDto);
	}

}
