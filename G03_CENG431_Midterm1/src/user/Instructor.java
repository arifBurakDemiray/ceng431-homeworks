package user;

public class Instructor extends Academician {

	/**
	 * Constructor for instructor class
	 * @param name
	 * @param id
	 * @param password
	 * @param email
	 */
	public Instructor(String name, String id, String password, String email) {
		super(name, id, password, email);
	}

	@Override
	public String getSimpleName() {
		return "Instructor";
	}

}
