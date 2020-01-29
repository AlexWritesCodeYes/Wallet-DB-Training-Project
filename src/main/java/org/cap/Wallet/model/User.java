package org.cap.Wallet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.Serializable;
import java.sql.Date; //util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.cap.Wallet.model.Account;

@Entity
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Id
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
	
	@OneToMany(mappedBy = "user",targetEntity = Account.class)
	private List<Account> accounts = new ArrayList<Account>(); 
	
	private Date dob;
	private String ssn;
	
	@OneToOne
	@JoinColumn(name="addressid")
	private Address address;
	
	public User() {
		//this.accounts = new ArrayList<Account>();
	};
	
	public User(String firstName, String lastName, Address address, String password, String emailId,
			int userId, LocalDate dob, String ssn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.password = password;
		this.emailId = emailId;
		this.userId = userId;
		this.dob = Date.valueOf(dob);
		this.ssn = ssn;
		this.accounts = new ArrayList<Account>();
	}
	
	/**
	 * @param newAccount
	 * 
	 * Add new account to User's accounts list
	 * 
	 * @return accounts.add(newAccount);
	 */
	public boolean addAccount(Account newAccount) {
		if(this.accounts.add(newAccount))
			return true;
		else 
			return false;
	}
	
	/**
	 * Print out all user accounts
	 */
	public void displayAccounts() {
		System.out.println("\n============================================");
		System.out.println("| User Accounts                            |");
		System.out.println("============================================");
		for(Account acc: accounts) {
			if(acc != null)
				System.out.println(acc);
		}
		System.out.println("============================================\n");
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDOB() {
		return dob;
	}
	public void setDOB(LocalDate dob) {
		this.dob = Date.valueOf(dob);
	}
	public String getSSN() {
		return ssn;
	}
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", password=" + password + ", accounts=" + accounts + ", emailId=" + emailId + ", dob=" + dob
				+ ", ssn=" + ssn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		result = prime * result + userId;
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
		User other = (User) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	
}
