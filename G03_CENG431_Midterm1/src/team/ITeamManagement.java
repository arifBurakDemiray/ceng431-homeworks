package team;

import channel.Channel;
import user.User;

public interface ITeamManagement {

	/**
	 * The function tries to add given channel to the team channels if it is
	 * possible
	 *
	 * @param ch given channel to add to the team's channels
	 * @return true if channel added successfully
	 */
	public boolean addChannel(Channel ch);

	/**
	 * The function tries to add the given user to the team's user.
	 * 
	 * @param user given user for adding to the team
	 * @return true if member added successfully
	 */
	public boolean addMember(User user);

	/**
	 * By this function Private channel members can add participant to their
	 * channel.
	 * 
	 * @param userId      which will be added to to channel by its id
	 * @param channelName name of the channel user will be added
	 */
	public void addMemberToChannel(String userId, String channelName);

	/**
	 * The function tries to add given academician to the team owners if it is
	 * possible
	 *
	 * @param user given user to add to the team's owners
	 * @return true if owner added successfully
	 */
	public boolean addTeamOwner(User user);

	/**
	 * The function tries to remove a channel from a team
	 * 
	 * @param ch that is going to be removed from a team
	 */
	public void removeChannel(Channel ch);

	/**
	 * By this function Private Channel members can remove members from channel.
	 * 
	 * @param userId      who is going to be removed from the channel
	 * @param channelName user is going to be removed from that channel by its name
	 */
	public void removeChannelMember(String userId, String channelName);

	/**
	 * The function tries to remove given user from the team users if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param user given user to remove from the team's user
	 */
	public void removeMember(User user);

	/**
	 * The function tries to remove given user from the team owners if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param user given user to remove from the team's owners
	 */
	public void removeTeamOwner(User user);

	/**
	 * This function removes a removes team from users' team list
	 */
	public void removeUsers();

	/**
	 * Setter function for Team Management's Team This function can provide doing
	 * operations in other teams
	 * 
	 * @param team which will be the new team of the Team Management
	 */
	public void setTeam(Team team);

}
