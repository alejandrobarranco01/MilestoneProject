package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.PostsDataService;
import com.gcu.data.entity.PostEntity;
import com.gcu.model.PostModel;

@Service
public class PostsBusinessService implements PostsBusinessServiceInterface {

	@Autowired
	PostsDataService service;

	@Override
	public List<PostEntity> getPosts(Long authorId) {
		return service.getPosts(authorId);
	}

	@Override
	public PostEntity getPost(Long postId) {
		try {
			return service.getPost(postId);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean createPost(PostModel postModel) {
		try {
			return service.createPost(postModel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

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
