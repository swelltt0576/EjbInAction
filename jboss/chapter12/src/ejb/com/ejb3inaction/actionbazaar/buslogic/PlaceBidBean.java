package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Item;
import com.ejb3inaction.actionbazaar.persistence.eao.BidEAO;
import com.ejb3inaction.actionbazaar.persistence.eao.ItemEAO;

@Stateless(name = "PlaceBid")
public class PlaceBidBean implements PlaceBid {

	@EJB
	private ItemEAO itemEAO;

	@EJB
	private BidEAO bidEAO;

	public Long addBid(String userId, Long itemId, Double bidPrice)
			throws BidException {
		System.out.println("Bid for " + itemId + " received with price "
				+ bidPrice);
		Item item = itemEAO.findByItemId(itemId);

		if (item == null) {
			throw new BidException("Invalid item ID: " + itemId);
		}

		Bid highBid = itemEAO.findHighestBidForItem(item);

		if (highBid != null) {
			System.out.println("Highest bid ID: " + highBid.getBidId()
					+ " price: " + highBid.getBidPrice());

			System.out.println("Current bid price: " + bidPrice);

			if (bidPrice <= highBid.getBidPrice()) {
				throw new BidException(
						"Bid price is lower than the current bid price.");
			}
		}

		Bid bid = bidEAO.addBid(userId, item, bidPrice);
		return bid.getBidId();
	}
}