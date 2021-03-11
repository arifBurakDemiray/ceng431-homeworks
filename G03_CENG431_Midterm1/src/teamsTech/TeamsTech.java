/**
 * 
 */
package teamsTech;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import exception.ItemExistException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileRead;
import fileio.FileWrite;
import program.Program;
import storage.IContainer;
import team.Team;
import user.User;

public class TeamsTech {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws ItemExistException 
	 * @throws NotSupportedException 
	 * @throws ItemNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ItemExistException, ParseException, ItemNotFoundException, NotSupportedException {
		
		FileRead fr = new FileRead();
		List<List<String>> lines = null;
		lines = fr.read("data\\teamList.csv");
		IContainer<Team> teams = fr.readTeams(lines);
		System.out.println("\nMAIN "+teams.getById("CENG611").toString()+"\n\n");
		
		lines = fr.read("data\\userList.csv");
		IContainer<User> users = fr.readUsers(lines,teams);
		String header1 = "User Type,User Name,User ID,Email,Password,Team ID,";
		String header2 = "Team Name,Team ID,Default Channel,Default Meeting Day and Time,Meeting Channel,Meeting Day and Time,Participant ID";
		FileWrite file = new FileWrite();
		file.writeItems(users, "data\\userLeest.csv",header1);
		file.writeItems(teams, "data\\teamLeaest.csv",header2);
		Program teamsTeach = new Program();
		teamsTeach.start();

	}

}


