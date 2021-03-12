package user;


public class Student extends User{

	

	
	public Student(String name, String id, String password, String email) {
		super(name, id, password,email);
		if(email.equals(""))
			this.setStudentEmail();
	}

	public Student(String name, String id, String password) {
		this(name,id,password,"");
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
		String email = firstName.toLowerCase()+lastName.toLowerCase()+"@std.iyte.edu.tr";
		setEmail(email);
	}
	
	
	@Override
	public String getSimpleName() {
		// TODO Auto-generated method stub
		return "Student";	
	}
}
