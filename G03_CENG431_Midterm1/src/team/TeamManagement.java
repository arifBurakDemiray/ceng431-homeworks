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

	public void addChannel(Channel ch) {
		// it tries to add the channel to the team's channels.
		// if channel was added before, return a message.

		boolean isAdded = this.team.getMeetingChannelList().add(ch);
		if (!isAdded) {
			System.out.println("Channel was added before.");
		}

	}

	public void addMember(User user) {
		// it tries to add the user to the team's members.
		// if user was added before, return a message.
		boolean isAdded = this.team.getMemberUsers().add(user);
		if (!isAdded) {
			System.out.println("User was added before.");
		}
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

	public void addTeamOwner(User user) {

		// control that given user is an academician or not
		if (user instanceof Academician) {

			// it tries to add the user to the team's owners.
			// if user was added before, return a message.
			boolean isAdded = this.team.getOwners().add(user);
			if (!isAdded) {
				System.out.println("User " + user.getName() + " was added before.");
			}
		} else {
			System.out.println("Only academicians can be owner.");
		}

	}

	@Override
	public void removeChannelMember(String userId, String channelName) {

		try {
			Channel temp = this.team.getMeetingChannelList().getByName(channelName);
			if (temp instanceof MeetingChannel) {
				System.out.println("Remove operation is not aplicable for channel " + channelName + " on team "
						+ this.team.getId());
			} else {
				((PrivateChannel) temp).removeParticipant(userId);
				if( ((PrivateChannel)temp).getParticipants().isEmpty())
					removeChannel(temp);
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println("There is no channel named " + channelName);
		}

	}

	/**
	 * The function tries to remove given item from the given list if it is possible
	 * If item is in the list remove it. Else return ItemNotFoundException. If
	 * removing process is not successfull, return the given error message.
	 *
	 * @param item      given item to remove from the given container
	 * @param container given container list
	 * @param error     error message to print if removing process is not
	 *                  successfull.
	 * @return
	 */
	private <T> T removeItem(IContainer<T> container, T item) {
		String removedItemToString = item.toString();
		T removedItem = null;
		try {
			removedItem = container.remove(item); // try to remove invoking container.remove method
			return removedItem;

		} catch (ItemNotFoundException e) {
			System.out.println("This item is not found in the container.");
			return null;
		}

	}

	public void removeChannel(Channel ch) {

		removeItem(this.team.getMeetingChannelList(), ch);

	}

	public void removeMember(User user) {
		User removedUser = ((User) removeItem(this.team.getMemberUsers(), user));
		if (removedUser.equals(user)) {
			removeMemberFromChannels(user);
		}
	}

	private void removeMemberFromChannels(User user) {
		for (Channel channel : team.getMeetingChannelList()) {
			boolean participantOfPrivate = (channel instanceof PrivateChannel)
					&& ((PrivateChannel) channel).isMember(user.getId());
			if (participantOfPrivate) {
				removeChannelMember(user.getId(), channel.getName());
			}
		}
	}

	public void removeTeamOwner(User user) {
		removeItem(this.team.getOwners(), user);
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void removeUsers() {
		for (User member : team.getMemberUsers()) {
			try {
				Team tempTeam = member.getTeams().remove(team);
				if (!tempTeam.equals(team))
					System.out.println("Error while removing team from user -> user " + member.toString());

			} catch (ItemNotFoundException e) {
				System.out.println(e);

			}
		}
	}

}
