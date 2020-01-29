package org.cap.Wallet.exceptions;

public class SignInFailedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignInFailedException() {
		super("Sorry! Too many failed sign-ins. Please try again later");
	}

	@Override
	public String getMessage() {
		return "Sorry! Too many failed sign-ins. Please try again later"; //super.getMessage();
	}
}
