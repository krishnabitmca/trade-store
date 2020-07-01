package com.example.trade.store.exception;

public class LowerVersionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LowerVersionException(int i) {

		super(String.format("Lower Version Trade is submitted", i));
	}
}
