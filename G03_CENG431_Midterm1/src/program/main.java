package program;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import exception.ItemExistException;
import exception.ItemNotFoundException;
import fileio.FileRead;
import storage.IContainer;
import team.Team;

public class main {

	public static void main(String[] args) throws IOException, ItemExistException, ItemNotFoundException, ParseException
	{
		FileRead fr = new FileRead();
		List<List<String>> lines = fr.read("data\\teamList.csv");
		IContainer<Team> teams = fr.createTeams(lines);
		
		System.out.println("MAIN\n\n"+teams.getById("CENG611").toString());
	}
}
