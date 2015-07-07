package com.ejb3inaction.actionbazaar.persistence;

import javax.persistence.PostPersist;

import com.ejb3inaction.actionbazaar.buslogic.Notification;

public class CategoryNotifier {

	@PostPersist
	public void newCategoryNotification(Category category) {
		Notification.sendEmailAlert(category.getCategoryId(), category
				.getCategoryName(), category.getUser().getFirstName(), category
				.getUser().getLastName());
	}
}