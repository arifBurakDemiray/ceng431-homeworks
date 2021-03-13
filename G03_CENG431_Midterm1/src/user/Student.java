package user;

import java.util.Locale;

public class Student extends User{

	public Student(String name, String id, String password, String email) {
		super(name, id, password, email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSimpleName() {
		// TODO Auto-generated method stub
		return "Student";	
	}
	
	/*
	 * It sets the email which is created with student domain
	 */
	@Override
	public void createEmail() {
		String name = getName();
		String[] splittedName = name.split(" ");
		String firstName = splittedName[0];
		String lastName = splittedName[splittedName.length-1];
		String email = (firstName+lastName).toLowerCase(Locale.US)+"@std.iyte.edu.tr";
		setEmail(email);
		
	}



	
	
}
