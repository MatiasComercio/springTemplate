package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	private final static String LOGGED_USER_ID = "userId";

	@Autowired
	private UserService us;

	@RequestMapping("/")
	public ModelAndView index(final HttpSession session) {
		final ModelAndView mav = new ModelAndView("index");
		mav.addObject("user", us.register("juan", "12345"));
		mav.addObject("userId", session.getAttribute(LOGGED_USER_ID));
		return mav;
	}

	@RequestMapping("/register")
	public ModelAndView register() {
		final ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", us.register("juan", "12345"));
		return mav;
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	/* No puedo pedirle parametros del request, pero si cosas generales */
	@ModelAttribute("userId")
	public Integer loggedUser(final HttpSession session) {
		return (Integer) session.getAttribute(LOGGED_USER_ID);
	}


	@RequestMapping(value = "/login" , method = {RequestMethod.POST})
	public ModelAndView login(@RequestParam("username") final String username,
	                          @RequestParam("password") final String password,
								final HttpSession session) {
		final User user = us.getByUsername(username);

		final ModelAndView mav;
		if (user != null && user.getPassword().equals(password)) {
			session.setAttribute(LOGGED_USER_ID, user.getId());
			mav = new ModelAndView("index");
			mav.addObject("user", user);
		} else {
			mav = new ModelAndView("redirect:/login");
		}
		return mav;
	}

	@RequestMapping("/users/{username}")
	public ModelAndView getUser(@PathVariable final String username) {
		final ModelAndView mav = new ModelAndView("user");
		mav.addObject("user", us.getByUsername(username));
		return mav;
	}

}
