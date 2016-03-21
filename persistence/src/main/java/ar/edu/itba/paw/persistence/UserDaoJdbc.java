package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoJdbc implements UserDao { /* TODO: Change for UserJdbcDao */

	private static final String TABLE_NAME = "users";
	private static final String USERNAME_COLUMN = "username";
	private static final String PASSWORD_COLUMN = "password";

	private final UserRowMapper userRowMapper;
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert jdbcInsert;

	private final CourseDao courseDao;

	@Autowired
	public UserDaoJdbc(final DataSource dataSource, final CourseDao courseDao) {

		this.userRowMapper = new UserRowMapper(courseDao);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		/* TODO: export table name as a private final String */
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns("userId");
		this.courseDao = courseDao;
		/* TODO: export table creation as a private final String */
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (" +
				"userId integer IDENTITY PRIMARY KEY, " +
				"username varchar (100), " +
				"password varchar (100))");
	}

	public User create(final String username, final String password) {
		final Map<String, Object> args = new HashMap<>();
		args.put(USERNAME_COLUMN, username);
		args.put(PASSWORD_COLUMN, password);
		final Number key = jdbcInsert.executeAndReturnKey(args);
		final int id = key.intValue();
//TODO:		jdbcInsert.execute(args);

		return new User(id, username, password, Collections.<Course>emptyList());
	}

	@Override
	public User getByUsername(final String username) {
		List<User> users = jdbcTemplate.query(
						"SELECT * " +
						"FROM users " +
						"WHERE username = ? LIMIT 1", userRowMapper, username);

		return users.isEmpty() ? null : users.get(0);
	}

	private static class UserRowMapper implements RowMapper<User> {

		private final CourseDao courseDao;

		public UserRowMapper(final CourseDao courseDao) {
			this.courseDao = courseDao;
		}

		@Override
		public User mapRow(final ResultSet resultSet, final int rowNumber) throws SQLException {
			final int id = resultSet.getInt("userId");
			final List<Course> courses = courseDao.getByUser(id);
			return new User(id, resultSet.getString(USERNAME_COLUMN), resultSet.getString(PASSWORD_COLUMN), courses);
		}
	}
}
