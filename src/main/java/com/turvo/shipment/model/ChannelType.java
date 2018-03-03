package com.turvo.shipment.model;

public enum ChannelType {
	EMAIL("Email"), SMS("SMS");

	private final String stringRep;

	private ChannelType(String stringRepresentation) {
		this.stringRep = stringRepresentation;
	}

	@Override
	public String toString() {
		return stringRep;
	}
}
