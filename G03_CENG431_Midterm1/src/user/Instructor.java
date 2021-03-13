package user;

import java.util.Locale;

public class Instructor extends Academician {

	public Instructor(String name, String id, String password, String email) {
		super(name, id, password, email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSimpleName() {
		// TODO Auto-generated method stub
		return "Instructor";
	}

	/*
	 * It sets the email which is created with academician domain
	 */
	@Override
	public void createEmail() {
		String name = getName();
		String[] splittedName = name.split(" ");
		String firstName = splittedName[0];
		String lastName = splittedName[splittedName.length - 1];
		String email = (firstName + lastName).toLowerCase(Locale.US) + "@iyte.edu.tr";
		setEmail(email);

	}

}
