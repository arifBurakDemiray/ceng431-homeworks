package program;

import user.Academician;
import user.Instructor;
import user.User;
import user.Student;
import user.TeachingAssistant;
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
		String string = "\n";

		for (Team item : teams) {
			string += item.getName() + "-" + item.getId() + "\n";
		}
		System.out.println(string);
	}

	public void addMeetingChannel(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		Scanner input = new Scanner(System.in);
		System.out.print("Select Meeting Channel Type : \n1-Public\n2-Private");
		String type = input.nextLine().strip();
		Channel tempChannel = null;
		if (type.equals("Public") || type.equals("Private")) {
			if (type.equals("Public") && !team.isTeamOwner(loggedInUser.getId())) {
				throw new UnauthorizedUserOperationException("You are not authorized to create a public channel.");
			}

			System.out.print("Meeting Channel Name: ");
			String name = input.nextLine().strip();
			System.out.print("Meeting Channel Date 'Day HH:MM AM/PM' : ");
			String date = input.nextLine().strip();

			try {
				Meeting meeting = new Meeting(date);
				if (type.equals("Public"))
					tempChannel = new MeetingChannel(meeting, name);

				if (type.equals("Private")) {
					tempChannel = new PrivateChannel(meeting, name);
					((PrivateChannel) tempChannel).addParticipant(loggedInUser.getId());
				}
				teamManagement.setTeam(team);
				teamManagement.addChannel(tempChannel);
				System.out.println(tempChannel.getName() + " is added successfully to " + team.getId());
			} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Date is in wrong format.");
			}
		} else {
			System.out.println("Wrong input");
		}

	}

	private void findChannel(Team team) {
		String string = "\n";

		for (Channel item : team.getMeetingChannelList()) {
			string += item.getName() + "\n";
		}

		System.out.println(string);
	}

	private void findUsers(IContainer<User> users) {
		String string = "\n";

		for (User user : users) {
			string += user.getId() + "-" + user.getName() + "\n";
		}

		System.out.println(string);
	}

	public void removeMeetingChannel(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		findChannel(team);
		Scanner input = new Scanner(System.in);
		System.out.print("\nMeeting Channel Name to remove : ");
		String channelName = input.nextLine().strip();
		Channel tempChannel = null;
		teamManagement.setTeam(team);
		try {
			tempChannel = team.getMeetingChannelList().getByName(channelName);
			boolean ownerOfMeeting = (tempChannel instanceof MeetingChannel && team.isTeamOwner(loggedInUser.getId()));
			boolean participantOfPrivate = tempChannel instanceof PrivateChannel
					&& ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());
			if (ownerOfMeeting || participantOfPrivate) {
				teamManagement.removeChannel(tempChannel);
				System.out.println(tempChannel.getName() + " is removed successfully from " + team.getId());

			} else {
				throw new UnauthorizedUserOperationException(
						"You are not authorized to remove channel " + channelName + ".");
			}

		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void updateMeetingChannelDate(User loggedInUser, Team team, Channel tempChannel)
			throws UnauthorizedUserOperationException {

		try {
			boolean ownerOfMeeting = (tempChannel instanceof MeetingChannel && team.isTeamOwner(loggedInUser.getId()));
			boolean participantOfPrivate = tempChannel instanceof PrivateChannel
					&& ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());

			if (ownerOfMeeting || participantOfPrivate) {
				Scanner input = new Scanner(System.in);
				System.out.print("Meeting Channel Date 'Day HH:MM' AM/PM : ");
				String date = input.nextLine().strip();
				tempChannel.getMeeting().updateDate(date); // handle IllegalArgumentException
															// |ArrayIndexOutOfBoundsException
				System.out.println(
						"In " + tempChannel.getName() + " date is updated as " + tempChannel.getMeeting().getDate());

			} else {
				throw new UnauthorizedUserOperationException(
						"You are not authorized to remove channel " + tempChannel.getName() + ".");
			}

		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}

	}

	public String mainOperationsMenu() {
		System.out.println("\n" + "1- Add a team\r\n" + "2- Remove a team\r\n" + "3- Update a team\r\n" + "4- Quit\n");

		System.out.print("Which operations do you want to do ( please write operation index ) : ");
		Scanner input = new Scanner(System.in);
		String mainOperationIndex = input.nextLine().strip();
		return mainOperationIndex;
	}

	public String updateTeamOperationsMenu() {
		// Monitoring each team’s meeting channels, meeting channels’ meeting time and
		// participants (if the channel is private)
		// Finding number of distinct students, instructors and teaching assistants.
		System.out.println(
				"\n" + "1- Add a meeting channel\n" + "2- Remove a meeting channel\n" + "3- Update a meeting channel\n"
						+ "4- Add a member\n" + "5- Remove a member\n" + "6- Add a team owner\n"
						+ "7- Remove a team owner\n" + "8- Monitor team\n" + "9- Statictics\n" + "0- Go Back\n");

		System.out.print("Which operations do you want to do ( please write operation index ) : ");
		Scanner input = new Scanner(System.in);
		String teamOperationIndex = input.nextLine().strip();
		return teamOperationIndex;
	}

	public String updateMeetingChannelMenu() {
		System.out.println("\n" + "1- Add a participant\n" + "2- Remove a participant\n"
				+ "3- Update a meeting channel date and time\n" + "4- Go Back\n");

		System.out.print("Which operations do you want to do ( please write operation index ) : ");
		Scanner input = new Scanner(System.in);
		String teamOperationIndex = input.nextLine().strip();
		return teamOperationIndex;
	}

	public void updateChannelOperation(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		String operationChoice = updateMeetingChannelMenu();
		findChannel(team);
		Scanner input = new Scanner(System.in);
		System.out.print("\nMeeting Channel Name to update date :");
		String channelName = input.nextLine().strip();
		Channel tempChannel = null;
		try {
			tempChannel = team.getMeetingChannelList().getByName(channelName);
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
			operationChoice = "4";
		}
		switch (operationChoice) {
		case "1": {
			if (tempChannel instanceof PrivateChannel)
				addParticipantToChannel(loggedInUser, team, tempChannel);
			else
				System.out.println("You can not add participants to Public Channels");
			break;
		}
		case "2": {
			if (tempChannel instanceof PrivateChannel) {

				removeParticipantFromChannel(loggedInUser, team, tempChannel);

			} else {
				System.out.println("You cannot remove a participant from public channel");
			}

			break;
		}
		case "3": {
			updateMeetingChannelDate(loggedInUser, team, tempChannel);
			break;
		}
		case "4": {

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + operationChoice);
		}
	}

	public void addParticipantToChannel(User loggedInUser, Team team, Channel channel)
			throws UnauthorizedUserOperationException {

		boolean participantOfPrivate = ((PrivateChannel) channel).isMember(loggedInUser.getId());

		if (participantOfPrivate) {
			findUsers(team.getMemberUsers());
			ITeamManagement teamManagement = new TeamManagement(team);
			Scanner input = new Scanner(System.in);
			System.out.print("User Id : ");
			String userId = input.nextLine().strip();
			teamManagement.addMemberToChannel(userId, channel.getName());
		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add member to channel " + channel.getName() + ".");
		}
	}

	private void findParticipant(Channel tempChannel) {
		String string = "\n";

		for (String item : ((PrivateChannel) tempChannel).getParticipants()) {
			string += item + "\n";
		}

		System.out.println(string);
	}

	public void removeParticipantFromChannel(User loggedInUser, Team team, Channel tempChannel)
			throws UnauthorizedUserOperationException {

		boolean participantOfPrivate = ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());
		try {
			if (participantOfPrivate) {
				findParticipant(tempChannel);
				Scanner input = new Scanner(System.in);
				System.out.print("Participant ID to remove : ");
				String participantId = input.nextLine().strip();
				((PrivateChannel) tempChannel).getParticipants().remove(participantId);
			} else {
				throw new UnauthorizedUserOperationException(
						"You are not authorized to remove channel " + tempChannel.getName() + ".");
			}
		} catch (ItemNotFoundException e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

	public void addTeamMember(IContainer<User> users, User loggedInUser, Team team)
			throws UnauthorizedUserOperationException {

		boolean isAcademician = (loggedInUser instanceof Academician) && team.isMember(loggedInUser.getId());

		if (isAcademician) {
			findUsers(users);
			Scanner input = new Scanner(System.in);
			System.out.print("User ID to add : ");
			String memberId = input.nextLine().strip();
			try {
				User user = users.getById(memberId);
				ITeamManagement teamManagement = new TeamManagement(team);
				teamManagement.addMember(user);
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(e.getMessage());
			}
		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add member to team " + team.getId());
		}

	}

	public void removeTeamMember(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		boolean academicianInTeam = (loggedInUser instanceof Academician) && team.isMember(loggedInUser.getId());
		if (academicianInTeam) {
			findUsers(team.getMemberUsers());
			Scanner input = new Scanner(System.in);
			System.out.print("Member ID to remove : ");
			String memberId = input.nextLine().strip();

			try {
				User user = team.getMemberUsers().getById(memberId);
				teamManagement.removeMember(user);

			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(e.getMessage());
			}

		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to remove a member form " + team.getName() + ".");
		}
	}

	public void addTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		boolean isTeamOwner = (loggedInUser instanceof Academician) && team.isTeamOwner(loggedInUser.getId());
		if (isTeamOwner) {
			IContainer<User> members = team.getMemberUsers();
			findUsers(members);
			Scanner input = new Scanner(System.in);
			System.out.print("User ID to add to Owners: ");
			String memberId = input.nextLine().strip();
			try {
				User user = members.getById(memberId);
				ITeamManagement teamManagement = new TeamManagement(team);
				teamManagement.addTeamOwner(user);
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(e.getMessage());
			}

		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add team owner to team " + team.getId());
		}
	}

	public void removeTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException {

		boolean teamOwnerInTeam = team.isTeamOwner(loggedInUser.getId());
		if (teamOwnerInTeam) {
			IContainer<User> owners = team.getOwners();
			findUsers(owners);
			Scanner input = new Scanner(System.in);
			System.out.print("Owner ID to remove : ");
			String ownerId = input.nextLine().strip();

			try {
				User user = owners.getById(ownerId);
				teamManagement.removeTeamOwner(user);

			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(e.getMessage());
			}

		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to remove a member form " + team.getName() + ".");
		}
	}

	public void statisticOfTeam(Team team) {
		int students = 0;
		int instructors = 0;
		int tas = 0;
		IContainer<User> members = team.getMemberUsers();
		if (!members.isEmpty()) {
			for (User user : members) {
				if (user instanceof Student)
					students++;
				else if (user instanceof Instructor)
					instructors++;
				else if (user instanceof TeachingAssistant)
					tas++;
			}
			System.out.println("Number of Students in team "+team.getId()+": "+students);
			System.out.println("Number of Instructors in team "+team.getId()+": "+instructors);
			System.out.println("Number of Teaching Assistants in team "+team.getId()+": "+tas);
		} else
			System.out.println("Team is empty, there is no statistics to show");
	}

	public void teamInfo(Team team)
	{
		IContainer<Channel> channels = team.getMeetingChannelList();
		
		for (Channel channel : channels) {
			String channelInfo = null;
			channelInfo = "Channel Name : " + channel.getName() +
					"\nChannel Meeting Date : " + channel.getMeeting().getDate();
			
			if(channel instanceof PrivateChannel)
			{
				String participants = "";
				IContainer<String> participantList = ((PrivateChannel) channel).getParticipants();
				for (String participant : participantList) {
						participants += (participant +",");					
				}
				
				if (participants.endsWith(",")) {
					participants = participants.substring(0, participants.length() - 1);
				}
				
				channelInfo += ("\nParticipants : " + participants);
				
			}
			
			System.out.println("---------------\n"+channelInfo);
		}
	}

}
