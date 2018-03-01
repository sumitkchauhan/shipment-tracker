package com.turvo.shipment.dao;

import org.springframework.data.repository.CrudRepository;

import com.turvo.shipment.model.Shipper;

/**
 * Shipper DAO
 *
 */
public interface IShipperDao extends CrudRepository<Shipper, String> {

}
