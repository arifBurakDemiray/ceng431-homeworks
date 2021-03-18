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

		// Control if loggedInUser is authorized to create a team
		if (loggedInUser instanceof Instructor) {
			// Team Name,Team ID,Default Channel,Default Meeting Day and Time

			/*
			 * Get Team Name and Team ID from user. If user wants to modify default meeting
			 * channel, get default meeting channel name from user.
			 */
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
				teamManagement.addTeamOwner((Academician) loggedInUser); // assign loggedInUser as teamOwner
				loggedInUser.getTeams().add(newTeam); // Update user's teams
				System.out.println("Team is added.");
			} else {
				System.out.println("Team exists.");
			}
		} else {
			throw new UnauthorizedUserOperationException("You are not authorized to create a team.");
		}
	}

	public void removeTeam(User loggedInUser, IContainer<Team> teams) throws UnauthorizedUserOperationException {

		// Control if loggedInUser is authorized to remove a team
		if (loggedInUser instanceof Academician) {
			Team tempTeam;

			// Get Team ID from user.
			Scanner input = new Scanner(System.in);
			System.out.print("Team Id to remove : ");
			String teamId = input.nextLine().strip();

			// try to find the team with given id using getById funciton of team container
			try {
				tempTeam = teams.getById(teamId);

				// if team is found, control that loggedInUser is the team owner of that team
				if (tempTeam.isTeamOwner(loggedInUser.getId())) {
					teamManagement.setTeam(tempTeam); // set teamManagement to make team operation
					teamManagement.removeUsers(); // remove the team from the each user's teams' container which holds
													// teams that user enrolled.
					Team removedTeam = teams.remove(tempTeam); // remove team from team container which holds all teams
																// of system
					System.out.println(removedTeam.getName() + " is deleted.");
				} else {
					throw new UnauthorizedUserOperationException(
							"You are not authorized to remove team " + tempTeam.getId() + ".");
				}
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println("Team not found"); // If team of givenId is not found, print message.
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

		// Get a input from user about which type of channel want to create
		Scanner input = new Scanner(System.in);
		System.out.print("Select Meeting Channel Type : \n1-Public\n2-Private\n");
		String type = input.nextLine().strip();

		Channel tempChannel = null;

		// Control the input
		if (type.equals("Public") || type.equals("Private")) {

			// Control that loggedInUser is authorized to create a public meeting channel by
			// controlling loggedInUser is a team owner of the selected team.
			if (type.equals("Public") && !team.isTeamOwner(loggedInUser.getId())) {
				throw new UnauthorizedUserOperationException("You are not authorized to create a public channel.");
			}

			// Get channel name and chanel date from user
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
					((PrivateChannel) tempChannel).addParticipant(loggedInUser.getId()); // add user as participant if
																							// channel is private.
				}
				teamManagement.setTeam(team); // set teamManagement to make team operation
				teamManagement.addChannel(tempChannel); // add channel to the team's channels
				System.out.println(tempChannel.getName() + " is added successfully to " + team.getId());
			}
			// If date is in the wrong type, hold exception gotten from Meeting.parseDate()
			catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Date is in wrong format.");
			}
		} else {
			System.out.println("Wrong input"); // If input is not Public or Private, print a message
		}

	}

	/*
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

	/*
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

	public void removeMeetingChannel(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		// print all channels of the selected team to help user select a channel easily
		findChannel(team);

		// Get channel naöe from user to remove.
		Scanner input = new Scanner(System.in);
		System.out.print("\nMeeting Channel Name to remove : ");
		String channelName = input.nextLine().strip();
		Channel tempChannel = null;
		teamManagement.setTeam(team); // set teamManagement to make team operation

		// try to find the channel of given name using ontainer.getByNamefunction, if
		// not exists holds item not found exception in the catch
		try {
			tempChannel = team.getMeetingChannelList().getByName(channelName);

			// Control that loggedInUser is the team owner if the selected channel is a meeting channel
			boolean ownerOfMeeting = (tempChannel instanceof MeetingChannel && team.isTeamOwner(loggedInUser.getId()));

			// Control loggedInUser is a team member if the selected channel is a private channel
			boolean participantOfPrivate = tempChannel instanceof PrivateChannel
					&& ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());

			if (ownerOfMeeting || participantOfPrivate) {
				teamManagement.removeChannel(tempChannel); // remove channel from team
				System.out.println(tempChannel.getName() + " is removed successfully from " + team.getId());

			} else {
				throw new UnauthorizedUserOperationException(
						"You are not authorized to remove channel " + channelName + "."); // if booleans are false,
																							// throw unauthorized
																							// exception.
			}

		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void updateMeetingChannelDate(User loggedInUser, Team team, Channel tempChannel)
			throws UnauthorizedUserOperationException {

		try {
			// Control that loggedInUser is the team owner if the selected channel is a meeting channel
			boolean ownerOfMeeting = (tempChannel instanceof MeetingChannel && team.isTeamOwner(loggedInUser.getId()));
			
			// Control that loggedInUser is a team member if the selected channel is a private channel
			boolean participantOfPrivate = tempChannel instanceof PrivateChannel
					&& ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());

			if (ownerOfMeeting || participantOfPrivate) {
				//Get date from loggedInUser
				Scanner input = new Scanner(System.in);
				System.out.print("Meeting Channel Date 'Day HH:MM' AM/PM : ");
				String date = input.nextLine().strip();
				
				//try to update date. If date is in wrong type, holds the exceptions gotten from Meeting object in the catch
				tempChannel.getMeeting().updateDate(date); // handle IllegalArgumentException
															// |ArrayIndexOutOfBoundsException
				System.out.println(
						"In " + tempChannel.getName() + " date is updated as " + tempChannel.getMeeting().getDate());

			} else {
				throw new UnauthorizedUserOperationException(
						"You are not authorized to remove channel " + tempChannel.getName() + "."); // if booleans are false, throw unauthorized exception
			}

		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			System.out.println("Date is in wrong type"); // if given date is in the wrong format holds the ecception there.
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
		String operationChoice = updateMeetingChannelMenu(); // get the selected operation for channel
		findChannel(team); // print all channels of the team
		
		//Get a channel name from loggedInUser.
		Scanner input = new Scanner(System.in);
		System.out.print("\nMeeting Channel Name to update date :");
		String channelName = input.nextLine().strip();
		Channel tempChannel = null;
		
		//try to find the channel of given name, if not found, holds the exception of ItemNotFound
		try {
			tempChannel = team.getMeetingChannelList().getByName(channelName);
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
			operationChoice = "4"; // exit from channel operation
		}
		
		//If channel is found, adjust the selected operation 
		switch (operationChoice) {
		case "1": {
			//Control that if selected channel is private
			if (tempChannel instanceof PrivateChannel)
				addParticipantToChannel(loggedInUser, team, tempChannel);
			else
				System.out.println("You can not add participants to Public Channels");
			break;
		}
		case "2": {
			//Control that if selected channel is private
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
		
		// Control loggedInUser is a team member if the selected channel is a private channel
		boolean participantOfPrivate = ((PrivateChannel) channel).isMember(loggedInUser.getId());
		
		//If not authorized for process, throw unauthorized exception.
		if (participantOfPrivate) {
			findUsers(team.getMemberUsers()); //print users of the team.
			teamManagement.setTeam(team); // set teamManagement for team operation
			
			//Get user id from loggedInUser
			Scanner input = new Scanner(System.in);
			System.out.print("User Id : ");
			String userId = input.nextLine().strip();
			
			//try to add the user of given id to the private channel.
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
		
		// Control loggedInUser is a team member if the selected channel is a private channel
		boolean participantOfPrivate = ((PrivateChannel) tempChannel).isMember(loggedInUser.getId());
		
		
		// If given id is not found, holds the ItemNotFound exception in catch
		try {
			
			//If user is not authorized for process, throw an unauthorized exception.
			if (participantOfPrivate) {
				findParticipant(tempChannel); // print all participants of the channel
				
				//Get a participant id to remove
				Scanner input = new Scanner(System.in);
				System.out.print("Participant ID to remove : ");
				String participantId = input.nextLine().strip();
				
				// try to remove participant from channel, if participant doesn't exist, throw ItemNotFound exception.
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
		
		// Control loggedInUser is an instructor or assistant of the team.
		boolean isAcademician = (loggedInUser instanceof Academician) && team.isMember(loggedInUser.getId());
		
		//If not authorized for process, throw unauthorized exception
		if (isAcademician) {
			findUsers(users); // print all users in the system 
			
			//Get an user id to add to team.
			Scanner input = new Scanner(System.in);
			System.out.print("User ID to add : ");
			String memberId = input.nextLine().strip();
			
			//Try to find the user of given id, if not exists holds the ItemNotFound exception in catch
			try {
				User user = users.getById(memberId); // find the user in the user container which holds all users of the system
				teamManagement.setTeam(team); // set teamManagement for team operation
				teamManagement.addMember(user); // add user to the team.
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(e.getMessage());
			}
		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add member to team " + team.getId());
		}

	}

	public void removeTeamMember(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		
		// Control loggedInUser is an instructor or assistant of the team.
		boolean academicianInTeam = (loggedInUser instanceof Academician) && team.isMember(loggedInUser.getId());
		
		//If not authorized for process, throw unauthorized exception
		if (academicianInTeam) {
			findUsers(team.getMemberUsers()); // print users of the team
			
			//Get an user id to remove from team.
			Scanner input = new Scanner(System.in);
			System.out.print("Member ID to remove : ");
			String memberId = input.nextLine().strip();
			
			//Try to find the user of given id, if not exists holds the ItemNotFound exception in catch
			try {
				User user = team.getMemberUsers().getById(memberId); // find the user in the member container of the team 
				teamManagement.setTeam(team); // set teamManagement for team operation
				teamManagement.removeMember(user); // remove user from the team.

			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(e.getMessage());
			}

		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to remove a member form " + team.getName() + ".");
		}
	}

	public void addTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		
		// Control loggedInUser is not student and is a teamOwner of the team.
		boolean isTeamOwner = (loggedInUser instanceof Academician) && team.isTeamOwner(loggedInUser.getId());
		
		//If not authorized for process, throw unauthorized exception
		if (isTeamOwner) {
			IContainer<User> members = team.getMemberUsers();
			findUsers(members); // print members of the team
			
			//Get an user id to assing as team owner for team.
			Scanner input = new Scanner(System.in);
			System.out.print("User ID to add to Owners: ");
			String memberId = input.nextLine().strip();
			
			//Try to find the user of given id, if not exists holds the ItemNotFound exception in catch
			try {
				User user = members.getById(memberId); // find the user in the member container of the team 
				teamManagement.setTeam(team); // set teamManagement for team operation
				teamManagement.addTeamOwner(user); // assign user as team owner.
			} catch (ItemNotFoundException | NotSupportedException e) {
				System.out.println(e.getMessage());
			}

		} else {
			throw new UnauthorizedUserOperationException(
					"You are not authorized to add team owner to team " + team.getId());
		}
	}

	public void removeTeamOwner(User loggedInUser, Team team) throws UnauthorizedUserOperationException {
		
		// Control loggedInUser is not student and is a teamOwner of the team.
		boolean teamOwnerInTeam = team.isTeamOwner(loggedInUser.getId());
		
		//If not authorized for process, throw unauthorized exception
		if (teamOwnerInTeam) {
			IContainer<User> owners = team.getOwners();
			findUsers(owners); // print owners of the team
			
			//Get an user id to assing as team owner for team.
			Scanner input = new Scanner(System.in);
			System.out.print("Owner ID to remove : ");
			String ownerId = input.nextLine().strip();
			
			//Try to find the user of given id, if not exists holds the ItemNotFound exception in catch
			try {
				User user = owners.getById(ownerId); // find the user  in the owner container of the team 
				teamManagement.setTeam(team); // set teamManagement for team operation
				teamManagement.removeTeamOwner(user); // remove user from team owner.

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
			System.out.println("Number of Students in team " + team.getId() + ": " + students);
			System.out.println("Number of Instructors in team " + team.getId() + ": " + instructors);
			System.out.println("Number of Teaching Assistants in team " + team.getId() + ": " + tas);
		} else
			System.out.println("Team is empty, there is no statistics to show");
	}

	public void teamInfo(Team team) {
		IContainer<Channel> channels = team.getMeetingChannelList();

		for (Channel channel : channels) {
			String channelInfo = null;
			channelInfo = "Channel Name : " + channel.getName() + "\nChannel Meeting Date : "
					+ channel.getMeeting().getDate();

			if (channel instanceof PrivateChannel) {
				String participants = "";
				IContainer<String> participantList = ((PrivateChannel) channel).getParticipants();
				for (String participant : participantList) {
					participants += (participant + ",");
				}

				if (participants.endsWith(",")) {
					participants = participants.substring(0, participants.length() - 1);
				}

				channelInfo += ("\nParticipants : " + participants);

			}

			System.out.println("---------------\n" + channelInfo);
		}
	}

}
