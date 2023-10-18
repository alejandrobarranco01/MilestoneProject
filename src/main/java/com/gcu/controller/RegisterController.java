/**
 * Controller class for handling user registration-related requests.
 */
package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.RegisterModel;

import jakarta.validation.Valid;

/**
 * Handles requests related to the user registration functionality.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	/**
	 * Displays the registration form view and populates the model with necessary attributes.
	 * 
	 * @param model Model object to add attributes for rendering the view.
	 * @return String representing the name of the registration form view.
	 */
	@GetMapping("/")
	public String display(Model model) {

		model.addAttribute("registerModel", new RegisterModel());
		model.addAttribute("title", "Registration Form:");
		return "register";
	}
	
	/**
	 * Processes the user registration form submission, validates the input, and redirects the user
	 * to the appropriate view based on the registration result.
	 * 
	 * @param registerModel Validated RegisterModel object containing user registration input.
	 * @param bindingResult BindingResult object to check for validation errors.
	 * @param model Model object to add attributes for rendering the view.
	 * @return String representing the name of the view to redirect to based on the registration result.
	 */
	@PostMapping("/doRegister")
	public String doRegister(@Valid RegisterModel registerModel, BindingResult bindingResult, Model model)
	{
		// Check for validation errors
		if (bindingResult.hasErrors()){
			model.addAttribute("title", "Registration Form");
			return "register";
		}
		// Process registration logic and redirect to home view for signed-in users
		model.addAttribute("message", "Test Message");
		return "home/homeSignedIn";
	}
}
