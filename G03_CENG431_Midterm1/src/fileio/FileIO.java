package fileio;

import java.util.List;

import storage.IContainer;
import team.Team;
import user.User;
import exception.FileFormatException;

public class FileIO implements IFileIO {
	private FileRead fRead;
	private FileWrite fWrite;

	public FileIO() {
		this.fRead = new FileRead(); // initialize file read
		this.fWrite = new FileWrite(); // initialize file write
	}

	public IContainer<Team> readTeams(String filePath) throws FileFormatException {
		List<List<String>> lines = fRead.read(filePath); // read file and send it to the readTeams function
		IContainer<Team> teams = fRead.readTeams(lines); // returns the team container which holds all team objects
		return teams; // and return it

	}

	public IContainer<User> readUsers(IContainer<Team> teams, String filePath) {
		List<List<String>> records = fRead.read(filePath); // read file and send it to the readUsers function
		IContainer<User> users = fRead.readUsers(records, teams); // returns the user container which holds all user
																	// objects

		// If it is the first read of the file, write the 
		// updated objects ( ex : userid, pass, email ) to the file
		if (fRead.isFirstRead == true) {
			writeTeams(teams, "data\\teamList.csv");
			writeUsers(users, filePath);
		}
		return users;
	}

	public boolean writeTeams(IContainer<Team> teams, String filePath) {
		// initialize header
		String header = "Team Name,Team ID,Default Channel,Default Meeting Day and Time,Meeting Channel,Meeting Day and Time,Participant ID";
		boolean result = fWrite.writeItems(teams, filePath, header); // and send it to the writeItems
		return result;
	}

	public boolean writeUsers(IContainer<User> users, String filePath) {
		// initialize header
		String header = "User Type,User Name,User ID,Email,Password,Team ID,";
		boolean result = fWrite.writeItems(users, filePath, header); // and send it to the writeItems
		return result;
	}

}
