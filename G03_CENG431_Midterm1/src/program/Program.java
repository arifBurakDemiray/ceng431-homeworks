package program;

import java.util.Scanner;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
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

	public Program() {
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
		writeAll();
		login();

	}

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

	private void login() {
		Scanner input = new Scanner(System.in);
		User user = null;
		while (user == null) {
			System.out.print("Email : ");
			String email = input.next();
			System.out.print("Password : ");
			String password = input.next();

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
