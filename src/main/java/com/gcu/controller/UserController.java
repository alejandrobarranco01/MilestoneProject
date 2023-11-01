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

	@GetMapping("/homeSignedIn")
	public String homeSignedIn(Model model, @ModelAttribute("email") String email) {
		model.addAttribute("newPost", new PostModel());
		model.addAttribute("posts", posts);
		this.email = email; // Storing the email
		return "home/homeSignedIn";
	}

	@PostMapping("/createPost")
	public String createPost(PostModel newPost) {
		// Setting the author email attribute to the post model
		// This will be important when we later try to query
		// the author id and author email
		newPost.setAuthorEmail(email);
		postService.createPost(newPost);
		posts.add(newPost);
		return "redirect:/home/homeSignedIn";
	}

	@PostMapping("/deletePost/{id}")
	public String deletePost(@PathVariable Long id) {
		Iterator<PostModel> iterator = posts.iterator();
		while (iterator.hasNext()) {
			PostModel post = iterator.next();
			if (post.getId().equals(id)) {
				iterator.remove();
				break;
			}
		}
		return "redirect:/home/homeSignedIn";
	}

	@PostMapping("/viewPost/{id}")
	public String viewPost(@PathVariable Long id, Model model) {
		for (PostModel post : posts) {
			if (post.getId().equals(id)) {
				model.addAttribute("post", post);
				return "viewPost";
			}
		}

		return "error";
	}

}