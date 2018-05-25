package com.pji.alexa.exceptions;

/**
 * This is the custome exception class for throwing exception for Store services.
 * @author anubh
 *
 */
public class StoreException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StoreException(String message) {
        super(message);
    }
}
