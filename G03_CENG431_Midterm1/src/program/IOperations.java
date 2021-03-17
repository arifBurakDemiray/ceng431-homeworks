package program;

import user.User;
import storage.IContainer;
import team.Team;
import channel.Channel;
import exception.UnauthorizedUserOperationException;

public interface IOperations {
	public void createTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException;

	public void removeTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException;

	public void findTeam(IContainer<Team> teams);

	public void addMeetingChannel(User user, Team team) throws UnauthorizedUserOperationException;

	public void removeMeetingChannel(User user, Team team) throws UnauthorizedUserOperationException;

	//public void updateMeetingChannelDate(User user, Team team, Channel channel) throws UnauthorizedUserOperationException;

	public String mainOperationsMenu();

	public String updateTeamOperationsMenu();

	public void updateChannelOperation(User user,Team team) throws UnauthorizedUserOperationException;
	
	public void addTeamMember(IContainer<User> users,User loggedInUser, Team team) throws UnauthorizedUserOperationException;
	
	public void removeTeamMember(User loggedInUser,Team team) throws UnauthorizedUserOperationException;

	public void addTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException;
	
	public void removeTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException;
	
	public void teamInfo(Team team);

	public void statisticOfTeam(Team team);
}
