package com.turvo.shipment.service;

import static com.turvo.shipment.util.Utils.blockEmptyArgument;
import static org.apache.commons.lang.StringUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.shipment.dao.ILocationDao;
import com.turvo.shipment.dao.IShipmentDao;
import com.turvo.shipment.model.Location;
import com.turvo.shipment.model.Shipment;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	private ILocationDao locationDao;

	@Transactional
	@Override
	public String createOrUpdateLocation(Location location) {
		blockEmptyArgument(location);
		locationDao.save(location);
		return location.getId();
	}
}
