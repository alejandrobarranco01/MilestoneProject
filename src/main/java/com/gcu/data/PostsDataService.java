package com.gcu.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.PostsRepository;
import com.gcu.data.repository.UsersRepository;
import com.gcu.data.mapper.UserRowMapper;
import com.gcu.model.PostModel;

/**
 * Service class providing data access methods for posts.
 */
@Service
public class PostsDataService implements PostsDataAccessInterface<PostEntity> {
	@Autowired
	private PostsRepository postsRepository;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	@Autowired
	private UsersRepository usersRepository;

	// We will also be storing the author email here in a temporary storage
	// for the class, it will be important to keep for any future CRUD methods
	private String authorEmail;

	/**
	 * Constructor for PostsDataService class.
	 *
	 * @param postsRepository The repository for posts.
	 * @param dataSource      The data source for database connection.
	 */
	public PostsDataService(PostsRepository postsRepository, DataSource dataSource) {
		this.postsRepository = postsRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * Retrieves a list of posts for a specific user based on the provided email.
	 *
	 * @param email The email of the user to retrieve posts for.
	 * @return A list of PostEntity objects representing the user's posts.
	 */
	@Override
	public List<PostEntity> getPosts(String email) {
		Long authorId = usersRepository.getAuthorIdFromEmail(email);
		return postsRepository.getPostsByAuthorId(authorId);
	}

	/**
	 * Retrieves a specific post based on its ID.
	 *
	 * @param postId The ID of the post to retrieve.
	 * @return A PostEntity object representing the retrieved post.
	 */
	@Override
	public PostEntity getPost(Long postId) {
		return postsRepository.getPostById(postId);
	}

	/**
	 * Creates a new post based on the provided PostModel object.
	 *
	 * @param postModel The PostModel object containing post data.
	 * @return true if the post is created successfully, false otherwise.
	 */
	@Override
	public boolean createPost(PostModel postModel) {
		// From the post model, we will create a post entity to send to the
		// database
		Date date = new Date();
		String title = postModel.getTitle();
		String text = postModel.getText();

		// If the class author email variable is null, we will set it equal
		// to the post model's author email
		if (this.authorEmail == null)
			this.authorEmail = postModel.getAuthorEmail();

		// Here we query the author id and author username based on the author email
		// This is important because we need these two values in order to create a
		// post entity that we will send to the database
		Long authorId = usersRepository.getAuthorIdFromEmail(authorEmail);
		String authorUsername = usersRepository.getAuthorUsernameFromEmail(authorEmail);

		PostEntity newPost = new PostEntity(date, title, text, authorId, authorUsername);

		try {
			postsRepository.save(newPost);
			return true;
		} catch (Error e) {
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
			postsRepository.deleteByPostId(postId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<PostEntity> getAllPosts() {
		return postsRepository.getAllPosts();
	}
}
