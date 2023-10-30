package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.UserEntity;

public interface UsersDataAccessInterface<T> {
	/**
	 * Retrieves a list of all entities from the data source.
	 *
	 * @return A list containing all entities.
	 */
	public List<UserEntity> findAll();

	/**
	 * Retrieves a specific entity from the data source based on its unique
	 * identifier.
	 *
	 * @param id The unique identifier of the entity to be retrieved.
	 * @return The entity with the specified ID, or null if not found.
	 */
	public T findById(Long id);

	/**
	 * Creates a new entity in the data source.
	 *
	 * @param entity The entity to be created.
	 * @return True if the creation was successful, false otherwise.
	 */
	public boolean create(T user);

	/**
	 * Updates an existing entity in the data source.
	 *
	 * @param entity The updated entity.
	 * @return True if the update was successful, false otherwise.
	 */
	public boolean update(T user);

	/**
	 * Deletes an entity from the data source.
	 *
	 * @param entity The entity to be deleted.
	 * @return True if the deletion was successful, false otherwise.
	 */
	public boolean delete(T user);

	/**
	 * Verifies if an entity with the given email already exists in the data source.
	 *
	 * @param email The email to be checked.
	 * @return True if an entity with the email exists, false otherwise.
	 */
	public boolean existsByEmail(String email);

	/**
	 * Verifies if an entity with the given username already exists in the data
	 * source.
	 *
	 * @param username The username to be checked.
	 * @return True if an entity with the username exists, false otherwise.
	 */
	public boolean existsByUsername(String username);
}
