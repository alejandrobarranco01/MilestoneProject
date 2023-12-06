package com.gcu.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.PostDataService;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.repository.UserRepository;
import com.gcu.model.PostModel;

/**
 * Service class responsible for handling business logic related to posts in the
 * application. Implements the PostBusinessServiceInterface to provide methods
 * for interacting with posts. This class is responsible for maintaining
 * communication between the controllers that call this class and the Post Data
 * Service class. It converts Post Model objects that the controllers can use to
 * Post Entity objects that the Post Data Service can use, and vice versa.
 */
@Service
public class PostBusinessService implements PostBusinessServiceInterface {

	@Autowired
	PostDataService postDataservice;

	@Autowired
	private UserRepository usersRepository;

	/**
	 * Retrieves a list of PostModel objects associated with the given user email.
	 * It does so by communicating with the Post Data Service, which returns Post
	 * Entity objects. This method converts the Post Entity objects to Post Model
	 * objects for the controllers to use.
	 *
	 * @param email The email of the user for whom posts are retrieved.
	 * @return A list of PostModel objects representing posts associated with the
	 *         user.
	 */
	@Override
	public List<PostModel> getPosts(String email) {
		List<PostEntity> postEntities = postDataservice.getPosts(email);
		List<PostModel> postModels = new ArrayList<>();

		for (PostEntity postEntity : postEntities) {
			PostModel postModel = new PostModel();
			postModel.setId(postEntity.getPostId());
			postModel.setTitle(postEntity.getTitle());
			postModel.setText(postEntity.getText());

			String sqlDate = postEntity.getDate().toString();
			sqlDate = formatDate(sqlDate);
			postModel.setDate(sqlDate);

			postModel.setAuthorEmail(postEntity.getAuthorUsername());
			postModels.add(postModel);
		}
		return postModels;
	}
	
	@Override
	public List<PostModel> getFeed(String email) {
		List<PostEntity> postEntities = postDataservice.getFeed(email);
		
		
		List<PostModel> postModels = new ArrayList<>();

		for (PostEntity postEntity : postEntities) {
			PostModel postModel = new PostModel();
			postModel.setId(postEntity.getPostId());
			postModel.setTitle(postEntity.getTitle());
			postModel.setText(postEntity.getText());

			String sqlDate = postEntity.getDate().toString();
			sqlDate = formatDate(sqlDate);
			postModel.setDate(sqlDate);

			postModel.setAuthorEmail(postEntity.getAuthorUsername());
			postModels.add(postModel);
		}
		return postModels;
	}

	/**
	 * Retrieves a specific post based on the provided post ID. It does so by
	 * communicating with the Post Data Service, which returns a Post Entity object.
	 * This method converts the Post Entity object to a Post Model object for the
	 * controllers to use.
	 *
	 * @param postId The ID of the post to be retrieved.
	 * @return The PostModel object representing the requested post.
	 */
	@Override
	public PostModel getPost(Long postId) {
		try {
			PostEntity postEntity = postDataservice.getPost(postId);
			PostModel postModel = new PostModel();
			postModel.setId(postEntity.getPostId());
			postModel.setTitle(postEntity.getTitle());
			postModel.setText(postEntity.getText());

			String sqlDate = postEntity.getDate().toString();
			sqlDate = formatDate(sqlDate);
			postModel.setDate(sqlDate);

			postModel.setAuthorEmail(postEntity.getAuthorUsername());
			return postModel;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Creates a new post using the provided PostModel object. It converts the Post
	 * Model object into a Post Entity object for the Post Data Service to use.
	 *
	 * @param postModel The PostModel object representing the new post.
	 * @return true if the post creation is successful, false otherwise.
	 */
	@Override
	public boolean createPost(PostModel postModel) {
		Date date = new Date();
		String title = postModel.getTitle();
		String text = postModel.getText();
		String authorEmail = postModel.getAuthorEmail();
		Long authorId = usersRepository.getAuthorIdFromEmail(authorEmail);
		String authorUsername = usersRepository.getAuthorUsernameFromEmail(authorEmail);
		PostEntity newPost = new PostEntity(date, title, text, authorId, authorUsername);

		try {
			return postDataservice.createPost(newPost);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a post based on the provided post ID. It sends the post ID to the
	 * Post Data Service.
	 *
	 * @param postId The ID of the post to be deleted.
	 * @return true if the post deletion is successful, false otherwise.
	 */
	@Override
	public boolean deletePost(Long postId) {
		try {
			return postDataservice.deletePost(postId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a list of all PostModel objects in the system for the Post Rest
	 * Service API to utilize. It converts the Post Entity objects into Post Model
	 * objects.
	 *
	 * @return A list of PostModel objects representing all posts in the system.
	 */
	public List<PostModel> getAllPosts() {
		List<PostEntity> postEntities = postDataservice.getAllPosts();
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

	private String formatDate(String sqlDate) {
		try {
			SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date date = sqlDateFormat.parse(sqlDate);

			SimpleDateFormat desiredDateFormat = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
			return desiredDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return sqlDate; // return the original date in case of an exception
		}
	}
}
