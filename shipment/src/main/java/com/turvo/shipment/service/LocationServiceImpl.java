package com.turvo.shipment.service;

import static com.turvo.shipment.util.Utils.blockEmptyArgument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.shipment.dao.ILocationDao;
import com.turvo.shipment.model.Location;

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
