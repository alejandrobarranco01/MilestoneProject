package com.gcu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.PostsBusinessService;
import com.gcu.model.PostModel;

/**
 * Controller class for handling user-related actions and views.
 */
@Controller
@RequestMapping("/home")
@CrossOrigin("http://localhost:8080")
public class UserController {
	@Autowired
	PostsBusinessService postService;

	// Will retrieve the posts specific to each user and store them here
	private List<PostModel> posts = new ArrayList<>();

	// Since we are not yet using Spring Configuration, we will store the email
	// inputed from the login or register page to maintain a "logged in" state
	private String email;

	/**
	 * Handles GET requests for the homeSignedIn view, displaying posts for the
	 * logged-in user.
	 * 
	 * @param model The model to be populated with data for the view.
	 * @param email The email of the logged-in user.
	 * @return The view name.
	 */
	@GetMapping("/homeSignedIn")
	public String homeSignedIn(Model model, @ModelAttribute("email") String email) {
		this.posts = postService.getPosts(email); // Retrieving and setting the posts
		model.addAttribute("newPost", new PostModel());
		model.addAttribute("posts", posts);
		this.email = email; // Storing the email
		return "home/homeSignedIn";
	}

	/**
	 * Handles POST requests to create a new post.
	 * 
	 * @param newPost The new post data.
	 * @return The redirect URL with the email in the query parameters.
	 */
	@PostMapping("/createPost")
	public String createPost(PostModel newPost) {
		// Setting the author email attribute to the post model
		// This will be important when we later try to query
		// the author id and author email
		newPost.setAuthorEmail(email);
		postService.createPost(newPost);

		// Redirect with the email in the query parameters
		return String.format("redirect:/home/homeSignedIn?email=%s", email);
	}

	/**
	 * Handles POST requests to delete a post by its ID.
	 * 
	 * @param id The ID of the post to be deleted.
	 * @return The redirect URL with the email in the query parameters.
	 */
	@PostMapping("/deletePost/{id}")
	public String deletePost(@PathVariable Long id) {
		postService.deletePost(id);
		Iterator<PostModel> iterator = posts.iterator();
		while (iterator.hasNext()) {
			PostModel post = iterator.next();
			if (post.getId().equals(id)) {
				iterator.remove();
				break;
			}
		}

		// Redirect with the email in the query parameters
		return String.format("redirect:/home/homeSignedIn?email=%s", email);
	}

	/**
	 * Handles POST requests to view a specific post by its ID.
	 * 
	 * @param id    The ID of the post to be viewed.
	 * @param model The model to be populated with data for the view.
	 * @return The view name.
	 */
	@PostMapping("/viewPost/{id}")
	public String viewPost(@PathVariable Long id, Model model) {
		// Retrieve the post model from the post business service
		PostModel post = postService.getPost(id);

		// If the post is not null (meaning it exists)
		// return the post
		if (post != null) {
			model.addAttribute("post", post);
			return "viewPost";

		}
		// Otherwise display the error page
		return "error";

	}

}