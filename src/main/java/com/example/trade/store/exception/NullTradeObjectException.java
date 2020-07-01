package com.example.trade.store.exception;

public class NullTradeObjectException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullTradeObjectException() {
		super(String.format("Trade object can't be null"));
	}

}
