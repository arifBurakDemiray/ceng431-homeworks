package fileio;

import java.util.List;

import storage.IContainer;
import team.Team;
import user.User;

public class FileIO implements IFileIO {
	private FileRead fRead;
	private FileWrite fWrite;

	public FileIO() {
		this.fRead = new FileRead();
		this.fWrite = new FileWrite();
	}

	public IContainer<Team> readTeams(String filePath) {
		List<List<String>> lines = fRead.read(filePath);
		return fRead.readTeams(lines);

	}

	public IContainer<User> readUsers(IContainer<Team> teams, String filePath) {
		List<List<String>> records = fRead.read(filePath);
		return fRead.readUsers(records, teams);
	}

	public boolean writeTeams(IContainer<Team> teams, String filePath) {
		String header = "Team Name,Team ID,Default Channel,Default Meeting Day and Time,Meeting Channel,Meeting Day and Time,Participant ID";
		return fWrite.writeItems(teams, filePath, header);
	}

	public boolean writeUsers(IContainer<User> users, String filePath) {
		String header = "User Type,User Name,User ID,Email,Password,Team ID,";
		return fWrite.writeItems(users, filePath, header);
	}

}
