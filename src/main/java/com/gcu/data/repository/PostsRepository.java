package com.gcu.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcu.data.entity.PostEntity;

/**
 * Repository interface for managing posts in the database.
 */
@Repository
public interface PostsRepository extends CrudRepository<PostEntity, Long> {
	/**
	 * Retrieves a list of posts for a specific user based on the provided author
	 * ID.
	 *
	 * @param authorId The ID of the author to retrieve posts for.
	 * @return A list of PostEntity objects representing the author's posts.
	 */
	@Query("SELECT * FROM POSTS WHERE AUTHOR_ID = :authorId")
	List<PostEntity> getPostsByAuthorId(Long authorId);

	/**
	 * Retrieves a specific post based on its ID.
	 *
	 * @param postId The ID of the post to retrieve.
	 * @return A PostEntity object representing the retrieved post.
	 */
	@Query("SELECT * FROM POSTS WHERE POST_ID = :postId")
	PostEntity getPostById(Long postId);

	/**
	 * Creates a new post in the database.
	 *
	 * @param date           The date of the post.
	 * @param title          The title of the post.
	 * @param text           The content of the post.
	 * @param authorId       The ID of the post's author.
	 * @param authorUsername The username of the post's author.
	 */
	@Query("INSERT INTO POSTS (DATE, TITLE, TEXT, AUTHOR_ID, AUTHOR_USERNAME) VALUES (:date, :title, :text, :authorId, :authorUsername)")
	void createPost(Date date, String title, String text, Long authorId, String authorUsername);

	/**
	 * Deletes a post from the database based on its ID.
	 *
	 * @param postId The ID of the post to delete.
	 */
	@Modifying
	@Query("DELETE FROM POSTS WHERE POST_ID = :postId")
	void deleteByPostId(Long postId);
	
	@Query("SELECT * FROM POSTS")
	List<PostEntity> getAllPosts();
}
