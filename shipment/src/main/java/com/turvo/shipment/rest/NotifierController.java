package com.turvo.shipment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.shipment.model.ShipmentNotification;
import com.turvo.shipment.service.INotifier;

@RestController
@RequestMapping("/notifier")
public class NotifierController {

	@Autowired
	private INotifier notifierSvc;

	@RequestMapping(method = RequestMethod.POST)
	public void createShipment(@RequestBody ShipmentNotification notification) {
		notifierSvc.notify(notification);
	}

}
