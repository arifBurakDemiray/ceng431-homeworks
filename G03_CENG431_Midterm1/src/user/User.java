package user;

import java.nio.charset.Charset;
import java.util.Random;

import storage.IContainer;
import storage.TeamContainer;
import team.Team;

public abstract class User {

	private IContainer<Team> teams;
	private String id, name, password, email;

	/**
	 * If password and id are not given, create User with given parameters and
	 * create a random id and a random password.
	 * @param name  = user's name
	 */
	
	public User(String id, String name, String password, String email) {
		this.email = email;
		this.id=id;
		this.name=name;
		this.password=password;
		
	}
	public User(String name) {
		setName(name);
		setRandomId(); // It sets random id. 
		setRandomPassword();// It sets random password. 
		teams = new TeamContainer();
	}

	/**
	 * If password and id are given, create User with given parameters
	 * 
	 * @param name  = user's name
	 * @param id         = user's unique id
	 * @param password   = user's 4-length password
	 */
	public User(String name,String id, String password) {
		setName(name);
		control(id, password);
		teams = new TeamContainer();
	}
	
	private void control(String id, String password)
	{
		if(id == null)
		{
			setRandomId();
		}
		else
		{
			setId(id);
		}
		
		if(password==null)
		{
			setRandomPassword();
		}
		else
		{
			setPassword(password);
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IContainer<Team> getTeamList() {
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
	public void setRandomPassword() {
		String randomPassword = createPassword(); // It creates and returns a password.
		setPassword(randomPassword); // It creates a password.
	}
	
	/**
	 * Function assigns the random generated id and password to the class' attributes.
	 */
	public void setRandomId() {
		String randomId = createId(); // It creates and returns an unique ID.
		setId(randomId);
	}


	public String toString() {
		String info = ("Name: " + this.name + "\n" + "\n" + "Id: " + this.id
				+ "\n" + "Email: " + this.email + "\n");

		return (info);
	}
	
	public boolean equals(User usr) {
		if(usr.getName().equals(this.getName()) && 
		   usr.getId().equals(this.getId()))
			return true;
		return false;
	}
}
