package team;

import channel.Channel;
import channel.MeetingChannel;
import channel.PrivateChannel;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import user.Academician;
import user.User;

public class TeamManagement implements ITeamManagement {

	private Team team;

	/**
	 * The Constructor creates a team management object
	 */
	public TeamManagement() {
	}

	/**
	 * The Constructor creates a team management object with given team object.
	 * 
	 * @param team given team to manage it
	 */
	public TeamManagement(Team team) {
		setTeam(team);
	}

	public boolean addChannel(Channel ch) {
		// it tries to add the channel to the team's channels.
		// if channel was added before, return a message.

		boolean isAdded = this.team.getMeetingChannelList().add(ch);
		return isAdded;

	}

	public boolean addMember(User user) {
		// it tries to add the user to the team's members.
		// if user was added before, return a message.
		boolean isAdded = this.team.getMemberUsers().add(user);
		return isAdded;
	}

	public void addMemberToChannel(String userId, String channelName) {

		try {
			Channel temp = this.team.getMeetingChannelList().getByName(channelName);
			if (temp instanceof MeetingChannel) {
				System.out.println(
						"Channel " + channelName + " is public and has all members on team " + this.team.getId());
			} else {
				if (!this.team.isMember(userId))
					System.out.println("This user is not on team " + this.team.getId());
				else if (!((PrivateChannel) temp).addParticipant(userId))
					System.out.println("This user is already in channel");
				else {
					System.out.println("User added to channel succesfully");
				}
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println("There is no channel named " + channelName);
		}
	}

	public boolean addTeamOwner(User user) {

		// control that given user is an academician or not
		if (user instanceof Academician) {

			// it tries to add the user to the team's owners.
			// if user was added before, return a message.
			boolean isAdded = this.team.getOwners().add(user);
			return isAdded;
			
		} else {
			System.out.println("Only academicians can be owner.");
			return false;
		}
	}

	public void removeChannel(Channel ch) {
		//remove given channel from team
		removeItem(this.team.getMeetingChannelList(), ch);

	}

	public void removeChannelMember(String userId, String channelName) {

		try {//get channel
			Channel temp = this.team.getMeetingChannelList().getByName(channelName);
			if (temp instanceof MeetingChannel) { //if it is a meeting channel means public channel
				System.out.println("Remove operation is not aplicable for channel " + channelName + " on team "
						+ this.team.getId());
			} else {
				((PrivateChannel) temp).removeParticipant(userId);//remove member from channel
				if (((PrivateChannel) temp).getParticipants().isEmpty())//if channel become empty
					removeChannel(temp);							// delete channel from team
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println("There is no channel named " + channelName);
		}

	}

	/**
	 * The function tries to remove given item from the given list if it is possible
	 * If item is in the list remove it. If removing process is not successful, 
	 * returns null.
	 *
	 * @param item      given item to remove from the given container
	 * @param container given container list
	 * @returns removed item if not found return null
	 */
	private <T> T removeItem(IContainer<T> container, T item) {
		T removedItem = null; //Initially null
		try {
			removedItem = container.remove(item); // try to remove invoking container.remove method
			return removedItem;
		} catch (ItemNotFoundException e) {
			return null;
		}

	}

	public void removeMember(User user) {
		User removedUser = ((User) removeItem(this.team.getMemberUsers(), user)); //remove user from team
		if (removedUser.equals(user)) { //if removed successfully from a team
			try {
				user.getTeams().remove(this.team);
				removeTeamOwner(removedUser);
				removeMemberFromChannels(user); //remove also from his channels
			} catch (ItemNotFoundException e) {
				System.out.println("User's team is not removed");
			}
			
		}
	}

	/**
	 * This function removes a user from channels that he joined
	 * @param user
	 */
	private void removeMemberFromChannels(User user) {
		for (Channel channel : team.getMeetingChannelList()) { //iterate through channels list
			boolean participantOfPrivate = (channel instanceof PrivateChannel) //if user participant of a private class
					&& ((PrivateChannel) channel).isMember(user.getId());
			if (participantOfPrivate) {
				removeChannelMember(user.getId(), channel.getName());	//remove user from private channel
			}
		}
	}

	public void removeTeamOwner(User user) {
		removeItem(this.team.getOwners(), user);//remove team owner by generic removeItem function
	}

	public void removeUsers() {
		for (User member : team.getMemberUsers()) { //iterate through members
			try {
				Team tempTeam = member.getTeams().remove(team); //remove team from user
				if (!tempTeam.equals(team))	//if not equals
					System.out.println("Error while removing team from user -> user " + member.toString());

			} catch (ItemNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
