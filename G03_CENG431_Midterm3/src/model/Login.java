package model;

import observation.Observable;

public class Login extends Observable {

	private Observable user;

	/**
	 * The model for the Login observable model
	 *
	 */
	public Login(Observable user) {
		this.setUser(user);
	}

	/**
	 * @return the user
	 */
	public Observable getUser() {
		return user;
	}

	/**
	 * The function set the given observable model of user set and notify after
	 * setting
	 * 
	 * @param user = the user to set
	 */
	public void setUser(Observable user) {
		this.user = user;
		this.setChanged();
		this.notifyObservers(user);
	}

}
