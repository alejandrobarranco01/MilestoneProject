package com.gcu.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.PostBusinessService;
import com.gcu.model.PostModel;
import com.gcu.model.PostsList;

@RestController
@RequestMapping("/post-service")
public class PostsRestService {

	@Autowired
	PostBusinessService postService;

	@GetMapping(path = "/getjson", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<PostModel> getOrdersAsJson() {
		return postService.getAllPosts();
	}

	@GetMapping(path = "/getxml", produces = { MediaType.APPLICATION_XML_VALUE })
	public PostsList getOrdersAsXML() {
		PostsList list = new PostsList();
		list.setPosts(postService.getAllPosts());
		return list;
	}

}
