package com.ejb3inaction.actionbazaar.buslogic;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Item;
import com.ejb3inaction.actionbazaar.persistence.eao.BidEAO;
import com.ejb3inaction.actionbazaar.persistence.eao.ItemEAO;

public class BidServiceBean implements BidService {
	private ItemEAO itemEAO;
	private BidEAO bidEAO;

	// Setters needed for Spring injection.
	public void setItemEAO(ItemEAO itemEAO) {
		this.itemEAO = itemEAO;
	}

	public void setBidEAO(BidEAO bidEAO) {
		this.bidEAO = bidEAO;
	}

	public Long addBid(String userId, Long itemId, Double bidPrice) {
		System.out.println("Bid for " + itemId + " received with price "
				+ bidPrice);
		System.out.println("Creating bid...");

		Item item = itemEAO.findByItemId(itemId);
		if (item == null) {
			throw new BidException("No such item with ID: " + itemId);
		}

		if (bidPrice <= item.getInitialPrice()) {
			throw new BidException("Price is lower than initial price.");
		}

		Bid highBid = itemEAO.findHighestBidForItem(item);

		if (highBid != null) {
			System.out.println("Highest bid ID: " + highBid.getBidId()
					+ ", price: " + highBid.getBidPrice());
			System.out.println("Current bid price: " + bidPrice);

			if (bidPrice <= highBid.getBidPrice()) {
				throw new BidException(
						"Bid price is lower than the current bid price.");
			}
		}

		Bid bid = this.bidEAO.addBid(userId, item, bidPrice);

		return bid.getBidId();
	}
}