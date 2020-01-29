package org.cap.Wallet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date; //util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.cap.Wallet.model.Transaction;
import org.cap.Wallet.model.User;

@Entity
public class Account implements Comparable<Account> {

	@Id
	private String accountID; //autogenerated
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	private double balance; // minimum 1000 
	
//	@ManyToMany
//	@JoinTable(name="accounts_to_from",
//	//joinColumns= {@JoinColumn(name="accountid")},
//	inverseJoinColumns = {@JoinColumn(name="transactionId")})
	@OneToMany(mappedBy="account", targetEntity=Transaction.class)
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="userid"),
		@JoinColumn(name="emailid")
	})
	private User user;
	
	//private LocalDate openingDate; // current date
	private Date openingDate; // current date
	private String description;
	private AccountType accountType;	//enum defined below
	
	public static enum AccountType{
		CHECKINGS(0), SAVINGS(1);
		
		private int value;
		private AccountType(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account() {
		this.transactions = new ArrayList<Transaction>();
	}
	
	public boolean addTransaction(Transaction transaction) {
		if(transactions.add(transaction))
			return true;
		else
			return false;
		
	}
	
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	
	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = Date.valueOf(openingDate);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountType getAccountType() {
		return this.accountType;
	}
	
	public void setAccountType(int accountType) {
		if(accountType == AccountType.CHECKINGS.getValue())
			this.accountType = AccountType.CHECKINGS;
		else if(accountType == AccountType.SAVINGS.getValue())
			this.accountType = AccountType.SAVINGS;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + ", transactions="
				/*+ Arrays.toString(transactions) */+ ", openingDate=" + openingDate + ", description=" + description
				+ ", accountType=" + accountType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountID == null) ? 0 : accountID.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((openingDate == null) ? 0 : openingDate.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID == null) {
			if (other.accountID != null)
				return false;
		} else if (!accountID.equals(other.accountID))
			return false;
		if (accountType != other.accountType)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (openingDate == null) {
			if (other.openingDate != null)
				return false;
		} else if (!openingDate.equals(other.openingDate))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	@Override
	public int compareTo(Account acc) {
		if(acc.getAccountID().compareTo(this.getAccountID()) == -1)
			return -1;
		else if(acc.getAccountID().compareTo(this.getAccountID()) == 0)
			return 0;
		else 
			return 1;
	}
	
}