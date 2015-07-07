package com.ejb3inaction.actionbazaar.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ejb3inaction.actionbazaar.buslogic.BidderAccountCreator;
import com.ejb3inaction.actionbazaar.persistence.BillingInfo;
import com.ejb3inaction.actionbazaar.persistence.BiographicalInfo;
import com.ejb3inaction.actionbazaar.persistence.LoginInfo;

public class AccountCreatorClient {
	private static BidderAccountCreator accountCreator;

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();

			accountCreator = (BidderAccountCreator) context.lookup("chapter3/"
					+ BidderAccountCreator.class.getSimpleName() + "/remote");

			LoginInfo login = new LoginInfo();
			login.setUsername("dpanda");
			login.setPassword("welcome");

			accountCreator.addLoginInfo(login);

			BiographicalInfo bio = new BiographicalInfo();
			bio.setFirstName("Debu");
			bio.setLastName("Panda");

			accountCreator.addBiographicalInfo(bio);

			BillingInfo billing = new BillingInfo();
			billing.setCreditCardType("VISA");
			billing.setAccountNumber("0123456789");
			billing.setExpiryDate("1/1/2010");

			accountCreator.addBillingInfo(billing);

			// Create account
			accountCreator.createAccount();

			System.out.println("Account created for username: "
					+ login.getUsername());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}