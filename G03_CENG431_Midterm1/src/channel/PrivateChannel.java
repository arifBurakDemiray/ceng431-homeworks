package channel;

import storage.IContainer;
import storage.UserContainer;
import team.Team;

public class PrivateChannel extends Channel{
	
	private IContainer participants;
	public PrivateChannel(Team team, Meeting meeting) {
		super(team, meeting);
		this.participants = new UserContainer();
		// TODO Auto-generated constructor stub
	}

}
