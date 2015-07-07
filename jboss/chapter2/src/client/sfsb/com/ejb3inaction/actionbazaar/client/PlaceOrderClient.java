package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.PlaceOrder;
import com.ejb3inaction.actionbazaar.buslogic.PlaceOrderBean;
import com.ejb3inaction.actionbazaar.persistence.BillingInfo;
import com.ejb3inaction.actionbazaar.persistence.ShippingInfo;

public class PlaceOrderClient {
	private static PlaceOrder placeOrder;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			placeOrder = (PlaceOrder) context.lookup("chapter2/"
					+ PlaceOrderBean.class.getSimpleName() + "/remote");

			System.out.println("Exercising PlaceOrder EJB...");
			placeOrder.setBidderId(new Long(100));
			placeOrder.addItem(new Long(200));
			placeOrder.addItem(new Long(201));
			placeOrder.setShippingInfo(new ShippingInfo("123 My Sweet Home",
					"My City", "My State"));
			placeOrder.setBillingInfo(new BillingInfo("VISA", "123456789",
					"0708"));
			Long orderId = placeOrder.confirmOrder();
			System.out.println("Added Order: " + orderId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
