package com.turvo.shipment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 394533537097042612L;
	@Enumerated(EnumType.STRING)
	private ChannelType channelType;
	@Column(name="address")
	private String addressValue;

	@Override
	public int hashCode() {
		return channelType.hashCode() + 31 * (addressValue.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (null != obj && obj.getClass().equals(this.getClass())) {
			Address that = (Address) obj;
			isEqual = this.channelType == that.channelType && this.addressValue.equalsIgnoreCase(that.addressValue);
		}
		return isEqual;
	}

	@Override
	public String toString() {
		return "[ChannelType=" + channelType + "; Address=" + addressValue + "]";
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public String getAddressValue() {
		return addressValue;
	}

	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue;
	}
	

}
