package org.cap.Wallet.dao;

import java.util.ArrayList;
import java.util.List;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.Transaction;
import org.cap.Wallet.model.User;

public interface IUserDao {
	
	public User registerUser(User user);
	public User logInUser(String email, String password);
	public List<Account> getAccounts(User user);
	public Account getAccount(String accountId, User user);
	public Account deposit(Account acc, double amount);
	public Account withdraw(Account acc, double amount);
	public Account createAccount(Account acc, User user);
	public double checkBalance(Account acc, User user);
	public List<Transaction> getTransactionSummary(Account acc, User user);
	public boolean transferFunds(Transaction transaction, Account acc1, Account acc2, User user);
	public boolean transferFunds(double amount, Account acc1, Account acc2, User user);
	

}
