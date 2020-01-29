package org.cap.Wallet.exceptions;

public class InvalidDOBException extends Exception {
	
	public InvalidDOBException() {
		super("Sorry, date of birth must be before today's date.");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
