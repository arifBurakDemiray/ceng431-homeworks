package user;

import java.nio.charset.Charset;
import java.util.Random;

import storage.TeamContainer;

public abstract class User {

	private TeamContainer teams;
	private String id, firstName, lastName, password, department, email;

	/**
	 * If password and id are not given, create User with given parameters and
	 * create a random id and a random password.
	 * @param firstName  = user's first name
	 * @param lastName   = user's last name
	 * @param department = user's department
	 */
	public User(String firstName, String lastName, String department) {
		setFirstName(firstName);
		setLastName(lastName);
		setDepartment(department);
		setRandom(); // It sets random password and random id. 
		teams = new TeamContainer();
	}

	/**
	 * If password and id are given, create User with given parameters
	 * 
	 * @param firstName  = user's first name
	 * @param lastName   = user's last name
	 * @param department = user's department
	 * @param id         = user's unique id
	 * @param password   = user's 4-length password
	 */
	public User(String firstName, String lastName, String department, String id, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setDepartment(department);
		setId(id);
		setPassword(password);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TeamContainer getTeamList() {
		return teams;
	}

	/**
	 * It creates a random unique id string.
	 * 
	 * @return generatedId which is created random id.
	 */
	public String createId() {
		String generatedId = "aaa";
		return (generatedId);
	}

	/**
	 * It creates a random password string whose length is 4.
	 * 
	 * @return generatedPassword which is created random password.
	 */
	public String createPassword() {

		byte[] array = new byte[4]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedPassword = new String(array, Charset.forName("UTF-8"));
		return generatedPassword;
	}

	/**
	 * Function assigns the random generated id and password to the class' attributes.
	 */
	public void setRandom() {
		String randomId = createId(); // It creates and returns an unique ID.
		setId(randomId);
		String randomPassword = createPassword(); // It creates and returns a password.
		setPassword(randomPassword); // It creates a password.
	}

	public String toString() {
		String info = ("First Name: " + this.firstName + "\n" + "Last Name: " + this.lastName + "\n" + "Id: " + this.id
				+ "\n" + "Email: " + this.email + "\n");

		return (info);
	}
	
	public boolean equals(User usr) {
		if(usr.getFirstName().equals(this.getFirstName()) && 
		   usr.getLastName().equals(this.getLastName()) &&
		   usr.getId().equals(this.getId()))
			return true;
		return false;
	}
}
