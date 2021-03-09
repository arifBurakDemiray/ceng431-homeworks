package fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import channel.Meeting;
import exception.ItemExistException;
import storage.IContainer;
import storage.TeamContainer;
import storage.UserContainer;
import team.Team;
import user.Academician;
import user.Instructor;
import user.Student;
import user.TeachingAssistant;
import user.User;

public class FileRead {

	public List<List<String>> read(String filePath) throws IOException {

		List<List<String>> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				
				String[] values = line.split(",",-1);

				records.add(Arrays.asList(values));
			}
		}
		return records;

	}

	@SuppressWarnings("deprecation")
	public IContainer<Team> createTeams(List<List<String>> records) throws ItemExistException, ParseException {
		IContainer<Team> teams = new TeamContainer();
		for (List<String> line : records) {
			String teamName, teamId, defaultChannel, defaultMeetingDate, meetingChannel, meetingDate, participantId = null;
			
			teamName = line.get(0);
			teamId = line.get(1);
			defaultChannel = line.get(2);
			defaultMeetingDate = line.get(3);
			meetingChannel = line.get(4);
			meetingDate = line.get(5);
			participantId = line.get(6); // "X,Y"
			String[] participantList = participantId.intern().split(",");
			
			Team team = new Team(teamName, teamId);
			Meeting meeting = null;
			if(defaultChannel != null)
			{
				if(defaultMeetingDate != null)
					meeting = new Meeting(defaultMeetingDate);
				else
					meeting = new Meeting();
			}
			teams.add(team);
			System.out.println(team);
			// create Team
			// add team
		}
		return teams;
	}

	public IContainer<User> createUsers(List<List<String>> records, IContainer<Team> teams) throws ItemExistException {
		IContainer<User> users = new UserContainer();
		
		for (List<String> line : records) {
			String userType, userName, userId, email, password, teamId;
			userType = line.get(0);
			userName = line.get(1);
			userId = line.get(2);
			email = line.get(3);
			password = line.get(4);
			User user;
			switch (userType) {
			case "Student": {
				
				user = new Student(userName, userId, password);
				break;
			}
			case "Instructor": {

				user = new Instructor(userName, userId, password);
				int n = 5;
				while (line.get(n) != null && (n+1)<line.size()) {
					teamId = line.get(n);
					try {
						Team team = teams.getById(teamId);
				
						team.addTeamOwner((Academician) user);
						
					} catch (Exception e) {
						System.out.println(e);
					}
					n++;
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
			
			int n = 5;
			while (line.get(n) != null && (n+1)<line.size()) {
				teamId = line.get(n);
				try {
					Team team = teams.getById(teamId);
					
					user.getTeamList().add(team);
					
					
					team.addMember(user);
					
				} catch (Exception e) {
					System.out.println("FileRead - CreateUser - try add(team)"+ e);
				}
				n++;
				
			}
			users.add(user);

		}
		return users;
	}

}
