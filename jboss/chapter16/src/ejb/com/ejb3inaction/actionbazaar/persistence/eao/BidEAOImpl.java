package com.ejb3inaction.actionbazaar.persistence.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb3inaction.actionbazaar.buslogic.BidException;
import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.BidStatus;
import com.ejb3inaction.actionbazaar.persistence.Bidder;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Stateless
public class BidEAOImpl implements BidEAO {
	@PersistenceContext(name = "actionBazaar")
	private EntityManager em;

	public Bid addBid(String bidderId, Item item, double bidPrice) {
		Bid bid = new Bid();
		bid.setItem(item);
		bid.setBidPrice(bidPrice);
		bid.setBidStatus(BidStatus.NEW);

		System.out.println("Finding bidder: " + bidderId);
		Bidder bidder = em.find(Bidder.class, bidderId);

		if (bidder == null) {
			throw new BidException("Invalid bidder ID: " + bidderId);
		}

		System.out.println("Found bidder: " + bidder.getFirstName());

		bid.setBidder(bidder);

		em.persist(bid);

		System.out.println("Persisted bid: " + bid.getBidId());

		return bid;
	}

	public Bid cancelBid(Long bidId) {
		Bid bid = em.find(Bid.class, bidId);
		bid.setBidStatus(BidStatus.CANCELLED);
		em.merge(bid);

		return bid;
	}
}