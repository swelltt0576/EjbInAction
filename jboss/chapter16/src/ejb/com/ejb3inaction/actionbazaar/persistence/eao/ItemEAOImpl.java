package com.ejb3inaction.actionbazaar.persistence.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb3inaction.actionbazaar.persistence.Bid;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Stateless
public class ItemEAOImpl implements ItemEAO {

	@PersistenceContext(name = "actionBazaar")
	private EntityManager em;

	public Item addItem(Item item) {
		em.persist(item);

		return item;
	}

	public Item findByItemId(Long itemId) {
		return em.find(Item.class, itemId);
	}

	public Bid findHighestBidForItem(Item item) {
		Query query = em.createQuery("select b from Bid as b "
				+ "where b.item=:item"
				+ " and b.bidPrice = (select max(b2.bidPrice) "
				+ "from Bid as b2 where b2.item=b.item)");
		query.setParameter("item", item);
		try {
			Bid bid = (Bid) query.getSingleResult();
			return bid;
		} catch (NoResultException e) {
			return null;
		}
	}
}