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

	protected AppView(FileController fileController, Creator creator) {
		super(fileController, creator);
	}

	private void init() throws FileFormatException {
		fileController.readAll();
	}

	private void login() throws WrongCredentialException, ItemNotFoundException, NotSupportedException {
		String userName = this.inputReceiver.getString("Username : ");
		String password = this.inputReceiver.getString("Password : ");
		Login login = new Login();
		this.user = login.login(userName, password, fileController.users());
	}
	
	public void start() {
		try {
			init();
			menu();
		} catch (RuntimeException e) {
			inputReceiver.close();
			System.out.println("\nThere is a problem with the system, all changes saved.");
			save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void save() {
		try {
			fileController.writeAll();
		} catch (FileFormatException e) {
			System.out.println(e.getMessage());
		}
	}

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

	private void summonUserView() throws UnauthorizedUserException {
		UserView userView = new UserView(this.user, fileController, creator);
		userView.start();
	}

}
