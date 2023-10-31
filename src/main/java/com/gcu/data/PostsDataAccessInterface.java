package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.PostEntity;

public interface PostsDataAccessInterface<T> {
	public List<PostEntity> getPosts(Long authorId);

	public PostEntity getPost(Long postId);

	public boolean createPost(PostEntity postEntity);

	public boolean deletePost(Long postId);

}
