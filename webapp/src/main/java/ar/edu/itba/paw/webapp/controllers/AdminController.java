package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private UserService us;

	@RequestMapping("/")
	public ModelAndView index(
			@ModelAttribute("loggedUser") final User loggedUser) {
		return new ModelAndView("admin.index");
	}

	@ModelAttribute("loggedUser")
	public User loggedUser() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return us.getByUsername(auth.getName()); // >>> 2.30: antes era getPrincipal y daba excepción.
	}

}
