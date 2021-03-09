package channel;

import team.Team;

public abstract class Channel {
	private Team team;
	private Meeting meeting;
	public Channel(Team team, Meeting meeting) {
		this.meeting = meeting;
		this.team = team;
	}
	
	
}
