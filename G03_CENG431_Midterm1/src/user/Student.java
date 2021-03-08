package user;

public class Student extends User{

	
	public Student(String firstName, String lastName, String department) {
		super(firstName, lastName, department);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * It sets the email which is created with student domain
	 */

	public void setStudentEmail()
	{	
		String email = getFirstName()+getLastName()+"@std.iyte.edu.tr";
		setEmail(email);
	}


	
	
	

}
