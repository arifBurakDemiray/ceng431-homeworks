package team;

import channel.Channel;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.ChannelContainer;
import storage.IContainer;
import storage.UserContainer;
import user.User;

public class Team {

	private String name, id;
	private IContainer<Channel> meeting_ch_list; //channel list
	private IContainer<User> memberUsers;	//members list
	private IContainer<User> owners;	//owners list
	
	/**
	 * Constructor creates a Team with given name and id.
	 * 
	 * @param name team's given name
	 * @param id   team's given id
	 */
	public Team(String name, String id) {
		setName(name);
		setId(id);
		this.owners = new UserContainer(); // it holds owners of the this team
		this.meeting_ch_list = new ChannelContainer(); // it holds channels of the this team
		this.memberUsers = new UserContainer(); // it holds members of the this team
	}
	
	/**
	 * This function takes a team looks for equality with @this
	 * @param team be looked for equality
	 * @returns if equal true
	 */
	@Override
	public boolean equals(Object obj) {
		Team team = ((Team) obj);
		if (this.id.equals(team.getId()))
			return true;
		return false;
	}
	

	//getters for team class
	public String getId() {
		return this.id;
	}

	public IContainer<Channel> getMeetingChannelList() {
		return this.meeting_ch_list;
	}

	public IContainer<User> getMemberUsers() {
		return this.memberUsers;
	}

	public String getName() {
		return this.name;
	}

	public IContainer<User> getOwners() {
		return this.owners;
	}

	/**
	 * Looks for given user id is a member of @this team
	 * @param id of a user
	 * @return true if user is member
	 */
	public boolean isMember(String id) {
		boolean isFound = false;
		try {
			this.getMemberUsers().getById(id); //try to get by id
			isFound = true; //if not throw any exception means we found
		} catch (ItemNotFoundException | NotSupportedException e) {}
		return isFound;
	}

	/**
	 * This function looks for given user id is owner of @this team
	 * @param id of a user
	 * @return true if user is owner
	 */
	public boolean isTeamOwner(String id) {
		boolean isFound = false;
		try {
			this.getOwners().getById(id); //try to get by id
			isFound = true; //if not throw any exception means we found
		} catch (ItemNotFoundException | NotSupportedException e) {}
		return isFound;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//to string method for team class
	public String toString() {
		return this.getName() + "," + this.getId() + "," + this.meeting_ch_list.toString();
	}

}
