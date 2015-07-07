package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.ItemManager;
import com.ejb3inaction.actionbazaar.persistence.Item;

public class ItemManagerClient {
	private static ItemManager itemManager;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			itemManager = (ItemManager) context.lookup("chapter9/"
					+ ItemManager.class.getSimpleName() + "/remote");

			Item item = itemManager.addItem("Vintage car from Junk Yard",
					"Flat tire, leaky top, rusty engine.", new Double(120.00),
					"junkaddict");

			System.out
					.println("Item created with Item ID: " + item.getItemId());

			item.setItemName("New Title: A collector's dream!!");

			itemManager.updateItem(item);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}