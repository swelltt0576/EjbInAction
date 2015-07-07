package com.ejb3inaction.actionbazaar.buslogic;

public class Notification {
	public static void sendEmailAlert(Long categoryId, String categoryName,
			String firstName, String lastName) {
		String emailText = "Category with ID: " + categoryId + " with name: "
				+ categoryName + " was created by: " + firstName + " "
				+ lastName;
		System.out.println(emailText);
	}
}