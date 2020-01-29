package org.cap.Wallet.util;

import java.time.LocalDate;

import org.cap.Wallet.exceptions.InsufficientBalanceException;
import org.cap.Wallet.exceptions.InvalidDOBException;

public class Utility {
	public static boolean isValidFirstName(String firstName) {
		return firstName.matches("\\b([A-Z]\\w*)\\b");
		
	}
	
	public static boolean isValidLastName(String lastName) {
		return lastName.matches("\\b([A-Z]\\w*)\\b");
	}
	
	public static boolean isValidPassword(String password) {
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
		return password.matches(regex);
	}
	
	public static boolean isValidEmail(String email) {
//		return email.matches("^(.+)@(.+)$");
		String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
		return email.matches(regex);
	}
	
	public static boolean isValidDOB(String dob) throws InvalidDOBException {
		
		// Ensure user enters date in the format "yyyy-MM-dd"
		String regex = "^(19|20)\\d{2}[\\-](0?[1-9]|1[0-2])[\\-](0?[1-9]|[12]\\d|3[01])$";
		if(!dob.matches(regex)) return false;
		
		// Ensure user enters date before current date
		LocalDate userDate = LocalDate.parse(dob);
		LocalDate todayDate = LocalDate.now();
		if(userDate.isAfter(todayDate)) {
			throw new InvalidDOBException();
		}
		
		return true;
	}
	
	public static boolean isValidSSN(String email) {
//		return email.matches("^(.+)@(.+)$");
		String regex = "([0-9]{9})|([0-9]{3}-[0-9]{2}-[0-9]{4})|([0-9]{2}-[0-9]{7})";
		return email.matches(regex);
	}
	
	public static boolean isValidHouseNumber(String houseNumber) {
		
		return true;
	}
	
	public static boolean isValidStreetName(String streetName) {
		
		return true;
	}
	
	public static boolean isValidCity(String city) {
		
		return true;
	}
	
	public static boolean isValidState(String state) {
		String regex = "^((A[AEKLPRSZ])|(C[AOT])|(D[EC])|(F[LM])|(G[AU])|(HI)|"
						+ "(I[ADLN])|(K[SY])|(LA)|(M[ADEHINOST])|(N[CDEHJMVY])|"
						+ "(MP)|(O[HKR])|(P[ARW])|(RI)|(S[CD])|(T[NX])|(UT)|"
						+ "(V[AIT])|(W[AIVY]))$";
		return state.matches(regex);
	}
	
	public static boolean isValidZipcode(String zipcode) {
		String regex = "[0-9]{5}(-[0-9]{4})?";
		return zipcode.matches(regex);
	}
	
	public static boolean isValidCountry(String country) {
		
		return true;
	}
	
	
	public static boolean isValidAccountId(String accountId) {
		return accountId.getClass().equals(String.class);
	}
	
	public static boolean isValidAccountBalance(double balance) throws InsufficientBalanceException {
		if(balance < 1000) {
			throw new InsufficientBalanceException();
		}
		return true;	
	}
	public static boolean isValidAccountType(int accountType) {
		if(accountType == 0 || accountType == 1)
			return true;
		
		return false;
		
	}
	public static boolean isValidDepositAmount(Double amount) {
		return amount.getClass().equals(Double.class) && amount > 0;
	}
	public static boolean isValidWithdrawAmount(Double amount) {
		return amount.getClass().equals(Double.class) && amount > 0;
	}
	public static boolean isValidTransferAmount(Double amount) {
		return amount.getClass().equals(Double.class) && amount > 0;
	}
}
