/**
 * Controller class for handling user registration-related requests.
 */
package com.gcu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.SecurityBusinessService;
import com.gcu.model.RegisterModel;

/**
 * Handles requests related to the user registration functionality.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private SecurityBusinessService securityBusinessService;

	/**
	 * Displays the registration form view and populates the model with necessary
	 * attributes.
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
	 * Processes the user registration form submission, validates the input, and
	 * redirects the user to the appropriate view based on the registration result.
	 * 
	 * @param registerModel Validated RegisterModel object containing user
	 *                      registration input.
	 * @param bindingResult BindingResult object to check for validation errors.
	 * @param model         Model object to add attributes for rendering the view.
	 * @return String representing the name of the view to redirect to based on the
	 *         registration result.
	 */
	@PostMapping("/doRegister")
	public String doRegister(@Valid RegisterModel registerModel, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		// Check for validation errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Registration Form");
			return "register";
		}

		int response = securityBusinessService.createAccount(registerModel.getEmail().toLowerCase(),
				registerModel.getUsername().toLowerCase(), registerModel.getPassword());

		switch (response) {
		// Success
		case 1:
			session.setAttribute("email", registerModel.getEmail().toLowerCase());
			return "redirect:/home/homeSignedIn";
		// User name already taken
		case 2:
			model.addAttribute("error", "Username already taken!");
			return "register";
		// Email already taken
		case 3:
			model.addAttribute("error", "Email is already associated with an account!");
			return "register";
		// Fail to save user (back-end error)
		case 4:
			model.addAttribute("error", "Account failed to register! Please try again later.");
			return "register";
		// All other situations
		default:
			model.addAttribute("error", "An unexpected error has occured. Please check all fields");
			return "register";
		}

	}
}
