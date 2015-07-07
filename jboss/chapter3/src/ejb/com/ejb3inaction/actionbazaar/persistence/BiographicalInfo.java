package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;

public class BiographicalInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String firstName;
	protected String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}