package user;

public class Student extends User{

	


	public Student(String name, String id, String password) {
		super(name, id, password);
		setStudentEmail();
		// TODO Auto-generated constructor stub
	}

	/*
	 * It sets the email which is created with student domain
	 */

	public void setStudentEmail()
	{	
		String name = getName();
		String[] splittedName = name.split(" ");
		String firstName = splittedName[0];
		String lastName = splittedName[splittedName.length-1];
		String email = firstName+lastName+"@std.iyte.edu.tr";
		setEmail(email);
	}


	
	
	

}
