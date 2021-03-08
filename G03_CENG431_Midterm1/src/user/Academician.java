package user;

public abstract class Academician extends User{

	
	

	public Academician(String firstName, String lastName, String department) {
		super(firstName, lastName, department);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * It sets the email which is created with academician domain
	 */

	public void setAcademicianEmail()
	{	
		String email = getFirstName()+getLastName()+"@iyte.edu.tr";
		setEmail(email);
	}



}
