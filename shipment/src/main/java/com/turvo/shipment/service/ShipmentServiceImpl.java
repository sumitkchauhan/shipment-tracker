package com.turvo.shipment.service;

import static com.turvo.shipment.util.Utils.blockEmptyArgument;
import static org.apache.commons.lang.StringUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.shipment.dao.IShipmentDao;
import com.turvo.shipment.model.Shipment;

public class ShipmentServiceImpl implements IShipmentService {

	@Autowired
	private IShipmentDao shipmentDao;

	@Transactional
	@Override
	public String createOrUpdateShipment(Shipment shipment) {
		blockEmptyArgument(shipment, shipment.getArrivals());
		if (!isEmpty(shipment.getShipmentId())) {
			Shipment loadedShipment = shipmentDao.findOne(shipment.getShipmentId());
			if (null != loadedShipment) {
				// this is an overwrite. Delete the old entity
				shipmentDao.delete(loadedShipment);
			}
		}
		shipmentDao.save(shipment);
		return null;
	}

	/*	*//**
			 * Assigns sequence number to arrivals
			 * 
			 * @param shipment
			 *//*
				 * private static void assignSequence(Shipment shipment) {
				 * Collections.sort(shipment.getArrivals(), new ArrivalDateComparator()); short
				 * seq = 0; for (Arrival arrival : shipment.getArrivals()) {
				 * arrival.setSequence(++seq); } }
				 */
}
