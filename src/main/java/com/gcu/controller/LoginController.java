/**
 * Controller class for handling login-related requests.
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

import com.gcu.business.PostBusinessService;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.LoginModel;

/**
 * Handles requests related to the login functionality.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private SecurityBusinessService securityBusinessService;

	@Autowired
	PostBusinessService postService;

	/**
	 * Displays the login form view and populates the model with necessary
	 * attributes.
	 *
	 * @param model Model object to add attributes for rendering the view.
	 * @return String representing the name of the login form view.
	 */
	@GetMapping("/")
	public String display(Model model) {
		// Display login form view
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}

	/**
	 * Processes the login form submission, validates the input, and redirects the
	 * user to the appropriate view based on the login result.
	 *
	 * @param loginModel    Validated LoginModel object containing user input.
	 * @param bindingResult BindingResult object to check for validation errors.
	 * @param model         Model object to add attributes for rendering the view.
	 * @return String representing the name of the view to redirect to based on the
	 *         login result.
	 */
	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {

		// Check for validation errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}
		// Milestone 4
		if (!securityBusinessService.authenticate(loginModel.getUsername(), loginModel.getPassword())) {
			// Display errors in login page
			return "login";
		}

		session.setAttribute("email", loginModel.getUsername());

		// Process login logic and redirect to home view for signed-in users
		return "redirect:/home/homeSignedIn";
	}
}
