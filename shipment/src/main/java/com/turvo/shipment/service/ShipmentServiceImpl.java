package com.turvo.shipment.service;

import static com.turvo.shipment.util.Utils.blockEmptyArgument;
import static org.apache.commons.lang.StringUtils.isEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.shipment.dao.ILocationDao;
import com.turvo.shipment.dao.IShipmentDao;
import com.turvo.shipment.dao.IShipperDao;
import com.turvo.shipment.exception.ShipmentRuntimeException;
import com.turvo.shipment.model.Arrival;
import com.turvo.shipment.model.Location;
import com.turvo.shipment.model.Shipment;
import com.turvo.shipment.model.Shipper;
import com.turvo.shipment.rest.dto.ArrivalDTO;
import com.turvo.shipment.rest.dto.ShipmentDTO;

/**
 * Shipment service class
 * 
 * @author schau32
 *
 */
@Service
public class ShipmentServiceImpl implements IShipmentService {
	private static final Logger LOG = LoggerFactory.getLogger(ShipmentServiceImpl.class);

	@Autowired
	private IShipmentDao shipmentDao;

	@Autowired
	private ILocationDao locationDao;

	@Autowired
	private IShipperDao shipperDao;

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
		return shipment.getShipmentId();
	}

	@Transactional
	public String createOrUpdateShipment(ShipmentDTO shipment) {
		blockEmptyArgument(shipment);
		return createOrUpdateShipment(validateMapShipment(shipment));
	}

	private Shipment validateMapShipment(ShipmentDTO shipment) {
		Shipment shipmentObj = new Shipment();
		Location location;
		Arrival arrival;
		Shipper shipper = shipperDao.findOne(shipment.getShipperId());
		if (null == shipper) {
			LOG.error("Shipper with id : " + shipment.getShipperId() + " not found for shipment : "
					+ shipment.getShipmentId());
			throw new ShipmentRuntimeException("Shipper with id : " + shipment.getShipperId()
					+ " not found for shipment : " + shipment.getShipmentId());
		}
		shipmentObj.setShipper(shipper);
		for (ArrivalDTO arrivalDto : shipment.getArrivals()) {
			if (null == (location = locationDao.findOne(arrivalDto.getLocationId()))) {
				LOG.error("Location with id:" + arrivalDto.getLocationId() + " not found for shipment : "
						+ shipment.getShipmentId());
				throw new ShipmentRuntimeException("Location with id:" + arrivalDto.getLocationId()
						+ " not found for shipment : " + shipment.getShipmentId());
			}
			arrival = new Arrival();
			arrival.setDestination(location);
			arrival.setExpectedTime(arrivalDto.getDate());
			shipmentObj.getArrivals().add(arrival);
		}
		shipmentObj.setShipmentId(shipment.getShipmentId());
		shipmentObj.setShipmentName(shipment.getShipmentName());
		return shipmentObj;
	}

}
