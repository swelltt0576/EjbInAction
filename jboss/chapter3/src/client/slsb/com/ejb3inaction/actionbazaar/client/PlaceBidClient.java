package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.BidManager;
import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Bidder;
import com.ejb3inaction.actionbazaar.persistence.Item;

public class PlaceBidClient {
	private static BidManager placeBid;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();

			placeBid = (BidManager) context.lookup("chapter3/"
					+ BidManager.class.getSimpleName() + "/remote");

			Item item = new Item();
			item.setItemId(new Long(100));

			Bidder bidder = new Bidder();
			bidder.setUserId("viper");

			Bid bid = new Bid();
			bid.setBidder(bidder);
			bid.setItem(item);
			bid.setBidPrice(10000.50);

			System.out.println("Bid Successful, BidId Received is:"
					+ placeBid.addBid(bid));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}