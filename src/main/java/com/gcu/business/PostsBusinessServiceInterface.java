package com.gcu.business;

import java.util.List;

import com.gcu.data.entity.PostEntity;
import com.gcu.model.PostModel;

/**
 * The interface for the business service handling operations related to posts.
 */
public interface PostsBusinessServiceInterface {
	/**
	 * Retrieves a list of posts associated with the given email.
	 *
	 * @param email The email of the author whose posts are to be retrieved.
	 * @return A list of {@link PostModel} objects representing posts.
	 */
	public List<PostModel> getPosts(String email);

	/**
	 * Retrieves a specific post based on its ID.
	 *
	 * @param postId The unique identifier of the post to be retrieved.
	 * @return A {@link PostModel} object representing the specified post.
	 */
	public PostModel getPost(Long postId);

	/**
	 * Creates a new post based on the provided {@link PostModel}.
	 *
	 * @param postModel The {@link PostModel} object containing post data.
	 * @return {@code true} if the post creation is successful, {@code false}
	 *         otherwise.
	 */
	public boolean createPost(PostModel postModel);

	/**
	 * Deletes a post based on its ID.
	 *
	 * @param postId The unique identifier of the post to be deleted.
	 * @return {@code true} if the post deletion is successful, {@code false}
	 *         otherwise.
	 */
	public boolean deletePost(Long postId);
	
	public List<PostModel> getAllPosts();

}
