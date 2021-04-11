package user;

public abstract class User {
	private String userName;
	private String password;

	/**
	 * The constructor for User object
	 * 
	 * @param userName = username of user
	 * @param password = password of user
	 */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	protected String getPassword() {
		return password;
	}

	/**
	 * The function controls that given name equals to that user's name or not
	 * 
	 * @param name = given name
	 * @return boolean of process
	 */
	public boolean equals(String name) {
		String thisName = this.getUserName();
		boolean result = thisName.equals(name);
		return result;
	}

	/**
	 * The function controls that given user equals to that user
	 * 
	 * @param user = given user
	 * @return boolean of process
	 */
	public boolean equals(User user) {
		String thisName = this.getUserName();
		String userName = user.getUserName();
		boolean result = thisName.equals(userName);
		return result;
	}

	/**
	 * The function returns a String of user info.
	 */
	public String toString() {
		String className = this.getClass().getSimpleName();
		String jsonValue = "\"" + this.getUserName() + "\":{\"type\":\"" + className + "\",\"password\":\""
				+ this.password + "\"}";
		return jsonValue;
	}
}
