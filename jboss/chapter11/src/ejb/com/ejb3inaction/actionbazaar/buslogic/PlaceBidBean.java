package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Bidder;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Stateless(name = "PlaceBid")
public class PlaceBidBean implements PlaceBid {
	@PersistenceContext(unitName = "actionBazaar")
	private EntityManager em;

	public Bid addBid(String bidderId, Long itemId, Double bidPrice) {
		// Find bidder.
		Bidder bidder = (Bidder) em.find(Bidder.class, bidderId);

		// Find item.
		Item item = (Item) em.find(Item.class, itemId);

		// Creates a new instance of Bid.
		Bid bid = new Bid();

		bid.setBidPrice(bidPrice);

		// Set relationships.
		bid.setBidder(bidder);
		bid.setItem(item);

		// Persist bid.
		em.persist(bid);

		return bid;
	}
}
