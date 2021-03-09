package team;

import java.util.ArrayList;
import java.util.List;

import channel.Channel;
import exception.ItemExistException;
import exception.UserExistException;
import storage.ChannelContainer;
import storage.IContainer;
import storage.UserContainer;
import user.Academician;
import user.User;

public class Team {

	private String name,id;
	private IContainer<Channel> meeting_ch_list;
	private IContainer<User> memberUsers;
	private List<Academician> owners;
	
	public Team(String name, String id) {
		this.name = name;
		this.id=id;
		this.owners = new ArrayList<Academician>();
		this.meeting_ch_list = new ChannelContainer();
		this.memberUsers = new UserContainer();
	}
	
	
	public void addChannel(Channel ch) throws ItemExistException
	{
		boolean isAdded = meeting_ch_list.add(ch);
		if(isAdded)
		{
			System.out.println("Channel is added");
		}
	}
	
	public void addMember(User user) throws ItemExistException
	{
		this.memberUsers.add(user);
	}
	
	public void addTeamOwner(Academician user)
	{
		this.owners.add(user);
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public String toString()
	{
		return ( this.name + "-" + this.id + "-" + this.memberUsers.getLength());
	}
	
}
