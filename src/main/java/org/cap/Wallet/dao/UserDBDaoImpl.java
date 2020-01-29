package org.cap.Wallet.dao;
import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.cap.Wallet.model.Account;
import org.cap.Wallet.model.Address;
import org.cap.Wallet.model.Transaction;
import org.cap.Wallet.model.User;
import org.hibernate.sql.Update;

public class UserDBDaoImpl implements IUserDao{
	
	public static EntityManagerFactory emf=
			Persistence.createEntityManagerFactory("jpawallet");
	public static EntityManager entityManager=
			emf.createEntityManager();
	
	public static EntityTransaction entityTransaction=
			entityManager.getTransaction();

	public UserDBDaoImpl() {
		//entityTransaction.begin();
	}
	
	@Override
	public User registerUser(User user) {
		
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		entityTransaction.begin();
		
		Address address = user.getAddress();
		entityManager.persist(address);
		entityManager.persist(user);
		
		entityTransaction.commit();
		//entityManager.close();
		//emf.close();
		
		return user;
	}

	@Override
	public User logInUser(String email, String password) {
		User user=null;
		
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		//entityTransaction.begin();
		
		String qry = "FROM User WHERE emailId='"+email+"'";
		Query query = entityManager.createQuery(qry);
		List<User> usrs = query.getResultList();
		if(usrs.size() == 0) {
			System.out.println("no such user found");
			return null;
		}
		else {
			System.out.println("found one user");
		}
		user = usrs.get(0);
		//user = entityManager.find(User.class, email);
		if(!user.getPassword().equals(password)) {
			System.out.println(user.getPassword());
			System.out.println(password);
			System.out.println("thelogin failed");
			//entityManager.close();
			return null;
		}
		else {
			System.out.println("success!");
			//entityManager.close();
			return user;
		}
		
		//entityTransaction.commit();
		//entityManager.close();
		
	}

	@Override
	public List<Account> getAccounts(User user) {
		
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		//entityTransaction.begin();
		String qry = "FROM Account WHERE userid="+user.getUserId()+"";
		TypedQuery<Account> query =
				entityManager.createQuery(qry, Account.class);
		List<Account> accs = query.getResultList();
		for(Account acc:accs) {
			user.addAccount(acc);
		}
		
		//entityTransaction.commit();
		//entityManager.close();
		return accs;
	}

	@Override
	public Account getAccount(String accountId, User user) {
//		EntityManager entityManager=
//				emf.createEntityManager();
		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		//entityTransaction.begin();
		String qry = "FROM Account WHERE accountID="+accountId+
			"AND userid="+user.getUserId()+"";	
		Query query = entityManager.createQuery(qry);
		List<Account> accl = query.getResultList();
		Account acc = (Account) accl.get(0);
		return acc;
		//return null;
	}
	
	public Transaction newDeposit(double amount, Account account) {
		Transaction transaction = new Transaction();
		
		
		String qry = "FROM Account WHERE accountID='"+account.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Account> accl = query.getResultList();
		account = (Account) accl.get(0);
		
		Date transdate = Date.valueOf(LocalDate.now());
		String description = "deposit of "+amount;
		int type = account.getAccountType().getValue();
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		Account nullAcc = new Account();
		nullAcc.setAccountID("null");
		entityTransaction.begin();	
		transaction.setAmount(amount);
		transaction.setAccount(account);
		transaction.setToAccount(account);
		transaction.setFromAccount(nullAcc);
		transaction.setDate(transdate);
		transaction.setAccountType(type);
		transaction.setDescription(description);
		
		entityManager.persist(transaction);
		
		entityTransaction.commit();
		//entityManager.close();
		return transaction;
	}
	
	public Transaction newWithdrawl(double amount, Account account) {
		Transaction transaction = new Transaction();
		
		String qry = "FROM Account WHERE accountID='"+account.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Account> accl = query.getResultList();
		account = (Account) accl.get(0);
		
		Date transdate = Date.valueOf(LocalDate.now());
		String description = "withdraw of "+amount;
		int type = account.getAccountType().getValue();
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
//		
		Account nullAcc = new Account();
		nullAcc.setAccountID("null");
		entityTransaction.begin();	
		transaction.setAmount(amount);
		transaction.setAccount(account);
		transaction.setToAccount(nullAcc);
		transaction.setFromAccount(account);
		transaction.setDate(transdate);
		transaction.setAccountType(type);
		transaction.setDescription(description);
		
		entityManager.persist(transaction);
		
		entityTransaction.commit();
		//entityManager.close();
		return transaction;
	}
	
