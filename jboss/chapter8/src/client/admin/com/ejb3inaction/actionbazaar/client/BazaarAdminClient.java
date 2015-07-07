package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.BazaarAdmin;
import com.ejb3inaction.actionbazaar.persistence.Bidder;

public class BazaarAdminClient {
	private static BazaarAdmin bazaarAdmin;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			bazaarAdmin = (BazaarAdmin) context.lookup("chapter8/"
					+ BazaarAdmin.class.getSimpleName() + "/remote");

			Bidder bidder = new Bidder();

			bidder.setUserId("junkaddict");
			bidder.setFirstName("Serious");
			bidder.setLastName("Bidder");
			bidder.setCreditRating(new Long(65));

			bidder = bazaarAdmin.createBidder(bidder);

			System.out.println("Bidder: " + bidder.getUserId()
					+ "BidderStatus: " + bidder.getBidderStatus());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}