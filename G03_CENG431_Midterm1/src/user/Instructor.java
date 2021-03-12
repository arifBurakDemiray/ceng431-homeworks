package user;

public class Instructor extends Academician{

	
	public Instructor(String name, String id, String password, String email) {
		super(name, id, password,email);
	}
	
	public Instructor(String name, String id, String password) {
		this(name,id,password,"");
	}

	@Override
	public String getSimpleName() {
		// TODO Auto-generated method stub
		return "Instructor";
	}


	
	

	


	

	

}
