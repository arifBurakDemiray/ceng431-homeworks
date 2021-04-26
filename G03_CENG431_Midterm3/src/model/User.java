package model;

import observation.Observable;
import storage.IContainer;

public class User extends Observable {

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
	
	/*public void addFollower(String id){
		
	}
	
	public void removeFollower(String id){
				try {
			this.followers.remove(id);
		} catch (ItemNotFoundException e) {
		}
		setChanged();
		notifyObservers("rmv");
	}
	public void addFollowing(String id){}
	public void removeFollowing(String id){
		try {
			this.followings.remove(id);
		} catch (ItemNotFoundException e) {
		}
		setChanged();
		notifyObservers("unf");
	}*/
	
	public void setAndNotify(String arg)
	{
		setChanged();
		notifyObservers(arg);
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
	
	public boolean equals(User user){
		return this.getUserName().equals(user.getUserName());
		
	}

}
