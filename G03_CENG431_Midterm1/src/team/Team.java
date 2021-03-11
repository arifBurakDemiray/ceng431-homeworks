package team;

import channel.Channel;
import storage.ChannelContainer;
import storage.IContainer;
import storage.UserContainer;
import user.User;

public class Team {

	private String name,id;
	private IContainer<Channel> meeting_ch_list;
	private IContainer<User> memberUsers;
	private IContainer<User> owners;
	
	public Team(String name, String id) {
		setName(name);
		setId(id);
		this.owners = new UserContainer();
		this.meeting_ch_list = new ChannelContainer();
		this.memberUsers = new UserContainer();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public IContainer<Channel> getMeetingChannelList()
	{
		return this.meeting_ch_list;
	}
	
	public IContainer<User> getMemberUsers()
	{
		return this.memberUsers;
	}
	
	public IContainer<User> getOwners()
	{
		return this.owners;
	}
		
	public String toString()
	{
		return this.getName()+","+this.getId()+","+this.meeting_ch_list.toString();
	}
	
}
