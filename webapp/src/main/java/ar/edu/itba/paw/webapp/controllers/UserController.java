package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
	public ModelAndView login(@ModelAttribute("loginForm") final LoginForm loginForm) {
		return new ModelAndView("login");
	}

	/* No puedo pedirle parametros del request, pero si cosas generales */
	@ModelAttribute("userId")
	public Integer loggedUser(final HttpSession session) {
		return (Integer) session.getAttribute(LOGGED_USER_ID);
	}


	@RequestMapping(value = "/login" , method = {RequestMethod.POST})
	public ModelAndView login(@Valid @ModelAttribute("loginForm") final LoginForm loginForm,
								final BindingResult errors,
								final HttpSession session) {
		final User user = us.getByUsername(loginForm.getUsername());

		if (errors.hasErrors()) {
			/*return new ModelAndView("redirect:/login");*/ /*+++xcheck: not working: ModelAttribute is being completely re-instantiated, not shared */
			return login(loginForm);/*This worked*/
		}

		final ModelAndView mav;
		if (user != null && user.getPassword().equals(loginForm.getPassword())) {
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
