package model;

import storage.IContainer;

public class User {

	private String userName;
	private String password;
	private IContainer<Collection> collections;
	private IContainer<User> followers;
	private IContainer<User> followings;
	
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	public User(String userName, String password, IContainer<Collection> collections, IContainer<User> followers,
			IContainer<User> followings) {
		this.userName = userName;
		this.password = password;
		this.collections = collections;
		this.followers = followers;
		this.followings = followings;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public IContainer<Collection> getCollections() {
		return collections;
	}
	public IContainer<User> getFollowers() {
		return followers;
	}
	public IContainer<User> getFollowings() {
		return followings;
	}
	
	@Override
	public String toString(){
		return "OKundu kardeşşş";
	}

	public boolean equals(String name){
		return this.getUserName().equals(name);
		
	}

}
