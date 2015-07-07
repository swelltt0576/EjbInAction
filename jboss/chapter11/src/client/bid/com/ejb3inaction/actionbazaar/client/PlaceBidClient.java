package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.PlaceBid;
import com.ejb3inaction.actionbazaar.persistence.Bid;

public class PlaceBidClient {
	private static PlaceBid placeBid;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			placeBid = (PlaceBid) context.lookup("chapter11/"
					+ PlaceBid.class.getSimpleName() + "/remote");

			Bid bid = placeBid.addBid("idiot", 100L, 2000.50);
			System.out.println("Bid successful, bid ID received is: "
					+ bid.getBidId());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}