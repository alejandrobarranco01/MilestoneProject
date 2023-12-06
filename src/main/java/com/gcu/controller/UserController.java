package com.gcu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.PostBusinessService;
import com.gcu.data.repository.UserRepository;
import com.gcu.model.PostModel;

/**
 * Controller class responsible for handling user-related requests and managing
 * the user interface. This class is part of the Spring MVC framework and
 * manages interactions related to user posts.
 */
@Controller
@RequestMapping("/home")
@CrossOrigin("http://localhost:8080")
public class UserController {
	@Autowired
	PostBusinessService postBusinessService;

	@Autowired
	UserRepository userRepository;

	private List<PostModel> feed = new ArrayList<>();

	private String email;
	private String username;

	/**
	 * Handles the GET request to "/home/homeSignedIn" and displays the home page
	 * for a signed-in user. If for some reason the email tied to the session is
	 * null, the "home/homeNotSignedIn" page is displayed instead.
	 *
	 * @param model   The model to convey data to the view.
	 * @param session The HttpSession object for managing user sessions.
	 * @return The view name for the signed-in home page or "home/homeNotSignedIn"
	 *         if the user is not signed in.
	 */
	@GetMapping("/homeSignedIn")
	public String homeSignedIn(Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		if (email == null)
			return "home/homeNotSignedIn";

		this.email = email;
		this.feed = postBusinessService.getPosts(email);
		this.username = userRepository.getAuthorUsernameFromEmail(email);

		model.addAttribute("newPost", new PostModel());
		model.addAttribute("feed", feed);
		model.addAttribute("username", username);
		return "home/homeSignedIn";
	}

	/**
	 * Handles the POST request to "/home/createPost" and creates a new post for the
	 * user. It sends the new post data to the next layer in the application design,
	 * the Post Business Service.
	 *
	 * @param newPost The new post to be created.
	 * @param session The HttpSession object for managing user sessions.
	 * @return Redirects to the signed-in home page after creating the post.
	 */
	@PostMapping("/createPost")
	public String createPost(PostModel newPost, HttpSession session) {
		String email = (String) session.getAttribute("email");

		newPost.setAuthorEmail(email);
		postBusinessService.createPost(newPost);

		return "redirect:/home/homeSignedIn";
	}

	/**
	 * Handles the POST request to "/home/deletePost/{id}" and deletes a post with
	 * the specified ID. It sends the post ID to the next layer in the application
	 * design, the Post Business Service.
	 *
	 * @param id The ID of the post to be deleted.
	 * @return Redirects to the signed-in home page after deleting the post.
	 */
	@PostMapping("/deletePost/{id}")
	public String deletePost(@PathVariable Long id) {
		if (email == null)
			return "home/homeNotSignedIn";
		postBusinessService.deletePost(id);
		Iterator<PostModel> iterator = feed.iterator();
		while (iterator.hasNext()) {
			PostModel post = iterator.next();
			if (post.getId().equals(id)) {
				iterator.remove();
				break;
			}
		}

		return "redirect:/home/homeSignedIn";
	}

	/**
	 * Handles the POST request to "/home/viewPost/{id}" and displays details of a
	 * post with the specified ID.
	 *
	 * @param id    The ID of the post to be viewed.
	 * @param model The model to convey data to the view.
	 * @return The view name for viewing the post or "error" if the user is not
	 *         signed in or the post is not found.
	 */
	@PostMapping("/viewPost/{id}")
	public String viewPost(@PathVariable Long id, Model model) {
		if (email == null)
			return "home/homeNotSignedIn";
		PostModel post = postBusinessService.getPost(id);

		if (post != null) {
			model.addAttribute("post", post);
			return "viewPost";

		}
		return "error";
	}

	@PostMapping("/search")
	public String searchUsers(@RequestParam String query, Model model) {
		Long userId = userRepository.findUsernamesContaining(query);
		if (userId == null) {
			return "redirect:/home/homeSignedIn";
		} else if (userId == userRepository.getAuthorIdFromEmail(email)) {
			return "redirect:/profile";
		} else {
			return "redirect:/profile/user/" + userId;
		}
	}

}