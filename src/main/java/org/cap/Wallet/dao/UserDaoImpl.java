package org.cap.Wallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.cap.Wallet.model.*;

public abstract class UserDaoImpl implements IUserDao{

	List<User> users = new ArrayList<User>();
	@Override
	public User registerUser(User user) {
		if(users.add(user)) {
			return user;
		}
		else {
			return null;
		}
	}

	@Override
	public User logInUser(String email, String password) {
		User user = null;
		for(User u: users) {
			if(u == null) continue;
			else if(u.getEmailId().equals(email) && u.getPassword().equals(password)) {			
				user = u;
				return u;
			}
		}
		return user;
	}
	
	//@Override
	public Account getAccount(String accountId, User user) {
		ListIterator<User> iter = users.listIterator();
		while(iter.hasNext()) {
			User u = iter.next();
			if(u == null) continue;
			else if(u.equals(user)) {			
				for(Account acc: user.getAccounts()) {
					if(acc.getAccountID().equals(accountId)) {
						return acc;
					}
						
				}
				return null;
			}
		}
		return null;
	}
	
	@Override
	public List<Account> getAccounts(User user) {
		int userIndex = users.indexOf(user);
		if(userIndex == -1) {
			return null;
		}
		else {
			return users.get(userIndex).getAccounts();
		}
	}
	
	@Override
	public Account createAccount(Account acc, User user) {
		int userIndex = users.indexOf(user);
		if(acc == null || userIndex == -1 ) {
			return null;
		}
		else {
			user.addAccount(acc);
			users.set(userIndex, user);
			return acc;
		}
		
	}
	
//	@Override
	/*
	 * public Account deposit(Transaction transaction, Account acc, User user) { int
	 * userIndex = users.indexOf(user); int accIndex =
	 * user.getAccounts().indexOf(acc); if(transaction == null || accIndex == -1 ||
	 * userIndex == -1 ) { return null; } else { // update amount Account newAcc =
	 * users.get(userIndex).getAccounts().get(accIndex);
	 * newAcc.setBalance(newAcc.getBalance() + transaction.getAmount());
	 * 
	 * // Add transaction objects to respective account
	 * newAcc.addTransaction(transaction);
	 * 
	 * return newAcc; }
	 * 
	 * }
	 */
	
	
	public Account withdraw(Transaction transaction, Account acc, User user) {
		int userIndex = users.indexOf(user);
		int accIndex = user.getAccounts().indexOf(acc);
		if(transaction == null || accIndex == -1 || userIndex == -1 ) {
			return null;
		}
		else if(users.get(userIndex).getAccounts().get(accIndex).getBalance() < transaction.getAmount()) {
			return null;
		}
		else {
			// update amount
			Account newAcc = users.get(userIndex).getAccounts().get(accIndex);
			transaction.setAmount(-transaction.getAmount());
			newAcc.setBalance(newAcc.getBalance() + transaction.getAmount());
			
			// Add transaction objects to respective account
			newAcc.addTransaction(transaction);
			
			return newAcc;
		}
		
	}

	@Override
	public double checkBalance(Account acc, User user) {
		int userIndex = users.indexOf(user);
		int accIndex = user.getAccounts().indexOf(acc);
		if(accIndex == -1 || userIndex == -1 ) {
			return -1;
		}
		else {
			Account newAcc = users.get(userIndex).getAccounts().get(accIndex);
			return newAcc.getBalance();
		}
		
	}

	@Override
	public List<Transaction> getTransactionSummary(Account acc, User user) {
		int userIndex = users.indexOf(user);
		int accIndex = user.getAccounts().indexOf(acc);
		if(accIndex == -1 || userIndex == -1 ) {
			return null;
		}
		else {
			return users.get(userIndex).getAccounts().get(accIndex).getTransactions();
		}
	}

	@Override
	public boolean transferFunds(Transaction transaction, Account acc1, Account acc2, User user ) {
		
		int fromAccIndex = user.getAccounts().indexOf(acc1);
		int toAccIndex = user.getAccounts().indexOf(acc2);
		int userIndex = users.indexOf(user);
		if( transaction == null || fromAccIndex == -1 || toAccIndex == -1 ||userIndex == -1) {
			return false;
		}
		else if(users.get(userIndex).getAccounts().get(fromAccIndex).getBalance() < transaction.getAmount()) {
			return false;
		}
		else {
			// Get account objects
			// Update account objects
			// Add transaction objects to respective accounts
			Account newToAcc = users.get(userIndex).getAccounts().get(toAccIndex);
			newToAcc.setBalance(newToAcc.getBalance() + transaction.getAmount());
			newToAcc.addTransaction(transaction);
			
			double amount = -transaction.getAmount();
			transaction.setAmount(amount);
			Account newFromAcc = users.get(userIndex).getAccounts().get(fromAccIndex);
			newFromAcc.setBalance(newFromAcc.getBalance() + transaction.getAmount());
			newFromAcc.addTransaction(transaction);
			return true;
		}
	}

	@Override
	public Account deposit(Account acc, double amount) {
		// TODO Auto-generated method stub
		return null;
	}
}
