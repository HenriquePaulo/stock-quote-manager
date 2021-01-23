package com.paulo.stockquotemanager.exception;

public class ValidateException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

}