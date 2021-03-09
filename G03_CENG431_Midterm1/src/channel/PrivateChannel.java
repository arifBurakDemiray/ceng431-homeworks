package channel;

import exception.UserExistException;
import storage.IContainer;
import storage.UserContainer;
import team.Team;
import user.User;

public class PrivateChannel extends Channel{
	
	private IContainer<User> participants;
	public PrivateChannel(Team team, Meeting meeting) {
		super(team, meeting);
		this.participants = new UserContainer();
		// TODO Auto-generated constructor stub
	}
	
	public boolean addParticipant(User user) {
		try {
			return participants.add(user);
		}
		catch(UserExistException e)
		{
			return false;
		}
	}

}
