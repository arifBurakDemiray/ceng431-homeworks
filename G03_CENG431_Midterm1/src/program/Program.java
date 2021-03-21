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

			// if user found by email and has same password return it.
			if (user.getPassword().equals(password)) {
				return user;
			} else { // wrong password
				System.out.println("Password is wrong");
				return null; // return null
			}
		} catch (ItemNotFoundException e) { // otherwise user not found
			System.out.println("User " + email + " is not found");
			return null; // return null
		}

	}

	public void createTeam() {
		try {
			this.operations.createTeam(loggedInUser, teams); // invoke createTeam operation of the operations.
		} catch (UnauthorizedUserOperationException e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * @return loggedInUser
	 */
	public User getLoggedInUser() {
		return loggedInUser;
	}

	/*
	 * @return team container which holds all teams in the system
	 */
	public IContainer<Team> getTeams() {
		return teams;
	}

	/*
	 * @return user container which holds all users in the system
	 */
	public IContainer<User> getUsers() {
		return users;
	}

	private void login() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		User user = null; // Initialise user as null

		// Until gotten user mail and password are belong to a valid user in system,
		// loop
		while (user == null) {
			System.out.print("Email : ");
			String email = input.nextLine();
			System.out.print("Password : ");
			String password = input.nextLine();

			user = authenticate(email, password); // Authenticate user, look for valid or not
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
				// Print main menu and take input
				// invoking mainOperationsMenu function
				mainOperationIndex = this.operations.mainOperationsMenu();
				if (mainOperationIndex.equals("4")) {
					System.out.println("Bye bye.");
					break;
				}

				else {
					mainMenuOperations(mainOperationIndex);
				}

			}

		} catch (RuntimeException e) {
			System.out.println("There has been an error in the system\nAll changes saved.");
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
			// print teams to help user select team easily
			operations.findTeam(teams);
			this.updateTeamMenu();
			break;
		}
		case "4": {
			// exit
			System.out.println("See you again " + loggedInUser.getId());
			break;
		}

		default:
			System.out.println("Unexpected value: " + mainOperationIndex);
		}

	}

	/**
	 * This function reads all data from csv files
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
			// print teams to help user select team easily
			this.operations.findTeam(teams);
			this.operations.removeTeam(loggedInUser, teams); // and invoke remove
		} catch (UnauthorizedUserOperationException e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * @param loggedInUser which logged in to the system
	 */
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	/*
	 * @param team container which holds all teams in the system
	 */
	public void setTeams(IContainer<Team> teams) {
		this.teams = teams;
	}

	/*
	 * @param user container which holds all users in the system
	 */
	public void setUsers(IContainer<User> users) {
		this.users = users;
	}

	public void start() {
		try {
			readAll(); // read all data
			login(); // login
			mainMenu(); // print main menu
			writeAll(); // than write all data
		} catch (RuntimeException e) {
			System.out.println("\nThere has been an error in the system\nShutting down.");
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
					// take input from user to which operation be done
					teamOperationIndex = this.operations.updateTeamOperationsMenu();

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
	 * @param teamOperationIndex for which operation is going to be done
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
			case "8": {// channels of the team info
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
				System.out.println("Unexpected value: " + teamOperationIndex);
			}
		} catch (UnauthorizedUserOperationException | IllegalArgumentException e) { // catches for lower level functions
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This function writes all data to csv files
	 */
	private void writeAll() {
		IFileIO fr = new FileIO();
		fr.writeTeams(this.teams, "data\\teamList.csv");
		fr.writeUsers(this.users, "data\\userList.csv");
	}

}
