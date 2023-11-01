package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.UserEntity;
/**
 * The interface for data access operations related to user accounts.
 *
 * @param <T> The type of object representing user data (e.g., UserEntity).
 */
public interface UsersDataAccessInterface<T> {
	/**
     * Verifies login credentials for a user.
     *
     * @param email The email address of the user attempting to log in.
     * @param password The password provided by the user for authentication.
     * @return {@code true} if the login credentials are valid, {@code false} otherwise.
     */
	public boolean verifyLogin(String email, String password);
	/**
     * Creates a new user account in the database.
     *
     * @param t The object representing user data, typically a UserEntity.
     * @return {@code true} if the account creation is successful, {@code false} otherwise.
     */
	public boolean createAccount(T t);
	/**
     * Checks if a user with the given username or email already exists in the database.
     *
     * @param username The username to be checked for existence.
     * @param email The email address to be checked for existence.
     * @return {@code true} if a user with the provided username or email already exists, {@code false} otherwise.
     */
	public boolean userExists(String username, String email);
}
