package team;

import channel.Channel;
import exception.ItemExistException;
import exception.UnauthorizedUserOperationException;
import user.Academician;
import user.User;

public interface ITeamManagement {

	/**
	 * The function tries to add given channel to the team channels if it is
	 * possible
	 *
	 * @param ch given channel to add to the team's channels
	 */
	public void addChannel(Channel ch) throws ItemExistException;

	/**
	 * The function tries to add given user to the team users if it is possible
	 *
	 * @param user given user to add to the team's users
	 */
	public void addMember(User user) throws ItemExistException, UnauthorizedUserOperationException;

	/**
	 * The function tries to add given academician to the team owners if it is
	 * possible
	 *
	 * @param user given user to add to the team's owners
	 */
	public void addTeamOwner(Academician user) throws ItemExistException, UnauthorizedUserOperationException;

	/**
	 * The function tries to remove given channel from the team channels if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param ch given channel to remove from the team's channels
	 */
	public void removeChannel(Channel ch);

	/**
	 * The function tries to remove given user from the team users if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param user given user to remove from the team's user
	 */
	public void removeMember(User user) throws UnauthorizedUserOperationException;

	/**
	 * The function tries to remove given user from the team owners if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param user given user to remove from the team's owners
	 */
	public void removeTeamOwner(User user) throws UnauthorizedUserOperationException;

	/**
	 * Setter function for Team Management's Team
	 * This function can provide doing operations in other teams
	 * @param team which will be the new team of the Team Management
	 */
	public void setTeam(Team team);

}
