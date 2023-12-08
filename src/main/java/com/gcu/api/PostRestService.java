package com.gcu.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.PostBusinessService;
import com.gcu.model.PostModel;
import com.gcu.model.PostList;

@RestController
@RequestMapping("/api/post-service")
public class PostRestService {

	@Autowired
	PostBusinessService postBusinessService;

	@GetMapping(path = "/getjson", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<PostModel>> getPostsAsJson() {
		List<PostModel> posts = postBusinessService.getAllPosts();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@GetMapping(path = "/getxml", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PostList> getPostsAsXML() {
		PostList list = new PostList();
		list.setPosts(postBusinessService.getAllPosts());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/getjson/{postId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PostModel> getPostasJSONById(@PathVariable Long postId) {

		if (postBusinessService.getPost(postId) == null)

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Optional<PostModel> post = Optional.of(postBusinessService.getPost(postId));

		return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "/getxml/{postId}", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PostModel> getPostAsXMLById(@PathVariable Long postId) {

		if (postBusinessService.getPost(postId) == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Optional<PostModel> post = Optional.of(postBusinessService.getPost(postId));
		return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
