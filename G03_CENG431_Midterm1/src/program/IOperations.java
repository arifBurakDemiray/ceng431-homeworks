package program;

import user.User;
import storage.IContainer;
import team.Team;
import exception.UnauthorizedUserOperationException;

public interface IOperations {

	/**
	 * The function add a meeting channel / private channel which is created
	 * according to given name and given date gotten from user, if user is
	 * authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param team : selected team
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void addMeetingChannel(User user, Team team) throws UnauthorizedUserOperationException;

	/**
	 * The function add a selected member to a selected team if loggedInUser is
	 * authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param team : selected team
	 * 
	 * @param users : all user in the system to print to select a user easily.
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void addTeamMember(IContainer<User> users, User loggedInUser, Team team)
			throws UnauthorizedUserOperationException;

	/**
	 * The function assign a selected instructor/teaching assistant as team owner to
	 * selected team if loggedInUser is authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param team : selected team
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void addTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException;

	/**
	 * The function creates a team with the user's given name and id inputs, if user
	 * is authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param teams : objects of readTeams from file
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void createTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException;

	/**
	 * The function prints all teams' info
	 * 
	 * @param teams : objects of readTeams from file
	 */
	public void findTeam(IContainer<Team> teams);

	/**
	 * The function returns a string to print main menu operation gets an input from
	 * user
	 * 
	 * @return string of operations
	 */
	public String mainOperationsMenu();

	/**
	 * The function remove a meeting channel / private channel which is selected
	 * from menu by user, if user is authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param team : selected team
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void removeMeetingChannel(User user, Team team) throws UnauthorizedUserOperationException;

	/**
	 * The function removes a team which is selected from menu by user, if user is
	 * authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param teams : objects of readTeams from file
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void removeTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException;

	/**
	 * The function remove a selected member from a selected team if loggedInUser is
	 * authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param team : selected team
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void removeTeamMember(User loggedInUser, Team team) throws UnauthorizedUserOperationException;

	/**
	 * The function remove a selected instructor/teaching assistant from team owner
	 * for selected team if loggedInUser is authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param team : selected team
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void removeTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException;

	/**
	 * The function prints statictics of the selected team
	 * 
	 * @param team : selected team
	 */
	public void statisticOfTeam(Team team);

	/**
	 * The function prints the selected team's info
	 * 
	 * @param team : selected team
	 */
	public void teamInfo(Team team);

	/**
	 * The function prints a menu to update a meeting channel / private channel
	 * which is selected from menu by user, if user is authorized to that process
	 * 
	 * @param loggedInUser : user which is logged in
	 * 
	 * @param team : selected team
	 * 
	 * @throws UnauthorizedUserOperationException
	 */
	public void updateChannelOperation(User user, Team team) throws UnauthorizedUserOperationException;

	/**
	 * The function returns a string to print team update operation and gets an
	 * input from user
	 * 
	 * @return strinf of operations
	 */
	public String updateTeamOperationsMenu();
}
