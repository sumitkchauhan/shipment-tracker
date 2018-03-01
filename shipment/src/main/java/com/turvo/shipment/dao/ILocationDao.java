package com.turvo.shipment.dao;

import org.springframework.data.repository.CrudRepository;

import com.turvo.shipment.model.Location;
import com.turvo.shipment.model.Shipment;
import com.turvo.shipment.model.Shipper;

/**
 * Shipper DAO
 *
 */
public interface ILocationDao extends CrudRepository<Location, String> {

}
