/**
 * This class serves as the main controller for handling requests related to the home page
 * and user sign-out functionality.
 */
package com.gcu.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for handling requests related to the main page and user
 * sign-out.
 */
@Controller
public class MainController {

	/**
	 * Handles requests for the root URL ("/"). Depending on the user's sign-in
	 * status, it returns the appropriate view - either the home view for signed-in
	 * users or the default home view for users who are not signed in.
	 *
	 * @param principal Represents the currently authenticated user. It can be null
	 *                  if the user is not signed in.
	 * @return ModelAndView containing the appropriate view based on the user's
	 *         sign-in status.
	 */
	@GetMapping("/")
	public ModelAndView mainPage(Principal principal) {
		if (principal != null) {
			// User is signed in, show a different view
			return new ModelAndView("home/homeSignedIn");
		} else {
			// User is not signed in, show the default view
			return new ModelAndView("home/homeNotSignedIn");
		}
	}

	/**
	 * Handles requests for the "/signout" URL. Redirects the user to the default
	 * home view for users who are not signed in.
	 *
	 * @return ModelAndView Redirects to the default home view for users who are not
	 *         signed in.
	 */
	@GetMapping("/signout")
	public ModelAndView signout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidate the session details
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return new ModelAndView("home/homeNotSignedIn");
	}
}
