package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BidException() {
	}

	public BidException(String msg) {
		super(msg);
	}

	public BidException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BidException(Throwable cause) {
		super(cause);
	}
}