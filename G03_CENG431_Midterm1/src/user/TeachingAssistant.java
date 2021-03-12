package user;


public class TeachingAssistant extends Academician {

	public TeachingAssistant(String name, String id, String password, String email) {
		super(name, id, password,email);
	}
	
	public TeachingAssistant(String name, String id, String password) {
		this(name,id,password,"");
	}

	@Override
	public String getSimpleName() {
		// TODO Auto-generated method stub
		return "Teaching Assistant";
	}

	

}
