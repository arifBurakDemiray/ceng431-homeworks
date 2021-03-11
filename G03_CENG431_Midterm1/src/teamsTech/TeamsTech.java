/**
 * 
 */
package teamsTech;

import java.io.IOException;
import java.text.ParseException;
import exception.ItemExistException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileIO;
import fileio.IFileIO;
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
		
		IFileIO fr = new FileIO();
		IContainer<Team> teams = fr.readTeams("data\\teamList.csv");
		System.out.println("\nMAIN "+teams.getById("CENG611").toString()+"\n\n");
		IContainer<User> users = fr.readUsers(teams,"data\\userList.csv");
		fr.writeUsers(users, "data\\userLeest.csv");
		fr.writeTeams(teams, "data\\teamLeaest.csv");
		Program teamsTeach = new Program();
		teamsTeach.start();

	}

}


