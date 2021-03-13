/**
 * 
 */
package teamsTech;

import java.io.IOException;
import java.text.ParseException;
import exception.ItemExistException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserOperationException;
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
	 * @throws UnauthorizedUserOperationException 
	 */
	public static void main(String[] args)
			throws IOException, ItemExistException, ParseException, ItemNotFoundException, NotSupportedException, UnauthorizedUserOperationException {

		IFileIO fr = new FileIO();
		IContainer<Team> teams = fr.readTeams("data\\teamLOST.csv");
		IContainer<User> users = fr.readUsers(teams, "data\\userLeest.csv");
		fr.writeTeams(teams, "data\\teamLEST.csv");
		Program teamsTeach = new Program();
		teamsTeach.start();

	}

}
