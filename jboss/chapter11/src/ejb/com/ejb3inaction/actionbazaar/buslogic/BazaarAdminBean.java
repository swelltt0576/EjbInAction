package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb3inaction.actionbazaar.persistence.Category;
import com.ejb3inaction.actionbazaar.persistence.Item;
import com.ejb3inaction.actionbazaar.persistence.User;

@Stateless(name = "BazaarAdmin")
public class BazaarAdminBean implements BazaarAdmin {
	@PersistenceContext(name = "actionBazaar")
	private EntityManager em;

	/* Un-comment if you want to use an application-managed entity manager. */
	/* Injects persistence unit. */
	// @PersistenceUnit(name="actionBazaar") private EntityManagerFactory emf;
	/* Un-comment if you want to use an application-managed entity manager. */
	/* Creates an application managed entity manager. */
	// @PostConstruct public void initialize() { em = emf.createEntityManager();
	// }
	public Category createCategory(String name, String userId) {
		Category category = new Category();
		category.setCategoryName(name);
		User user = em.find(User.class, userId);
		category.setUser(user);
		user.addCategory(category);
		return category;
	}

	public Item createItem(String name, String userId, Long categoryId,
			Double initialPrice) {
		Item item = new Item();
		item.setItemName(name);
		item.setInitialPrice(initialPrice);
		Category category = em.find(Category.class, categoryId);
		item.addCategory(category);
		return item;
	}

	public Category mergeCategory(Category category) {
		em.merge(category);
		return category;
	}

	public Category refreshCategory(Category category) {
		em.refresh(category);
		return category;
	}

	public void removeCategory(Category category) {
		em.remove(em.merge(category));
	}

	/* Un-comment if you want to use an application-managed entity manager. */
	/* Closes an application managed entity manager in post destroy. */
	// @PreDestroy public void cleanup() { if (em.isOpen()) em.close(); }
}