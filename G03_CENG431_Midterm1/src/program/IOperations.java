package program;

import user.User;
import storage.IContainer;
import team.Team;
import exception.UnauthorizedUserOperationException;

public interface IOperations {
	public void createTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException;

	public void removeTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException;

	public void findTeam(IContainer<Team> teams);
	
	public void addMeetingChannel(User user, Team team) throws UnauthorizedUserOperationException;
	
	public String mainOperationsMenu();

	public String updateTeamOperationsMenu();
}
