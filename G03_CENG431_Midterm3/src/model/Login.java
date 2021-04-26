package model;

import observation.Observable;

public class Login extends Observable {

	private Observable user;
	
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
	 * @param user the user to set
	 */
	public void setUser(Observable user) {
		this.user = user;
		this.setChanged();
		this.notifyObservers(user);
	}
	
	
}
