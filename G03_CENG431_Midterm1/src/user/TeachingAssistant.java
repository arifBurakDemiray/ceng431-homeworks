package user;

public class TeachingAssistant extends Academician {

	/**
	 * Constructor for Teaching assistant subclass
	 * 
	 * @param name
	 * @param id
	 * @param password
	 * @param email
	 */
	public TeachingAssistant(String name, String id, String password, String email) {
		super(name, id, password, email);
	}

	@Override
	public String getSimpleName() {// Simple name of this class
		return "Teaching Assistant";
	}

}
