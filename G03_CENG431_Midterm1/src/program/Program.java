package program;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import exception.ItemExistException;
import exception.ItemNotFoundException;
import fileio.FileRead;
import fileio.FileWrite;
import storage.IContainer;
import team.Team;
import user.User;

public class Program {

	public static void main(String[] args) throws IOException, ItemExistException, ItemNotFoundException, ParseException
	{
		FileRead fr = new FileRead();
		List<List<String>> lines = null;
		lines = fr.read("data\\teamList.csv");
		IContainer<Team> teams = fr.readTeams(lines);
		System.out.println("\nMAIN "+teams.getById("CENG611").toString()+"\n\n");
		
		lines = fr.read("data\\userList.csv");
		IContainer<User> users = fr.readUsers(lines,teams);
		String header = "User Type,User Name,User ID,Email,Password,Team ID,";
		FileWrite file = new FileWrite();
		file.writeItems(users, "data\\userLeest.csv",header);

	}
}
