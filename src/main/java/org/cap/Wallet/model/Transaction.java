package org.cap.Wallet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//import org.cap.wallet.model.Account;

@Entity
public class Transaction {	
	//private LocalDate date;
	@Id
	@Column(name="transactionId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;
	private Date date;
	private double amount;
	private String description;
	
	private TransactionType transactionType;
	public enum TransactionType{
		CREDIT(0), DEBIT(1);
		private int value;
		private TransactionType(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	//@Column(name = "fromAccount")
	private String fromAccount;
	//@Column(name = "toAccount")
	private String toAccount;
	
//	@ManyToMany
//	@JoinTable(name="accounts_to_from",
//	joinColumns= {@JoinColumn(name="transactionId", referencedColumnName="transactionId")},
//			//@JoinColumn(name="fromaccount", referencedColumnName="fromAccount")},
//	inverseJoinColumns = {@JoinColumn(name="toAccount", referencedColumnName="accountID"), 
//			@JoinColumn(name="fromAccount", referencedColumnName="accountID")})
	@ManyToOne
	@JoinColumn(name="accountid")
	private Account account;
	//private List<Account> accounts = new ArrayList<Account>();
	
	//private Account toAccount;
	
	//@ManyToOne
	//@JoinColumn(name="accountId")
	//private Account fromAccount;
	//private Account account;
	
	
//	public Account getAccount() {
//		return fromAccount;
//	}
//	
//	public void setAccount(Account account) {
//		this.fromAccount = account;
//	}
	
	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public int getTransactionId() {
		return transactionId;
	}
	
	
	public Transaction() {};
	
	public Transaction(Date date, double amount, String description, Account fromAccount, Account toAccount) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.fromAccount = fromAccount.getAccountID();
		this.toAccount = toAccount.getAccountID();
		this.account = fromAccount;
		//this.accounts.add(fromAccount);
		//this.accounts.add(toAccount);
	}
	
	public Transaction(Date date, double amount, String description, String transactionType,
			Account account) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.transactionType = TransactionType.valueOf(transactionType);
		this.toAccount = account.getAccountID();
		this.account=account;
		//this.accounts.add(account);
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount.getAccountID();
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount.getAccountID();
	}
	public TransactionType getTransactionType() {
		return this.transactionType;
	}
	
	public void setAccountType(int accountType) {
		if(accountType == TransactionType.CREDIT.getValue())
			this.transactionType = TransactionType.CREDIT;
		else if(accountType == TransactionType.DEBIT.getValue())
			this.transactionType = TransactionType.DEBIT;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fromAccount == null) ? 0 : fromAccount.hashCode());
		result = prime * result + ((toAccount == null) ? 0 : toAccount.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fromAccount == null) {
			if (other.fromAccount != null)
				return false;
		} else if (!fromAccount.equals(other.fromAccount))
			return false;
		if (toAccount == null) {
			if (other.toAccount != null)
				return false;
		} else if (!toAccount.equals(other.toAccount))
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [date=" + date + ", amount=" + amount + ", description=" + description + ", fromAccount="
				+ fromAccount + ", toAccount=" + toAccount + ", transactionType=" + transactionType + "]";
	}
	
}
