package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.BazaarAdmin;
import com.ejb3inaction.actionbazaar.persistence.Category;

public class BazaarAdminClient {
	private static BazaarAdmin bazaarAdmin;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();

			bazaarAdmin = (BazaarAdmin) context.lookup("chapter11/"
					+ BazaarAdmin.class.getSimpleName() + "/remote");

			Category category = bazaarAdmin.createCategory("Test category",
					"idiot2");

			System.out.println("Category created with ID: "
					+ category.getCategoryId());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}