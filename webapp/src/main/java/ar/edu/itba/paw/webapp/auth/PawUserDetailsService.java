package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class PawUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService us;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = us.getByUsername(username);
		if (user != null) {
			final Collection<GrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER")); /* +++xchange: estamos cableandole el rol; esto deberiamos levantarlo tambien de los datos del usuario de la base de datos */
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.	getPassword(), true, true , true, true, authorities);
		}

		throw new UsernameNotFoundException("No user found by " + username);
	}


}
