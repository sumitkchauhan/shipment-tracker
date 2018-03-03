package com.turvo.shipment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.shipment.dao.ILocationDao;
import com.turvo.shipment.dao.IShipmentDao;
import com.turvo.shipment.exception.ShipmentRuntimeException;
import com.turvo.shipment.model.Location;
import com.turvo.shipment.model.Shipment;
import com.turvo.shipment.model.ShipmentMessage;
import com.turvo.shipment.model.ShipmentNotification;

/**
 * Notifier Service
 * 
 * @author schau32
 *
 */
@Service
public class Notifier implements INotifier {

	private static final Logger LOG = LoggerFactory.getLogger(Notifier.class);
	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${notification.queue.name}")
	private String queueName;

	@Autowired
	private IShipmentDao shipmentDao;
	@Autowired
	private ILocationDao locationDao;

	@Transactional
	@Override
	public void notify(ShipmentNotification shipmentNotification) {
		jmsTemplate.convertAndSend(queueName, validateShipmentNotification(shipmentNotification));
		LOG.debug("Sent notification for {}", shipmentNotification.getShipmentId());
	}

	private ShipmentMessage validateShipmentNotification(ShipmentNotification shipmentNotification) {
		Shipment shipment = shipmentDao.findOne(shipmentNotification.getShipmentId());
		if (null == shipment) {
			LOG.error("No shipment found with the id:" + shipmentNotification.getShipmentId());
			throw new ShipmentRuntimeException("No shipment found with the id:" + shipmentNotification.getShipmentId());
		}
		if (null == shipment.getShipper()) {
			LOG.error("No shipper found for shipment with id:" + shipmentNotification.getShipmentId());
			throw new ShipmentRuntimeException(
					"No shipper found for shipment with id:" + shipmentNotification.getShipmentId());
		}
		Location location = locationDao.findOne(shipmentNotification.getLastSeenLocId());
		if (null == location) {
			LOG.error("No location found with the id:" + shipmentNotification.getLastSeenLocId() + " for shipment "
					+ shipmentNotification.getShipmentId());
			throw new ShipmentRuntimeException(
					"No location found with the id:" + shipmentNotification.getLastSeenLocId() + " for shipment "
							+ shipmentNotification.getShipmentId());
		}
		ShipmentMessage mssg = new ShipmentMessage();
		mssg.setLastSeenDate(shipmentNotification.getLastSeenDate());
		mssg.setLastSeenLocation(location);
		mssg.setShipment(shipment);
		mssg.setNextDestinations(shipmentNotification.getNextDestinations());
		return mssg;
	}

}
