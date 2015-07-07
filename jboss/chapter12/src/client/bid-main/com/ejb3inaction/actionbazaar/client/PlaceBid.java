package com.ejb3inaction.actionbazaar.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ejb3inaction.actionbazaar.buslogic.BidException;
import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Bidder;
import com.ejb3inaction.actionbazaar.persistence.Item;

public class PlaceBid {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		String userId = "idiot";
		Long itemId = new Long(1);
		Double bidPrice = 2001.50;

		try {
			emf = Persistence.createEntityManagerFactory("actionBazaar");
			em = emf.createEntityManager();

			em.getTransaction().begin();
			addBid(userId, itemId, bidPrice);
			em.getTransaction().commit();

			System.out.println("Successfully added bid for item ID: " + itemId
					+ " with user ID: " + userId + " for the amount of: "
					+ bidPrice);
		} finally {
			// Close the EntityManager when done.
			em.close();
			emf.close();
		}
	}

	private static Long addBid(String userId, Long itemId, Double bidPrice)
			throws BidException {
		Item item = em.find(Item.class, itemId);

		if (item == null) {
			throw new BidException("Invalid item ID.");
		}

		Bidder bidder = em.find(Bidder.class, userId);

		if (bidder == null) {
			throw new BidException("Invalid bidder ID.");
		}

		Bid bid = new Bid();
		bid.setItem(item);
		bid.setBidder(bidder);
		bid.setBidPrice(bidPrice);

		em.persist(bid);

		return bid.getBidId();
	}
}