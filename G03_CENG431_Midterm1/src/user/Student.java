package user;

import java.util.Locale;

public class Student extends User {

	/**
	 * Student class constructor
	 * 
	 * @param name
	 * @param id
	 * @param password
	 * @param email
	 */
	public Student(String name, String id, String password, String email) {
		super(name, id, password, email);
	}

	/*
	 * It sets the email which is created with student domain
	 */
	@Override
	protected void createEmail() {
		String name = getName();
		String[] splittedName = name.split(" ");
		String firstName = splittedName[0];
		String lastName = splittedName[splittedName.length - 1];
		String email = (firstName + lastName).toLowerCase(Locale.US) + "@std.iyte.edu.tr";
		setEmail(email);

	}

	@Override
	public String getSimpleName() {
		return "Student";
	}

}
