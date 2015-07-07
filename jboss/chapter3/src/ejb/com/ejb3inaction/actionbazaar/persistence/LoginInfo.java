package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;

public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}