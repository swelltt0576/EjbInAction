package com.ejb3inaction.actionbazaar.buslogic;

import java.io.Serializable;

public class ShippingRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private long item;
	private String shippingAddress;
	private String shippingMethod;
	private double insuranceAmount;

	public long getItem() {
		return item;
	}

	public void setItem(long item) {
		this.item = item;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public double getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(double insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
}