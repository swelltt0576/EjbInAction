package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;

public class BillingInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String creditCardType;
	private String accountNumber;
	private String expiryDate;

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}