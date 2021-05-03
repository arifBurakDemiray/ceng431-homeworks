package model;

import observation.Observable;
import storage.IContainer;

public class User extends Observable {

	private String userName; // user name 
	private String password; // user password
	private IContainer<Collection> collections; // collection container of user
	private IContainer<String> followers; // follower users container of user
	private IContainer<String> followings; // following users container of user
	
	/**
	 * The model for the User
	 *
	 */
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	public User(String userName, String password, IContainer<Collection> collections, IContainer<String> followers,
			IContainer<String> followings) {
		this.userName = userName;
		this.password = password;
		this.collections = collections;
		this.followers = followers;
		this.followings = followings;
		collections = this.collections;
		followers = this.followers;
		followings = this.followings;
	}
	
	/**
	 * Returns the user name
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Returns the user password
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Returns the collection container of user
	 * @return Collection Container
	 */
	public IContainer<Collection> getCollections() {
		return collections;
	}
	
	/**
	 * Returns the follower container of user
	 * @return String Container
	 */
	public IContainer<String> getFollowers() {
		return followers;
	}
	
	/**
	 * Returns the following users container of user
	 * @return String Container
	 */
	public IContainer<String> getFollowings() {
		return followings;
	}
	

	@Override
	//modified to write in a xml file
	public String toString(){
		return "    <user userName=\""+this.getUserName()+"\">\n\t"+
		"\t<password>"+this.getPassword()+"</password>\n\t"+
		"\t<followers>"+this.getFollowers().toString()+"</followers>\n\t"+
		"\t<followings>"+this.getFollowings().toString()+"</followings>\n\t\t"+
		this.getCollections().toString()+"\n    </user>\n";
	}

	//This function looks given user is equal that user or not by given user name
	public boolean equals(String name){
		return this.getUserName().equals(name);
		
	}
	
	// This function looks given user is equal that user or not
	public boolean equals(User user){
		return this.getUserName().equals(user.getUserName());
		
	}

}
