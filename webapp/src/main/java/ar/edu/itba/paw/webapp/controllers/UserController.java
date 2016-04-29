package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService us;

	@RequestMapping("/403")
	public ModelAndView AccessDenied() {
		return new ModelAndView("403");
	}

	@RequestMapping("/")
	public ModelAndView index(
			@ModelAttribute("loggedUser") final User loggedUser) {
		final ModelAndView mav = new ModelAndView("index");
		/* +++xcheck: should validate that loggedUser is not null */
		mav.addObject("user", loggedUser);
		mav.addObject("userId", loggedUser.getId());
		return mav;
	}

	@RequestMapping("/register")
	public ModelAndView register() {
		final ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", us.register("juan", "12345"));
		return mav;
	}

	@RequestMapping("/login")
	public ModelAndView login(@ModelAttribute("loginForm") final LoginForm loginForm) {
		return new ModelAndView("login");
	}

	@ModelAttribute("loggedUser")
	public User loggedUser() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		/* +++xcheck: that auth is not null */
		return us.getByUsername(auth.getName()); // >>> 2.30: antes era getPrincipal y daba excepción.
	}

	@RequestMapping("/users/{username}")
	public ModelAndView getUser(@PathVariable final String username) {
		final ModelAndView mav = new ModelAndView("user");
		mav.addObject("user", us.getByUsername(username));
		return mav;
	}

}
