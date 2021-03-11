package team;

import channel.Channel;
import exception.ItemExistException;
import storage.ChannelContainer;
import storage.IContainer;
import storage.UserContainer;
import user.Academician;
import user.User;

public class Team {

	private String name,id;
	private IContainer<Channel> meeting_ch_list;
	private IContainer<User> memberUsers;
	private IContainer<User> owners;
	
	public Team(String name, String id) {
		this.name = name;
		this.id=id;
		this.owners = new UserContainer();
		this.meeting_ch_list = new ChannelContainer();
		this.memberUsers = new UserContainer();
	}
	
	
	public void addChannel(Channel ch) throws ItemExistException
	{
		boolean isAdded = meeting_ch_list.add(ch);
		if(!isAdded)
		{
			System.out.println("Channel was added before.");
		}
	}
	
	public void addMember(User user) throws ItemExistException
	{
		boolean isAdded = memberUsers.add(user);
		if(!isAdded)
		{
			System.out.println("User was added before.");
		}
	}
	
	public void addTeamOwner(Academician user)
	{
		if(user instanceof Academician)
		{
			boolean isAdded = owners.add(user);
			if(!isAdded)
			{
				System.out.println("User "+user.getName()+ " was added before.");
			}
		}
		else
		{
			System.out.println("Only academicians can be owner.");
		}
		
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString()
	{
		return this.getName()+","+this.getId()+","+this.meeting_ch_list.toString();
	}
	
}
