package user;

import java.util.Random;
import storage.IContainer;
import storage.TeamContainer;
import team.Team;

public abstract class User {

	private IContainer<Team> teams; //This container holds user's teams
	private String id, name, password, email;

	/**
	 * The Constructor creates User with given parameters and invoke control() to do
	 * that if the given id or password is null it creates a random id or a random
	 * password.
	 * 
	 * @param name     = user's name
	 * @param id       = user's id
	 * @param password = user's password
	 * @param email    = user's email
	 */

	public User(String name, String id, String password, String email) {
		setName(name);
		this.control(id, password, email);
		teams = new TeamContainer(); // create a TeamContainer to hold the teams which user is in.
	}

	/**
	 * The functions controls if the given password is null it invokes
	 * setRandomPassword() to create random password. If password or id are not null
	 * just set them.
	 *
	 * @param id       user's given id
	 * @param password user's given password
	 */

	private void control(String id, String password, String email) {
		if (!id.equals("")) { //if id not equal null, set id
			setId(id);
		}

		if (password.equals("")) {
			setRandomPassword(); // invoke it to create random password and set password.
		} else {
			setPassword(password);
		}

		if (email.equals("")) {
			createEmail(); // invoke it to create user email.
		} else {
			setEmail(email);
		}

	}

	/**
	 * It creates a email in the subclasses.
	 */
	protected abstract void createEmail();

	/**
	 * It creates a random unique id string.
	 * 
	 * @return generatedId which is created random id.
	 */
	private String createId() {
		Random rand = new Random();
		Integer id = rand.nextInt(999) + 1;
		return (String.valueOf(id));
	}

	/**
	 * It creates a random password string whose length is 4.
	 * 
	 * @return generatedPassword which is created random password.
	 */
	private String createPassword() {

		int length = 4;
		String symbol = "-./*_=+)";
		String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String small_letter = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		String finalString = cap_letter + small_letter + numbers + symbol; //add all possibilities

		Random random = new Random();

		char[] password = new char[length]; //create id array

		for (int i = 0; i < length; i++) { //and randomly select from finalString
			password[i] = finalString.charAt(random.nextInt(finalString.length()));

		}
		return String.valueOf(password);
	}
	
	
	
	/**
	 * The function controls that given user is equal to this user and returns a
	 * boolean
	 *
	 * @return true/false
	 */
	@Override
	public boolean equals(Object obj) {
		User usr = ((User) obj);
		if (usr.getName().equals(this.getName()) && usr.getId().equals(this.getId()))
			return true;
		return false;
	}
	
	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * this function creates simple name of child classes
	 * @returns simple name of the child classes
	 */
	public abstract String getSimpleName();

	public IContainer<Team> getTeams() {
		return teams;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Function assigns the random generated id and password to the class'
	 * attributes.
	 */
	public void setRandomId() {
		String randomId = createId(); // It creates and returns an unique ID.
		setId(randomId);
	}

	/**
	 * Function assigns the random generated id and password to the class'
	 * attributes.
	 */
	private void setRandomPassword() {
		String randomPassword = createPassword(); // It creates and returns a password.
		setPassword(randomPassword); // It creates a password.
	}

	public void setTeams(IContainer<Team> teams) {
		this.teams = teams;
	}

	/**
	 * The function returns a string which includes all info of user.
	 *
	 * @return info
	 */
	public String toString() {
		String teams = "";
		for (Team item : this.teams.getContainer()) {
			teams = teams + item.getId() + ",";
		}
		if (teams.endsWith(",")) {
			teams = teams.substring(0, teams.length() - 1);
		}

		// String className = tempClassName.split(".")[1];
		String info = (this.getSimpleName() + "," + getName() + "," + getId() + "," + getEmail() + "," + getPassword()
				+ "," + teams);
		return info;
	}
}
