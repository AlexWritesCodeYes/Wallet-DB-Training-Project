package org.cap.Wallet.service;

import java.util.List;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.Transaction;
import org.cap.Wallet.model.User;

public interface IAccountService {
	public List<Account> getAccounts(User user);
	public Account getAccount(String accountId, User user);
	public Account createAccount(Account acc, User user);
	public Account deposit(int type, double amount, String description, Account acc, User user);
	public Account withdraw(int type, double amount, String description, Account acc, User user);
	public double checkBalance(Account acc, User user);
	public List<Transaction> getTransactionSummary(Account acc, User user);
	public boolean transferFunds(int type, double amount, String description, Account acc1, Account acc2, User user);
	public Account deposit(Account acc, double parseDouble);
	public Account withdraw(Account acc, double parseDouble);
	public boolean transferFunds(double amount, Account acc1, Account acc2, User user);
}
