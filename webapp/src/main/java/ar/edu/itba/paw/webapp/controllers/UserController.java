package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.forms.LoginForm;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
		us.register("juan", "12345");

//		// assume SLF4J is bound to logback in the current environment
//		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//		// print logback's internal status
//		StatusPrinter.print(lc);

		LOGGER.debug("Logged user is {}", loggedUser);
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
		return us.getByUsername(auth.getName()); // >>> 2.30: antes era getPrincipal y daba excepción.
	}

	@RequestMapping("/users/{username}")
	public ModelAndView getUser(@PathVariable final String username) {
		final ModelAndView mav = new ModelAndView("user");
		mav.addObject("user", us.getByUsername(username));
		return mav;
	}

}
