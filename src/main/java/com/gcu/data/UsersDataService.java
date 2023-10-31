package com.gcu.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UsersRepository;

/**
 * Service class handling user data operations such as account creation,
 * existence checks, and login verification.
 */
@Service
public class UsersDataService implements UsersDataAccessInterface<UserEntity> {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	/**
	 * Constructor for UsersDataService class.
	 * 
	 * @param usersRepository The repository for managing user data.
	 * @param dataSource      The data source providing the database connection.
	 */
	public UsersDataService(UsersRepository usersRepository, DataSource dataSource) {
		this.usersRepository = usersRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * Creates a new user account in the database.
	 * 
	 * @param user The user entity containing user name, email, and password.
	 * @return True if the account was created successfully, false if a user with
	 *         the same user name or email already exists.
	 */
	@Override
	public boolean createAccount(UserEntity user) {
		// Check if the user name or email already exists
		if (userExists(user.getUsername(), user.getEmail())) {
			// User with the same user name or email already exists
			return false;
		}

		String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
		int rowsAffected = jdbcTemplateObject.update(sql, user.getUsername(), user.getEmail(), user.getPassword());
		return rowsAffected > 0;
	}
	
	/**
     * Checks if a user with the given user name or email already exists in the database.
     * 
     * @param username The user name to check.
     * @param email The email address to check.
     * @return True if a user with the same user name or email exists, false otherwise.
     */
	@Override
	public boolean userExists(String username, String email) {
		String sql = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ? OR EMAIL = ?";
		int count = jdbcTemplateObject.queryForObject(sql, Integer.class, username, email);
		return count > 0;
	}
	
	 /**
     * Verifies user login credentials based on the provided email and password.
     * 
     * @param email The email address of the user.
     * @param password The password associated with the user's account.
     * @return True if a matching user was found, indicating successful login; false otherwise.
     */
	@Override
	public boolean verifyLogin(String email, String password) {
		// If count is greater than 0, it means a matching user was found
		return usersRepository.verifyLogin(email, password) > 0;
	}

}
