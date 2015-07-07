package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.PlaceBid;

public class PlaceBidClient {
	private static PlaceBid placeBid;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();

			placeBid = (PlaceBid) context.lookup("chapter12/"
					+ PlaceBid.class.getSimpleName() + "/remote");

			System.out.println("Bid price sent to the bean is: " + 200000.5);
			System.out.println("Bid successful, bid ID received is: "
					+ placeBid.addBid("idiot", 1L, 2000005.50));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}