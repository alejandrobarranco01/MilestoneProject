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

import com.gcu.business.PostBusinessService;
import com.gcu.data.UserDataService;
import com.gcu.data.repository.UserRepository;
import com.gcu.model.PostModel;

/**
 * Controller class responsible for handling user profile-related requests and
 * managing the user's profile page. This class is part of the Spring MVC
 * framework and manages interactions related to the user's own profile.
 */
@Controller
@RequestMapping("/profile")
@CrossOrigin("http://localhost:8080")
public class ProfileController {
	@Autowired
	PostBusinessService postBusinessService;

	@Autowired
	UserDataService usersDataService;

	@Autowired
	UserRepository usersRepository;

	// Will retrieve the posts specific to each user and store them here
	private List<PostModel> posts = new ArrayList<>();

	private String email;

	/**
	 * Handles GET requests to view the user's own profile.
	 * 
	 * @param model The model to be populated with data for the view.
	 * @return The view name.
	 */
	@GetMapping
	public String viewProfile(Model model, HttpSession session) {
		this.email = (String) session.getAttribute("email");
		// Check if the user is logged in
		if (email == null)
			return "home/homeNotSignedIn";
		this.posts = postBusinessService.getPosts(email); // Retrieving and setting the posts

		String username = usersRepository.getAuthorUsernameFromEmail(email);

		model.addAttribute("newPost", new PostModel());
		model.addAttribute("posts", posts);
		model.addAttribute("username", username);
		return "profile";
	}

	/**
	 * Handles the POST request to "/profile/deletePost/{id}" and deletes a post
	 * with the specified ID. It sends the post ID to the next layer in the
	 * application design, the Post Business Service.
	 *
	 * @param id The ID of the post to be deleted.
	 * @return Redirects to the signed-in home page after deleting the post.
	 */
	@PostMapping("/deletePost/{id}")
	public String deletePost(@PathVariable Long id) {
		if (email == null)
			return "home/homeNotSignedIn";
		postBusinessService.deletePost(id);
		Iterator<PostModel> iterator = posts.iterator();
		while (iterator.hasNext()) {
			PostModel post = iterator.next();
			if (post.getId().equals(id)) {
				iterator.remove();
				break;
			}
		}

		return "redirect:/profile";
	}

	/**
	 * Handles the POST request to "/profile/viewPost/{id}" and displays details of
	 * a post with the specified ID.
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

	@GetMapping("/user/{id}")
	public String viewOtherProfile(@PathVariable Long id, Model model) {
		String otherUserEmail = usersRepository.getAuthorEmailFromId(id);
		if (otherUserEmail == null)
			return "error";
		List<PostModel> otherUserPosts = postBusinessService.getPosts(otherUserEmail);

		String otherUserUsername = usersRepository.getAuthorUsernameFromId(id);

		model.addAttribute("posts", otherUserPosts);
		model.addAttribute("username", otherUserUsername);
		return "user";
	}
	
	@PostMapping("/user/viewPost/{id}")
	public String viewOtherUserPost(@PathVariable Long id, Model model) {
		PostModel post = postBusinessService.getPost(id);

		if (post != null) {
			model.addAttribute("post", post);
			return "viewPost";

		}
		
		return "error";
	}

}
