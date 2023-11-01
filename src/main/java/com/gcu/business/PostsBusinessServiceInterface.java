package com.gcu.business;

import java.util.List;

import com.gcu.data.entity.PostEntity;
import com.gcu.model.PostModel;

public interface PostsBusinessServiceInterface {
	public List<PostModel> getPosts(String email);

	public PostModel getPost(Long postId);

	public boolean createPost(PostModel postModel);

	public boolean deletePost(Long postId);
}
