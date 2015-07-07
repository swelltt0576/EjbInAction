package com.ejb3inaction.actionbazaar.persistence;

import javax.persistence.PrePersist;

import com.ejb3inaction.actionbazaar.buslogic.ItemException;

public class ItemVerifier {
	public static double MIN_PRICE = new Double(50);

	@PrePersist
	public void newItemVerifier(Item item) {
		System.out.println("Item price: " + item.getInitialPrice());
		if (item.getInitialPrice() < MIN_PRICE) {
			throw new ItemException("Item price is lower than minimum price.");
		}
	}
}