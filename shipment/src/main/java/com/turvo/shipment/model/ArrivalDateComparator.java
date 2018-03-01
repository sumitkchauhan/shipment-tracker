package com.turvo.shipment.model;

import java.util.Comparator;

public class ArrivalDateComparator implements Comparator<Arrival> {

	@Override
	public int compare(Arrival arr1, Arrival arr2) {
		return arr1.getExpectedTime().compareTo(arr2.getExpectedTime());
	}

}
