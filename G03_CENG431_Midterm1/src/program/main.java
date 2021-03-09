package program;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import exception.ItemExistException;
import exception.ItemNotFoundException;
import fileio.FileRead;
import storage.IContainer;
import team.Team;
import user.User;

public class main {

	public static void main(String[] args) throws IOException, ItemExistException, ItemNotFoundException, ParseException
	{
		FileRead fr = new FileRead();
		List<List<String>> lines = null;
		lines = fr.read("data\\teamList.csv");
		IContainer<Team> teams = fr.createTeams(lines);
		
		System.out.println("\nMAIN "+teams.getById("CENG611").toString()+"\n\n");
		
		lines = fr.read("data\\userList.csv");
		IContainer<User> users = fr.createUsers(lines,teams);
		
		System.out.println("MAIN "+users.getLength());
	}
}
