package model;

import storage.IContainer;

public class User {

	private String userName;
	private String password;
	private IContainer<Collection> collections;
	private IContainer<String> followers;
	private IContainer<String> followings;
	
	
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
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public IContainer<Collection> getCollections() {
		return collections;
	}
	public IContainer<String> getFollowers() {
		return followers;
	}
	public IContainer<String> getFollowings() {
		return followings;
	}
	
	@Override
	public String toString(){
		return "    <user userName=\""+this.getUserName()+"\">\n\t"+
		"<password>"+this.getPassword()+"</password>\n\t"+
		"<followers>"+this.getFollowers().toString()+"</followers>\n\t"+
		"<followings>"+this.getFollowings().toString()+"</followings>\n\t"+
		this.getCollections().toString()+"\n    </user>\n";
	}

	public boolean equals(String name){
		return this.getUserName().equals(name);
		
	}

}
