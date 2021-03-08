package user;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import team.Team;

public abstract class User {

	private List<Team> teamList;
	private String id, firstName, lastName, password, department, email;

	/**
	 * If password and id are not given, create User with given parameters
	 * and create a random id and a random password.
	 * @param firstName
	 * @param lastName
	 * @param department
	 */
	public User(String firstName, String lastName, String department) {
		setFirstName(firstName);
		setLastName(lastName);
		setDepartment(department);
		createId(); // It creates an unique ID.
		createPassword(); // It creates a password.
	}
	
	/**
	 * If password and id are given, create User with given parameters
	 * @param firstName
	 * @param lastName
	 * @param department
	 * @param id
	 * @param password
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

	public List<Team> getTeamList() {
		return teamList;
	}
	
	
	/*
	 * It creates a random id string 
	 *  BURADA UNIQUE OLMA OLAYINA BAKMAK LAZIM. TÜM ID'leri bir yerde tutmak lazım ki kontrol etmek lazım.
	 */
	public void createId() {
		String id = "aaa";
		setId(id);
	}
	
	
	/*
	 * It creates a random password string whose length is 4, and sets the password with that string.
	 */
	public void createPassword() {

		byte[] array = new byte[4]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		setPassword(generatedString);
	}

	public String toString() {
		String info = ("First Name: " + this.firstName + "\n" + "Last Name: " + this.lastName + "\n" + "Id: " + this.id
				+ "\n" + "Email: " + this.email + "\n");

		return (info);
	}

}
