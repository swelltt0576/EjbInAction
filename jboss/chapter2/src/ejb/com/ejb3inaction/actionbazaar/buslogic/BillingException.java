package com.ejb3inaction.actionbazaar.buslogic;

public class BillingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BillingException() {
	}

	public BillingException(String msg) {
		super(msg);
	}

	public BillingException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BillingException(Throwable cause) {
		super(cause);
	}
}