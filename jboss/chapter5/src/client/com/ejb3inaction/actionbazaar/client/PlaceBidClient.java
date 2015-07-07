package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.PlaceBid;
import com.ejb3inaction.actionbazaar.buslogic.PlaceBidBean;

public class PlaceBidClient {
	private static PlaceBid placeBid;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();

			placeBid = (PlaceBid) context.lookup("chapter5/"
					+ PlaceBidBean.class.getSimpleName() + "/remote");

			System.out.println("Bid price sent is: " + 20000.5);
			System.out.println("Bid successful, bid ID received is: "
					+ placeBid.addBid("dpanda", 403L, 20000.50));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}