package program;

import user.Academician;
import user.Instructor;
import user.User;

import java.util.Scanner;

import channel.Channel;
import channel.Meeting;
import channel.MeetingChannel;
import channel.PrivateChannel;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserOperationException;
import storage.IContainer;
import team.ITeamManagement;
import team.Team;
import team.TeamManagement;


public class Operations implements IOperations {
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
			Meeting defaultMeeting = new Meeting();
			if (modify.equals("Y")) {
				System.out.println("Please give default channel's name: ");
				String chName = input.next();
				meetingChannel = new MeetingChannel(defaultMeeting, chName);
			} else {
				meetingChannel = new MeetingChannel(defaultMeeting, "General");
			}
			// input.close();
			Team newTeam = new Team(teamName, teamId);
			boolean isAdded = teams.add(newTeam);
			if (isAdded) {
				teamManagement.setTeam(newTeam);
				teamManagement.addChannel(meetingChannel);
				teamManagement.addTeamOwner((Academician) loggedInUser);
				loggedInUser.getTeams().add(newTeam);
				System.out.println("Team is added.");
			} else {
				System.out.println("Team exists.");
			}
		} else {
			throw new UnauthorizedUserOperationException("You are not authorized to create a team.");
		}
	}

	public void removeTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException {
		if (loggedInUser instanceof Academician) {
			Team tempTeam;
			Scanner input = new Scanner(System.in);
			System.out.print("Team Id to remove : ");

			String teamId = input.nextLine().strip();
			try {
				tempTeam = teams.getById(teamId);

				if (tempTeam.isTeamOwner(loggedInUser.getId())) {
					teamManagement.setTeam(tempTeam);
					teamManagement.removeUsers();

					Team removedTeam = teams.remove(tempTeam);

					System.out.println(removedTeam.getName() + " is deleted.");
				} else {
					throw new UnauthorizedUserOperationException(
							"You are not authorized to remove team " + tempTeam.getId() + ".");
				}
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println("Team not found");
			}
		} else {
			throw new UnauthorizedUserOperationException("You are not authorized to remove a team.");
		}
	}

	public void findTeam(IContainer<Team> teams) {
		String string = "";

		for (Team item : teams) {
			string += item.toString() + "\n";
		}
		if (string.endsWith(",")) {
			string = string.substring(0, string.length() - 1);
		}
		System.out.println(string.toString());
	}
	
	public void addMeetingChannel(User loggedInUser,Team team) throws UnauthorizedUserOperationException
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Select Meeting Channel Type : \n1-Public\n2-Private");
		String type = input.nextLine().strip();
		Channel tempChannel = null;
		if(type.equals("Public") || type.equals("Private"))
		{
			if(type.equals("Public") && !team.isTeamOwner(loggedInUser.getId()))
			{
				throw new UnauthorizedUserOperationException("You are not authorized to create a public channel.");
			}
				
			
			System.out.print("Meeting Channel Name: ");
			String name = input.nextLine().strip();
			System.out.print("Meeting Channel Date 'Day HH:MM' : ");
			String date = input.nextLine().strip();
			
			try {
				 Meeting meeting  = new Meeting(date);
				 if(type.equals("Public"))
					 tempChannel = new MeetingChannel(meeting,name);
				
				 if(type.equals("Private")){
					 tempChannel = new PrivateChannel(meeting,name);
					 ((PrivateChannel)tempChannel).addParticipant(loggedInUser.getId());	
						}
				 teamManagement.setTeam(team);
				 teamManagement.addChannel(tempChannel);
			} catch (IllegalArgumentException|ArrayIndexOutOfBoundsException e) {
				System.out.println("Date is in wrong format.");
			}
		}
		else
		{
			System.out.println("Wrong input");
		}
		
	}

	public String mainOperationsMenu() {
		System.out.println("" + "1- Add a team\r\n" + "2- Remove a team\r\n" + "3- Update a team\r\n" + "4- Quit");

		System.out.print("Which operations do you want to do ( please write operation index ) : ");
		Scanner input = new Scanner(System.in);
		String mainOperationIndex = input.nextLine().strip();
		return mainOperationIndex;
	}

	public String updateTeamOperationsMenu() {
		System.out.println("" + "1- Add a meeting channel\r\n" + "2- Remove a meeting channel\r\n"
				+ "3- Update a meeting channel\r\n" + "4- Add a member\r\n" + "5- Remove a member\r\n"
				+ "6- Add a team owner\r\n" + "7- Monitor team\r\n" + "8- Statictics\r\n" + "9- Go Back\r\n");

		System.out.print("Which operations do you want to do ( please write operation index ) : ");
		Scanner input = new Scanner(System.in);
		String teamOperationIndex = input.nextLine().strip();
		return teamOperationIndex;
	}

	

}