	public Transaction newTransaction(double amount, Account acc1, Account acc2) {
		Transaction transaction = new Transaction();
		
		String qry = "FROM Account WHERE accountID='"+acc1.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Account> accs = query.getResultList();
		acc1 = (Account) accs.get(0);
		String qry2 = "FROM Account WHERE accountID='"+acc2.getAccountID()+"'";	
		Query query2 = entityManager.createQuery(qry2);
		List<Account> accs2 = query2.getResultList();
		acc2 = (Account) accs2.get(0);
		
		Date transdate = Date.valueOf(LocalDate.now());
		String description = "transfer of "+amount;
		int type1 = acc1.getAccountType().getValue();
		int type2 = acc2.getAccountType().getValue();
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		entityTransaction.begin(); 
		transaction.setAmount(amount);
		transaction.setAccount(acc1);
		transaction.setFromAccount(acc1);
		transaction.setToAccount(acc2);
		transaction.setDate(transdate);
		transaction.setAccountType(type1);
//		transaction.setAccountType(type2);
		transaction.setDescription(description);
		
		entityManager.persist(transaction);
		
		entityTransaction.commit();
		//entityManager.close();
		return transaction;
	}

	@Override
	public Account deposit(Account acc, double amount) {
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
//		
		//entityTransaction.begin();
		String qry = "FROM Account WHERE accountID='"+acc.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Account> accl = query.getResultList();
		acc = (Account) accl.get(0);
		
		double newbalance = acc.getBalance() + amount;
		acc.setBalance(newbalance);
		acc.addTransaction(newDeposit(amount, acc));
		
		entityTransaction.begin();
		String qry2 = "UPDATE Account SET balance="+newbalance+" WHERE accountID='"+acc.getAccountID()+"'";
		Query query2 = entityManager.createQuery(qry2);
		query2.executeUpdate();
		//entityManager.refresh(entity);//.persist(acc);
		
		entityTransaction.commit();
		//entityManager.close();
		
		
		return acc;
	}

	@Override
	public Account withdraw(Account acc, double amount) {
		String qry = "FROM Account WHERE accountID='"+acc.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Account> accl = query.getResultList();
		acc = (Account) accl.get(0);
		
		if(acc.getBalance() < amount) {
			System.out.println("Sorry! Insufficient balance.");
			return null;
		}
		else {
//			EntityManager entityManager=
//					emf.createEntityManager();
//			
//			EntityTransaction entityTransaction=
//					entityManager.getTransaction();
			
			//entityTransaction.begin();
			
			double newbalance = acc.getBalance() - amount;
			acc.setBalance(newbalance);
			acc.addTransaction(newWithdrawl(amount, acc));
			
			entityTransaction.begin();
			String qry2 = "UPDATE Account SET balance="+newbalance+" WHERE accountID='"+acc.getAccountID()+"'";
			Query query2 = entityManager.createQuery(qry2);
			//entityManager.refresh(acc);//.persist(acc);
			query2.executeUpdate();
			entityTransaction.commit();
			//entityManager.close();
			
			return acc;
		}
	}

	@Override
	public Account createAccount(Account acc, User user) {
		
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		
		
		String prelimSQL = "FROM Account";	//getting a unique account id
		String newid = "ac_";
		Query query = entityManager.createQuery(prelimSQL);
		List<Account> acclist = query.getResultList();
		List<String> idlist = new ArrayList<String>();
		int count = 2;
		for(Account ac:acclist) {
			count++;
			idlist.add(ac.getAccountID());
		}
		newid = newid+count;
		
		entityTransaction.begin();
		acc.setAccountID(newid);
		
		user.addAccount(acc);
		acc.setOpeningDate(Date.valueOf(LocalDate.now()));
		acc.setUser(user);
		System.out.println("persisting");
		entityManager.persist(acc);
		entityTransaction.commit();
		//entityManager.refresh(user);
		return acc;
	}

	@Override
	public double checkBalance(Account acc, User user) {
		
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		//entityTransaction.begin();
		String sql= "FROM Account WHERE "
				+ "accountID='"+acc.getAccountID()+"'";// AND "
		//				+ "userId="+user.getUserId()+"";
		Query query = entityManager.createQuery(sql);
		List<Account> results = query.getResultList();
		Account accget = results.get(0);
		double result = accget.getBalance();
		
		return result;
	}

