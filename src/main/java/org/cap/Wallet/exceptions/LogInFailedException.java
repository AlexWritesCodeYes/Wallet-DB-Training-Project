package org.cap.Wallet.exceptions;

public class LogInFailedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogInFailedException() {
		super("Sorry! Too many failed password attempts. Please try again later");
	}

	@Override
	public String getMessage() {
		return "Sorry! Too many failed password attempts. Please try again later"; //super.getMessage();
	}
}
