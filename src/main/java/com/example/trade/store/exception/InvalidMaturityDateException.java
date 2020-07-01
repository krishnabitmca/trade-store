package com.example.trade.store.exception;

public class InvalidMaturityDateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMaturityDateException(int i) {
		super(String.format("Maturity date can't be ealier than current date", i));
	}
}
