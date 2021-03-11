package team;

import channel.Channel;
import exception.ItemExistException;
import exception.ItemNotFoundException;
import storage.IContainer;
import user.Academician;
import user.User;

public class TeamManagement implements ITeamManagement {

	private Team team;

	public TeamManagement(Team team) {
		setTeam(team);
	}

	public void addChannel(Channel ch) throws ItemExistException {
		boolean isAdded = this.team.getMeetingChannelList().add(ch);
		if (!isAdded) {
			System.out.println("Channel was added before.");
		}
	}

	public void addMember(User user) throws ItemExistException {
		boolean isAdded = this.team.getMemberUsers().add(user);
		if (!isAdded) {
			System.out.println("User was added before.");
		}
	}

	public void addTeamOwner(Academician user) {
		if (user instanceof Academician) {
			boolean isAdded = this.team.getOwners().add(user);
			if (!isAdded) {
				System.out.println("User " + user.getName() + " was added before.");
			}
		} else {
			System.out.println("Only academicians can be owner.");
		}

	}

	public void removeChannel(Channel ch) {
		removeItem("Channel has not removed.", this.team.getMeetingChannelList(), ch);
	}

	private <T> void removeItem(String error, IContainer<T> container, T item) {
		String removedItemToString = item.toString();
		T removedItem;
		try {
			removedItem = container.remove(item);
			if (!removedItem.toString().equals(removedItemToString)) {
				System.out.println(error);
			}
		} catch (ItemNotFoundException e) {
			System.out.println("This item is not found in the container.");
		}
	}

	public void removeMember(User user) {
		removeItem("User has not removed.", this.team.getMemberUsers(), user);
	}

	public void removeTeamOwner(User user) {
		removeItem("Team Owner has not removed.", this.team.getOwners(), user);
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
}
