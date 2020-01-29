package org.cap.Wallet.exceptions;

public class InsufficientBalanceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
		super("Sorry! Minimum sign-up balance is $1000");
	}

	@Override
	public String getMessage() {
		return "Sorry! Minimum sign-up balance is $1000"; //super.getMessage();
	}
}
