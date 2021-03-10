package user;

public abstract class Academician extends User{



	public Academician(String name, String id, String password) {
		super(name, id, password);
		setAcademicianEmail();
		// TODO Auto-generated constructor stub
	}

	/*
	 * It sets the email which is created with academician domain
	 */

	public void setAcademicianEmail()
	{	
		String name = getName();
		String[] splittedName = name.split(" ");
		String firstName = splittedName[0];
		String lastName = splittedName[splittedName.length-1];
		String email = firstName.toLowerCase()+lastName.toLowerCase()+"@iyte.edu.tr";
		setEmail(email);
	}



}
