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

			placeOrder = (PlaceOrder) context.lookup("chapter13/"
					+ PlaceOrderBean.class.getSimpleName() + "/remote");

			System.out.println("Exercising PlaceOrder EJB...");

			placeOrder.setBidder("idiot2");
			placeOrder.addItem(new Long(1));
			placeOrder.addItem(new Long(2));
			placeOrder.setShippingInfo(new ShippingInfo("123 My Sweet Home",
					"MyCity", "MyState"));
			placeOrder.setBillingInfo(new BillingInfo("123456789", "VISA",
					"0708"));

			Long orderId = placeOrder.confirmOrder();

			System.out.println("Order confirmation number: " + orderId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}