package com.turvo.shipment.service;

import static org.apache.commons.lang.StringUtils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.shipment.dao.IShipperDao;
import com.turvo.shipment.model.Shipper;

@Service
public class ShipperServiceImpl implements IShipperService {

	@Autowired
	private IShipperDao shipperDao;

	@Transactional
	@Override
	public String createShipper(Shipper shipper) {
		if (!isEmpty(shipper.getId())) {
			Shipper loadedShipper = shipperDao.findOne(shipper.getId());
			if (null != loadedShipper) {
				// this is an overwrite. Delete the old entity
				shipperDao.delete(loadedShipper);
			}
		}
		shipperDao.save(shipper);
		return shipper.getId();
	}

}
