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
		teamManagement = new TeamManagement(); // create teamManagement for team operations
	}

	public void addMeetingChannel(User loggedInUser, Team team) throws UnauthorizedUserOperationException {

		// Get a input from user about which type of channel want to create
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Select Meeting Channel Type : \n1-Public\n2-Private\n");
		String type = input.nextLine().strip();

		Channel tempChannel = null; // create tempChannel as null first.

		// Control the input
		if (type.equals("Public") || type.equals("Private")) {

			// Control that loggedInUser is authorized to create a public meeting channel by
			// controlling loggedInUser is a team owner of the selected team.
			if (type.equals("Public") && !team.isTeamOwner(loggedInUser.getId())) {
				throw new UnauthorizedUserOperationException("You are not authorized to create a public channel.");
			}

			// Get channel name and channel date from user
			System.out.print("Meeting Channel Name: ");
			String name = input.nextLine().strip();
			System.out.print("Meeting Channel Date 'Day HH:MM AM/PM' : ");
			String date = input.nextLine().strip();

			// try to create a new meeting/private channel with given inputs.
			try {
				Meeting meeting = new Meeting(date);
				if (type.equals("Public"))
					tempChannel = new MeetingChannel(meeting, name);

				if (type.equals("Private")) {
					tempChannel = new PrivateChannel(meeting, name);

					// add user as participant if channel is private.
					((PrivateChannel) tempChannel).addParticipant(loggedInUser.getId());
				}
				teamManagement.setTeam(team); // set teamManagement to make team operation

				// add channel to the team's channels
				if (teamManagement.addChannel(tempChannel)) {
					System.out.println(tempChannel.getName() + " is added successfully to " + team.getId());
				} else {
					System.out.println(tempChannel.getName() + " was added before ! ");
				}

			}
			// If date is in the wrong type, hold exception gotten from Meeting.parseDate()
			catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Date is in wrong format.");
			}
		} else {
			// If input is not Public or Private, print a message
			System.out.println("Wrong channel type. Please text 'Public' or 'Private' !");
		}

	}

	public void addParticipantToChannel(User loggedInUser, Team team, Channel channel)
			throws UnauthorizedUserOperationException {

		// Control loggedInUser is a team member if the selected channel
		// is a private channel
		boolean participantOfPrivate = ((PrivateChannel) channel).isMember(loggedInUser.getId());

		// If not authorized for process, throw unauthorized exception.
		if (participantOfPrivate) {
			findUsers(team.getMemberUsers()); // print users of the team.
			teamManagement.setTeam(team); // set teamManagement for team operation

			// Get user id from loggedInUser
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("User Id : ");
			String userId = input.nextLine().strip();

			// try to add the user of given id to the private channel.
			teamManagement.addMemberToChannel(userId, channel.getName());
		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add member to channel " + channel.getName() + ".");
		}
	}

	public void addTeamMember(IContainer<User> users, User loggedInUser, Team team)
			throws UnauthorizedUserOperationException {

		// Control loggedInUser is an instructor or assistant of the team.
		boolean isAcademician = (loggedInUser instanceof Academician) && team.isMember(loggedInUser.getId());

		// If not authorized for process, throw unauthorized exception
		if (isAcademician) {
			findUsers(users); // print all users in the system

			// Get an user id to add to team.
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("User ID to add : ");
			String memberId = input.nextLine().strip();

			// Try to find the user of given id, if not exists
			// holds the ItemNotFound exception in catch
			try {
				// find the user in the user container which holds all users of the system
				User user = users.getById(memberId);
				teamManagement.setTeam(team); // set teamManagement for team operation
				if (teamManagement.addMember(user)) // try to add user to the team.
				{
					user.getTeams().add(team);
					System.out.println(memberId + " added successfully.");

				} else {
					System.out.println("User " + memberId + " was added before.");
				}
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println("User " + memberId + " is not found.");
			}
		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add member to team " + team.getId());
		}

	}

	public void addTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException {

		// Control loggedInUser is not student and is a teamOwner of the team.
		boolean isTeamOwner = (loggedInUser instanceof Academician) && team.isTeamOwner(loggedInUser.getId());

		// If not authorized for process, throw unauthorized exception
		if (isTeamOwner) {
			IContainer<User> members = team.getMemberUsers();
			findUsers(members); // print members of the team

			// Get an user id to assing as team owner for team.
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("User ID to add to Owners: ");
			String memberId = input.nextLine().strip();

			// Try to find the user of given id, if not exists
			// holds the ItemNotFound exception in catch
			try {
				User user = members.getById(memberId); // find the user in the member container of the team
				teamManagement.setTeam(team); // set teamManagement for team operation

				// try to assign user as team owner.
				if (teamManagement.addTeamOwner(user)) {
					System.out.println("User " + memberId + " is assigned as owner.");
				} else {
					System.out.println("Operation is invalid");
				}
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println("The user not found in team " + team.getId());
			}
		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add team owner to team " + team.getId());
		}
	}

	public void createTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException {

		// Control if loggedInUser is authorized to create a team
		if (loggedInUser instanceof Instructor) {
			// Team Name,Team ID,Default Channel,Default Meeting Day and Time

			/*
			 * Get Team Name and Team ID from user. If user wants to modify default meeting
			 * channel, get default meeting channel name from user.
			 */
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("Team Name : ");
			String teamName = input.nextLine().strip();
			System.out.print("Team ID : ");
			String teamId = input.nextLine().strip();
			System.out.println(
					"If you want to modify default channel press Y\nOtherwise default channel's name is General and has no meeting date");
			String modify = input.next();

			Channel meetingChannel = null; // declare default meeting channel as null
			Meeting defaultMeeting = new Meeting(); // create default meeting for channel

			if (modify.equals("Y")) {
				System.out.println("Please give default channel's name: ");
				String chName = input.next();

				// Create default meeting channel with given name and meeting
				meetingChannel = new MeetingChannel(defaultMeeting, chName);
			} else {
				// If user doesn't want to modify, make meeting channel name "General"
				meetingChannel = new MeetingChannel(defaultMeeting, "General");
			}

			Team newTeam = new Team(teamName, teamId); // Create a team with given name and id.

			/*
			 * If a team which is equal to new created team doesn't exists, add new team to
			 * the team container.
			 */
			boolean isAdded = teams.add(newTeam);
			if (isAdded) {
				teamManagement.setTeam(newTeam); // set teamManagement to make team operation
				teamManagement.addChannel(meetingChannel); // add created channel to team
				teamManagement.addMember(loggedInUser); // add user to the team
				teamManagement.addTeamOwner((Academician) loggedInUser); // assign loggedInUser as teamOwner
				loggedInUser.getTeams().add(newTeam); // Update user's teams
				System.out.println("Team " + newTeam.getId() + " is added.");
			} else {
				System.out.println(newTeam.getId() + " already exists.");
			}
		} else {
			throw new UnauthorizedUserOperationException("You are not authorized to create a team.");
		}
	}

	/**
	 * The function prints all the channels of the team to help select a channel
	 * easily.
	 * 
	 * @param team : whose channels are printed.
	 */
	private void findChannel(Team team) {
		String string = "\n";

		for (Channel item : team.getMeetingChannelList()) {
			string += item.getName() + "\n";
		}

		System.out.println(string);
	}

	/**
	 * This function prints all participants of a private channel
	 * 
	 * @param tempChannel channel
	 */
	private void findParticipant(Channel tempChannel) {
		String string = "\n";

		for (String item : ((PrivateChannel) tempChannel).getParticipants()) {
			string += item + "\n";
		}

		System.out.println(string);
	}

	/**
	 * This function prints all teams in a system
	 * 
	 * @param teams teams container
	 */
	public void findTeam(IContainer<Team> teams) {
		String string = "\n";

		for (Team item : teams) {
			string += item.getName() + "-" + item.getId() + "\n";
		}
		System.out.println(string);
	}

	/**
	 * The function prints all the users of the system to help select a user easily.
	 * 
	 * @param users : is a container which holds all users in the system.
	 */
	private void findUsers(IContainer<User> users) {
		String string = "\n";

		for (User user : users) {
			string += user.getId() + "-" + user.getName() + "\n";
		}

		System.out.println(string);
	}

	public String mainOperationsMenu() {
		System.out.println("\n" + "1- Add a team\r\n" + "2- Remove a team\r\n" + "3- Update a team\r\n" + "4- Quit\n");

		System.out.print("Which operations do you want to do ( please write operation index ) : "); // print choices
		Scanner input = new Scanner(System.in);
		String mainOperationIndex = input.nextLine().strip();
		if (mainOperationIndex.equals("4")) // close scanner just a one time
			input.close();
		return mainOperationIndex; // return input
	}

	public void removeMeetingChannel(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		// print all channels of the selected team to help user select a channel easily
		if(team.getMeetingChannelList().isEmpty()) {
			System.out.println("There is no channel in team "+team.getId());
			return;
		}
		findChannel(team);

		// Get channel name from user to remove.
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("\nMeeting Channel Name to remove : ");
		String channelName = input.nextLine().strip();
		Channel tempChannel = null;
		teamManagement.setTeam(team); // set teamManagement to make team operation

		// Try to find the channel of given name using ontainer.getByNamefunction,
		// if not exists holds ItemNotFound Exception in the catch
		try {
			tempChannel = team.getMeetingChannelList().getByName(channelName);

			// Control that loggedInUser is the team owner if the selected channel
			// is a meeting channel
			boolean ownerOfMeeting = (tempChannel instanceof MeetingChannel && team.isTeamOwner(loggedInUser.getId()));

			// Control loggedInUser is a team member if the selected channel
			// is a private channel
			boolean participantOfPrivate = tempChannel instanceof PrivateChannel
					&& ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());

			if (ownerOfMeeting || participantOfPrivate) {
				teamManagement.removeChannel(tempChannel); // remove channel from team
				System.out.println(tempChannel.getName() + " is removed successfully from " + team.getId());

			} else {
				throw new UnauthorizedUserOperationException(
						// If booleans are false, throw unauthorized exception.
						"You are not authorized to remove channel " + channelName + ".");
			}

		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println("Channel " + channelName + " is not found.");
		}

	}

	public void removeParticipantFromChannel(User loggedInUser, Team team, Channel tempChannel)
			throws UnauthorizedUserOperationException {

		// Control loggedInUser is a team member if the selected channel is a private
		// channel
		boolean participantOfPrivate = ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());

		// If given id is not found, holds the ItemNotFound exception in catch
		try {

			// If user is not authorized for process, throw an unauthorized exception.
			if (participantOfPrivate) {
				findParticipant(tempChannel); // print all participants of the channel

				// Get a participant id to remove
				@SuppressWarnings("resource")
				Scanner input = new Scanner(System.in);
				System.out.print("Participant ID to remove : ");
				String participantId = input.nextLine().strip();

				// try to remove participant from channel, if participant doesn't exist,
				// throw ItemNotFound exception.
				if (((PrivateChannel) tempChannel).getParticipants().getLength() >= 2) {
					((PrivateChannel) tempChannel).getParticipants().remove(participantId);
					System.out.println(participantId + " is removed.");
				} else {
					System.out.println("You are the only person in channel " + tempChannel.getName()
							+ "\nIf you want to remove a channel, use remove a meeting channel option.");
				}
			} else {
				throw new UnauthorizedUserOperationException(
						"You are not authorized to remove channel " + tempChannel.getName() + ".");
			}
		} catch (ItemNotFoundException e) {
			System.out.println("Participant is not found.");
		}

	}

	public void removeTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException {

		// Control if loggedInUser is authorized to remove a team
		if (loggedInUser instanceof Academician) {
			Team tempTeam;

			// Get Team ID from user.
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("Team Id to remove : ");
			String teamId = input.nextLine().strip();

			// try to find the team with given id using getById funciton of team container
			try {
				tempTeam = teams.getById(teamId);

				// if team is found, control that loggedInUser is the team owner of that team
				if (tempTeam.isTeamOwner(loggedInUser.getId())) {
					teamManagement.setTeam(tempTeam); // set teamManagement to make team operation

					// remove the team from the each user's teams' container which
					// holds teams that user enrolled.
					teamManagement.removeUsers();

					// remove team from team container which
					// holds all teams of system
					Team removedTeam = teams.remove(tempTeam);

					System.out.println(removedTeam.getName() + " is deleted.");
				} else {
					throw new UnauthorizedUserOperationException(
							"You are not authorized to remove team " + tempTeam.getId() + ".");
				}
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(teamId + " is not found"); // If team of givenId is not found, print message.
			}
		} else {
			throw new UnauthorizedUserOperationException("You are not authorized to remove a team.");
		}
	}

	public void removeTeamMember(User loggedInUser, Team team) throws UnauthorizedUserOperationException {

		// Control loggedInUser is an instructor or assistant of the team.
		boolean academicianInTeam = (loggedInUser instanceof Academician) && team.isMember(loggedInUser.getId());

		// If not authorized for process, throw unauthorized exception
		if (academicianInTeam) {
			findUsers(team.getMemberUsers()); // print users of the team

			// Get an user id to remove from team.
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("Member ID to remove : ");
			String memberId = input.nextLine().strip();

			// Try to find the user of given id, if not exists
			// holds the ItemNotFound exception in catch
			try {
				// find the user in the member container of the team
				User user = team.getMemberUsers().getById(memberId);
				teamManagement.setTeam(team); // set teamManagement for team operation
				User owner = null;
				boolean flag = true;
				try {
					owner = team.getOwners().getById(memberId);
				} catch (ItemNotFoundException e) {
					flag = false;
				}
				if (team.getOwners().getLength() == 1 && flag && user.equals(owner)) {
					System.out.println("You cannot remove the last owner.");
				} else {
					teamManagement.removeMember(user); // remove user from the team.
					System.out.println("User " + user.getId() + " is removed succesfully.");
				}
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println("User " + memberId + " is not found in the team " + team.getId());
			}

		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to remove a member from " + team.getName() + ".");
		}
	}

	public void removeTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException {

		// Control loggedInUser is not student and is a teamOwner of the team.
		boolean teamOwnerInTeam = team.isTeamOwner(loggedInUser.getId());

		// If not authorized for process, throw unauthorized exception
		if (teamOwnerInTeam) {
			IContainer<User> owners = team.getOwners();
			findUsers(owners); // print owners of the team

			// Get an user id to assign as team owner for team.
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("Owner ID to remove : ");
			String ownerId = input.nextLine().strip();

			// Try to find the user of given id, if not exists
			// holds the ItemNotFound exception in catch
			try {
				User user = owners.getById(ownerId); // find the user in the owner container of the team
				teamManagement.setTeam(team); // set teamManagement for team operation
				if (team.getOwners().getLength() == 1) {
					System.out.println("You cannot remove yourself from team owner if you are the only owner.\n "
							+ "If you want to delete team, please select 'Remove a team' from menu.");
				} else {
					teamManagement.removeTeamOwner(user); // remove user from team owner.
					System.out.println("User " + user.getId() + " is removed from owners successfuly.");
				}

			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println("Owner is not found in team " + team.getId());
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
		IContainer<User> members = team.getMemberUsers(); // get members of a team
		if (!members.isEmpty()) { // if not empty
			for (User user : members) {
				if (user instanceof Student) // increase student
					students++;
				else if (user instanceof Instructor) // increase instructor
					instructors++;
				else if (user instanceof TeachingAssistant) // increase tas
					tas++;
			}
			// print infos
			System.out.println("Number of Students in team " + team.getId() + ": " + students);
			System.out.println("Number of Instructors in team " + team.getId() + ": " + instructors);
			System.out.println("Number of Teaching Assistants in team " + team.getId() + ": " + tas);
		} else
			System.out.println("Team is empty, there is no statistics to show");
	}

	public void teamInfo(Team team) {

		IContainer<Channel> channels = team.getMeetingChannelList(); // get channels

		System.out.println(team.getName() + "\n");

		for (Channel channel : channels) { // iterate through channels
			String channelInfo = null;
			String date = channel.getMeeting().getDate();
			if (date == null) {
				date = "Meeting date is not set.";
			}
			channelInfo = "Channel Name : " + channel.getName() + "\nChannel Meeting Date : " + date;// channel info

			if (channel instanceof PrivateChannel) {
				String participants = "";
				IContainer<String> participantList = ((PrivateChannel) channel).getParticipants();
				for (String participant : participantList) {
					participants += (participant + ","); // if private participants info
				}

				if (participants.endsWith(",")) { // remove last comma
					participants = participants.substring(0, participants.length() - 1);
				}

				channelInfo += ("\nParticipants : " + participants); // add participant info

			}

			System.out.println("---------------\n" + channelInfo); // print team info
		}
	}

	public void updateChannelOperation(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		if(team.getMeetingChannelList().isEmpty()) {
			System.out.println("There is no channel in team "+team.getId());
			return;
		}
		
		findChannel(team); // print all channels of the team
		
		// Get a channel name from loggedInUser.
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("\nMeeting Channel Name :");
		String channelName = input.nextLine().strip();
		Channel tempChannel = null;
		
		String operationChoice = "";
		// try to find the channel of given name, if not found
		// holds the exception of ItemNotFound
		try {
			tempChannel = team.getMeetingChannelList().getByName(channelName);
			operationChoice = updateMeetingChannelMenu(); // get the selected operation for channel
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println("Channel " + channelName + " is not found.");
			operationChoice = "4"; // exit from channel operation
		}
		// If channel is found, adjust the selected operation
		switch (operationChoice) {
		case "1": {
			// Control that if selected channel is private
			if (tempChannel instanceof PrivateChannel)
				addParticipantToChannel(loggedInUser, team, tempChannel);
			else
				System.out.println("You can not add participants to Public Channels");
			break;
		}
		case "2": {
			// Control that if selected channel is private
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

	public void updateMeetingChannelDate(User loggedInUser, Team team, Channel tempChannel)
			throws UnauthorizedUserOperationException {

		try {
			// Control that loggedInUser is the team owner if the selected channel
			// is a meeting channel
			boolean ownerOfMeeting = (tempChannel instanceof MeetingChannel && team.isTeamOwner(loggedInUser.getId()));

			// Control that loggedInUser is a team member if the selected channel
			// is a private channel
			boolean participantOfPrivate = tempChannel instanceof PrivateChannel
					&& ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());

			if (ownerOfMeeting || participantOfPrivate) {
				// Get date from loggedInUser
				@SuppressWarnings("resource") // scanner must close one time
				Scanner input = new Scanner(System.in);
				System.out.print("Meeting Channel Date 'Day HH:MM' AM/PM : ");
				String date = input.nextLine().strip();

				// try to update date. If date is in wrong type, holds the exceptions gotten
				// from Meeting object in the catch
				tempChannel.getMeeting().updateDate(date); // handle IllegalArgumentException
															// |ArrayIndexOutOfBoundsException
				System.out.println(
						"In " + tempChannel.getName() + ", date is updated as " + tempChannel.getMeeting().getDate());

			} else {
				// If booleans are false, throw unauthorized exception.
				throw new UnauthorizedUserOperationException(
						"You are not authorized to update channel " + tempChannel.getName() + ".");
			}

		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			// If the given date is in the wrong format holds the exception there.
			System.out.println("Date is in wrong type");
		}

	}

	public String updateMeetingChannelMenu() {
		System.out.println("\n" + "1- Add a participant\n" + "2- Remove a participant\n"
				+ "3- Update a meeting channel date and time\n" + "4- Go Back\n");

		System.out.print("Which operations do you want to do ( please write operation index ) : "); // print menu
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String channelOperationIndex = input.nextLine().strip();
		return channelOperationIndex;
	}

	public String updateTeamOperationsMenu() {
		System.out.println(
				"\n" + "1- Add a meeting channel\n" + "2- Remove a meeting channel\n" + "3- Update a meeting channel\n"
						+ "4- Add a member\n" + "5- Remove a member\n" + "6- Add a team owner\n"
						+ "7- Remove a team owner\n" + "8- Monitor team\n" + "9- Statictics\n" + "0- Go Back\n");

		System.out.print("Which operations do you want to do ( please write operation index ) : "); // print menu
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in); // take input
		String teamOperationIndex = input.nextLine().strip();
		return teamOperationIndex; // and return it
	}
}
