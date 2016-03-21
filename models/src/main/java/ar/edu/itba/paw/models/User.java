package ar.edu.itba.paw.models;

import java.util.Collections;
import java.util.List;

public class User {
	private final int id;
	private final String username;
	private final String password;
	private final List<Course> courses;

	public User(final int id, final String username, final String password, final List<Course> courses) {
		this.id = id;

		this.username = username;
		this.password = password;
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", id=" + id +
				'}';
	}
}
