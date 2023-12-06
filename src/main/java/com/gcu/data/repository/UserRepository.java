package com.gcu.data.repository;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
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

	@Query("SELECT COUNT(*) > 0 FROM FOLLOWS WHERE FOLLOWER_ID = :followerId AND FOLLOWED_ID = :followedId")
	public boolean isFollowed(Long followerId, Long followedId);

	@Transactional
	@Modifying
	@Query("INSERT INTO FOLLOWS (FOLLOWER_ID, FOLLOWED_ID) VALUES (:followerId, :followedId)")
	void follow(Long followerId, Long followedId);

	@Transactional
	@Modifying
	@Query("DELETE FROM FOLLOWS WHERE FOLLOWER_ID = :followerId AND FOLLOWED_ID = :followedId")
	void unfollow(Long followerId, Long followedId);

	@Query("SELECT PASSWORD FROM USERS WHERE EMAIL = :email")
	public String getUserPasswordFromEmail(String email);

	@Transactional
	@Modifying
	@Query("DELETE FROM USERS WHERE ID = :userId")
	boolean deleteUserById(Long userId);

	@Transactional
	@Modifying
	@Query("DELETE FROM POSTS WHERE AUTHOR_ID = :userId")
	void deleteAllUserPostsByAuthorId(Long userId);

	@Transactional
	@Modifying
	@Query("DELETE FROM FOLLOWS WHERE FOLLOWER_ID = :userId OR FOLLOWED_ID = :userId")
	void deleteFollows(Long userId);

	@Query("SELECT COUNT(*) FROM FOLLOWS WHERE FOLLOWED_ID = :userId")
	int getFollowerCount(Long userId);

	@Query("SELECT COUNT(*) FROM FOLLOWS WHERE FOLLOWER_ID = :userId")
	int getFollowsCount(Long userId);

}
