package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.BillingInfo;
import com.ejb3inaction.actionbazaar.persistence.ShippingInfo;

@Remote
public interface PlaceOrder {
	void setBidder(String bidderId);

	void addItem(Long itemId);

	void setShippingInfo(ShippingInfo shippingInfo);

	void setBillingInfo(BillingInfo billingInfo);

	Long confirmOrder();
}