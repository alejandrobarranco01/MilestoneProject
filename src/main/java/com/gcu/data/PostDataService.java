package com.gcu.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.PostEntity;
import com.gcu.data.repository.PostRepository;
import com.gcu.data.repository.UsersRepository;

/**
 * Service class responsible for interacting with the data layer to perform CRUD
 * operations on posts. Implements the PostDataAccessInterface to provide
 * methods for accessing and manipulating post data. This class communicates
 * with the Post Repository class to retrieve Post Entity object(s).
 */
@Service
public class PostDataService implements PostDataAccessInterface<PostEntity> {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UsersRepository usersRepository;

	/**
	 * Constructor for PostDataService.
	 *
	 * @param postsRepository The repository for accessing and managing post
	 *                        entities.
	 */
	public PostDataService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	/**
	 * Retrieves a list of Post Entity objects associated with the given user email.
	 *
	 * @param email The email of the user for whom posts are retrieved.
	 * @return A list of PostEntity objects representing posts associated with the
	 *         user.
	 */
	@Override
	public List<PostEntity> getPosts(String email) {
		Long authorId = usersRepository.getAuthorIdFromEmail(email);
		return postRepository.getPostsByAuthorId(authorId);
	}

	/**
	 * Retrieves a specific Post Entity object based on the provided post ID.
	 *
	 * @param postId The ID of the post to be retrieved.
	 * @return The PostEntity object representing the requested post.
	 */
	@Override
	public PostEntity getPost(Long postId) {
		return postRepository.getPostById(postId);
	}

	/**
	 * Creates a new post using the provided Post Entity object.
	 *
	 * @param postModel The PostModel object representing the new post.
	 * @return true if the post creation is successful, false otherwise.
	 */
	@Override
	public boolean createPost(PostEntity newPost) {

		try {
			postRepository.save(newPost);
			return true;
		} catch (Error e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Deletes a post based on the provided post ID.
	 *
	 * @param postId The ID of the post to be deleted.
	 * @return true if the post deletion is successful, false otherwise.
	 */
	@Override
	public boolean deletePost(Long postId) {
		try {
			postRepository.deleteByPostId(postId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<PostEntity> getAllPosts() {
		return postRepository.getAllPosts();
	}
}
