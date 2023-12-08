package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UserRepository;

/**
 * Service class handling user data operations such as account creation,
 * existence checks, and login verification. It utilizes the User Entity passed
 * from the Security Business Service to communicate with the User Repository,
 * and thereby the database.
 */
@Service
public class UserDataService implements UserDataAccessInterface<UserEntity> {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	/**
	 * Constructor for UserDataService class.
	 * 
	 * @param usersRepository The repository for managing user data.
	 * @param dataSource      The data source providing the database connection.
	 */
	public UserDataService(UserRepository userRepository, DataSource dataSource) {
		this.userRepository = userRepository;
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
	public int createAccount(UserEntity user) {
		// Check if the user name or email already exists
		if (userExists(user.getUsername(), user.getEmail())) {
			int response;
			if (emailExists(user.getEmail())) {
				response = 3;
				return response;
			} else if (usernameExists(user.getUsername())) {
				response = 2;
				return response;
			} else {
				response = 4;
				return response;
			}
		}

		String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
		int rowsAffected = jdbcTemplateObject.update(sql, user.getUsername(), user.getEmail(), user.getPassword());
		if (rowsAffected > 0)
			return 1;
		else
			return 4;
	}

	/**
	 * Checks if a user with the given user name or email already exists in the
	 * database.
	 * 
	 * @param username The user name to check.
	 * @param email    The email address to check.
	 * @return True if a user with the same user name or email exists, false
	 *         otherwise.
	 */
	@Override
	public boolean userExists(String username, String email) {
		String sql = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ? OR EMAIL = ?";
		int count = jdbcTemplateObject.queryForObject(sql, Integer.class, username, email);
		return count > 0;
	}

	@Override
	public boolean emailExists(String email) {
		String sql = "SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";
		int count = jdbcTemplateObject.queryForObject(sql, Integer.class, email);
		return count > 0;
	}

	@Override
	public boolean usernameExists(String username) {
		String sql = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ?";
		int count = jdbcTemplateObject.queryForObject(sql, Integer.class, username);
		return count > 0;
	}

	/**
	 * Verifies user login credentials based on the provided email and password.
	 * 
	 * @param email    The email address of the user.
	 * @param password The password associated with the user's account.
	 * @return True if a matching user was found, indicating successful login; false
	 *         otherwise.
	 */
	@Override
	public int verifyLogin(String email, String password) {
		int response = userRepository.verifyLogin(email, password);

		// Success
		if (response > 0)
			return 1;

		// Wrong password
		else if (emailExists(email))
			return 3;

		// No email associated with account
		else
			return 2;

	}

	@Override
	public boolean updateUserUsername(String email, String newUsername) {

		String sql = "UPDATE USERS SET USERNAME = ? WHERE EMAIL = ?";
		int rowsAffected = jdbcTemplateObject.update(sql, newUsername, email);

		Long author_id = userRepository.getAuthorIdFromEmail(email);
		String updatePostUsernames = "UPDATE POSTS SET AUTHOR_USERNAME = ? WHERE AUTHOR_ID = ?";
		jdbcTemplateObject.update(updatePostUsernames, newUsername, author_id);

		return rowsAffected > 0;
	}

	@Override
	public boolean updateUserPassword(String email, String newPassword) {
		String sql = "UPDATE USERS SET PASSWORD = ? WHERE EMAIL = ?";
		int rowsAffected = jdbcTemplateObject.update(sql, newPassword, email);
		return rowsAffected > 0;
	}

	@Override
	public List<UserEntity> getFollowers(Long userId) {
		String sql = "SELECT u.* FROM USERS u " + "INNER JOIN FOLLOWS f ON u.ID = f.FOLLOWER_ID "
				+ "WHERE f.FOLLOWED_ID = ?";

		return jdbcTemplateObject.query(sql, new Object[] { userId }, new BeanPropertyRowMapper<>(UserEntity.class));
	}

	@Override
	public List<UserEntity> getFollows(Long userId) {
		String sql = "SELECT u.* FROM USERS u " + "INNER JOIN FOLLOWS f ON u.ID = f.FOLLOWED_ID "
				+ "WHERE f.FOLLOWER_ID = ?";

		return jdbcTemplateObject.query(sql, new Object[] { userId }, new BeanPropertyRowMapper<>(UserEntity.class));
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public UserEntity getUser(Long userId) {
		if (userRepository.getUser(userId) == null)
			return null;

		return userRepository.getUser(userId);
	}

}
