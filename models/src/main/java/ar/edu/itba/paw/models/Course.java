package ar.edu.itba.paw.models;

public class Course {

	private final int id;
	private final String name;

	public Course(final int id, final String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
