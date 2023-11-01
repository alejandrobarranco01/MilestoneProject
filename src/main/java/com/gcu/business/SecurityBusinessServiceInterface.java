package com.gcu.business;

/**
 * The interface for the business service handling security-related operations.
 */
public interface SecurityBusinessServiceInterface {
	/**
	 * Authenticates a user based on the provided username and password.
	 *
	 * @param username The username of the user attempting to authenticate.
	 * @param password The password of the user attempting to authenticate.
	 * @return {@code true} if the authentication is successful, {@code false}
	 *         otherwise.
	 */
	public boolean authenticate(String username, String password);

	/**
	 * Creates a new user account with the provided email, username, and password.
	 *
	 * @param email    The email address for the new user account.
	 * @param username The desired username for the new user account.
	 * @param password The password for the new user account.
	 * @return {@code true} if the account creation is successful, {@code false}
	 *         otherwise.
	 */
	public boolean createAccount(String email, String username, String password);
}
