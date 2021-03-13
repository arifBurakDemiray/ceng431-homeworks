package team;

import channel.Channel;
import exception.ItemExistException;
import exception.ItemNotFoundException;
import exception.UnauthorizedUserOperationException;
import storage.IContainer;
import user.Academician;
import user.User;

public class TeamManagement implements ITeamManagement {

	private Team team;

	/**
	 * The Constructor creates a team management object with given team object.
	 *
	 * @param team given team to manage it
	 */
	public TeamManagement(Team team) {
		setTeam(team);
	}

	/**
	 * The function tries to add given channel to the team channels if it is
	 * possible
	 *
	 * @param ch given channel to add to the team's channels
	 */
	public void addChannel(Channel ch) throws ItemExistException {
		// it tries to add the channel to the team's channels.
		// if channel was added before, return a message.
		boolean isAdded = this.team.getMeetingChannelList().add(ch);
		if (!isAdded) {
			System.out.println("Channel was added before.");
		}
	}

	/**
	 * The function tries to add given user to the team users if it is possible
	 *
	 * @param user given user to add to the team's users
	 */
	public void addMember(User user) throws ItemExistException, UnauthorizedUserOperationException {
		// it tries to add the user to the team's users.
		// if user was added before, return a message.
		boolean isAdded = this.team.getMemberUsers().add(user);
		if (!isAdded) {
			System.out.println("User was added before.");
		}
	}

	/**
	 * The function tries to add given academician to the team owners if it is
	 * possible
	 *
	 * @param user given user to add to the team's owners
	 */
	public void addTeamOwner(Academician user) throws UnauthorizedUserOperationException {

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

	/**
	 * The function tries to remove given channel from the team channels if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param ch given channel to remove from the team's channels
	 */
	public void removeChannel(Channel ch) {
		removeItem("Channel has not removed.", this.team.getMeetingChannelList(), ch);
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
	 */
	private <T> void removeItem(String error, IContainer<T> container, T item) {
		String removedItemToString = item.toString();
		T removedItem;
		try {
			removedItem = container.remove(item); // try to remove invoking container.remove method
			if (!removedItem.toString().equals(removedItemToString)) {
				System.out.println(error);
			}
		} catch (ItemNotFoundException e) {
			System.out.println("This item is not found in the container.");
		}
	}

	/**
	 * The function tries to remove given user from the team users if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param user given user to remove from the team's user
	 */
	public void removeMember(User user) throws UnauthorizedUserOperationException {
		removeItem("User has not removed.", this.team.getMemberUsers(), user);
	}

	/**
	 * The function tries to remove given user from the team owners if it is
	 * possible, invoking removeItem method.
	 * 
	 * @param user given user to remove from the team's owners
	 */
	public void removeTeamOwner(User user) throws UnauthorizedUserOperationException {
		removeItem("Team Owner has not removed.", this.team.getOwners(), user);
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
