package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb3inaction.actionbazaar.persistence.Bidder;
import com.ejb3inaction.actionbazaar.persistence.BidderStatus;
import com.ejb3inaction.actionbazaar.persistence.Category;
import com.ejb3inaction.actionbazaar.persistence.Item;
import com.ejb3inaction.actionbazaar.persistence.Seller;
import com.ejb3inaction.actionbazaar.persistence.User;

@Stateless(name = "BazaarAdmin")
public class BazaarAdminBean implements BazaarAdmin {

	@PersistenceContext
	private EntityManager em;

	// Un-comment if you want to use application-managed entity manager. Injects
	// persistence unit.
	// @PersistenceUnit(name="actionBazaar")
	// private EntityManagerFactory emf;

	// Un-comment if you want to use application-managed entity manager. Creates
	// application managed entity manager.
	// @PostConstruct
	// public void initialize() {
	// em = emf.createEntityManager();
	// }

	public Category createCategory(String name, String userId) {
		Category category = new Category();
		category.setCategoryName(name);

		User user = em.find(User.class, userId);

		category.setUser(user);
		user.addCategory(category);

		em.persist(category);

		return category;
	}

	public Item createItem(String name, String userId, Long categoryId,
			Double initialPrice) {
		Item item = new Item();
		item.setItemName(name);
		item.setInitialPrice(initialPrice);

		Seller seller = em.find(Seller.class, userId);
		item.setSeller(seller);

		Category category = em.find(Category.class, categoryId);
		item.addCategory(category);

		em.persist(item);

		return item;
	}

	public Category mergeCategory(Category category) {
		em.merge(category);

		return category;
	}

	public Bidder createBidder(Bidder bidder) {
		bidder.setBidderStatus(BidderStatus.NEW);

		em.persist(bidder);

		return bidder;
	}

	public Category refreshCategory(Category category) {
		em.refresh(category);

		return category;
	}

	public void removeCategory(Category category) {
		em.remove(em.merge(category));
	}

	// Un-comment if you want to use application-managed entity manager. Closes
	// application managed entity manager in post destroy.
	// @PreDestroy
	// public void cleanup() {
	// if (em.isOpen())
	// em.close();
	// }
}