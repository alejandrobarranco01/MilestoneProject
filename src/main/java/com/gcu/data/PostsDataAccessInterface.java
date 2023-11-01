package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.PostEntity;
import com.gcu.model.PostModel;

public interface PostsDataAccessInterface<T> {
	public List<PostEntity> getPosts(Long authorId);

	public PostEntity getPost(Long postId);

	public boolean createPost(PostModel postModel);

	public boolean deletePost(Long postId);

}
