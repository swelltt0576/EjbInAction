package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb3inaction.actionbazaar.persistence.Bid;

@Stateless(name = "PlaceBid")
public class PlaceBidBean implements PlaceBid {
	@PersistenceContext(unitName = "actionBazaar")
	private EntityManager em;

	public Bid addBid(Bid bid) {
		System.out.println("Adding bid, bidder ID=" + bid.getBidderId()
				+ ", item ID=" + bid.getItemId() + ", bid amount="
				+ bid.getBidPrice() + ".");
		return save(bid);
	}

	private Bid save(Bid bid) {
		em.persist(bid);
		System.out.println("Your bid item id: " + bid.getItemId()
				+ " was successful.");
		System.out.println("Your bid id is: " + bid.getBidId());
		return bid;
	}
}