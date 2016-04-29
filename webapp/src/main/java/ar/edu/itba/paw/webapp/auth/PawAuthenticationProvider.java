package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class PawAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService us;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String username = (String) authentication.getPrincipal();
		final String password = (String) authentication.getCredentials();

		final User user = us.getByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			return new UsernamePasswordAuthenticationToken(username, password);
		}

		return null;
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
