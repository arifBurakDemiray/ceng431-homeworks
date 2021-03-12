package user;

import java.util.Locale;

public abstract class Academician extends User{


	public Academician(String name, String id, String password, String email) {
		super(name, id, password,email);
		if(email.equals(""))
			this.setAcademicianEmail();
	}

	public Academician(String name, String id, String password) {
		this(name,id,password,"");
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
		String email = (firstName+lastName).toLowerCase(Locale.US)+"@iyte.edu.tr";
		setEmail(email);
	}



}
