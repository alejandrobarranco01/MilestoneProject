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

	public PostsDataService(PostsRepository postsRepository, DataSource dataSource) {
		this.postsRepository = postsRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<PostEntity> getPosts(Long authorId) {
		return postsRepository.getPostsByAuthorId(authorId);
	}

	@Override
	public PostEntity getPost(Long postId) {
		return postsRepository.getPostById(postId);
	}

	@Override
	public boolean createPost(PostModel postModel) {
		// From the post model,  we will create a post entity to send to the 
		// database
		Date date = new Date();
		String title = postModel.getTitle();
		String text = postModel.getText();
		
		// If the class author email variable is null, we will set it equal
		// to the post model's author email
		if (this.authorEmail == null) this.authorEmail = postModel.getAuthorEmail();

		
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

	@Override
	public boolean deletePost(Long postId) {
		try {
			postsRepository.deletePost(postId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
