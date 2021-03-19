package fileio;

import storage.IContainer;
import team.Team;
import user.User;
import exception.FileFormatException;

public interface IFileIO {

	/**
	 * This function reads the teams of the system, file should have a format like
	 * Team Name,Team ID,Default Channel,Default Meeting Day and Time,Meeting
	 * Channel,Meeting Day and Time,Participant(s) ID and all other channels comes
	 * to the end in order Meeting Channel,Meeting Day and Time,Participant(s) ID
	 * Meeting day and time should have a pattern like "DAY HH:MM PERIOD" example
	 * "Monday 09:45 AM" next team should be in a newline
	 * 
	 * @param filePath is the name of the teams file
	 * @return s the read teams from file
	 */
	public IContainer<Team> readTeams(String filePath) throws FileFormatException;

	/**
	 * To read users we should provide teams first to add them to their team User
	 * format should have a pattern like User Type,User Name,User
	 * ID,Email,Password,Team ID1,Team ID2,... next user should be in a newline
	 * 
	 * @param teams    list for add users to their teams
	 * @param filePath is the name of the user file
	 * @returns read user container
	 */
	public IContainer<User> readUsers(IContainer<Team> teams, String filePath);

	/**
	 * This function writes teams in a pattern like mentioned in readTeams method
	 * 
	 * @param teams    that is going to be wrote
	 * @param filePath is the fileName of the teams data file
	 * @returns true if successfully wrote the team ,false if there is a problem
	 *          with the file and container is empty
	 */
	public boolean writeTeams(IContainer<Team> teams, String filePath);

	/**
	 * This function writes users in a pattern like mentioned in readUsers method
	 * 
	 * @param users    that is going to be wrote
	 * @param filePath is the fileName of the users data file
	 * @returns true if successfully wrote the team ,false if there is a problem
	 *          with the file and container is empty
	 */
	public boolean writeUsers(IContainer<User> users, String filePath);
}
