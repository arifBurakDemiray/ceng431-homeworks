package fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import channel.*;
import exception.ItemExistException;
import storage.*;
import team.ITeamManagement;
import team.Team;
import team.TeamManagement;
import user.*;

public class FileRead {

	public List<List<String>> read(String filePath) throws IOException {

		List<List<String>> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				if(line.contains("\"")) {
					line = line.replace("\"", "");
					System.out.println(line);
				}
				String[] values = line.split(",", -1);

				records.add(Arrays.asList(values));
			}
			br.close();
		}

		return records;

	}

	public IContainer<Team> readTeams(List<List<String>> records) throws ItemExistException, ParseException {
		
		
		IContainer<Team> teams = new TeamContainer();
		for (List<String> line : records) {
			String teamName, teamId, defaultChannel, defaultMeetingDate, meetingChannel, meetingDate,
					participantId = null;

			teamName = line.get(0).strip();
			teamId = line.get(1).strip();
			defaultChannel = line.get(2).strip();
			defaultMeetingDate = line.get(3).strip();
			meetingChannel = line.get(4).strip();
			meetingDate = line.get(5).strip();
		
			List<String> participantList = new ArrayList<String>();
			int n = 6;
			while (((n) < line.size() && !line.get(n).equals(""))) {
				participantId = line.get(n).strip();
				participantList.add(participantId);
				n = n + 1;
			}
				

			Team team = new Team(teamName, teamId);
			ITeamManagement teamManagement = new TeamManagement(team);
			
			Meeting meeting = null;
			Channel tempChannel = null;
			if (!defaultChannel.equals("")) {
				if (!defaultMeetingDate.equals(""))
					meeting = new Meeting(defaultMeetingDate);
				else
					meeting = new Meeting();
			}
			tempChannel = new MeetingChannel(meeting,defaultChannel);
			teamManagement.addChannel(tempChannel);
			if (!meetingChannel.equals("")) {
				if (!meetingDate.equals("")) {
					meeting = new Meeting(meetingDate);
				} else {
					meeting = new Meeting();
				}
				tempChannel = new PrivateChannel(meeting,meetingChannel);
				for (String id : participantList) {
					((PrivateChannel) tempChannel).addParticipant(id);
				}
				teamManagement.addChannel(tempChannel);
			}
			teams.add(team);
			// create Team
			// add team
		}
		return teams;
	}

	public IContainer<User> readUsers(List<List<String>> records, IContainer<Team> teams) throws ItemExistException {
		IContainer<User> users = new UserContainer();
		IContainer<String> ids = new IdContainer();
		for (List<String> line : records) {
			@SuppressWarnings("unused") // we should look that situation
			String userType, userName, userId, email, password, teamId;
			userType = line.get(0).strip();
			userName = line.get(1).strip();
			userId = line.get(2).strip();
			email = line.get(3).strip();
			password = line.get(4).strip();
			User user;
			switch (userType) {
			case "Student": {

				user = new Student(userName, userId, password);
				break;
			}
			case "Instructor": {

				user = new Instructor(userName, userId, password);
				int n = 5;
				while (((n) < line.size() && !line.get(n).equals(""))) {
					teamId = line.get(n);
					try {
						Team team = teams.getById(teamId);
						TeamManagement teamManagement = new TeamManagement(team);
						teamManagement.addTeamOwner((Academician) user);

					} catch (Exception e) {
						System.out.println(e);
					}
					n = n + 1;
				}
				break;
			}
			case "Teaching Assistant": {

				user = new TeachingAssistant(userName, userId, password);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected user type value: " + userType);
			}

			if (userId.equals("")) {
				checkId(ids, user);
			}

			int n = 5;
			while (((n) < line.size()) && (!line.get(n).equals(""))) {
				teamId = line.get(n);
				try {
					Team team = teams.getById(teamId);

					user.getTeams().add(team);
					TeamManagement teamManagement = new TeamManagement(team);
					teamManagement.addMember(user);

				} catch (Exception e) {
					System.out.println("FileRead - CreateUser - try add(team)" + e);
				}
				n++;

			}
			users.add(user);

		}
		return users;
	}

	private void checkId(IContainer<String> ids, User user) {
		String id = user.createId();
		while (!ids.add(id)) {
			id = user.createId();
		}
		user.setId(id);
	}

}
