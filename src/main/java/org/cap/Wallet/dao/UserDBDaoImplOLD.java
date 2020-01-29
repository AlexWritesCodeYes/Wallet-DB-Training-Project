package org.cap.Wallet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.Transaction;
import org.cap.Wallet.model.User;

public class UserDBDaoImplOLD implements IUserDao{
	
	private Connection getDBConnection() throws ClassNotFoundException,SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/capgdbservlet"; //capg_wallet";
		
		Connection connection=DriverManager.getConnection(url,"root", "admin");

		return connection;
	}
	
	public UserDBDaoImplOLD() {
		EntityManagerFactory emf=
				Persistence.createEntityManagerFactory("jpawallet");
		
		EntityManager entityManager=
				emf.createEntityManager();
		
		EntityTransaction entityTransaction=
				entityManager.getTransaction();
		
		entityTransaction.begin();
		
		entityTransaction.commit();
		entityManager.close();
		emf.close();
	}


	@Override
	public User registerUser(User user) {
		
		
		try(Connection conn=getDBConnection()){
		String sql="insert into user(firstname,lastname,password,emailid,dob,ssn) "
				+ "values(?,?,?,?,?,?)";
		
	
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, user.getFirstName());
		pst.setString(2, user.getLastName());
		pst.setString(3, user.getPassword());
		pst.setString(4, user.getEmailId());
		pst.setDate(5, user.getDOB());
		pst.setString(6, user.getSSN());
		
		int count=pst.executeUpdate();
		if(count>0)
		{
			String getUserId="select max(userid) from user";
			PreparedStatement pst1=conn.prepareStatement(getUserId);
			ResultSet rs= pst1.executeQuery();
			if(rs.next()) {
				user.setUserId(rs.getInt(1));
			}
		}
		
		String sql2="insert into address(houseNumber,streetName,city,state,zipcode,country,userid) "
				+ "values(?,?,?,?,?,?,?)";
			
		PreparedStatement pst3=conn.prepareStatement(sql2);
		pst3.setString(1, user.getAddress().getHouseNumber());
		pst3.setString(2, user.getAddress().getStreetName());
		pst3.setString(3, user.getAddress().getCity());
		pst3.setString(4, user.getAddress().getState());
		pst3.setString(5, user.getAddress().getZipcode());
		pst3.setString(6, user.getAddress().getCountry());
		pst3.setInt(7, user.getUserId());
		
		int value=pst3.executeUpdate();
		if(value>0) {
			return user;
		}
		//pst3.close();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public User logInUser(String email, String password) {
		User user=null;
		try(
				Connection conn=getDBConnection()){
		String sql="select * from user where emailId=? and password=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, email);
		pst.setString(2, password);

		ResultSet rs=pst.executeQuery();
		
		if(rs.next()) {
			user=new User();
			user.setUserId(rs.getInt("userid"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setPassword(rs.getString("password"));
			user.setDOB(rs.getDate("dateOfBirth").toLocalDate());
			user.setSSN(rs.getString("ssn"));
			user.setEmailId(rs.getString("emailId"));
			
		}
		
		
		//Fetch Address
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Account> getAccounts(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(String accountId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account deposit(Transaction transaction, Account acc, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account withdraw(Transaction transaction, Account acc, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account createAccount(Account acc, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double checkBalance(Account acc, User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Transaction> getTransactionSummary(Account acc, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean transferFunds(Transaction transaction, Account acc1, Account acc2, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account deposit(Account acc, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdraw(Account acc, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean transferFunds(double amount, Account acc1, Account acc2, User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
