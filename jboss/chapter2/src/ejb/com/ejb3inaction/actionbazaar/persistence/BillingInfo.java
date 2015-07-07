package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillingInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String creditCardType;
	private String accountNumber;
	private String expiryDate;

	public BillingInfo() {
	}

	public BillingInfo(String creditCardType, String accountNumber,
			String expiryDate) {
		this.creditCardType = creditCardType;
		this.accountNumber = accountNumber;
		this.expiryDate = expiryDate;
	}

	@Column(name = "CREDIT_CARD_TYPE")
	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	@Column(name = "ACCOUNT_NUMBER")
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "EXPIRY_DATE")
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}