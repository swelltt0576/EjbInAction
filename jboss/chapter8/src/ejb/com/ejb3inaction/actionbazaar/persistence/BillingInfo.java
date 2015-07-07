package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
	protected long billingId;
	protected String accountNumber;
	protected String expiryDate;
	protected String secretCode;
	protected Address address;

	@SequenceGenerator(name = "BILLING_SEQUENCE_GENERATOR", sequenceName = "BILLING_SEQUENCE", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILLING_SEQUENCE_GENERATOR")
	@Column(name = "BILLING_ID")
	public Long getBillingId() {
		return this.billingId;
	}

	public void setBillingId(Long billingId) {
		this.billingId = billingId;
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

	@Column(name = "SECRET_CODE")
	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name = "state", column = @Column(name = "STATE_CODE")),
			@AttributeOverride(name = "zip", column = @Column(name = "ZIP_CODE")) })
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}