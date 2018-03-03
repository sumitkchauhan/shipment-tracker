package com.turvo.shipment.dao;

import org.springframework.data.repository.CrudRepository;

import com.turvo.shipment.model.Location;

/**
 * Shipper DAO
 *
 */
public interface ILocationDao extends CrudRepository<Location, String> {

}
