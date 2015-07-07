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
		// Find Bidder
		Bidder bidder = (Bidder) em.find(Bidder.class, bidderId);

		// Find Item
		Item item = (Item) em.find(Item.class, itemId);

		// Create new instance of Bid
		Bid bid = new Bid();

		bid.setBidPrice(bidPrice);

		// Set RelationShip
		bid.setBidder(bidder);
		bid.setItem(item);

		// Persist Bid
		em.persist(bid);

		return bid;
	}
}