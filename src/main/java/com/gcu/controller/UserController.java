package com.gcu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.PostsBusinessService;
import com.gcu.model.PostModel;

@Controller
@RequestMapping("/home")
@CrossOrigin("http://localhost:8080")
public class UserController {
	@Autowired
	PostsBusinessService postService; 
    private List<PostModel> posts = new ArrayList<>();
	
    @GetMapping("/homeSignedIn")
    public String homeSignedIn(Model model) {
        model.addAttribute("newPost", new PostModel());
        model.addAttribute("posts", posts);
        return "home/homeSignedIn";
    }
	
	@PostMapping("/createPost")
    public String createPost(PostModel newPost) {
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
                return "viewPost"; // 
            }
        }
  
        return "error"; 
    }	
	
}