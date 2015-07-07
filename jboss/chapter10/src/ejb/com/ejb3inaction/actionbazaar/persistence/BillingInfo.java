package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BILLING_DETAILS")
public class BillingInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Long id;
	protected String accountType;
	protected String accountNumber;
	protected String expiryDate;

	public BillingInfo() {
	}

	public BillingInfo(String accountType, String accountNumber,
			String expiryDate) {
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.expiryDate = expiryDate;
		return;
	}

	@SequenceGenerator(name = "BILLING_SEQUENCE_GENERATOR", sequenceName = "BILLING_SEQUENCE", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILLING_SEQUENCE_GENERATOR")
	@Column(name = "BILLING_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT_TYPE")
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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