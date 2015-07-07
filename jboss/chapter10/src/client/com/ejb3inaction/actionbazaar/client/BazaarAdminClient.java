package com.ejb3inaction.actionbazaar.client;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.BazaarAdmin;
import com.ejb3inaction.actionbazaar.persistence.Category;
import com.ejb3inaction.actionbazaar.persistence.Item;
import com.ejb3inaction.actionbazaar.persistence.User;

public class BazaarAdminClient {
	private static BazaarAdmin bazaarAdmin;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			bazaarAdmin = (BazaarAdmin) context.lookup("chapter10/"
					+ BazaarAdmin.class.getSimpleName() + "/remote");

			System.out.println("Finding categories by name...");
			List categories;
			categories = bazaarAdmin.findByCategoryName("Dumpster");
			Iterator i = categories.iterator();
			while (i.hasNext()) {
				Category category = (Category) i.next();
				System.out.println("ID: " + category.getCategoryId()
						+ " name: " + category.getCategoryName());
			}

			System.out.println("Finding categories by Viper admin...");
			categories = bazaarAdmin.findByUser("viper");
			Iterator j = categories.iterator();
			while (j.hasNext()) {
				Category category = (Category) j.next();
				System.out.println("ID: " + category.getCategoryId()
						+ " name: " + category.getCategoryName());
			}

			System.out.println("Finding items by date...");
			Date currentDate = new Date();
			List items = bazaarAdmin.getItemByDate(currentDate);
			Iterator k = items.iterator();
			while (k.hasNext()) {
				Item item = (Item) k.next();
				System.out.println("ID: " + item.getItemId()
						+ " initial price: " + item.getInitialPrice());
			}

			System.out.println("Finding items by price...");
			items = bazaarAdmin.getItemByPriceRange(100.0, 145.0);
			Iterator l = items.iterator();
			while (l.hasNext()) {
				Item item = (Item) l.next();
				System.out.println("ID: " + item.getItemId()
						+ " initial price: " + item.getInitialPrice());
			}

			System.out.println("Finding users that have created items...");
			List users = bazaarAdmin.getUserWithItems();
			Iterator m = users.iterator();
			while (m.hasNext()) {
				User user = (User) m.next();
				System.out.println("ID: " + user.getUserId() + " first name: "
						+ user.getFirstName());
			}

			System.out.println("Finding users with items...");
			users = bazaarAdmin.getUserWithNoItems();
			Iterator n = users.iterator();
			while (n.hasNext()) {
				User user = (User) n.next();
				System.out.println("ID: " + user.getUserId() + " first name: "
						+ user.getFirstName());
			}

			System.out.println("Finding users with more than items...");
			users = bazaarAdmin.getUserWithItemsWithNativeQuery();
			Iterator o = users.iterator();
			while (o.hasNext()) {
				User user = (User) o.next();
				System.out.println("ID: " + user.getUserId() + " first name: "
						+ user.getFirstName());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}