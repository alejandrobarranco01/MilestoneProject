package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.UserDataService;
import com.gcu.data.entity.UserEntity;

/**
 * Service class handling user authentication and account creation operations.
 */
@Service
public class SecurityBusinessService implements SecurityBusinessServiceInterface {
	@Autowired
	UserDataService service;

	/**
	 * Authenticates the user based on the provided email and password.
	 * 
	 * @param email    The email address of the user.
	 * @param password The password associated with the user's account.
	 * @return True if the provided email and password match a user's record,
	 *         indicating successful authentication; false otherwise.
	 */
	public boolean authenticate(String email, String password) {
		return service.verifyLogin(email, password);
	}

	/**
	 * Creates a new user account with the provided email, user name, and password.
	 * It converts the User Model information and converts it to a Post Entity
	 * object for the User Data Service to utilize.
	 * 
	 * @param email    The email address of the user.
	 * @param username The desired user name for the user.
	 * @param password The password for the user's account.
	 * @return True if the account was created successfully, false if a user with
	 *         the same user name or email already exists.
	 */
	public boolean createAccount(String email, String username, String password) {
		UserEntity user = new UserEntity(username, email, password);
		return service.createAccount(user);
	}
}
