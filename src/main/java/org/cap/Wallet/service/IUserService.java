package org.cap.Wallet.service;

import org.cap.Wallet.model.User;

public interface IUserService {

	public User registerUser(User user);
	public User logInUser(String email, String password);

}
