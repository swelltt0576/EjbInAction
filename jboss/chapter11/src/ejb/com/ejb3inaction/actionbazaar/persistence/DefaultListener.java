package com.ejb3inaction.actionbazaar.persistence;

import javax.persistence.PostPersist;

public class DefaultListener {

	@PostPersist
	public void notification(Object entity) {
		System.out.println("Default Listener invoked...");
		// Implement your logic here.
	}
}