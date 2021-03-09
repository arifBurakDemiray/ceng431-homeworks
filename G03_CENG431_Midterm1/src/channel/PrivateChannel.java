package channel;

import exception.ItemExistException;
import exception.UserExistException;
import storage.IContainer;
import storage.UserContainer;
import team.Team;
import user.User;

public class PrivateChannel extends Channel{
	
	private IContainer<User> participants;
	public PrivateChannel(Meeting meeting) {
		super(meeting);
		this.participants = new UserContainer();
		// TODO Auto-generated constructor stub
	}
	
	public boolean addParticipant(User user) throws ItemExistException {
		try {
			return participants.add(user);
		}
		catch(ItemExistException e)
		{
			return false;
		}
	}

}
