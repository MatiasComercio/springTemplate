package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public User register(final String username, final String password) {
		return userDao.create(username, password);
	}

	@Transactional
	@Override
	public User getByUsername(final String username) {
		return userDao.getByUsername(username);
	}

	/* Test purpose only */
	/* default */ void setUserDao(final UserDao userDao) {
		this.userDao = userDao;
	}
}
