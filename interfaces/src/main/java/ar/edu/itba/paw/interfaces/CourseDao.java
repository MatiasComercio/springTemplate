package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Course;

import java.util.List;

public interface CourseDao {
	List<Course> getByUser(int userId);
}