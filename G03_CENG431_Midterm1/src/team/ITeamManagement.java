package team;

import channel.Channel;
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
	public void addChannel(Channel ch);

	/*
	 * The function tries to add the given user to the team's user.
	 * @param user given user to add.
	 * @throws UnauthorizedUserOperationException
	 */
	public void addMember(User user) throws UnauthorizedUserOperationException;

	/**
	 * By this function Private channel members can add participant to their
	 * channel.
	 * 
	 * @param userId      which will be added to to channel by its id
	 * @param channelName name of the channel user will be added
	 * @throws UnauthorizedUserOperationException if non member of the channel tries
	 *                                            to do that function
	 */
	public void addMemberToChannel(String userId, String channelName) throws UnauthorizedUserOperationException;

	/**
	 * The function tries to add given academician to the team owners if it is
	 * possible
	 *
	 * @param user given user to add to the team's owners
	 * @throws UnauthorizedUserOperationException
	 */
	public void addTeamOwner(Academician user) throws UnauthorizedUserOperationException;

	/*
	 * The function tries to remove the defined user from the defined channel.
	 * If channel is not private, return an error because normal channels don't support removing a member from a channel
	 * @param userId given userId to add.
	 * @param channelName to identify which channel to remove
	 * @throws UnauthorizedUserOperationException
	 */
	public void removeChannel(Channel ch);
	

	/**
	 * By this function Private Channel members can remove members from channel.
	 * 
	 * @param userId      who is going to be removed to the channel
	 * @param channelName user is going to be removes from that channel by its name
	 * @throws UnauthorizedUserOperationException if non member of the channel tries
	 *                                            to do that function
	 */
	public void removeChannelMember(String userId, String channelName) throws UnauthorizedUserOperationException;

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
	 * Setter function for Team Management's Team This function can provide doing
	 * operations in other teams
	 * 
	 * @param team which will be the new team of the Team Management
	 */
	public void setTeam(Team team);

}
