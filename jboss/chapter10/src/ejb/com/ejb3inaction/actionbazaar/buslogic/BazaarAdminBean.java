package com.ejb3inaction.actionbazaar.buslogic;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.ejb3inaction.actionbazaar.persistence.Category;
import com.ejb3inaction.actionbazaar.persistence.User;

@Stateless(name = "BazaarAdmin")
public class BazaarAdminBean implements BazaarAdmin {

	@PersistenceContext
	private EntityManager em;

	/* Demonstrates dynamic query, positional parameters and a single result. */
	public Category findByFullCategoryName(String categoryName) {
		Query query = em
				.createQuery("SELECT c FROM Category c WHERE c.categoryName =?1");
		query.setParameter(1, categoryName);
		return (Category) query.getSingleResult();
	}

	/*
	 * Demonstrates named queries with named parameters and paginating through a
	 * query.
	 */
	public List findByCategoryName(String categoryName) {
		Query query = em.createNamedQuery("findCategoryByName");
		query.setParameter("categoryName", '%' + categoryName + '%');
		query.setMaxResults(10);
		query.setFirstResult(3);
		List categories = query.getResultList();
		return categories;
	}

	public List findByUser(String userId) {
		Query query = em.createNamedQuery("findCategoryByUser");
		query.setParameter(1, userId);
		List categories = query.getResultList();
		return categories;
	}

	/* Demonstrates named queries with a date type as parameter. */
	public List getItemByDate(Date currentDate) {
		return em.createNamedQuery("findItemByDate").setParameter(
				"currentDate", currentDate, TemporalType.DATE).getResultList();
	}

	public List getItemByPriceRange(Double lowPrice, Double highPrice) {
		return em.createNamedQuery("findItemByInitialPrice").setParameter(
				"lowPrice", lowPrice).setParameter("highPrice", highPrice)
				.getResultList();
	}

	public List getUserWithItems() {
		return em.createNamedQuery("findUserWithItems").getResultList();
	}

	public List getUserWithNoItems() {
		return em.createNamedQuery("findUserWithNoItems").getResultList();
	}

	/* Demonstrates dynamic native query. */
	public List getUserWithItemsWithNativeQuery() {
		Query q = em.createNativeQuery(
				"select user_id, first_name, last_name, birth_date, billing_id from users"
						+ " where user_id in (select seller_id from "
						+ "items group by seller_id having count(*) >1)",
				User.class);
		return q.getResultList();
	}

	/* Demonstrates named native queries. */
	public List getUserWithItemsWithNamedNativeQuery() {
		return em.createNamedQuery("findUserWithMoreItems").setParameter(1, 5)
				.getResultList();
	}
}