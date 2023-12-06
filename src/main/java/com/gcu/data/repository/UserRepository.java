package com.gcu.data.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcu.data.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	@Query("SELECT COUNT(*) FROM USERS WHERE EMAIL = :email AND PASSWORD = :password")
	public int verifyLogin(String email, String password);

	@Query("SELECT ID FROM USERS WHERE EMAIL = :email")
	public Long getAuthorIdFromEmail(String email);
	
	@Query("SELECT EMAIL FROM USERS WHERE ID = :id")
	public String getAuthorEmailFromId(Long id);
	
	@Query("SELECT USERNAME FROM USERS WHERE ID = :id")
	public String getAuthorUsernameFromId(Long id);

	@Query("SELECT USERNAME FROM USERS WHERE EMAIL = :email")
	public String getAuthorUsernameFromEmail(String email);

	@Query("SELECT ID FROM USERS WHERE LOWER(USERNAME) LIKE LOWER(CONCAT('%', :query, '%'))")
	public Long findUsernamesContaining(String query);
}
