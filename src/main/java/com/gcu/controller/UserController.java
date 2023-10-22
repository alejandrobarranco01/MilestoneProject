package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.PostModel;

@Controller
@RequestMapping("/home")
public class UserController {
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
        System.out.println(newPost.getText());
        System.out.println("here");
        return "redirect:/home/homeSignedIn";
    }
	
	
}