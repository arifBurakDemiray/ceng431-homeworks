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
	private IContainer<Channel> meeting_ch_list;
	private IContainer<User> memberUsers;
	private IContainer<User> owners;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IContainer<Channel> getMeetingChannelList() {
		return this.meeting_ch_list;
	}

	public IContainer<User> getMemberUsers() {
		return this.memberUsers;
	}

	public IContainer<User> getOwners() {
		return this.owners;
	}

	public String toString() {
		return this.getName() + "," + this.getId() + "," + this.meeting_ch_list.toString();
	}

	public boolean isTeamOwner(String id) {
		boolean isFound = false;
		try {
			this.getOwners().getById(id);
			isFound = true;
		} catch (ItemNotFoundException | NotSupportedException e) {
			
		}
		return isFound;
	}
	
	public boolean isMember(String id) {
		boolean isFound = false;
		try {
			this.getMemberUsers().getById(id);
			isFound = true;
		} catch (ItemNotFoundException | NotSupportedException e) {
			
		}
		return isFound;
	}
	
	public boolean equals(Team team){
		if(this.name.equals(team.getName()) && this.id.equals(team.getId()))
			return true;
		return false;
	}
	
	
}
