package program;

import java.util.Scanner;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserOperationException;
import fileio.FileIO;
import fileio.IFileIO;
import storage.IContainer;
import storage.UserContainer;
import team.Team;
import user.User;

public class Program implements IProgram {

	private User loggedInUser = null;
	private IContainer<User> users = null;
	private IContainer<Team> teams = null;
	private IOperations operations;

	public Program() {
		operations = new Operations();
	}

	/**
	 * This function authenticates a user, looks from system user repo
	 * 
	 * @param email    of the user
	 * @param password of the user
	 * @returns user if found else null
	 */
	private User authenticate(String email, String password) {

		try {
			User user = ((UserContainer) users).getByEmail(email);
			if (user.getPassword().equals(password)) { // if user found by email and has same password
				return user; // Authenticate
			} else { // wrong password
				System.out.println("Password is wrong");
				return null; // return null
			}
		} catch (ItemNotFoundException e) { // otherwise user not found
			System.out.println("User is not found");
			return null; // return null
		}

	}

	public void createTeam() {
		try {
			this.operations.createTeam(loggedInUser, teams); // go operations to create a team
		} catch (UnauthorizedUserOperationException e) {
			System.out.println(e.getMessage());
		}

	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public IContainer<Team> getTeams() {
		return teams;
	}

	public IContainer<User> getUsers() {
		return users;
	}

	private void login() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		User user = null; // Initialize
		while (user == null) { // while user has a valid user in system
			System.out.print("Email : ");
			String email = input.nextLine();
			System.out.print("Password : ");
			String password = input.nextLine();

			user = authenticate(email, password); // Authenticate user, look for we have or not
			if (user != null) { // if authenticated
				this.loggedInUser = user;
				System.out.println(user.getName() + " Welcome to the TeamsTech");
			}
		}
	}


	private void mainMenu() {
		String mainOperationIndex = null;
		try {
			while (true) {
				mainOperationIndex = this.operations.mainOperationsMenu(); // take input
				if (mainOperationIndex.equals("4")) // if 4 exit
					break;
				else
					mainMenuOperations(mainOperationIndex);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Main menu operations
	 * 
	 * @param mainOperationIndex that operation number
	 */
	public void mainMenuOperations(String mainOperationIndex) {
		switch (mainOperationIndex) {
		case "1": {
			this.createTeam();
			break;
		}
		case "2": {
			this.removeTeam();
			break;
		}
		case "3": {
			operations.findTeam(teams); // print teams
			this.updateTeamMenu();
			break;
		}
		case "4": {
			// exit
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + mainOperationIndex);
		}

	}

	/**
	 * This function reads all datas from csv files
	 */
	private void readAll() throws FileFormatException {
		IFileIO fr = new FileIO();
		IContainer<Team> teams = fr.readTeams("data\\teamList.csv");
		IContainer<User> users = fr.readUsers(teams, "data\\userList.csv");
		this.setUsers(users);
		this.setTeams(teams);

	}

	public void removeTeam() {
		try {
			this.operations.findTeam(teams); // print teams
			this.operations.removeTeam(loggedInUser, teams); // and go remove
		} catch (UnauthorizedUserOperationException e) {
			System.out.println(e.getMessage());
		}

	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public void setTeams(IContainer<Team> teams) {
		this.teams = teams;
	}

	public void setUsers(IContainer<User> users) {
		this.users = users;
	}

	public void start() {
		try {
			readAll(); // read all datas
			login(); // login
			mainMenu(); // print main menü
			writeAll(); // than write all datas
		} catch (FileFormatException e) { // if there is an error reading file
			System.out.println(e.getMessage());
		}
	}

	private void updateTeamMenu() {

		try {
			String teamOperationIndex = null;
			@SuppressWarnings("resource") // scanner must close one time in main menu
			Scanner input = new Scanner(System.in);
			System.out.print("Team Id to update: ");

			String teamId = input.nextLine().strip(); // strip for blank
			Team tempTeam = teams.getById(teamId); // get team
			if (tempTeam.isMember(loggedInUser.getId())) { // if user is member of this team
				while (true) {
					teamOperationIndex = this.operations.updateTeamOperationsMenu(); // take input from user to which
																						// operation be done
					if (teamOperationIndex.equals("0")) // if zero go upper menu
						break;
					else { // else do operation
						updateTeamOperations(teamOperationIndex, tempTeam);
					}

				}

			} else { // throw not authorized for not member of a team
				throw new UnauthorizedUserOperationException("You are not authorized to update this team ");
			}
		} catch (ItemNotFoundException | NotSupportedException | UnauthorizedUserOperationException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This function is update team menu
	 * 
	 * @param teamOperationIndex for which operaiton is going to be done
	 * @param team               that is going to be updated
	 * @throws UnauthorizedUserOperationException if user not authorized for some
	 *                                            operations
	 */
	public void updateTeamOperations(String teamOperationIndex, Team team) throws UnauthorizedUserOperationException {
		try {
			switch (teamOperationIndex) {
			case "1": {
				operations.addMeetingChannel(loggedInUser, team);
				break;
			}
			case "2": {
				operations.removeMeetingChannel(loggedInUser, team);
				break;
			}
			case "3": {
				operations.updateChannelOperation(loggedInUser, team);
				break;
			}
			case "4": {
				operations.addTeamMember(users, loggedInUser, team);
				break;
			}
			case "5": {
				operations.removeTeamMember(loggedInUser, team);
				break;
			}
			case "6": {
				operations.addTeamOwner(loggedInUser, team);
				break;
			}
			case "7": {
				operations.removeTeamOwner(loggedInUser, team);
				break;
			}
			case "8": {// team info
				operations.teamInfo(team);
				break;
			}
			case "9": { // team statistics
				operations.statisticOfTeam(team);
				break;
			}
			case "0": { // exit from menu
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + teamOperationIndex);
			}
		} catch (UnauthorizedUserOperationException e) { // catches for lower level functions
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This function writes all datas to csv files
	 */
	private void writeAll() {
		IFileIO fr = new FileIO();
		fr.writeTeams(this.teams, "data\\teamList.csv");
		fr.writeUsers(this.users, "data\\userList.csv");
	}

}
