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
			placeBid = (PlaceBid) context.lookup("chapter2/"
					+ PlaceBid.class.getSimpleName() + "/remote");

			Bid bid = new Bid();
			bid.setBidderId("rrahman");
			bid.setItemId(Long.valueOf(100));
			bid.setBidPrice(20000.40);

			System.out.println("Bid successful, bid ID received is:"
					+ placeBid.addBid(bid).getBidId());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}