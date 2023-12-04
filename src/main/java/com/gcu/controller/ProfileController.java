package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.PostsBusinessService;
import com.gcu.business.SecurityBusinessService;
import com.gcu.data.UsersDataService;
import com.gcu.data.repository.UsersRepository;
import com.gcu.model.PostModel;

@Controller
@RequestMapping("/profile")
@CrossOrigin("http://localhost:8080")
public class ProfileController {
	@Autowired
	PostsBusinessService postService;

	@Autowired
	UsersDataService usersDataService;
	
	@Autowired
	UsersRepository usersRepository;
	
	// Will retrieve the posts specific to each user and store them here
	private List<PostModel> posts = new ArrayList<>();

	// Since we are not yet using Spring Configuration, we will store the email
	// inputed from the login or register page to maintain a "logged in" state
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
        this.posts = postService.getPosts(email); // Retrieving and setting the posts
        
        String username = usersRepository.getAuthorUsernameFromEmail(email);
        
		model.addAttribute("newPost", new PostModel());
		model.addAttribute("posts", posts);
		model.addAttribute("username", username);
        return "profile";
    }

}
