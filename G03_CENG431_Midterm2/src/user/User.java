package user;

public abstract class User {
	private String userName;
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(String name) {
		String thisName = this.getUserName();
		boolean result = thisName.equals(name);
		return result;
	}

	public boolean equals(User user) {
		String thisName = this.getUserName();
		String userName = user.getUserName();
		boolean result = thisName.equals(userName);
		return result;
	}

	public String toString() {
		String className = this.getClass().getSimpleName();
		String jsonValue = "\""+this.getUserName()+"\":{\"type\":\""+className+"\",\"password\":\""+this.password+"\"}";
		return jsonValue;
	}
}