	@Override
	public List<Transaction> getTransactionSummary(Account acc, User user) {
//		EntityManager entityManager=
//				emf.createEntityManager();
//		
//		EntityTransaction entityTransaction=
//				entityManager.getTransaction();
		
		//entityTransaction.begin();
		String qry = "FROM Transaction WHERE fromAccount='"+acc.getAccountID()+"' OR "
				+ "toAccount='"+acc.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Transaction> translist = query.getResultList();
		//return translist;
		List<Transaction> tlist = new ArrayList<Transaction>();
		ListIterator<Transaction> iter = translist.listIterator();
		while(iter.hasNext()) {
			tlist.add((Transaction) iter.next());
		}
		return tlist;
//		return null;
	}

	@Override
	public boolean transferFunds(Transaction transaction, Account acc1, Account acc2, User user) {
		
		String qry = "FROM Account WHERE accountID='"+acc1.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Account> accs = query.getResultList();
		acc1 = (Account) accs.get(0);
		String qry2 = "FROM Account WHERE accountID='"+acc2.getAccountID()+"'";	
		Query query2 = entityManager.createQuery(qry2);
		List<Account> accs2 = query2.getResultList();
		acc2 = (Account) accs2.get(0);
		
		if(this.getAccount(acc1.getAccountID(), user) == null)
			return false;
		
		if(this.getAccount(acc2.getAccountID(), user) == null)
			return false;
		
		if(this.checkBalance(acc1, user) < transaction.getAmount()) {
			System.out.println("insufficientBalance");
			return false;
		}
		else {
			String sql = "UPDATE Account SET balance = balance - "
					+transaction.getAmount()+" WHERE accountId = '"+acc1.getAccountID()+ "'"
							+ " AND userId = "+user.getUserId()+"";
			String sql2 = "UPDATE Account SET balance = balance + "
					+transaction.getAmount()+" WHERE accountId = '"+acc2.getAccountID()+ "'"
							+ " AND userId = "+user.getUserId()+"";
			
//			EntityManager entityManager=
//					emf.createEntityManager();
//			
//			EntityTransaction entityTransaction=
//					entityManager.getTransaction();
			
			entityTransaction.begin();
			Query query3 = entityManager.createQuery(sql);
			query3.executeUpdate();
			Query query4 = entityManager.createQuery(sql2);
			query4.executeUpdate();
			
			//Transaction transaction = newTransaction(amount, acc1, acc2);
			entityManager.persist(transaction);
			
			entityTransaction.commit();
			//entityManager.close();
			return true;
		}
		//return false;
	}
	
	@Override
	public boolean transferFunds(double amount, Account acc1, Account acc2, User user) {
		
		String qry = "FROM Account WHERE accountID='"+acc1.getAccountID()+"' "
				+ "OR accountID='"+acc2.getAccountID()+"'";	
		Query query = entityManager.createQuery(qry);
		List<Account> accs = query.getResultList();
		if(accs.size() < 2) {
			return false;
		}
		acc1 = (Account) accs.get(0);
		//String qry2 = "FROM Account WHERE accountID='"+acc2.getAccountID()+"'";	
		//Query query2 = entityManager.createQuery(qry2);
		//List<Account> accs2 = query2.getResultList();
		acc2 = (Account) accs.get(1);
		
//		if(this.getAccount(acc1.getAccountID(), user) == null)
//			return false;
//		
//		if(this.getAccount(acc2.getAccountID(),user) == null)
//			return false;
//		
		if(this.checkBalance(acc1, user) < amount) {
			System.out.println("insufficientBalance");
			return false;
		}
		else {
			String sql = "UPDATE Account SET balance = balance - "
					+amount+" WHERE accountId = '"+acc1.getAccountID()+ "'";
							//+ " AND userId = "+user.getUserId()+"";
			String sql2 = "UPDATE Account SET balance = balance + "
					+amount+" WHERE accountId = '"+acc2.getAccountID()+ "'";
							//+ " AND userId = "+user.getUserId()+"";
			
//			EntityManager entityManager=
//					emf.createEntityManager();
//			
//			EntityTransaction entityTransaction=
//					entityManager.getTransaction();
			
			Transaction transaction = newTransaction(amount, acc1, acc2);
			
			entityTransaction.begin();
			Query query3 = entityManager.createQuery(sql);
			query3.executeUpdate();
			Query query4 = entityManager.createQuery(sql2);
			query4.executeUpdate();
			
			//entityTransaction.begin();
			//entityManager.persist(transaction);
			
			entityTransaction.commit();
			//entityManager.close();
			return true;
		}
		//return false;
	}

}
