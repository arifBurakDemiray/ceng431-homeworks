package view;

import auth.Login;
import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserException;
import exception.WrongCredentialException;
import factory.Creator;
import fileio.FileController;
import user.User;

public class AppView extends View {

	private User user;

	public AppView() {
		super();
	}

	/**
	 * The constructor creates a View invoking superclass' constructor.
	 * 
	 * @param fileController = holds containers ( file data )
	 * @param creator        = creator to create any type of instance of class
	 */
	protected AppView(FileController fileController, Creator creator) {
		super(fileController, creator);
	}

	/**
	 * The function to initialise the all data invoking fileController.readAll()
	 * 
	 * @throws FileFormatException
	 */
	private void init() throws FileFormatException {
		fileController.readAll();
	}

	/**
	 * The function tries to login the user with gotten user name and password by
	 * invoking login.login() function
	 * 
	 * @throws WrongCredentialException
	 * @throws ItemNotFoundException
	 * @throws NotSupportedException
	 */
	private void login() throws WrongCredentialException, ItemNotFoundException, NotSupportedException {
		String userName = this.inputReceiver.getString("Username : ");
		String password = this.inputReceiver.getString("Password : ");
		Login login = new Login();
		// Try to log in user if user exists in the system, else throw
		// WrongCredentialException
		this.user = login.login(userName, password, fileController.users());
	}

	/**
	 * The function initialise the processes of the app view
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	public void start() {
		try {
			// initialise the all data invoking fileController.readAll()
			init();
			// Print menu and get a choice from user
			menu();
		} catch (RuntimeException e) {
			// For unexpected runtime exceptions
			inputReceiver.close();
			System.out.println("\nThere is a problem with the system, all changes saved.");
			save(); // save data
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * The function to save the all data invoking fileController.writeAll()
	 */
	private void save() {
		try {
			fileController.writeAll();
		} catch (FileFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * The function prints menu and get a menu choice from user
	 */
	@Override
	protected void menu() {
		System.out.println("\n\tAPP MENU\n\n1: Login\n2: Exit");
		String menuNumber = "";
		while (!menuNumber.equals("2")) {
			menuNumber = inputReceiver.getString("\nLogin Choice : ");
			if (menuNumber.equals("2")) {
				inputReceiver.close();
				System.out.println("See you again.");
			} else if (menuNumber.equals("1")) {
				try {
					login();
					summonUserView();
					save();
				} catch (WrongCredentialException | ItemNotFoundException | NotSupportedException
						| UnauthorizedUserException e) {
					System.out.println(e.getMessage());
				}
			} else
				System.out.println("Write a valid number");
		}
	}

	/**
	 * According to logged in user type, invoke the new user view
	 * 
	 * @throws UnauthorizedUserException
	 */
	private void summonUserView() throws UnauthorizedUserException {
		UserView userView = new UserView(this.user, fileController, creator);
		userView.start();
	}

}
