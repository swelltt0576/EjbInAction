package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ShippingInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String street;
	protected String city;
	protected String state;

	public ShippingInfo() {
	}

	public ShippingInfo(String street, String city, String state) {
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}