package com.gcu.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "posts")
public class PostList {
	private List<PostModel> posts = new ArrayList<PostModel>();

	@XmlElement(name = "post")
	public List<PostModel> getPosts() {
		return this.posts;
	}

	public void setPosts(List<PostModel> posts) {
		this.posts = posts;
	}
}
