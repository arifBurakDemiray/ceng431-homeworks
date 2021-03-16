package program;

import user.Instructor;
import user.User;

import java.util.Scanner;

import channel.Channel;
import channel.Meeting;
import channel.MeetingChannel;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserOperationException;
import storage.IContainer;
import team.ITeamManagement;
import team.Team;
import team.TeamManagement;

public class Operations {
	private ITeamManagement teamManagement;

	public Operations() {
		teamManagement = new TeamManagement();
	}

	public void createTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException {
		if (loggedInUser instanceof Instructor) {
			// Team Name,Team ID,Default Channel,Default Meeting Day and Time
			Scanner input = new Scanner(System.in);
			System.out.print("Team Name : ");
			String teamName = input.nextLine().strip();
			System.out.print("Team ID : ");
			String teamId = input.nextLine().strip();
			System.out.println(
					"If you want to modify default channel press Y\nOtherwise default channel's name is General and has no meeting date");
			String modify = input.next();
			Channel meetingChannel = null;
			if (modify.equals("Y")) {
				System.out.println("Please give default channel's name: ");
				String chName = input.next();
				Meeting defaultMeeting = new Meeting();
				meetingChannel = new MeetingChannel(defaultMeeting, chName);
			}
			//input.close();
			Team newTeam = new Team(teamName, teamId);
			boolean isAdded = teams.add(newTeam);
			if (isAdded) {
				teamManagement.setTeam(newTeam);
				teamManagement.addChannel(meetingChannel);
				System.out.println("Team is added.");
			} else {
				System.out.println("Team exists.");
			}
		} else {
			throw new UnauthorizedUserOperationException("You are not authorized to create a team.");
		}
	}

	public void removeTeam(User loggedInUser, IContainer<Team> teams)
			throws UnauthorizedUserOperationException {
		Team tempTeam;
		Scanner input = new Scanner(System.in);
		System.out.print("Team Id to remove : ");
		
		String teamId = input.nextLine().strip();
		input.close();
		try {
			tempTeam = teams.getById(teamId);

			if (tempTeam.isTeamOwner(loggedInUser.getId())) {

				Team removedTeam = teams.remove(tempTeam);

				System.out.println(removedTeam.getName()+" is deleted.");
			} else {
				throw new UnauthorizedUserOperationException("You are not authorized to remove a team.");
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println("Team not found");
		}
	}
	
	public void findTeam(IContainer<Team> teams) {
		String string = "";
		
		for (Team item : teams) {
			string += "1 - "+ item.toString() + "\n";
		}
		if (string.endsWith(",")) {
			string = string.substring(0, string.length() - 1);
		}
		System.out.println(string.toString());
	}
	
	
	public void updateTeam() {
		
	}
}
