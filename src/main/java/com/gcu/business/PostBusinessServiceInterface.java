package com.gcu.business;

import java.util.List;

import com.gcu.model.PostModel;

public interface PostBusinessServiceInterface {

	public List<PostModel> getPosts(String email);

	public PostModel getPost(Long postId);

	public boolean createPost(PostModel postModel);

	public boolean deletePost(Long postId);

	public List<PostModel> getAllPosts();

}
