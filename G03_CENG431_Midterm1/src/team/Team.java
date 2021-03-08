package team;

import java.util.ArrayList;
import java.util.List;

import channel.Channel;
import storage.IContainer;
import storage.UserContainer;
import user.TeamOwner;
import user.User;

public class Team {

	private String name;
	private List<Channel> meeting_ch_list;
	private IContainer<User> memberUsers;
	private List<TeamOwner> owners;
	
	public Team(String name, TeamOwner owner) {
		this.name = name;
		this.owners = new ArrayList<TeamOwner>();
		this.owners.add(owner);
		this.meeting_ch_list = new ArrayList<Channel>();
		this.memberUsers = new UserContainer();
	}
}
