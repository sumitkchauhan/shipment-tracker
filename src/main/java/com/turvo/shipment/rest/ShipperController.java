package com.turvo.shipment.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.shipment.model.Shipper;
import com.turvo.shipment.service.IShipperService;

@RestController
@RequestMapping("/shipper")
public class ShipperController {

	@Autowired
	private IShipperService shipmentService;

	@RequestMapping(method = RequestMethod.POST)
	public String createShipper(@RequestBody Shipper shipper) {
		shipper.setId(null);
		return shipmentService.createShipper(shipper);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public String updateShipper(@PathVariable("id") String id, @RequestBody Shipper shipper) {
		shipper.setId(id);
		return shipmentService.createShipper(shipper);
	}
}
