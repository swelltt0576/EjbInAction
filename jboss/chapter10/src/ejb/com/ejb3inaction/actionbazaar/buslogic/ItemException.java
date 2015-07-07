package com.ejb3inaction.actionbazaar.buslogic;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ItemException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ItemException() {
	}

	public ItemException(String msg) {
		super(msg);
	}

	public ItemException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ItemException(Throwable cause) {
		super(cause);
	}
}