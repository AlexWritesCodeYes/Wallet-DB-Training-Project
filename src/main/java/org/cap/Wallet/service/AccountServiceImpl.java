package org.cap.Wallet.service;

import java.sql.Date; //util.Date;

import java.time.LocalDate;
import java.util.List;
import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.Transaction;
import org.cap.Wallet.model.User;

public class AccountServiceImpl implements IAccountService {

	private static String accountIdCounter = "00000";
	
	@Override
	public Account getAccount(String accountId, User user) {
		if(accountId == null || user == null) 
			return null;
		else
			return UserServiceImpl.dao.getAccount(accountId, user);
	}
	
	@Override
	public Account createAccount(Account acc, User user) {
		if(acc == null || user == null) {
			return null;
		}
		else {
			// auto generate accountId
			/*String newAccountId =  String.format("%0" + accountIdCounter.length() + "d",
		            Integer.parseInt(accountIdCounter) + 1);
			if(acc.getAccountType() == Account.AccountType.CHECKINGS) {
				acc.setAccountID("CHK_" + newAccountId);
			}
			else if(acc.getAccountType() == Account.AccountType.SAVINGS) {
				acc.setAccountID("SAV_" + newAccountId);
			}
			accountIdCounter = newAccountId; */
			
			// auto fill opening date 
			LocalDate todayDate = LocalDate.now();
			acc.setOpeningDate(todayDate);
			return UserServiceImpl.dao.createAccount(acc, user);
		}
	}

	@Override
	public Account deposit(int type, double amount, String description, Account acc, User user){
		if((type != Transaction.TransactionType.CREDIT.getValue() && type != Transaction.TransactionType.DEBIT.getValue()) || 
				amount <= 0 || description == null || acc == null || user == null) {
			return null;
		}
		else {
			Transaction t1 = new Transaction(Date.valueOf(LocalDate.now()), amount, description, acc, acc);
			t1.setAccountType(type);
			return UserServiceImpl.dao.deposit(acc, t1.getAmount());//, user);
		}
	}

	@Override
	public Account withdraw(int type, double amount, String description, Account acc, User user){
		if((type != Transaction.TransactionType.CREDIT.getValue() && type != Transaction.TransactionType.DEBIT.getValue()) || 
				amount <= 0 || description == null || acc == null || user == null) {
			return null;
		}
		else {
			Transaction t1 = new Transaction(Date.valueOf(LocalDate.now()), amount, description, acc, acc);
			t1.setAccountType(type);
			return UserServiceImpl.dao.withdraw(acc, t1.getAmount());//t1, acc, user);
		}
	}

	@Override
	public double checkBalance(Account acc, User user) {
		if(acc == null && user == null) {
			return -1;
		}
		else {
			return UserServiceImpl.dao.checkBalance(acc, user);
			
		}
	}

	@Override
	public List<Transaction> getTransactionSummary(Account acc, User user) {
		if(acc == null || user == null) {
			return null;
		}
		else {
			return UserServiceImpl.dao.getTransactionSummary(acc, user);
		}
	}

	@Override
	public boolean transferFunds(int type, double amount, String description, Account acc1, Account acc2, User user){
		if((type != Transaction.TransactionType.CREDIT.getValue() && type != Transaction.TransactionType.DEBIT.getValue()) ||
				amount <= 0 || description == null || acc1 == null || acc2 == null || user == null) {
			return false;
		}
		else {
			Transaction t1 = new Transaction(Date.valueOf(LocalDate.now()), amount, description, acc1, acc2);
			t1.setAccountType(type);
			return UserServiceImpl.dao.transferFunds(t1, acc1, acc2, user);
		}
		
	}

	@Override
	public List<Account> getAccounts(User user) {
		if(user == null) {
			return null;
		}
		else {
			return UserServiceImpl.dao.getAccounts(user);
		}
	}

	@Override
	public Account deposit(Account acc, double parseDouble) {
		return UserServiceImpl.dao.deposit(acc, parseDouble);
		//return null;
	}

	@Override
	public Account withdraw(Account acc, double parseDouble) {
		return UserServiceImpl.dao.withdraw(acc, parseDouble);
	}

	@Override
	public boolean transferFunds(double amount, Account acc1, Account acc2, User user) {
		return UserServiceImpl.dao.transferFunds(amount, acc1, acc2, user);
	}
	
}
