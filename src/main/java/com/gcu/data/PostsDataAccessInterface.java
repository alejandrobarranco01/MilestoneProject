package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.PostEntity;
import com.gcu.model.PostModel;

/**
 * The interface for data access operations related to posts.
 *
 * @param <T> The type of object representing post data (e.g., PostModel).
 */
public interface PostsDataAccessInterface<T> {
	/**
	 * Retrieves a list of posts associated with the given email address.
	 *
	 * @param email The email address of the user whose posts are to be retrieved.
	 * @return A list of PostEntity objects representing the user's posts.
	 */
	public List<PostEntity> getPosts(String email);

	/**
	 * Retrieves a specific post based on its ID.
	 *
	 * @param postId The unique identifier of the post to retrieve.
	 * @return The PostEntity object representing the specified post.
	 */
	public PostEntity getPost(Long postId);

	/**
	 * Creates a new post based on the provided PostModel object.
	 *
	 * @param postModel The PostModel object containing post data to be stored.
	 * @return {@code true} if the post creation is successful, {@code false}
	 *         otherwise.
	 */
	public boolean createPost(PostModel postModel);

	/**
	 * Deletes a post with the specified ID.
	 *
	 * @param postId The unique identifier of the post to be deleted.
	 * @return {@code true} if the post deletion is successful, {@code false}
	 *         otherwise.
	 */
	public boolean deletePost(Long postId);
	
	public List<PostEntity> getAllPosts();

}
