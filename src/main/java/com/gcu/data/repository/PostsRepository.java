package com.gcu.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcu.data.entity.PostEntity;

@Repository
public interface PostsRepository extends CrudRepository<PostEntity, Long> {
	@Query("SELECT * FROM POSTS WHERE AUTHOR_ID = :authorId")
	List<PostEntity> getPostsByAuthorId(Long authorId);

	@Query("SELECT * FROM POSTS WHERE POST_ID = :postId")
	PostEntity getPostById(Long postId);

	@Query("INSERT INTO POSTS (DATE, TITLE, TEXT, AUTHOR_ID, AUTHOR_USERNAME) VALUES (:date, :title, :text, :authorId, :authorUsername)")
	void createPost(Date date, String title, String text, Long authorId, String authorUsername);

	@Query("DELETE FROM POSTS WHERE POST_ID = :postId")
	void deletePost(Long postId);
}
