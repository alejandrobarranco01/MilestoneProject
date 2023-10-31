package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.PostEntity;
import com.gcu.data.repository.PostsRepository;

@Service
public class PostsDataService implements PostsDataAccessInterface<PostEntity> {
	@Autowired
	private PostsRepository postsRepository;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

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
    public boolean createPost(PostEntity postEntity) {
        try {
            postsRepository.createPost(postEntity.getDate(), postEntity.getTitle(), postEntity.getText(),
                    postEntity.getAuthorId(), postEntity.getAuthorUsername());
            return true;
        } catch (Exception e) {
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
