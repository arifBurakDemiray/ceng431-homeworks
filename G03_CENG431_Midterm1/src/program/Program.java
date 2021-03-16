package program;

import java.util.Scanner;

import exception.ItemNotFoundException;
import exception.UnauthorizedUserOperationException;
import fileio.FileIO;
import fileio.IFileIO;
import storage.IContainer;
import storage.UserContainer;
import team.Team;
import user.User;

public class Program {

	private User loggedInUser = null;
	private IContainer<User> users = null;
	private IContainer<Team> teams = null;
	private Operations operations;

	public Program() {
		operations = new Operations();
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public IContainer<User> getUsers() {
		return users;
	}

	public void setUsers(IContainer<User> users) {
		this.users = users;
	}

	public IContainer<Team> getTeams() {
		return teams;
	}

	public void setTeams(IContainer<Team> teams) {
		this.teams = teams;
	}

	public void start() {
		readAll();
		login();
		menu();
		writeAll();
	}
	
	public void createTeam()
	{
		try {
			this.operations.createTeam(loggedInUser, teams);
		} catch (UnauthorizedUserOperationException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void removeTeam()
	{
		try {
			this.operations.findTeam(teams);
			this.operations.removeTeam(loggedInUser, teams);
		} catch (UnauthorizedUserOperationException e) {
			System.out.println(e.getMessage());
		}
		
	}
	;

	private User authenticate(String email, String password) {

		try {
			User user = ((UserContainer) users).getByEmail(email);
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				System.out.println("Password is wrong");
				return null;
			}
		} catch (ItemNotFoundException e) {
			System.out.println("User is not found");
			return null;
		}

	}
	
	private void menu()
	{
		String mainOperationIndex=null;
		try {
			while(true)
			{
				mainOperationIndex = this.operations.mainOperationsMenu();
				if(mainOperationIndex.equals("4"))
					break;
				else
					mainOperations(mainOperationIndex);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	public void mainOperations(String mainOperationIndex) {
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

			break;
		}
		case "4": {

			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + mainOperationIndex);
		}

	}

	private void login() {
		Scanner input = new Scanner(System.in);
		User user = null;
		while (user == null) {
			System.out.print("Email : ");
			String email = input.nextLine();
			System.out.print("Password : ");
			String password = input.nextLine();

			user = authenticate(email, password);
			if (user != null) {
				this.loggedInUser = user;
				System.out.println(user.toString());
			}
		}

	}

	private void readAll() {
		IFileIO fr = new FileIO();
		IContainer<Team> teams = fr.readTeams("data\\teamList.csv");
		IContainer<User> users = fr.readUsers(teams, "data\\userList.csv");
		this.setUsers(users);
		this.setTeams(teams);

	}

	private void writeAll() {
		IFileIO fr = new FileIO();
		fr.writeTeams(this.teams, "data\\teamLEST.csv");
		fr.writeUsers(this.users, "data\\userLEST.csv");
	}

}
