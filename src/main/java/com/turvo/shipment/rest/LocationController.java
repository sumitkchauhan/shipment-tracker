package com.turvo.shipment.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.shipment.model.Location;
import com.turvo.shipment.service.ILocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private ILocationService shipmentService;

	@RequestMapping(method = RequestMethod.POST)
	public String createShipper(@RequestBody Location location) {
		location.setId(null);
		return shipmentService.createOrUpdateLocation(location);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public String updateShipper(@PathVariable("id") String id, @RequestBody Location location) {
		location.setId(id);
		return shipmentService.createOrUpdateLocation(location);
	}
}
