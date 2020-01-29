package org.cap.Wallet.service;

import org.cap.Wallet.dao.IUserDao;
import org.cap.Wallet.dao.UserDBDaoImpl;
import org.cap.Wallet.model.User;

public class UserServiceImpl implements IUserService {
	//public static UserDaoImpl dao = new UserDaoImpl();
	public static IUserDao dao=new UserDBDaoImpl();
	
	
	private static String userIdCounter = "00000";
	
	@Override
	public User registerUser(User user) {
		if(user == null)
			return null;
		else {
			/*
			 * String newUserId = String.format("%0" + userIdCounter.length() + "d",
			 * Integer.parseInt(userIdCounter) + 1); user.setUserId("USER_" + newUserId);
			 * userIdCounter = newUserId;
			 */
			return dao.registerUser(user);
		}
		
	}
	
	@Override
	public User logInUser(String email, String password) {
		if(email == null || password == null)
			return null;
		else
			return dao.logInUser(email, password);
	}

}
