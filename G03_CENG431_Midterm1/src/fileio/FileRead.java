package fileio;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import channel.*;
import exception.FileFormatException;
import storage.*;
import team.ITeamManagement;
import team.Team;
import team.TeamManagement;
import user.*;

public class FileRead {

	protected boolean isFirstRead = true; // boolean holds the info that is the first read or not

	/**
	 * This is a helper function. If an user has not an id, it creates an unique id
	 * and sets the id to the user.
	 * 
	 * @param ids  which holds all the ids. In this way, prevent to assign an
	 *             existing id to the new user.
	 * @param user whose id is empty.
	 */
	private void checkId(IContainer<String> ids, User user) {
		user.setRandomId(); // create an id invoking createId() function of the user.
		String id = user.getId();
				
		// Control that id is in the id container or not. If id is already in the
		// container, until unique id is created repeat the creating process
		while (!ids.add(id)) {
			user.setRandomId(); // After creating an unique id, set the user id.
			id = user.getId();
		}
	}

	/**
	 * The function tries to read the given file which is in the given path. After
	 * reading, it returns a list of string list which holds all the lines as a list
	 * of splitted string attributes list.
	 * 
	 * @param filePath path of the file to read.
	 * 
	 * @return List<List<String>> which holds all the lines as a list of splitted
	 *         string attributes list.
	 * 
	 * @throws IOException if file can not be read.
	 */
	protected List<List<String>> read(String filePath) {

		List<List<String>> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				// Get the line's participantID which is in the form of "1,2,3.." as
				// a splittedstrings 1,2,3
				if (line.contains("\"")) {
					line = line.replace("\"", "");
				}

				String[] values = line.split(",", -1);
				records.add(Arrays.asList(values));
			}
			br.close();
		}

		catch (IOException e) {
			System.out.println("File can not be read at the moment");
		}

		return records;

	}

	/**
	 * The function read the given records and creates the teams. After process, it
	 * returns a container which holds team objects.
	 * 
	 * @param records list of splitted string attributes list for teams.
	 * 
	 * @return IContainer<Team> which holds team objects.
	 * @throws FileFormatException
	 */
	protected IContainer<Team> readTeams(List<List<String>> records) throws FileFormatException {

		IContainer<Team> teams = new TeamContainer(); // holds created teams

		// get the each string list in the records
		for (List<String> line : records) {

			// If there exists "TEAMSTECH" line at the end of the file, we understand that
			// this is not the first reading.
			if (line.size() < 2 && line.get(0).strip().toUpperCase(Locale.US).equals("TEAMSTECH")) {
				isFirstRead = false;
				break;
			}

			// define strings to assign the strings in the line.
			String teamName, teamId, meetingChannel, meetingDate, participantId = null;
			// assign the strings to the variables.
			teamName = line.get(0).strip();
			teamId = line.get(1).strip();

			Team team = new Team(teamName, teamId); // Create team with the attributes
			ITeamManagement teamManagement = new TeamManagement(team); // Create team management obect with the given
																		// team to do team processes.

			Meeting meeting = null; // create a meeting object as null;
			Channel tempChannel = null; // create a channel object as null;

			// When reading the teams' file, line is created in the form of :
			// Team Name,Team ID,Default Channel,Default Meeting Day and Time,
			// Meeting Channel 1 (MC1), Meeting Day and Time of MC1, MC1 PRT ID 1,
			// MC1 PRTID 2, MC1 PRT ID 3,
			// Meeting Channel 2 (MC2), Meeting Day and Time of MC2, MC2 PRT ID 1,
			// MC2 PRT ID 2, MC2 PRT ID 3,
			// The loop gets these meeting channels and their attributes.
			int n = 2;
			while (line.size() - n > 1) {
				meetingChannel = line.get(n).strip(); // get the meeting channel
				n++;
				meetingDate = line.get(n).strip(); // get the meeting channel date
				n++;

				// When reading the teams' file, line is created in the form of :
				// Team Name,Team ID,Default Channel,Default Meeting Day and Time,
				// Meeting Channel 1 (MC1), Meeting Day and Time of MC1, MC1 PRT ID 1,
				// MC1 PRT ID 2, MC1 PRT ID 3,
				// Meeting Channel 2 (MC2), Meeting Day and Time of MC2, MC2 PTR ID 1,
				// MC2 PRT ID 2, MC2 PRT ID 3,

				// After getting meeting channel and its date, while loop gets the each
				// participant's id until the gotten item is not the type of participant id
				// checkType controls that it is the id or the new meeting channel
				// For example Meeting Channel 1 (MC1), Meeting Day and Time of MC1 are taken
				// above,
				// Take the participants id MC1 PRT ID 1, MC1 PRT ID 2... until the taken item
				// is not in the type of participant id
				// if not in the type of id -> checkType(line.get(n) returns false and loop is
				// broken.
				List<String> participantList = new ArrayList<String>(); // it holds the list of participants' id
				while (((n) < line.size() && !line.get(n).equals("")) && checkType(line.get(n))) {
					participantId = line.get(n).strip();
					participantList.add(participantId);

					n++;
				}
				// if meetingChannel exists, create a new private meeting channel object
				// Control the meetingDate. If meetingDate exists, create a new meeting and
				// assign it to the channel.
				if (!meetingChannel.equals("")) {
					if (!meetingDate.equals("")) {
						try {
							meeting = new Meeting(meetingDate);
						} catch (ArrayIndexOutOfBoundsException e) {
							throw new FileFormatException("Date format is in wrong type. Please fix the file");
						}
					} else {
						meeting = new Meeting();
					}

					if (participantList.size() > 0) {// add the participants'id to the channel's participant container.
						tempChannel = new PrivateChannel(meeting, meetingChannel);
						for (String id : participantList) {
							((PrivateChannel) tempChannel).addParticipant(id);
						}
					} else {

						tempChannel = new MeetingChannel(meeting, meetingChannel);
					}
					teamManagement.addChannel(tempChannel); // add the channel to the team.

				}
			}
			teams.add(team); // add the team to the team container which holds teams.

		}
		return teams; // return team container which holds teams.
	}

	/**
	 * The function read the given records and creates the users. After process, it
	 * returns a container which holds user objects.
	 * 
	 * @param records list of splitted string attributes list for user.
	 * 
	 * @param teams   which holds teams
	 * 
	 * @return IContainer<User> which holds user objects.
	 */
	protected IContainer<User> readUsers(List<List<String>> records, IContainer<Team> teams) {
		IContainer<User> users = new UserContainer(); // create an UserContainer to hold user objects

		// create an IDContainer to holds ids.
		// after each creating of user if user has not an id, create a random id
		// and save the id in the id container to prevent to assign existing id
		// to the new user.
		IContainer<String> ids = new IdContainer();

		// get the each string list in the records
		for (List<String> line : records) {

			// If there exists "TEAMSTECH" line at the end of the file, we understand that
			// this is not the first reading.
			if (line.size() < 2 && line.get(0).strip().toUpperCase(Locale.US).equals("TEAMSTECH")) {
				isFirstRead = false;
				break;
			}

			// define strings to assign the strings in the line.
			String userType, userName, userId, email, password, teamId;

			// assign the strings to the variables.
			userType = line.get(0).strip();
			userName = line.get(1).strip();
			userId = line.get(2).strip();
			email = line.get(3).strip();
			password = line.get(4).strip();

			// According to useType, create the user object.
			User user;
			switch (userType) {
			case "Student": {
				user = new Student(userName, userId, password, email);
				break;
			}
			case "Instructor": {
				user = new Instructor(userName, userId, password, email);
				break;
			}
			case "Teaching Assistant": {
				user = new TeachingAssistant(userName, userId, password, email);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected user type value: " + userType);
			}

			// If user id is empty or exist but we have same id, create an unique id
			// and set the id to the user invoking checkId()
			if (userId.equals("") || !ids.add(userId)) {
				checkId(ids, user);
			}

			// In this loop, get the user's each team id
			// and get the team object from team container.
			// After that add the user to the team.
			// If the gotten line is in type O-TeamId ( ex : O-CENG431 )
			// we understand user is the owner of the team.
			// After that add the user to the owner of the team.
			int n = 5;
			while (((n) < line.size()) && (!line.get(n).equals(""))) {
				teamId = line.get(n); // get the team id

				// control that gotten Id indicates that user is the owner or not
				String[] splitOwner = teamId.split("-");

				// If there exists O at the start, make isOwner true
				boolean isOwner = false;
				if (splitOwner.length > 1) {
					isOwner = true;
					teamId = splitOwner[1];
				}

				try {
					Team team = teams.getById(teamId); // try to get team object

					user.getTeams().add(team); // add team to the user's team.
					ITeamManagement teamManagement = new TeamManagement(team); // set teamManagement for team operation
					teamManagement.addMember(user); // add user to the team
					
					// If isOwner is true and user is an instructor or assistant,
					// add the user to the owners of the team
					if ((userType.equals("Instructor") && isFirstRead)
							|| ((userType.equals("Instructor") || userType.equals("Teaching Assistant")) && isOwner)) {

						teamManagement.addTeamOwner((Academician) user); 

					}

				} catch (Exception e) {
					System.out.println("Team " + teamId + " is not found while reading file." + e);
				}
				n++;

			}
			users.add(user); // add the user to the user container.

		}
		return users; // return the user container
	}

	/**
	 * It is a helper function to differenciate that the gotten string in the line
	 * is a participant Id or the new meeting channel
	 * 
	 * @param value : string gotten from the line
	 * @return true if the value is a participant id.
	 */
	private boolean checkType(String value) {

		String temp = value.strip();
		try {
			boolean result = Integer.valueOf(temp) instanceof Integer;
			return result;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
