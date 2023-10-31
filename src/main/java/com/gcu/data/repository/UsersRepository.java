package com.gcu.data.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcu.data.entity.UserEntity;
/**
 * This interface represents a repository for managing user data.
 * It provides methods for verifying user login credentials.
 */
@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	 /**
     * Verifies user login credentials based on the provided email and password.
     * 
     * @param email The email address of the user.
     * @param password The password associated with the user's account.
     * @return An integer representing the count of matching user records found in the database.
     *         If a matching user is found, the count will be greater than 0; otherwise, it will be 0.
     */
	@Query("SELECT COUNT(*) FROM USERS WHERE EMAIL = :email AND PASSWORD = :password")
    public int verifyLogin(String email, String password);
	
}
