package com.ejb3inaction.actionbazaar.buslogic;

import java.rmi.RemoteException;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.Category;
import com.ejb3inaction.actionbazaar.persistence.Item;

@Remote
public interface BazaarAdmin {
	Category mergeCategory(Category category) throws RemoteException;

	Category refreshCategory(Category category);

	void removeCategory(Category category);

	Category createCategory(String name, String userId);

	Item createItem(String name, String userId, Long categoryId,
			Double initialPrice) throws RemoteException;
}