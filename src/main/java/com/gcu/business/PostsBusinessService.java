package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.PostsDataService;
import com.gcu.data.entity.PostEntity;
import com.gcu.model.PostModel;

/**
 * Service class implementing business logic for handling post-related
 * operations. Most operations involve converting a post entity object from the
 * database to a post model object that the front end can display
 */
@Service
public class PostsBusinessService implements PostsBusinessServiceInterface {

	@Autowired
	PostsDataService service;

	/**
	 * Retrieves a list of posts for a specific user based on the provided email.
	 *
	 * @param email The email of the user to retrieve posts for.
	 * @return A list of PostModel objects representing the user's posts. Converts
	 *         from PostEntity to PostModel.
	 */
	@Override
	public List<PostModel> getPosts(String email) {
		List<PostEntity> postEntities = service.getPosts(email);
		List<PostModel> postModels = new ArrayList<>();

		for (PostEntity postEntity : postEntities) {
			PostModel postModel = new PostModel();
			postModel.setId(postEntity.getPostId());
			postModel.setTitle(postEntity.getTitle());
			postModel.setText(postEntity.getText());
			postModel.setAuthorEmail(postEntity.getAuthorUsername());
			postModels.add(postModel);
		}
		return postModels;
	}

	/**
	 * Retrieves a specific post based on its ID.
	 *
	 * @param postId The ID of the post to retrieve.
	 * @return A PostModel object representing the retrieved post. Converts from
	 *         PostEntity to PostModel.
	 */
	@Override
	public PostModel getPost(Long postId) {
		try {
			PostEntity postEntity = service.getPost(postId);
			PostModel postModel = new PostModel();
			postModel.setId(postEntity.getPostId());
			postModel.setTitle(postEntity.getTitle());
			postModel.setText(postEntity.getText());
			postModel.setAuthorEmail(postEntity.getAuthorUsername());
			return postModel;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a new post based on the provided PostModel object.
	 *
	 * @param postModel The PostModel object containing post data.
	 * @return true if the post is created successfully, false otherwise.
	 * 
	 */
	@Override
	public boolean createPost(PostModel postModel) {
		try {
			return service.createPost(postModel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a post based on its ID.
	 *
	 * @param postId The ID of the post to delete.
	 * @return true if the post is deleted successfully, false otherwise.
	 */
	@Override
	public boolean deletePost(Long postId) {
		try {
			return service.deletePost(postId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
